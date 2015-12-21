package com.cmbb.smartkids.activity.diary;

import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.media.upload.UploadListener;
import com.alibaba.sdk.android.media.upload.UploadTask;
import com.alibaba.sdk.android.media.utils.FailReason;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.model.BabyInfoModel;
import com.cmbb.smartkids.activity.diary.model.BabyListModel;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.network.image.ImageUpload;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.Tools;
import com.cmbb.smartkids.utils.log.Log;
import com.cmbb.smartkids.widget.wheelview.CustomDialogBuilder;
import com.cmbb.smartkids.widget.wheelview.DateTimeSelectorDialogBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AddBabyActivity extends BaseActivity implements DateTimeSelectorDialogBuilder.OnSaveListener{
    private final String TAG = AddBabyActivity.class.getSimpleName();
    private final int MODIFY_BABY_HEADER = 11110;
    private final int MODIFY_BABY_NAME = 11111;
    private RelativeLayout mRlHeader, mRlName, mRlBirth, mRlSex;
    private SimpleDraweeView sdvHeader;
    private TextView tvName, tvBirth, tvSex, tvSave;
    private CustomDialogBuilder builder = null;
    private int male = 0; // 1 男 2 女
    private DateTimeSelectorDialogBuilder timeBuilder;
    private  ArrayList<String> tempUrls; // 宝宝头像参数
    private String url, width, height;
    private BabyListModel.DataEntity.RowsEntity babyInfo;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_baby;
    }

    @Override
      protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_baby_material));
        initView();
        initDate();
        addListener();
    }

    private void initView() {
        mRlHeader = (RelativeLayout) findViewById(R.id.rl_addbaby_header);
        sdvHeader = (SimpleDraweeView) findViewById(R.id.iv_addbaby_header);
        mRlName = (RelativeLayout) findViewById(R.id.rl_addbaby_nickname);
        tvName = (TextView) findViewById(R.id.tv_addbaby_nickname);
        mRlBirth = (RelativeLayout) findViewById(R.id.rl_addbaby_birthday);
        tvBirth = (TextView) findViewById(R.id.tv_addbaby_birthday);
        mRlSex = (RelativeLayout) findViewById(R.id.rl_addbaby_sex);
        tvSex = (TextView) findViewById(R.id.tv_addbaby_sex);
        tvSave = (TextView) findViewById(R.id.tv_addbaby_save);
    }

    private void initDate() {
        if(getIntent() != null){
            babyInfo = getIntent().getParcelableExtra("baby_info");
            if(babyInfo != null){
                FrescoTool.loadImage(sdvHeader, babyInfo.getBabyImg());
                tempUrls = new ArrayList<>();
                url = babyInfo.getBabyImg();
                width = babyInfo.getBabyImgWidth();
                height = babyInfo.getBabyImgheigth();
                tvName.setText(babyInfo.getBabyNike());
                if(Integer.parseInt(babyInfo.getBabySex()) == 1){
                    male = 1;
                    tvSex.setText("男");
                    tvSex.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.btn_male_boy_bg), null, null, null);
                    tvSex.setTag(babyInfo.getBabySex());
                }else{
                    male = 2;
                    tvSex.setText("女");
                    tvSex.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.btn_male_girl_bg), null, null, null);
                    tvSex.setTag(babyInfo.getBabySex());
                }
                try {
                    String birthday = Tools.DataToString(babyInfo.getBabyBirthday(), "yyyy-MM-dd");
                    tvBirth.setText(birthday);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void addListener() {
        mRlHeader.setOnClickListener(this);
        mRlName.setOnClickListener(this);
        mRlBirth.setOnClickListener(this);
        mRlSex.setOnClickListener(this);
        tvSave.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_addbaby_header:
                PhotoPickerIntent intent = new PhotoPickerIntent(this);
                intent.setPhotoCount(1);
                startActivityForResult(intent, MODIFY_BABY_HEADER);
                break;
            case R.id.rl_addbaby_nickname:
                Intent modifyName = new Intent(AddBabyActivity.this, ModifyBabyNameActivity.class);
                String name = tvName.getText().toString();
                modifyName.putExtra("baby_name", name);
                startActivityForResult(modifyName, MODIFY_BABY_NAME);
                break;
            case R.id.rl_addbaby_birthday:
                if (timeBuilder == null) {
                    timeBuilder = DateTimeSelectorDialogBuilder.getInstance(this, System.currentTimeMillis()+"");
                    timeBuilder.setOnSaveListener(this);
                }
                timeBuilder.show();
                break;
            case R.id.rl_addbaby_sex:
                showChooseMaleDialog();
                break;
            case R.id.tv_addbaby_save:
                String babyName = tvName.getText().toString();
                String birthday = tvBirth.getText().toString();
                String sex = tvSex.getText().toString();
                if(TextUtils.isEmpty(babyName)){
                    showShortToast("请输入您的宝宝名字");
                    return;
                }
                if(TextUtils.isEmpty(birthday)){
                    showShortToast("请选择您宝宝的生日");
                    return;
                }
                if(TextUtils.isEmpty(sex)){
                    showShortToast("请选择您宝宝的性别");
                    return;
                }
                verifyHeaderUrl(babyName, birthday, String.valueOf(male));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(MODIFY_BABY_NAME == requestCode && resultCode == RESULT_OK){
            String name = data.getStringExtra("baby_name");
            tvName.setText(name);
        }else if(MODIFY_BABY_HEADER == requestCode && resultCode == RESULT_OK){
            if (data != null) {
                tempUrls = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                Log.e(TAG, "pic path:" + tempUrls.get(0));
                FrescoTool.loadLocalImage(sdvHeader, tempUrls.get(0));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (builder != null) {
            builder.setDialogDismiss();
        }
        if (timeBuilder != null)
            timeBuilder.setDialogDismiss();
    }


    private void showChooseMaleDialog(){
        View view = getLayoutInflater().inflate(R.layout.view_sex_selected, null);
        RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg_sex);
        if (male == 2) {
            ((RadioButton) view.findViewById(R.id.rb_sex_woman)).setChecked(true);
        } else {
            ((RadioButton) view.findViewById(R.id.rb_sex_man)).setChecked(true);
        }
        rg.setOnCheckedChangeListener(rgListener);
        int width = TDevice.getScreenWidth(this) * 3 / 4;
        builder = CustomDialogBuilder.getInstance(this).setDialogWindows(width, ViewGroup.LayoutParams.WRAP_CONTENT).isCancelableOnTouchOutside(false);
        builder.withTitle("更改性别")
                .withMessageMiss(View.GONE)
                .withCustomContentView(view, this)
                .withCancelText("取消", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        Object obj = tvSex.getTag();
                        if(obj != null)
                            male = Integer.parseInt((String)obj);
                        builder.dismiss();
                    }
                }).withComfirmText("确定", new CustomListener.DialogListener() {
            @Override
            public void onClick(View v) {
                if(male == 1){
                    tvSex.setText("男");
                    tvSex.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.btn_male_boy_bg), null, null, null);
                }else{
                    tvSex.setText("女");
                    tvSex.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.btn_male_girl_bg), null, null, null);
                }
               tvSex.setTag(male+"");
            }
        });
        builder.show();
    }

    private RadioGroup.OnCheckedChangeListener rgListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rb_sex_man) {
                male = 1;
            } else if (checkedId == R.id.rb_sex_woman) {
                male = 2;
            }
        }
    };

    @Override
    public void onSaveSelectedDate(String selectedDate) {
        if(!TextUtils.isEmpty(selectedDate))
            tvBirth.setText(selectedDate);
    }

    private void verifyHeaderUrl(final String name, final String birthday, final String male){
        if(tempUrls == null){
            showShortToast("请选择您宝宝的头像");
            return;
        }
        showWaitDialog();
        if(tempUrls.size() != 0){
            ImageUpload.getInstance().uploadImages(AddBabyActivity.this, tempUrls, new UploadListener() {
                @Override
                public void onUploading(UploadTask uploadTask) {

                }

                @Override
                public void onUploadFailed(UploadTask uploadTask, FailReason failReason) {
                    hideWaitDialog();
                    showShortToast(failReason.getMessage());
                }

                @Override
                public void onUploadComplete(UploadTask uploadTask) {
                    try {
                        JSONObject object = new JSONObject(uploadTask.getResult().message);
                        url = object.optString("url");
                        width = object.optString("returnBody").split("_")[0];
                        height = object.optString("returnBody").split("_")[1];
                        Log.e(TAG, "url : " + url + ", width : " + width + ", height : " + height);
                        Log.i("uploadTask", "uploadTask = " + tempUrls.size());
                        Log.i(TAG, "uploadTask = " + uploadTask.getResult().message);
                        if(TextUtils.isEmpty(url)){
                            showShortToast("头像验证失败");
                            throw new NullPointerException();
                        }else{
                            handleRequest(url, width, height, name, male, birthday);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onUploadCancelled(UploadTask uploadTask) {
                    hideWaitDialog();
                }
            });
        }else{
            handleRequest(url, width, height, name, male, birthday);
        }

    }



    private void handleRequest(String headerImg, String width, String height, String name, String male, final String birthday){
        HashMap<String, String> params = new HashMap<>();
        if(babyInfo != null)
        params.put("id", String.valueOf(babyInfo.getId()));
        params.put("babyNike", name);
        params.put("babySex", male);
        params.put("babyBirthday", birthday);
        params.put("babyImg", headerImg);
        params.put("babyImgWidth", width);
        params.put("babyImgHeight", height);
        NetRequest.postRequest(Constants.ServiceInfo.CREATE_BABY, BaseApplication.token, params, BabyInfoModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                BabyInfoModel data = (BabyInfoModel) object;
                if(babyInfo == null){
                    setResult(RESULT_OK);
                    finish();
                }else{
                    Intent intent = getIntent();
                    babyInfo.setBabyImg(data.getData().getBabyImg());
                    babyInfo.setBabyImgWidth(data.getData().getBabyImgWidth());
                    babyInfo.setBabyImgheigth(data.getData().getBabyImgHeight());
                    babyInfo.setBabyNike(data.getData().getBabyNike());
                    babyInfo.setAge(data.getData().getAge());
                    babyInfo.setBabyBirthday(data.getData().getBabyBirthday());
                    babyInfo.setBabySex(data.getData().getBabySex());
                    intent.putExtra("baby_info", babyInfo);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                showShortToast(msg);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }



}
