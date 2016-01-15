package com.cmbb.smartkids.activity.order.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.adapter.AcceptOrderAdapter;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.model.OrderStatus;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/15 14:07
 */
public class AcceptOrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private View root;
    private SimpleDraweeView ivHeader;
    private TextView tvTitle, tvStatus, tvNumber, tvTime;
    private AcceptOrderAdapter adapter;
    private int position;

    public AcceptOrderHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        ivHeader = (SimpleDraweeView) itemView.findViewById(R.id.iv_header_accept_order);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title_accept_order);
        tvStatus = (TextView) itemView.findViewById(R.id.tv_status_accept_order);
        tvNumber = (TextView) itemView.findViewById(R.id.tv_number_accept_order);
        tvTime = (TextView) itemView.findViewById(R.id.tv_time_accept_order);
    }

    public void setData(AcceptOrderAdapter adapter, int position, OrderListModel.DataEntity.RowsEntity itemData){
        this.adapter = adapter;
        this.position = position;
        FrescoTool.loadImage(ivHeader, itemData.getServicesImg());
        tvTitle.setText(itemData.getTitle());
        tvStatus.setText(OrderStatus.getStatusByValue(itemData.getStatus()).toString());
        tvNumber.setText("订单号：" + itemData.getOrderCode());
        tvTime.setText("订单时间：" + itemData.getOrderDate());
        root.setTag(itemData);
        root.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, root.getTag());
    }
}
