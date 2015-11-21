package com.cmbb.smartkids.activity.user.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.ActiveDetailActivity;
import com.cmbb.smartkids.activity.serve.model.MyServiceListModel;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.activity.user.adapter.MyServiceAdapter;
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
 * 创建时间：2015/10/12 19:15
 */
public class ServiceFragment extends BaseFragment {
    private final String TAG = ServiceFragment.class.getSimpleName();
    public LoadMoreRecyclerView lmrv;
    private MyServiceAdapter adapter;
    private int myCenter = 0;
    private int pager = 0;
    private int pagerSize = 5;
    private String userId, isPopman;

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
        Bundle bundle = null;
        if(getArguments() != null && (bundle = getArguments()) != null){
            userId = bundle.getString("userId");
            isPopman = bundle.getString("isPopman");
        }else{
            showShortToast("传参出错~");
            return;
        }

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
//            showWaitsDialog();
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
            ServiceListModel.DataEntity.RowsEntity item = (ServiceListModel.DataEntity.RowsEntity) object;
            Intent intent = new Intent(getActivity(), ActiveDetailActivity.class);
            intent.putExtra("serviceId", item.getId());
            startActivity(intent);
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void handleRequest(int pager, int pagerSize){
        HashMap<String, String> params = new HashMap<>();
        params.put("myCenter", String.valueOf(myCenter));
        params.put("isEredar", String.valueOf(isPopman));
        params.put("id", String.valueOf(userId));
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.ServiceInfo.MY_SERVICE_REQUEST, BaseApplication.token, params, ServiceListModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                ServiceListModel listModel = (ServiceListModel) object;
                if (listModel != null && listModel.getData() != null && listModel.getData().getRecords() != 0) {
                    adapter.addData(listModel.getData().getRows(), lmrv);
                } else {
                    showShortToast(msg);
                }
                if (adapter.getDataSize() == 0){
                    lmrv.setVisibility(View.GONE);
                    getView().findViewById(R.id.nsv_self).setVisibility(View.VISIBLE);
                }
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
