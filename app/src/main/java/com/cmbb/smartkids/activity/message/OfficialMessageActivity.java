package com.cmbb.smartkids.activity.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.model.HomeMessageModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;

import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/12/9 下午4:54
 */
public class OfficialMessageActivity extends BaseActivity {

    protected TextView tvContent;

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("官方消息");
        tvContent = (TextView) findViewById(R.id.tv_content);
        /*if (!TextUtils.isEmpty(getIntent().getStringExtra("relateField"))) {
            handleRequest(getIntent().getStringExtra("relateField"));
        } else {
            showShortToast("程序出现错误");
        }*/
        tvContent.setText(getIntent().getStringExtra("content"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_official;
    }

    private void handleRequest(String id) {
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
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

    /**
     * 启动入口
     *
     * @param context Context
     */
    public static void newInstance(Context context, String content) {
        Intent intent = new Intent(context, OfficialMessageActivity.class);
        intent.putExtra("content", content);
        context.startActivity(intent);
    }


}
