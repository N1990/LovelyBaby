package com.cmbb.smartkids.activity.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.home.holder.CommunityHolder;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 11:58
 */
public class CommunityAdapter extends BaseRecyclerAdapter {
    private final int HEADER = -1;


    private CustomListener.ItemClickListener onItemListener;
    private View.OnClickListener onHeaderListener;

    @Override
    public int getItemViewType(int position) {
        if (dataList != null) { //  && dataList.size() != 0
            if (position == dataList.size() + 1) {
                return FOOTER;
            } else if (position == 0) {
                return HEADER;
            } else {
                return NORMAL;
            }
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        if (dataList == null && dataList.size() == 0) {
            return 0;
        } else {
            return dataList.size() + 2;
        }
    }

    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {
        if (HEADER == viewType) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_community_list_header, parent, false);
            return new HeaderHolder(root, this);
        } else {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_community_item, parent, false);
            return new CommunityHolder(root);
        }
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommunityHolder) {
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (position != dataList.size() + 1 && position != 0)
        if (holder instanceof CommunityHolder) {
            ((CommunityHolder) holder).setData((TopicListModel.DataEntity.RowsEntity) dataList.get(position - 1), this, position - 1);
        }
    }

    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public View.OnClickListener getOnHeaderListener() {
        return onHeaderListener;
    }

    public void setOnHeaderListener(View.OnClickListener onHeaderListener) {
        this.onHeaderListener = onHeaderListener;
    }

    class HeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RelativeLayout rl;
        private CommunityAdapter adapter;

        public HeaderHolder(View itemView) {
            super(itemView);
        }

        public HeaderHolder(View itemView, CommunityAdapter adapter) {
            this(itemView);
            this.adapter = adapter;
            rl = (RelativeLayout) itemView.findViewById(R.id.rl_community_item_header);
            rl.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (adapter.getOnHeaderListener() != null)
                adapter.getOnHeaderListener().onClick(v);
        }
    }
}
