package com.cmbb.smartkids.activity.user.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.model.UserEvaluateModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by javon on 16/3/21.
 */
public class MyEvaluateUserHolder extends BaseViewHolder<UserEvaluateModel.DataEntity.RowsEntity> {

    private SimpleDraweeView sdvEvaluatePopmanItem;
    private TextView tvEvaluatePopmanNicknameItem;
    private RatingBar rbEvaluatePopmanItem;
    private TextView tvEvaluatePopmanTag;
    private TextView tvEvaluatePopmanContent;

    public MyEvaluateUserHolder(ViewGroup parent) {
        super(parent, R.layout.list_popman_evaluate_item);
        sdvEvaluatePopmanItem = $(R.id.sdv_evaluate_popman_item);
        tvEvaluatePopmanNicknameItem = $(R.id.tv_evaluate_popman_nickname_item);
        rbEvaluatePopmanItem = $(R.id.rb_evaluate_popman_item);
        tvEvaluatePopmanTag = $(R.id.tv_evaluate_popman_tag);
        tvEvaluatePopmanContent = $(R.id.tv_evaluate_popman_content);

    }

    @Override
    public void setData(UserEvaluateModel.DataEntity.RowsEntity data, int position) {
        super.setData(data, position);
        FrescoTool.loadImage(sdvEvaluatePopmanItem, data.getUserBasicInfo().getUserSmallImg());
        tvEvaluatePopmanNicknameItem.setText(data.getUserBasicInfo().getUserNike());
        rbEvaluatePopmanItem.setRating(data.getUserBasicInfo().getUserLevel());
        if(TextUtils.isEmpty(data.getEvaluateContent())){
            tvEvaluatePopmanContent.setVisibility(View.GONE);
        }else{
            tvEvaluatePopmanContent.setVisibility(View.VISIBLE);
            tvEvaluatePopmanContent.setText(data.getEvaluateContent());
        }
        switch (data.getEvaluateType()){
            case 1:
                tvEvaluatePopmanTag.setText("强力推荐");
                break;
            case 2:
                tvEvaluatePopmanTag.setText("非常满意");
                break;
            case 3:
                tvEvaluatePopmanTag.setText("满意");
                break;
            case 4:
                tvEvaluatePopmanTag.setText("一般");
                break;
        }
    }
}
