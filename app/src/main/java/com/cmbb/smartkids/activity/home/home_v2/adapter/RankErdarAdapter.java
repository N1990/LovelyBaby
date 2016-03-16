package com.cmbb.smartkids.activity.home.home_v2.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.home.home_v2.holder.RankErdarItemHolder;
import com.cmbb.smartkids.activity.home.model.RankEredarModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;


/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 13:31
 */
public class RankErdarAdapter extends RecyclerArrayAdapter<RankEredarModel> {

    public RankErdarAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RankErdarItemHolder(parent);
    }
}
