package com.cmbb.smartkids.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.HomeActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.log.Log;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

public class SplashActivity extends BaseActivity {


    @Override
    protected void init(Bundle savedInstanceState) {
        // 启动Push
        mPushAgent.enable(mRegisterCallback);
        PushAgent.getInstance(this).setMergeNotificaiton(false); //接收多条信息
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断Token
                String token = SPCache.getString(Constants.TOKEN, "");
                Intent intent = null;
                if (TextUtils.isEmpty(token)) {
                    intent = new Intent(SplashActivity.this, GuidActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, HomeActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 2000);
    }





    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override

    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    // 友盟推送注册器
    private IUmengRegisterCallback mRegisterCallback = new IUmengRegisterCallback() {

        @Override
        public void onRegistered(String registrationId) {
            Log.e("mRegisterCallback", "token:" + mPushAgent.getRegistrationId());
        }
    };


}
