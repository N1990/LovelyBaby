package com.javon.loadmorerecyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * 项目名称：LoadMoreRecyclerView
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 13:22
 */
public class RecyclerViewOnScroll extends RecyclerView.OnScrollListener {
    private final String TAG = RecyclerViewOnScroll.class.getSimpleName();
    private LoadMoreRecyclerView mLoadMoreRecyclerView;
    private int lastVisibleItem = 0;
    private int firstVisibleItem = 0;
    private int totalItemCount = 0;

    public RecyclerViewOnScroll(LoadMoreRecyclerView LoadMoreRecyclerView) {
        this.mLoadMoreRecyclerView = LoadMoreRecyclerView;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        totalItemCount = layoutManager.getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = ((LinearLayoutManager) layoutManager);
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            firstVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
            //通过LayoutManager找到当前显示的最后的item的position
            lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
            firstVisibleItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = ((StaggeredGridLayoutManager) layoutManager);
            //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
            //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
            int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
            staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
            lastVisibleItem = findMax(lastPositions);
            firstVisibleItem = staggeredGridLayoutManager.findFirstVisibleItemPositions(lastPositions)[0];
        }

        if (firstVisibleItem == 0 && mLoadMoreRecyclerView.isCanRefresh()) {
            mLoadMoreRecyclerView.setPullRefreshEnable(true);
        } else {
            mLoadMoreRecyclerView.setPullRefreshEnable(false);
        }


    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        /**
         * 无论水平还是垂直
         */
        if (!mLoadMoreRecyclerView.isRefresh() && mLoadMoreRecyclerView.isHasMore() && (lastVisibleItem == totalItemCount - 1)
                && !mLoadMoreRecyclerView.isLoadMore() && totalItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE) { //(dx > 0 || dy > 0)
            mLoadMoreRecyclerView.loadMore();
        }
    }

    //找到数组中的最大值

    private int findMax(int[] lastPositions) {

        int max = lastPositions[0];
        for (int value : lastPositions) {
            //       int max    = Math.max(lastPositions,value);
            if (value > max) {
                max = value;
            }
        }
        return max;
    }



}
