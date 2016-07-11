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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home.adapter.BannerLoopAdapter;
import com.cmbb.smartkids.activity.home.home.adapter.HomeAdapter;
import com.cmbb.smartkids.activity.home.model.HomePageRootModel;
import com.cmbb.smartkids.activity.home.model.ManagerAdModel;
import com.cmbb.smartkids.activity.home.model.ShowMarketModel;
import com.cmbb.smartkids.activity.home.model.SignModel;
import com.cmbb.smartkids.activity.search.SearchActivity;
import com.cmbb.smartkids.activity.serve.view.ServerDetailActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.cmbb.smartkids.utils.SPCache;
import com.jude.rollviewpager.RollPagerView;
import com.squareup.okhttp.Request;
import com.umeng.update.UmengUpdateAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午2:59
 */
public class HomeActivity extends BaseHomeActivity implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener, AppBarLayout.OnOffsetChangedListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    protected SmartRecyclerView mSmartRecyclerView;
    protected AppBarLayout abl;
    protected HomeAdapter adapter;
    private RollPagerView mRollViewPager;
    private BannerLoopAdapter bannerLoopAdapter;
    private ManagerAdModel managerAdModel;
    private List<ManagerAdModel.DataEntity> adData;
    private LinearLayout llSearch;
    private ImageView ivSign;
    private int pager = 0;
    private int pagerSize = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_v2;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        setNoBack();
        //关闭wifi
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(this);
        abl = (AppBarLayout) findViewById(R.id.abl);
        llSearch = (LinearLayout) findViewById(R.id.ll_search);
        llSearch.setOnClickListener(this);
        mRollViewPager = (RollPagerView) findViewById(R.id.roll_view_pager);
        mRollViewPager.setPlayDelay(2000);
        mRollViewPager.setAnimationDurtion(500);
        bannerLoopAdapter = new BannerLoopAdapter(this, adData);
        mRollViewPager.setAdapter(bannerLoopAdapter);
        initRecyclerView();
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                LinearLayout header = (LinearLayout) LayoutInflater.from(HomeActivity.this).inflate(R.layout.activity_home_banner_head_v2, null);
                header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return header;
            }

            @Override
            public void onBindView(View headerView) {
                headerView.findViewById(R.id.tv_mengbaoduijia).setOnClickListener(HomeActivity.this);
                headerView.findViewById(R.id.tv_haohaoxuexi).setOnClickListener(HomeActivity.this);
                headerView.findViewById(R.id.tv_zuiaihuwai).setOnClickListener(HomeActivity.this);
                headerView.findViewById(R.id.tv_yihaixunshi).setOnClickListener(HomeActivity.this);
                headerView.findViewById(R.id.tv_zuixindingzhi).setOnClickListener(HomeActivity.this);
                headerView.findViewById(R.id.tv_sijiameishi).setOnClickListener(HomeActivity.this);
                headerView.findViewById(R.id.tv_guoyimiaoshou).setOnClickListener(HomeActivity.this);
                headerView.findViewById(R.id.tv_lamasudi).setOnClickListener(HomeActivity.this);
            }
        });

        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                LinearLayout header = (LinearLayout) LayoutInflater.from(HomeActivity.this).inflate(R.layout.activity_home_banner_head2_v2, null);
                LinearLayout.LayoutParams headerParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                header.setLayoutParams(headerParams);
                ivSign = (ImageView) header.findViewById(R.id.iv_sign);
                return header;
            }

            @Override
            public void onBindView(View headerView) {
                headerView.findViewById(R.id.rl_sign).setOnClickListener(HomeActivity.this);
                headerView.findViewById(R.id.rl_rank).setOnClickListener(HomeActivity.this);
                headerView.findViewById(R.id.rl_wonderful).setOnClickListener(HomeActivity.this);
                headerView.findViewById(R.id.rl_week).setOnClickListener(HomeActivity.this);
            }
        });
    }

    private void initRecyclerView() {
        mSmartRecyclerView = (SmartRecyclerView) findViewById(R.id.recyclerView);
        mSmartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomeAdapter(this);
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
        ManagerAdModel.getHomeImgRequest(new OkHttpClientManager.ResultCallback<ManagerAdModel>() {
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
                    managerAdModel = response;
                    adData = response.getData();
                    bannerLoopAdapter.update(adData);
                } else {
                    adData = new ArrayList<>();
                }
            }
        });
    }

    @Override
    public void onLoadMore() {
        pager++;
        // 热门
        HomePageRootModel.getHomeHotServiceRequest(pagerSize, pager, new OkHttpClientManager.ResultCallback<HomePageRootModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                adapter.add(null);
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(HomePageRootModel response) {
                if (response != null) {
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        // 热门
        pager = 0;
        HomePageRootModel.getHomeHotServiceRequest(pagerSize, pager, new OkHttpClientManager.ResultCallback<HomePageRootModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                adapter.add(null);
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(HomePageRootModel response) {
                if (response != null) {
                    if (response.getData().getRows().size() > 0) {
                        adapter.clear();
                    }
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
        //重新加载广告
        requestAdd();
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_sign:// 签到
                SignActivity.newIntent(this);
                break;
            case R.id.rl_rank://大人排行
                RankErdarActivity.newIntent(this);
                break;
            case R.id.rl_wonderful://精彩回顾
                if (managerAdModel != null)
                    WonderfulReviewActivity.newIntent(this);
                break;
            case R.id.rl_week:
                WeekReviewActivity.newIntent(this);
                break;
            case R.id.tv_mengbaoduijia:// 萌宝独家
                HomeServiceActivity.newIntent(this, "MBDJ");
                break;
            case R.id.tv_haohaoxuexi:// 好好学习
                HomeServiceActivity.newIntent(this, "HHXX");
                break;
            case R.id.tv_zuiaihuwai://最爱户外
                HomeServiceActivity.newIntent(this, "ZAHW");
                break;
            case R.id.tv_yihaixunshi://艺海寻师
                HomeServiceActivity.newIntent(this, "YHXS");
                break;
            case R.id.tv_zuixindingzhi:// 醉心定制
                HomeServiceActivity.newIntent(this, "ZXDZ");
                break;
            case R.id.tv_sijiameishi://私家美食
                HomeServiceActivity.newIntent(this, "SJMS");
                break;
            case R.id.tv_guoyimiaoshou://国医妙手
                HomeServiceActivity.newIntent(this, "GYMS");
                break;
            case R.id.tv_lamasudi://辣妈速度
                HomeServiceActivity.newIntent(this, "LMSD");
                break;
            case R.id.ll_search:
                SearchActivity.newIntent(this);
                break;
        }
    }

    @Override
    protected void netChange() {
        onRefresh();
        if (!showMarket) {
            ShowMarketModel.isShowMarket(new OkHttpClientManager.ResultCallback<ShowMarketModel>() {
                @Override
                public void onError(Request request, Exception e, String response) {

                }

                @Override
                public void onResponse(ShowMarketModel response) {
                    if (response == null)
                        return;
                    showMarket(response.getData().isIsShow());
                    if (response.getData().isIsShow()) {
                        SPCache.putBoolean("show_market", response.getData().isIsShow());
                        SPCache.putString("show_market_url", response.getData().getShowUrl());
                    }
                }
            });
        }
    }

    @Override
    public void onItemClick(int position) {
        ServerDetailActivity.newIntent(this, adapter.getItem(position).getId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvHome.setSelected(true);
        abl.addOnOffsetChangedListener(this);
        // 请求是否签到
        if (!TextUtils.isEmpty(BaseApplication.token)) {
            SignModel.getSignRequest(new OkHttpClientManager.ResultCallback<SignModel>() {
                @Override
                public void onError(Request request, Exception e, String msg) {
                    if (TextUtils.isEmpty(msg)) {
                        showShortToast(getString(R.string.is_netwrok));
                    } else {
                        showShortToast(msg);
                    }
                }

                @Override
                public void onResponse(SignModel response) {
                    if (response == null)
                        return;
                    switch (response.getData().getIsSign()) {
                        case 0:
                            ivSign.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            ivSign.setVisibility(View.INVISIBLE);
                            break;
                    }
                }
            });
        }
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
