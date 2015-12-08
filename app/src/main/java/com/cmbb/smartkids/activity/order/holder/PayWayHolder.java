package com.cmbb.smartkids.activity.order.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.adapter.PayWayAdapter;
import com.cmbb.smartkids.activity.order.model.PayWayModel;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/22 20:30
 */
public class PayWayHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ImageView iv;
    private TextView tv;
    private RadioButton rb;
    private PayWayAdapter adapter;
    private View root;
    private int position;

    public PayWayHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        iv = (ImageView) itemView.findViewById(R.id.iv_pay_way_item);
        tv = (TextView) itemView.findViewById(R.id.tv_pay_way_name_item);
        rb = (RadioButton) itemView.findViewById(R.id.rb_pay_way_item);
    }

    public void setData(PayWayAdapter adapter, int position, PayWayModel.PayTypesEntity model){
        this.adapter = adapter;
        this.position = position;
        this.root.setTag(model);
        this.root.setOnClickListener(this);
        this.rb.setChecked(position == adapter.getSelectPos());
        tv.setText(model.getName());
    }

    @Override
    public void onClick(View v) {
        if(adapter.getOnItemClick() != null)
            adapter.getOnItemClick().onItemClick(v, position, v.getTag());
    }
}
