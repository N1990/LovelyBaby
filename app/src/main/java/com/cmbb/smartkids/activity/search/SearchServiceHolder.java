package com.cmbb.smartkids.activity.search;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.adapter.ServiceFraAdapter;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.model.ServiceStatus;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.Tools;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 17:15
 */
public class SearchServiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final String TAG = SearchServiceHolder.class.getSimpleName();
    private SearchServiceAdapter adapter;
    private RelativeLayout llHomeServiceItem;
    private SimpleDraweeView ivHomeServiceItem;
    private TextView tvTitleHomeServiceItem;
    private TextView tvPriceHomeServiceItem;
    private TextView tvCityHomeServiceItem;
    private TextView tvPreviewHomeServiceItem;
    private TextView tvTimeHomeServiceItem;
    private TextView tvTagHomeServiceItem;
    private int position;

    public SearchServiceHolder(View itemView) {
        super(itemView);
        llHomeServiceItem = (RelativeLayout) itemView.findViewById(R.id.ll_home_service_item);
        ivHomeServiceItem = (SimpleDraweeView) itemView.findViewById(R.id.iv_home_service_item);
        tvTitleHomeServiceItem = (TextView) itemView.findViewById(R.id.tv_title_home_service_item);
        tvPriceHomeServiceItem = (TextView) itemView.findViewById(R.id.tv_price_home_service_item);
        tvPreviewHomeServiceItem = (TextView) itemView.findViewById(R.id.tv_preview_home_service_item);
        tvCityHomeServiceItem = (TextView) itemView.findViewById(R.id.tv_city_home_service_item);
        tvTimeHomeServiceItem = (TextView) itemView.findViewById(R.id.tv_time_home_service_item);
        tvTagHomeServiceItem = (TextView) itemView.findViewById(R.id.tv_home_service_tag_item);

    }

    public void setData(SearchServiceModel.DataEntity.RowsEntity row, int position, SearchServiceAdapter adapter) {
        Log.e(TAG, "setData position : " + position);
        this.adapter = adapter;
        this.position = position;
        llHomeServiceItem.setTag(row);
        llHomeServiceItem.setOnClickListener(this);
        FrescoTool.loadImage(ivHomeServiceItem, row.getServicesImg(), String.valueOf(TDevice.dip2px(90, ivHomeServiceItem.getContext())));
        tvTitleHomeServiceItem.setText(row.getTitle());
        if (!TextUtils.isEmpty(row.getPrice())) {
            double price = Double.valueOf(row.getPrice());
            tvPriceHomeServiceItem.setText(price != 0 ? "￥" + row.getPrice() : "免费");
        }
        tvPreviewHomeServiceItem.setText(row.getBrowseNumber() + "");
        tvTagHomeServiceItem.setVisibility(View.VISIBLE);
        int statusValue = row.getStatus();
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
        if (TextUtils.isEmpty(row.getCityText()))
            tvCityHomeServiceItem.setVisibility(View.GONE);
        tvCityHomeServiceItem.setText(row.getCityText());
        try {
            String applyStartTime = Tools.DataToString(row.getApplyStartTime(), "MM/dd");
            String applyEndTime = Tools.DataToString(row.getApplyStartTime(), "MM/dd");
            tvTimeHomeServiceItem.setText(applyStartTime + "-" + applyEndTime);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnItemClick() != null)
            adapter.getOnItemClick().onItemClick(v, position, llHomeServiceItem.getTag());
    }
}
