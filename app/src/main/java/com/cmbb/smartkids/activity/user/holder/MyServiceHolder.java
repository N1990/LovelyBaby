package com.cmbb.smartkids.activity.user.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 11:22
 */
public class MyServiceHolder extends BaseViewHolder<ServiceListModel.DataEntity.RowsEntity> {
    private SimpleDraweeView ivHomeServiceItem;
    private TextView tvTitleHomeServiceItem;
    private ImageView ivTag;
    private TextView tvTimeHomeServiceItem;
    private TextView tvServiceWayItem;
    private TextView tvServiceStatusItem;
    private TextView tvServicePurchaseNumberItem;
    private TextView tvServicePriceItem;

    public MyServiceHolder(ViewGroup parent) {
        super(parent, R.layout.list_home_service_item_v);
        ivHomeServiceItem = $(R.id.iv_home_service_item);
        tvTitleHomeServiceItem = $(R.id.tv_title_home_service_item);
        ivTag = $(R.id.iv_tag);
        tvTimeHomeServiceItem = $(R.id.tv_time_home_service_item);
        tvServiceWayItem = $(R.id.tv_service_way_item);
        tvServiceStatusItem = $(R.id.tv_service_status_item);
        tvServicePurchaseNumberItem = $(R.id.tv_service_purchase_number_item);
        tvServicePriceItem = $(R.id.tv_service_price_item);
    }

    @Override
    public void setData(ServiceListModel.DataEntity.RowsEntity data) {
        FrescoTool.loadImage(ivHomeServiceItem, data.getServicesImg(), String.valueOf(TDevice.dip2px(90, ivHomeServiceItem.getContext())));
        tvTitleHomeServiceItem.setText(data.getTitle());
        tvTimeHomeServiceItem.setText("活动时间: " + data.getServiceTime());
        tvServiceWayItem.setText("服务方式: " + data.getTypeText());
        tvServiceStatusItem.setText(data.getStatusName());
        tvServicePurchaseNumberItem.setText("已购买人数" + data.getRealityPeoples());
        if (!TextUtils.isEmpty(data.getPrice())) {
            double price = Double.valueOf(data.getPrice());
            tvServicePriceItem.setText(price != 0 ? "￥" + data.getPrice() : "免费");
        }

        if (data.isIsNew()) {
            ivTag.setVisibility(View.VISIBLE);
        } else {
            ivTag.setVisibility(View.GONE);
        }
    }

}
