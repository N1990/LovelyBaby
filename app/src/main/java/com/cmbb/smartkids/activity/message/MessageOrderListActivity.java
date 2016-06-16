package com.cmbb.smartkids.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.message.adapter.MsgOrderAdapter;
import com.cmbb.smartkids.activity.message.model.MessageCountModel;
import com.cmbb.smartkids.activity.message.model.MessageListModel;
import com.cmbb.smartkids.activity.order.view.GenerateOrder;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.cmbb.smartkids.utils.log.Log;
import com.squareup.okhttp.Request;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/21 上午11:27
 */
public class MessageOrderListActivity extends BaseActivity implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private static final String TAG = MessageOrderListActivity.class.getSimpleName();

    protected SmartRecyclerView mSmartRecyclerView;
    private MsgOrderAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;
    private MessageCountModel.DataEntity dataEntity;//获取的参数

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("订单消息");
        Log.i(TAG, "订单消息");
        dataEntity = getIntent().getParcelableExtra("DataEntity");
        initRecyclerView();
        onRefresh();
    }

    private void initRecyclerView() {
        mSmartRecyclerView = (SmartRecyclerView) findViewById(R.id.recyclerView);
        mSmartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MsgOrderAdapter(this);
        mSmartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        mSmartRecyclerView.setRefreshListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_list;
    }

    @Override
    public void onItemClick(int position) {
        GenerateOrder.newInstance(this, adapter.getItem(position).getRelateField());
    }

    @Override
    public void onLoadMore() {
        pager++;
        MessageListModel.getOfficialMessageRequest(dataEntity.getModual(), pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<MessageListModel>() {
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
        MessageCountModel.handleEmptyMessageRequest(dataEntity.getId() + "", null);

        MessageListModel.getOfficialMessageRequest(dataEntity.getModual(), pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<MessageListModel>() {
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

    @Override
    public void onBackPressed() {
        setResult(100);
        super.onBackPressed();
    }

    public static void newInstance(BaseActivity context, MessageCountModel.DataEntity dataEntity) {
        Intent intent = new Intent(context, MessageOrderListActivity.class);
        intent.putExtra("DataEntity", dataEntity);
        context.startActivityForResult(intent, 10);
    }
}
