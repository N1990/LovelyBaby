package com.cmbb.smartkids.activity.user.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.activity.serve.view.ServerDetailActivity;
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
        return inflater.inflate(R.layout.recyclerview_layout, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        onRefresh();
    }

    private void initView(View view) {
        srv = (SmartRecyclerView) view.findViewById(R.id.srv_self);
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
        ServiceListModel.DataEntity.RowsEntity itemData = adapter.getItem(position);
        ServerDetailActivity.newIntent((AppCompatActivity) getActivity(), itemData.getId(), SERVICE_DETAIL_REQUEST);
    }

    @Override
    public void onLoadMore() {
        pager++;
        handleRequest(pager, pagerSize, false);
    }

    @Override
    public void onRefresh() {
        pager = 0;
        handleRequest(pager, pagerSize, true);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SERVICE_DETAIL_REQUEST && resultCode == -1) {
            adapter.clear();
            pager = 0;
            handleRequest(pager, pagerSize, true);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void handleRequest(int pager, int pagerSize, final boolean refresh) {
        ServiceListModel.getCollectServiceRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<ServiceListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(getString(R.string.is_netwrok));
            }

            @Override
            public void onResponse(ServiceListModel response) {
                hideWaitDialog();
                if (response != null) {
                    if (refresh)
                        adapter.clear();
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }
}
