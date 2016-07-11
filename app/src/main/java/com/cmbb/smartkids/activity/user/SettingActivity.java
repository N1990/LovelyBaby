package com.cmbb.smartkids.activity.user;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.SplashActivity;
import com.cmbb.smartkids.activity.more.AboutActivity;
import com.cmbb.smartkids.activity.more.GrownValusActivity;
import com.cmbb.smartkids.activity.more.SuggestActivity;
import com.cmbb.smartkids.activity.user.model.LogoutModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.db.DBHelper;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.log.Log;
import com.squareup.okhttp.Request;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/7/8 上午10:51
 * 修改人：N.Sun
 * 修改时间：16/7/8 上午10:51
 * 修改备注：
 */
public class SettingActivity extends BaseActivity {

    private static final String TAG = SettingActivity.class.getSimpleName();
    private DBHelper dbHelper;

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("设置");
        dbHelper = new DBHelper(this);
        findViewById(R.id.tv_suggest_feek).setOnClickListener(this);
        findViewById(R.id.tv_growth_values).setOnClickListener(this);
        findViewById(R.id.tv_about).setOnClickListener(this);
        findViewById(R.id.tv_info).setOnClickListener(this);
        findViewById(R.id.tv_account).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_layout;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_info:
                InfoActivity.newIntent(this);
                break;
            case R.id.tv_suggest_feek:
                SuggestActivity.newInstance(this);
                break;
            case R.id.tv_growth_values:
                GrownValusActivity.newInstance(this);
                break;
            case R.id.tv_about:
                AboutActivity.newInstance(this);
                break;
            case R.id.tv_account:
                changeAccountRequest();
                break;
        }
    }

    /**
     * 切换账户
     */
    private void changeAccountRequest() {
        showWaitDialog();
        LogoutModel.logoutRequest(new OkHttpClientManager.ResultCallback<LogoutModel>() {
            @Override
            public void onError(Request request, Exception e, String response) {
                hideWaitDialog();
                if (TextUtils.isEmpty(response)) {
                    Log.e(TAG, e.toString());
                    showToast(getString(R.string.err_network));
                } else {
                    showToast(response);
                }
            }

            @Override
            public void onResponse(LogoutModel logoutModel) {
                hideWaitDialog();
                if (logoutModel != null) {
                    showShortToast(logoutModel.getMsg());
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                boolean flag = BaseApplication.mPushAgent.removeAlias(SPCache.getString(Constants.USER_ID, "") + "_" + TDevice.getDeviceId(SettingActivity.this), "service");
                                Log.e("Alias", "Alias remove = " + flag);
                                Log.e("Alias", "Alias remove id = " + SPCache.getString(Constants.USER_ID, ""));
                                if (flag) {
                                    BaseApplication.token = "";
                                    SPCache.clear();
                                    //删除表
                                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                                    dbHelper.delete(db);
                                    //关闭主页面
                                    Intent intent = new Intent(Constants.INTENT_ACTION_EXIT_APP);
                                    sendBroadcast(intent);
                                    SplashActivity.newIntent(SettingActivity.this);
                                    finish();
                                } else {
                                    showToast("注销失败");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            }
        });
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
}
