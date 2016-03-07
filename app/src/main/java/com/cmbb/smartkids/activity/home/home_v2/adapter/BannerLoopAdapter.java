package com.cmbb.smartkids.activity.home.home_v2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.model.BannerModel;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/2 上午9:38
 */
public class BannerLoopAdapter extends StaticPagerAdapter implements View.OnClickListener {
    private List<BannerModel.DataEntity> imgs;

    public BannerLoopAdapter( List<BannerModel.DataEntity> dataEntities) {
        super();
        if (dataEntities == null) {
            this.imgs = new ArrayList<>();
        } else {
            this.imgs = dataEntities;
        }
    }

    public void update(List<BannerModel.DataEntity> dataEntities) {
        if (dataEntities != null && dataEntities.size() >= 0) {
            imgs.clear();
            imgs.addAll(dataEntities);
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(ViewGroup container, int position) {
        SimpleDraweeView iv = (SimpleDraweeView) LayoutInflater.from(container.getContext()).inflate(R.layout.activity_home_banner_image, null);
        BannerModel.DataEntity img = imgs.get(position);
        FrescoTool.loadImage(iv, img.getImg(), String.valueOf(TDevice.dip2px(180, container.getContext())));
        iv.setTag(position);
        iv.setOnClickListener(this);
        return iv;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public int getCount() {
        return imgs.size();
    }
}