package com.cmbb.smartkids.activity.diary.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.diary.holder.BabyItemHolder;
import com.cmbb.smartkids.activity.diary.model.BabyListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 13:31
 */
public class BabyListAdapter extends RecyclerArrayAdapter<BabyListModel.DataEntity.RowsEntity> {

    public BabyListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BabyItemHolder(parent);
    }
}
