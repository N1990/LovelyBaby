package com.javon.loadmorerecyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LoadMoreRecyclerView
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 13:17
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = BaseRecyclerAdapter.class.getSimpleName();

    protected List dataList;
    protected final int NORMAL = -2;
    protected final int FOOTER = -3;
    private View.OnClickListener onFooterClick;
    private FooterViewHolder vhFooter;
    //    private int flag = 0; // 标识位 控制footerView内容   0 没有更多数据； 1 加载更多数据； 2 网络出错重试

    public void setData(List dataList) {
        if (dataList == null) {
            this.dataList = new ArrayList<>();
        } else {
            this.dataList = dataList;
        }
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        if (dataList != null) {
            dataList.remove(position);
        }
        notifyItemRemoved(position);
    }

    public void addData(List dataList, LoadMoreRecyclerView view) {
        if (vhFooter != null)
            setFooterEmpty();
        if (dataList != null && dataList.size() != 0) {
            if (this.dataList != null) {
                this.dataList.addAll(dataList);
            } else {
                this.dataList = dataList;
            }
            notifyDataSetChanged();
        } else if ((dataList == null || dataList.size() == 0) && this.dataList.size() != 0) {
            view.setHasMore(false);
            setNoData();
        } else if (dataList == null) {
            setNetError();
        }
    }

    public int getDataSize() {
        return dataList.size();
    }

    public void clearData() {
        if (dataList != null)
            dataList.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (dataList != null && dataList.size() != 0) {
            if (position == dataList.size()) {
                return FOOTER;
            } else {
                return NORMAL;
            }
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER) {
            View footer = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_footer, null);
            return vhFooter = new FooterViewHolder(BaseRecyclerAdapter.this, footer);
        } else {
            return onCustomViewHolder(parent, viewType);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position != dataList.size())
            onBindCustomViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        if (dataList == null || dataList.size() == 0) {
            return 0;
        } else {
            return dataList.size() + 1;
        }
    }


    protected abstract RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position);


    public void setNetError() {
        vhFooter.setFooterError();
    }

    public void setFooterEmpty() {
        vhFooter.setFooterEmpty();
    }


    public void setNoData() {
        vhFooter.setFooterNoData();
    }

    public void setLoadMore() {
        vhFooter.setLoadMore();
    }


    public void setOnFooterTryAgain(View.OnClickListener onFooterClick) {
        if (onFooterClick != null)
            this.onFooterClick = onFooterClick;
    }

    public View.OnClickListener getFooterClick() {
        return this.onFooterClick;
    }

//    交错布局
//    public int getViewHeight(View view) {
//        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        view.measure(w, h);
//        return view.getMeasuredHeight();
//    }
}
