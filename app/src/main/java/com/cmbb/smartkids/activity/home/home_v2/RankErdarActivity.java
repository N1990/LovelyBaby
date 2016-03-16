package com.cmbb.smartkids.activity.home.home_v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home_v2.adapter.RankErdarAdapter;
import com.cmbb.smartkids.activity.home.model.RankEredarRootModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/16 下午1:56
 */
public class RankErdarActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {

    private static final String TAG = RankErdarActivity.class.getSimpleName();

    protected SmartRecyclerView mSmartRecyclerView;
    protected RankErdarAdapter adapter;

    private int pager = 0;
    private int pagerSize = 30;

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("推荐达人");
        initRecyclerView();
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rank_erdar;
    }


    private void initRecyclerView() {
        mSmartRecyclerView = (SmartRecyclerView) findViewById(R.id.recyclerView);
        mSmartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RankErdarAdapter(this);
        mSmartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLoadMore() {
        pager++;
        RankEredarRootModel.getRankEredarRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<RankEredarRootModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(RankEredarRootModel response) {
                if (response != null) {
                    adapter.addAll(response.getData());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pager = 0;
        RankEredarRootModel.getRankEredarRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<RankEredarRootModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(RankEredarRootModel response) {
                if (response != null) {
                    adapter.clear();
                    adapter.addAll(response.getData());
                }
            }
        });
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, RankErdarActivity.class);
        context.startActivity(intent);
    }
}
