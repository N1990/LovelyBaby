package com.cmbb.smartkids.activity.home.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.LoginActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.utils.log.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/17 下午4:17
 */
public abstract class BaseHomeActivity extends BaseActivity {
    private static final String TAG = BaseHomeActivity.class.getSimpleName();
    protected TextView tvHome;
    protected TextView tvService;
    protected TextView tvTopic;
    protected TextView tvMe;
    protected TextView tvMore;

    private Boolean isQuit = false;// 退出应用标识符
    private Timer timer = new Timer();// 程序退出定时器
    private NetWorkChangeBroadcastReceiver netWorkChangeBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        netWorkChangeBroadcastReceiver = new NetWorkChangeBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangeBroadcastReceiver, filter);
        super.onCreate(savedInstanceState);
        //注册网络监听器
        tvHome = (TextView) findViewById(R.id.tv_home);
        tvService = (TextView) findViewById(R.id.tv_service);
        tvTopic = (TextView) findViewById(R.id.tv_topic);
        tvMe = (TextView) findViewById(R.id.tv_me);
        tvMore = (TextView) findViewById(R.id.tv_more);
        tvHome.setOnClickListener(this);
        tvService.setOnClickListener(this);
        tvTopic.setOnClickListener(this);
        tvMe.setOnClickListener(this);
        tvMore.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_home:
                HomeActivity.newIntent(this);
                break;
            case R.id.tv_service:
                HomeServiceActivity.newIntent(this);
                break;
            case R.id.tv_topic:
                if (TextUtils.isEmpty(BaseApplication.token)) {
                    new AlertDialog.Builder(this)
                            .setTitle("温馨提示")
                            .setMessage("您还没有登陆萌宝派")
                            .setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    LoginActivity.newIntent(BaseHomeActivity.this);
                                }
                            })
                            .create().show();
                } else {
                    HomeTopicActivity.newIntent(this);
                }
                break;
            case R.id.tv_me:
                if (TextUtils.isEmpty(BaseApplication.token)) {
                    new AlertDialog.Builder(this)
                            .setTitle("温馨提示")
                            .setMessage("您还没有登陆萌宝派")
                            .setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    LoginActivity.newIntent(BaseHomeActivity.this);
                                }
                            })
                            .create().show();
                } else {
                    HomeMeActivity.newIntent(this);
                }
                break;
            case R.id.tv_more:
                HomeMoreActivity.newIntent(this);
                break;
        }
    }


    public class NetWorkChangeBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                /*if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan
                }*/
                Log.i(TAG, "activeNetwork  = " + activeNetwork.getTypeName());

                netChange();
            } else {
                // 无网络连接
                showShortToast("网络出现连接错误，请检测网络");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netWorkChangeBroadcastReceiver);
    }

    protected abstract void netChange();

    @Override
    public void onBackPressed() {
        if (!isQuit) {
            isQuit = true;
            showShortToast(getString(R.string.quit_app));
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    isQuit = false;
                }
            };
            timer.schedule(task, 2000);
        } else {
            Intent intent = new Intent(Constants.INTENT_ACTION_EXIT_APP);
            sendBroadcast(intent);
        }
    }

}
