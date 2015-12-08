package com.cmbb.smartkids.activity.serve.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.model.ActiveDetailModel;
import com.cmbb.smartkids.activity.serve.adapter.ServiceUserAdapter;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/21 13:52
 */
public class ServiceUserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private SimpleDraweeView ivHeader;
    private TextView tvName, tvIdentity, tvFan;
    private RatingBar rb;
    private View root;
    private ServiceUserAdapter adapter;
    private int position;
    public ServiceUserHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        ivHeader = (SimpleDraweeView) itemView.findViewById(R.id.iv_user_center_header);
        tvName = (TextView) itemView.findViewById(R.id.tv_user_center_nickename);
        tvIdentity = (TextView) itemView.findViewById(R.id.tv_user_center_identity);
        tvFan = (TextView) itemView.findViewById(R.id.tv_user_center_fan);
        rb = (RatingBar) itemView.findViewById(R.id.rb_user_center_perssion);

    }

    public void setData(ServiceUserAdapter adapter, int position, ActiveDetailModel.DataEntity.UserInfoListEntity obj){
        this.adapter = adapter;
        this.position = position;
        FrescoTool.loadImage(ivHeader, obj.getUserSmallImg());
        tvName.setText(obj.getUserNike());
        tvIdentity.setText(obj.getUserRole().get(0).getEredarName());
        tvFan.setText("Fans("+obj.getFans()+")");
        rb.setRating(obj.getUserLevel());
        root.setOnClickListener(this);
        root.setTag(obj);
    }

    @Override
    public void onClick(View v) {
        if(adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, root.getTag());
    }
}
