package com.cmbb.smartkids.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.model.PhotoAdd;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：15/9/1 下午15:59
 */
public class PopmanAddPicViewHolder extends RecyclerView.ViewHolder {


    private final SimpleDraweeView ivAddPic;
    private final ImageView ivAddDel;


    public PopmanAddPicViewHolder(View itemView) {
        super(itemView);
        ivAddPic = (SimpleDraweeView) itemView.findViewById(R.id.iv_pic_item);
        ivAddDel = (ImageView) itemView.findViewById(R.id.iv_pic_delete_item);
    }

    public void setData(final Context context, final ArrayList<PhotoAdd> photoContents, final int position, final PhotoAddAdapter adapter) {
        //PicassoTools.loadImage(context, ivAddPic, photoContents.get(position).getPhotoUrl(), false);
        FrescoTool.loadImage(ivAddPic, photoContents.get(position).getPhotoUrl());
        ivAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getPicPreListener() != null)
                    adapter.getPicPreListener().onItemClick(v, position, photoContents.get(position));
            }
        });
        ivAddDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getPicDeleteListener() != null)
                    adapter.getPicDeleteListener().onItemClick(v, position, photoContents.get(position));
            }
        });
    }


}
