package com.cmbb.smartkids.activity.order.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.adapter.MyOrderAdapter;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.model.OrderStatus;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 14:55
 */
public class MyOrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final String TAG = MyOrderHolder.class.getSimpleName();
    private LinearLayout root , llHandler;
    private SimpleDraweeView ivOrde;
    private TextView tvTitle, tvPrice, tvStatus, tvHandler, tvPay;
    private View vDivider, vPayDivider;
    private MyOrderAdapter adapter;
    private int position;

    public MyOrderHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View root) {
        this.root = (LinearLayout) root.findViewById(R.id.ll_order_root_item);
        ivOrde = (SimpleDraweeView) root.findViewById(R.id.iv_order_item);
        tvTitle = (TextView) root.findViewById(R.id.tv_order_title_item);
        tvPrice = (TextView) root.findViewById(R.id.tv_order_price_item);
        tvStatus = (TextView) root.findViewById(R.id.tv_order_status_item);
        tvHandler = (TextView) root.findViewById(R.id.tv_order_cancel_item);
        tvPay = (TextView) root.findViewById(R.id.tv_order_pay_item);
        llHandler = (LinearLayout) root.findViewById(R.id.ll_order_list_handler);
        vDivider = root.findViewById(R.id.v_order_list_divider);
        vPayDivider = root.findViewById(R.id.v_order_pay_divider);

    }


    public void setData(MyOrderAdapter adapter, int position, OrderListModel.DataEntity.RowsEntity obj) {
        this.adapter = adapter;
        this.position = position;
        FrescoTool.loadImage(ivOrde, obj.getServicesImg());
        tvTitle.setText(obj.getTitle());
        String price = obj.getPrice();
        tvPrice.setText(Double.valueOf(price) == 0?"费用：免费" : "费用：" + price + "元");
        OrderStatus status  = OrderStatus.getStatusByValue(obj.getStatus());
        tvStatus.setText(status.toString());
        switch (status){
            case WEI_ZHI_FU:
                llHandler.setVisibility(View.VISIBLE);
                vDivider.setVisibility(View.VISIBLE);
                tvPay.setVisibility(View.VISIBLE);
                vPayDivider.setVisibility(View.VISIBLE);
                tvStatus.setText(status.toString());
                tvHandler.setVisibility(View.VISIBLE);
                tvHandler.setText("取消订单");
                break;
            case YI_ZHI_FU:
                double p = Double.valueOf(price);
                llHandler.setVisibility(View.VISIBLE);
                vDivider.setVisibility(View.VISIBLE);
                tvPay.setVisibility(View.GONE);
                vPayDivider.setVisibility(View.GONE);
                tvStatus.setText(status.toString());
                tvHandler.setVisibility(View.VISIBLE);
                if(p != 0){
                    tvHandler.setText("申请退款");
                }else{
                    tvHandler.setText("取消订单");
                }
                break;
            case YI_GUO_QI:
                llHandler.setVisibility(View.VISIBLE);
                vDivider.setVisibility(View.VISIBLE);
                tvPay.setVisibility(View.GONE);
                vPayDivider.setVisibility(View.GONE);
                tvStatus.setText(status.toString());
                tvHandler.setVisibility(View.VISIBLE);
                tvHandler.setText("申请退款");
                break;
            case YI_CAN_JIA:
                llHandler.setVisibility(View.VISIBLE);
                vDivider.setVisibility(View.VISIBLE);
                tvPay.setVisibility(View.GONE);
                vPayDivider.setVisibility(View.GONE);
                tvStatus.setText(status.toString());
                tvHandler.setVisibility(View.VISIBLE);
                tvHandler.setText("立即评价");
                break;
            case YI_PING_JIA:
            case YI_TUI_KUAN:
            case YI_QU_XIAO:
            case TUI_KUAN_ZHONG:
                vDivider.setVisibility(View.GONE);
                tvPay.setVisibility(View.GONE);
                vPayDivider.setVisibility(View.GONE);
                tvStatus.setText(status.toString());
                llHandler.setVisibility(View.GONE);
                vDivider.setVisibility(View.GONE);
                break;

        }
        root.setTag(obj);
        root.setOnClickListener(this);
        tvHandler.setOnClickListener(this);
        tvPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_order_root_item:
                if (adapter.getOnItemListener() != null)
                    adapter.getOnItemListener().onItemClick(v, position, root.getTag());
                break;
            case R.id.tv_order_cancel_item:
                if (adapter.getOnHandlerListener() != null)
                    adapter.getOnHandlerListener().onItemClick(v, position, root.getTag());
                break;
            case R.id.tv_order_pay_item:
                if(adapter.getOnPayListener() != null)
                    adapter.getOnPayListener().onItemClick(v, position, root.getTag());
                break;
        }

    }
}
