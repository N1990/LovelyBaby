package com.cmbb.smartkids.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.message.adapter.MsgServiceAdapter;
import com.cmbb.smartkids.activity.message.model.MessageCountModel;
import com.cmbb.smartkids.activity.message.model.MessageListModel;
import com.cmbb.smartkids.activity.serve.v2.ServerDetailActivityV2;
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
public class MessageServiceListActivity extends BaseActivity implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private static final String TAG = MessageServiceListActivity.class.getSimpleName();

    protected SmartRecyclerView mSmartRecyclerView;
    private MsgServiceAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;
    private MessageCountModel.DataEntity dataEntity;//获取的参数

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("服务消息");
        Log.i(TAG, "服务消息");
        dataEntity = getIntent().getParcelableExtra("DataEntity");
        initRecyclerView();
        onRefresh();
    }

    private void initRecyclerView() {
        mSmartRecyclerView = (SmartRecyclerView) findViewById(R.id.recyclerView);
        mSmartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MsgServiceAdapter(this);
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
        ServerDetailActivityV2.newIntent(this,Integer.valueOf(adapter.getItem(position).getRelateField()));
    }

    @Override
    public void onLoadMore() {
        pager++;
        MessageListModel.getOfficialMessageRequest(dataEntity.getModual(), pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<MessageListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
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
        MessageListModel.getOfficialMessageRequest(dataEntity.getModual(), pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<MessageListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
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
        Intent intent = new Intent(context, MessageServiceListActivity.class);
        intent.putExtra("DataEntity", dataEntity);
        context.startActivityForResult(intent, 10);
    }
}
