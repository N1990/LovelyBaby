package com.cmbb.smartkids.activity.message.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.message.adapter.MsgOfficalAdapter;
import com.cmbb.smartkids.activity.message.adapter.MsgOrderAdapter;

/**
 * Created by javon on 15/12/10.
 */
public class MsgOrderHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
    private TextView tvTitle, tvContent;
    private View vDivider;
    private MsgOrderAdapter adapter;
    private int position;




    public MsgOrderHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title_msg_order_item);
        tvContent = (TextView) itemView.findViewById(R.id.tv_content_msg_order_item);
        vDivider = itemView.findViewById(R.id.v_msg_divider);
    }



    public void setData(MsgOrderAdapter adapter, int position){
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
