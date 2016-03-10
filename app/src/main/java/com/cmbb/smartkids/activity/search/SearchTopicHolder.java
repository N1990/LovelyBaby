package com.cmbb.smartkids.activity.search;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.Tools;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 17:15
 */
public class SearchTopicHolder extends BaseViewHolder<TopicListModel.DataEntity.RowsEntity> {
    private final String TAG = SearchTopicHolder.class.getSimpleName();
    private SimpleDraweeView ivHeader, iv1, iv2, iv3;
    private ImageView ivTag1, ivTag2;
    private TextView tvName, tvIdentity, tvTag, tvTitle, tvContent, tvTime, tvPerssion, tvPre;


    public SearchTopicHolder(ViewGroup parent) {
        super(parent, R.layout.list_community_item);
        ivHeader = $(R.id.iv_community_user_header_item);
        iv1 = $(R.id.iv_community_content_first_item);
        iv2 = $(R.id.iv_community_content_second_item);
        iv3 = $(R.id.iv_community_content_third_item);
        ivTag1 = $(R.id.iv_community_title_item_one);
        ivTag2 = $(R.id.iv_community_title_item_two);
        tvName = $(R.id.tv_community_user_nickname_item);
        tvIdentity = $(R.id.tv_community_user_identity_item);
        tvTag = $(R.id.tv_community_tag_item);
        tvTitle = $(R.id.tv_community_title_item);
        tvContent = $(R.id.tv_community_content_item);
        tvTime = $(R.id.tv_community_time_item);
        tvPerssion = $(R.id.tv_community_care_item);
        tvPre = $(R.id.tv_community_comment_item);
    }


    @Override
    public void setData(TopicListModel.DataEntity.RowsEntity data) {
        super.setData(data);
        FrescoTool.loadImage(ivHeader, data.getUserBasicInfo().getUserSmallImg(), data.getUserBasicInfo().getUserSmallWidth() + "", data.getUserBasicInfo().getUserSmallHeight() + "");
        if (!TextUtils.isEmpty(data.getUserBasicInfo().getUserNike())) {
            tvName.setText(data.getUserBasicInfo().getUserNike());
        } else {
            tvName.setText(data.getUserBasicInfo().getUserPhone());
        }
        tvIdentity.setText(data.getUserBasicInfo().getUserRole().get(0).getEredarName());
        tvTag.setText(data.getTopicType());
        if (data.getIsStick() == 1) {
            ivTag1.setVisibility(View.VISIBLE);
        } else {
            ivTag1.setVisibility(View.GONE);
        }
        if (data.getIsEnssence() == 1) {
            ivTag2.setVisibility(View.VISIBLE);
        } else {
            ivTag2.setVisibility(View.GONE);
        }
        tvTitle.setText(data.getTitle());
        tvContent.setText(data.getContents());
        if (data.getTopicImgList() != null && data.getTopicImgList().size() >= 3) {
            iv1.setVisibility(View.VISIBLE);
            iv2.setVisibility(View.VISIBLE);
            iv3.setVisibility(View.VISIBLE);
            FrescoTool.loadImage(iv1, data.getTopicImgList().get(0).getImg(), TDevice.dip2px(120, iv1.getContext()) + "");
            FrescoTool.loadImage(iv2, data.getTopicImgList().get(1).getImg(), TDevice.dip2px(120, iv2.getContext()) + "");
            FrescoTool.loadImage(iv3, data.getTopicImgList().get(2).getImg(), TDevice.dip2px(120, iv3.getContext()) + "");
        } else if (data.getTopicImgList() != null && data.getTopicImgList().size() == 2) {
            iv1.setVisibility(View.VISIBLE);
            iv2.setVisibility(View.VISIBLE);
            iv3.setVisibility(View.INVISIBLE);
            FrescoTool.loadImage(iv1, data.getTopicImgList().get(0).getImg(), TDevice.dip2px(120, iv1.getContext()) + "");
            FrescoTool.loadImage(iv2, data.getTopicImgList().get(1).getImg(), TDevice.dip2px(120, iv2.getContext()) + "");
        } else if (data.getTopicImgList() != null && data.getTopicImgList().size() == 1) {
            iv1.setVisibility(View.VISIBLE);
            iv2.setVisibility(View.INVISIBLE);
            iv3.setVisibility(View.INVISIBLE);
            FrescoTool.loadImage(iv1, data.getTopicImgList().get(0).getImg(), TDevice.dip2px(120, iv1.getContext()) + "");
        } else if (data.getTopicImgList() != null && data.getTopicImgList().size() == 0) {
            iv1.setVisibility(View.GONE);
            iv2.setVisibility(View.GONE);
            iv3.setVisibility(View.GONE);
        }

        try {
            if (!TextUtils.isEmpty(data.getPublishDate())) {
                tvTime.setVisibility(View.VISIBLE);
                tvTime.setText(Tools.DataToString(data.getPublishDate(), "yyyy-MM-dd HH:mm"));
            } else {
                tvTime.setVisibility(View.GONE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvPerssion.setText(data.getReplys() + "");
        tvPre.setText(data.getBrowseNumber() + "");

    }
}
