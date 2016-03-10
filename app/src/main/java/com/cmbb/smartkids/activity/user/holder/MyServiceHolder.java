package com.cmbb.smartkids.activity.user.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
public class MyServiceHolder extends BaseViewHolder<ServiceListModel.DataEntity.RowsEntity>  {
    private RelativeLayout llHomeServiceItem;
    private LinearLayout llHomeServiceItemFrist;
    private SimpleDraweeView ivHomeServiceItem;
    private TextView tvServiceItemCity;
    private LinearLayout llTitleHomeServiceItem;
    private TextView tvTitleHomeServiceItem;
    private TextView tvHomeServiceTagItem;
    private TextView tvTimeHomeServiceItem;
    private TextView tvServiceWayItem;
    private TextView tvServiceStatusItem;
    private TextView tvServicePurchaseNumberItem;
    private TextView tvServicePriceItem;

    public MyServiceHolder(ViewGroup parent) {
        super(parent, R.layout.list_home_service_item_v);
        llHomeServiceItem = $(R.id.ll_home_service_item);
        llHomeServiceItemFrist = $(R.id.ll_home_service_item_frist);
        ivHomeServiceItem = $(R.id.iv_home_service_item);
        tvServiceItemCity = $(R.id.tv_service_item_city);
        llTitleHomeServiceItem = $(R.id.ll_title_home_service_item);
        tvTitleHomeServiceItem = $(R.id.tv_title_home_service_item);
        tvHomeServiceTagItem = $(R.id.tv_home_service_tag_item);
        tvTimeHomeServiceItem = $(R.id.tv_time_home_service_item);
        tvServiceWayItem = $(R.id.tv_service_way_item);
        tvServiceStatusItem = $(R.id.tv_service_status_item);
        tvServicePurchaseNumberItem = $(R.id.tv_service_purchase_number_item);
        tvServicePriceItem = $(R.id.tv_service_price_item);
    }


    @Override
    public void setData(ServiceListModel.DataEntity.RowsEntity data) {
        FrescoTool.loadImage(ivHomeServiceItem, data.getServicesImg(), String.valueOf(TDevice.dip2px(90, ivHomeServiceItem.getContext())));
        tvServiceItemCity.setText(data.getCityText());
        tvTitleHomeServiceItem.setText(data.getTitle());
        tvTimeHomeServiceItem.setText("活动时间:" + data.getServiceTime());
        tvServiceWayItem.setText("服务方式:" + data.getType());
        tvServiceStatusItem.setText(data.getStatusName());
        tvServicePurchaseNumberItem.setText("已购买人数" + data.getRealityPeoples());
        if (!TextUtils.isEmpty(data.getPrice())) {
            double price = Double.valueOf(data.getPrice());
            tvServicePriceItem.setText(price != 0 ? "￥" + data.getPrice() : "免费");
        }

        if(data.isIsNew()){
            tvHomeServiceTagItem.setVisibility(View.VISIBLE);
            tvHomeServiceTagItem.setText("NEW");
        }else{
            tvHomeServiceTagItem.setVisibility(View.GONE);
        }
    }

}
