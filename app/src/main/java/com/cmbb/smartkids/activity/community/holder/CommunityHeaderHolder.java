package com.cmbb.smartkids.activity.community.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.adapter.CommunityDetailAdapter;
import com.cmbb.smartkids.activity.community.model.CommunityDetailModel;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/5 10:43
 */
public class CommunityHeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private CommunityDetailAdapter adapter;
    private int position;
    private View root;
    private SimpleDraweeView ivHeader;
    private TextView tvName, tvUserIdentify, tvCommunityTopic, tvTile, tvContent;
    private ImageView ivDing, ivJing;


    public CommunityHeaderHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        ivHeader = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_header_first_item);
        tvName = (TextView) itemView.findViewById(R.id.tv_community_nickname_first_item);
        tvUserIdentify = (TextView) itemView.findViewById(R.id.tv_community_sort_first_item);
        tvCommunityTopic = (TextView) itemView.findViewById(R.id.tv_community_tag_first_item);
        tvTile = (TextView) itemView.findViewById(R.id.tv_community_title_first_item);
        tvContent = (TextView) itemView.findViewById(R.id.tv_community_content_first_item);
    }

    public void setData(CommunityDetailModel data, CommunityDetailAdapter adapter, int position) {
        this.adapter = adapter;

        // 设置数据
        if (!TextUtils.isEmpty(data.getData().getUserBasicInfo().getUserSmallImg())) {
            FrescoTool.loadImage(ivHeader, data.getData().getUserBasicInfo().getUserSmallImg(), data.getData().getUserBasicInfo().getUserSmallWidth() + "", data.getData().getUserBasicInfo().getUserSmallHeight() + "");
        } else {
            ivHeader.setImageURI(null);
        }
        tvName.setText(data.getData().getUserBasicInfo().getUserNike());
        if (TextUtils.isEmpty(data.getData().getContents())) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(data.getData().getContents());
        }
        tvTile.setText(data.getData().getTitle());
        tvUserIdentify.setText(data.getData().getUserBasicInfo().getUserRole().get(0).getEredarName());
        tvCommunityTopic.setText(data.getData().getTopicType());


        ivHeader.setTag(data.getData().getUserBasicInfo().getUserId());
        ivHeader.setOnClickListener(this);
        tvName.setOnClickListener(this);
        root.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnEveryListener() != null) {
            adapter.getOnEveryListener().onItemClick(v, -1, -1);
        }

        switch (v.getId()) {
            case R.id.tv_community_nickname_first_item:
            case R.id.iv_community_header_first_item:
                if (adapter.getOnHeaderListener() != null)
                    adapter.getOnHeaderListener().onClick(v);
                break;
        }

    }
}
