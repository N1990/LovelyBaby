package com.cmbb.smartkids.activity.order.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.order.EvaluateListActivity;
import com.cmbb.smartkids.activity.order.adapter.MyOrderAdapter;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.activity.order.OrderDetailActivity;
import com.cmbb.smartkids.activity.order.model.RefundModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.model.OrderStatus;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.pay.PayActivity;
import com.cmbb.smartkids.widget.wheelview.CustomDialogBuilder;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/14 16:29
 */
public class AllOrderFragment extends BaseFragment {
    private final String TAG = AllOrderFragment.class.getSimpleName();
    private final int HANDLER_ORDER_REQUEST = 11111;
    private LoadMoreRecyclerView lmrv;
    private MyOrderAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;
    private CustomDialogBuilder builder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerview_layout, null);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        addListener();
    }

    private void initView(){
        lmrv = (LoadMoreRecyclerView) getView().findViewById(R.id.lmrv_self);
        lmrv.setLinearLayout();
        adapter = new MyOrderAdapter();
        adapter.setData(new ArrayList<OrderListModel.DataEntity.RowsEntity>());// 模拟数据
        lmrv.setAdapter(adapter);
    }

    private void initData(){

    }

    private void addListener(){
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
        adapter.setOnItemListener(itemListener);
        adapter.setOnHandlerListener(onHandlerListener);
        adapter.setOnPayListener(onPayListener);
    }

    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {
            showWaitsDialog();
            handleRequest(pager, pagerSize);

        }

        @Override
        public void onRefresh() {
            adapter.clearData();
            pager = 0;
            handleRequest(pager, pagerSize);
        }

        @Override
        public void onLoadMore() {
            pager ++;
            handleRequest(pager, pagerSize);
        }
    };

    private CustomListener.ItemClickListener itemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            OrderListModel.DataEntity.RowsEntity itemData = (OrderListModel.DataEntity.RowsEntity) object;
            String orderCode = itemData.getOrderCode();
            Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
            intent.putExtra("orderCode", orderCode);
            startActivityForResult(intent, HANDLER_ORDER_REQUEST);
        }
    };


    private CustomListener.ItemClickListener onHandlerListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            OrderListModel.DataEntity.RowsEntity itemData = (OrderListModel.DataEntity.RowsEntity) object;
            listViewHandler(itemData);
        }
    };

    private CustomListener.ItemClickListener onPayListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            OrderListModel.DataEntity.RowsEntity data = (OrderListModel.DataEntity.RowsEntity) object;
            Intent pay = new Intent(getActivity(), PayActivity.class);
            pay.putExtra("order_title", data.getTitle());
            pay.putExtra("order_price", data.getPrice());
            pay.putExtra("orderCode", String.valueOf(data.getOrderCode()));
            startActivityForResult(pay, HANDLER_ORDER_REQUEST);
//            showShortToast("立即支付功能尚未开通");
        }
    };


    /**
     * 处理列表右下方按钮事件
     * @param data
     */
    public void listViewHandler(OrderListModel.DataEntity.RowsEntity data) {
        OrderStatus status = OrderStatus.getStatusByValue(data.getStatus());
        switch (status) {
            case WEI_ZHI_FU:
                showCustomDialog(data.getOrderCode());
                break;
            case YI_ZHI_FU:
                double price = Double.valueOf(data.getPrice());
                if(price != 0){
                    showRefundDialog(data.getOrderCode());
//                    handleApplyRefund(data.getOrderCode());
//                    showShortToast("申请退款功能尚未开通...");
                }else{
                    showCustomDialog(data.getOrderCode());
                }
                break;
            case YI_GUO_QI:
                showRefundDialog(data.getOrderCode());
//                handleApplyRefund(data.getOrderCode());
                break;
            case YI_CAN_JIA:
                Intent evaluate = new Intent(getActivity(), EvaluateListActivity.class);
                evaluate.putExtra("order_code", data.getOrderCode());
                evaluate.putExtra("service_id", data.getServiceId());
                startActivityForResult(evaluate, HANDLER_ORDER_REQUEST);
                break;
            case YI_PING_JIA:
            case YI_TUI_KUAN:
                break;

        }
    }

    private void showCustomDialog(final String orderCode){
        builder = CustomDialogBuilder.getInstance(getActivity()).withTitle("取消订单")
                .withMessage("您确认要取消此订单吗？取消之后您需重新预订此服务...")
                .withComfirmText("确认", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        if(builder != null)
                            builder.setDialogDismiss();
                        handleCancelRequest(orderCode);
                    }
                })
                .withCancelText("取消", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        if(builder != null)
                            builder.dismiss();
                    }
                });
        builder.show();
    }

    private void showRefundDialog(final String orderCode){
        builder = CustomDialogBuilder.getInstance(getActivity()).withTitle("申请退款")
                .withMessage("您确认要申请退款吗？退款之后您需重新预订此服务...")
                .withComfirmText("确认", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        if(builder != null)
                            builder.setDialogDismiss();
                        handleApplyRefund(orderCode);
                    }
                })
                .withCancelText("取消", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        if(builder != null)
                            builder.dismiss();
                    }
                });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == HANDLER_ORDER_REQUEST && resultCode == -1){
            showWaitsDialog();
            adapter.clearData();
            pager = 0;
            handleRequest(pager, pagerSize);
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(builder != null)
            builder.setDialogDismiss();
    }

    private void handleRequest(int pager, int pagerSize){ //ORDER_LIST_REQUEST
        HashMap<String, String> params = new HashMap<>();
        params.put("queryType", "0");
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.ServiceInfo.ORDER_LIST_REQUEST, BaseApplication.token, params, OrderListModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                OrderListModel orderList = (OrderListModel) object;
                if (orderList != null && orderList.getData() != null && orderList.getData().getRecords() != 0) {
                    adapter.addData(orderList.getData().getRows(), lmrv);
                } else {
                    showShortToast(msg);
                }
                if (adapter.getDataSize() == 0)
                    lmrv.setNoContent();
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                showShortToast(message);
            }
        }));
    }

    /**取消订单
     * @param orderCode
     */
    private void handleCancelRequest(String orderCode){
        showWaitsDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("orderCode", orderCode);
        NetRequest.postRequest(Constants.ServiceInfo.CANCEL_ORDER_REQUEST, BaseApplication.token, params, SecurityCodeModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                SecurityCodeModel result = (SecurityCodeModel) object;
                if(result != null){
                    showShortToast(result.getMsg());
                    adapter.clearData();
                    handleRequest(0, pagerSize);
                }else{
                    hideWaitDialog();
                    showShortToast("订单取消失败");
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }


    /**
     * 申请退款
     * @param orderCode
     */
    private void handleApplyRefund(String orderCode){
        showWaitsDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("orderCode", orderCode);
        NetRequest.postRequest(Constants.ServiceInfo.APPLY_REFUND_REQUEST, BaseApplication.token, params, RefundModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                RefundModel result = (RefundModel) object;
                if(result != null){
                    showWaitsDialog();
                    adapter.clearData();
                    pager = 0;
                    handleRequest(pager, pagerSize);
                }else{
                    showShortToast("退款中...");
//                    showShortToast(msg);
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }



}
