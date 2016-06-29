package com.cmbb.smartkids.activity.user.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.user.holder.GoldGrowthItemHolder;
import com.cmbb.smartkids.activity.user.model.GoldGrowthModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 13:31
 */
public class GoldGrowthAdapter extends RecyclerArrayAdapter<GoldGrowthModel.DataEntity.RowsEntity> {

    public GoldGrowthAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoldGrowthItemHolder(parent);
    }
}
