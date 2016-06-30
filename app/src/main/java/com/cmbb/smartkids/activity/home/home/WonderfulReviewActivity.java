package com.cmbb.smartkids.activity.home.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.CommunityDetailActivity;
import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.home.home.adapter.BannerLoopAdapter;
import com.cmbb.smartkids.activity.home.home.adapter.TopicAdapter;
import com.cmbb.smartkids.activity.home.model.ManagerAdModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/16 下午4:05
 */
public class WonderfulReviewActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener, AppBarLayout.OnOffsetChangedListener {
    private static final String TAG = WonderfulReviewActivity.class.getSimpleName();
    protected SmartRecyclerView mSmartRecyclerView;
    protected AppBarLayout abl;
    protected TopicAdapter adapter;
    private RollPagerView mRollViewPager;
    private BannerLoopAdapter bannerLoopAdapter;
    private List<ManagerAdModel.DataEntity> adData;
    private int pager = 0;
    private int pagerSize = 10;


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_wonderful_review;
    }

    private void initView() {
        abl = (AppBarLayout) findViewById(R.id.abl);
        mRollViewPager = (RollPagerView) findViewById(R.id.roll_view_pager);
        mRollViewPager.setPlayDelay(2000);
        mRollViewPager.setAnimationDurtion(500);
        bannerLoopAdapter = new BannerLoopAdapter(this, adData);
        mRollViewPager.setAdapter(bannerLoopAdapter);
        initRecyclerView();
        requestAdd();
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                LinearLayout header = (LinearLayout) LayoutInflater.from(WonderfulReviewActivity.this).inflate(R.layout.activity_wonderful_review_head, null);
                header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        onRefresh();
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

    /**
     * 请求广告
     */
    private void requestAdd() {
        ManagerAdModel.getWonderfulImgRequest(new OkHttpClientManager.ResultCallback<ManagerAdModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                adData = new ArrayList<>();
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(ManagerAdModel response) {
                if (response != null) {
                    adData = response.getData();
                    bannerLoopAdapter.update(adData);
                } else {
                    adData = new ArrayList<>();
                }
            }
        });
    }

    private final int COMMUNITY_DETAIL_REQUEST = 10101;

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, CommunityDetailActivity.class);
        intent.putExtra("id", adapter.getItem(position).getId());
        startActivityForResult(intent, COMMUNITY_DETAIL_REQUEST);
    }

    @Override
    public void onLoadMore() {
        pager++;
        TopicListModel.getWonderfulReviewRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<TopicListModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
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
        TopicListModel.getWonderfulReviewRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<TopicListModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
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

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, WonderfulReviewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        abl.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        abl.removeOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            mSmartRecyclerView.getSwipeToRefresh().setEnabled(true);
        } else {
            mSmartRecyclerView.getSwipeToRefresh().setEnabled(false);
        }
    }
}
