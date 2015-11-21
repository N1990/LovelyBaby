package com.javon.loadmorerecyclerview;

import android.content.Context;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 项目名称：LoadMoreRecyclerView
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 13:20
 */
public class LoadMoreRecyclerView extends LinearLayout implements View.OnClickListener {


    private final String TAG = LoadMoreRecyclerView.class.getSimpleName();
    private RecyclerView mRecyclerView;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private PullLoadMoreListener mPullLoadMoreListener;
    private boolean hasMore = true;
    private boolean isInitialize = false;
    private boolean isRefresh = false;
    private boolean isLoadMore = false;
    private int row = 2;
    private LinearLayout llTry, llLoading;
    private RelativeLayout rlTryAgain;
    private ContentLoadingProgressBar pbLoading;
    private ImageView ivTry;
    private TextView tvTry;
    private Context mContext;
    private boolean canRefresh = true;


    public LoadMoreRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.pull_loadmore_layout, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark, android.R.color.holo_red_dark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayoutOnRefresh(this));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        // 设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(
        //getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        mRecyclerView.addOnScrollListener(new RecyclerViewOnScroll(this));
        //加载出错
        rlTryAgain = (RelativeLayout) view.findViewById(R.id.rl_try_again);
        llTry = (LinearLayout) view.findViewById(R.id.ll_try_again);
        ivTry = (ImageView) view.findViewById(R.id.iv_try_again);
        tvTry = (TextView) view.findViewById(R.id.tv_try_again);
        ivTry.setOnClickListener(this);
        rlTryAgain.setVisibility(GONE);
        llTry.setVisibility(GONE);

        llLoading = (LinearLayout) view.findViewById(R.id.ll_recycler_loading);
        pbLoading = (ContentLoadingProgressBar) view.findViewById(R.id.cpb_recycler_progress);
        llLoading.setVisibility(GONE);

        this.addView(view);

    }


    /**
     * row > 2
     * 要在网格布局和交错布局之前调用
     *
     * @param row
     */
    public void setGridRow(int row) {
        this.row = row;
    }


    /**
     * 线性布局管理器
     */
    public void setLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * 网格布局管理器
     */

    public void setGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, row);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        /*StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(row, OrientationHelper.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);*/
        // 设置布局管理器
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

    /**
     * 是否允许刷新
     * @return
     */
    public boolean isCanRefresh() {
        return canRefresh;
    }

    public void setCanRefresh(boolean canRefresh) {
        this.canRefresh = canRefresh;
    }

    /**
     * 交错网格布局管理器
     */

    public void setStaggeredGridLayout() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(row, StaggeredGridLayoutManager.VERTICAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }


    public void setPullRefreshEnable(boolean enable) {
        mSwipeRefreshLayout.setEnabled(enable);
    }

    public boolean getPullRefreshEnable() {
        return mSwipeRefreshLayout.isEnabled();
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return mRecyclerView.getLayoutManager();
    }


    public void loadMore() {
        if (mPullLoadMoreListener != null && hasMore) {
            isLoadMore = true;
            mPullLoadMoreListener.onLoadMore();
            Log.e("loader", "加载更多");
        }
    }


    public void setPullLoadMoreListener(PullLoadMoreListener listener) {
        mPullLoadMoreListener = listener;
    }

    /**
     * 加载更多完毕,为防止频繁网络请求,isLoadMore为false才可再次请求更多数据
     */

    public void setPullLoadMoreCompleted() {
        if (isInitialize) {
            isInitialize = false;
            pbLoading.hide();
            llLoading.setVisibility(GONE);
            mSwipeRefreshLayout.setVisibility(VISIBLE);
        }
        isRefresh = false;
        isLoadMore = false;
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }

    /**
     * 初始化页面
     */
    public void setInitialize() {
        isInitialize = true;
        rlTryAgain.setVisibility(VISIBLE);
        llTry.setVisibility(GONE);
        llLoading.setVisibility(VISIBLE);
        pbLoading.setVisibility(VISIBLE);
        pbLoading.show();
        if (mPullLoadMoreListener != null)
            mPullLoadMoreListener.onInitialize();

    }

    public void setInitializeWithoutPb() {
        isInitialize = true;
        if (mPullLoadMoreListener != null)
            mPullLoadMoreListener.onInitialize();
    }


    public void setRefreshing(final boolean isRefreshing) {
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(isRefreshing);
            }
        });

    }

    /**
     * 初始化时
     * 网络出错调用
     */
    public void setErrorView() {
        mSwipeRefreshLayout.setVisibility(GONE);
        rlTryAgain.setVisibility(VISIBLE);
        llTry.setVisibility(VISIBLE);

    }


    /**
     * 请求数据，返回结果没有任何内容
     */
    public void setNoContent() {
        mSwipeRefreshLayout.setVisibility(GONE);
        rlTryAgain.setVisibility(VISIBLE);
        llLoading.setVisibility(GONE);
        llTry.setVisibility(VISIBLE);
        ivTry.setVisibility(GONE);
        tvTry.setText("目前没有任何内容~");
    }

    /**
     * 请求数据，返回结果有内容
     */
    public void setHasContent() {
        mSwipeRefreshLayout.setVisibility(VISIBLE);
        rlTryAgain.setVisibility(GONE);
        llLoading.setVisibility(GONE);
        llTry.setVisibility(GONE);
        ivTry.setVisibility(GONE);
    }


    public void refresh() {
        if (mPullLoadMoreListener != null) {
            isRefresh = true;
            hasMore = true;
            mSwipeRefreshLayout.setVisibility(VISIBLE);
            rlTryAgain.setVisibility(GONE);
            mPullLoadMoreListener.onRefresh();
        }
    }

    public void scrollToTop() {
        mRecyclerView.scrollToPosition(0);
    }


    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public void setIsLoadMore(boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public interface PullLoadMoreListener {
        public void onInitialize();

        public void onRefresh();

        public void onLoadMore();

    }

    @Override
    public void onClick(View v) {
        setInitialize();
    }
}
