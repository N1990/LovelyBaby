package com.cmbb.smartkids.activity.order.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.EvaluateListActivity;
import com.cmbb.smartkids.activity.order.OrderDetailActivity;
import com.cmbb.smartkids.activity.order.adapter.MyOrderAdapter;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.model.OrderStatus;
import com.cmbb.smartkids.network.NetRequest;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/14 16:38
 */
public class ReimburseOrderFragment extends BaseFragment {
    private final String TAG = AllOrderFragment.class.getSimpleName();
    private final int HANDLER_ORDER_REQUEST = 11112;
    private LoadMoreRecyclerView lmrv;
    private MyOrderAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recyclerview_layout, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        addListener();
    }

    private void initView() {
        lmrv = (LoadMoreRecyclerView) getView().findViewById(R.id.lmrv_self);
        lmrv.setLinearLayout();
        adapter = new MyOrderAdapter();
        adapter.setData(new ArrayList<OrderListModel.DataEntity.RowsEntity>());// 模拟数据
        lmrv.setAdapter(adapter);
    }

    private void initData() {

    }

    private void addListener() {
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
        adapter.setOnItemListener(itemListener);
        adapter.setOnHandlerListener(onHandlerListener);
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
            pager++;
            handleRequest(pager, pagerSize);
        }
    };

    private CustomListener.ItemClickListener itemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            OrderListModel.DataEntity.RowsEntity itemData = (OrderListModel.DataEntity.RowsEntity) object;
            String orderId = itemData.getOrderCode();
            Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
            intent.putExtra("orderCode", orderId);
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

    /**
     * 处理列表右下方按钮事件
     *
     * @param data
     */
    public void listViewHandler(OrderListModel.DataEntity.RowsEntity data) {
        OrderStatus status = OrderStatus.getStatusByValue(data.getStatus());
        switch (status) {
            case WEI_ZHI_FU:

                break;
            case YI_ZHI_FU:
            case YI_GUO_QI:
                showShortToast("申请退款功能尚未开通...");
                break;
            case YI_CAN_JIA:
                Intent evaluate = new Intent(getActivity(), EvaluateListActivity.class);
                evaluate.putExtra("service_id", data.getServiceId());
                evaluate.putExtra("order_code", data.getOrderCode());
                startActivityForResult(evaluate, HANDLER_ORDER_REQUEST);
                break;
            case YI_PING_JIA:
            case YI_TUI_KUAN:
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == HANDLER_ORDER_REQUEST && resultCode == -1) {
            showWaitsDialog();
            adapter.clearData();
            pager = 0;
            handleRequest(pager, pagerSize);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void handleRequest(int pager, int pagerSize) { //ORDER_LIST_REQUEST
        HashMap<String, String> params = new HashMap<>();
        params.put("queryType", "0");
        params.put("status", "6");
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
}
