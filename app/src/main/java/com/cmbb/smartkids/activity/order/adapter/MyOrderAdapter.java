package com.cmbb.smartkids.activity.order.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.holder.MyOrderHolder;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 14:55
 */
public class MyOrderAdapter extends BaseRecyclerAdapter {
    private CustomListener.ItemClickListener onItemListener;
    private CustomListener.ItemClickListener onHandlerListener;
    private CustomListener.ItemClickListener onPayListener;

    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_item, parent, false);
        return new MyOrderHolder(root);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyOrderHolder) holder).setData(this, position, (OrderListModel.DataEntity.RowsEntity) dataList.get(position));

    }

    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public CustomListener.ItemClickListener getOnHandlerListener() {
        return onHandlerListener;
    }

    public void setOnHandlerListener(CustomListener.ItemClickListener onHandlerListener) {
        this.onHandlerListener = onHandlerListener;
    }

    public CustomListener.ItemClickListener getOnPayListener() {
        return onPayListener;
    }

    public void setOnPayListener(CustomListener.ItemClickListener onPayListener) {
        this.onPayListener = onPayListener;
    }
}
