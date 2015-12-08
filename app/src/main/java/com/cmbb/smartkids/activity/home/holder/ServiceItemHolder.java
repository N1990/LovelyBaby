package com.cmbb.smartkids.activity.home.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.adapter.ServiceFraAdapter;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.model.ServiceStatus;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 17:15
 */
public class ServiceItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final String TAG = ServiceItemHolder.class.getSimpleName();
    private ServiceFraAdapter adapter;
    private LinearLayout llHomeServiceItem;
    private SimpleDraweeView ivHomeServiceItem;
    private TextView tvTitleHomeServiceItem;
    private TextView tvPriceHomeServiceItem;
    private TextView tvJoinHomeServiceItem;
    private TextView tvCityHomeServiceItem;
    private ImageView iv;
    private int position;

    public ServiceItemHolder(View itemView) {
        super(itemView);
        llHomeServiceItem = (LinearLayout) itemView.findViewById(R.id.ll_home_service_item);
        ivHomeServiceItem = (SimpleDraweeView) itemView.findViewById(R.id.iv_home_service_item);
        tvTitleHomeServiceItem = (TextView) itemView.findViewById(R.id.tv_title_home_service_item);
        tvPriceHomeServiceItem = (TextView) itemView.findViewById(R.id.tv_price_home_service_item);
        tvJoinHomeServiceItem = (TextView) itemView.findViewById(R.id.tv_join_home_service_item);
        tvCityHomeServiceItem = (TextView) itemView.findViewById(R.id.tv_city_home_service_item);
        iv = (ImageView) itemView.findViewById(R.id.iv_home_service_tag_item);

    }

    public void setData(ServiceListModel.DataEntity.RowsEntity row, int position, ServiceFraAdapter adapter) {
        Log.e(TAG, "setData position : " + position);
        this.adapter = adapter;
        this.position = position;
        llHomeServiceItem.setTag(row);
        llHomeServiceItem.setOnClickListener(this);
        FrescoTool.loadImage(ivHomeServiceItem, row.getServicesImg(), String.valueOf(TDevice.dip2px(200, ivHomeServiceItem.getContext())));
        tvTitleHomeServiceItem.setText(row.getTitle());
        if (!TextUtils.isEmpty(row.getPrice())) {
            double price = Double.valueOf(row.getPrice());
            tvPriceHomeServiceItem.setText(price != 0 ? row.getPrice() + "元" : "免费");
        }
        tvJoinHomeServiceItem.setText("参加人数：" + row.getRealityPeoples() + "/" + row.getPeoples());
        iv.setVisibility(View.VISIBLE);
        int statusValue = row.getStatus();
        ServiceStatus status = ServiceStatus.getStatusByValue(statusValue);
        switch (status) {
            case WEI_KAI_SHI:
                iv.setVisibility(View.GONE);
                break;
            case YI_KAI_SHI:
                iv.setImageResource(R.mipmap.ic_order_start_bg);
                break;
            case YU_DING_ZHONG:
                iv.setImageResource(R.mipmap.ic_order_reserving_bg);
                break;
//            case YI_YU_DING:
//                iv.setImageResource(R.mipmap.ic_order_reserved_bg);
//                break;
            case YI_JIE_SHU:
                iv.setImageResource(R.mipmap.ic_order_overtime_bg);
                break;
        }
        tvCityHomeServiceItem.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(row.getCityText()))
            tvCityHomeServiceItem.setVisibility(View.GONE);
        tvCityHomeServiceItem.setText(row.getCityText());
    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnItemClick() != null)
            adapter.getOnItemClick().onItemClick(v, position, llHomeServiceItem.getTag());
    }
}
