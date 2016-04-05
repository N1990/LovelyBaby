package com.cmbb.smartkids.activity.home.home_v2.holder.servicedict;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home_v2.adapter.PopuDictAdapter;
import com.cmbb.smartkids.activity.home.model.ServiceSortModel;
import com.cmbb.smartkids.base.CustomListener;


/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/10 下午12:51
 */
public class ServiceDictFooterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView btnRest;
    TextView btnComfirm;
    ServiceSortModel model;
    PopuDictAdapter adapter;
    CustomListener.ItemClickListener itemClickListener;

    public ServiceDictFooterHolder(View itemView, ServiceSortModel model, PopuDictAdapter adapter, CustomListener.ItemClickListener itemClickListener) {
        super(itemView);
        this.model = model;
        this.adapter = adapter;
        this.itemClickListener = itemClickListener;
        btnRest = (TextView) itemView.findViewById(R.id.btn_rest);
        btnRest.setOnClickListener(this);
        btnComfirm = (TextView) itemView.findViewById(R.id.btn_comfirm);
        btnComfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rest:
                for (ServiceSortModel.DataEntity.ServiceCategroyEntity entity : model.getData().getServiceCategroy()) {
                    entity.setChecked(false);
                }
                for (ServiceSortModel.DataEntity.ServicesEntity entity : model.getData().getServices()) {
                    entity.setChecked(false);
                }
                for (ServiceSortModel.DataEntity.ServiceSortTypeEntity entity : model.getData().getServiceSortType()) {
                    entity.setChecked(false);
                }

                for (ServiceSortModel.DataEntity.ServiceStatusEntity entity : model.getData().getServiceStatus()) {
                    entity.setChecked(false);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_comfirm:
                if (itemClickListener != null) {
                    ConfirmModel confirmModel = new ConfirmModel();
                    for (ServiceSortModel.DataEntity.ServiceCategroyEntity entity : model.getData().getServiceCategroy()) {
                        if (entity.isChecked()) {
                            confirmModel.setCategory(entity.getValue());
                        }
                    }
                    for (ServiceSortModel.DataEntity.ServicesEntity entity : model.getData().getServices()) {
                        if (entity.isChecked()) {
                            confirmModel.setType(entity.getValue());
                        }
                    }
                    for (ServiceSortModel.DataEntity.ServiceSortTypeEntity entity : model.getData().getServiceSortType()) {
                        if (entity.isChecked()) {
                            confirmModel.setSortType(entity.getValue());
                        }
                    }
                    for (ServiceSortModel.DataEntity.ServiceStatusEntity entity : model.getData().getServiceStatus()) {
                        if (entity.isChecked()) {
                            confirmModel.setStatus(entity.getValue());
                        }
                    }
                    itemClickListener.onItemClick(v, -1, confirmModel);
                }
                break;
        }
    }

    public class ConfirmModel {

        String category;
        String sortType;
        String type;
        String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getSortType() {
            return sortType;
        }

        public void setSortType(String sortType) {
            this.sortType = sortType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
