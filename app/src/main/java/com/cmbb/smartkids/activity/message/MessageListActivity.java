package com.cmbb.smartkids.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.CommunityDetailActivity;
import com.cmbb.smartkids.activity.message.adapter.MsgOfficalAdapter;
import com.cmbb.smartkids.activity.message.adapter.MsgOrderAdapter;
import com.cmbb.smartkids.activity.message.adapter.MsgReverAdapter;
import com.cmbb.smartkids.activity.message.adapter.MsgServiceAdapter;
import com.cmbb.smartkids.activity.message.model.MessageCountModel;
import com.cmbb.smartkids.activity.message.model.MessageListModel;
import com.cmbb.smartkids.activity.order.OrderDetailActivity;
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

    private MessageCountModel.DataEntity dataEntity;//获取的参数

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
        if (getIntent() != null && (bundle = getIntent().getExtras()) != null) {
            dataEntity = bundle.getParcelable("DataEntity");
        }
        if (dataEntity == null) return;
        switch (dataEntity.getModual()) {
            case "system":
                reflushOfficalView();
                break;
            case "order":
                reflushOrderView();
                break;
            case "topic":
                reflushReverView();
                break;
            case "service":
                reflushServiceView();
                break;
            default:
                setTitle("消  息");
                lmrv.setVisibility(View.GONE);
                nsvEmpty.setVisibility(View.VISIBLE);
                showShortToast("传参数出错~");
                break;
        }
    }

    /**
     * 官方消息列表
     */
    private void reflushOfficalView() {
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
    private void reflushOrderView() {
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
    private void reflushReverView() {
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
    private void reflushServiceView() {
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
            switch (dataEntity.getModual()) {
                case "system":
                    OfficialMessageActivity.newInstance(MessageListActivity.this, ((MessageListModel.DataEntity.RowsEntity) object).getContents());
                    break;
                case "order":
                    OrderDetailActivity.newInstance(MessageListActivity.this, ((MessageListModel.DataEntity.RowsEntity) object).getRelateField());
                    break;
                case "topic":
                    CommunityDetailActivity.newInstance(MessageListActivity.this, Integer.valueOf(((MessageListModel.DataEntity.RowsEntity) object).getRelateField()));
                    break;
                case "service":
//                    handleServiceRequest(pager, pagerSize);
                    break;
            }
        }
    };


    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {
            pager = 0;
            handleEmptyMessageRequest(dataEntity.getId() + "");
            switch (dataEntity.getModual()) {
                case "system":
                    officalAdapter.clearData();
                    handleSystemRequest(pager, pagerSize);
                    break;
                case "order":
                    orderAdapter.clearData();
                    handleOrderRequest(pager, pagerSize);
                    break;
                case "topic":
                    reverAdapter.clearData();
                    handleTopicRequest(pager, pagerSize);
                    break;
                case "service":
                    serviceAdapter.clearData();
                    handleServiceRequest(pager, pagerSize);
                    break;
            }
        }

        @Override
        public void onRefresh() {
            pager = 0;
            switch (dataEntity.getModual()) {
                case "system":
                    officalAdapter.clearData();
                    handleSystemRequest(pager, pagerSize);
                    break;
                case "order":
                    orderAdapter.clearData();
                    handleOrderRequest(pager, pagerSize);
                    break;
                case "topic":
                    reverAdapter.clearData();
                    handleTopicRequest(pager, pagerSize);
                    break;
                case "service":
                    serviceAdapter.clearData();
                    handleServiceRequest(pager, pagerSize);
                    break;
            }
        }

        @Override
        public void onLoadMore() {
            pager++;
            switch (dataEntity.getModual()) {
                case "system":
                    handleSystemRequest(pager, pagerSize);
                    break;
                case "order":
                    handleOrderRequest(pager, pagerSize);
                    break;
                case "topic":
                    handleTopicRequest(pager, pagerSize);
                    break;
                case "service":
                    handleServiceRequest(pager, pagerSize);
                    break;
            }
        }
    };


    /**
     * 系统消息列表
     *
     * @param pager
     * @param pagerSize
     */
    private void handleSystemRequest(int pager, int pagerSize) {
        HashMap<String, String> params = new HashMap<>();
        params.put("modual", dataEntity.getModual());
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.ServiceInfo.MESSAGE_GET_PAGE, BaseApplication.token, params, MessageListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                MessageListModel result = (MessageListModel) object;
                if (result.getData() != null && result.getData().getRows() != null && result.getData().getRows().size() > 0) {
                    lmrv.setHasContent();
                    officalAdapter.addData(result.getData().getRows(), lmrv);
                }
                if (officalAdapter.getDataSize() == 0) {
                    lmrv.setNoContent();
                }
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
     * 订单消息列表
     *
     * @param pager
     * @param pagerSize
     */
    private void handleOrderRequest(int pager, int pagerSize) {
        HashMap<String, String> params = new HashMap<>();
        params.put("modual", dataEntity.getModual());
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.ServiceInfo.MESSAGE_GET_PAGE, BaseApplication.token, params, MessageListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                MessageListModel result = (MessageListModel) object;
                if (result.getData() != null && result.getData().getRows() != null && result.getData().getRows().size() > 0) {
                    lmrv.setHasContent();
                    orderAdapter.addData(result.getData().getRows(), lmrv);
                }
                if (orderAdapter.getDataSize() == 0) {
                    lmrv.setNoContent();
                }
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
     * 话题消息列表
     *
     * @param pager     页码
     * @param pagerSize 页面加载数量
     */
    private void handleTopicRequest(int pager, int pagerSize) {
        HashMap<String, String> params = new HashMap<>();
        params.put("modual", dataEntity.getModual());
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.ServiceInfo.MESSAGE_GET_PAGE, BaseApplication.token, params, MessageListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                MessageListModel result = (MessageListModel) object;
                if (result.getData() != null && result.getData().getRows() != null && result.getData().getRows().size() > 0) {
                    lmrv.setHasContent();
                    reverAdapter.addData(result.getData().getRows(), lmrv);
                }
                if (reverAdapter.getDataSize() == 0) {
                    lmrv.setNoContent();
                }
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
     * 服务消息列表
     *
     * @param pager
     * @param pagerSize
     */
    private void handleServiceRequest(int pager, int pagerSize) {
        HashMap<String, String> params = new HashMap<>();
        params.put("modual", dataEntity.getModual());
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.ServiceInfo.MESSAGE_GET_PAGE, BaseApplication.token, params, MessageListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                MessageListModel result = (MessageListModel) object;
                if (result.getData() != null && result.getData().getRows() != null && result.getData().getRows().size() > 0) {
                    lmrv.setHasContent();
                    serviceAdapter.addData(result.getData().getRows(), lmrv);
                }
                if (serviceAdapter.getDataSize() == 0) {
                    lmrv.setNoContent();
                }
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
     * @param context    Context
     * @param dataEntity MessageCountModel.DataEntity
     */
    public static void newInstance(BaseActivity context, MessageCountModel.DataEntity dataEntity) {
        Intent intent = new Intent(context, MessageListActivity.class);
        intent.putExtra("DataEntity", dataEntity);
        context.startActivityForResult(intent, 10);
    }


    /**
     * 至空消息
     *
     * @param id 消息ID
     */
    private void handleEmptyMessageRequest(String id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        NetRequest.postRequest(Constants.ServiceInfo.MESSAGE_SET_MESSAGE_TYPE, BaseApplication.token, params, MessageCountModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

    @Override
    public void onBackPressed() {
        setResult(100);
        super.onBackPressed();
    }
}
