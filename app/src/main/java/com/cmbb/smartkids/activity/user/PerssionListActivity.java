package com.cmbb.smartkids.activity.user;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.model.EvaluateListModel;
import com.cmbb.smartkids.activity.user.adapter.MyPerssionAdapter;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

public class PerssionListActivity extends BaseActivity implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private final String TAG = PerssionListActivity.class.getSimpleName();

    private MyPerssionAdapter adapter;
    public SmartRecyclerView smartRecyclerView;
    private int myCenter = 0;
    private int pager = 0;
    private int pagerSize = 5;
    private int isPopman;
    private int cachePager = -1; //缓存上次的pager

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_perssion_list;
    }

    private void initData() {
        Bundle bundle = null;
        if (getIntent() != null && (bundle = getIntent().getExtras()) != null) {
            isPopman = bundle.getInt("isPop");
        } else {
            showShortToast("传参出错~");
            return;
        }
    }

    private void initView() {
        smartRecyclerView = (SmartRecyclerView) findViewById(R.id.srv_self);
        smartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyPerssionAdapter(this);
        smartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        smartRecyclerView.setRefreshListener(this);
    }


    /*@Override
    public void onItemClick(int position) {
        ServiceListModel.DataEntity.RowsEntity item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UserCenterActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }*/

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
        EvaluateListModel.getMeServiceRequest(isPopman + "", pager, pagerSize, new OkHttpClientManager.ResultCallback<EvaluateListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(EvaluateListModel response) {
                if (response != null && response.getData() != null && response.getData().getRows().size() > 0 && cachePager != pager) {
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


    @Override
    public void onItemClick(int position) {

    }


}
