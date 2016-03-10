package com.cmbb.smartkids.activity.home.home_v2.holder.servicedict;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/10 下午12:51
 */
public class ServiceHeadHolder extends RecyclerView.ViewHolder {
    TextView tvTitle;

    public ServiceHeadHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView)itemView.findViewById(R.id.tv_service_dict);

    }
    public void setTitle(String title) {
        tvTitle.setText(title);
    }
}
