package com.javon.loadmorerecyclerview;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * 项目名称：LoadMoreRecyclerView
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 13:24
 */
public class SwipeRefreshLayoutOnRefresh implements SwipeRefreshLayout.OnRefreshListener {
    private LoadMoreRecyclerView mLoadMoreRecyclerView;

    public SwipeRefreshLayoutOnRefresh(LoadMoreRecyclerView pullLoadMoreRecyclerView) {
        this.mLoadMoreRecyclerView = pullLoadMoreRecyclerView;
    }

    @Override
    public void onRefresh() {
        if (!mLoadMoreRecyclerView.isRefresh()) {
            mLoadMoreRecyclerView.setIsRefresh(true);
            mLoadMoreRecyclerView.refresh();
        }
    }

}
