package com.cmbb.smartkids.activity.order.holder;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.adapter.OrderAdapter;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.model.OrderStatus;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by javon on 16/3/17.
 */
public class OrderHolder extends BaseViewHolder<OrderListModel.DataEntity.RowsEntity> {
    private OrderAdapter adapter;
    private TextView tvOrderCodeItem;
    private TextView tvOrderStatusItem;
    private SimpleDraweeView sdvOrderItem;
    private TextView tvOrderTitleItem;
    private TextView tvOrderNumItem;
    private TextView tvOrderPriceItem;
    private TextView tvOrderCancleItem;
    private TextView tvOrderHandlerItem;
    private TextView tvOrderCheckItem;

    public OrderHolder(ViewGroup parent, OrderAdapter adapter) {
        super(parent, R.layout.list_order_item_v);
        this.adapter = adapter;
        tvOrderCodeItem = $(R.id.tv_order_code_item);
        tvOrderStatusItem = $(R.id.tv_order_status_item);
        sdvOrderItem = $(R.id.sdv_order_item);
        tvOrderTitleItem =  $(R.id.tv_order_title_item);
        tvOrderNumItem =  $(R.id.tv_order_num_item);
        tvOrderPriceItem =  $(R.id.tv_order_price_item);
        tvOrderCancleItem = $(R.id.tv_order_cancle_item);
        tvOrderHandlerItem =  $(R.id.tv_order_handler_item);
        tvOrderCheckItem = $(R.id.tv_order_check_item);
    }

    @Override
    public void setData(final OrderListModel.DataEntity.RowsEntity data, final int position) {
        tvOrderCodeItem.setText("订单编号 :" + data.getOrderCode());
        FrescoTool.loadImage(sdvOrderItem, data.getServicesImg(), 1.67f);
        tvOrderTitleItem.setText(data.getTitle());
        tvOrderNumItem.setText("数量:" + data.getBuyCount());
        String price = data.getPrice();
        String  orderPrice = Double.valueOf(price) == 0?"费用：免费" : "费用：" + price + "元";
        SpannableString spanText = new SpannableString(orderPrice);
        spanText.setSpan(new ForegroundColorSpan(tvOrderPriceItem.getResources().getColor(R.color.primaryColor)), 3, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tvOrderPriceItem.setText(spanText);
        OrderStatus status  = OrderStatus.getStatusByValue(data.getStatus());
        tvOrderStatusItem.setText(status.toString());
        switch (status){ //已取消  已退款  退款中   已预订  已过期
            case WEI_ZHI_FU:
                tvOrderHandlerItem.setVisibility(View.VISIBLE);
                tvOrderHandlerItem.setText("去支付");
                tvOrderStatusItem.setText(status.toString());
                tvOrderCancleItem.setVisibility(View.VISIBLE);
                tvOrderCancleItem.setText("取消订单");
                tvOrderCheckItem.setVisibility(View.GONE);
                break;
            case YI_ZHI_FU:
                tvOrderCancleItem.setVisibility(View.GONE);
                tvOrderStatusItem.setText(status.toString());
                tvOrderHandlerItem.setVisibility(View.VISIBLE);
                tvOrderHandlerItem.setText("申请退款");
                tvOrderCheckItem.setVisibility(View.GONE);
                break;
            case YI_GUO_QI: //查看订单
                tvOrderCancleItem.setVisibility(View.GONE);
                tvOrderStatusItem.setText(status.toString());
                tvOrderHandlerItem.setVisibility(View.VISIBLE);
                tvOrderHandlerItem.setText("申请退款");
                tvOrderCheckItem.setVisibility(View.GONE);
                break;
            case YI_CAN_JIA:
                tvOrderCancleItem.setVisibility(View.GONE);
                tvOrderStatusItem.setText(status.toString());
                tvOrderHandlerItem.setVisibility(View.VISIBLE);
                tvOrderHandlerItem.setText("立即评价");
                tvOrderCheckItem.setVisibility(View.GONE);
                break;
            case YI_PING_JIA: //查看订单
            case YI_TUI_KUAN:
            case YI_QU_XIAO:
            case TUI_KUAN_ZHONG:
                tvOrderCancleItem.setVisibility(View.GONE);
                tvOrderHandlerItem.setVisibility(View.GONE);
                tvOrderStatusItem.setText(status.toString());
                tvOrderCheckItem.setVisibility(View.VISIBLE);
                break;
            case YI_YU_DING: //查看订单
                tvOrderCancleItem.setVisibility(View.VISIBLE);
                tvOrderStatusItem.setText(status.toString());
                tvOrderHandlerItem.setVisibility(View.GONE);
                tvOrderCheckItem.setVisibility(View.VISIBLE);
                break;
        }
        tvOrderCancleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getOnCancelListener() != null) {
                    adapter.getOnCancelListener().onItemClick(v, position, data);
                }
            }
        });
        tvOrderHandlerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getOnHandlerListener() != null) {
                    adapter.getOnHandlerListener().onItemClick(v, position, data);
                }
            }
        });

        tvOrderCheckItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.getOnCkeckOrderListener() != null){
                    adapter.getOnCkeckOrderListener().onItemClick(v, position, data);
                }
            }
        });
    }
}
