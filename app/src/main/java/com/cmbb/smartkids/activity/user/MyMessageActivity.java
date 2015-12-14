

package com.cmbb.smartkids.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.order.OrderDetailActivity;
import com.cmbb.smartkids.activity.serve.ActiveDetailActivity;
import com.cmbb.smartkids.activity.user.adapter.MyMsgAdapter;
import com.cmbb.smartkids.activity.user.model.MessageListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyMessageActivity extends BaseActivity {
    private final String TAG = MyMessageActivity.class.getSimpleName();
    private LoadMoreRecyclerView lmrv;
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
        addListener();
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_notify_message));
        lmrv = (LoadMoreRecyclerView) findViewById(R.id.lmrv_self);
        lmrv.setLinearLayout();
        adapter = new MyMsgAdapter();
        adapter.setData(new ArrayList<MessageListModel.DataEntity.RowsEntity>());
        lmrv.setAdapter(adapter);
    }


    private void addListener() {
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
        adapter.setOnItemListener(onItemListener);
    }

    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {
            showWaitDialog();
            handleRequest(pager, pagerSize);
        }

        @Override
        public void onRefresh() {
            adapter.clearData();
            pager = 0;
            handleRequest(pager, pagerSize);
        }

        @Override
        public void onLoadMore() {
            pager++;
            handleRequest(pager, pagerSize);
        }
    };

    private CustomListener.ItemClickListener onItemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            MessageListModel.DataEntity.RowsEntity item = (MessageListModel.DataEntity.RowsEntity) object;
            int messageId = item.getId();
            String relationId = item.getRelateField();
            String type = item.getModual();
            int isRead = item.getIsRead();
            if (!TextUtils.isEmpty(relationId)) {
                if (isRead == 1) {
                    if ("order".equals(type)) {
                        Intent order = new Intent(MyMessageActivity.this, OrderDetailActivity.class);
                        order.putExtra("orderCode", relationId);
                        startActivity(order);
                    } else if ("service".equals(type)) {
                        Intent service = new Intent(MyMessageActivity.this, ActiveDetailActivity.class);
                        service.putExtra("serviceId", Integer.parseInt(relationId));
                        startActivity(service);
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
    };


    /**
     * 消息列表
     *
     * @param pager
     * @param pagerSize
     */
    private void handleRequest(int pager, int pagerSize) {
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.ServiceInfo.MESSAGE_LIST_REQUEST, BaseApplication.token, params, MessageListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                MessageListModel result = (MessageListModel) object;
                if (result.getData() != null && result.getData().getRows() != null && result.getData().getRows().size() > 0) {
                    lmrv.setHasContent();
                    adapter.addData(result.getData().getRows(), lmrv);
                }
                if (adapter.getDataSize() == 0) {
                    lmrv.setNoContent();
                }
                showShortToast(msg);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                showShortToast(message);
            }
        }));
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
        NetRequest.postRequest(Constants.ServiceInfo.MESSAGE_REEAD_REQUEST, BaseApplication.token, params, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                SecurityCodeModel result = (SecurityCodeModel) object;
                adapter.setRead(position);
                if ("order".equals(type)) {
                    Intent order = new Intent(MyMessageActivity.this, OrderDetailActivity.class);
                    order.putExtra("orderCode", relationId);
                    startActivity(order);
                } else if ("service".equals(type)) {
                    Intent service = new Intent(MyMessageActivity.this, ActiveDetailActivity.class);
                    service.putExtra("serviceId", Integer.parseInt(relationId));
                    startActivity(service);
                } else if ("system".equals(type)) {
                    Intent system = new Intent(MyMessageActivity.this, HomeMessageActivity.class);
                    system.putExtra("id", messageId);
                    startActivity(system);
                }
                showShortToast(msg);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }


}
