package com.cmbb.smartkids.activity.serve.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.model.ActiveDetailModel;
import com.cmbb.smartkids.activity.serve.adapter.ActiveAdapter;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by javon on 15/12/9.
 */
public class ActiveHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private SimpleDraweeView iv;
    private TextView tv;
    private ActiveAdapter adapter;
    private int position;

    public ActiveHolder(View itemView) {
        super(itemView);
        iv = (SimpleDraweeView) itemView.findViewById(R.id.iv_active_detail_item);
        tv = (TextView) itemView.findViewById(R.id.tv_active_detail_item);
        iv.setOnClickListener(this);
    }

    public void setData(ActiveDetailModel.DataEntity.ServiceImgListEntity item, ActiveAdapter adapter, int position){
        this.adapter = adapter;
        this.position = position;
        FrescoTool.loadImage(iv, item.getImgPath(), item.getImgWidth(), item.getImgHeight());
        if(!TextUtils.isEmpty(item.getContent())){
            tv.setVisibility(View.VISIBLE);
            tv.setText(item.getContent());
        }else{
            tv.setVisibility(View.GONE);
        }
        iv.setTag(item.getImgPath());
        iv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(adapter.getOnItemImgListener() != null)
            adapter.getOnItemImgListener().onItemClick(v, position, v.getTag());
    }
}
