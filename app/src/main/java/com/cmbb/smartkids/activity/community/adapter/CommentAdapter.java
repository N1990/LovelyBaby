package com.cmbb.smartkids.activity.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.holder.CommunityCommentHolder;
import com.cmbb.smartkids.activity.community.model.CommunityReplayModel;
import com.cmbb.smartkids.base.CustomListener;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/6 10:56
 */
public class CommentAdapter extends RecyclerView.Adapter<CommunityCommentHolder> {
    private CommunityReplayModel data;
    private CustomListener.ItemClickListener onCommentHeaderListener;
    private CustomListener.ItemClickListener onReverListener;
    private CustomListener.ItemClickListener onMoreListener;
    private CustomListener.ItemClickListener onMoreReverListener;
    private CustomListener.ItemClickListener onReverDeepListener;
    private CustomListener.ItemClickListener onPreCommentListener;
    private CustomListener.ItemClickListener onPreReverListener;
    private CustomListener.ItemClickListener onReverDelListener;
    private View.OnClickListener onHeaderListener;
    private CustomListener.ItemClickListener onReverNickListener;
    private CustomListener.ItemClickListener onEveryListener;

    public View.OnClickListener getOnHeaderListener() {
        return onHeaderListener;
    }

    public void setOnHeaderListener(View.OnClickListener onHeaderListener) {
        this.onHeaderListener = onHeaderListener;
    }

    public CommunityReplayModel getData() {
        return data;
    }

    public void setData(CommunityReplayModel data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void updateData(CommunityReplayModel replayData) {
        if (replayData != null) {
            this.data = replayData;
        } else {
            return;
        }
        notifyDataSetChanged();
    }

    @Override
    public CommunityCommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_community_comment_item, parent, false);
        return new CommunityCommentHolder(root);
    }

    @Override
    public void onBindViewHolder(CommunityCommentHolder holder, int position) {
        holder.setData(data, this, position);
    }

    @Override
    public int getItemCount() {
        return this.data.getData().getRows().size();
    }


    public CustomListener.ItemClickListener getOnCommentHeaderListener() {
        return onCommentHeaderListener;
    }


    public void setOnCommentHeaderListener(CustomListener.ItemClickListener onCommentHeaderListener) {
        this.onCommentHeaderListener = onCommentHeaderListener;
    }


    public CustomListener.ItemClickListener getOnEveryListener() {
        return onEveryListener;
    }

    public void setOnEveryListener(CustomListener.ItemClickListener onEveryListener) {
        this.onEveryListener = onEveryListener;
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

    public CustomListener.ItemClickListener getOnReverNickListener() {
        return onReverNickListener;
    }

    public void setOnReverNickListener(CustomListener.ItemClickListener onReverNickListener) {
        this.onReverNickListener = onReverNickListener;
    }
}
