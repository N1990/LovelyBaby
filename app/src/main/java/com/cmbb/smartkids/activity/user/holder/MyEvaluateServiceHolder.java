package com.cmbb.smartkids.activity.user.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.MyEvaluateServiceAdapter;
import com.cmbb.smartkids.activity.user.model.EvaluateServiceModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.Tools;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;

/**
 * Created by javon on 16/3/21.
 */
public class MyEvaluateServiceHolder extends BaseViewHolder<EvaluateServiceModel.DataEntity.RowsEntity> {

    private SimpleDraweeView sdvEvaluateServiceItem;
    private TextView tvEvaluateTimeItem;
    private TextView tvEvaluateServiceNicknameItem;
    private RatingBar rbEvaluateServiceItem;
    private TextView tvEvaluateServiceContent;
    private SimpleDraweeView ivEvaluateServiceFirstItem;
    private SimpleDraweeView ivEvaluateServiceSecondItem;
    private SimpleDraweeView ivEvaluateServiceThirdItem;
    private SimpleDraweeView ivEvaluateServiceTitleItem;
    private RelativeLayout rlService;
    private TextView tvEvaluateServiceTitleItem;
    private TextView tvEvaluateServicePriceItem;
    private TextView tvEvaluateServiceCity;
    private MyEvaluateServiceAdapter adapter;

    public MyEvaluateServiceHolder(ViewGroup parent, MyEvaluateServiceAdapter adapter) {
        super(parent, R.layout.list_service_evaluate_item);
        this.adapter = adapter;
        sdvEvaluateServiceItem = $(R.id.sdv_evaluate_service_item);
        tvEvaluateTimeItem = $(R.id.tv_evaluate_time_item);
        tvEvaluateServiceNicknameItem = $(R.id.tv_evaluate_service_nickname_item);
        rbEvaluateServiceItem = $(R.id.rb_evaluate_service_item);
        tvEvaluateServiceContent = $(R.id.tv_evaluate_service_content);
        ivEvaluateServiceFirstItem = $(R.id.iv_evaluate_service_first_item);
        ivEvaluateServiceSecondItem = $(R.id.iv_evaluate_service_second_item);
        ivEvaluateServiceThirdItem = $(R.id.iv_evaluate_service_third_item);
        ivEvaluateServiceTitleItem = $(R.id.iv_evaluate_service_title_item);
        tvEvaluateServiceTitleItem = $(R.id.tv_evaluate_service_title_item);
        tvEvaluateServicePriceItem = $(R.id.tv_evaluate_service_price_item);
        tvEvaluateServiceCity = $(R.id.tv_evaluate_city_item);
        rlService = $(R.id.rl_evaluate_service_item);
    }

    @Override
    public void setData(final EvaluateServiceModel.DataEntity.RowsEntity data, final int position) {
        super.setData(data, position);
        FrescoTool.loadImage(sdvEvaluateServiceItem, data.getUserBasicInfo().getUserSmallImg());
        tvEvaluateServiceNicknameItem.setText(data.getUserBasicInfo().getUserNike());
        rbEvaluateServiceItem.setRating(data.getEvaluateType());
        try {
            String date = Tools.DataToString(data.getEvaluateDate(), "yyyy年MM月dd日");
            tvEvaluateTimeItem.setText(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(TextUtils.isEmpty(data.getEvaluateContent())){
            tvEvaluateServiceContent.setVisibility(View.GONE);
        }else{
            tvEvaluateServiceContent.setVisibility(View.VISIBLE);
            tvEvaluateServiceContent.setText(data.getEvaluateContent());
        }
        if(TextUtils.isEmpty(data.getFirstImg())){
            ivEvaluateServiceFirstItem.setVisibility(View.GONE);
        }else{
            ivEvaluateServiceFirstItem.setVisibility(View.VISIBLE);
            FrescoTool.loadImage(ivEvaluateServiceFirstItem, data.getFirstImg(), Float.parseFloat(data.getFirstImgWidth()) / Float.parseFloat(data.getFirstImgHeight()));
        }

        if(TextUtils.isEmpty(data.getSecondImg())){
            ivEvaluateServiceSecondItem.setVisibility(View.GONE);
        }else{
            ivEvaluateServiceSecondItem.setVisibility(View.VISIBLE);
            FrescoTool.loadImage(ivEvaluateServiceSecondItem, data.getSecondImg(), Float.parseFloat(data.getSecondImgWidth()) / Float.parseFloat(data.getSecondImgHeight()));
        }

        if(TextUtils.isEmpty(data.getThirdImg())){
            ivEvaluateServiceThirdItem.setVisibility(View.GONE);
        }else{
            ivEvaluateServiceThirdItem.setVisibility(View.VISIBLE);
            FrescoTool.loadImage(ivEvaluateServiceThirdItem, data.getThirdImg(), Float.parseFloat(data.getThirdImgWidth()) / Float.parseFloat(data.getThirdImgHeight()));
        }

        FrescoTool.loadImage(ivEvaluateServiceTitleItem, data.getServiceBasicInfo().getServicesImg(), 1.67f);
        tvEvaluateServiceTitleItem.setText(data.getServiceBasicInfo().getTitle());
        tvEvaluateServicePriceItem.setText("¥" + data.getServiceBasicInfo().getPrice() + "/人");
        tvEvaluateServiceCity.setText(data.getServiceBasicInfo().getCityText());
        rlService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.getOnServiceListener() != null)
                    adapter.getOnServiceListener().onItemClick(v, position, data);
            }
        });
    }
}
