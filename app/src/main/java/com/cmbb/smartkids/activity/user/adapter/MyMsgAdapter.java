package com.cmbb.smartkids.activity.user.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.user.holder.MyMessageHolder;
import com.cmbb.smartkids.activity.user.model.MessageListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;


/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/16 14:10
 */
public class MyMsgAdapter extends RecyclerArrayAdapter<MessageListModel.DataEntity.RowsEntity> {

    public void setRead(int position) {
        getItem(position).setIsRead(1);
        notifyDataSetChanged();
    }

    public MyMsgAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyMessageHolder(parent);
    }
}
