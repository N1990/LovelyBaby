package com.cmbb.smartkids.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.login.model.ValidCodeModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.utils.MyTimeCount;
import com.cmbb.smartkids.utils.Tools;
import com.squareup.okhttp.Request;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/6/29 下午3:50
 * 修改人：N.Sun
 * 修改时间：16/6/29 下午3:50
 * 修改备注：
 */
public class BundlePhoneActivity extends BaseActivity {

    private static final String TAG = BundlePhoneActivity.class.getSimpleName();

    private TextView tvVerifyPhoneCode;
    private TextView tvVerifySubmit;
    private EditText etVerifyPhone;
    private EditText etVerifyCode;

    private MyTimeCount timeCount;

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("绑定手机号码");
        tvVerifyPhoneCode = (TextView) findViewById(R.id.tv_verify_phone_code);
        tvVerifySubmit = (TextView) findViewById(R.id.tv_verify_submit);
        etVerifyPhone = (EditText) findViewById(R.id.et_verify_phone);
        etVerifyCode = (EditText) findViewById(R.id.et_verify_code);
        tvVerifyPhoneCode.setOnClickListener(this);
        tvVerifySubmit.setOnClickListener(this);
        timeCount = new MyTimeCount(60000, 1000, tvVerifyPhoneCode);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_verify_phone_code:
                if (TextUtils.isEmpty(etVerifyPhone.getText().toString().trim())) {
                    showToast("请输入手机号码");
                    return;
                }
                if (!Tools.isMobileNo(etVerifyPhone.getText().toString().trim())) {
                    showToast("请输入正确的手机号码");
                    return;
                }
                SecurityCodeModel.getPhoneCodeRequest(etVerifyPhone.getText().toString().trim(), new OkHttpClientManager.ResultCallback<SecurityCodeModel>() {
                    @Override
                    public void onError(Request request, Exception e, String msg) {

                        tvVerifyPhoneCode.setEnabled(true);
                        if (TextUtils.isEmpty(msg)) {
                            showShortToast(getString(R.string.is_netwrok));
                            Log.e(TAG, e.toString());
                        } else {
                            showShortToast(msg);
                        }
                    }

                    @Override
                    public void onResponse(SecurityCodeModel response) {
                        if (response != null) {
                            showToast(response.getMsg());
                            tvVerifyPhoneCode.setEnabled(false);
                            timeCount.start();
                        }
                    }
                });
                break;
            case R.id.tv_verify_submit:
                if (TextUtils.isEmpty(etVerifyPhone.getText().toString().trim())) {
                    showToast("请输入手机号码");
                    return;
                }
                if (!Tools.isMobileNo(etVerifyPhone.getText().toString().trim())) {
                    showToast("请输入正确的手机号码");
                    return;
                }
                if (TextUtils.isEmpty(etVerifyCode.getText().toString().trim())) {
                    showToast("请输入手机验证码");
                    return;
                }

                showWaitDialog();
                ValidCodeModel.handleValidCodeRequest(etVerifyPhone.getText().toString().trim(), etVerifyCode.getText().toString().trim(), new OkHttpClientManager.ResultCallback<ValidCodeModel>() {
                    @Override
                    public void onError(Request request, Exception e, String response) {
                        hideWaitDialog();
                        if (TextUtils.isEmpty(response)) {
                            showShortToast(getString(R.string.is_netwrok));
                            Log.i("err", e.toString());
                        } else {
                            showShortToast(response);
                        }
                    }

                    @Override
                    public void onResponse(ValidCodeModel response) {
                        if (response != null && response.getData().getHasPassword().equals("true")) {
                            Intent intent = new Intent();
                            intent.putExtra("phone", etVerifyPhone.getText().toString().trim());
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            //设置密码
                            SetPwdActivity.newIntent(BundlePhoneActivity.this, etVerifyPhone.getText().toString().trim());
                            finish();
                        }
                    }
                });
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bundle_phone_layout;
    }

    public static void newIntent(BaseActivity context, int requestCode) {
        Intent intent = new Intent(context, BundlePhoneActivity.class);
        context.startActivityForResult(intent, requestCode);
    }
}
