package com.cmbb.smartkids.activity.serve.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.model.ActiveDetailModel;
import com.cmbb.smartkids.activity.serve.holder.ActiveHolder;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.utils.log.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javon on 15/12/9.
 */
public class ActiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEADER = -1;
    private final int NORMAL = -2;
    private final int FOOTER = -3;
    private List<ActiveDetailModel.DataEntity.ServiceImgListEntity> data;
    private String title, content;
    private boolean isOrder;
    private View.OnClickListener onFooterListener;
    private CustomListener.ItemClickListener onItemImgListener;


    public void setData(List<ActiveDetailModel.DataEntity.ServiceImgListEntity> data, String title, String content, boolean isOrder){
        this.title = title;
        this.content  = content;
        this.isOrder = isOrder;
        if(data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }


    public void setIsOrder(boolean isOrder){
        this.isOrder = isOrder;
        notifyItemChanged(data.size() + 1);
    }


    public View.OnClickListener getOnFooterListener() {
        return onFooterListener;
    }

    public void setOnFooterListener(View.OnClickListener onFooterListener) {
        this.onFooterListener = onFooterListener;
    }

    public CustomListener.ItemClickListener getOnItemImgListener() {
        return onItemImgListener;
    }

    public void setOnItemImgListener(CustomListener.ItemClickListener onItemImgListener) {
        this.onItemImgListener = onItemImgListener;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return HEADER;
        }else if(position == data.size() + 1){
            return FOOTER;
        }else{
            return NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = null;
        if(viewType == HEADER){
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_active_detail_header, parent, false);
            return new ActiveHeaderHolder(root);
        }else if(viewType == FOOTER){
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_active_detail_footer, parent, false);
            return new ActiveFooterHolder(root);
        }else{
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_active_detail_item, parent, false);
            return new ActiveHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ActiveHeaderHolder){
            Log.e("activeAdapter", "i come here0");
            ((ActiveHeaderHolder) holder).setData(title, content);
        }else if(holder instanceof ActiveFooterHolder){
            Log.e("activeAdapter", "i come here1");
            ((ActiveFooterHolder) holder).setData(isOrder, this);
        }else{
            Log.e("activeAdapter", "i come here2");
            ((ActiveHolder) holder).setData(data.get(position - 1), this, position - 1);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 2;
    }

    class ActiveHeaderHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle, tvContent;

        public ActiveHeaderHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_active_detail_title_header);
            tvContent = (TextView) itemView.findViewById(R.id.tv_active_detail_content_header);
        }

        private void setData(String title, String content){
            if(!TextUtils.isEmpty(title))
                tvTitle.setText(title);
            if(!TextUtils.isEmpty(content))
                tvContent.setText(content);
        }
    }

    class ActiveFooterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvOrder;
        private ActiveAdapter adapter;

        public ActiveFooterHolder(View itemView) {
            super(itemView);
            tvOrder = (TextView) itemView.findViewById(R.id.tv_active_detail_order_footer);
        }

        public void setData(boolean isOrder, ActiveAdapter adapter) {
            this.adapter = adapter;
            if (isOrder) {
                tvOrder.setEnabled(false);
                tvOrder.setBackgroundResource(R.drawable.btn_service_detail_order_disenable);
                tvOrder.setText("不可预定");
            } else {
                tvOrder.setEnabled(true);
                tvOrder.setBackgroundResource(R.drawable.btn_service_detail_order_enable);
                tvOrder.setText("预定");
            }
            tvOrder.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (adapter.getOnFooterListener() != null)
                adapter.getOnFooterListener().onClick(v);
        }
    }


}
