package com.cmbb.smartkids.activity.home.home_v2.holder;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.TopicTypeModel;
import com.cmbb.smartkids.activity.home.home_v2.adapter.TopicTypeAdapter;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 14:38
 */
public class TopicTypeItemHolder extends BaseViewHolder<TopicTypeModel.DataEntity> implements View.OnClickListener {
    private TextView tv;

    private TopicTypeAdapter adapter;

    public TopicTypeItemHolder(ViewGroup parent, TopicTypeAdapter adapter) {
        super(parent, R.layout.activity_topic_type_list_item);
        this.adapter = adapter;
        tv = $(R.id.tv_topic_community_item);
    }

    @Override
    public void setData(TopicTypeModel.DataEntity data, int position) {
        data.setPosition(position);
        if (position == adapter.getSelecIndex()) {
            tv.setBackgroundColor(tv.getResources().getColor(R.color.primaryColorDark));
            tv.setTextColor(Color.WHITE);
        } else {
            tv.setBackgroundColor(Color.WHITE);
            tv.setTextColor(tv.getResources().getColor(R.color.primaryColorDark));
        }
        tv.setText(data.getName());
        tv.setOnClickListener(this);
        tv.setTag(data);
    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, ((TopicTypeModel.DataEntity)v.getTag()).getPosition(), v.getTag());
    }
}
