package com.cmbb.smartkids.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.user.adapter.MyCommunityAdapter;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

public class MyCommunityActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private final String TAG = MyCommunityActivity.class.getSimpleName();
    private final int COMMUNITY_DETAIL_REQUEST = 10102;
    public SmartRecyclerView smartRecyclerView;
    private MyCommunityAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;
    private String userId;
    private int cachePager = -1;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_community;
    }

    private void initView() {
        setTitle("我的话题");
        smartRecyclerView = (SmartRecyclerView) findViewById(R.id.srv_self);
        smartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyCommunityAdapter(this);
        smartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        smartRecyclerView.setRefreshListener(this);
    }


    private void handleRequest(final int pager, int pagerSize, final boolean flag) {
        TopicListModel.getTopicListRequest(1, pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicListModel>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        showShortToast(e.toString());
                    }

                    @Override
                    public void onResponse(TopicListModel response) {
                        if (response != null) {
                            if (flag)
                                adapter.clear();
                            adapter.addAll(response.getData().getRows());
                        }
                    }
                }
        );
    }


    @Override
    public void onPause() {
        super.onPause();
        cachePager = pager;
    }

    @Override
    public void onItemClick(int position) {

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
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == COMMUNITY_DETAIL_REQUEST) {
            // 刷新页面
            pager = 0;
            onRefresh();
        }
    }


}
