package com.cmbb.smartkids.activity.message.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.message.model.MessageListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;

/**
 * Created by javon on 15/12/10.
 */
public class MsgOrderHolder extends BaseViewHolder<MessageListModel.DataEntity.RowsEntity> {
    private TextView tvTitle, tvContent;


    public MsgOrderHolder(ViewGroup parent) {
        super(parent, R.layout.list_msg_order_item);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title_msg_order_item);
        tvContent = (TextView) itemView.findViewById(R.id.tv_content_msg_order_item);
    }


    public void setData(MessageListModel.DataEntity.RowsEntity data) {
        tvContent.setText(data.getContents());
        tvTitle.setText(data.getTitle());
    }

}
