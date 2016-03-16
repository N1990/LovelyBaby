package com.cmbb.smartkids.activity.home.home_v2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.utils.log.Log;
import com.cmbb.smartkids.widget.jsbridge.BridgeHandler;
import com.cmbb.smartkids.widget.jsbridge.BridgeWebView;
import com.cmbb.smartkids.widget.jsbridge.CallBackFunction;
import com.cmbb.smartkids.widget.jsbridge.DefaultHandler;
import com.google.gson.Gson;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/15 上午11:41
 */
public class SignActivity extends BaseActivity {

    private static final String TAG = SignActivity.class.getSimpleName();
    BridgeWebView webView;

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("签到");
        webView = (BridgeWebView) findViewById(R.id.webView);
        webView.setDefaultHandler(new DefaultHandler());
        webView.setWebChromeClient(new WebChromeClient() {

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
                this.openFileChooser(uploadMsg);
            }

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
                this.openFileChooser(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {

            }
        });

        webView.loadUrl(Constants.H5.SMART_SIGN);//+ "?token=" + BaseApplication.token
        webView.registerHandler("showAdLink", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i(TAG, "handler = submitFromWeb, data from web = " + data);
                function.onCallBack("Android");
            }

        });



        webView.callHandler("showMsg", "萌宝派 h5 test", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Log.i("form JS", data);
            }
        });

        H5SignInitModel h5SignInitModel = new H5SignInitModel();
        h5SignInitModel.setToken(BaseApplication.token);
        String sendSiginInit = new Gson().toJson(h5SignInitModel);
        webView.callHandler("getParams", sendSiginInit, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Log.i("H5数据", data);
            }
        });
        webView.send("hello");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign;
    }

    /**
     * 启动入口
     *
     * @param context Context
     */
    public static void newIntent(Context context) {
        Intent intent = new Intent(context, SignActivity.class);
        context.startActivity(intent);
    }

    class H5SignInitModel {
        String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
