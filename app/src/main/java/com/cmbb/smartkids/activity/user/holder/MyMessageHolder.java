package com.cmbb.smartkids.activity.user.holder;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.model.MessageListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.Tools;

import java.text.ParseException;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/16 15:58
 */
public class MyMessageHolder extends BaseViewHolder<MessageListModel.DataEntity.RowsEntity> {
    private TextView tvMessage, tvSort, tvTime;
    private ImageView ivTag;

    public MyMessageHolder(ViewGroup itemView) {
        super(itemView, R.layout.list_service_message_item);
        tvMessage =$(R.id.tv_message_item);
        tvSort = $(R.id.tv_message_sort_item);
        tvTime = $(R.id.tv_message_time_item);
        ivTag = $(R.id.iv_message_tag_item);
    }

    @Override
    public void setData(MessageListModel.DataEntity.RowsEntity data) {
        int readFlag = data.getIsRead();
        if (readFlag == 1) {
            tvMessage.setTextColor(Color.GRAY);
            tvSort.setTextColor(Color.GRAY);

        } else {
            tvMessage.setTextColor(Color.BLACK);
            tvSort.setTextColor(Color.BLACK);
        }
        tvMessage.setText(data.getContents());
        tvSort.setText(data.getTitle());
        String sort = data.getModual();
        ivTag.setVisibility(View.VISIBLE);
        if ("order".equals(sort)) {
            ivTag.setBackgroundResource(R.mipmap.btn_message_order_bg);
        } else if ("service".equals(sort)) {
            ivTag.setBackgroundResource(R.mipmap.btn_message_service_bg);
        } else if ("system".equals(sort)) {
            ivTag.setBackgroundResource(R.mipmap.btn_message_home_bg);
        } else {
            ivTag.setVisibility(View.INVISIBLE);
        }
        try {
            tvTime.setText(Tools.DataToString(data.getCreateDate(), "yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
