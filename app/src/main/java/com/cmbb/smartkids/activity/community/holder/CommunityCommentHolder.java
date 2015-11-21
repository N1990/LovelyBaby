package com.cmbb.smartkids.activity.community.holder;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.adapter.CommentAdapter;
import com.cmbb.smartkids.activity.community.adapter.CommunityDetailAdapter;
import com.cmbb.smartkids.activity.community.model.CommunityReplayModel;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.JTimeTransform;
import com.cmbb.smartkids.utils.RecentDateFormat;
import com.cmbb.smartkids.utils.TDevice;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/5 10:43
 */
public class CommunityCommentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private CommunityDetailAdapter adapter;
    private CommentAdapter adapter1;
    private int position;
    private View root, vDivider;
    private SimpleDraweeView header, ivContent;
    private ImageView ivComment, ivMore, ivReverPreOne, ivReverPreTwo, ivReverDelOne, ivReverDelTwo;
    private TextView tvName, tvTime, tvIdentify, tvContent, tvMore, tvReverTimeOne, tvReverTimeTwo, tvReverNameOne, tvReverNameTwo;
    private LinearLayout llReverContainer, llOne, llTow;
    private View line;


    public CommunityCommentHolder(View itemView) {
        super(itemView);
        root = itemView;
        line = itemView.findViewById(R.id.v_community_comment_item);
        header = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_comment_item);
        ivContent = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_comment_content_item);
        ivComment = (ImageView) itemView.findViewById(R.id.iv_community_comment_perssion_item);
        ivMore = (ImageView) itemView.findViewById(R.id.iv_community_comment_more_item);
        tvName = (TextView) itemView.findViewById(R.id.tv_community_comment_nickname_item);
        tvTime = (TextView) itemView.findViewById(R.id.tv_community_comment_time_item);
        tvIdentify = (TextView) itemView.findViewById(R.id.tv_community_comment_tag_item);
        tvContent = (TextView) itemView.findViewById(R.id.tv_community_comment_content_item);
        tvMore = (TextView) itemView.findViewById(R.id.tv_community_comment_more_item);
        llOne = (LinearLayout) itemView.findViewById(R.id.ll_community_comment_item_one);
        llTow = (LinearLayout) itemView.findViewById(R.id.ll_community_comment_item_two);
        llReverContainer = (LinearLayout) itemView.findViewById(R.id.ll_community_comment_container);
        ivReverDelOne = (ImageView) itemView.findViewById(R.id.iv_community_comment_delete_item_one);
        ivReverDelTwo = (ImageView) itemView.findViewById(R.id.iv_community_comment_delete_item_two);
        tvReverNameOne = (TextView) itemView.findViewById(R.id.tv_community_comment_rever_name_item_one);
        tvReverNameTwo = (TextView) itemView.findViewById(R.id.tv_community_comment_rever_name_item_two);
        vDivider = itemView.findViewById(R.id.v_community_comment_divider_item);
        root.setOnClickListener(this);
    }


    /**
     * 详情加评论列表
     *
     * @param data
     * @param adapter
     * @param position
     */
    public void setData(CommunityReplayModel data, CommunityDetailAdapter adapter, int position) {
        this.adapter = adapter;
        this.position = position;

        FrescoTool.loadImage(header, data.getData().getRows().get(position).getUserBasicInfo().getUserSmallImg(), data.getData().getRows().get(position).getUserBasicInfo().getUserSmallWidth() + "", data.getData().getRows().get(position).getUserBasicInfo().getUserSmallHeight() + "");
        tvName.setTag(data.getData().getRows().get(position).getUserBasicInfo().getUserId());
        tvName.setText(data.getData().getRows().get(position).getUserBasicInfo().getUserNike());
        tvIdentify.setText(data.getData().getRows().get(position).getUserBasicInfo().getUserRole().get(0).getEredarName());
        tvContent.setText(data.getData().getRows().get(position).getContents());
        tvTime.setText("第" + data.getData().getRows().get(position).getReplysFloor() + "楼  " + new JTimeTransform(data.getData().getRows().get(position).getCreateDate()).toString(new RecentDateFormat()));
        if (TextUtils.isEmpty(data.getData().getRows().get(position).getImg())) {
            ivContent.setVisibility(View.GONE);

        } else {
            ivContent.setVisibility(View.VISIBLE);
            FrescoTool.loadImage(ivContent, data.getData().getRows().get(position).getImg(), TDevice.dip2px(200, BaseApplication.getContext()) + "");
        }
        // 回复的回复
        switch (data.getData().getRows().get(position).getChildReply().size()) {
            case 0:
                llReverContainer.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                break;
            case 1:
                line.setVisibility(View.VISIBLE);
                llReverContainer.setVisibility(View.VISIBLE);
                llOne.setVisibility(View.VISIBLE);
                llTow.setVisibility(View.GONE);
                String name1 = data.getData().getRows().get(position).getChildReply().get(0).getUserBasicInfo().getUserNike();
                String title1 = data.getData().getRows().get(position).getChildReply().get(0).getContents();
                String time1 = data.getData().getRows().get(position).getChildReply().get(0).getCreateDate();
                String image1 = data.getData().getRows().get(position).getChildReply().get(0).getImg();
                setReplayType(name1, title1, time1, image1, tvReverNameOne);
                tvReverNameOne.setTag(data.getData().getRows().get(position).getChildReply().get(0));
                switch (data.getData().getRows().get(position).getChildReply().get(0).getUserBasicInfo().getIsLoginUser()) {
                    case 0:
                        ivReverDelOne.setVisibility(View.GONE);
                        break;
                    case 1:
                        ivReverDelOne.setTag(data.getData().getRows().get(position).getChildReply().get(0).getId());

                        ivReverDelOne.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case 2:
                line.setVisibility(View.VISIBLE);
                llReverContainer.setVisibility(View.VISIBLE);
                llOne.setVisibility(View.VISIBLE);
                llTow.setVisibility(View.VISIBLE);
                String name3 = data.getData().getRows().get(position).getChildReply().get(0).getUserBasicInfo().getUserNike();
                String title3 = data.getData().getRows().get(position).getChildReply().get(0).getContents();
                String time3 = data.getData().getRows().get(position).getChildReply().get(0).getCreateDate();
                String image3 = data.getData().getRows().get(position).getChildReply().get(0).getImg();
                setReplayType(name3, title3, time3, image3, tvReverNameOne);
                tvReverNameOne.setTag(data.getData().getRows().get(position).getChildReply().get(0));
                switch (data.getData().getRows().get(position).getChildReply().get(0).getUserBasicInfo().getIsLoginUser()) {
                    case 0:
                        ivReverDelOne.setVisibility(View.GONE);
                        break;
                    case 1:
                        ivReverDelOne.setTag(data.getData().getRows().get(position).getChildReply().get(0).getId());
                        ivReverDelOne.setVisibility(View.VISIBLE);
                        break;
                }
                String name2 = data.getData().getRows().get(position).getChildReply().get(1).getUserBasicInfo().getUserNike();
                String title2 = data.getData().getRows().get(position).getChildReply().get(1).getContents() + "  ";
                String time2 = data.getData().getRows().get(position).getChildReply().get(1).getCreateDate();
                String image2 = data.getData().getRows().get(position).getChildReply().get(1).getImg();
                setReplayType(name2, title2, time2, image2, tvReverNameTwo);
                tvReverNameTwo.setTag(data.getData().getRows().get(position).getChildReply().get(1));
                switch (data.getData().getRows().get(position).getChildReply().get(1).getUserBasicInfo().getIsLoginUser()) {
                    case 0:
                        ivReverDelTwo.setVisibility(View.GONE);
                        break;
                    case 1:
                        ivReverDelTwo.setTag(data.getData().getRows().get(position).getChildReply().get(1).getId());

                        ivReverDelTwo.setVisibility(View.VISIBLE);
                        break;
                }
                break;
        }

        //设置监听事件
        header.setTag(data.getData().getRows().get(position).getUserBasicInfo().getUserId());
        header.setOnClickListener(this);
        tvName.setOnClickListener(this);
        ivContent.setTag(data.getData().getRows().get(position).getImg());
        ivContent.setOnClickListener(this);
        ivComment.setTag(data.getData().getRows().get(position).getId());
        ivComment.setOnClickListener(this);

        ArrayList<Integer> more = new ArrayList<>();
        more.add(data.getData().getRows().get(position).getId());
        more.add(data.getData().getRows().get(position).getUserBasicInfo().getIsLoginUser());
        ivMore.setTag(more);
        ivMore.setOnClickListener(this);
        if (data.getData().getRows().get(position).getChildCount() == 0) {
            tvMore.setVisibility(View.GONE);
        } else {
            tvMore.setVisibility(View.VISIBLE);
            tvMore.setText("更多" + data.getData().getRows().get(position).getChildCount() + "条多回复..");
            if (data.getData().getRows().get(position).getId() != -1) {
                tvMore.setTag(data.getData().getRows().get(position).getId());
                tvMore.setOnClickListener(this);
            }
        }
        llOne.setOnClickListener(this);
        llTow.setOnClickListener(this);
        switch (data.getData().getRows().get(position).getChildReply().size()) {
            case 0:

        }
        ivReverDelOne.setOnClickListener(this);
        ivReverDelTwo.setOnClickListener(this);
    }


    /**
     * 分页之后纯评论列表
     *
     * @param data
     * @param adapter
     * @param position
     */
    public void setData(CommunityReplayModel data, CommentAdapter adapter, int position) {
        this.adapter1 = adapter;
        this.position = position;
        FrescoTool.loadImage(header, data.getData().getRows().get(position).getUserBasicInfo().getUserSmallImg(), data.getData().getRows().get(position).getUserBasicInfo().getUserSmallWidth() + "", data.getData().getRows().get(position).getUserBasicInfo().getUserSmallHeight() + "");
        tvName.setTag(data.getData().getRows().get(position).getUserBasicInfo().getUserId());
        tvName.setText(data.getData().getRows().get(position).getUserBasicInfo().getUserNike());
        tvIdentify.setText(data.getData().getRows().get(position).getUserBasicInfo().getUserRole().get(0).getEredarName());
        tvContent.setText(data.getData().getRows().get(position).getContents());
        tvTime.setText("第" + data.getData().getRows().get(position).getReplysFloor() + "楼  " + new JTimeTransform(data.getData().getRows().get(position).getCreateDate()).toString(new RecentDateFormat()));
        if (TextUtils.isEmpty(data.getData().getRows().get(position).getImg())) {
            ivContent.setVisibility(View.GONE);

        } else {
            ivContent.setVisibility(View.VISIBLE);
            FrescoTool.loadImage(ivContent, data.getData().getRows().get(position).getImg(), TDevice.dip2px(150, BaseApplication.getContext()) + "");
        }
        // 回复的回复

        switch (data.getData().getRows().get(position).getChildReply().size()) {
            case 0:
                llReverContainer.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                break;
            case 1:
                line.setVisibility(View.VISIBLE);
                llReverContainer.setVisibility(View.VISIBLE);
                llOne.setVisibility(View.VISIBLE);
                llTow.setVisibility(View.GONE);
                String name1 = data.getData().getRows().get(position).getChildReply().get(0).getUserBasicInfo().getUserNike();
                String title1 = data.getData().getRows().get(position).getChildReply().get(0).getContents();
                String time1 = data.getData().getRows().get(position).getChildReply().get(0).getCreateDate();
                String image1 = data.getData().getRows().get(position).getChildReply().get(0).getImg();
                setReplayType(name1, title1, time1, image1, tvReverNameOne);
                tvReverNameOne.setTag(data.getData().getRows().get(position).getChildReply().get(0));
                switch (data.getData().getRows().get(position).getChildReply().get(0).getUserBasicInfo().getIsLoginUser()) {
                    case 0:
                        ivReverDelOne.setVisibility(View.GONE);
                        break;
                    case 1:
                        ivReverDelOne.setTag(data.getData().getRows().get(position).getChildReply().get(0).getId());

                        ivReverDelOne.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case 2:
                line.setVisibility(View.VISIBLE);
                llReverContainer.setVisibility(View.VISIBLE);
                llOne.setVisibility(View.VISIBLE);
                llTow.setVisibility(View.VISIBLE);
                String name3 = data.getData().getRows().get(position).getChildReply().get(0).getUserBasicInfo().getUserNike();
                String title3 = data.getData().getRows().get(position).getChildReply().get(0).getContents();
                String time3 = data.getData().getRows().get(position).getChildReply().get(0).getCreateDate();
                String image3 = data.getData().getRows().get(position).getChildReply().get(0).getImg();
                setReplayType(name3, title3, time3, image3, tvReverNameOne);
                tvReverNameOne.setTag(data.getData().getRows().get(position).getChildReply().get(0));
                switch (data.getData().getRows().get(position).getChildReply().get(0).getUserBasicInfo().getIsLoginUser()) {
                    case 0:
                        ivReverDelOne.setVisibility(View.GONE);
                        break;
                    case 1:
                        ivReverDelOne.setTag(data.getData().getRows().get(position).getChildReply().get(0).getId());
                        ivReverDelOne.setVisibility(View.VISIBLE);
                        break;
                }
                String name2 = data.getData().getRows().get(position).getChildReply().get(1).getUserBasicInfo().getUserNike();
                String title2 = data.getData().getRows().get(position).getChildReply().get(1).getContents() + "  ";
                String time2 = data.getData().getRows().get(position).getChildReply().get(1).getCreateDate();
                String image2 = data.getData().getRows().get(position).getChildReply().get(1).getImg();
                setReplayType(name2, title2, time2, image2, tvReverNameTwo);
                tvReverNameTwo.setTag(data.getData().getRows().get(position).getChildReply().get(1));
                switch (data.getData().getRows().get(position).getChildReply().get(1).getUserBasicInfo().getIsLoginUser()) {
                    case 0:
                        ivReverDelTwo.setVisibility(View.GONE);
                        break;
                    case 1:
                        ivReverDelTwo.setTag(data.getData().getRows().get(position).getChildReply().get(1).getId());
                        ivReverDelTwo.setVisibility(View.VISIBLE);
                        break;
                }
                break;
        }

        //设置监听事件
        header.setTag(data.getData().getRows().get(position).getUserBasicInfo().getUserId());
        header.setOnClickListener(this);
        tvName.setOnClickListener(this);
        ivContent.setTag(data.getData().getRows().get(position).getImg());
        ivContent.setOnClickListener(this);
        ivComment.setTag(data.getData().getRows().get(position).getId());
        ivComment.setOnClickListener(this);
        ivMore.setTag(data.getData().getRows().get(position).getId());
        ivMore.setOnClickListener(this);
        if (data.getData().getRows().get(position).getChildCount() == 0) {
            tvMore.setVisibility(View.GONE);
        } else {
            tvMore.setVisibility(View.VISIBLE);
            tvMore.setText("更多" + data.getData().getRows().get(position).getChildCount() + "条多回复..");
            if (data.getData().getRows().get(position).getId() != -1) {
                tvMore.setTag(data.getData().getRows().get(position).getId());
                tvMore.setOnClickListener(this);
            }
        }
        ivReverDelOne.setOnClickListener(this);
        ivReverDelTwo.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (adapter.getOnEveryListener() != null) {
            adapter.getOnEveryListener().onItemClick(v, -1, -1);
        }
        switch (v.getId()) {
            case R.id.iv_community_comment_item:
                if (adapter != null && adapter.getOnCommentHeaderListener() != null)
                    adapter.getOnCommentHeaderListener().onItemClick(v, position, position);
                if (adapter1 != null && adapter1.getOnCommentHeaderListener() != null)
                    adapter1.getOnCommentHeaderListener().onItemClick(v, position, position);
                break;
            case R.id.tv_community_comment_nickname_item:
                if (adapter != null && adapter.getOnCommentHeaderListener() != null)
                    adapter.getOnCommentHeaderListener().onItemClick(v, position, position);
                if (adapter1 != null && adapter1.getOnCommentHeaderListener() != null)
                    adapter1.getOnCommentHeaderListener().onItemClick(v, position, position);
                break;
            case R.id.iv_community_comment_content_item:
                if (adapter != null && adapter.getOnPreCommentListener() != null)
                    adapter.getOnPreCommentListener().onItemClick(v, position, position);

                if (adapter1 != null && adapter1.getOnPreCommentListener() != null)
                    adapter1.getOnPreCommentListener().onItemClick(v, position, position);
                break;
            case R.id.iv_community_comment_perssion_item:
                if (adapter != null && adapter.getOnReverListener() != null) {
                    adapter.getOnReverListener().onItemClick(v, position, position);
                }

                if (adapter1 != null && adapter1.getOnReverListener() != null) {
                    adapter1.getOnReverListener().onItemClick(v, position, position);
                }
                break;
            case R.id.iv_community_comment_more_item:
                if (adapter != null && adapter.getOnMoreListener() != null)
                    adapter.getOnMoreListener().onItemClick(v, position, v.getTag());

                if (adapter1 != null && adapter1.getOnMoreListener() != null)
                    adapter1.getOnMoreListener().onItemClick(v, position, v.getTag());
                break;
            case R.id.tv_community_comment_more_item:
                // topic detail
                if (adapter != null && adapter.getOnMoreReverListener() != null) {
                    adapter.getOnMoreReverListener().onItemClick(v, position, position);
                }

                if (adapter1 != null && adapter1.getOnMoreReverListener() != null) {
                    adapter1.getOnMoreReverListener().onItemClick(v, position, position);
                }
                break;
           /* case R.id.ll_community_comment_item_one:
                if (adapter != null && adapter.getOnReverDeepListener() != null)
                    adapter.getOnReverDeepListener().onItemClick(v, 1, 1);

                if (adapter1 != null && adapter1.getOnReverDeepListener() != null)
                    adapter1.getOnReverDeepListener().onItemClick(v, 1, 1);
                break;
            case R.id.ll_community_comment_item_two:
                if (adapter != null && adapter.getOnReverDeepListener() != null)
                    adapter.getOnReverDeepListener().onItemClick(v, 2, 2);

                if (adapter1 != null && adapter1.getOnReverDeepListener() != null)
                    adapter1.getOnReverDeepListener().onItemClick(v, 2, 2);
                break;*/
            case R.id.iv_community_comment_delete_item_one:
                if (adapter != null && adapter.getOnReverDelListener() != null)
                    adapter.getOnReverDelListener().onItemClick(v, 1, 1);

                if (adapter1 != null && adapter1.getOnReverDelListener() != null)
                    adapter1.getOnReverDelListener().onItemClick(v, 1, 1);
                break;
            case R.id.iv_community_comment_delete_item_two:
                if (adapter != null && adapter.getOnReverDelListener() != null)
                    adapter.getOnReverDelListener().onItemClick(v, 2, 2);

                if (adapter1 != null && adapter1.getOnReverDelListener() != null)
                    adapter1.getOnReverDelListener().onItemClick(v, 2, 2);
                break;
        }
    }

    private void setReplayType(String name, String title, String time, String image, TextView tv) {
        String source1 = "";
        String name1 = name + " : ";
        String title1 = title + "  ";
        String time1 = new JTimeTransform(time).toString(new RecentDateFormat()) + "  ";
        source1 += name1 + title1 + time1 + " ";
        SpannableString ss1 = new SpannableString(source1);
        ss1.setSpan(new ForegroundColorSpan(Color.BLUE), 0, name1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss1.setSpan(new AbsoluteSizeSpan(12, true), name1.length() + title1.length(), name1.length() + title1.length() + time1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        NicClickableSpan nicClickableSpan = new NicClickableSpan();
        ss1.setSpan(nicClickableSpan, 0, name1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (!TextUtils.isEmpty(image)) {
            Drawable drawable1 = tv.getContext().getResources().getDrawable(R.mipmap.btn_community_preview_bg);
            drawable1.setBounds(0, 0, TDevice.dip2px(16, tv.getContext()), TDevice.dip2px(16, tv.getContext()));
            ImageSpan is1 = new ImageSpan(drawable1);
            ss1.setSpan(is1, ss1.length() - 1, ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            PicClickableSpan clickSpan1 = new PicClickableSpan();
            ss1.setSpan(clickSpan1, ss1.length() - 1, ss1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(ss1);
    }


    class PicClickableSpan extends ClickableSpan {

        @Override
        public void onClick(View widget) {
            if (adapter != null && adapter.getOnPreReverListener() != null)
                adapter.getOnPreReverListener().onItemClick(widget, 1, 1);
            if (adapter1 != null && adapter1.getOnPreReverListener() != null)
                adapter1.getOnPreReverListener().onItemClick(widget, 1, 1);
        }
    }

    class NicClickableSpan extends ClickableSpan {

        @Override
        public void onClick(View widget) {
            if (adapter != null && adapter.getOnReverNickListener() != null)
                adapter.getOnReverNickListener().onItemClick(widget, 1, 1);
            if (adapter1 != null && adapter1.getOnReverNickListener() != null)
                adapter1.getOnReverNickListener().onItemClick(widget, 1, 1);
        }

        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
        }
    }


   /* class MyClickableSpan1 extends ClickableSpan {

        @Override
        public void onClick(View widget) {
            if (adapter != null && adapter.getOnReverDeepListener() != null)
                adapter.getOnReverDeepListener().onItemClick(widget, 1, 1);

            if (adapter1 != null && adapter1.getOnReverDeepListener() != null)
                adapter1.getOnReverDeepListener().onItemClick(widget, 1, 1);
        }
    }*/

    class MyClickableSpan2 extends ClickableSpan {

        @Override
        public void onClick(View widget) {
            if (adapter != null && adapter.getOnPreReverListener() != null)
                adapter.getOnPreReverListener().onItemClick(widget, 2, 2);
            if (adapter1 != null && adapter1.getOnPreReverListener() != null)
                adapter1.getOnPreReverListener().onItemClick(widget, 2, 2);
        }
    }

    /*class MyClickableSpan3 extends ClickableSpan {

        @Override
        public void onClick(View widget) {
            if (adapter != null && adapter.getOnReverDeepListener() != null)
                adapter.getOnReverDeepListener().onItemClick(widget, 2, 2);

            if (adapter1 != null && adapter1.getOnReverDeepListener() != null)
                adapter1.getOnReverDeepListener().onItemClick(widget, 2, 2);
        }
    }
*/
}
