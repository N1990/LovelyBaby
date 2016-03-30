package com.cmbb.smartkids.activity.order.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.model.OrderStatus;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/15 14:07
 */
public class AcceptOrderHolder extends BaseViewHolder<OrderListModel.DataEntity.RowsEntity> {
    private SimpleDraweeView ivHeader;
    private TextView tvTitle, tvStatus, tvNumber, tvTime;

    public AcceptOrderHolder(ViewGroup parent) {
        super(parent, R.layout.list_accept_order_item);
        ivHeader = $(R.id.iv_header_accept_order);
        tvTitle = $(R.id.tv_title_accept_order);
        tvStatus = $(R.id.tv_status_accept_order);
        tvNumber = $(R.id.tv_number_accept_order);
        tvTime = $(R.id.tv_time_accept_order);
    }

    public void setData(OrderListModel.DataEntity.RowsEntity itemData) {
        FrescoTool.loadImage(ivHeader, itemData.getServicesImg());
        tvTitle.setText(itemData.getTitle());
        tvStatus.setText(OrderStatus.getStatusByValue(itemData.getStatus()).toString());
        tvNumber.setText("订单号：" + itemData.getOrderCode());
        tvTime.setText("订单时间：" + itemData.getOrderDate());
    }
}
