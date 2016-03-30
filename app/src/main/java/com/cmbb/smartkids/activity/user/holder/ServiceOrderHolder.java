package com.cmbb.smartkids.activity.user.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.model.MyServiceListModel;
import com.cmbb.smartkids.model.ServiceStatus;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/20 14:40
 */
public class ServiceOrderHolder extends BaseViewHolder<MyServiceListModel.DataEntity.RowsEntity> {
    private TextView tvTitle, tvStatus;

    public ServiceOrderHolder(ViewGroup parent) {
        super(parent, R.layout.list_my_service_item);
        tvTitle = $(R.id.tv_my_service_title);
        tvStatus = $(R.id.tv_my_service_status);
    }

    public void setData(MyServiceListModel.DataEntity.RowsEntity obj) {
        tvTitle.setText(obj.getTitle());
        tvStatus.setText(ServiceStatus.getStatusByValue(obj.getStatus()).toString());
    }
}
