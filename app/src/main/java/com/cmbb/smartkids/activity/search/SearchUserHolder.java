package com.cmbb.smartkids.activity.search;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 17:15
 */
public class SearchUserHolder extends BaseViewHolder<SearchUserModel.DataEntity.RowsEntity> {
    private final String TAG = SearchUserHolder.class.getSimpleName();

    private SimpleDraweeView sdvSearchUserHead;
    private TextView tvSearchUserNick;
    private RatingBar rbSearchUserPerssion;
    private TextView tvSearchUserTag;
    private TextView tvFans;


    public SearchUserHolder(ViewGroup parent) {
        super(parent, R.layout.list_search_user_item);
        sdvSearchUserHead = $(R.id.sdv_search_user_head);
        tvSearchUserNick = $(R.id.tv_search_user_nick);
        rbSearchUserPerssion = $(R.id.rb_search_user_perssion);
        tvSearchUserTag = $(R.id.tv_search_user_tag);
        tvFans = $(R.id.tv_fans);
    }

    @Override
    public void setData(SearchUserModel.DataEntity.RowsEntity data) {
        super.setData(data);
        Log.e(TAG, "header : " + data.getUserSmallImg());
        FrescoTool.loadImage(sdvSearchUserHead, data.getUserSmallImg());
        tvSearchUserNick.setVisibility(View.VISIBLE);
        if(!TextUtils.isEmpty(data.getUserNike())){
            tvSearchUserNick.setText(data.getUserNike());
        }else{
            tvSearchUserNick.setVisibility(View.INVISIBLE);
        }
        rbSearchUserPerssion.setRating(data.getUserLevel());
        tvSearchUserTag.setText(data.getUserRole().get(0).getEredarName());
        tvFans.setText("Fans(" + data.getFans() + ")");
    }


}
