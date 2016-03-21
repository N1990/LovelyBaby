package com.cmbb.smartkids.activity.message.holder;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.message.model.MessageListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by javon on 15/12/10.
 */
public class MsgServiceHolder extends BaseViewHolder<MessageListModel.DataEntity.RowsEntity> {
    private SimpleDraweeView iv;
    private TextView tvTitle, tvContent;


    public MsgServiceHolder(ViewGroup parent) {
        super(parent, R.layout.list_msg_service_item);
        iv = (SimpleDraweeView) itemView.findViewById(R.id.iv_msg_service_item);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title_msg_service_item);
        tvContent = (TextView) itemView.findViewById(R.id.tv_content_msg_service_item);
    }


    public void setData(MessageListModel.DataEntity.RowsEntity data) {
        tvContent.setText(data.getContents());
        tvTitle.setText(data.getTitle());
        if (!TextUtils.isEmpty(data.getImg()))
            FrescoTool.loadImage(iv, data.getImg());
    }
}
