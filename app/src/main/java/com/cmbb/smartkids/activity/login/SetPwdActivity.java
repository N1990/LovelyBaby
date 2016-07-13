package com.cmbb.smartkids.activity.login;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
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
import com.cmbb.smartkids.db.DBHelper;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.utils.CustomWatcher;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.TDevice;
import com.squareup.okhttp.Request;

public class SetPwdActivity extends BaseActivity {

    private static final String TAG = SetPwdActivity.class.getSimpleName();
    private EditText etPwd, etPwdComfirm;
    private ImageView ivPwd, ivPwdComfirm;

    private DBHelper dbHelper;  //临时逻辑

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        dbHelper = new DBHelper(this);
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
        //        LoginActivity.newIntent(this, getIntent().getExtras().getString("phone"), pwdConfirm);
        handleUnBundleThreeLogin(getIntent().getStringExtra("phone"), pwdConfirm);

    }

    public static void newIntent(Context context, String phone, String openId, String uid, String userName, int platform) {
        Intent intent = new Intent(context, SetPwdActivity.class);
        intent.putExtra("phone", phone);
        intent.putExtra("openId", openId);
        intent.putExtra("uid", uid);
        intent.putExtra("userName", userName);
        intent.putExtra("platform", platform);
        context.startActivity(intent);
    }

    private void handleUnBundleThreeLogin(String phone, String psw) {
        showWaitDialog();
        UserAssistModel.loginUnBundleRequest(this, phone, psw, getIntent().getStringExtra("openId"), getIntent().getStringExtra("uid"), getIntent().getStringExtra("userName"), getIntent().getIntExtra("platform", 0), new OkHttpClientManager.ResultCallback<UserAssistModel>() {
            @Override
            public void onError(Request request, Exception e, String response) {
                hideWaitDialog();
                if (TextUtils.isEmpty(response)) {
                    showToast(getString(R.string.err_network));
                    Log.e(TAG, e.toString());
                } else {
                    showToast(response);
                }
            }

            @Override
            public void onResponse(UserAssistModel response) {
                if (response != null) {
                    loginSuccessSave(response);
                }
            }
        });
    }

    private void loginSuccessSave(UserAssistModel obj) {
        //删除表
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.delete(db);
        UserRootModel user = obj.getData();
        SPCache.putString(Constants.TOKEN, user.getToken());
        SPCache.putString(Constants.LOGIN_ACCOUNT, user.getLoginAccount());
        SPCache.putString(Constants.USER_ID, user.getUserId() + "");
        // 更改Application里面的值
        BaseApplication.token = user.getToken();
        // 发送信息注册Alias
        Intent intent = new Intent(BaseApplication.PUSH_ALIAS_ITENTACTION);
        intent.putExtra("umeng_id", user.getUserId() + "_" + TDevice.getDeviceId(this));
        intent.putExtra("umeng_type", "service");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(BaseApplication.PUSH_ALIAS_RESUME_REQUEST));
        //写入数据库
        ContentValues valus = new ContentValues();
        valus.put(DBContent.DBUser.USER_ID, user.getUserId());
        valus.put(DBContent.DBUser.USER_UID, user.getUid());
        valus.put(DBContent.DBUser.USER_RECOMMONED, user.getRecommoned());
        valus.put(DBContent.DBUser.USER_NIKE, user.getUserNike());
        valus.put(DBContent.DBUser.USER_SEX, user.getUserSex());
        valus.put(DBContent.DBUser.USER_BIRTHDAY, user.getUserBirthday());
        valus.put(DBContent.DBUser.USER_BACKGROUNDIMG, user.getBackgroundImg());
        valus.put(DBContent.DBUser.USER_BIGIMG, user.getUserBigImg());
        valus.put(DBContent.DBUser.USER_BIGWIDTH, user.getUserBigWidth());
        valus.put(DBContent.DBUser.USER_BIGHEIGHT, user.getUserBigHeight());
        valus.put(DBContent.DBUser.USER_SMALLIMG, user.getUserSmallImg());
        valus.put(DBContent.DBUser.USER_SMALLWIDTH, user.getUserSmallWidth());
        valus.put(DBContent.DBUser.USER_SMALLHEIGHT, user.getUserSmallHeight());
        valus.put(DBContent.DBUser.USER_LOGINACCOUNTTYPE, user.getLoginAccountType());
        valus.put(DBContent.DBUser.USER_LOGINTIME, user.getLoginTime());
        valus.put(DBContent.DBUser.USER_LOGINACCOUNT, user.getLoginAccount());
        valus.put(DBContent.DBUser.USER_TOKEN, user.getToken());
        valus.put(DBContent.DBUser.USER_ISSHUTUP, user.getIsShutup());
        valus.put(DBContent.DBUser.USER_SHUTUPTIME, user.getShutupTime());
        valus.put(DBContent.DBUser.USER_ISBANNED, user.getIsBanned());
        valus.put(DBContent.DBUser.USER_ADDRESS, user.getUserAddress());
        valus.put(DBContent.DBUser.USER_PHONE, user.getUserPhone());
        valus.put(DBContent.DBUser.USER_PHONEVERSION, user.getUserPhoneVersion());
        valus.put(DBContent.DBUser.USER_PROVINCE, user.getProvince());
        valus.put(DBContent.DBUser.USER_PROVINCETEXT, user.getProvinceText());
        valus.put(DBContent.DBUser.USER_DISTRICT, user.getDistrict());
        valus.put(DBContent.DBUser.USER_DISTRICTTEXT, user.getDistrictText());
        valus.put(DBContent.DBUser.USER_CITY, user.getCity());
        valus.put(DBContent.DBUser.USER_CITYTEXT, user.getCityText());
        valus.put(DBContent.DBUser.USER_LEVEL, user.getUserLevel());
        valus.put(DBContent.DBUser.USER_PRESENTATION, user.getUserPresentation());
        valus.put(DBContent.DBUser.USER_BACKIMGWIDTH, user.getBackImgWidth());
        valus.put(DBContent.DBUser.USER_BACKIMGHEIGHT, user.getBackImgHeight());
        valus.put(DBContent.DBUser.USER_GOLDCOUNT, user.getGoldCount());
        valus.put(DBContent.DBUser.USER_GROWTHCOUNT, user.getGrowthCount());
        valus.put(DBContent.DBUser.USER_FANS, user.getFans());
        valus.put(DBContent.DBUser.USER_ATTENTIONCOUNT, user.getAttentionCount());
        valus.put(DBContent.DBUser.USER_ISSIGN, user.getIsSign());
        valus.put(DBContent.DBUser.USER_ISATTENTION, user.getIsAttention());
        valus.put(DBContent.DBUser.USER_ISEREDAR, user.getIsEredar());
        valus.put(DBContent.DBUser.USER_ISLOGINUSER, user.getIsLoginUser());
        if (user.getUserRole() != null && user.getUserRole().size() > 0) {
            valus.put(DBContent.DBUser.USER_EREDARCODE, user.getUserRole().get(0).getEredarCode());
            valus.put(DBContent.DBUser.USER_EREDARNAME, user.getUserRole().get(0).getEredarName());
        }
        getContentResolver().insert(DBContent.DBUser.CONTENT_URI, valus);

        showShortToast(obj.getMsg());
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Intent intent1 = new Intent(this, HomeActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);
        finish();
    }

}
