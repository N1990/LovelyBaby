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
    private TextView tvTitle, tvPrice, tvJoin, tvCity;
    private ImageView iv;
    private int position;


    public MyServiceHolder(View itemView) {
        super(itemView);
        this.item = itemView;
        iv = (ImageView) itemView.findViewById(R.id.iv_home_service_tag_item);
        ivBg = (SimpleDraweeView) itemView.findViewById(R.id.iv_home_service_item);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title_home_service_item);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_price_home_service_item);
        tvJoin = (TextView) itemView.findViewById(R.id.tv_join_home_service_item);
        tvCity = (TextView) itemView.findViewById(R.id.tv_city_home_service_item);
    }

    public void setData(MyServiceAdapter adapter, int position, ServiceListModel.DataEntity.RowsEntity itemData) {
        this.adapter = adapter;
        this.position = position;
        FrescoTool.loadImage(ivBg, itemData.getServicesImg(), String.valueOf(TDevice.dip2px(200, ivBg.getContext())));
        tvTitle.setText(itemData.getTitle());
        double price = Double.valueOf(itemData.getPrice());
        tvPrice.setText(price != 0 ? price + "元" : "免费");
        tvJoin.setText("参加人数：" + itemData.getRealityPeoples() + "/" + itemData.getPeoples());
        if (TextUtils.isEmpty(itemData.getCityText()))
            tvCity.setVisibility(View.GONE);
        tvCity.setText(String.valueOf(itemData.getCityText()));
        iv.setVisibility(View.VISIBLE);
        int statusValue = itemData.getStatus();
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
        itemView.setTag(itemData);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnItemClick() != null)
            adapter.getOnItemClick().onItemClick(v, position, v.getTag());
    }
}
