package com.cmbb.smartkids.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home_v2.HomeActivity;
import com.cmbb.smartkids.activity.home.model.ManagerAdModel;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.okhttp.Request;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

public class SplashActivity extends AppCompatActivity {

    private SimpleDraweeView sdv;
    public PushAgent mPushAgent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);
        sdv = (SimpleDraweeView) findViewById(R.id.sdv_splash);
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.onAppStart();
        mPushAgent.enable(mRegisterCallback);
        PushAgent.getInstance(this).setMergeNotificaiton(false); //接收多条信息
        ManagerAdModel.getSplashImgRequest(new OkHttpClientManager.ResultCallback<ManagerAdModel>() {
            @Override
            public void onError(Request request, Exception e) {
                Toast.makeText(SplashActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(ManagerAdModel response) {
                if (response.getData() != null && response.getData().size() != 0)
                    FrescoTool.loadImage(sdv, response.getData().get(0).getAdImg(), TDevice.getScreenWidth(SplashActivity.this) + "", TDevice.getScreenHeight(SplashActivity.this) + "");
            }
        });

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
        }, 6000);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }


    @Override

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    // 友盟推送注册器
    private IUmengRegisterCallback mRegisterCallback = new IUmengRegisterCallback() {
        @Override
        public void onRegistered(String registrationId) {
            Log.e("mRegisterCallback", "token:" + mPushAgent.getRegistrationId());
        }
    };
}
