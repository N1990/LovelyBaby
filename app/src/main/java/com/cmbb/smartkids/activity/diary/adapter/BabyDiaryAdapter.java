package com.cmbb.smartkids.activity.diary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.holder.BabyListHolder;
import com.cmbb.smartkids.activity.diary.model.BabyListModel;
import com.cmbb.smartkids.base.CustomListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javon on 15/12/18.
 */
public class BabyDiaryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int NORMAL = -1;
    private final int FOOTER = -2;
    private List<BabyListModel.DataEntity.RowsEntity> data;
    private CustomListener.ItemClickListener onItemListener;
    private CustomListener.ItemClickListener onLongItemListener;
    private View.OnClickListener onFooterListener;



    public void setData(List<BabyListModel.DataEntity.RowsEntity> data){
        if(data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public void removeData(int position){
        this.data.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemViewType(int position) {
        if(position == data.size()){
            return FOOTER;
        }else{
            return NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == FOOTER){
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_baby_footer, parent, false);
            return new FooterHolder(root);
        }else{
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mybaby_item, parent, false);
            return new BabyListHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FooterHolder){
            ((FooterHolder) holder).setData(this);
        }else{
            ((BabyListHolder) holder).setData(data.get(position), this, position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }


    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public View.OnClickListener getOnFooterListener() {
        return onFooterListener;
    }

    public void setOnFooterListener(View.OnClickListener onFooterListener) {
        this.onFooterListener = onFooterListener;
    }

    public CustomListener.ItemClickListener getOnLongItemListener() {
        return onLongItemListener;
    }

    public void setOnLongItemListener(CustomListener.ItemClickListener onLongItemListener) {
        this.onLongItemListener = onLongItemListener;
    }

    class FooterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvAdd;
        private BabyDiaryAdapter adapter;

        public FooterHolder(View itemView) {
            super(itemView);
            tvAdd = (TextView) itemView.findViewById(R.id.tv_my_baby_diary_footer);
        }

        public void setData(BabyDiaryAdapter adapter){
            this.adapter = adapter;
            tvAdd.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(adapter.getOnFooterListener() != null)
                adapter.getOnFooterListener().onClick(v);
        }
    }
}
