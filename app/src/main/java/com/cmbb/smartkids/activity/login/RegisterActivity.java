package com.cmbb.smartkids.activity.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.db.DBContent;
import com.cmbb.smartkids.activity.home.HomeActivity;
import com.cmbb.smartkids.activity.login.model.UserRootModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.activity.login.model.UserAssistModel;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.db.DBHelper;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.CustomWatcher;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.log.Log;
import com.cmbb.smartkids.widget.wheelview.LocationSelectorDialogBuilder;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity implements LocationSelectorDialogBuilder.OnSaveLocationLister {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private EditText etNickname, etCity, etPwd, etPwdComfirm;
    private ImageView ivNickname, ivPwd, ivPwdComfirm;
    private LocationSelectorDialogBuilder locationBuilder;
    // 地址参数
    private String provinceId;
    private String cityId;
    private String areaId;
    private DBHelper dbHelper;  //临时逻辑


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        addListener();
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_register));
        dbHelper = new DBHelper(this);
        etNickname = (EditText) findViewById(R.id.et_register_nickname);
        ivNickname = (ImageView) findViewById(R.id.iv_register_nickname);
        etCity = (EditText) findViewById(R.id.et_register_city);
        etPwd = (EditText) findViewById(R.id.et_register_pwd);
        ivPwd = (ImageView) findViewById(R.id.iv_register_pwd_clear);
        etPwdComfirm = (EditText) findViewById(R.id.et_registser_pwd_comfirm);
        ivPwdComfirm = (ImageView) findViewById(R.id.iv_register_pwd_comfirm_clear);

    }

    private void addListener() {
        findViewById(R.id.tv_register_finish).setOnClickListener(this);
        findViewById(R.id.tv_register_city).setOnClickListener(this);
        new CustomWatcher(etNickname, ivNickname);
        new CustomWatcher(etPwd, ivPwd);
        new CustomWatcher(etPwdComfirm, ivPwdComfirm);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_register_city) {
            if (locationBuilder == null) {
                locationBuilder = LocationSelectorDialogBuilder.getInstance(this);
                locationBuilder.setOnSaveLocationLister(this);
            }
            locationBuilder.show();
        } else if (id == R.id.tv_register_finish) {
            String nickeName = etNickname.getText().toString();
            String city = etCity.getText().toString();
            String pwd = etPwd.getText().toString();
            String pwdComfirm = etPwdComfirm.getText().toString();
            if (TextUtils.isEmpty(nickeName)) {
                showShortToast("请输入您的昵称");
                return;
            }
            if (TextUtils.isEmpty(city)) {
                showShortToast("请选择您所在的城市");
                return;
            }
            if (TextUtils.isEmpty(pwd)) {
                showShortToast("请输入您的密码");
                return;
            }
            if (TextUtils.isEmpty(pwdComfirm)) {
                showShortToast("请再次输入您的密码");
                return;
            }
            if (!pwd.equals(pwdComfirm)) {
                showShortToast("您两次输入的密码不一致");
                return;
            }
            handleRequest(nickeName, pwd);
        }
    }


    private void handleRequest(String nickeName, String pwd) {
        /*{
                "messageId": null,
                    "requestId": "2d79f323-575d-11e5-909e-1a376e9a916d",
                    "error": null,
                    "statusCode": 200,
                    "cmd": "smart/register",
                    "response": {
                "status": 1,
                        "data": {
                    "token": "d860ea1fa2b243228eb06a54b8961e04/33ad7e90c3664443b9ae0e721551e05f",
                            "userId": 8,
                            "userRole": [
                    {
                        "id": 3,
                            "properties": {

                    },
                        "userBasicInfo": {
                        "id": 8,
                                "properties": {

                        },
                        "userNike": "niesen918",
                                "userSex": null,
                                "userBigImg": null,
                                "userBigWidth": null,
                                "userBigHeight": null,
                                "userSmallImg": null,
                                "userSmallWidth": null,
                                "userSmallHeight": null,
                                "loginAccountType": 0,
                                "loginTime": "2015-09-10 09:42:21",
                                "loginAccount": "15201921714",
                                "loginPassword": "e10adc3949ba59abbe56e057f20f883e",
                                "loginAccessToken": null,
                                "loginToken": "d860ea1fa2b243228eb06a54b8961e04",
                                "isShutup": 0,
                                "shutupTime": null,
                                "isBanned": 0,
                                "userAddress": null,
                                "userPhone": "15201921714",
                                "userPhoneVersion": null,
                                "province": 320000,
                                "district": 320102,
                                "userLevel": 1,
                                "userPresentation": null,
                                "isRecommoned": null,
                                "createDate": "2015-09-10 09:42:21",
                                "createUserId": null,
                                "updateDate": null,
                                "updateUserId": null,
                                "city": null
                    },
                        "eredarCode": 0,
                            "eredarName": "萌宝用户",
                            "createDate": "2015-09-10 09:42:21",
                            "createUserId": null,
                            "updateDate": null,
                            "updateUserId": null
                    }
                    ]
                },
                "msg": "登录成功"
            },
                "responseTime": "2015-09-10 09:42:21",
                    "responseTimestamp": 1441849341791,
                    "duration": 670,
                    "debugInfo": null,
                    "clientIp": "192.168.100.115",
                    "usedCache": false,
                    "extraInfo": {
                "consuming": "670毫秒",
                        "serverName": "192.168.100.135"
            },
                "auth": {

            }
            }*/
        showWaitDialog();
        HashMap<String, String> body = new HashMap<>();
        body.put("loginAccount", getIntent().getStringExtra("phone"));
        body.put("loginPassword", pwd);
        body.put("userNike", nickeName);
        body.put("province", provinceId);
        body.put("cityId", cityId);
        body.put("district", areaId);
        body.put("device", 2 + "");
        body.put("deviceVersion", android.os.Build.VERSION.RELEASE);
        body.put("model", android.os.Build.MODEL);
        body.put("imei", TDevice.getDeviceId(this));
        body.put("applicationVersion", TDevice.getVersionName());
        body.put("thirdType", 0 + "");
        NetRequest.postRequest("smart/register", "", body, UserAssistModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                UserAssistModel obj = (UserAssistModel) object;
                if (obj != null && obj.getData() != null) {
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
                    intent.putExtra("umeng_id", user.getUserId() + "_" + TDevice.getDeviceId(RegisterActivity.this));
                    intent.putExtra("umeng_type", "service");
                    LocalBroadcastManager.getInstance(RegisterActivity.this).sendBroadcast(intent);
                    //写入数据库
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

                    getContentResolver().insert(DBContent.DBUser.CONTENT_URI, valus);
                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    showShortToast("注册成功");
                    startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
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

    @Override
    public void onSaveLocation(String local, String province, String city, String area, String provinceId, String cityId, String areaId) {
        etCity.setText(local);
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.areaId = areaId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationBuilder != null)
            locationBuilder.setDialogDismiss();
    }
}
