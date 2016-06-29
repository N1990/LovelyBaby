package com.cmbb.smartkids.activity.home.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.CommunityDetailActivity;
import com.cmbb.smartkids.activity.community.PublishCommunityActivity;
import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.community.model.TopicTypeModel;
import com.cmbb.smartkids.activity.home.home.adapter.TopicAdapter;
import com.cmbb.smartkids.activity.home.home.adapter.TopicTypeAdapter;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午4:17
 */
public class HomeTopicActivity extends BaseHomeActivity implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {

    private static final String TAG = HomeTopicActivity.class.getSimpleName();

    protected SmartRecyclerView mSmartRecyclerView;

    protected TopicAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;

    PopupWindow mSmartPopupWindow;
    private TopicTypeAdapter topicTypeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_topic_v2;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ((TextView) findViewById(R.id.tv_title)).setText("全部话题");
        findViewById(R.id.tv_title).setOnClickListener(this);
        setNoBack();
        initRecyclerView();
        initPopup();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvTopic.setSelected(true);
    }

    private void initRecyclerView() {
        mSmartRecyclerView = (SmartRecyclerView) findViewById(R.id.recyclerView);
        mSmartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TopicAdapter(this);
        mSmartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        mSmartRecyclerView.setRefreshListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_topic, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_post:
                // 发帖
                Intent intent = new Intent(this, PublishCommunityActivity.class);
                startActivityForResult(intent, 11);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private final int COMMUNITY_DETAIL_REQUEST = 10101;

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, CommunityDetailActivity.class);
        intent.putExtra("id", ((TopicAdapter) adapter).getItem(position).getId());
        startActivityForResult(intent, COMMUNITY_DETAIL_REQUEST);
    }

    SmartRecyclerView popuRecyclerView;

    private void initPopup() {
        View view = LayoutInflater.from(this).inflate(R.layout.popup_topic_type, null);
        popuRecyclerView = (SmartRecyclerView) view.findViewById(R.id.popu_recycler);
        popuRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        topicTypeAdapter = new TopicTypeAdapter(this);
        topicTypeAdapter.setOnItemListener(onTypeListener);
        popuRecyclerView.setAdapter(topicTypeAdapter);
        // 初始化全部数据
        TopicTypeModel.DataEntity allTopic = new TopicTypeModel.DataEntity();
        allTopic.setName("全部话题");
        allTopic.setValue(null);
        topicTypeAdapter.add(allTopic);
        TopicTypeModel.getTopicTypeRequest(new OkHttpClientManager.ResultCallback<TopicTypeModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(getString(R.string.is_netwrok));
            }

            @Override
            public void onResponse(TopicTypeModel response) {
                if (response != null) {
                    topicTypeAdapter.addAll(response.getData());
                }
            }
        });
        mSmartPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mSmartPopupWindow.setOutsideTouchable(true);
        mSmartPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mSmartPopupWindow.setTouchable(true);
    }

    private CustomListener.ItemClickListener onTypeListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            topicTypeAdapter.setSelectIndex(position);
            TopicTypeModel.DataEntity data = (TopicTypeModel.DataEntity) object;
            ((TextView) findViewById(R.id.tv_title)).setText(data.getName());
            mSmartPopupWindow.dismiss();
            onRefresh();
        }
    };

    @Override
    public void onLoadMore() {
        pager++;
        TopicListModel.getTopicListRequest(topicTypeAdapter.getSelectData().getValue(), pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(getString(R.string.is_netwrok));
            }

            @Override
            public void onResponse(TopicListModel response) {
                if (response != null) {
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pager = 0;
        TopicListModel.getTopicListRequest(topicTypeAdapter.getSelectData().getValue(), pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(getString(R.string.is_netwrok));
            }

            @Override
            public void onResponse(TopicListModel response) {
                if (response != null) {
                    if (response.getData().getRows().size() > 0) {
                        adapter.clear();
                    }
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_title:
                mSmartPopupWindow.showAsDropDown(findViewById(R.id.tv_title));
                break;
        }
    }

    @Override
    protected void netChange() {
        onRefresh();
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, HomeTopicActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

}
