package com.cmbb.smartkids.activity.order.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.holder.PayWayHolder;
import com.cmbb.smartkids.activity.order.model.PayWayModel;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.FooterViewHolder;

import java.util.ArrayList;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/22 19:45
 */
public class PayWayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEADER = -1;
    private final int NORMAL = -2;
    private final int FOOTER = -3;
    private ArrayList<PayWayModel.PayTypesEntity> data;
    private ArrayList<String> headerData;
    private View.OnClickListener onBottomListener;
    private CustomListener.ItemClickListener onItemClick;
    private int selectPos = 0;
    private String price;

    public void setData(ArrayList<String> headerData, ArrayList<PayWayModel.PayTypesEntity> data){
        if(headerData != null){
            this.headerData = headerData;
        }else{
            this.headerData = new ArrayList<>();
        }
        if(data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public ArrayList<PayWayModel.PayTypesEntity> getData(){
        return this.data;
    }


    public View.OnClickListener getOnBottomListener() {
        return onBottomListener;
    }

    public void setOnBottomListener(View.OnClickListener onBottomListener) {
        this.onBottomListener = onBottomListener;
    }

    public CustomListener.ItemClickListener getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(CustomListener.ItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }

    public int getSelectPos() {
        return selectPos;
    }

    public void setSelectPos(int selectPos) {
        this.selectPos = selectPos;
        notifyDataSetChanged();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == HEADER){
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pay_way_header, parent, false);
            return new HeaderHolder(root);
        }else if(viewType == FOOTER){
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pay_way_item_footer, parent, false);
            return new FooterHolder(root);
        }else{
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pay_way_item, parent, false);
            return new PayWayHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderHolder){
            ((HeaderHolder) holder).setData(headerData);
        }else if(holder instanceof FooterHolder){
            ((FooterHolder) holder).setData(this);
        }else{
            ((PayWayHolder) holder).setData(this, position - 1, data.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 2;
    }

    class HeaderHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle, tvPrice, tvLeave;
        private View root;

        public HeaderHolder(View itemView) {
            super(itemView);
            this.root = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_pay_way_title_item);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_pay_way_price_item);
            tvLeave = (TextView) itemView.findViewById(R.id.tv_pay_way_leave_item);
        }

        public void setData(ArrayList<String> data){
            if(data != null && data.size() > 0){
                tvTitle.setText("订单名称：" + data.get(0));
                tvPrice.setText("订单金额：" + data.get(1));
                tvLeave.setText(data.get(1) + "元");
            }else{
                this.root.setVisibility(View.GONE);
            }
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private PayWayAdapter adapter;

        public FooterHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void setData(PayWayAdapter adapter){
            this.adapter = adapter;
        }

        @Override
        public void onClick(View v) {
            if(adapter.getOnBottomListener() != null)
                adapter.getOnBottomListener().onClick(v);
        }
    }
}
