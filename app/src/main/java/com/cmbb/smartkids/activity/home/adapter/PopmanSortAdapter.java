package com.cmbb.smartkids.activity.home.adapter;

import android.content.Context;

import com.cmbb.smartkids.model.PopmanSortModel;
import com.cmbb.smartkids.widget.spinner.NiceSpinnerBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 19:41
 */
public class PopmanSortAdapter extends NiceSpinnerBaseAdapter {

    private List<PopmanSortModel.DataEntity> data;

    public PopmanSortAdapter(Context context) {
        super(context);
    }


    public void setData(List<PopmanSortModel.DataEntity> data){
        if(data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public List<PopmanSortModel.DataEntity> getData(){
        return this.data;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getName();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItemInDataset(int position) {
        return data.get(position).getName();
    }
}
