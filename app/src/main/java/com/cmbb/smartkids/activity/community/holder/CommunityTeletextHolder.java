package com.cmbb.smartkids.activity.community.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.adapter.CommunityDetailAdapter;
import com.cmbb.smartkids.activity.community.model.CommunityDetailModel;
import com.cmbb.smartkids.activity.community.model.ImageTagModel;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;


/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/5 10:43
 */
public class CommunityTeletextHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private CommunityDetailAdapter adapter;
    private int position;
    private View root;
    private SimpleDraweeView iv;
    private TextView tv;

    public CommunityTeletextHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        this.iv = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_second_item);
        this.tv = (TextView) itemView.findViewById(R.id.tv_community_second_item);
    }

    public void setData(CommunityDetailModel data, CommunityDetailAdapter adapter, int position) {
        this.adapter = adapter;
        this.position = position;
        ImageTagModel imageTagModel = new ImageTagModel();
        ArrayList<String> images = new ArrayList<>();
        for (CommunityDetailModel.DataEntity.TopicImgListEntity entity : data.getData().getTopicImgList()) {
            images.add(entity.getImg());
        }
        imageTagModel.setImages(images);
        imageTagModel.setPosition(position);
        this.iv.setTag(imageTagModel);
        this.iv.setOnClickListener(this);

        FrescoTool.loadImage(iv, data.getData().getTopicImgList().get(position).getImg(), data.getData().getTopicImgList().get(position).getImgWidth() + "", data.getData().getTopicImgList().get(position).getImgHeight() + "");
        if (TextUtils.isEmpty(data.getData().getTopicImgList().get(position).getContents())) {
            tv.setVisibility(View.GONE);
        } else {
            tv.setVisibility(View.VISIBLE);
            tv.setText(data.getData().getTopicImgList().get(position).getContents());
        }
        root.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnEveryListener() != null) {
            adapter.getOnEveryListener().onItemClick(v, -1, -1);
        }
        switch (v.getId()) {
            case R.id.iv_community_second_item:
                if (adapter.getOnTeletextListener() != null)
                    adapter.getOnTeletextListener().onItemClick(v, position, position);
                break;
        }

    }
}
