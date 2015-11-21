package com.cmbb.smartkids.activity.search;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.TopicTypeModel;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 14:38
 */
public class SearchHotHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private SearchHotAdapter adapter;
    private int position;
    private TextView tv;


    public SearchHotHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv_topic_community_item);
    }

    public void setData(TopicTypeModel.DataEntity data, SearchHotAdapter adapter, int position) {
        this.adapter = adapter;
        this.position = position;
        /*if (position == adapter.getSelecIndex()) {
            tv.setBackgroundColor(tv.getResources().getColor(R.color.primaryColorDark));
            tv.setTextColor(Color.WHITE);
        } else {*/
        tv.setBackgroundColor(Color.WHITE);
        tv.setText(data.getName());
        tv.setTextColor(tv.getResources().getColor(R.color.primaryColorDark));
        /*}*/
        tv.setOnClickListener(this);
        tv.setTag(data);
    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, v.getTag());
    }
}
