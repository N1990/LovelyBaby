package com.cmbb.smartkids.activity.home.holder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.TopicTypeModel;
import com.cmbb.smartkids.activity.home.adapter.TopicAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 14:38
 */
public class TopicHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TopicAdapter adapter;
    private int position;
    private TextView tv;


    public TopicHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv_topic_community_item);
    }

    public void setData(TopicTypeModel.DataEntity data, TopicAdapter adapter, int position) {
        this.adapter = adapter;
        this.position = position;
        if(position == adapter.getSelecIndex()){
            tv.setBackgroundColor(tv.getResources().getColor(R.color.primaryColorDark));
            tv.setTextColor(Color.WHITE);
        }else{
            tv.setBackgroundColor(Color.WHITE);
            tv.setTextColor(tv.getResources().getColor(R.color.primaryColorDark));
        }
        tv.setText(data.getName());
        tv.setOnClickListener(this);
        tv.setTag(data);
    }

    @Override
    public void onClick(View v) {
        if(adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, v.getTag());
    }
}
