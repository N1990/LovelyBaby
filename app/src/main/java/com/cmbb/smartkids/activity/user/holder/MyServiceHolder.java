package com.cmbb.smartkids.activity.user.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.activity.user.adapter.MyServiceAdapter;
import com.cmbb.smartkids.model.ServiceStatus;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.Tools;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 11:22
 */
public class MyServiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private MyServiceAdapter adapter;
    private View item;
    private SimpleDraweeView ivBg;
    private TextView tvTitle, tvPrice, tvPreview, tvCity, tvTime, tvTag;
    private int position;


    public MyServiceHolder(View itemView) {
        super(itemView);
        this.item = itemView;
        tvTag = (TextView) itemView.findViewById(R.id.tv_home_service_tag_item);
        ivBg = (SimpleDraweeView) itemView.findViewById(R.id.iv_home_service_item);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title_home_service_item);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_price_home_service_item);
        tvPreview = (TextView) itemView.findViewById(R.id.tv_preview_home_service_item);
        tvCity = (TextView) itemView.findViewById(R.id.tv_city_home_service_item);
        tvTime = (TextView) itemView.findViewById(R.id.tv_time_home_service_item);
    }

    public void setData(MyServiceAdapter adapter, int position, ServiceListModel.DataEntity.RowsEntity itemData) {
        this.adapter = adapter;
        this.position = position;
        FrescoTool.loadImage(ivBg, itemData.getServicesImg(), String.valueOf(TDevice.dip2px(200, ivBg.getContext())));
        tvTitle.setText(itemData.getTitle());
        double price = Double.valueOf(itemData.getPrice());
        tvPrice.setText(price != 0 ? "￥" + price : "免费");
        tvPreview.setText(itemData.getBrowseNumber() + "");
        tvTag.setVisibility(View.VISIBLE);
        int statusValue = itemData.getStatus();
        ServiceStatus status = ServiceStatus.getStatusByValue(statusValue);
        switch (status) {
            case WEI_KAI_SHI:
                tvTag.setVisibility(View.GONE);
                break;
            case YI_KAI_SHI:
                tvTag.setText("已开始");
                break;
            case YU_DING_ZHONG:
                tvTag.setText("报名中");
                break;
            case YI_JIE_SHU:
                tvTag.setText("已过期");
                break;
        }

        tvCity.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(itemData.getCityText()))
            tvCity.setVisibility(View.GONE);
        tvCity.setText(itemData.getCityText());
        try {
            String applyStartTime = Tools.DataToString(itemData.getApplyStartTime(), "MM/dd");
            String applyEndTime = Tools.DataToString(itemData.getApplyStartTime(), "MM/dd");
            tvTime.setText(applyStartTime + "-" + applyEndTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        itemView.setTag(itemData);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnItemClick() != null)
            adapter.getOnItemClick().onItemClick(v, position, v.getTag());
    }
}
