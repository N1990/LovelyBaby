package com.cmbb.smartkids.activity.diary.adapter;

import android.os.NetworkOnMainThreadException;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.holder.BabyDiaryDetailHolder;
import com.cmbb.smartkids.activity.diary.model.BabyDiaryDetailModel;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.Tools;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javon on 15/12/19.
 */
public class BabyDiaryDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int HEADER = -1;
    private final int NORMAL = -2;
    private final int FOOTER = -3;
    private CustomListener.ItemClickListener itemImgListener;
    private BabyDiaryDetailModel.DataEntity.BabyBasicInfoEntity babyInfo;
    private List<BabyDiaryDetailModel.DataEntity.DiaryImgEntity> data;
    private String date;

    public CustomListener.ItemClickListener getItemImgListener() {
        return itemImgListener;
    }

    public void setItemImgListener(CustomListener.ItemClickListener itemImgListener) {
        this.itemImgListener = itemImgListener;
    }

    public void setData(BabyDiaryDetailModel.DataEntity.BabyBasicInfoEntity babyInfo, List<BabyDiaryDetailModel.DataEntity.DiaryImgEntity> data, String date){
        this.babyInfo = babyInfo;
        this.date = date;
        if(data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return HEADER;
        }else if(position == data.size() + 1){
            return FOOTER;
        } else{
            return NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = null;
        if(viewType == HEADER){
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_baby_diary_detail_header_item, parent, false);
            return new HeaderHolder(root);
        }else if(viewType == FOOTER){
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_baby_diary_detail_footer_item, parent, false);
            return new FooterHolder(root);
        } else{
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_baby_diary_detail_item, parent, false);
            return new BabyDiaryDetailHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderHolder){
            ((HeaderHolder) holder).setData(babyInfo);
        }else if(holder instanceof FooterHolder){
            ((FooterHolder) holder).setData(date);
        } else{
            ((BabyDiaryDetailHolder) holder).setData(data.get(position - 1), this, position - 1);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 2;
    }


    class HeaderHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView ivHeader;
        private TextView tvName, tvAge;

        public HeaderHolder(View itemView) {
            super(itemView);
            ivHeader = (SimpleDraweeView) itemView.findViewById(R.id.sdv_baby_diary_detail_item);
            tvName = (TextView) itemView.findViewById(R.id.tv_nickname_baby_diary_detail_item);
            tvAge = (TextView) itemView.findViewById(R.id.tv_age_baby_diary_item);
        }

        public void setData(BabyDiaryDetailModel.DataEntity.BabyBasicInfoEntity data){
            FrescoTool.loadImage(ivHeader, data.getBabyImg());
            tvName.setText(data.getBabyNike());
            tvAge.setText(data.getAge());
        }
    }


    class FooterHolder extends RecyclerView.ViewHolder{
        private TextView tvTime;

        public FooterHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tv_baby_diary_detail_footer_item);
        }

        public void setData(String date){
            try{
                String time = Tools.DataToString(date, "yyyy年MM月dd日");
                tvTime.setText(time);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
