package com.cmbb.smartkids.activity.home.holder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.adapter.HomeFraAdapter;
import com.cmbb.smartkids.activity.home.model.HomePageRootModel;
import com.cmbb.smartkids.model.ServiceStatus;
import com.cmbb.smartkids.utils.FrescoTool;
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
    private TextView tvTitle, tvTime, tvCity;
    private Context context;
    private HomeFraAdapter adapter;
    private int position;


    public HomeItemHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.root = itemView;
        iv = (SimpleDraweeView) itemView.findViewById(R.id.iv_home_fra_item);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_home_fra_title_item);
        tvTime = (TextView) itemView.findViewById(R.id.tv_home_fra_start_time);
        tvCity = (TextView) itemView.findViewById(R.id.tv_home_fra_city_item);
        ivTag = (ImageView) itemView.findViewById(R.id.iv_home_fra_tag_item);
    }

    public void setData(HomePageRootModel.DataEntity.RowsEntity row, int position, HomeFraAdapter adapter) {
        this.adapter = adapter;
        this.position = position;
        tvTitle.setText(row.getTitle());
        if (TextUtils.isEmpty(row.getCityText()))
            tvCity.setVisibility(View.GONE);
        tvCity.setText(row.getCityText());
        String startTime = row.getSurplusTime();
//        List<Integer> intTemp = new ArrayList<>();
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
