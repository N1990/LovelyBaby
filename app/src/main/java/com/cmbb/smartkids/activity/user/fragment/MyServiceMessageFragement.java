package com.cmbb.smartkids.activity.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.order.view.GenerateOrder;
import com.cmbb.smartkids.activity.serve.view.ServerDetailActivity;
import com.cmbb.smartkids.activity.user.adapter.MyMsgAdapter;
import com.cmbb.smartkids.activity.user.model.MessageListModel;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 13:20
 */
public class MyServiceMessageFragement extends BaseFragment implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private final String TAG = MyServiceMessageFragement.class.getSimpleName();
    public SmartRecyclerView smartRecyclerView;
    private MyMsgAdapter adapter;
    private int pager = 0;
    private int pagerSize = 15;


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
        adapter = new MyMsgAdapter(getActivity());
        smartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        smartRecyclerView.setRefreshListener(this);
    }

    private void handleMessageRequest(int id, final int position, final String type, final String relationId) {
        showWaitsDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id + "");
        params.put("isRead", "1");
        params.put("token", BaseApplication.token);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.MESSAGE_REEAD_REQUEST, params, new OkHttpClientManager.ResultCallback<SecurityCodeModel>() {
            @Override
            public void onError(Request request, Exception e) {
                hideWaitDialog();
                showShortToast(getString(R.string.is_netwrok));
            }

            @Override
            public void onResponse(SecurityCodeModel response) {
                hideWaitDialog();
                if (response != null) {
                    adapter.setRead(position);
                    if ("order".equals(type)) {
                        GenerateOrder.newInstance(getActivity(), relationId);
                    } else if ("service".equals(type)) {
                        ServerDetailActivity.newIntent(getActivity(), Integer.parseInt(relationId));
                    }
                    showShortToast(response.getMsg());
                }
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        int messageId = adapter.getItem(position).getId();
        String relationId = adapter.getItem(position).getRelateField();
        String type = adapter.getItem(position).getModual();
        int isRead = adapter.getItem(position).getIsRead();
        if (!TextUtils.isEmpty(relationId)) {
            if (isRead == 1) {
                if ("order".equals(type)) {
                    GenerateOrder.newInstance(getActivity(), relationId);
                } else if ("service".equals(type)) {
                    ServerDetailActivity.newIntent(getActivity(), Integer.parseInt(relationId));
                }
            } else {
                handleMessageRequest(messageId, position, type, relationId);
            }
        }
    }

    @Override
    public void onLoadMore() {
        pager++;
        MessageListModel.getMessageListRequest(pager, pagerSize, 0, BaseApplication.token, new OkHttpClientManager.ResultCallback<MessageListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(getString(R.string.is_netwrok));
            }

            @Override
            public void onResponse(MessageListModel response) {
                if (response != null) {
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pager = 0;
        MessageListModel.getMessageListRequest(pager, pagerSize, 0, BaseApplication.token, new OkHttpClientManager.ResultCallback<MessageListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(getString(R.string.is_netwrok));
            }

            @Override
            public void onResponse(MessageListModel response) {
                if (response != null) {
                    adapter.clear();
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }
}
