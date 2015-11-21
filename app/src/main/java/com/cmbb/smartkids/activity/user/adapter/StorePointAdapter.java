package com.cmbb.smartkids.activity.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.holder.StorePointHeadHolder;
import com.cmbb.smartkids.activity.user.holder.StorePointItemHolder;
import com.cmbb.smartkids.activity.user.model.StorePointModel;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;


/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/16 14:10
 */
public class StorePointAdapter extends BaseRecyclerAdapter {
    private final String TAG = StorePointAdapter.class.getSimpleName();
    private final int HEADER = -1;
    private CustomListener.ItemClickListener onItemListener;

    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }


    int gold;

    public StorePointAdapter(int gold) {
        this.gold = gold;
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
    public int getItemCount() {
        if (dataList != null && dataList.size() > 0) {
            return dataList.size() + 2;
        } else if (dataList != null && dataList.size() == 0) {
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position != dataList.size() + 1)
            onBindCustomViewHolder(holder, position);
    }

    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER:
                View rootHeader = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_store_point_head, parent, false);
                return new StorePointHeadHolder(rootHeader);
            case NORMAL:
                View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_store_point_item, parent, false);
                return new StorePointItemHolder(root);
            default:
                return null;
        }
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StorePointHeadHolder) {
            ((StorePointHeadHolder) holder).setData(this, position, position);
        } else if (holder instanceof StorePointItemHolder) {
            ((StorePointItemHolder) holder).setData(this, (StorePointModel.DataEntity.RowsEntity) (dataList.get(position - 1)), position);
        }
    }

}
