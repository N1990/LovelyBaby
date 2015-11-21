package com.cmbb.smartkids.activity.user.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.model.EvaluateListModel;
import com.cmbb.smartkids.activity.user.adapter.MyPerssionAdapter;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.Tools;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/15 11:02
 */
public class MyPerssionHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private View root;
    private SimpleDraweeView ivHeader;
    private TextView tvNickname, tvIdentity, tvTitle, tvTime;
    private MyPerssionAdapter adapter;
    private int position;

    public MyPerssionHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        ivHeader = (SimpleDraweeView) itemView.findViewById(R.id.iv_header_perssion_item);
        tvNickname = (TextView) itemView.findViewById(R.id.tv_nickname_perssion_item);
        tvIdentity = (TextView) itemView.findViewById(R.id.tv_identity_perssion_item);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title_perssion_item);
        tvTime = (TextView) itemView.findViewById(R.id.tv_time_perssion_item);
    }

    public void setData(MyPerssionAdapter adapter, int position, EvaluateListModel.DataEntity.RowsEntity data){
        this.adapter = adapter;
        this.position = position;
        FrescoTool.loadImage(ivHeader, data.getUserBasicInfo().getUserSmallImg(), TDevice.dip2px(60, ivHeader.getContext()) + "");
        if(TextUtils.isEmpty(data.getUserBasicInfo().getUserNike())){
            tvNickname.setText(data.getUserBasicInfo().getUserNike());
        }else{
            tvNickname.setText(data.getUserBasicInfo().getUserPhone());
        }
        tvIdentity.setText(data.getUserBasicInfo().getUserRole().get(0).getEredarName());
        tvTitle.setText(data.getTitle() + "(" + data.getEvaluateContent() + ")");
        try{
           tvTime.setText(Tools.DataToString(data.getEvaluateDate(), "yyyy-MM-dd"));
        }catch (Exception e){
            e.printStackTrace();
        }
        ivHeader.setTag(data.getUserBasicInfo().getUserId());
        tvNickname.setTag(data.getUserBasicInfo().getUserId());
        tvNickname.setOnClickListener(this);
        ivHeader.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(adapter.getOnHeaderListener() != null)
            adapter.getOnHeaderListener().onItemClick(v, position, v.getTag());
    }
}
