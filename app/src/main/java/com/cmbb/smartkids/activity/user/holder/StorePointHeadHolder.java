package com.cmbb.smartkids.activity.user.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.StorePointAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/16 15:58
 */
public class StorePointHeadHolder extends RecyclerView.ViewHolder {
    private View root;
    private TextView tvPointTitle;
    private TextView tvPointCount;
    private StorePointAdapter adapter;
    private int position;

    public StorePointHeadHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        tvPointTitle = (TextView) itemView.findViewById(R.id.tv_point_title);
        tvPointCount = (TextView) itemView.findViewById(R.id.tv_point_count);
    }

    public void setData(StorePointAdapter adapter, int data, int position) {
        this.adapter = adapter;
        this.position = position;
        tvPointCount.setText(data + "");

    }


}
