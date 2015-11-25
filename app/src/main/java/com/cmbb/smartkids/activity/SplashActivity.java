package com.cmbb.smartkids.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.HomeActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.db.DBContent;
import com.cmbb.smartkids.model.DBAddressModel;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.Tools;
import com.cmbb.smartkids.utils.log.Log;
import com.google.gson.Gson;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends BaseActivity {


    @Override
    protected void init(Bundle savedInstanceState) {
        // 启动Push
        mPushAgent.enable(mRegisterCallback);
        PushAgent.getInstance(this).setMergeNotificaiton(false); //接收多条信息
        initData();
        /*new Handler().postDelayed(new Runnable() {
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
        }, 2000);*/
    }


    private void initData() {


        MyTask task = new MyTask();
        task.execute(this);
    }


    class MyTask extends AsyncTask<Context, Void, Void> {


        @Override
        protected Void doInBackground(Context... params) {
            int versionCode = SPCache.getInt("VersionCode", 0);
            switch (versionCode) {
                case 0:
                    String tempJson = Tools.getJson(params[0], "address.json");
                    String json = "{data:" + tempJson + "}";
                    Gson gson = new Gson();
                    DBAddressModel address = gson.fromJson(json, DBAddressModel.class);
                    List<DBAddressModel.DataEntity> data = address.getData();
                    for (DBAddressModel.DataEntity province : data) {
                        for (DBAddressModel.DataEntity.CitiesEntity city : province.getCities()) {
                            for (DBAddressModel.DataEntity.CitiesEntity.CountiesEntity area : city.getCounties()) {
                                ContentValues valus = new ContentValues();
                                valus.put(DBContent.DBAddress.ADDRESS_ID, area.getId());
                                valus.put(DBContent.DBAddress.ADDRESS_TEXT, area.getName());
                                valus.put(DBContent.DBAddress.CITY_TEXT, city.getName());
                                valus.put(DBContent.DBAddress.PROVINCE_TEXT, province.getName());
                                getContentResolver().insert(DBContent.DBAddress.CONTENT_URI, valus);
                            }
                        }
                    }
                    SPCache.putInt("VersionCode", 300);
                    break;
                case 300:
                    
                    break;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
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
