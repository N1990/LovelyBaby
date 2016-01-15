package com.javon.loadmorerecyclerview;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 项目名称：LoadMoreRecyclerView
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 13:19
 */
public class FooterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView ivReload;
    private TextView tvTip;
    private ContentLoadingProgressBar pb;
    private BaseRecyclerAdapter adapter;

    public FooterViewHolder(BaseRecyclerAdapter adapter, View itemView) {
        super(itemView);
        this.adapter = adapter;
        ivReload = (ImageView) itemView.findViewById(R.id.iv_no_data_footer);
        ivReload.setOnClickListener(this);
        tvTip = (TextView) itemView.findViewById(R.id.tv_load_more_footer);
        pb = (ContentLoadingProgressBar) itemView.findViewById(R.id.pb_load_more_footer);
        setFooterNoData();
    }

    @Override
    public void onClick(View v) {
        if(adapter.getFooterClick() != null)
            adapter.getFooterClick().onClick(v);
    }

    public void setFooterError(){
        ivReload.setVisibility(View.VISIBLE);
        tvTip.setText("网络出错啦~请检查您的网络");
        pb.setVisibility(View.GONE);
    }

    public void setFooterNoData(){
        ivReload.setVisibility(View.GONE);
        tvTip.setText(""); //没有更多了~
        pb.setVisibility(View.GONE);
//        Log.e("BaseRecyclerAdapter", "i come here");
    }

    public void setLoadMore(){
        ivReload .setVisibility(View.GONE);
        tvTip.setText("正在加载...");
        pb.setVisibility(View.VISIBLE);
    }


    public void setFooterEmpty(){
        ivReload.setVisibility(View.GONE);
        tvTip.setText(""); //没有更多了~
        pb.setVisibility(View.GONE);
//        Log.e("BaseRecyclerAdapter", "i come here");
    }



}
