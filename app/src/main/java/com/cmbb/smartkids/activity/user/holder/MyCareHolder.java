package com.cmbb.smartkids.activity.user.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.MyCareAdapter;
import com.cmbb.smartkids.activity.user.model.FriendListModel;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 14:10
 */
public class MyCareHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private MyCareAdapter adapter;
    private View item;
    private TextView tvName, tvIdentity;
    private SimpleDraweeView ivHeader;
    private int position;

    public MyCareHolder(View itemView) {
        super(itemView);
        this.item = itemView;
        ivHeader = (SimpleDraweeView) itemView.findViewById(R.id.iv_friend_item);
        tvName = (TextView) itemView.findViewById(R.id.tv_friend_name_item);
        tvIdentity = (TextView) itemView.findViewById(R.id.tv_friend_identity_item);
    }

    public void setData(Context context, MyCareAdapter adapter, int position, FriendListModel.DataEntity.RowsEntity itemData) {
        this.adapter = adapter;
        this.position = position;
        tvName.setText(itemData.getUserNike());
        //PicassoTools.loadCircleImage(context, ivHeader, itemData.getUserSmallImg(), true);
        FrescoTool.loadImage(ivHeader, itemData.getUserSmallImg());
        tvIdentity.setText(itemData.getUserRole().get(0).getEredarName());
        item.setTag(itemData);
        item.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FriendListModel.DataEntity.RowsEntity itemData = (FriendListModel.DataEntity.RowsEntity) v.getTag();
        if (adapter.getOnItemClick() != null)
            adapter.getOnItemClick().onItemClick(v, position, itemData);
    }
}
