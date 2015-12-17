package com.cmbb.smartkids.activity.message.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.message.adapter.MsgOfficalAdapter;
import com.cmbb.smartkids.activity.message.model.MessageListModel;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by javon on 15/12/10.
 */
public class MsgOfficalHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private SimpleDraweeView ivHeader;
    private TextView tvTitle, tvContent;
    private View vDivider;
    private MsgOfficalAdapter adapter;
    private int position;

    public MsgOfficalHolder(View itemView) {
        super(itemView);
        ivHeader = (SimpleDraweeView) itemView.findViewById(R.id.iv_msg_offical_item);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title_msg_offical_item);
        tvContent = (TextView) itemView.findViewById(R.id.tv_content_msg_offical_item);
        vDivider = itemView.findViewById(R.id.v_msg_divider);
    }

    public void setData(MsgOfficalAdapter adapter, MessageListModel.DataEntity.RowsEntity data){
        this.adapter = adapter;
        tvContent.setText(data.getContents());
        tvTitle.setText(data.getTitle());
        if (!TextUtils.isEmpty(data.getImg()))
            FrescoTool.loadImage(ivHeader, data.getImg());
    }

    @Override
    public void onClick(View v) {
        if(adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, v.getTag());
    }
}
