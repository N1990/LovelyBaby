package com.cmbb.smartkids.activity.message.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.message.adapter.MsgOfficalAdapter;
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

    public void setData(MsgOfficalAdapter adapter, int position){
        this.adapter = adapter;
        this.position = position;
        if(position == 4)
            vDivider.setVisibility(View.GONE);
        itemView.setTag(position);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, v.getTag());
    }
}
