package com.cmbb.smartkids.activity.login;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home.HomeActivity;
import com.cmbb.smartkids.activity.login.model.OpenIdModel;
import com.cmbb.smartkids.activity.login.model.UserAssistModel;
import com.cmbb.smartkids.activity.login.model.UserRootModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.db.DBContent;
import com.cmbb.smartkids.db.DBHelper;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.utils.CustomWatcher;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.ShareUtils;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.Tools;
import com.squareup.okhttp.Request;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.UMSsoHandler;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {
    private final String TAG = LoginActivity.class.getSimpleName();
    private EditText etPhone, etPwd;
    private ImageView ivPhoneClear, ivPwdClear;
    private LinearLayout llBottom;
    private UMSocialService mController;
    private SHARE_MEDIA media;
    private String openId;
    private String uid;
    private String userName;
    private DBHelper dbHelper;  //临时逻辑
    private int platform = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        assignViews();
        initData();
        addListener();
        LocalBroadcastManager.getInstance(this).registerReceiver(setPswLoginReceiver, new IntentFilter("com.cmbb.smartkids.login.set.psw"));
    }

    private void initData() {
        dbHelper = new DBHelper(this);
        mController = ShareUtils.instanceOf(this);
    }

    private void addListener() {
        new CustomWatcher(etPhone, ivPhoneClear);
        new CustomWatcher(etPwd, ivPwdClear);
    }

    private void assignViews() {
        setTitle(getString(R.string.title_activity_login));
        etPhone = (EditText) findViewById(R.id.et_login_phone);
        ivPhoneClear = (ImageView) findViewById(R.id.iv_login_phone_clear);
        etPwd = (EditText) findViewById(R.id.et_login_pwd);
        ivPwdClear = (ImageView) findViewById(R.id.iv_login_pwd_clear);
        llBottom = (LinearLayout) findViewById(R.id.ll_login_bottom);
        findViewById(R.id.tv_login_submit).setOnClickListener(this);
        findViewById(R.id.iv_login_wx).setOnClickListener(this);
        findViewById(R.id.iv_login_qq).setOnClickListener(this);
        findViewById(R.id.iv_login_sina).setOnClickListener(this);
        findViewById(R.id.tv_forget).setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tv_login_register) {
            Intent intent = new Intent(LoginActivity.this, VerifyActivity.class);
            intent.putExtra("tag", "register");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login_wx:
                platform = 2;
                media = SHARE_MEDIA.WEIXIN;
                ShareUtils.addWXPlatform();
                authToPlatform();
                break;
            case R.id.iv_login_qq:
                platform = 1;
                media = SHARE_MEDIA.QQ;
                ShareUtils.addQQQZonePlatform();
                authToPlatform();
                break;
            case R.id.iv_login_sina:
                platform = 3;
                media = SHARE_MEDIA.SINA;
                authToPlatform();
                break;
            case R.id.tv_login_submit:
                platform = 0;
                String phone = etPhone.getText().toString();
                String pwd = etPwd.getText().toString();
                handleLogin(phone, pwd);
                break;

            case R.id.tv_forget:
                Intent intent = new Intent(LoginActivity.this, VerifyActivity.class);
                intent.putExtra("tag", "forget");
                startActivity(intent);
                break;
        }
    }

    private void handleLogin(String phone, String pwd) {
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号码");
            return;
        }
        if (!Tools.isMobileNo(phone)) {
            showToast("请输入正确的手机号码");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }
        if (!Tools.isPswNo(pwd)) {
            showToast("请输入正确的密码");
            return;
        }
        showWaitDialog();
        HashMap<String, String> body = new HashMap<>();
        body.put("loginAccount", phone);
        body.put("loginPassword", pwd);
        body.put("device", 2 + "");
        body.put("deviceVersion", android.os.Build.VERSION.RELEASE);
        body.put("model", android.os.Build.MODEL);
        body.put("imei", TDevice.getDeviceId(this));
        body.put("applicationVersion", TDevice.getVersionName());
        body.put("thirdType", platform + "");
        NetRequest.postRequest(Constants.ServiceInfo.LOGIN_REQUEST, "", body, UserAssistModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                Log.i(TAG, "message = " + msg);
                UserAssistModel obj = (UserAssistModel) object;
                if (null != obj && obj.getData() != null) {
                    //删除表
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    dbHelper.delete(db);
                    UserRootModel user = obj.getData();
                    SPCache.putString(Constants.TOKEN, user.getToken());
                    SPCache.putString(Constants.USER_ID, user.getUserId() + "");
                    // 更改Application里面的值
                    BaseApplication.token = user.getToken();
                    // 发送信息注册Alias
                    Intent intent = new Intent(BaseApplication.PUSH_ALIAS_ITENTACTION);
                    intent.putExtra("umeng_id", user.getUserId() + "_" + TDevice.getDeviceId(LoginActivity.this));
                    intent.putExtra("umeng_type", "service");
                    LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(intent);
                    LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(new Intent(BaseApplication.PUSH_ALIAS_RESUME_REQUEST));
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
                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
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

    private void handleBundleThreeLogin(String phone, String openId) {
        showWaitDialog();
        HashMap<String, String> body = new HashMap<>();
        body.put("loginAccount", phone);
        body.put("openId", openId);
        if (!TextUtils.isEmpty(uid))
            body.put("unionId", uid);//添加uid
        Log.i("openId", "openId = " + openId);
        Log.i("uid", "uid = " + uid);
        body.put("userNike", userName);
        body.put("device", 2 + "");
        body.put("deviceVersion", android.os.Build.VERSION.RELEASE);
        body.put("model", android.os.Build.MODEL);
        body.put("imei", TDevice.getDeviceId(this));
        body.put("applicationVersion", TDevice.getVersionName());
        body.put("thirdType", platform + "");
        NetRequest.postRequest(Constants.ServiceInfo.LOGIN_REQUEST, "", body, UserAssistModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                Log.i(TAG, "message = " + msg);
                UserAssistModel obj = (UserAssistModel) object;
                if (null != obj && obj.getData() != null) {
                    //删除表
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    dbHelper.delete(db);
                    UserRootModel user = obj.getData();
                    SPCache.putString(Constants.TOKEN, user.getToken());
                    SPCache.putString(Constants.USER_ID, user.getUserId() + "");
                    // 更改Application里面的值
                    BaseApplication.token = user.getToken();
                    // 发送信息注册Alias
                    Intent intent = new Intent(BaseApplication.PUSH_ALIAS_ITENTACTION);
                    intent.putExtra("umeng_id", user.getUserId() + "_" + TDevice.getDeviceId(LoginActivity.this));
                    intent.putExtra("umeng_type", "service");
                    LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(intent);
                    LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(new Intent(BaseApplication.PUSH_ALIAS_RESUME_REQUEST));
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
                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
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

    private void handleUnBundleThreeLogin(String phone, String psw) {
        showWaitDialog();
        HashMap<String, String> body = new HashMap<>();
        body.put("loginAccount", phone);
        if (!TextUtils.isEmpty(psw))
            body.put("loginPassword", psw);
        body.put("openId", openId);
        if (!TextUtils.isEmpty(uid))
            body.put("unionId", uid);//添加uid
        Log.i("openId", "openId = " + openId);
        Log.i("uid", "uid = " + uid);
        body.put("userNike", userName);
        body.put("device", 2 + "");
        body.put("deviceVersion", android.os.Build.VERSION.RELEASE);
        body.put("model", android.os.Build.MODEL);
        body.put("imei", TDevice.getDeviceId(this));
        body.put("applicationVersion", TDevice.getVersionName());
        body.put("thirdType", platform + "");
        NetRequest.postRequest(Constants.ServiceInfo.LOGIN_REQUEST, "", body, UserAssistModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                Log.i(TAG, "message = " + msg);
                UserAssistModel obj = (UserAssistModel) object;
                if (null != obj && obj.getData() != null) {
                    //删除表
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    dbHelper.delete(db);
                    UserRootModel user = obj.getData();
                    SPCache.putString(Constants.TOKEN, user.getToken());
                    SPCache.putString(Constants.USER_ID, user.getUserId() + "");
                    // 更改Application里面的值
                    BaseApplication.token = user.getToken();
                    // 发送信息注册Alias
                    Intent intent = new Intent(BaseApplication.PUSH_ALIAS_ITENTACTION);
                    intent.putExtra("umeng_id", user.getUserId() + "_" + TDevice.getDeviceId(LoginActivity.this));
                    intent.putExtra("umeng_type", "service");
                    LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(intent);
                    LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(new Intent(BaseApplication.PUSH_ALIAS_RESUME_REQUEST));
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
                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
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

    /**
     * 第三方平台授权
     */
    private void authToPlatform() {
        mController.doOauthVerify(this, media, new SocializeListeners.UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.e(TAG, "i come here");
            }

            @Override
            public void onComplete(Bundle bundle, SHARE_MEDIA share_media) {
                if (bundle != null && !TextUtils.isEmpty(bundle.getString("uid"))) {
                    openId = bundle.getString("uid");
                    if (!TextUtils.isEmpty(bundle.getString("unionid"))) {
                        uid = bundle.getString("unionid");
                    }
                    Log.e("bundle", "bundle = " + bundle.toString());
                    mController.getPlatformInfo(LoginActivity.this, media, uMDataListener);
                } else {
                    showShortToast("授权失败");
                }
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA share_media) {
                Log.i("err", e.toString());
                showShortToast("授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                showShortToast("已取消授权");
            }
        });
    }

    /**
     * 第三方登录
     * 获取来自第三方用户的信息
     */
    private SocializeListeners.UMDataListener uMDataListener = new SocializeListeners.UMDataListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onComplete(int status, Map<String, Object> info) {
            if (media == SHARE_MEDIA.SINA || media == SHARE_MEDIA.QQ) {
                userName = info.get("screen_name").toString();
            } else if (media == SHARE_MEDIA.WEIXIN) {
                userName = info.get("nickname").toString();
            }
            SPCache.putString(Constants.SharePreference.OPEN_ID, openId);
            SPCache.putString(Constants.SharePreference.SCREEN_NAME, userName);
            //处理绑定的问题
            OpenIdModel.handleOpenIdRequest(openId, new OkHttpClientManager.ResultCallback<OpenIdModel>() {
                @Override
                public void onError(Request request, Exception e, String msg) {
                    if (TextUtils.isEmpty(msg)) {
                        showShortToast(getString(R.string.is_netwrok));
                        Log.i("err", e.toString());
                    } else {
                        showShortToast(msg);
                    }
                }

                @Override
                public void onResponse(OpenIdModel response) {
                    if (response != null && !TextUtils.isEmpty(response.getData().getLoginAccount())) {
                        handleBundleThreeLogin(response.getData().getLoginAccount(), openId);
                    } else {
                        BundlePhoneActivity.newIntent(LoginActivity.this, 150);
                    }
                }
            });

        }
    };

    BroadcastReceiver setPswLoginReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //设置密码登陆
            handleUnBundleThreeLogin(intent.getStringExtra("phone"), intent.getStringExtra("psw"));
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 150 && resultCode == RESULT_OK) {
            //有密码登陆
            handleUnBundleThreeLogin(data.getStringExtra("phone"), "");
            return;
        }
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}