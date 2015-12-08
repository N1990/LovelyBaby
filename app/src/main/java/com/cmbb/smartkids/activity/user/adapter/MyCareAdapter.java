package com.cmbb.smartkids.activity.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.holder.MyCareHolder;
import com.cmbb.smartkids.activity.user.model.FriendListModel;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 14:09
 */
public class MyCareAdapter extends BaseRecyclerAdapter {
    private CustomListener.ItemClickListener onItemClick;
    private Context context;

    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View root = LayoutInflater.from(this.context).inflate(R.layout.list_friend_item, parent, false);
        return new MyCareHolder(root);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        FriendListModel.DataEntity.RowsEntity friend = (FriendListModel.DataEntity.RowsEntity) dataList.get(position);
        ((MyCareHolder)holder).setData(context, this, position, friend);
    }

    public CustomListener.ItemClickListener getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(CustomListener.ItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }
}
