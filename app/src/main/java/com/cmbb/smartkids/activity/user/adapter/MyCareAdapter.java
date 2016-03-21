package com.cmbb.smartkids.activity.user.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.user.holder.MyCareHolder;
import com.cmbb.smartkids.activity.user.model.FriendListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 14:09
 */
public class MyCareAdapter extends RecyclerArrayAdapter<FriendListModel.DataEntity.RowsEntity> {


    public MyCareAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyCareHolder(parent);
    }
}
