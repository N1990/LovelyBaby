package com.cmbb.smartkids.activity.login;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home.HomeActivity;
import com.cmbb.smartkids.activity.login.model.UserAssistModel;
import com.cmbb.smartkids.activity.login.model.UserRootModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.db.DBContent;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.CustomWatcher;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.log.Log;

import java.util.HashMap;

public class ForgetPwdActivity extends BaseActivity {

    private static final String TAG = ForgetPwdActivity.class.getSimpleName();
    private EditText etPwd, etPwdComfirm;
    private ImageView ivPwd, ivPwdComfirm;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initViews();
        addListener();
    }

    private void addListener() {
        findViewById(R.id.tv_forget_pwd_finish).setOnClickListener(this);
        new CustomWatcher(etPwd, ivPwd);
        new CustomWatcher(etPwd, ivPwdComfirm);
        findViewById(R.id.tv_forget_pwd_finish).setOnClickListener(this);
    }

    private void initViews() {
        setTitle(getString(R.string.title_activity_forget_pwd));
        etPwd = (EditText) findViewById(R.id.et_forget_pwd);
        ivPwd = (ImageView) findViewById(R.id.iv_forget_pwd_clear);
        etPwdComfirm = (EditText) findViewById(R.id.et_forget_pwd_comfirm);
        ivPwdComfirm = (ImageView) findViewById(R.id.iv_login_pwd_comfirm_clear);

    }

    @Override
    public void onClick(View v) {
        String pwd = etPwd.getText().toString();
        String pwdConfirm = etPwdComfirm.getText().toString();
        if (TextUtils.isEmpty(pwd)) {
            showShortToast("请输入您的密码");
            return;
        }
        if (TextUtils.isEmpty(pwdConfirm)) {
            showShortToast("请再次输入您的密码");
            return;
        }
        if (!pwd.equals(pwdConfirm)) {
            showShortToast("您两次输入的密码不一致");
            return;
        }
        handleRequest(pwd);

    }

    private void handleRequest(String pwd) {
        showWaitDialog();
        HashMap<String, String> body = new HashMap<>();
        body.put("loginAccount", getIntent().getStringExtra("phone"));
        body.put("loginPassword", pwd);
        body.put("device", 2 + "");
        body.put("deviceVersion", android.os.Build.VERSION.RELEASE);
        body.put("model", android.os.Build.MODEL);
        body.put("imei", TDevice.getDeviceId(this));
        body.put("applicationVersion", TDevice.getVersionName());
        NetRequest.postRequest(Constants.ServiceInfo.FORGEST_PWD_REQUEST, "", body, UserAssistModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                UserAssistModel obj = (UserAssistModel) object;
                hideWaitDialog();
                if (null != obj && obj.getData() != null) {
                    UserRootModel user = obj.getData();
                    if (TextUtils.isEmpty(user.getToken())) {
                        showShortToast(msg);
                        startActivity(new Intent(ForgetPwdActivity.this, LoginActivity.class));
                        finish();
                    }
                    SPCache.putString(Constants.TOKEN, user.getToken());
                    SPCache.putString(Constants.USER_ID, user.getUserId() + "");
                    // 更改Application里面的值
                    BaseApplication.token = user.getToken();
                    // 发送信息注册Alias
                    Intent intent = new Intent(BaseApplication.PUSH_ALIAS_ITENTACTION);
                    intent.putExtra("umeng_id", user.getUserId() + "_" + TDevice.getDeviceId(ForgetPwdActivity.this));
                    intent.putExtra("umeng_type", "service");
                    LocalBroadcastManager.getInstance(ForgetPwdActivity.this).sendBroadcast(intent);
                    //更新数据库
                    ContentValues valus = new ContentValues();
                    valus.put(DBContent.DBUser.USER_ID, user.getUserId());
                    valus.put(DBContent.DBUser.USER_TOKEN, user.getToken());
                    valus.put(DBContent.DBUser.USER_HEAD_IMG, user.getUserSmallImg());
                    valus.put(DBContent.DBUser.USER_NICK_NAME, user.getUserNike());
                    valus.put(DBContent.DBUser.USER_MALE, user.getUserSex());
                    valus.put(DBContent.DBUser.USER_PHONE, user.getUserPhone());
                    valus.put(DBContent.DBUser.USER_PROVINCE_ID, user.getProvince());
                    valus.put(DBContent.DBUser.USER_CITY_ID, user.getCity());
                    valus.put(DBContent.DBUser.USER_AREA_ID, user.getDistrict());
                    valus.put(DBContent.DBUser.USER_IS_POPMAN, user.getIsEredar());
                    valus.put(DBContent.DBUser.USER_ADDRESS, user.getUserAddress());
                    valus.put(DBContent.DBUser.USER_INTRODUCE, user.getUserPresentation());
                    getContentResolver().update(DBContent.DBUser.CONTENT_URI, valus, DBContent.DBUser.USER_ID + " = " + user.getUserId(), null);
                    startActivity(new Intent(ForgetPwdActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Log.i(TAG, "message = " + msg);
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
}
