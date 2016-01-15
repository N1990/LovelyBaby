package com.cmbb.smartkids.activity.community.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.adapter.PublicCommunityAdapter;
import com.cmbb.smartkids.activity.community.model.ImageModel;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/18 17:34
 */
public class PublicItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView ivHandle, ivClear, ivZoom;
    private SimpleDraweeView sdv;
    private View root;
    private PublicCommunityAdapter adapter;
    private int position;

    public PublicItemHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        ivHandle = (ImageView) itemView.findViewById(R.id.iv_public_community_handle_item);
        ivClear = (ImageView) itemView.findViewById(R.id.iv_public_community_delete_item);
        sdv = (SimpleDraweeView) itemView.findViewById(R.id.iv_public_community_item);
        ivZoom = (ImageView) itemView.findViewById(R.id.iv_public_community_zoom_item);

    }

    public void setData(PublicCommunityAdapter adapter, int position, ImageModel model) {
        this.adapter = adapter;
        this.position = position;
        if (model != null) {
            model.setIsCheck(false);
            ivHandle.setVisibility(View.GONE);
            if (position == adapter.getCurPos())
                ivHandle.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(model.getImgUrl()))
                FrescoTool.loadLocalImage(sdv, model.getImgUrl());
            ivClear.setOnClickListener(this);
            sdv.setOnClickListener(this);
            ivZoom.setOnClickListener(this);
            sdv.setTag(model);
            ivClear.setTag(model);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        ImageModel model = (ImageModel) v.getTag();
        if (id == R.id.iv_public_community_delete_item) {
            if (adapter.getOnItemDeleteListener() != null) {
                adapter.getOnItemDeleteListener().onItemClick(v, position, model);
            }
        } else if (id == R.id.iv_public_community_item) {
            model.setIsCheck(true);
            ivHandle.setVisibility(View.VISIBLE);
            if (adapter.getOnItemListener() != null)
                adapter.getOnItemListener().onItemClick(v, position, model);
        } else if (id == R.id.iv_public_community_zoom_item) {
            if (adapter.getOnItemZoomListener() != null)
                adapter.getOnItemZoomListener().onItemClick(v, position, adapter.getData());
        }
    }
}
