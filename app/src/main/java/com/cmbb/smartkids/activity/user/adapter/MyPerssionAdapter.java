package com.cmbb.smartkids.activity.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.model.EvaluateListModel;
import com.cmbb.smartkids.activity.user.holder.MyPerssionHolder;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/15 10:54
 */
public class MyPerssionAdapter extends BaseRecyclerAdapter {
    private CustomListener.ItemClickListener onHeaderListener;



    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_perssion_item, parent, false);
        return new MyPerssionHolder(root);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyPerssionHolder) holder).setData(this, position, (EvaluateListModel.DataEntity.RowsEntity) dataList.get(position));
    }

    public CustomListener.ItemClickListener getOnHeaderListener() {
        return onHeaderListener;
    }

    public void setOnHeaderListener(CustomListener.ItemClickListener onHeaderListener) {
        this.onHeaderListener = onHeaderListener;
    }
}
