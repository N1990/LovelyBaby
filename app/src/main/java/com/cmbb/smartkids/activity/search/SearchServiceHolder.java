package com.cmbb.smartkids.activity.search;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.model.ServiceStatus;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.Tools;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 17:15
 */
public class SearchServiceHolder extends BaseViewHolder<SearchServiceModel.DataEntity.RowsEntity> {
    private final String TAG = SearchServiceHolder.class.getSimpleName();
    private RelativeLayout llHomeServiceItem;
    private SimpleDraweeView ivHomeServiceItem;
    private TextView tvTitleHomeServiceItem;
    private TextView tvPriceHomeServiceItem;
    private TextView tvCityHomeServiceItem;
    private TextView tvPreviewHomeServiceItem;
    private TextView tvTimeHomeServiceItem;
    private TextView tvTagHomeServiceItem;

    public SearchServiceHolder(ViewGroup parent) {
        super(parent, R.layout.list_home_service_item);
        llHomeServiceItem = $(R.id.ll_home_service_item);
        ivHomeServiceItem = $(R.id.iv_home_service_item);
        tvTitleHomeServiceItem = $(R.id.tv_title_home_service_item);
        tvPriceHomeServiceItem = $(R.id.tv_price_home_service_item);
        tvPreviewHomeServiceItem = $(R.id.tv_preview_home_service_item);
        tvCityHomeServiceItem = $(R.id.tv_city_home_service_item);
        tvTimeHomeServiceItem = $(R.id.tv_time_home_service_item);
        tvTagHomeServiceItem = $(R.id.tv_home_service_tag_item);

    }


    @Override
    public void setData(SearchServiceModel.DataEntity.RowsEntity data) {
        super.setData(data);
        FrescoTool.loadImage(ivHomeServiceItem, data.getServicesImg(), String.valueOf(TDevice.dip2px(90, ivHomeServiceItem.getContext())));
        tvTitleHomeServiceItem.setText(data.getTitle());
        if (!TextUtils.isEmpty(data.getPrice())) {
            double price = Double.valueOf(data.getPrice());
            tvPriceHomeServiceItem.setText(price != 0 ? "￥" + data.getPrice() : "免费");
        }
        tvPreviewHomeServiceItem.setText(data.getBrowseNumber() + "");
        tvTagHomeServiceItem.setVisibility(View.VISIBLE);
        int statusValue = data.getStatus();
        ServiceStatus status = ServiceStatus.getStatusByValue(statusValue);
        switch (status) {
            case WEI_KAI_SHI:
                tvTagHomeServiceItem.setVisibility(View.GONE);
                break;
            case YI_KAI_SHI:
                tvTagHomeServiceItem.setText("已开始");
                break;
            case YU_DING_ZHONG:
                tvTagHomeServiceItem.setText("报名中");
                break;
            case YI_JIE_SHU:
                tvTagHomeServiceItem.setText("已过期");
                break;
        }
        tvCityHomeServiceItem.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(data.getCityText()))
            tvCityHomeServiceItem.setVisibility(View.GONE);
        tvCityHomeServiceItem.setText(data.getCityText());
        try {
            String applyStartTime = Tools.DataToString(data.getApplyStartTime(), "MM/dd");
            String applyEndTime = Tools.DataToString(data.getApplyStartTime(), "MM/dd");
            tvTimeHomeServiceItem.setText(applyStartTime + "-" + applyEndTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
