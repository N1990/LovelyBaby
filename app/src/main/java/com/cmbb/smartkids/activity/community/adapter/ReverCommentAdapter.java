package com.cmbb.smartkids.activity.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.holder.ReverHeaderHolder;
import com.cmbb.smartkids.activity.community.holder.ReverHolder;
import com.cmbb.smartkids.activity.community.model.ReplayDetailListModel;
import com.cmbb.smartkids.activity.community.model.ReplayDetailModel;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;

import java.util.List;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/6 9:46
 */
public class ReverCommentAdapter extends BaseRecyclerAdapter {
    private final int HEADER = -1;
    private ReplayDetailModel resultReplayDetail; // 头数据

    private CustomListener.ItemClickListener onDeleteListener;
    private CustomListener.ItemClickListener onReverPreListener;
    private View.OnClickListener onReverListener;
    private View.OnClickListener onMoreListener;
    private View.OnClickListener onPreListener;
    private View.OnClickListener onHeaderListener;
    private View.OnClickListener onEveryListener;


    public void setData(ReplayDetailModel resultReplayDetail, List<ReplayDetailListModel.DataEntity.RowsEntity> resultReplayList) {
        this.resultReplayDetail = resultReplayDetail;
        setData(resultReplayList);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (dataList.size() != 0) {
            if (position == 0) {
                return HEADER;
            } else if (position == dataList.size() + 1) {
                return FOOTER;
            } else {
                return NORMAL;
            }
        } else {
            if (position == 0) {
                return HEADER;
            } else {
                return FOOTER;
            }
        }

    }


    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rever_comment_header_item, parent, false);
            return new ReverHeaderHolder(root);
        } else {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rever_comment_item, parent, false);
            return new ReverHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position != dataList.size() + 1)
            onBindCustomViewHolder(holder, position);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ReverHeaderHolder) {
            ((ReverHeaderHolder) holder).setData(resultReplayDetail, this);
        } else if (holder instanceof ReverHolder) {
            ((ReverHolder) holder).setData(dataList.get(position - 1), this, position - 1);
        }
    }


    @Override
    public int getItemCount() {
        if (dataList != null && dataList.size() > 0) {
            return dataList.size() + 2;
        } else if (dataList != null && dataList.size() == 0) {
            return 2;
        } else {
            return 0;
        }
    }


    public View.OnClickListener getOnEveryListener() {
        return onEveryListener;
    }

    public void setOnEveryListener(View.OnClickListener onEveryListener) {
        this.onEveryListener = onEveryListener;
    }

    public CustomListener.ItemClickListener getOnDeleteListener() {
        return onDeleteListener;
    }

    public void setOnDeleteListener(CustomListener.ItemClickListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public CustomListener.ItemClickListener getOnReverPreListener() {
        return onReverPreListener;
    }

    public void setOnReverPreListener(CustomListener.ItemClickListener onReverPreListener) {
        this.onReverPreListener = onReverPreListener;
    }

    public View.OnClickListener getOnReverListener() {
        return onReverListener;
    }

    public void setOnReverListener(View.OnClickListener onReverListener) {
        this.onReverListener = onReverListener;
    }

    public View.OnClickListener getOnMoreListener() {
        return onMoreListener;
    }

    public void setOnMoreListener(View.OnClickListener onMoreListener) {
        this.onMoreListener = onMoreListener;
    }

    public View.OnClickListener getOnPreListener() {
        return onPreListener;
    }

    public void setOnPreListener(View.OnClickListener onPreListener) {
        this.onPreListener = onPreListener;
    }

    public View.OnClickListener getOnHeaderListener() {
        return onHeaderListener;
    }

    public void setOnHeaderListener(View.OnClickListener onHeaderListener) {
        this.onHeaderListener = onHeaderListener;
    }
}
