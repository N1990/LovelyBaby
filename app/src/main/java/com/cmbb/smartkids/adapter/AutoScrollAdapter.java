package com.cmbb.smartkids.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.adapter.HomeFraAdapter;
import com.cmbb.smartkids.activity.home.model.BannerModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/25 15:32
 */
public class AutoScrollAdapter extends PagerAdapter implements View.OnClickListener {
    private List<BannerModel.DataEntity> imgs;
    private Context context;
    private HomeFraAdapter adapter;

    public AutoScrollAdapter(Context context, List<BannerModel.DataEntity> imgs) {
        this.context = context;
        if (imgs != null) {
            this.imgs = imgs;
        } else {
            this.imgs = new ArrayList<>();
        }
    }

    public AutoScrollAdapter(Context context, List<BannerModel.DataEntity> imgs, HomeFraAdapter adapter) {
        this.context = context;
        if (imgs != null) {
            this.imgs = imgs;
        } else {
            this.imgs = new ArrayList<>();
        }
        this.adapter = adapter;
    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SimpleDraweeView iv = (SimpleDraweeView) LayoutInflater.from(container.getContext()).inflate(R.layout.activity_home_banner_image, null);
        BannerModel.DataEntity img = imgs.get(position);
        iv.setImageURI(Uri.parse(img.getImg()));
//        FrescoTool.loadImage(iv, img.getImg(), String.valueOf(img.getImgWidth()), String.valueOf(img.getImgHeight()));
        iv.setTag(position);
        iv.setOnClickListener(this);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (adapter != null) {
            if (adapter.getOnAdItemClick() != null)
                adapter.getOnAdItemClick().onItemClick(v, position, imgs.get(position));
        }
    }
}
