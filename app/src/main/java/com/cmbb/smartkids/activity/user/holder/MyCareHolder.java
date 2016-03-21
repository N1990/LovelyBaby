package com.cmbb.smartkids.activity.user.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.model.FriendListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 14:10
 */
public class MyCareHolder extends BaseViewHolder<FriendListModel.DataEntity.RowsEntity> {
    private TextView tvName, tvIdentity;
    private SimpleDraweeView ivHeader;

    public MyCareHolder(ViewGroup itemView) {
        super(itemView, R.layout.list_friend_item);
        ivHeader = $(R.id.iv_friend_item);
        tvName = $(R.id.tv_friend_name_item);
        tvIdentity = $(R.id.tv_friend_identity_item);
    }

    @Override
    public void setData(FriendListModel.DataEntity.RowsEntity itemData) {
        tvName.setText(itemData.getUserNike());
        FrescoTool.loadImage(ivHeader, itemData.getUserSmallImg());
        tvIdentity.setText(itemData.getUserRole().get(0).getEredarName());
    }

}
