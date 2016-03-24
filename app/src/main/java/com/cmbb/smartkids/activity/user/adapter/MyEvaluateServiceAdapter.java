package com.cmbb.smartkids.activity.user.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.user.holder.MyEvaluateServiceHolder;
import com.cmbb.smartkids.activity.user.model.EvaluateServiceModel;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by javon on 16/3/21.
 */
public class MyEvaluateServiceAdapter extends RecyclerArrayAdapter<EvaluateServiceModel.DataEntity.RowsEntity> {
    private CustomListener.ItemClickListener onServiceListener;

    public MyEvaluateServiceAdapter(Context context) {
        super(context);
    }

    public CustomListener.ItemClickListener getOnServiceListener() {
        return onServiceListener;
    }

    public void setOnServiceListener(CustomListener.ItemClickListener onServiceListener) {
        this.onServiceListener = onServiceListener;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyEvaluateServiceHolder(parent, this);
    }
}
