package com.cmbb.smartkids.activity.order.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.adapter.EvaluationAdapter;
import com.cmbb.smartkids.activity.order.model.EvaluateModel;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/20 14:59
 */
public class EvaluationHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{
    private EvaluationAdapter adapter;
    private SimpleDraweeView ivHeader;
    private TextView tvNickName, tvIdentity;
    private RadioGroup rp;
    private int position;

    public EvaluationHolder(View itemView) {
        super(itemView);
         ivHeader = (SimpleDraweeView) itemView.findViewById(R.id.iv_perssion_header);
         tvNickName = (TextView) itemView.findViewById(R.id.tv_perssion_nickname);
         tvIdentity = (TextView) itemView.findViewById(R.id.tv_perssion_identity);
         rp = (RadioGroup) itemView.findViewById(R.id.rp_perssion);
    }

    public void setData(EvaluationAdapter adapter, int position, EvaluateModel.DataEntity obj){
        this.adapter = adapter;
        this.position = position;
        FrescoTool.loadImage(ivHeader, obj.getUserSmallImg(), obj.getUserSmallWidth(), obj.getUserSmallHeight());
        if(!TextUtils.isEmpty(obj.getUserNike())){
            tvNickName.setText(obj.getUserNike());
        }else{
            tvNickName.setText(obj.getUserPhone());
        }
        tvIdentity.setText(obj.getUserRole().get(0).getEredarName());
        ivHeader.setOnClickListener(this);
        tvNickName.setOnClickListener(this);
        ivHeader.setTag(obj);
        tvNickName.setTag(obj);
        rp.setOnCheckedChangeListener(this);
        rp.setTag(position);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int position = (int) group.getTag();
        switch (checkedId){
            case R.id.rb_perssion_common:
                adapter.getData().get(position).setEvaluateLevel(4);
                adapter.getData().get(position).setEvaluateContent("一般");
                break;
            case R.id.rb_perssion_satisfaction:
                adapter.getData().get(position).setEvaluateLevel(3);
                adapter.getData().get(position).setEvaluateContent("满意");
                break;
            case R.id.rb_perssion_very_satisfaction:
                adapter.getData().get(position).setEvaluateLevel(2);
                adapter.getData().get(position).setEvaluateContent("非常满意");
                break;
            case R.id.rb_perssion_recommend:
                adapter.getData().get(position).setEvaluateLevel(1);
                adapter.getData().get(position).setEvaluateContent("强力推荐");
                break;

        }
        if (adapter.getOnHandlerListener() != null)
            adapter.getOnHandlerListener().onItemClick(group, position, group.getTag());
    }

    @Override
    public void onClick(View v) {
        if(adapter.getOnHeaderListener() != null)
            adapter.getOnHeaderListener().onItemClick(v, position, v.getTag());
    }
}
