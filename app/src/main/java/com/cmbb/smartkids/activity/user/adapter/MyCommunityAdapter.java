package com.cmbb.smartkids.activity.user.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.user.holder.MyCommunityHolder;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 11:58
 */
public class MyCommunityAdapter extends RecyclerArrayAdapter<TopicListModel.DataEntity.RowsEntity> {


    public MyCommunityAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyCommunityHolder(parent);
    }

}
