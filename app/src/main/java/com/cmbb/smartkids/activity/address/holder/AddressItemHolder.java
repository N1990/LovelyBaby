package com.cmbb.smartkids.activity.address.holder;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.address.model.DeliveryAddressListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 14:25
 */
public class AddressItemHolder extends BaseViewHolder<DeliveryAddressListModel.DataEntity.RowsEntity> {
    private final String TAG = AddressItemHolder.class.getSimpleName();

    private TextView tvName, tvPhone, tvAddress;
    private ImageView ivCheck;

    public AddressItemHolder(ViewGroup parent) {
        super(parent, R.layout.list_delivery_address_item);
        tvName = $(R.id.tv_name_delivery_address_item);
        tvPhone = $(R.id.tv_phone_delivery_address_item);
        tvAddress = $(R.id.tv_address_delivery_address_item);
        ivCheck = $(R.id.iv_delivery_address_item);
    }

    public void setData(DeliveryAddressListModel.DataEntity.RowsEntity row) {
        if (row == null)
            return;
        tvName.setText(row.getReceiveName());
        tvPhone.setText(row.getReceivePhone());
        if (row.getIsDefault() == 1) {
            ivCheck.setVisibility(View.VISIBLE);
            String address = row.getProvinceText() + row.getCityText() + row.getDistrictText() + row.getAddress();
            SpannableString ss = new SpannableString("[默认]" + address);
            ss.setSpan(new ForegroundColorSpan(tvAddress.getContext().getResources().getColor(R.color.orange)), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new ForegroundColorSpan(Color.BLACK), 4, ss.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvAddress.setText(ss);
        } else {
            ivCheck.setVisibility(View.INVISIBLE);
            tvAddress.setTextColor(Color.BLACK);
            tvAddress.setText(row.getProvinceText() + row.getCityText() + row.getDistrictText() + row.getAddress());
        }
    }
}
