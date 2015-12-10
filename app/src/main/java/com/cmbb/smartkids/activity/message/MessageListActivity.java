package com.cmbb.smartkids.activity.message;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.message.adapter.MsgOfficalAdapter;
import com.cmbb.smartkids.activity.message.adapter.MsgOrderAdapter;
import com.cmbb.smartkids.activity.message.adapter.MsgReverAdapter;
import com.cmbb.smartkids.activity.message.adapter.MsgServiceAdapter;
import com.cmbb.smartkids.activity.order.OrderDetailActivity;
import com.cmbb.smartkids.activity.serve.ActiveDetailActivity;
import com.cmbb.smartkids.activity.user.HomeMessageActivity;
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

public class MessageListActivity extends BaseActivity {
    private final String TAG = MessageListActivity.class.getSimpleName();
    private LoadMoreRecyclerView lmrv;
    private NestedScrollView nsvEmpty;
    private MsgOfficalAdapter officalAdapter;
    private MsgOrderAdapter orderAdapter;
    private MsgReverAdapter reverAdapter;
    private MsgServiceAdapter serviceAdapter;
    private int pager = 0;
    private int pagerSize = 10;
    private int tag = -1; // 1 官方消息  2 订单状态   3 回复提醒  4 关注服务

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
    }


    private void initView() {
        lmrv = (LoadMoreRecyclerView) findViewById(R.id.lmrv_self);
        nsvEmpty = (NestedScrollView) findViewById(R.id.nsv_self);
        lmrv.setLinearLayout();
    }

    private void initData() {
        Bundle bundle = null;
        if(getIntent() != null && (bundle = getIntent().getExtras()) != null){
            tag = bundle.getInt("tag", -1);
        }
        if(tag == 1){
            reflushOfficalView();
        }else if(tag == 2){
           reflushOrderView();
        }else if(tag == 3){
            reflushReverView();
        }else if(tag == 4){
            reflushServiceView();
        }else{
            setTitle("消  息");
            lmrv.setVisibility(View.GONE);
            nsvEmpty.setVisibility(View.VISIBLE);
            showShortToast("传参数出错~");
        }
    }

    /**
     * 官方消息列表
     */
    private void reflushOfficalView(){
        setTitle("官方消息");
        officalAdapter = new MsgOfficalAdapter();
        officalAdapter.setData(new ArrayList<String>());
        lmrv.setAdapter(officalAdapter);
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        officalAdapter.setOnFooterTryAgain(this);
        officalAdapter.setOnItemListener(onItemListener);
    }


    /**
     * 订单消息列表
     */
    private void reflushOrderView(){
        setTitle("订单动态");
        orderAdapter = new MsgOrderAdapter();
        orderAdapter.setData(new ArrayList<String>());
        lmrv.setAdapter(orderAdapter);
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        orderAdapter.setOnFooterTryAgain(this);
        orderAdapter.setOnItemListener(onItemListener);
    }


    /**
     * 回复提醒列表
     */
    private void reflushReverView(){
        setTitle("回复提醒");
        reverAdapter = new MsgReverAdapter();
        reverAdapter.setData(new ArrayList<String>());
        lmrv.setAdapter(reverAdapter);
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        reverAdapter.setOnFooterTryAgain(this);
        reverAdapter.setOnItemListener(onItemListener);
    }


    /**
     * 服务消息列表
     */
    private void reflushServiceView(){
        setTitle("关注服务");
        serviceAdapter = new MsgServiceAdapter();
        serviceAdapter.setData(new ArrayList<String>());
        lmrv.setAdapter(serviceAdapter);
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        serviceAdapter.setOnFooterTryAgain(this);
        serviceAdapter.setOnItemListener(onItemListener);
    }




    private CustomListener.ItemClickListener onItemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            switch (tag){
                case 1:
                    showShortToast("官方消息" + position);
                    break;
                case 2:
                    showShortToast("订单动态" + position);
                    break;
                case 3:
                    showShortToast("回复提醒" + position);
                    break;
                case 4:
                    showShortToast("关注服务" + position);
                    break;
            }
        }
    };















    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {
//            showWaitDialog();
            handleRequest(pager, pagerSize);
            lmrv.setPullLoadMoreCompleted();
        }

        @Override
        public void onRefresh() {
//            adapter.clearData();
            pager = 0;
            handleRequest(pager, pagerSize);
            lmrv.setPullLoadMoreCompleted();
        }

        @Override
        public void onLoadMore() {
            pager++;
            handleRequest(pager, pagerSize);
            lmrv.setPullLoadMoreCompleted();
        }
    };


    /*private CustomListener.ItemClickListener onItemListener = new CustomListener.ItemClickListener() {
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
                        Intent order = new Intent(MessageListActivity.this, OrderDetailActivity.class);
                        order.putExtra("orderCode", relationId);
                        startActivity(order);
                    } else if ("service".equals(type)) {
                        Intent service = new Intent(MessageListActivity.this, ActiveDetailActivity.class);
                        service.putExtra("serviceId", Integer.parseInt(relationId));
                        startActivity(service);
                    }
                } else {
                    handleMessageRequest(messageId, position, type, relationId, messageId);
                }
            } else if ("system".equals(type)) {
                if (isRead == 1) {
                    Intent system = new Intent(MessageListActivity.this, HomeMessageActivity.class);
                    system.putExtra("id", messageId);
                    startActivity(system);
                } else {
                    handleMessageRequest(messageId, position, type, relationId, messageId);
                }
            }

        }
    };*/


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
       /* NetRequest.postRequest(Constants.ServiceInfo.MESSAGE_LIST_REQUEST, BaseApplication.token, params, MessageListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                MessageListModel result = (MessageListModel) object;
                if (result.getData() != null && result.getData().getRows() != null && result.getData().getRows().size() > 0) {
                    lmrv.setHasContent();
//                    adapter.addData(result.getData().getRows(), lmrv);
                }
              *//*  if (adapter.getDataSize() == 0) {
                    lmrv.setNoContent();
                }*//*
                showShortToast(msg);


            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                showShortToast(message);
            }
        }));*/
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
//                adapter.setRead(position);
                if ("order".equals(type)) {
                    Intent order = new Intent(MessageListActivity.this, OrderDetailActivity.class);
                    order.putExtra("orderCode", relationId);
                    startActivity(order);
                } else if ("service".equals(type)) {
                    Intent service = new Intent(MessageListActivity.this, ActiveDetailActivity.class);
                    service.putExtra("serviceId", Integer.parseInt(relationId));
                    startActivity(service);
                } else if ("system".equals(type)) {
                    Intent system = new Intent(MessageListActivity.this, HomeMessageActivity.class);
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


    /**
     * @param context
     * @param tag
     * tag : 1 官方消息  2 订单状态   3 回复提醒  4 关注服务
     */
    public static void newInstance(Context context, int tag){
        Intent intent = new Intent(context, MessageListActivity.class);
        intent.putExtra("tag", tag);
        context.startActivity(intent);
    }


}
