package com.cmbb.smartkids.activity.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.holder.ServiceItemHolder;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 16:12
 */
public class ServiceFraAdapter extends BaseRecyclerAdapter {
    private CustomListener.ItemClickListener onItemClick;

    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home_service_item, parent, false);
        return new ServiceItemHolder(root);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ServiceListModel.DataEntity.RowsEntity row = (ServiceListModel.DataEntity.RowsEntity) dataList.get(position);
        ((ServiceItemHolder) holder).setData(row, position, this);
    }


    public CustomListener.ItemClickListener getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(CustomListener.ItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }
}
