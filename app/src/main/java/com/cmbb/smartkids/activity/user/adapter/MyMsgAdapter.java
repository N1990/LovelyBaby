package com.cmbb.smartkids.activity.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.holder.MyMessageHolder;
import com.cmbb.smartkids.activity.user.model.MessageListModel;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;


/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/16 14:10
 */
public class MyMsgAdapter extends BaseRecyclerAdapter{
    private final String TAG = MyMsgAdapter.class.getSimpleName();
    private CustomListener.ItemClickListener onItemListener;

    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_service_message_item, parent, false);
        return new MyMessageHolder(root);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyMessageHolder) holder).setData(this, (MessageListModel.DataEntity.RowsEntity) dataList.get(position), position);
    }

    public void setRead(int position){
        ((MessageListModel.DataEntity.RowsEntity) dataList.get(position)).setIsRead(1);
        notifyDataSetChanged();
    }


}
