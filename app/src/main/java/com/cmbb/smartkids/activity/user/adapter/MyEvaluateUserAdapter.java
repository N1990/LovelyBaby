package com.cmbb.smartkids.activity.user.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.user.holder.MyEvaluateUserHolder;
import com.cmbb.smartkids.activity.user.model.UserEvaluateModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by javon on 16/3/21.
 */
public class MyEvaluateUserAdapter extends RecyclerArrayAdapter<UserEvaluateModel.DataEntity.RowsEntity> {


    public MyEvaluateUserAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyEvaluateUserHolder(parent);
    }
}
