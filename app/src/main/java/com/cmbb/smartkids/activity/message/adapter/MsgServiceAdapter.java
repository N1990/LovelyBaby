package com.cmbb.smartkids.activity.message.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.message.holder.MsgOfficalHolder;
import com.cmbb.smartkids.activity.message.holder.MsgOrderHolder;
import com.cmbb.smartkids.activity.message.holder.MsgServiceHolder;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;

/**
 * Created by javon on 15/12/10.
 */
public class MsgServiceAdapter extends BaseRecyclerAdapter {
    private CustomListener.ItemClickListener onItemListener;

    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_msg_service_item, parent, false);
        return new MsgServiceHolder(root);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MsgServiceHolder) holder).setData(this, position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
