package com.cmbb.smartkids.activity.user;

import android.os.Bundle;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.model.HomeMessageModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;

import java.util.HashMap;

public class HomeMessageActivity extends BaseActivity {
    private final String TAG = HomeMessageActivity.class.getSimpleName();
    private TextView tvContent;
    private int id;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_message;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_home_message));
        tvContent = (TextView) findViewById(R.id.tv_home_message_content);
        if (getIntent() != null)
            id = getIntent().getIntExtra("id", -1);
        handleRequest(id);
    }


    private void handleRequest(int id) {
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id + "");
        NetRequest.postRequest(Constants.ServiceInfo.MESSAGE_HOME_REQUEST, BaseApplication.token, params, HomeMessageModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                HomeMessageModel result = (HomeMessageModel) object;
                if (result != null && result.getData() != null)
                    tvContent.setText(result.getData().getContents());
                showShortToast(msg);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

}
