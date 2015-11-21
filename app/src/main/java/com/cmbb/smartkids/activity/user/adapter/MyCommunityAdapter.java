package com.cmbb.smartkids.activity.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.home.holder.CommunityHolder;
import com.cmbb.smartkids.activity.user.holder.MyCommunityHolder;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 11:58
 */
public class MyCommunityAdapter extends BaseRecyclerAdapter {

    private CustomListener.ItemClickListener onItemListener;


    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_community_item, parent, false);
        return new MyCommunityHolder(root);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyCommunityHolder) holder).setData((TopicListModel.DataEntity.RowsEntity) dataList.get(position), this, position);
    }


    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }


}
