package com.cmbb.smartkids.activity.user.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.user.adapter.MyCommunityAdapter;
import com.cmbb.smartkids.base.BaseApplication;
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
public class CommunityFragment extends BaseFragment implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private final String TAG = MyCollectCommunityFragment.class.getSimpleName();
    private final int COMMUNITY_DETAIL_REQUEST = 10020;
    public SmartRecyclerView smartRecyclerView;
    private MyCommunityAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;
    private String userId;
    private int cachePager = -1;


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
        adapter = new MyCommunityAdapter(getActivity());
        smartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        smartRecyclerView.setRefreshListener(this);
    }

    private void initData() {
        Bundle bundle = null;
        if ((bundle = getArguments()) != null) {
            userId = bundle.getString("userId");
        } else {
            showShortToast("传参出错~");
            return;
        }
    }


    private void handleRequest(final int pager, int pagerSize, final boolean flag) {
        TopicListModel.getTopicListRequest(0, pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicListModel>() {
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
