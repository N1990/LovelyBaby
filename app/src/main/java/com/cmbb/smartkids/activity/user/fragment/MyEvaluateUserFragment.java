package com.cmbb.smartkids.activity.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.MyEvaluateUserAdapter;
import com.cmbb.smartkids.activity.user.model.UserEvaluateModel;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;
import com.umeng.socialize.utils.Log;

/**
 * Created by javon on 16/3/21.
 */
public class MyEvaluateUserFragment extends BaseFragment implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{
    private final String TAG = MyEvaluateUserFragment.class.getSimpleName();
    private SmartRecyclerView srv;
    private MyEvaluateUserAdapter adapter;
    private int isEredar;
    private int pager = 0;
    private int pagerSize = 10;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerview_layout, container, false);
        srv = (SmartRecyclerView) root.findViewById(R.id.srv_self);
        srv.setLayoutManager(new LinearLayoutManager(getActivity()));
        srv.setRefreshListener(this);
        adapter = new MyEvaluateUserAdapter(getActivity());
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        srv.setAdapterWithProgress(adapter);
        if(getArguments() != null){
            isEredar = getArguments().getInt("isEredar");
            onRefresh();
        }else{
            showShortToast("传参出错~");
        }
        return root;
    }

    @Override
    public void onRefresh() {
        pager = 0;
        UserEvaluateModel.getEvaluateUserRequest(isEredar, pager, pagerSize, new OkHttpClientManager.ResultCallback<UserEvaluateModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(UserEvaluateModel response) {
                if(response != null){
                    adapter.clear();
                    adapter.addAll(response.getData().getRows());
                }
                showShortToast(response.getMsg());
            }
        });
    }

    @Override
    public void onLoadMore() {
        pager ++;
        UserEvaluateModel.getEvaluateUserRequest(isEredar, pager, pagerSize, new OkHttpClientManager.ResultCallback<UserEvaluateModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(UserEvaluateModel response) {
                if(response != null){
                    adapter.addAll(response.getData().getRows());
                }
                showShortToast(response.getMsg());
            }
        });

    }

}
