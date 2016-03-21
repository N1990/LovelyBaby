package com.cmbb.smartkids.activity.message.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.message.holder.MsgOrderHolder;
import com.cmbb.smartkids.activity.message.model.MessageListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by javon on 15/12/10.
 */
public class MsgOrderAdapter extends RecyclerArrayAdapter<MessageListModel.DataEntity.RowsEntity> {

    public MsgOrderAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MsgOrderHolder(parent);
    }
}
