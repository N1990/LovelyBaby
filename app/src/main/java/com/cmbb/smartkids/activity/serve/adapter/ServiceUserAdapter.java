package com.cmbb.smartkids.activity.serve.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.model.ActiveDetailModel;
import com.cmbb.smartkids.activity.serve.holder.ServiceUserHolder;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/21 13:52
 */
public class ServiceUserAdapter extends RecyclerView.Adapter {
    private CustomListener.ItemClickListener onItemListener;
    private List<ActiveDetailModel.DataEntity.UserInfoListEntity> data;

    public ServiceUserAdapter(List<ActiveDetailModel.DataEntity.UserInfoListEntity> data){
        if(data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
    }

    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_base_message, parent, false);
        return new ServiceUserHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ServiceUserHolder)holder).setData(this, position, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
