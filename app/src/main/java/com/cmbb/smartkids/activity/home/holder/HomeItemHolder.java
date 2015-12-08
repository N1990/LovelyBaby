package com.cmbb.smartkids.activity.home.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.adapter.HomeFraAdapter;
import com.cmbb.smartkids.activity.home.model.HomePageRootModel;
import com.cmbb.smartkids.model.ServiceStatus;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 14:25
 */
public class HomeItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final String TAG = HomeItemHolder.class.getSimpleName();
    private View root;
    private SimpleDraweeView iv;
    private ImageView ivTag;
    private TextView tvTitle, tvPrice, tvCity, tvJoin;
    private Context context;
    private HomeFraAdapter adapter;
    private int position;


    public HomeItemHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.root = itemView;
        iv = (SimpleDraweeView) itemView.findViewById(R.id.iv_home_fra_item);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_home_fra_title_item);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_home_fra_price_item);
        tvCity = (TextView) itemView.findViewById(R.id.tv_home_fra_city_item);
        tvJoin = (TextView) itemView.findViewById(R.id.tv_join_home_service_item);
        ivTag = (ImageView) itemView.findViewById(R.id.iv_home_fra_tag_item);
    }

    public void setData(HomePageRootModel.DataEntity.RowsEntity row, int position, HomeFraAdapter adapter) {
        this.adapter = adapter;
        this.position = position;
        tvTitle.setText(row.getTitle());
        if (TextUtils.isEmpty(row.getCityText()))
            tvCity.setVisibility(View.GONE);
        tvCity.setText(row.getCityText());
        double price = Double.valueOf(row.getPrice());
        tvPrice.setText(price != 0 ? row.getPrice() + "元" : "免费");
        tvJoin.setText("参加人数：" + row.getRealityPeoples() + "/" + row.getPeoples());
        FrescoTool.loadImage(iv, row.getServicesImg(), String.valueOf(TDevice.dip2px(200, context)));
        ivTag.setVisibility(View.VISIBLE);
        int statusValue = row.getStatus();
        ServiceStatus status = ServiceStatus.getStatusByValue(statusValue);
        switch (status) {
            case WEI_KAI_SHI:
                ivTag.setVisibility(View.GONE);
                break;
            case YI_KAI_SHI:
                ivTag.setImageResource(R.mipmap.ic_order_start_bg);
                break;
            case YU_DING_ZHONG:
                ivTag.setImageResource(R.mipmap.ic_order_reserving_bg);
                break;
            case YI_JIE_SHU:
                ivTag.setImageResource(R.mipmap.ic_order_overtime_bg);
                break;
        }
        root.setTag(row);
        root.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        HomePageRootModel.DataEntity.RowsEntity active = (HomePageRootModel.DataEntity.RowsEntity) v.getTag();
        if (adapter.getOnItemClick() != null)
            adapter.getOnItemClick().onItemClick(v, position, active);
    }
}
