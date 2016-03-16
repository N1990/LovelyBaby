package com.cmbb.smartkids.activity.home.home_v2.holder;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.model.HomePageRootModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 14:25
 */
public class HomeItemHolder extends BaseViewHolder<HomePageRootModel.DataEntity.RowsEntity> {
    private final String TAG = HomeItemHolder.class.getSimpleName();
    private SimpleDraweeView iv;
    private ImageView ivTag;
    private TextView tvTitle, tvTime, tvCity, tvTag, tvPrice;


    public HomeItemHolder(ViewGroup parent) {
        super(parent, R.layout.list_home_fragment_item);
        iv = $(R.id.iv_home_fra_item);
        tvTitle = $(R.id.tv_home_fra_title_item);
        tvTime = $(R.id.tv_home_fra_start_time);
        tvCity = $(R.id.tv_home_fra_city_item);
        ivTag = $(R.id.iv_home_fra_tag_item);
        tvTag = $(R.id.tv_tag);
        tvPrice = $(R.id.tv_home_price);
    }

    public void setData(HomePageRootModel.DataEntity.RowsEntity row) {
        tvTitle.setText(row.getTitle());
        tvTag.setText(row.getTypeText());
        tvPrice.setText("￥ " + row.getPrice());
        if (TextUtils.isEmpty(row.getCityText()))
            tvCity.setVisibility(View.GONE);
        tvCity.setText(row.getCityText());
        String startTime = row.getSurplusTime();
        if (TextUtils.isEmpty(startTime)) {
            tvTime.setVisibility(View.GONE);
        } else {
            SpannableString ss = new SpannableString(startTime);
            tvTime.setVisibility(View.VISIBLE);
            for (int i = 0; i < ss.length(); i++) {
                char temp = startTime.charAt(i);
                if (temp >= 48 && temp <= 57) { //判断是否是数字
                    ss.setSpan(new ForegroundColorSpan(Color.RED), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else {
                    ss.setSpan(new ForegroundColorSpan(Color.WHITE), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                tvTime.setText(ss);
            }
        }
        FrescoTool.loadImage(iv, row.getServicesImg(), 1.67f);
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
    }
}
