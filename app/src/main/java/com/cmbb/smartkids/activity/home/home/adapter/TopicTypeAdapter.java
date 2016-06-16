package com.cmbb.smartkids.activity.home.home.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.community.model.TopicTypeModel;
import com.cmbb.smartkids.activity.home.home.holder.TopicTypeItemHolder;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;


/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 13:31
 */
public class TopicTypeAdapter extends RecyclerArrayAdapter<TopicTypeModel.DataEntity> {

    private CustomListener.ItemClickListener onItemListener;
    private int selecIndex = 0;

    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public TopicTypeModel.DataEntity getSelectData() {
        if (selecIndex == -1)
            return this.mObjects.get(0);
        return this.mObjects.get(selecIndex);
    }

    public void setSelectIndex(int selecIndex) {
        this.selecIndex = selecIndex;
        notifyDataSetChanged();
    }


    public int getSelecIndex() {
        return this.selecIndex;
    }

    public TopicTypeAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicTypeItemHolder(parent, this);
    }
}
