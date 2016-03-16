package com.cmbb.smartkids.activity.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.holder.HomeAdHolder;
import com.cmbb.smartkids.activity.home.holder.HomeGridHolder;
import com.cmbb.smartkids.activity.home.holder.HomeItemHolder;
import com.cmbb.smartkids.activity.home.model.BannerModel;
import com.cmbb.smartkids.activity.home.model.HomePageRootModel;
import com.cmbb.smartkids.activity.home.model.RankEredarModel;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 13:31
 */
public class HomeFraAdapter extends BaseRecyclerAdapter {

    private CustomListener.ItemClickListener onAdItemClick;
    private CustomListener.ItemClickListener onGridItemClick;
    private CustomListener.ItemClickListener onItemClick;
    private List<BannerModel.DataEntity> adData;
    private ArrayList<RankEredarModel> popManData;

    public void setHeaderData(List<BannerModel.DataEntity> adData, ArrayList<RankEredarModel> popManData) {
        if (adData != null) {
            this.adData = adData;
        } else {
            this.adData = new ArrayList<>();
        }
        if (popManData != null) {
            this.popManData = popManData;
        } else {
            this.popManData = new ArrayList<>();
        }
        notifyDataSetChanged();
    }


    @Override
    public void addData(List dataList, LoadMoreRecyclerView view) {
        super.addData(dataList, view);
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList != null && dataList.size() != 0) {
            if (position == 0) {
                return 0;
            } else if (position == 1) {
                return 1;
            } else if (position == dataList.size() + 2) {
                return FOOTER;
            } else {
                return NORMAL;
            }
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 0) {
            v = inflater.inflate(R.layout.view_home_fragment_ad, parent, false);
            return new HomeAdHolder(parent.getContext(), v);
        } else if (viewType == 1) {
            v = inflater.inflate(R.layout.view_home_fragment_grid, parent, false);
            return new HomeGridHolder(parent.getContext(), v);
        } else {
            v = inflater.inflate(R.layout.list_home_fragment_item, parent, false);
            return new HomeItemHolder(parent.getContext(), v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position != dataList.size() + 2)
            onBindCustomViewHolder(holder, position);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((HomeAdHolder) holder).setData(adData, this);
        } else if (position == 1) {
            ((HomeGridHolder) holder).setData(popManData, this);
        } else {
            HomePageRootModel.DataEntity.RowsEntity row = (HomePageRootModel.DataEntity.RowsEntity) dataList.get(position - 2);
            ((HomeItemHolder) holder).setData(row, position, this);
        }
    }

    @Override
    public int getItemCount() {
        if (dataList.size() != 0) {
            return dataList.size() + 3;
        } else {
            return 0;
        }
    }

    public CustomListener.ItemClickListener getOnAdItemClick() {
        return onAdItemClick;
    }

    public void setOnAdItemClick(CustomListener.ItemClickListener onAdItemClick) {
        this.onAdItemClick = onAdItemClick;
    }

    public CustomListener.ItemClickListener getOnGridItemClick() {
        return onGridItemClick;
    }

    public void setOnGridItemClick(CustomListener.ItemClickListener onGridItemClick) {
        this.onGridItemClick = onGridItemClick;
    }

    public CustomListener.ItemClickListener getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(CustomListener.ItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }
}
