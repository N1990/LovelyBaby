package com.cmbb.smartkids.activity.home.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.adapter.HomeFraAdapter;
import com.cmbb.smartkids.activity.home.model.BannerModel;
import com.cmbb.smartkids.adapter.AutoScrollAdapter;
import com.cmbb.smartkids.photopicker.widget.indication.CirclePageIndicator;
import com.cmbb.smartkids.widget.autoscroll.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 14:25
 */
public class HomeAdHolder extends RecyclerView.ViewHolder {
    private AutoScrollViewPager asvp;
    private CirclePageIndicator indicator;
    private AutoScrollAdapter adapter;
    private List<BannerModel.DataEntity> imgs;
    private Context context;

    public HomeAdHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        asvp = (AutoScrollViewPager) itemView.findViewById(R.id.asvp_home_fra_ad);
        indicator = (CirclePageIndicator) itemView.findViewById(R.id.cpi_home_fra_ad);
    }

    public void setData(List<BannerModel.DataEntity> imgs, HomeFraAdapter hAdapter) {
        if (imgs != null) {
            this.imgs = imgs;
        } else {
            this.imgs = new ArrayList<>();
        }
        adapter = new AutoScrollAdapter(context, imgs, hAdapter);
        asvp.setAdapter(adapter);
        asvp.setOffscreenPageLimit(6);
        asvp.setScrollFactgor(5);
        asvp.startAutoScroll(3000);
        indicator.setViewPager(asvp);
        indicator.setSnap(true);
    }
}
