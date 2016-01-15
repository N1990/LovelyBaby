package com.cmbb.smartkids.activity.diary.holder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ScaleXSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.adapter.BabyDiaryListAdapter;
import com.cmbb.smartkids.activity.diary.model.BabyDiaryListModel;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.Tools;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by javon on 15/12/18.
 */
public class BabyDiaryListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private BabyDiaryListAdapter adapter;
    private int position;
    private SimpleDraweeView ivOne, ivtwo, ivThree;
    private ImageView ivTag;
    private TextView tvToday, tvPublishDate;
    public BabyDiaryListHolder(View itemView) {
        super(itemView);
        ivOne = (SimpleDraweeView) itemView.findViewById(R.id.sdv_baby_diary_one_item);
        ivtwo = (SimpleDraweeView) itemView.findViewById(R.id.sdv_baby_diary_two_item);
        ivThree = (SimpleDraweeView) itemView.findViewById(R.id.sdv_baby_diary_three_item);
        ivTag = (ImageView) itemView.findViewById(R.id.iv_header_tag_baby_diary_item);
        tvToday = (TextView) itemView.findViewById(R.id.tv_today_baby_diary_item);
        tvPublishDate = (TextView) itemView.findViewById(R.id.tv_date_baby_diary_item);
    }

    public void setData(BabyDiaryListModel.DataEntity.RowsEntity data, BabyDiaryListAdapter adapte, int position){
        this.adapter = adapte;
        this.position = position;
        if(position != 1){
            ivTag.setVisibility(View.GONE);
            tvToday.setVisibility(View.GONE);
        }else{
            ivTag.setVisibility(View.VISIBLE);
            tvToday.setVisibility(View.VISIBLE);
        }
        try{
            String todayDate = Tools.DateToString(System.currentTimeMillis(), "今天MM月dd日");
            String publisDate = Tools.DataToString(data.getDiaryDate(), "yyyy年MM月dd日");
            tvPublishDate.setText(publisDate);
            SpannableString ss = new SpannableString(todayDate);
            ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new ScaleXSpan(1.03f), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvToday.setText(ss);
        }catch (Exception e){
            e.printStackTrace();
        }
        ivtwo.setVisibility(View.GONE);
        ivThree.setVisibility(View.GONE);
        for (int i = 0; i < data.getDiaryImg().size(); i ++){
            if(i == 0){
                FrescoTool.loadImage(ivOne, data.getDiaryImg().get(i).getImg(), TDevice.dip2px(100, ivOne.getContext()) + "");
            }else if(i == 1){
                ivtwo.setVisibility(View.VISIBLE);
                FrescoTool.loadImage(ivtwo, data.getDiaryImg().get(i).getImg(), TDevice.dip2px(100, ivtwo.getContext()) + "");
            }else if(i == 2){
                ivThree.setVisibility(View.VISIBLE);
                FrescoTool.loadImage(ivThree, data.getDiaryImg().get(i).getImg(), TDevice.dip2px(100, ivThree.getContext()) + "");
            }
        }
        itemView.setTag(data);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, v.getTag());
    }
}
