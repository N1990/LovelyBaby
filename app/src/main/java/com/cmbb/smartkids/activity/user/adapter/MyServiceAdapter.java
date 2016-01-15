package com.cmbb.smartkids.activity.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.activity.user.holder.MyServiceHolder;
import com.cmbb.smartkids.base.CustomListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 10:38
 */
public class MyServiceAdapter extends BaseRecyclerAdapter {
    private CustomListener.ItemClickListener onItemClick;


    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home_service_item, parent, false);
        return new MyServiceHolder(root);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ServiceListModel.DataEntity.RowsEntity itemData = (ServiceListModel.DataEntity.RowsEntity) dataList.get(position);
        ((MyServiceHolder)holder).setData(this, position, itemData);
    }

    public CustomListener.ItemClickListener getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(CustomListener.ItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }
}
