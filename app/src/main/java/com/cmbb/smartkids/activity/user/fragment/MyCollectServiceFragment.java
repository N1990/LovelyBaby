package com.cmbb.smartkids.activity.user.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.ActiveDetailActivity;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.activity.serve.model.ServiceOrderModel;
import com.cmbb.smartkids.activity.user.adapter.MyServiceAdapter;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 11:54
 */
public class MyCollectServiceFragment extends BaseFragment{
    private final String TAG = MyCollectServiceFragment.class.getSimpleName();
    private final int SERVICE_DETAIL_REQUEST = 10009;
    private LoadMoreRecyclerView lmrv;
    private MyServiceAdapter adapter;
    private int pager = 0;
    private int pagerSize = 5;



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
        adapter = new MyServiceAdapter();
        adapter.setData(new ArrayList<ServiceListModel.DataEntity.RowsEntity>());
        lmrv.setAdapter(adapter);
    }

    private void initData() {

    }

    private void addListener() {
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
        adapter.setOnItemClick(itemClick);
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

    private CustomListener.ItemClickListener itemClick = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            ServiceListModel.DataEntity.RowsEntity itemData = (ServiceListModel.DataEntity.RowsEntity) object;
            Intent intent = new Intent(getActivity(), ActiveDetailActivity.class);
            intent.putExtra("serviceId", itemData.getId());
            startActivityForResult(intent, SERVICE_DETAIL_REQUEST);
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SERVICE_DETAIL_REQUEST && resultCode == -1) {
            adapter.clearData();
            pager = 0;
            handleRequest(pager, pagerSize);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void handleRequest(int pager, int pagerSize){
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.ServiceInfo.COLLECT_SERVICE_REQUEST, BaseApplication.token, params, ServiceListModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                showShortToast(msg);
                ServiceListModel list = (ServiceListModel) object;
                if(list != null && list.getData() != null && list.getData().getRecords() != 0){
                    adapter.addData(list.getData().getRows(), lmrv);
                    lmrv.setPullLoadMoreCompleted();
                }else{
                    showShortToast(msg);
                }
                if(adapter.getDataSize() == 0)
                    lmrv.setNoContent();

            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }
}
