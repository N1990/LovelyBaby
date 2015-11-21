package com.cmbb.smartkids.activity.home.adapter;

import android.content.Context;

import com.cmbb.smartkids.activity.home.model.ServiceSortModel;
import com.cmbb.smartkids.widget.spinner.NiceSpinnerBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 19:41
 */
public class ServiceCityAdapter extends NiceSpinnerBaseAdapter {

    private List<ServiceSortModel.DataEntity.ServiceCityEntity> data;

    public ServiceCityAdapter(Context context) {
        super(context);
    }


    public void setData(List<ServiceSortModel.DataEntity.ServiceCityEntity> data){
        if(data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public List<ServiceSortModel.DataEntity.ServiceCityEntity> getData(){
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


    public String getValues(int position){
        return data.get(position).getValue();
    }
}
