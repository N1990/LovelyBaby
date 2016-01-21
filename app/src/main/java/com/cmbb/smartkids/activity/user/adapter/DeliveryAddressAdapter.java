package com.cmbb.smartkids.activity.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.holder.DeliveryAddressHolder;
import com.cmbb.smartkids.activity.user.model.DeliveryAddressListModel;
import com.cmbb.smartkids.base.CustomListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javon on 16/1/13.
 */
public class DeliveryAddressAdapter extends RecyclerView.Adapter<DeliveryAddressHolder> {
    private CustomListener.ItemClickListener onCheckItemListener;
    private CustomListener.ItemClickListener onManagerItemListener;
    private String flag; //manager  check
    private List<DeliveryAddressListModel.DataEntity.RowsEntity> data;
    private int checkId;
    private DeliveryAddressListModel.DataEntity.RowsEntity chooseData;

    public DeliveryAddressAdapter(String flag, List<DeliveryAddressListModel.DataEntity.RowsEntity> data){
        this.flag = flag;
        if(data != null) {
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
    }

    public void setData(List<DeliveryAddressListModel.DataEntity.RowsEntity> data){
        if(data != null) {
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public void setData(List<DeliveryAddressListModel.DataEntity.RowsEntity> data, int checkId){
        this.checkId = checkId;
        if(data != null) {
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public DeliveryAddressListModel.DataEntity.RowsEntity getChooseData() {
        return chooseData;
    }

    public void setChooseData(DeliveryAddressListModel.DataEntity.RowsEntity chooseData) {
        this.chooseData = chooseData;
    }

    public List<DeliveryAddressListModel.DataEntity.RowsEntity> getData() {
        return data;
    }

    public CustomListener.ItemClickListener getOnCheckItemListener() {
        return onCheckItemListener;
    }

    public void setOnCheckItemListener(CustomListener.ItemClickListener onCheckItemListener) {
        this.onCheckItemListener = onCheckItemListener;
    }

    public CustomListener.ItemClickListener getOnManagerItemListener() {
        return onManagerItemListener;
    }

    public void setOnManagerItemListener(CustomListener.ItemClickListener onManagerItemListener) {
        this.onManagerItemListener = onManagerItemListener;
    }

    @Override
    public DeliveryAddressHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_delivery_address_item, parent, false);
        return new DeliveryAddressHolder(root);
    }

    @Override
    public void onBindViewHolder(DeliveryAddressHolder holder, int position) {
        if("manager".equals(flag)){
            holder.setData(this, data.get(position), position, flag);
        }else{
            holder.setData(this, data.get(position), position, flag, checkId);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
