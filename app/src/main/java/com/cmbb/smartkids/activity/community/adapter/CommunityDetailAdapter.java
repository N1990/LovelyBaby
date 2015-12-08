package com.cmbb.smartkids.activity.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.holder.CommunityCommentHolder;
import com.cmbb.smartkids.activity.community.holder.CommunityHeaderHolder;
import com.cmbb.smartkids.activity.community.holder.CommunityPreuserHolder;
import com.cmbb.smartkids.activity.community.holder.CommunityTeletextHolder;
import com.cmbb.smartkids.activity.community.model.CommunityDetailModel;
import com.cmbb.smartkids.activity.community.model.CommunityReplayModel;
import com.cmbb.smartkids.base.CustomListener;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/5 10:14
 */
public class CommunityDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEADER = -1;
    private final int TELETEXT = -2;
    private final int PREUSERS = -3;
    private final int COMMENTS = -4;
    private CommunityDetailModel detailData;
    private CommunityReplayModel replayData;
    private CustomListener.ItemClickListener onTeletextListener;
    private View.OnClickListener onHeaderListener;
    private View.OnClickListener onPraiseListener;
    private CustomListener.ItemClickListener onCommentHeaderListener;
    private CustomListener.ItemClickListener onPreUserListener;
    private CustomListener.ItemClickListener onReverListener;
    private CustomListener.ItemClickListener onMoreListener;
    private CustomListener.ItemClickListener onMoreReverListener;
    private CustomListener.ItemClickListener onReverDeepListener;
    private CustomListener.ItemClickListener onPreCommentListener;
    private CustomListener.ItemClickListener onPreReverListener;
    private CustomListener.ItemClickListener onReverDelListener;
    private CustomListener.ItemClickListener onReverHeaderListener;
    private CustomListener.ItemClickListener onReverNickListener;
    private CustomListener.ItemClickListener onEveryListener;


    public void setDate(CommunityDetailModel detailData, CommunityReplayModel replayData) {
        if (detailData != null) {
            this.detailData = detailData;
        } else {
            this.detailData = new CommunityDetailModel();
        }

        if (replayData != null) {
            this.replayData = replayData;
        } else {
            this.replayData = new CommunityReplayModel();
        }
        notifyDataSetChanged();
    }


    public void updateData(CommunityReplayModel replayData) {
        if (replayData != null) {
            this.replayData = replayData;
        } else {
            return;
        }
        notifyDataSetChanged();
    }


    public CommunityDetailModel getDetailData() {
        return detailData;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else if (position > 0 && position <= detailData.getData().getTopicImgList().size()) {
            return TELETEXT;
        } else if (position == detailData.getData().getTopicImgList().size() + 1) {
            return PREUSERS;
        } else {
            return COMMENTS;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_community_first_item, parent, false);
            return new CommunityHeaderHolder(root);
        } else if (viewType == TELETEXT) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_community_second_item, parent, false);
            return new CommunityTeletextHolder(root);
        } else if (viewType == PREUSERS) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_community_third_item, parent, false);
            return new CommunityPreuserHolder(root);
        } else {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_community_comment_item, parent, false);
            return new CommunityCommentHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommunityHeaderHolder) {
            ((CommunityHeaderHolder) holder).setData(detailData, this, position);
        } else if (holder instanceof CommunityTeletextHolder) {
            int pos = position - 1;
            ((CommunityTeletextHolder) holder).setData(detailData, this, pos);
        } else if (holder instanceof CommunityPreuserHolder) {
            int pos = position - detailData.getData().getTopicImgList().size() - 1;
            ((CommunityPreuserHolder) holder).setData(detailData, this);
        } else if (holder instanceof CommunityCommentHolder) {
            int pos = position - detailData.getData().getTopicImgList().size() - 2;
            ((CommunityCommentHolder) holder).setData(replayData, this, pos);
        }
    }

    @Override
    public int getItemCount() {
        if (detailData.getData() != null) {
            return replayData.getData().getRows().size() + 2 + detailData.getData().getTopicImgList().size();
        } else {
            return 2 + detailData.getData().getTopicImgList().size();
        }
    }

    public CustomListener.ItemClickListener getOnEveryListener() {
        return onEveryListener;
    }

    public void setOnEveryListener(CustomListener.ItemClickListener onEveryListener) {
        this.onEveryListener = onEveryListener;
    }

    public CustomListener.ItemClickListener getOnTeletextListener() {
        return onTeletextListener;
    }

    public void setOnTeletextListener(CustomListener.ItemClickListener onTeletextListener) {
        this.onTeletextListener = onTeletextListener;
    }


    public CustomListener.ItemClickListener getOnPreUserListener() {
        return onPreUserListener;
    }

    public void setOnPreUserListener(CustomListener.ItemClickListener onPreUserListener) {
        this.onPreUserListener = onPreUserListener;
    }

    public View.OnClickListener getOnPraiseListener() {
        return onPraiseListener;
    }

    public void setOnPraiseListener(View.OnClickListener onPraiseListener) {
        this.onPraiseListener = onPraiseListener;
    }

    public CustomListener.ItemClickListener getOnReverListener() {
        return onReverListener;
    }

    public void setOnReverListener(CustomListener.ItemClickListener onReverListener) {
        this.onReverListener = onReverListener;
    }

    public CustomListener.ItemClickListener getOnMoreListener() {
        return onMoreListener;
    }

    public void setOnMoreListener(CustomListener.ItemClickListener onMoreListener) {
        this.onMoreListener = onMoreListener;
    }

    public CustomListener.ItemClickListener getOnMoreReverListener() {
        return onMoreReverListener;
    }

    public void setOnMoreReverListener(CustomListener.ItemClickListener onMoreReverListener) {
        this.onMoreReverListener = onMoreReverListener;
    }

    public CustomListener.ItemClickListener getOnReverDeepListener() {
        return onReverDeepListener;
    }

    public void setOnReverDeepListener(CustomListener.ItemClickListener onReverDeepListener) {
        this.onReverDeepListener = onReverDeepListener;
    }

    public CustomListener.ItemClickListener getOnPreCommentListener() {
        return onPreCommentListener;
    }

    public void setOnPreCommentListener(CustomListener.ItemClickListener onPreCommentListener) {
        this.onPreCommentListener = onPreCommentListener;
    }

    public CustomListener.ItemClickListener getOnPreReverListener() {
        return onPreReverListener;
    }

    public void setOnPreReverListener(CustomListener.ItemClickListener onPreReverListener) {
        this.onPreReverListener = onPreReverListener;
    }

    public CustomListener.ItemClickListener getOnReverDelListener() {
        return onReverDelListener;
    }

    public void setOnReverDelListener(CustomListener.ItemClickListener onReverDelListener) {
        this.onReverDelListener = onReverDelListener;
    }

    public CustomListener.ItemClickListener getOnReverHeaderListener() {
        return onReverHeaderListener;
    }

    public void setOnReverHeaderListener(CustomListener.ItemClickListener onReverHeaderListener) {
        this.onReverHeaderListener = onReverHeaderListener;
    }

    public CustomListener.ItemClickListener getOnCommentHeaderListener() {
        return onCommentHeaderListener;
    }

    public void setOnCommentHeaderListener(CustomListener.ItemClickListener onCommentHeaderListener) {
        this.onCommentHeaderListener = onCommentHeaderListener;
    }

    public View.OnClickListener getOnHeaderListener() {
        return onHeaderListener;
    }

    public void setOnHeaderListener(View.OnClickListener onHeaderListener) {
        this.onHeaderListener = onHeaderListener;
    }

    public CustomListener.ItemClickListener getOnReverNickListener() {
        return onReverNickListener;
    }

    public void setOnReverNickListener(CustomListener.ItemClickListener onReverNickListener) {
        this.onReverNickListener = onReverNickListener;
    }


}
