package com.cmbb.smartkids.activity.order.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.order.adapter.OrderAdapter;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.model.OrderStatus;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.cmbb.smartkids.widget.wheelview.CustomDialogBuilder;
import com.squareup.okhttp.Request;

/**
 * Created by javon on 16/3/17.
 */
public class BaseOrderListActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private final int HANDLER_ORDER_REQUEST = 11111;
    private SmartRecyclerView srv;
    protected OrderAdapter adapter;
    protected int pager = 0;
    protected int pagerSize = 10;
    private CustomDialogBuilder builder;


    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview_layout_v;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        onRefresh();
    }

    private void initView() {
        srv = (SmartRecyclerView) findViewById(R.id.srv_self);
        srv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderAdapter(this);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        srv.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(onItemListener);
        adapter.setOnCancelListener(onCancelListener);
        adapter.setOnHandlerListener(onHandlerListener);
        adapter.setOnCkeckOrderListener(onCheckOrderListener);
        srv.setRefreshListener(this);
    }

    private RecyclerArrayAdapter.OnItemClickListener onItemListener = new RecyclerArrayAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            OrderListModel.DataEntity.RowsEntity itemData = adapter.getItem(position);
            String orderCode = itemData.getOrderCode();
            GenerateOrder.newInstance(BaseOrderListActivity.this, orderCode, HANDLER_ORDER_REQUEST);
        }
    };

    private CustomListener.ItemClickListener onCancelListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            OrderListModel.DataEntity.RowsEntity itemData = (OrderListModel.DataEntity.RowsEntity) object;
            showCustomDialog(itemData.getOrderCode());
        }
    };

    private CustomListener.ItemClickListener onHandlerListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            OrderListModel.DataEntity.RowsEntity data = (OrderListModel.DataEntity.RowsEntity) object;
            listViewHandler(data);
        }
    };


    private CustomListener.ItemClickListener onCheckOrderListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            OrderListModel.DataEntity.RowsEntity itemData = adapter.getItem(position);
            String orderCode = itemData.getOrderCode();
            GenerateOrder.newInstance(BaseOrderListActivity.this, orderCode, HANDLER_ORDER_REQUEST);
        }
    };


    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }

    /**
     * 处理列表右下方按钮事件
     *
     * @param data
     */
    public void listViewHandler(OrderListModel.DataEntity.RowsEntity data) {
        OrderStatus status = OrderStatus.getStatusByValue(data.getStatus());
        switch (status) {
            case WEI_ZHI_FU:
                Intent pay = new Intent(BaseOrderListActivity.this, PayConfirm.class);
                pay.putExtra("order_code", data.getOrderCode());
                startActivityForResult(pay, HANDLER_ORDER_REQUEST);
                break;
            case YI_ZHI_FU:
                showRefundDialog(data.getOrderCode());
                break;
            case YI_GUO_QI:
                showRefundDialog(data.getOrderCode());
                break;
            case YI_CAN_JIA:
                EvaluateOrderActivity.newInstance(BaseOrderListActivity.this, data.getServiceId(), data.getOrderCode(), HANDLER_ORDER_REQUEST);
                break;
            case YI_PING_JIA:
            case YI_TUI_KUAN:
                break;

        }
    }

    private void showCustomDialog(final String orderCode) {
        builder = CustomDialogBuilder.getInstance(this).withTitle("取消订单")
                .withMessage("您确认要取消此订单吗？取消之后您需重新预订此服务...")
                .withComfirmText("确认", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        if (builder != null)
                            builder.setDialogDismiss();
                        handleCancelRequest(orderCode);
                    }
                })
                .withCancelText("取消", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        if (builder != null)
                            builder.dismiss();
                    }
                });
        builder.show();
    }

    private void showRefundDialog(final String orderCode) {
        builder = CustomDialogBuilder.getInstance(this).withTitle("申请退款")
                .withMessage("您确认要申请退款吗？退款之后您需重新预订此服务...")
                .withComfirmText("确认", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        if (builder != null)
                            builder.setDialogDismiss();
                        ReimburseActivity.newInstance(BaseOrderListActivity.this, orderCode, HANDLER_ORDER_REQUEST);
                    }
                })
                .withCancelText("取消", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        if (builder != null)
                            builder.dismiss();
                    }
                });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == HANDLER_ORDER_REQUEST && resultCode == -1) {
            pager = 0;
            onRefresh();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 取消订单
     *
     * @param orderCode
     */
    private void handleCancelRequest(String orderCode) {
        showWaitDialog();
        SecurityCodeModel.handleCancelOrderRequest(orderCode, new OkHttpClientManager.ResultCallback<SecurityCodeModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                hideWaitDialog();
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(SecurityCodeModel response) {
                hideWaitDialog();
                if (response != null) {
                    showShortToast(response.getMsg());
                    pager = 0;
                    onRefresh();
                } else {
                    showShortToast("订单取消失败");
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (builder != null)
            builder.setDialogDismiss();
    }

}
