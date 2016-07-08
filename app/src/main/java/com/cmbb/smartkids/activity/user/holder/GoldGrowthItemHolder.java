package com.cmbb.smartkids.activity.user.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.model.GoldGrowthModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 17:15
 */
public class GoldGrowthItemHolder extends BaseViewHolder<GoldGrowthModel.DataEntity.RowsEntity> {

    private TextView tvTitle;
    private TextView tvTime;
    private TextView tvCount;

    public GoldGrowthItemHolder(ViewGroup parent) {
        super(parent, R.layout.list_gold_growth_item);
        tvTitle = $(R.id.tv_title);
        tvTime = $(R.id.tv_time);
        tvCount = $(R.id.tv_count);
    }

    public void setData(GoldGrowthModel.DataEntity.RowsEntity row) {
        if (row == null)
            return;
        tvTitle.setText(row.getContents());
        tvTime.setText(row.getGetDate());
        tvCount.setText("+ " + row.getGrowth());
    }
}
