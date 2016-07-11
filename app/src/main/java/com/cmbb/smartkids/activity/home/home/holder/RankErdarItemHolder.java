package com.cmbb.smartkids.activity.home.home.holder;

import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.model.RankEredarModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 14:25
 */
public class RankErdarItemHolder extends BaseViewHolder<RankEredarModel> {
    private final String TAG = RankErdarItemHolder.class.getSimpleName();

    private SimpleDraweeView sdbHead;
    private TextView tvNick;
    private TextView tvFans;
    private RatingBar rbErdar;
    private TextView tvTag;

    public RankErdarItemHolder(ViewGroup parent) {
        super(parent, R.layout.list_rank_erdar_item);
        sdbHead = $(R.id.sdb_head);
        tvNick = $(R.id.tv__nick);
        tvFans = $(R.id.tv_fans);
        rbErdar = $(R.id.rb_erdar);
        tvTag = $(R.id.tv_tag);
    }

    public void setData(RankEredarModel row) {
        tvNick.setText(row.getUserNike());
        FrescoTool.loadImage(sdbHead, row.getUserSmallImg());
        rbErdar.setRating(row.getUserLevel());
        tvFans.setText("Fans(" + row.getFans() + ")");
        if (row.getUserRole() != null && row.getUserRole().size() > 0)
            tvTag.setText(row.getUserRole().get(0).getEredarName());
    }
}
