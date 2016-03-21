package com.cmbb.smartkids.activity.home.home_v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home_v2.adapter.BannerLoopAdapter;
import com.cmbb.smartkids.activity.home.home_v2.adapter.HomeAdapter;
import com.cmbb.smartkids.activity.home.model.HomePageRootModel;
import com.cmbb.smartkids.activity.home.model.ManagerAdModel;
import com.cmbb.smartkids.activity.serve.v2.ServerDetailActivityV2;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午2:59
 */
public class HomeActivity extends BaseHomeActivity implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {

    private static final String TAG = HomeActivity.class.getSimpleName();

    protected SmartRecyclerView mSmartRecyclerView;
    protected HomeAdapter adapter;
    private RollPagerView mRollViewPager;
    private BannerLoopAdapter bannerLoopAdapter;
    private ManagerAdModel managerAdModel;
    private List<ManagerAdModel.DataEntity> adData;
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

    @Override
    protected void onResume() {
        super.onResume();
        tvHome.setSelected(true);
    }

    private void initView() {
        setNoBack();
        mRollViewPager = (RollPagerView) findViewById(R.id.roll_view_pager);
        mRollViewPager.setPlayDelay(2000);
        mRollViewPager.setAnimationDurtion(500);
        bannerLoopAdapter = new BannerLoopAdapter(adData);
        mRollViewPager.setAdapter(bannerLoopAdapter);
        requestAdd();
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
                return header;
            }

            @Override
            public void onBindView(View headerView) {
                headerView.findViewById(R.id.rl_sign).setOnClickListener(HomeActivity.this);
                headerView.findViewById(R.id.rl_rank).setOnClickListener(HomeActivity.this);
                headerView.findViewById(R.id.rl_wonderful).setOnClickListener(HomeActivity.this);

            }
        });
        onRefresh();
    }

    private void initRecyclerView() {
        mSmartRecyclerView = (SmartRecyclerView) findViewById(R.id.recyclerView);
        mSmartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomeAdapter(this);
        mSmartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
    }

    /**
     * 请求广告
     */
    private void requestAdd() {
        ManagerAdModel.getHomeImgRequest(new OkHttpClientManager.ResultCallback<ManagerAdModel>() {
            @Override
            public void onError(Request request, Exception e) {
                adData = new ArrayList<>();
                showShortToast(e.toString());
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
        HashMap<String, String> bodyHot = new HashMap<>();
        bodyHot.put("isRecommoned", "1");
        bodyHot.put("sortType", "home");
        bodyHot.put("numberOfPerPage", String.valueOf(pagerSize));
        bodyHot.put("pageNo", String.valueOf(pager));
        HomePageRootModel.getHomeHotServiceRequest(pagerSize, pager, new OkHttpClientManager.ResultCallback<HomePageRootModel>() {
            @Override
            public void onError(Request request, Exception e) {
                adapter.pauseMore();
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
        HashMap<String, String> bodyHot = new HashMap<>();
        bodyHot.put("isRecommoned", "1");
        bodyHot.put("sortType", "home");
        bodyHot.put("numberOfPerPage", String.valueOf(pagerSize));
        bodyHot.put("pageNo", String.valueOf(pager));

        HomePageRootModel.getHomeHotServiceRequest(pagerSize, pager, new OkHttpClientManager.ResultCallback<HomePageRootModel>() {
            @Override
            public void onError(Request request, Exception e) {
                adapter.pauseMore();
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
            case R.id.tv_home:
                HomeActivity.newIntent(this);
                break;
            case R.id.tv_service:
                HomeServiceActivity.newIntent(this);
                break;
            case R.id.tv_topic:
                HomeTopicActivity.newIntent(this);
                break;
            case R.id.tv_me:
                HomeMeActivity.newIntent(this);
                break;
            case R.id.tv_more:
                HomeMoreActivity.newIntent(this);
                break;
            case R.id.rl_sign:// 签到
                SignActivity.newIntent(this);
                break;
            case R.id.rl_rank://大人排行
                RankErdarActivity.newIntent(this);
                break;
            case R.id.rl_wonderful://精彩回顾
                if (managerAdModel != null)
                    WonderfulReviewActivity.newIntent(this, managerAdModel);
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
        }
    }

    @Override
    protected void netChange() {
        onRefresh();
    }

    @Override
    public void onItemClick(int position) {
        ServerDetailActivityV2.newIntent(this, adapter.getItem(position).getId());
    }
}
