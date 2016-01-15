package com.cmbb.smartkids.activity.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.TopicTypeModel;
import com.cmbb.smartkids.activity.home.holder.TopicHolder;
import com.cmbb.smartkids.base.CustomListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 14:17
 */
public class TopicAdapter extends RecyclerView.Adapter<TopicHolder> {
    private CustomListener.ItemClickListener onItemListener;
    private List<TopicTypeModel.DataEntity> data;
    private int selecIndex = 0;

    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public void setData(List<TopicTypeModel.DataEntity> data){
        if(data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public void addData(List<TopicTypeModel.DataEntity> data){
        if(data != null)
            this.data.addAll(data);
        notifyDataSetChanged();
    }

    public TopicTypeModel.DataEntity getSelectData(){
        if(selecIndex == -1)
            return this.data.get(0);
        return this.data.get(selecIndex);
    }

    public  void setSelectIndex(int selecIndex){
        this.selecIndex = selecIndex;
        notifyDataSetChanged();
    }


    public int getSelecIndex(){
        return  this.selecIndex;
    }



    @Override
    public TopicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_topic_item, parent, false);
        return new TopicHolder(root);
    }

    @Override
    public void onBindViewHolder(TopicHolder holder, int position) {
            holder.setData(data.get(position), this, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
