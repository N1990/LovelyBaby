package com.cmbb.smartkids.activity.order.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.holder.EvaluationHolder;
import com.cmbb.smartkids.activity.order.model.EvaluateModel;
import com.cmbb.smartkids.base.CustomListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/20 14:58
 */
public class EvaluationAdapter extends RecyclerView.Adapter<EvaluationHolder> {
    private final String TAG = EvaluationAdapter.class.getSimpleName();
    private CustomListener.ItemClickListener onHandlerListener;
    private CustomListener.ItemClickListener onHeaderListener;

    private List<EvaluateModel.DataEntity> data;

    public List<EvaluateModel.DataEntity> getData() {
        return data;
    }

    public void setData(List<EvaluateModel.DataEntity> data) {
        if(data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public CustomListener.ItemClickListener getOnHandlerListener() {
        return onHandlerListener;
    }

    public void setOnHandlerListener(CustomListener.ItemClickListener onHandlerListener) {
        this.onHandlerListener = onHandlerListener;
    }


    public CustomListener.ItemClickListener getOnHeaderListener() {
        return onHeaderListener;
    }

    public void setOnHeaderListener(CustomListener.ItemClickListener onHeaderListener) {
        this.onHeaderListener = onHeaderListener;
    }

    @Override
    public EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_evaluate_item, parent, false);
        return new EvaluationHolder(root);
    }

    @Override
    public void onBindViewHolder(EvaluationHolder holder, int position) {
        holder.setData(this, position, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
