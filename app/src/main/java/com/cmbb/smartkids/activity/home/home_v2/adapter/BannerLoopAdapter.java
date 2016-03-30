package com.cmbb.smartkids.activity.home.home_v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.CommunityDetailActivity;
import com.cmbb.smartkids.activity.home.home_v2.ADActivity;
import com.cmbb.smartkids.activity.home.model.ManagerAdModel;
import com.cmbb.smartkids.activity.serve.v2.ServerDetailActivityV2;
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
    private List<ManagerAdModel.DataEntity> imgs;
    private Context context;

    public BannerLoopAdapter(Context context, List<ManagerAdModel.DataEntity> dataEntities) {
        super();
        this.context = context;
        if (dataEntities == null) {
            this.imgs = new ArrayList<>();
        } else {
            this.imgs = dataEntities;
        }
    }

    public void update(List<ManagerAdModel.DataEntity> dataEntities) {
        if (dataEntities != null && dataEntities.size() >= 0) {
            imgs.clear();
            imgs.addAll(dataEntities);
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(ViewGroup container, int position) {
        SimpleDraweeView iv = (SimpleDraweeView) LayoutInflater.from(container.getContext()).inflate(R.layout.activity_home_banner_image, null);
        ManagerAdModel.DataEntity img = imgs.get(position);
        FrescoTool.loadImage(iv, img.getAdImg(), String.valueOf(TDevice.dip2px(180, container.getContext())));
        iv.setTag(imgs.get(position));
        iv.setOnClickListener(this);
        return iv;
    }


    @Override
    public void onClick(View v) {
        ManagerAdModel.DataEntity entity = (ManagerAdModel.DataEntity) v.getTag();
        switch (entity.getRedirectType()) {
            case "INNER":
                switch (entity.getInnerRedirectType()) {
                    case "APP_TOPIC":
                        CommunityDetailActivity.newInstance(this.context, entity.getRelateId());

                        break;
                    case "APP_SERVICE":
                        ServerDetailActivityV2.newIntent(this.context, entity.getRelateId());
                        break;
                }
                break;
            case "OUTTER":
                ADActivity.newIntent(this.context, entity.getRedirectUrl());
                break;
        }
    }

    @Override
    public int getCount() {
        return imgs.size();
    }
}
