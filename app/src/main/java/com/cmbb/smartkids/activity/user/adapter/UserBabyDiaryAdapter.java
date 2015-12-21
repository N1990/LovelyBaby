package com.cmbb.smartkids.activity.user.adapter;

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
public class UserBabyDiaryAdapter extends RecyclerView.Adapter<BabyListHolder> {
    private List<BabyListModel.DataEntity.RowsEntity> data;
    private CustomListener.ItemClickListener onItemListener;


    public void setData(List<BabyListModel.DataEntity.RowsEntity> data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }


    @Override
    public BabyListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mybaby_item, parent, false);
        return new BabyListHolder(root);
    }

    @Override
    public void onBindViewHolder(BabyListHolder holder, int position) {
       holder.setData(data.get(position), this, position);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

}
