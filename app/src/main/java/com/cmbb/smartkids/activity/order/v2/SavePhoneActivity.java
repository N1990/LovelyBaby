package com.cmbb.smartkids.activity.order.v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.ForgetPwdActivity;
import com.cmbb.smartkids.activity.login.RegisterActivity;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.user.model.ModifyUserModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.CustomWatcher;
import com.cmbb.smartkids.utils.MyTimeCount;
import com.cmbb.smartkids.utils.Tools;

import java.util.HashMap;

public class SavePhoneActivity extends BaseActivity {
    private final String TAG = SavePhoneActivity.class.getSimpleName();
    private ImageView ivPhoneClear;
    private EditText etPhone, etCode;
    private TextView tvCode, tvSubmit;
    private MyTimeCount timeCount;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_verify;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initViews();
        initData();
        addListener();
    }

    private void addListener() {
        new CustomWatcher(etPhone, ivPhoneClear);
        tvCode.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    private void initData() {
        setTitle(getString(R.string.title_modify_phone));
        tvSubmit.setText("保存手机号");
    }

    private void initViews() {
        etPhone = (EditText) findViewById(R.id.et_verify_phone);
        ivPhoneClear = (ImageView) findViewById(R.id.iv_verify_phone_clear);
        etCode = (EditText) findViewById(R.id.et_verify_code);
        tvCode = (TextView) findViewById(R.id.tv_verify_phone_code);
        tvSubmit = (TextView) findViewById(R.id.tv_verify_submit);
        tvCode.setText("获取验证码");
        timeCount = new MyTimeCount(60000, 1000, tvCode);
        /*timeRegsiter = new TimerRegister();
        IntentFilter filter = new IntentFilter(Constants.Broadcast.COUNT_TIME);
        registerReceiver(timeRegsiter, filter);*/
    }


    @Override
    public void onClick(View v) {
        String phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showShortToast("手机号码不能为空");
            return;
        }
        if (!Tools.isMobileNo(phone)) {
            showShortToast("请输入正确的手机号码");
            return;
        }
        switch (v.getId()) {
            case R.id.tv_verify_phone_code:
                tvCode.setEnabled(false);
                HashMap<String, String> body = new HashMap<>();
                body.put("loginAccount", phone);
                NetRequest.postRequest(Constants.ServiceInfo.VERIFY_CODE, "", body, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
                    @Override
                    public void onSuccessListener(Object object, String msg) {
                        showShortToast(msg);
                        tvCode.setEnabled(false);
                        timeCount.start();
                    }

                    @Override
                    public void onErrorListener(String message) {
                        showShortToast(message);
                        tvCode.setEnabled(true);
                    }
                }));
                break;
            case R.id.tv_verify_submit:
                String verify = etCode.getText().toString();
                if (TextUtils.isEmpty(verify)) {
                    showShortToast("验证码不能为空");
                    return;
                }
                handleSumbit(verify, phone);
                break;
        }
    }

    private void handleSumbit(String code, final String phone) {
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("userPhone", phone);
        params.put("securityCode", code);
        NetRequest.postRequest(Constants.ServiceInfo.MODIFY_USER_INFO, BaseApplication.token, params, ModifyUserModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                ModifyUserModel modify = (ModifyUserModel) object;
                if (modify != null && modify.getData() != null) {
                    hideWaitDialog();
                    showShortToast("手机号码修改成功");
                    Intent intent = getIntent();
                    intent.putExtra("phone", phone);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);

            }
        }));
    }

    public static void newInstance(Activity activity, int requestCode){
        Intent intent = new Intent(activity, SavePhoneActivity.class);
        activity.startActivityForResult(intent, requestCode);

    }

}
