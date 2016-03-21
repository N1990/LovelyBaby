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
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.activity.user.adapter.MyCareAdapter;
import com.cmbb.smartkids.activity.user.model.FriendListModel;
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
 * 创建时间：2015/9/8 13:11
 */
public class MyCareFriendFragment extends BaseFragment implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private final String TAG = MyCareFriendFragment.class.getSimpleName();
    private final int USER_CENTER_REQUEST = 1101;
    public SmartRecyclerView smartRecyclerView;
    private MyCareAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;


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
        smartRecyclerView = (SmartRecyclerView) view.findViewById(R.id.srv_self);
        smartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyCareAdapter(getActivity());
        smartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        smartRecyclerView.setRefreshListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == USER_CENTER_REQUEST && resultCode == -1) {
            adapter.clear();
            onRefresh();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onItemClick(int position) {
        UserCenterActivity.newIntent((AppCompatActivity) getActivity(), adapter.getItem(position).getUserId(), USER_CENTER_REQUEST);
    }

    @Override
    public void onLoadMore() {
        pager++;
        FriendListModel.getFriendListRequest(0,pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<FriendListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(FriendListModel response) {
                if (response != null) {
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pager = 0;
        FriendListModel.getFriendListRequest(0,pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<FriendListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(FriendListModel response) {
                if (response != null) {
                    adapter.clear();
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }
}
