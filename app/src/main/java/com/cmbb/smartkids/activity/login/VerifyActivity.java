package com.cmbb.smartkids.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.CustomWatcher;
import com.cmbb.smartkids.utils.MyTimeCount;
import com.cmbb.smartkids.utils.Tools;

import java.util.HashMap;

public class VerifyActivity extends BaseActivity {
    private final String TAG = VerifyActivity.class.getSimpleName();
    private ImageView ivPhoneClear;
    private EditText etPhone, etCode;
    private TextView tvCode, tvSubmit;
    private String tag; // register  forget
//    private TimerRegister timeRegsiter;
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
        Bundle bundle = null;
        if (getIntent() != null && (bundle = getIntent().getExtras()) != null) {
            tag = bundle.getString("tag");
           if ("register".equals(tag)) {
                setTitle(getString(R.string.title_activity_register));
                tvSubmit.setText("下一步");
            } else if ("forget".equals(tag)) {
                setTitle(getString(R.string.title_activity_forget_pwd));
                tvSubmit.setText("下一步");
            }
        } else {
            showShortToast("数据传输出错...");
        }
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
//                startService(new Intent(this, CountTimeService.class));
                if ("register".equals(tag)) {
                    // 注册验证码
                    HashMap<String, String> body = new HashMap<>();
                    body.put("loginAccount", phone);
                       /*{
                            "messageId": null,
                                "requestId": "fcac2e4e-56e5-11e5-9655-1a376e9a916d",
                                "error": null,
                                "statusCode": 200,
                                "cmd": "smart/getSecurityCode",
                                "response": {
                            "status": 1,
                                    "msg": "发送成功"
                        },
                            "responseTime": "2015-09-09 19:29:09",
                                "responseTimestamp": 1441798149813,
                                "duration": 682,
                                "debugInfo": null,
                                "clientIp": "192.168.100.125",
                                "usedCache": false,
                                "extraInfo": {
                            "consuming": "682毫秒",
                                    "serverName": "192.168.100.168"
                        },
                            "auth": {

                        }
                        }*/
                    NetRequest.postRequest(Constants.ServiceInfo.REGISTER_VERIFY_CODE, "", body, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
                        @Override
                        public void onSuccessListener(Object object, String msg) {
                            showShortToast(msg);
                            tvCode.setEnabled(false);
                            timeCount.start();
                        }

                        @Override
                        public void onErrorListener(String message) {
                            showShortToast(message);
                        }
                    }));
                } else if (tag.equals("forget")) {
                    /*{
                        "messageId": null,
                            "requestId": "6171cf98-5765-11e5-bf79-1a376e9a916d",
                            "error": null,
                            "statusCode": 200,
                            "cmd": "smart/getSecurityCode",
                            "response": {
                        "status": 1,
                                "msg": "发送成功"
                    },
                        "responseTime": "2015-09-10 10:41:05",
                            "responseTimestamp": 1441852865093,
                            "duration": 810,
                            "debugInfo": null,
                            "clientIp": "192.168.100.115",
                            "usedCache": false,
                            "extraInfo": {
                        "consuming": "810毫秒",
                                "serverName": "192.168.100.135"
                    },
                        "auth": {

                    }
                    }*/
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
                        }
                    }));
                }
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
        HashMap<String, String> body = new HashMap<>();
        body.put("loginAccount", phone);
        body.put("securityCode", code);
           /*{
                "messageId":null,
                    "requestId":"33992e60-56ee-11e5-98cd-eab1fcca9c3e",
                    "error":null,
                    "statusCode":200,
                    "cmd":"smart/registerNext",
                    "response":{
                "status":1,
                        "msg":"手机号与验证码正确"
            },
                "responseTime":"2015-09-09 20:28:55",
                    "responseTimestamp": 1441801735090,
                    "duration": 57835,
                    "debugInfo": null,
                    "clientIp": "192.168.100.158",
                    "usedCache": false,
                    "extraInfo": {
                "consuming": "57.835秒",
                        "serverName": "192.168.100.150"
            },
                "auth": {

            }
            }*/
        NetRequest.postRequest(Constants.ServiceInfo.REGISTER_NEXT_REQUEST, "", body, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                if ("login".equals(tag)) { // 第三方登录

                } else if ("register".equals(tag)) { // 注册
                    Intent intent = new Intent(VerifyActivity.this, RegisterActivity.class);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                    finish();
                } else if ("forget".equals(tag)) { // 忘记密码
                    Intent intent = new Intent(VerifyActivity.this, ForgetPwdActivity.class);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

    @Override
    protected void onDestroy() {
//        unregisterReceiver(timeRegsiter);
        super.onDestroy();
    }

   /* class TimerRegister extends BroadcastReceiver {
        int second = 0;

        @Override
        public void onReceive(Context context, Intent intent) {
            second = intent.getIntExtra("second", 0);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (second == 0) {
                        tvCode.setText("获取验证码");
                        tvCode.setTextColor(getResources().getColor(R.color.primaryColor));
                        tvCode.setEnabled(true);
                    } else {
                        tvCode.setEnabled(false);
                        tvCode.setTextColor(Color.GRAY);
                        tvCode.setText(second < 10 ? "获取验证码0" + second + "s" : "获取验证码" + second + "s");
                    }
                }
            });

        }
    }*/

}
