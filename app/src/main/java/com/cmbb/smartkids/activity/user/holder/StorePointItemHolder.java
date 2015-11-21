package com.cmbb.smartkids.activity.user.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.StorePointAdapter;
import com.cmbb.smartkids.activity.user.model.StorePointModel;
import com.cmbb.smartkids.utils.JTimeTransform;
import com.cmbb.smartkids.utils.RecentDateFormat;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/16 15:58
 */
public class StorePointItemHolder extends RecyclerView.ViewHolder {
    private View root;
    private TextView tvContent;
    private TextView tvCount;
    private TextView tvTime;
    private StorePointAdapter adapter;
    private int position;

    public StorePointItemHolder(View view) {
        super(view);
        this.root = view;
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        tvCount = (TextView) view.findViewById(R.id.tv_count);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
    }

    public void setData(StorePointAdapter adapter, StorePointModel.DataEntity.RowsEntity data, int position) {
        this.adapter = adapter;
        this.position = position;
        tvContent.setText(data.getRemarks());
        tvCount.setText(String.valueOf(data.getGoldCount()));
        tvTime.setText(new JTimeTransform(data.getValidDate()).toString(new RecentDateFormat()));
    }

}
