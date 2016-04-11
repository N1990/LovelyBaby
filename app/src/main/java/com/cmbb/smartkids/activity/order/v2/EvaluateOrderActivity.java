package com.cmbb.smartkids.activity.order.v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.ImageModel;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.order.model.EvaluateModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.model.OrderStatus;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by javon on 16/3/21.
 */
public class EvaluateOrderActivity extends BaseActivity {

    private static final String TAG = EvaluateOrderActivity.class.getSimpleName();
    private final int PIC_REQUEST_CODE = 1001;
    private final int picNumber = 3;
    private ArrayList<String> urls = new ArrayList<>();
    private SimpleDraweeView ivHomeMyselfHeader;
    private TextView tvNick;
    private TextView tvTag;
    private RatingBar ratingBar;
    private RadioGroup rgEvaluate;
    private RadioButton rbEvaluate01;
    private RadioButton rbEvaluate02;
    private RadioButton rbEvaluate03;
    private RadioButton rbEvaluate04;
    private TextView tvTitleEvaluate;
    private RatingBar ratingBar2;
    private LinearLayout llEvaluate;
    private ImageView ivAddPicPopman;
    private TextView tvSubmit;
    private EditText etEvaluatePeople;
    private EditText etEvaluateService;
    private int eredarId;
    private int serviceId;
    private String orderCode;
    private int eredarEvaluateType = 1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluatev_layout_v2;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        if(getIntent() != null){
            serviceId = getIntent().getIntExtra("service_id", -1);
            orderCode = getIntent().getStringExtra("order_code");
            showWaitDialog();
            EvaluateModel.getEvaluateServicePeoples(serviceId, new OkHttpClientManager.ResultCallback<EvaluateModel>() {
                @Override
                public void onError(Request request, Exception e) {
                    hideWaitDialog();
                    showShortToast(e.toString());
                }

                @Override
                public void onResponse(EvaluateModel response) {
                    hideWaitDialog();
                    eredarId = response.getData().get(0).getUserId();
                    FrescoTool.loadImage(ivHomeMyselfHeader, response.getData().get(0).getUserSmallHeight());
                    ratingBar.setRating(response.getData().get(0).getUserLevel());
                    tvNick.setText(response.getData().get(0).getUserNike());
                    tvTag.setText(response.getData().get(0).getUserRole().get(0).getEredarName());
                }
            });
        }else{
            showShortToast("传参出错啦~");
        }
    }

    private void initView() {
        setTitle(getString(R.string.title_evaluate_order));
        ivHomeMyselfHeader = (SimpleDraweeView) findViewById(R.id.iv_home_myself_header);
        tvNick = (TextView) findViewById(R.id.tv_nick);
        tvTag = (TextView) findViewById(R.id.tv_tag);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        rgEvaluate = (RadioGroup) findViewById(R.id.rg_evaluate);
        rbEvaluate01 = (RadioButton) findViewById(R.id.rb_evaluate_01);
        rbEvaluate02 = (RadioButton) findViewById(R.id.rb_evaluate_02);
        rbEvaluate03 = (RadioButton) findViewById(R.id.rb_evaluate_03);
        rbEvaluate04 = (RadioButton) findViewById(R.id.rb_evaluate_04);
        tvTitleEvaluate = (TextView) findViewById(R.id.tv_title_evaluate);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        llEvaluate = (LinearLayout) findViewById(R.id.ll_evaluate);
        ivAddPicPopman = (ImageView) findViewById(R.id.iv_add_pic_popman);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        etEvaluatePeople = (EditText) findViewById(R.id.et_evaluate);
        etEvaluateService = (EditText) findViewById(R.id.editText);
        rbEvaluate01.setChecked(true);
        ivAddPicPopman.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        rgEvaluate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_evaluate_01:
                        eredarEvaluateType = 1;
                        break;
                    case R.id.rb_evaluate_02:
                        eredarEvaluateType = 2;
                        break;
                    case R.id.rb_evaluate_03:
                        eredarEvaluateType = 3;
                        break;
                    case R.id.rb_evaluate_04:
                        eredarEvaluateType = 4;
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add_pic_popman:
                PhotoPickerIntent intent = new PhotoPickerIntent(this);
                intent.setPhotoCount(picNumber - urls.size());
                startActivityForResult(intent, PIC_REQUEST_CODE);
                break;
            case R.id.iv_pic_item:
                int position = (int) v.getTag();
                PhotoViewActivity.IntentPhotoView(v.getContext(), urls, position);
                break;
            case R.id.iv_pic_delete_item:
                LinearLayout child = (LinearLayout) v.getTag(R.id.iv_pic_item);
                String imgUrl = (String) v.getTag(R.id.iv_pic_delete_item);
                if (llEvaluate != null && llEvaluate.getChildCount() != 0) {
                    urls.remove(imgUrl);
                    llEvaluate.removeView(child);
                }
                if (llEvaluate.getChildCount() < picNumber)
                    ivAddPicPopman.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_submit:
                String evaluatePeopleContent = etEvaluatePeople.getText().toString();
                int evaluateServiceRating = (int)ratingBar2.getRating();
                String evaluateServiceContent = etEvaluateService.getText().toString();
                if(urls == null && urls.size() == 0 && TextUtils.isEmpty(evaluateServiceContent)){
                    showShortToast("请评价服务内容或是上传服务评价图片");
                    return;
                }
                HashMap<String, String> params = new HashMap<>();
                params.put("token", BaseApplication.token);
                params.put("eredarId", eredarId+"");
                params.put("orderCode", orderCode);
                params.put("eredarEvaluateType", eredarEvaluateType + "");
                if(!TextUtils.isEmpty(evaluatePeopleContent)){
                    params.put("eredarEvaluateContent", evaluatePeopleContent);
                }else{
                    params.put("eredarEvaluateContent", etEvaluatePeople.getHint().toString());
                }
                params.put("serviceEvaluateType", evaluateServiceRating+"");
                if(!TextUtils.isEmpty(evaluateServiceContent))
                    params.put("serviceEvaluateContent", evaluateServiceContent);
                ArrayList<ImageModel> files = null;
                if(urls.size() > 0){
                    files = new ArrayList<>();
                    for (String url : urls){
                        ImageModel img = new ImageModel();
                        img.setImgUrl(url);
                        files.add(img);
                    }
                }
                showWaitDialog();
                NetRequest.postRequestWithFiles(Constants.ServiceInfo.EVALUATE_ORDER, params, "evaluateImgList", "imgText", files, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
                    @Override
                    public void onSuccessListener(Object object, String msg) {
                        hideWaitDialog();
                        showShortToast(msg);
//                        Intent intent = getIntent();
//                        intent.putExtra("order_status", OrderStatus.YI_PING_JIA.getValue());
                        setResult(RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onErrorListener(String message) {
                        hideWaitDialog();
                        showShortToast(message);
                    }
                }));
                break;
        }
    }

    private void addPicItem(ArrayList<String> urls) {
        if (urls.size() > 0)
            llEvaluate.setVisibility(View.VISIBLE);
        int index = 0;
        for (String url : urls) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin = TDevice.dip2px(5, this);
            View child = getLayoutInflater().inflate(R.layout.list_image_item, null);
            child.setLayoutParams(params);
            SimpleDraweeView ivPic = (SimpleDraweeView) child.findViewById(R.id.iv_pic_item);
            ImageView ivDel = (ImageView) child.findViewById(R.id.iv_pic_delete_item);
            Log.e(TAG, url);
            Log.e(TAG, Environment.getExternalStorageDirectory().toString());
            FrescoTool.loadLocalImage(ivPic, url);
            ivDel.setOnClickListener(this);
            ivPic.setOnClickListener(this);
            ivDel.setTag(R.id.iv_pic_item, child);
            ivDel.setTag(R.id.iv_pic_delete_item, url);
            ivPic.setTag(index);
            llEvaluate.addView(child);
            index++;
        }
        if (picNumber == llEvaluate.getChildCount()) {
            ivAddPicPopman.setVisibility(View.GONE);
        } else {
            ivAddPicPopman.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PIC_REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> tempUrls = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                addPicItem(tempUrls);
                urls.addAll(tempUrls);
            }
        }
    }

    public static void newInstance(Activity activity, int serviceId, String orderCode, int requestCode){
        Intent intent = new Intent(activity, EvaluateOrderActivity.class);
        intent.putExtra("service_id", serviceId);
        intent.putExtra("order_code", orderCode);
        activity.startActivityForResult(intent, requestCode);
    }

}
