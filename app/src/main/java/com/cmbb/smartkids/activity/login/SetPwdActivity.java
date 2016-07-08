package com.cmbb.smartkids.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.utils.CustomWatcher;

public class SetPwdActivity extends BaseActivity {

    private static final String TAG = SetPwdActivity.class.getSimpleName();
    private EditText etPwd, etPwdComfirm;
    private ImageView ivPwd, ivPwdComfirm;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
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
        Intent intent = new Intent("com.cmbb.smartkids.login.set.psw");
        intent.putExtra("phone", getIntent().getExtras().getString("phone"));
        intent.putExtra("psw", pwdConfirm);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        finish();
    }

    public static void newIntent(Context context, String phone) {
        Intent intent = new Intent(context, SetPwdActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }
}
