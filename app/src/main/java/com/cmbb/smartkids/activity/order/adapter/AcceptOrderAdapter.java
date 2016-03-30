package com.cmbb.smartkids.activity.order.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.order.holder.AcceptOrderHolder;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/15 14:00
 */
public class AcceptOrderAdapter extends RecyclerArrayAdapter<OrderListModel.DataEntity.RowsEntity> {

    public AcceptOrderAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new AcceptOrderHolder(parent);
    }
}
