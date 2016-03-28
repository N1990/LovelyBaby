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
 * 创建时间：2015/10/12 19:15
 */
public class ServiceFragment extends BaseFragment implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private final String TAG = ServiceFragment.class.getSimpleName();
    public SmartRecyclerView smartRecyclerView;
    private MyServiceAdapter adapter;
    private int myCenter = 0;
    private int pager = 0;
    private int pagerSize = 5;
    private String userId, isPopman;
    private int cachePager = -1; //缓存上次的pager

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
        onRefresh();
    }


    private void initView() {
        smartRecyclerView = (SmartRecyclerView) getView().findViewById(R.id.srv_self);
        smartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyServiceAdapter(getActivity());
        smartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        smartRecyclerView.setRefreshListener(this);
    }

    private void initData() {
        Bundle bundle = null;
        if (getArguments() != null && (bundle = getArguments()) != null) {
            userId = bundle.getString("userId");
            isPopman = bundle.getString("isPopman");
        } else {
            showShortToast("传参出错~");
        }
    }

    @Override
    public void onItemClick(int position) {
        ServiceListModel.DataEntity.RowsEntity item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), ActiveDetailActivity.class);
        intent.putExtra("serviceId", item.getId());
        startActivity(intent);
    }

    @Override
    public void onLoadMore() {
        pager++;
        handleRequest(pager, pagerSize, false);
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        pager = 0;
        handleRequest(pager, pagerSize, true);
    }


    private void handleRequest(final int pager, int pagerSize, final boolean flag) {
        ServiceListModel.getUserCenterServiceRequest(myCenter, isPopman, userId, pager, pagerSize, new OkHttpClientManager.ResultCallback<ServiceListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(ServiceListModel response) {
                if (response != null) {
                    if (flag) {
                        adapter.clear();
                    }
                    adapter.addAll(response.getData().getRows());
                }
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        cachePager = pager;
    }
}
