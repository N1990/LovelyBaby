package com.cmbb.smartkids.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.model.MyServiceListModel;
import com.cmbb.smartkids.activity.user.adapter.ServiceOrderAdapter;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/20 14:26
 */
public class MyServiceOrderActivity extends BaseActivity implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private final String TAG = MyServiceOrderActivity.class.getSimpleName();
    private SmartRecyclerView srv;
    private ServiceOrderAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_service_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_my_service_order));
        srv = (SmartRecyclerView) findViewById(R.id.srv_self);
        srv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ServiceOrderAdapter(this);
        srv.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        srv.setRefreshListener(this);
        onRefresh();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void onLoadMore() {
        pager++;
        MyServiceListModel.handleMyServiceListRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<MyServiceListModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(MyServiceListModel response) {
                if (response != null) {
                    adapter.addAll(response.getData().getRows());
                }
            }
        });

    }

    @Override
    public void onRefresh() {
        pager = 0;
        MyServiceListModel.handleMyServiceListRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<MyServiceListModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(MyServiceListModel response) {
                if (response != null) {
                    adapter.clear();
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MyServiceOrderActivity.this, MyAcceptActivity.class);
        intent.putExtra("serviceId", adapter.getItem(position).getId());
        startActivity(intent);
    }
}
