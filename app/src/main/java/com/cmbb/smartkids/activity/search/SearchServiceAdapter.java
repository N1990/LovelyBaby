package com.cmbb.smartkids.activity.search;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;


/*
* * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 16:12
 * */


public class SearchServiceAdapter extends RecyclerArrayAdapter<SearchServiceModel.DataEntity.RowsEntity> {

    public SearchServiceAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchServiceHolder(parent);
    }


}
