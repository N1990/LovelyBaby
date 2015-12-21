package com.cmbb.smartkids.activity.diary.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.adapter.BabyDiaryDetailAdapter;
import com.cmbb.smartkids.activity.diary.model.BabyDiaryDetailModel;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by javon on 15/12/19.
 */
public class BabyDiaryDetailHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private BabyDiaryDetailAdapter adapter;
    private int position;
    private SimpleDraweeView iv;
    private TextView tv;

    public BabyDiaryDetailHolder(View itemView) {
        super(itemView);
        iv = (SimpleDraweeView) itemView.findViewById(R.id.iv_baby_diary_detail_item);
        tv = (TextView) itemView.findViewById(R.id.tv_baby_diary_detail_content_item);
    }

    public void setData(BabyDiaryDetailModel.DataEntity.DiaryImgEntity data, BabyDiaryDetailAdapter adapter, int position){
        this.position = position;
        this.adapter = adapter;
        this.position = position;
        FrescoTool.loadImage(iv, data.getImg(), data.getImgWidth(), data.getImgHeight());
        if(!TextUtils.isEmpty(data.getContents())){
            tv.setVisibility(View.VISIBLE);
            tv.setText(data.getContents());
        }else{
            tv.setVisibility(View.GONE);
        }
        iv.setTag(data.getImg());
        iv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(adapter.getItemImgListener() != null)
            adapter.getItemImgListener().onItemClick(v, position, v.getTag());
    }
}
