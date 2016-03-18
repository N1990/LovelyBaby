package com.cmbb.smartkids.activity.order.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.order.holder.OrderHolder;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by javon on 16/3/17.
 */
public class OrderAdapter extends RecyclerArrayAdapter<OrderListModel.DataEntity.RowsEntity> {
    private CustomListener.ItemClickListener onCancelListener;
    private CustomListener.ItemClickListener onHandlerListener;

    public OrderAdapter(Context context) {
        super(context);
    }

    public CustomListener.ItemClickListener getOnCancelListener() {
        return onCancelListener;
    }

    public void setOnCancelListener(CustomListener.ItemClickListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }

    public CustomListener.ItemClickListener getOnHandlerListener() {
        return onHandlerListener;
    }

    public void setOnHandlerListener(CustomListener.ItemClickListener onHandlerListener) {
        this.onHandlerListener = onHandlerListener;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderHolder(parent, this);
    }
}
