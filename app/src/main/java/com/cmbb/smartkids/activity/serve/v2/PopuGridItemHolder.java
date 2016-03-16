package com.cmbb.smartkids.activity.serve.v2;

import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.v2.model.H5ServiceDetailModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 14:25
 */
public class PopuGridItemHolder extends BaseViewHolder<H5ServiceDetailModel.PriceListEntity> {
    private final String TAG = PopuGridItemHolder.class.getSimpleName();

    TextView tv01;

    public PopuGridItemHolder(ViewGroup parent) {
        super(parent, R.layout.popu_grid_item);
        tv01 = $(R.id.tv01);
    }

    public void setData(H5ServiceDetailModel.PriceListEntity row) {
        tv01.setText(row.getSpecification());
        if (row.isSelected()) {
            tv01.setSelected(true);
        } else {
            tv01.setSelected(false);
        }
    }
}
