package com.cmbb.smartkids.activity.community.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.adapter.ReverCommentAdapter;
import com.cmbb.smartkids.activity.community.model.ReplayDetailModel;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.date.JTimeTransform;
import com.cmbb.smartkids.utils.date.RecentDateFormat;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/6 10:10
 */
public class ReverHeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ReverCommentAdapter adapter;

    private View root;

    private SimpleDraweeView header;
    private SimpleDraweeView ivContent;
    private ImageView ivComment, ivMore;
    private TextView tvName, tvTime, tvIdentify, tvContent;


    public ReverHeaderHolder(View itemView) {
        super(itemView);
        root = itemView;
        header = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_comment_item);
        ivContent = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_comment_content_item);
        ivComment = (ImageView) itemView.findViewById(R.id.iv_community_comment_perssion_item);
        ivMore = (ImageView) itemView.findViewById(R.id.iv_community_comment_more_item);
        tvName = (TextView) itemView.findViewById(R.id.tv_community_comment_nickname_item);
        tvTime = (TextView) itemView.findViewById(R.id.tv_community_comment_time_item);
        tvIdentify = (TextView) itemView.findViewById(R.id.tv_community_comment_tag_item);
        tvContent = (TextView) itemView.findViewById(R.id.tv_community_comment_content_item);
    }


    public void setData(ReplayDetailModel data, ReverCommentAdapter adapter) {
        this.adapter = adapter;
        root.setOnClickListener(this);
        // 设置数据
        FrescoTool.loadImage(header, data.getData().getUserBasicInfo().getUserSmallImg(), data.getData().getUserBasicInfo().getUserSmallWidth() + "", data.getData().getUserBasicInfo().getUserSmallHeight() + "");
        if (TextUtils.isEmpty(data.getData().getImg())) {
            ivContent.setVisibility(View.GONE);
        } else {
            ivContent.setVisibility(View.VISIBLE);
            FrescoTool.loadImage(ivContent, data.getData().getImg(), TDevice.dip2px(200, ivComment.getContext()) + "");
        }
        tvName.setText(data.getData().getUserBasicInfo().getUserNike());
        tvContent.setText(data.getData().getContents());
        tvIdentify.setText(data.getData().getUserBasicInfo().getUserRole().get(0).getEredarName());
        tvTime.setText("第" + data.getData().getReplysFloor() + "楼  " + new JTimeTransform(data.getData().getCreateDate()).toString(new RecentDateFormat()));

        header.setTag(data.getData().getUserBasicInfo().getUserId());
        header.setOnClickListener(this);
        tvName.setTag(data.getData().getUserBasicInfo().getUserId());
        tvName.setOnClickListener(this);
        ivContent.setTag(data.getData().getImg());
        ivContent.setOnClickListener(this);
        ivComment.setVisibility(View.GONE);
        ivComment.setOnClickListener(this);
        ivMore.setTag(data);
        ivMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnEveryListener() != null) {
            adapter.getOnEveryListener().onClick(v);
        }

        switch (v.getId()) {
            case R.id.iv_community_comment_item:
            case R.id.tv_community_comment_nickname_item:
                if (adapter.getOnHeaderListener() != null)
                    adapter.getOnHeaderListener().onClick(v);
                break;
            case R.id.iv_community_comment_content_item:
                if (adapter.getOnPreListener() != null)
                    adapter.getOnPreListener().onClick(v);
                break;
            case R.id.iv_community_comment_perssion_item:
                if (adapter.getOnReverListener() != null)
                    adapter.getOnReverListener().onClick(v);
                break;
            case R.id.iv_community_comment_more_item:
                if (adapter.getOnMoreListener() != null)
                    adapter.getOnMoreListener().onClick(v);
                break;
        }

    }
}
