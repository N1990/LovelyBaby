package com.cmbb.smartkids.activity.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;

import java.util.HashMap;

public class SuggestActivity extends BaseActivity implements TextWatcher{

    private EditText etCotent;
    private TextView tvLimit;
    private int realLen = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_suggest;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_suggest));
        etCotent = (EditText) findViewById(R.id.et_cotent_suggest);
        tvLimit = (TextView) findViewById(R.id.tv_limit_suggest);
        etCotent.addTextChangedListener(this);
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
        if(realLen <= 450){
            tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvLimit.setText(realLen + "/500");
        }else if(realLen < 500 && realLen > 450){
            tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvLimit.setText("还剩余" + (500 - realLen) + "个");
        }else if(realLen == 500){
            tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvLimit.setText("文字已输满");
        }else{
            tvLimit.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            tvLimit.setText("超过规定字数"+(realLen - 500) + "个");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_suggest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_suggest) {
            if(realLen > 500 || realLen == 0){
                showShortToast("输入的字数不符合要求");
                return true;
            }
           handleSuggestRequest(etCotent.getText().toString());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void handleSuggestRequest(String contents){
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("contents", contents);
        NetRequest.postRequest(Constants.ServiceInfo.FEEDBACK_SUGGEST_REQUEST, BaseApplication.token, params, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
//                SecurityCodeModel data = (SecurityCodeModel) object;
                showShortToast(msg);
                finish();
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);

            }
        }));
    }

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, SuggestActivity.class);
        context.startActivity(intent);
    }
}
