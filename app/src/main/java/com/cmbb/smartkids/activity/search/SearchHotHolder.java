package com.cmbb.smartkids.activity.search;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.TopicTypeModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 14:38
 */
public class SearchHotHolder extends BaseViewHolder<TopicTypeModel.DataEntity> {

    private TextView tv;

    public SearchHotHolder(ViewGroup parent) {
        super(parent, R.layout.list_topic_item);
        tv = $(R.id.tv_topic_community_item);
    }

    @Override
    public void setData(TopicTypeModel.DataEntity data) {
        super.setData(data);
        tv.setBackgroundColor(Color.WHITE);
        tv.setText(data.getName());
        tv.setTextColor(tv.getResources().getColor(R.color.primaryColorDark));
    }
}
