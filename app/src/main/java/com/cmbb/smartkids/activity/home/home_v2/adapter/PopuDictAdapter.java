package com.cmbb.smartkids.activity.home.home_v2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home_v2.holder.servicedict.ServiceCategroyHeadHolder;
import com.cmbb.smartkids.activity.home.home_v2.holder.servicedict.ServiceDictFooterHolder;
import com.cmbb.smartkids.activity.home.home_v2.holder.servicedict.ServiceHeadHolder;
import com.cmbb.smartkids.activity.home.home_v2.holder.servicedict.ServiceNormalHolder;
import com.cmbb.smartkids.activity.home.home_v2.holder.servicedict.ServiceSortTypeHeadHolder;
import com.cmbb.smartkids.activity.home.model.ServiceSortModel;
import com.cmbb.smartkids.base.CustomListener;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/10 下午4:39
 */
public class PopuDictAdapter extends RecyclerView.Adapter {

    private Context context;
    ServiceSortModel serviceSmartDictModel;
    private CustomListener.ItemClickListener onConfirmClick;

    public static final int SERVICE_TYPE_HEAD = 1;
    public static final int SERVICE_WAY_HEAD = 2;
    public static final int SERVICE_SORT_HEAD = 3;
    public static final int SERVICE_NORMAL = 4;
    public static final int SERVICE_FOOTER = 5;
    private ServiceNormalHolder normalHolder;

    int categroy;
    int services;
    int sortType;

    public PopuDictAdapter(Context context, ServiceSortModel serviceSmartDictModel) {
        this.setModel(serviceSmartDictModel);
        this.context = context;
    }

    public void setModel(ServiceSortModel model) {
        if (model != null) {
            this.serviceSmartDictModel = model;
            notifyDataSetChanged();
        } else {
            serviceSmartDictModel = new ServiceSortModel();
        }
    }

    public CustomListener.ItemClickListener getOnConfirmClick() {
        return onConfirmClick;
    }

    public void setOnConfirmClick(CustomListener.ItemClickListener onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return SERVICE_TYPE_HEAD;
        } else if (position == categroy) {
            return SERVICE_WAY_HEAD;
        } else if (position == categroy + services) {
            return SERVICE_SORT_HEAD;
        } else if (position == categroy + services + sortType) {
            return SERVICE_FOOTER;
        } else {
            return SERVICE_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case SERVICE_TYPE_HEAD:
                View itemCategroy = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_service_popu_head_dict, parent, false);
                return new ServiceCategroyHeadHolder(itemCategroy);
            case SERVICE_WAY_HEAD:
                View itemService = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_service_popu_head_dict, parent, false);
                return new ServiceHeadHolder(itemService);
            case SERVICE_SORT_HEAD:
                View itemSortType = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_service_popu_head_dict, parent, false);
                return new ServiceSortTypeHeadHolder(itemSortType);
            case SERVICE_FOOTER:
                View itemFooter = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_service_popu_foot_dict, parent, false);
                return new ServiceDictFooterHolder(itemFooter, serviceSmartDictModel, this, onConfirmClick);
            case SERVICE_NORMAL:
                View itemNormal = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_service_popu_normal_dict, parent, false);
                return normalHolder = new ServiceNormalHolder(itemNormal, this, serviceSmartDictModel);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ServiceCategroyHeadHolder) {
            ((ServiceCategroyHeadHolder) holder).setTitle("服务类型");
        } else if (holder instanceof ServiceHeadHolder) {
            ((ServiceHeadHolder) holder).setTitle("服务方式");
        } else if (holder instanceof ServiceSortTypeHeadHolder) {
            ((ServiceSortTypeHeadHolder) holder).setTitle("排序");
        } else {
            // Normal Layout
            if (0 < position && position < categroy) {
                //第一段
                ((ServiceNormalHolder) holder).setCategroyData(serviceSmartDictModel.getData().getServiceCategroy(), 4 * (position - 1));
            } else if (position > categroy && position < categroy + services) {
                //第二段
                ((ServiceNormalHolder) holder).setServiceData(serviceSmartDictModel.getData().getServices(), 4 * (position - 1 - categroy));
            } else if (position > categroy + services && position < categroy + services + sortType) {
                //第三段
                ((ServiceNormalHolder) holder).setServiceSortData(serviceSmartDictModel.getData().getServiceSortType(), 4 * (position - 1 - (categroy + services)));
            }
        }
    }

    @Override
    public int getItemCount() {
        if (serviceSmartDictModel.getData() == null || serviceSmartDictModel.getData().getServiceCategroy() == null) {
            return 0;
        }
        categroy = serviceSmartDictModel.getData().getServiceCategroy().size() / 4 + (serviceSmartDictModel.getData().getServiceCategroy().size() % 4 > 0 ? 1 : 0) + 1;
        services = serviceSmartDictModel.getData().getServices().size() / 4 + (serviceSmartDictModel.getData().getServices().size() % 4 > 0 ? 1 : 0) + 1;
        sortType = serviceSmartDictModel.getData().getServiceSortType().size() / 4 + (serviceSmartDictModel.getData().getServiceSortType().size() % 4 > 0 ? 1 : 0) + 1;
        return categroy + services + sortType + 1;
    }
}


