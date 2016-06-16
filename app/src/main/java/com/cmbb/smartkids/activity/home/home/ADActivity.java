package com.cmbb.smartkids.activity.home.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.widget.jsbridge.BridgeWebView;
import com.cmbb.smartkids.widget.jsbridge.DefaultHandler;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/15 上午11:41
 */
public class ADActivity extends BaseActivity {

    private static final String TAG = ADActivity.class.getSimpleName();
    BridgeWebView webView;

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("广告");
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

        webView.loadUrl(getIntent().getStringExtra("url"));//+ "?token=" + BaseApplication.token
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
    public static void newIntent(Context context, String url) {
        Intent intent = new Intent(context, ADActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
