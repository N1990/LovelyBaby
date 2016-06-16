package com.cmbb.smartkids.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.media.upload.UploadListener;
import com.alibaba.sdk.android.media.upload.UploadTask;
import com.alibaba.sdk.android.media.utils.FailReason;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.adapter.PopmanSortAdapter;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.model.PopmanSortModel;
import com.cmbb.smartkids.model.TeletextModel;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.network.image.ImageUpload;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.utils.CustomWatcher;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.MyTimeCount;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.Tools;
import com.cmbb.smartkids.utils.log.Log;
import com.cmbb.smartkids.widget.spinner.NiceSpinner;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApplyPopmanActivity extends BaseActivity implements TextWatcher {

    private final String TAG = ApplyPopmanActivity.class.getSimpleName();
    private final int PIC_REQUEST_CODE = 1001;
    private final int picNumber = 5;
    private LinearLayout llPicContent;
    private TextView tvVerifyCode, tvIntroduce;
    private EditText etRealName, etIdentity, etPhone, etVerifyCode, etIntroduce;
    private ImageView ivRealNameClear, ivIdentityClear, ivPhoneClear, ivAddPic;
    private NiceSpinner ns;
    private int realLen = 0;
    private ArrayList<String> urls = new ArrayList<>();
    private MyTimeCount timeCount;
    private TeletextModel upData;
    private PopmanSortAdapter adapter;
    private int sortValue = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_popman;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView() {
        setTitle("达人申请");
        etRealName = (EditText) findViewById(R.id.et_real_name_popman);
        ivRealNameClear = (ImageView) findViewById(R.id.iv_real_name_popman_clear);
        etIdentity = (EditText) findViewById(R.id.et_identity_popman);
        ivIdentityClear = (ImageView) findViewById(R.id.iv_identity_popman_clear);
        etPhone = (EditText) findViewById(R.id.et_phone_popman);
        ivPhoneClear = (ImageView) findViewById(R.id.iv_phone_popman_clear);
        etVerifyCode = (EditText) findViewById(R.id.et_verify_code_popman);
        tvVerifyCode = (TextView) findViewById(R.id.tv_verify_phone_code_popman);
        ns = (NiceSpinner) findViewById(R.id.ns_sort_popman);
        etIntroduce = (EditText) findViewById(R.id.et_introduce_popman);
        tvIntroduce = (TextView) findViewById(R.id.tv_introduce_popman);
        ivAddPic = (ImageView) findViewById(R.id.iv_add_pic_popman);
        llPicContent = (LinearLayout) findViewById(R.id.ll_pic_popman);
        adapter = new PopmanSortAdapter(this);
        timeCount = new MyTimeCount(60000, 1000, tvVerifyCode);
        upData = new TeletextModel();
        List<TeletextModel.ImgsEntity> data = new ArrayList<>();
        upData.setImageFiles(data);
    }

    private void initData() {
        requestPopmanSort();
    }

    private void addListener() {
        new CustomWatcher(etRealName, ivRealNameClear);
        new CustomWatcher(etIdentity, ivIdentityClear);
        new CustomWatcher(etPhone, ivPhoneClear);
        findViewById(R.id.tv_notify_popman).setOnClickListener(this);
        tvVerifyCode.setOnClickListener(this);
        etIntroduce.addTextChangedListener(this);
        ivAddPic.setOnClickListener(this);
        ns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PopmanSortModel.DataEntity data = adapter.getData().get(position);
                String sortStr = data.getValue();
                sortValue = Integer.valueOf(sortStr);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_apply_popman, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_popman_submit) {
            handleRequest();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_notify_popman:
                PopmanRuleActivity.newInstance(this);
                break;
            case R.id.tv_verify_phone_code_popman:
                String phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showShortToast("手机号码不能为空");
                    return;
                }
                if (!Tools.isMobileNo(phone)) {
                    showShortToast("请输入正确的手机号码");
                    return;
                }
                handleVeriftRequest(phone);
                break;
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
                if (llPicContent != null && llPicContent.getChildCount() != 0) {
                    urls.remove(imgUrl);
                    llPicContent.removeView(child);
                }
                if (llPicContent.getChildCount() < picNumber)
                    ivAddPic.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        realLen = s.length();
        if (realLen <= 120) {
            tvIntroduce.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvIntroduce.setText(realLen + "/150");
        } else if (realLen < 150 && realLen > 120) {
            tvIntroduce.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvIntroduce.setText("还剩余" + (150 - realLen) + "个");
        } else if (realLen == 150) {
            tvIntroduce.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvIntroduce.setText("文字已输满");
        } else {
            tvIntroduce.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            tvIntroduce.setText("超过规定字数" + (realLen - 150) + "个");
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

    private void addPicItem(ArrayList<String> urls) {
        if (urls.size() > 0)
            llPicContent.setVisibility(View.VISIBLE);
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
            llPicContent.addView(child);
            index++;
        }
        if (picNumber == llPicContent.getChildCount()) {
            ivAddPic.setVisibility(View.GONE);
        } else {
            ivAddPic.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void handleRequest() {
        String nickName = etRealName.getText().toString();
        String identity = etIdentity.getText().toString();
        String phone = etPhone.getText().toString();
        String verifyCode = etVerifyCode.getText().toString();
        String sort = ns.getText().toString();
        String introduce = etIntroduce.getText().toString();
        if (TextUtils.isEmpty(nickName)) {
            showShortToast("昵称不能为空");
            return;
        }
        if (TextUtils.isEmpty(identity)) {
            showShortToast("身份证号码不能为空");
            return;
        }
        if (!Tools.isID(identity)) {
            showShortToast("请输入正确身份证ID");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showShortToast("手机号码不能为空");
            return;
        }
        if (!Tools.isMobileNo(phone)) {
            showShortToast("请输入正确的手机号码");
            return;
        }
        if (TextUtils.isEmpty(verifyCode)) {
            showShortToast("验证码不能为空");
            return;
        }
        if (TextUtils.isEmpty(sort)) {
            showShortToast("达人类型不能为空");
            return;
        }
        if (realLen > 150 || realLen == 0) {
            showShortToast("输入的字数不符合要求");
            return;
        }

        if (llPicContent.getChildCount() != 0) {
            upLoadImageToUmeng(nickName, identity, phone, verifyCode, sortValue, introduce);
        } else {
            showWaitDialog();
            handleApplyPopRequest(nickName, identity, phone, verifyCode, sortValue, introduce, null);
        }

    }

    /**
     * 图片上传到友盟资源服务器
     */
    private void upLoadImageToUmeng(final String nickName, final String identityCard, final String phone, final String verifyCode, final int sort, final String introduce) {
        showWaitDialog();
        ImageUpload.getInstance().uploadImages(this, urls, new UploadListener() {
            @Override
            public void onUploading(UploadTask uploadTask) {

            }

            @Override
            public void onUploadFailed(UploadTask uploadTask, FailReason failReason) {
                hideWaitDialog();
                Log.e(TAG, failReason.getMessage());
                ImageUpload.getInstance().cancelAllUpload();
                showShortToast("申请失败，请重新点击申请~");

            }

            @Override
            public void onUploadComplete(UploadTask uploadTask) {
                try {
                    JSONObject object = new JSONObject(uploadTask.getResult().message);
                    String url = object.optString("url");
                    String width = object.optString("returnBody").split("_")[0];
                    String height = object.optString("returnBody").split("_")[1];
                    Log.i(TAG, "uploadTask = " + uploadTask.getResult().message);
                    Log.e(TAG, "url : " + url + ", width : " + width + ", height : " + height);
                    Log.i("uploadTask popman", "popman uploadTask = " + urls.size());
                    TeletextModel.ImgsEntity imgsEntity = new TeletextModel.ImgsEntity();
                    imgsEntity.setFilePath(url);
                    imgsEntity.setWidth(Double.valueOf(width));
                    imgsEntity.setHeight(Double.valueOf(height));
                    upData.getImageFiles().add(imgsEntity);
                    if (upData.getImageFiles().size() == urls.size()) {
                        Gson gson = new Gson();
                        String imgs = gson.toJson(upData);
                        int index = imgs.indexOf("[");
                        String realImgs = imgs.substring(index + 1, imgs.length() - 2);
                        //                        realImgs = realImgs.replaceAll("\"", "\\\\\"");
                        Log.e(TAG, realImgs);
                        handleApplyPopRequest(nickName, identityCard, phone, verifyCode, sort, introduce, realImgs);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onUploadCancelled(UploadTask uploadTask) {

            }
        });
    }

    /**
     * 提交达人申请
     *
     * @param nickName
     * @param identityCard
     * @param phone
     * @param sort
     * @param introduce
     */
    private void handleApplyPopRequest(String nickName, String identityCard, String phone, String verifyCode, int sort, String introduce, String imgs) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", nickName);
        params.put("identityCard", identityCard);
        params.put("phone", phone);
        params.put("securityCode", verifyCode);
        params.put("eredarType", sort + "");
        params.put("userPresentation", introduce);
        if (!TextUtils.isEmpty(imgs))
            params.put("imageFiles", imgs);
        NetRequest.postRequest(Constants.ServiceInfo.APPLY_POPMAN_REQUEST, BaseApplication.token, params, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                showShortToast(msg);
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));

    }

    /**
     * 获取验证码
     *
     * @param phone
     */
    private void handleVeriftRequest(String phone) {
        HashMap<String, String> body = new HashMap<>();
        body.put("loginAccount", phone);
        NetRequest.postRequest(Constants.ServiceInfo.VERIFY_CODE, BaseApplication.token, body, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                tvVerifyCode.setEnabled(false);
                timeCount.start();
                showShortToast(msg);
            }

            @Override
            public void onErrorListener(String message) {
                showShortToast(message);
            }
        }));
    }

    /**
     * 获取达人类型
     */
    private void requestPopmanSort() {
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("typeCode", "eredar");
        NetRequest.postRequest(Constants.ServiceInfo.SMARTS_SORT_REQUEST, BaseApplication.token, params, PopmanSortModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                PopmanSortModel data = (PopmanSortModel) object;
                if (data != null && data.getData() != null) {
                    adapter.setData(data.getData());
                    ns.setAdapter(adapter);
                    sortValue = Integer.valueOf(adapter.getData().get(0).getValue());
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

}
