package com.cmbb.smartkids.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home.HomeActivity;
import com.cmbb.smartkids.activity.home.model.ManagerAdModel;
import com.cmbb.smartkids.activity.login.LoginActivity;
import com.cmbb.smartkids.activity.login.VerifyActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.photopicker.widget.indication.CirclePageIndicator;
import com.cmbb.smartkids.utils.ExitBroadcast;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.okhttp.Request;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;


public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    // splash
    private SimpleDraweeView sdv;
    public PushAgent mPushAgent;
    private TextView tvSecond;
    private int recLen = 3;
    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    recLen--;
                    tvSecond.setText("跳过");
                    if (recLen > 0) {
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);
                    } else {
                        HomeActivity.newIntent(SplashActivity.this);
                        finish();
                    }
            }
            super.handleMessage(msg);
        }
    };

    // guide
    private ViewPager vp;
    private CirclePageIndicator cpiGuid;
    private boolean skipFlag;
    private LinearLayout ll_bottom;
    private TextView tvIn;

    private BroadcastReceiver existReceiver;// EXIT


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);
        if (TextUtils.isEmpty(SPCache.getString("app_first", ""))) {
            findViewById(R.id.guide).setVisibility(View.VISIBLE);
            findViewById(R.id.splash).setVisibility(View.GONE);
            SPCache.putString("app_first", "3.1.0");
            initGuideView();
        } else {
            findViewById(R.id.guide).setVisibility(View.GONE);
            findViewById(R.id.splash).setVisibility(View.VISIBLE);
            initSplashView();
        }
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.onAppStart();
        mPushAgent.enable(mRegisterCallback);
        PushAgent.getInstance(this).setMergeNotificaiton(false); //接收多条信息
        initBroadcast();
    }

    private void initGuideView() {
        tvIn = (TextView) findViewById(R.id.tv_in);
        ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
        vp = (ViewPager) findViewById(R.id.vp_guid);
        cpiGuid = (CirclePageIndicator) findViewById(R.id.cpi_guid);
        SimpleAdapter adapter = new SimpleAdapter();
        vp.setAdapter(adapter);
        cpiGuid.setViewPager(vp);
        cpiGuid.setSnap(true);
        findViewById(R.id.tv_guid_login).setOnClickListener(this);
        findViewById(R.id.tv_guid_register).setOnClickListener(this);
        findViewById(R.id.tv_in).setOnClickListener(this);
        tvIn.setOnClickListener(this);
    }

    private void initSplashView() {
        tvSecond = (TextView) findViewById(R.id.tv_second);
        tvSecond.setOnClickListener(this);
        sdv = (SimpleDraweeView) findViewById(R.id.sdv_splash);
        ManagerAdModel.getSplashImgRequest(new OkHttpClientManager.ResultCallback<ManagerAdModel>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(ManagerAdModel response) {
                if (response != null && response.getData().size() > 0) {
                    tvSecond.setVisibility(View.VISIBLE);
                    FrescoTool.loadImage(sdv, response.getData().get(0).getAdImg(), TDevice.getScreenWidth(SplashActivity.this) + "", TDevice.getScreenHeight(SplashActivity.this) + "");
                }
            }
        });
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 1000);
    }

    /**
     * 程序退出
     */
    private void initBroadcast() {
        existReceiver = new ExitBroadcast(this);
        IntentFilter filter = new IntentFilter(Constants.INTENT_ACTION_EXIT_APP);
        registerReceiver(existReceiver, filter);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(existReceiver);
    }

    // 友盟推送注册器
    private IUmengRegisterCallback mRegisterCallback = new IUmengRegisterCallback() {
        @Override
        public void onRegistered(String registrationId) {
            Log.e("mRegisterCallback", "token:" + mPushAgent.getRegistrationId());
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_second:
                recLen = 0;
                break;
            case R.id.tv_guid_login:
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                break;
            case R.id.tv_guid_register:
                Intent intent = new Intent(SplashActivity.this, VerifyActivity.class);
                intent.putExtra("tag", "register");
                startActivity(intent);
                break;
            case R.id.tv_in:
                HomeActivity.newIntent(this);
                break;
        }
    }

    private int[] imgs = {R.mipmap.guide_bac_01, R.mipmap.guide_bac_02, R.mipmap.guide_bac_03};

    class SimpleAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(container.getContext());
            iv.setAdjustViewBounds(true);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setAdjustViewBounds(false);
            int img = imgs[position];
            iv.setImageResource(img);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //container.removeViewAt(position);
        }
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
