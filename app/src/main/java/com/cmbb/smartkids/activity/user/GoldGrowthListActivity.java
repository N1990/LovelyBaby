package com.cmbb.smartkids.activity.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.GoldGrowthAdapter;
import com.cmbb.smartkids.activity.user.model.GoldGrowthModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/6/20 下午3:20
 * 修改人：N.Sun
 * 修改时间：16/6/20 下午3:20
 * 修改备注：
 */
public class GoldGrowthListActivity extends BaseActivity implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private static final String TAG = GoldGrowthListActivity.class.getSimpleName();

    private int pager = 0;
    private int pagerSize = 10;

    protected SmartRecyclerView mSmartRecyclerView;
    GoldGrowthAdapter mGoldGrowthAdapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("成长值明细");
        mSmartRecyclerView = (SmartRecyclerView) findViewById(R.id.recyclerView);
        mSmartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGoldGrowthAdapter = new GoldGrowthAdapter(this);
        mSmartRecyclerView.setAdapterWithProgress(mGoldGrowthAdapter);
        mGoldGrowthAdapter.setOnItemClickListener(this);
        mGoldGrowthAdapter.setMore(R.layout.view_more, this);
        mGoldGrowthAdapter.setNoMore(R.layout.view_nomore);
        mSmartRecyclerView.setRefreshListener(this);
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gold_growth_layout;
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLoadMore() {
        pager++;
        GoldGrowthModel.getGoldGrowthRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<GoldGrowthModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(GoldGrowthModel response) {
                if (response != null) {
                    mGoldGrowthAdapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pager = 0;
        GoldGrowthModel.getGoldGrowthRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<GoldGrowthModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(GoldGrowthModel response) {
                if (response != null) {
                    mGoldGrowthAdapter.clear();
                    mGoldGrowthAdapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, GoldGrowthListActivity.class);
        context.startActivity(intent);
    }
}
