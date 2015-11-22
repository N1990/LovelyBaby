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
import com.cmbb.smartkids.activity.home.HomeActivity;
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
        /*{
                "messageId": null,
                    "requestId": "bafc68a2-5767-11e5-90d9-1a376e9a916d",
                    "error": null,
                    "statusCode": 200,
                    "cmd": "smart/forgetPWD",
                    "response": {
                "status": 0,
                        "data": {
                    "recommoned": null,
                            "userNike": "niesen1990",
                            "userSex": null,
                            "userBigImg": null,
                            "userBigWidth": null,
                            "userBigHeight": null,
                            "userSmallImg": null,
                            "userSmallWidth": null,
                            "userSmallHeight": null,
                            "loginAccountType": 0,
                            "loginTime": "2015-09-10 09:59:07",
                            "loginAccount": "13818155072",
                            "token": "33ad7e90c3664443b9ae0e721551e05f",
                            "isShutup": 0,
                            "shutupTime": null,
                            "isBanned": 0,
                            "userAddress": null,
                            "userPhone": null,
                            "userPhoneVersion": null,
                            "province": 310000,
                            "district": 310110,
                            "city": null,
                            "userLevel": 1,
                            "userPresentation": null,
                            "userRole": [
                    {
                        "eredarCode": 0,
                            "eredarName": "萌宝用户",
                            "createDate": null,
                            "createUserId": null,
                            "updateDate": null,
                            "updateUserId": null
                    }
                    ]
                },
                "msg": "更新成功"
            },
                "responseTime": "2015-09-10 10:57:54",
                    "responseTimestamp": 1441853874114,
                    "duration": 612,
                    "debugInfo": null,
                    "clientIp": "192.168.100.115",
                    "usedCache": false,
                    "extraInfo": {
                "consuming": "612毫秒",
                        "serverName": "192.168.100.135"
            },
                "auth": {

            }
            }*/
        NetRequest.postRequest(Constants.ServiceInfo.FORGEST_PWD_REQUEST, "", body, UserAssistModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                UserAssistModel obj = (UserAssistModel) object;
                hideWaitDialog();
                if (null != obj && obj.getData() != null) {
                    Log.i(TAG, "userInfo = " + obj.toString());
                    UserRootModel user = obj.getData();
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
                    /*valus.put(DBContent.USER_IMG_WIDTH, Integer.valueOf(user.getUserSmallWidth()));
                    valus.put(DBContent.USER_IMG_HEIGHT, Integer.valueOf(user.getUserSmallHeight()));*/
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
                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++


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
