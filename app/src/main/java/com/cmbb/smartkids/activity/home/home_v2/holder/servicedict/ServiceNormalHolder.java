package com.cmbb.smartkids.activity.home.home_v2.holder.servicedict;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home_v2.adapter.PopuDictAdapter;
import com.cmbb.smartkids.activity.home.model.ServiceSortModel;

import java.util.List;


/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/10 上午11:44
 */
public class ServiceNormalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tv01, tv02, tv03, tv04;
    PopuDictAdapter adapter;
    ServiceSortModel model;

    public ServiceNormalHolder(View itemView, PopuDictAdapter adapter, ServiceSortModel model) {
        super(itemView);
        this.adapter = adapter;
        this.model = model;
        tv01 = (TextView) itemView.findViewById(R.id.tv01);
        tv02 = (TextView) itemView.findViewById(R.id.tv02);
        tv03 = (TextView) itemView.findViewById(R.id.tv03);
        tv04 = (TextView) itemView.findViewById(R.id.tv04);
    }

    public void setCategroyData(List<ServiceSortModel.DataEntity.ServiceCategroyEntity> entities, int part) {
        if (part < entities.size()) {
            tv01.setText(entities.get(part).getName());
            if (entities.get(part).isChecked()) {
                tv01.setSelected(true);
            } else {
                tv01.setSelected(false);
            }
            tv01.setTag(entities.get(part));
            tv01.setOnClickListener(this);
        } else {
            tv01.setVisibility(View.INVISIBLE);
        }
        if (part + 1 < entities.size()) {
            tv02.setText(entities.get(part + 1).getName());
            if (entities.get(part + 1).isChecked()) {
                tv02.setSelected(true);
            } else {
                tv02.setSelected(false);
            }
            tv02.setTag(entities.get(part + 1));
            tv02.setOnClickListener(this);
        } else {
            tv02.setVisibility(View.INVISIBLE);
        }
        if (part + 2 < entities.size()) {
            tv03.setText(entities.get(part + 2).getName());
            if (entities.get(part + 2).isChecked()) {
                tv03.setSelected(true);
            } else {
                tv03.setSelected(false);
            }
            tv03.setTag(entities.get(part + 2));
            tv03.setOnClickListener(this);
        } else {
            tv03.setVisibility(View.INVISIBLE);
        }
        if (part + 3 < entities.size()) {
            tv04.setText(entities.get(part + 3).getName());
            if (entities.get(part + 3).isChecked()) {
                tv04.setSelected(true);
            } else {
                tv04.setSelected(false);
            }
            tv04.setTag(entities.get(part + 3));
            tv04.setOnClickListener(this);
        } else {
            tv04.setVisibility(View.INVISIBLE);
        }
    }

    public void setServiceData(List<ServiceSortModel.DataEntity.ServicesEntity> entities, int part) {
        if (part < entities.size()) {
            tv01.setText(entities.get(part).getName());
            if (entities.get(part).isChecked()) {
                tv01.setSelected(true);
            } else {
                tv01.setSelected(false);
            }
            tv01.setTag(entities.get(part));
            tv01.setOnClickListener(this);
        } else {
            tv01.setVisibility(View.INVISIBLE);
        }
        if (part + 1 < entities.size()) {
            tv02.setText(entities.get(part + 1).getName());
            if (entities.get(part + 1).isChecked()) {
                tv02.setSelected(true);
            } else {
                tv02.setSelected(false);
            }
            tv02.setTag(entities.get(part + 1));
            tv02.setOnClickListener(this);
        } else {
            tv02.setVisibility(View.INVISIBLE);
        }
        if (part + 2 < entities.size()) {
            tv03.setText(entities.get(part + 2).getName());
            if (entities.get(part + 2).isChecked()) {
                tv03.setSelected(true);
            } else {
                tv03.setSelected(false);
            }
            tv03.setTag(entities.get(part + 2));
            tv03.setOnClickListener(this);
        } else {
            tv03.setVisibility(View.INVISIBLE);
        }
        if (part + 3 < entities.size()) {
            tv04.setText(entities.get(part + 3).getName());
            if (entities.get(part + 3).isChecked()) {
                tv04.setSelected(true);
            } else {
                tv04.setSelected(false);
            }
            tv04.setTag(entities.get(part + 3));
            tv04.setOnClickListener(this);
        } else {
            tv04.setVisibility(View.INVISIBLE);
        }
    }

    public void setServiceSortData(List<ServiceSortModel.DataEntity.ServiceSortTypeEntity> entities, int part) {
        if (part < entities.size()) {
            tv01.setText(entities.get(part).getName());
            if (entities.get(part).isChecked()) {
                tv01.setSelected(true);
            } else {
                tv01.setSelected(false);
            }
            tv01.setTag(entities.get(part));
            tv01.setOnClickListener(this);
        } else {
            tv01.setVisibility(View.INVISIBLE);
        }
        if (part + 1 < entities.size()) {
            tv02.setText(entities.get(part + 1).getName());
            if (entities.get(part + 1).isChecked()) {
                tv02.setSelected(true);
            } else {
                tv02.setSelected(false);
            }
            tv02.setTag(entities.get(part + 1));
            tv02.setOnClickListener(this);
        } else {
            tv02.setVisibility(View.INVISIBLE);
        }
        if (part + 2 < entities.size()) {
            tv03.setText(entities.get(part + 2).getName());
            if (entities.get(part + 2).isChecked()) {
                tv03.setSelected(true);
            } else {
                tv03.setSelected(false);
            }
            tv03.setTag(entities.get(part + 2));
            tv03.setOnClickListener(this);
        } else {
            tv03.setVisibility(View.INVISIBLE);
        }
        if (part + 3 < entities.size()) {
            tv04.setText(entities.get(part + 3).getName());
            if (entities.get(part + 3).isChecked()) {
                tv04.setSelected(true);
            } else {
                tv04.setSelected(false);
            }
            tv04.setTag(entities.get(part + 3));
            tv04.setOnClickListener(this);
        } else {
            tv04.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv01:
                changeChecked(tv01);
                break;
            case R.id.tv02:
                changeChecked(tv02);
                break;
            case R.id.tv03:
                changeChecked(tv03);
                break;
            case R.id.tv04:
                changeChecked(tv04);
                break;
        }
    }

    private void changeChecked(View view) {
        if (view.getTag() instanceof ServiceSortModel.DataEntity.ServiceCategroyEntity) {
            for (ServiceSortModel.DataEntity.ServiceCategroyEntity entity : model.getData().getServiceCategroy()) {
                entity.setChecked(false);
            }
            ((ServiceSortModel.DataEntity.ServiceCategroyEntity) (view.getTag())).setChecked(true);
            adapter.notifyDataSetChanged();
        }
        if (view.getTag() instanceof ServiceSortModel.DataEntity.ServicesEntity) {
            for (ServiceSortModel.DataEntity.ServicesEntity entity : model.getData().getServices()) {
                entity.setChecked(false);
            }
            ((ServiceSortModel.DataEntity.ServicesEntity) (view.getTag())).setChecked(true);
            adapter.notifyDataSetChanged();
        }
        if (view.getTag() instanceof ServiceSortModel.DataEntity.ServiceSortTypeEntity) {
            for (ServiceSortModel.DataEntity.ServiceSortTypeEntity entity : model.getData().getServiceSortType()) {
                entity.setChecked(false);
            }
            ((ServiceSortModel.DataEntity.ServiceSortTypeEntity) (view.getTag())).setChecked(true);
            adapter.notifyDataSetChanged();
        }
    }
}
