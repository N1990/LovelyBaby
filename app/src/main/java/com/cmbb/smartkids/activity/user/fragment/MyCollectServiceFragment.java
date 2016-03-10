package com.cmbb.smartkids.activity.user.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.ActiveDetailActivity;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.activity.user.adapter.MyServiceAdapter;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 11:54
 */
public class MyCollectServiceFragment extends BaseFragment implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private final String TAG = MyCollectServiceFragment.class.getSimpleName();
    private final int SERVICE_DETAIL_REQUEST = 10009;
    private SmartRecyclerView srv;
    private MyServiceAdapter adapter;
    private int pager = 0;
    private int pagerSize = 5;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerview_layout_v, null);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        showWaitsDialog();
        handleRequest(pager, pagerSize);
    }

    private void initView() {
        srv = (SmartRecyclerView) getView().findViewById(R.id.srv_self);
        srv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyServiceAdapter(getActivity());
        srv.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        srv.setRefreshListener(this);
    }

    @Override
    public void onItemClick(int position) {
        ServiceListModel.DataEntity.RowsEntity itemData =  adapter.getItem(position);
        Intent intent = new Intent(getActivity(), ActiveDetailActivity.class);
        intent.putExtra("serviceId", itemData.getId());
        startActivityForResult(intent, SERVICE_DETAIL_REQUEST);
    }

    @Override
    public void onLoadMore() {
        pager ++;
        handleRequest(pager, pagerSize);
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        pager = 0;
        handleRequest(pager, pagerSize);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SERVICE_DETAIL_REQUEST && resultCode == -1) {
            adapter.clear();
            pager = 0;
            handleRequest(pager, pagerSize);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void handleRequest(int pager, int pagerSize){
        ServiceListModel.getCollectServiceRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<ServiceListModel>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(ServiceListModel response) {
                hideWaitDialog();
                showShortToast(response.getMsg());
                if(response != null && response.getData() != null && response.getData().getRecords() != 0){
                    adapter.addAll(response.getData().getRows());
                }else{
                    showShortToast(response.getMsg());
                }
            }
        });
    }
}
