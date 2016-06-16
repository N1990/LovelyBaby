package com.cmbb.smartkids.activity.home.home.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.home.home.holder.TopicItemHolder;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;


/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 13:31
 */
public class TopicAdapter extends RecyclerArrayAdapter<TopicListModel.DataEntity.RowsEntity> {

    public TopicAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicItemHolder(parent);
    }
}
