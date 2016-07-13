package com.cmbb.smartkids.activity.user.holder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.DeliveryAddressAdapter;
import com.cmbb.smartkids.activity.address.model.DeliveryAddressListModel;

/**
 * Created by javon on 16/1/13.
 */
public class DeliveryAddressHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView tvName, tvPhone, tvAddress;
    private ImageView ivCheck;
    private DeliveryAddressAdapter adapter;
    private int position;
    private String flag;
    private int checkId;
    public DeliveryAddressHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_name_delivery_address_item);
        tvPhone = (TextView) itemView.findViewById(R.id.tv_phone_delivery_address_item);
        tvAddress = (TextView) itemView.findViewById(R.id.tv_address_delivery_address_item);
        ivCheck = (ImageView) itemView.findViewById(R.id.iv_delivery_address_item);
    }

    public void setData(DeliveryAddressAdapter adapter, DeliveryAddressListModel.DataEntity.RowsEntity item, int position, String flag){//manager  check
        this.adapter = adapter;
        this.position = position;
        this.flag = flag;
        tvName.setText(item.getReceiveName());
        tvPhone.setText(item.getReceivePhone());
        if(item.getIsDefault() == 1){
            ivCheck.setVisibility(View.VISIBLE);
            String address = item.getProvinceText() + item.getCityText() + item.getDistrictText() + item.getAddress();
            SpannableString ss = new SpannableString("[默认]" + address);
            ss.setSpan(new ForegroundColorSpan(tvAddress.getContext().getResources().getColor(R.color.orange)), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new ForegroundColorSpan(Color.BLACK), 4, ss.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvAddress.setText(ss);
        } else {
            ivCheck.setVisibility(View.INVISIBLE);
            tvAddress.setTextColor(Color.BLACK);
            tvAddress.setText(item.getProvinceText() + item.getCityText() + item.getDistrictText() + item.getAddress());
        }
        itemView.setTag(item);
        itemView.setOnClickListener(this);
    }

    public void setData(DeliveryAddressAdapter adapter, DeliveryAddressListModel.DataEntity.RowsEntity item, int position, String flag, int checkId){//manager  check
        this.adapter = adapter;
        this.position = position;
        this.flag = flag;
        tvName.setText(item.getReceiveName());
        tvPhone.setText(item.getReceivePhone());
        if(checkId == item.getId()){
            ivCheck.setVisibility(View.VISIBLE);
           this.adapter.setChooseData(item);
            if(item.getIsDefault() == 1){
                String address = item.getProvinceText() + item.getCityText() + item.getDistrictText() + item.getAddress();
                SpannableString ss = new SpannableString("[默认]" + address);
                ss.setSpan(new ForegroundColorSpan(tvAddress.getContext().getResources().getColor(R.color.orange)), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(new ForegroundColorSpan(Color.BLACK), 4, ss.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvAddress.setText(ss);
            } else {
                tvAddress.setTextColor(Color.BLACK);
                tvAddress.setText(item.getProvinceText() + item.getCityText() + item.getDistrictText() + item.getAddress());
            }
        }else {
            ivCheck.setVisibility(View.INVISIBLE);
            if(item.getIsDefault() == 1){
                String address = item.getProvinceText() + item.getCityText() + item.getDistrictText() + item.getAddress();
                SpannableString ss = new SpannableString("[默认]" + address);
                ss.setSpan(new ForegroundColorSpan(tvAddress.getContext().getResources().getColor(R.color.orange)), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(new ForegroundColorSpan(Color.BLACK), 4, ss.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvAddress.setText(ss);
            } else {
                tvAddress.setTextColor(Color.BLACK);
                tvAddress.setText(item.getProvinceText() + item.getCityText() + item.getDistrictText() + item.getAddress());
            }
        }
        itemView.setTag(item);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if("manager".equals(flag) && adapter.getOnManagerItemListener() != null){
            adapter.getOnManagerItemListener().onItemClick(v, position, v.getTag());
        }else if("check".equals(flag) && adapter.getOnCheckItemListener() != null){
            adapter.getOnCheckItemListener().onItemClick(v, position, v.getTag());
            adapter.notifyDataSetChanged();
        }
    }
}



