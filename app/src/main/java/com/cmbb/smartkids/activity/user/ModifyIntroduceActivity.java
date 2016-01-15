package com.cmbb.smartkids.activity.user;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.model.ModifyUserModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.db.DBContent;
import com.cmbb.smartkids.network.NetRequest;

import java.util.HashMap;

public class ModifyIntroduceActivity extends BaseActivity implements TextWatcher{
    private final String TAG = ModifyIntroduceActivity.class.getSimpleName();
    private EditText etContent;
    private TextView tvLimit;
    private int realLen;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_introduce;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
    }


    private void initView(){
        setTitle(getString(R.string.title_activity_modify_introduce));
        etContent = (EditText) findViewById(R.id.et_modify_introduce);
        tvLimit = (TextView) findViewById(R.id.tv_limit_introduce);
        findViewById(R.id.tv_modify_introduce_submit).setOnClickListener(this);
        etContent.addTextChangedListener(this);
    }

    private void initData(){
        if(getIntent() != null){
            String content = getIntent().getStringExtra("introduce");
            if(!TextUtils.isEmpty(content))
                etContent.setText(content);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        realLen = s.length();
        if(realLen <= 100){
            tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvLimit.setText(realLen + "/100");
        }else if(realLen < 150 && realLen > 100){
            tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvLimit.setText("还剩余" + (150 - realLen) + "个");
        }else if(realLen == 150){
            tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvLimit.setText("文字已输满");
        }else{
            tvLimit.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            tvLimit.setText("超过规定字数"+(realLen - 150) + "个");
        }
    }


    @Override
    public void onClick(View v) {
        String content = etContent.getText().toString();
        if(TextUtils.isEmpty(content)){
            showShortToast("个人介绍不能为空~");
            return;
        }
        handleRequest(content);
    }


    /**
     * 修改个人介绍
     * @param introduce
     */
    private void handleRequest(final String introduce){
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("userPresentation", introduce);
        NetRequest.postRequest(Constants.ServiceInfo.MODIFY_USER_INFO, BaseApplication.token, params, ModifyUserModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                ModifyUserModel modify = (ModifyUserModel) object;
                if (modify != null && modify.getData() != null) {
                    ContentValues valus = new ContentValues();
                    valus.put(DBContent.DBUser.USER_INTRODUCE, modify.getData().getUserPresentation());
                    getContentResolver().update(DBContent.DBUser.CONTENT_URI, valus, DBContent.DBUser.USER_ID + " = " + modify.getData().getUserId(), null);
                    showShortToast("修改成功");
                    Intent intent = new Intent(ModifyIntroduceActivity.this, MySetActivity.class);
                    intent.putExtra("introduce", introduce);
                    setResult(RESULT_OK, intent);
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
}
