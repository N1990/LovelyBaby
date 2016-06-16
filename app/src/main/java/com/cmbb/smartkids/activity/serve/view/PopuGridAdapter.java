package com.cmbb.smartkids.activity.serve.view;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.activity.serve.view.model.H5ServiceDetailModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;


/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 13:31
 */
public class PopuGridAdapter extends RecyclerArrayAdapter<H5ServiceDetailModel.PriceListEntity> {

    public PopuGridAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopuGridItemHolder(parent);
    }
}
