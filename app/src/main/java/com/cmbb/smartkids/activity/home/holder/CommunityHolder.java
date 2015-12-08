package com.cmbb.smartkids.activity.home.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.home.adapter.CommunityAdapter;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.date.JTimeTransform;
import com.cmbb.smartkids.utils.date.RecentDateFormat;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 11:59
 */
public class CommunityHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final String TAG = CommunityHolder.class.getSimpleName();
    private SimpleDraweeView ivHeader, iv1, iv2, iv3;
    private ImageView ivTag1, ivTag2;
    private TextView tvName, tvIdentity, tvTag, tvTitle, tvContent, tvTime, tvPerssion, tvPre;

    private CommunityAdapter adapter;
    private View root;
    private int position;

    public CommunityHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        ivHeader = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_user_header_item);
        iv1 = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_content_first_item);
        iv2 = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_content_second_item);
        iv3 = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_content_third_item);
        ivTag1 = (ImageView) itemView.findViewById(R.id.iv_community_title_item_one);
        ivTag2 = (ImageView) itemView.findViewById(R.id.iv_community_title_item_two);
        tvName = (TextView) itemView.findViewById(R.id.tv_community_user_nickname_item);
        tvIdentity = (TextView) itemView.findViewById(R.id.tv_community_user_identity_item);
        tvTag = (TextView) itemView.findViewById(R.id.tv_community_tag_item);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_community_title_item);
        tvContent = (TextView) itemView.findViewById(R.id.tv_community_content_item);
        tvTime = (TextView) itemView.findViewById(R.id.tv_community_time_item);
        tvPerssion = (TextView) itemView.findViewById(R.id.tv_community_care_item);
        tvPre = (TextView) itemView.findViewById(R.id.tv_community_comment_item);
    }

    public void setData(TopicListModel.DataEntity.RowsEntity data, CommunityAdapter adapter, int position) {
        this.adapter = adapter;
        this.position = position;
        this.root.setOnClickListener(this);
        if (data == null) return;
        this.root.setTag(data);
        if (!TextUtils.isEmpty(data.getUserBasicInfo().getUserSmallImg())) {
            FrescoTool.loadImage(ivHeader, data.getUserBasicInfo().getUserSmallImg(), data.getUserBasicInfo().getUserSmallWidth() + "", data.getUserBasicInfo().getUserSmallHeight() + "");
        } else {
            ivHeader.setImageURI(null);
        }
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
        if (TextUtils.isEmpty(data.getContents())) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(data.getContents());
        }
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

        if (!TextUtils.isEmpty(data.getPublishDate())) {
            tvTime.setVisibility(View.VISIBLE);
            tvTime.setText(new JTimeTransform(data.getPublishDate()).toString(new RecentDateFormat()));
        } else {
            tvTime.setVisibility(View.GONE);
        }
        tvPerssion.setText(data.getBrowseNumber() + "");
        tvPre.setText(data.getReplys() + "");
    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, v.getTag());
    }
}
