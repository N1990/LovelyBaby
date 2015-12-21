package com.cmbb.smartkids.activity.diary.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.adapter.BabyDiaryAdapter;
import com.cmbb.smartkids.activity.diary.model.BabyListModel;
import com.cmbb.smartkids.activity.user.adapter.UserBabyDiaryAdapter;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by javon on 15/12/18.
 */
public class BabyListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private SimpleDraweeView sdvHeader;
    private TextView tvName, tvAge, tvRecord;
    private BabyDiaryAdapter adapter;
    private UserBabyDiaryAdapter uAdapter;
    private int position;

    public BabyListHolder(View itemView) {
        super(itemView);
        sdvHeader = (SimpleDraweeView) itemView.findViewById(R.id.sdv_baby_diary_item);
        tvName = (TextView) itemView.findViewById(R.id.tv_nickname_baby_diary_item);
        tvAge = (TextView) itemView.findViewById(R.id.tv_age_baby_diary_item);
        tvRecord = (TextView) itemView.findViewById(R.id.tv_record_baby_diary_item);
    }

    public void setData(BabyListModel.DataEntity.RowsEntity data, BabyDiaryAdapter adapter, int position){
        this.adapter = adapter;
        this.position = position;
        FrescoTool.loadImage(sdvHeader, data.getBabyImg());
        tvName.setText(data.getBabyNike());
        if (Integer.parseInt(data.getBabySex()) == 1){
            tvName.setCompoundDrawables(tvName.getContext().getResources().getDrawable(R.mipmap.btn_male_boy_bg), null, null, null);
        }else{
            tvName.setCompoundDrawables(tvName.getContext().getResources().getDrawable(R.mipmap.btn_male_girl_bg), null, null, null);
        }
        tvAge.setText(data.getAge());
        tvRecord.setText(data.getDiaryCount() > 999 ? "999+" : data.getDiaryCount()+ "");
        itemView.setTag(data);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(onLongClickListener);
    }

    public void setData(BabyListModel.DataEntity.RowsEntity data, UserBabyDiaryAdapter adapter, int position){
        this.uAdapter = adapter;
        this.position = position;
        FrescoTool.loadImage(sdvHeader, data.getBabyImg());
        tvName.setText(data.getBabyNike());
        if (Integer.parseInt(data.getBabySex()) == 1){
            tvName.setCompoundDrawablesWithIntrinsicBounds(tvName.getContext().getResources().getDrawable(R.mipmap.btn_male_boy_bg), null, null, null);
        }else{
            tvName.setCompoundDrawablesWithIntrinsicBounds(tvName.getContext().getResources().getDrawable(R.mipmap.btn_male_girl_bg), null, null, null);
        }
        tvAge.setText(data.getAge());
        tvRecord.setText(data.getDiaryCount() > 999 ? "999+" : data.getDiaryCount() + "");
        itemView.setTag(data);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(adapter != null && adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, v.getTag());
        if(uAdapter != null && uAdapter.getOnItemListener() != null)
            uAdapter.getOnItemListener().onItemClick(v, position, v.getTag());
    }

    private View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if(adapter.getOnLongItemListener() != null)
                adapter.getOnLongItemListener().onItemClick(v, position, v.getTag());
            return true;
        }
    };
}
