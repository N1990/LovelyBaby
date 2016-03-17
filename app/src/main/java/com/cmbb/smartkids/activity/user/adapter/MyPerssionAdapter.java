package com.cmbb.smartkids.activity.user.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.order.model.EvaluateListModel;
import com.cmbb.smartkids.activity.user.holder.MyPerssionHolder;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/15 10:54
 */
public class MyPerssionAdapter extends RecyclerArrayAdapter<EvaluateListModel.DataEntity.RowsEntity> {
    public MyPerssionAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyPerssionHolder(parent);
    }
}
