package com.cmbb.smartkids.activity.order.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.MyAcceptActivity;
import com.cmbb.smartkids.activity.order.adapter.AcceptOrderAdapter;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/15 11:50
 */
public class AcceptOrderFragment extends BaseFragment {

    private final String TAG = VerifyOrderFragment.class.getSimpleName();
    private LoadMoreRecyclerView lmrv;
    private AcceptOrderAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;
    private int serviceId;

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

    private void initView() {
        lmrv = (LoadMoreRecyclerView) getView().findViewById(R.id.lmrv_self);
        lmrv.setLinearLayout();
        adapter = new AcceptOrderAdapter();
        adapter.setData(new ArrayList<OrderListModel.DataEntity.RowsEntity>());// 模拟数据
        lmrv.setAdapter(adapter);
    }

    private void initData() {
        Bundle bundle = getArguments();
        if(bundle !=  null)
            serviceId = bundle.getInt("serviceId", -1);
    }

    private void addListener() {
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
//        adapter.setOnHeaderListener(itemListener);
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

   /* private CustomListener.ItemClickListener itemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            OrderListModel.DataEntity.RowsEntity itemData = (OrderListModel.DataEntity.RowsEntity) object;
            Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
            intent.putExtra("orderId", itemData.getOrderCode());
            intent.putExtra("fromList", true);
            startActivity(intent);
        }
    };*/


    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MyAcceptActivity.VERIFY_ORDER_REQUEST && resultCode == -1){
            pager = 0;
            handleRequest(pager, pagerSize);
            Log.e(TAG, TAG + " is reflush");
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void handleRequest(int pager, int pagerSize){
        HashMap<String, String> params = new HashMap<>();
        params.put("queryType", "1");
        params.put("status", "2,4");
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("serviceId", String.valueOf(serviceId));
        NetRequest.postRequest(Constants.ServiceInfo.ORDER_LIST_REQUEST, BaseApplication.token, params, OrderListModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                OrderListModel listModel = (OrderListModel) object;
                if(listModel != null && listModel.getData() != null && listModel.getData().getRecords() != 0){
                    adapter.addData(listModel.getData().getRows(), lmrv);
                }else{
                    showShortToast(msg);
                }
                if(adapter.getDataSize() == 0)
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
