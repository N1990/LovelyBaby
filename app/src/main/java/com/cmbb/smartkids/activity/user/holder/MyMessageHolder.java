package com.cmbb.smartkids.activity.user.holder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.MyMsgAdapter;
import com.cmbb.smartkids.activity.user.model.MessageListModel;
import com.cmbb.smartkids.utils.Tools;

import java.text.ParseException;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/16 15:58
 */
public class MyMessageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvMessage, tvSort, tvTime;
    private ImageView ivTag;
    private View root;
    private MyMsgAdapter adapter;
    private int position;

    public MyMessageHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        tvMessage = (TextView) itemView.findViewById(R.id.tv_message_item);
        tvSort = (TextView) itemView.findViewById(R.id.tv_message_sort_item);
        tvTime = (TextView) itemView.findViewById(R.id.tv_message_time_item);
        ivTag = (ImageView) itemView.findViewById(R.id.iv_message_tag_item);
    }

    public void setData(MyMsgAdapter adapter, MessageListModel.DataEntity.RowsEntity data, int position) {
        this.adapter = adapter;
        this.position = position;
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
        root.setTag(data);
        root.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, root.getTag());
    }
}
