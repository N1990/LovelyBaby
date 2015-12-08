package com.cmbb.smartkids.activity.user.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.model.MyServiceListModel;
import com.cmbb.smartkids.activity.user.adapter.ServiceOrderAdapter;
import com.cmbb.smartkids.model.ServiceStatus;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/20 14:40
 */
public class ServiceOrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private View root;
    private ServiceOrderAdapter adapter;
    private TextView tvTitle, tvStatus;
    private int position;

    public ServiceOrderHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        tvTitle = (TextView) itemView.findViewById(R.id.tv_my_service_title);
//        tvAcceptNo = (TextView) itemView.findViewById(R.id.tv_my_service_accept_number);
        tvStatus = (TextView) itemView.findViewById(R.id.tv_my_service_status);
    }

    public void setData(ServiceOrderAdapter adapter, int position , MyServiceListModel.DataEntity.RowsEntity obj){
        this.adapter = adapter;
        this.position = position;
        tvTitle.setText(obj.getTitle());
        tvStatus.setText(ServiceStatus.getStatusByValue(obj.getStatus()).toString());
        root.setTag(obj);
        root.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, root.getTag());
    }
}
