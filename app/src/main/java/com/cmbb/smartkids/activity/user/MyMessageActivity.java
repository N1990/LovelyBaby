

package com.cmbb.smartkids.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.order.view.GenerateOrder;
import com.cmbb.smartkids.activity.serve.view.ServerDetailActivity;
import com.cmbb.smartkids.activity.user.adapter.MyMsgAdapter;
import com.cmbb.smartkids.activity.user.model.MessageListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

import java.util.HashMap;

public class MyMessageActivity extends BaseActivity implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private final String TAG = MyMessageActivity.class.getSimpleName();
    public SmartRecyclerView smartRecyclerView;
    private MyMsgAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        smartRecyclerView = (SmartRecyclerView) findViewById(R.id.srv_self);
        smartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyMsgAdapter(this);
        smartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        smartRecyclerView.setRefreshListener(this);
    }


    /**
     * 消息置为已读
     *
     * @param id
     * @param position
     * @param type
     * @param relationId
     */
    private void handleMessageRequest(int id, final int position, final String type, final String relationId, final int messageId) {
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id + "");
        params.put("isRead", "1");
        params.put("token", BaseApplication.token);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.MESSAGE_REEAD_REQUEST, params, new OkHttpClientManager.ResultCallback<SecurityCodeModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                hideWaitDialog();
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(SecurityCodeModel response) {
                hideWaitDialog();
                if (response != null) {
                    adapter.setRead(position);
                    if ("order".equals(type)) {
                        GenerateOrder.newInstance(MyMessageActivity.this, relationId);


                    } else if ("service".equals(type)) {
                        ServerDetailActivity.newIntent(MyMessageActivity.this, Integer.parseInt(relationId));

                    } else if ("system".equals(type)) {
                        Intent system = new Intent(MyMessageActivity.this, HomeMessageActivity.class);
                        system.putExtra("id", messageId);
                        startActivity(system);
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
                    GenerateOrder.newInstance(MyMessageActivity.this, relationId);

                } else if ("service".equals(type)) {
                    ServerDetailActivity.newIntent(MyMessageActivity.this, Integer.parseInt(relationId));

                }
            } else {
                handleMessageRequest(messageId, position, type, relationId, messageId);
            }
        } else if ("system".equals(type)) {
            if (isRead == 1) {
                Intent system = new Intent(MyMessageActivity.this, HomeMessageActivity.class);
                system.putExtra("id", messageId);
                startActivity(system);
            } else {
                handleMessageRequest(messageId, position, type, relationId, messageId);
            }
        }
    }

    @Override
    public void onLoadMore() {
        pager++;
        MessageListModel.getMessageListRequest(pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<MessageListModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
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
        MessageListModel.getMessageListRequest(pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<MessageListModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
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
