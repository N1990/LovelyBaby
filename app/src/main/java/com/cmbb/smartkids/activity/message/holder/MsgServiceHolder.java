package com.cmbb.smartkids.activity.message.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.message.adapter.MsgServiceAdapter;
import com.cmbb.smartkids.activity.message.model.MessageListModel;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by javon on 15/12/10.
 */
public class MsgServiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private SimpleDraweeView iv;
    private TextView tvTitle, tvContent;
    private View vDivider;
    private MsgServiceAdapter adapter;
    private int position;


    public MsgServiceHolder(View itemView) {
        super(itemView);
        iv = (SimpleDraweeView) itemView.findViewById(R.id.iv_msg_service_item);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title_msg_service_item);
        tvContent = (TextView) itemView.findViewById(R.id.tv_content_msg_service_item);
        vDivider = itemView.findViewById(R.id.v_msg_divider);
    }


    public void setData(MsgServiceAdapter adapter, MessageListModel.DataEntity.RowsEntity data) {
        this.adapter = adapter;
        itemView.setTag(data);
        itemView.setOnClickListener(this);
        tvContent.setText(data.getContents());
        tvTitle.setText(data.getTitle());
        if (!TextUtils.isEmpty(data.getImg()))
            FrescoTool.loadImage(iv, data.getImg());
    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, v.getTag());
    }
}
