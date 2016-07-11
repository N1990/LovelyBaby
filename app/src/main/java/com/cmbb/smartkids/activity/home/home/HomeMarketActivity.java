package com.cmbb.smartkids.activity.home.home;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.more.AboutActivity;
import com.cmbb.smartkids.activity.more.GrownValusActivity;
import com.cmbb.smartkids.activity.more.SuggestActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.db.DBContent;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.log.Log;
import com.cmbb.smartkids.widget.jsbridge.BridgeWebView;
import com.cmbb.smartkids.widget.jsbridge.DefaultHandler;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午4:55
 */
public class HomeMarketActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = HomeMarketActivity.class.getSimpleName();

    BridgeWebView webView;

    @Override
    protected void init(Bundle savedInstanceState) {
        webView = (BridgeWebView) findViewById(R.id.webView);
        webView.setDefaultHandler(new DefaultHandler());
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_market_layout;
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, HomeMarketActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_suggest_feek:
                SuggestActivity.newInstance(this);
                break;
            case R.id.tv_growth_values:
                GrownValusActivity.newInstance(this);
                break;
            case R.id.tv_about:
                AboutActivity.newInstance(this);
                break;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, DBContent.DBUser.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.getCount() > 0 && data.moveToFirst()) {
            String phone = data.getString(data.getColumnIndex(DBContent.DBUser.USER_LOGINACCOUNT));
            /*if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(SPCache.getString("show_market_url", ""))) {
                LoginActivity.newIntent(this);
            } else {
                Log.e(TAG, SPCache.getString("show_market_url", "") + "?userPhone=" + phone);
                webView.loadUrl(SPCache.getString("show_market_url", "") + "?userPhone=" + phone);
            }*/
            if (TextUtils.isEmpty(phone)) {
                webView.loadUrl(SPCache.getString("show_market_url", ""));
            } else {
                Log.e(TAG, SPCache.getString("show_market_url", "") + "?loginAccount=" + phone);
                webView.loadUrl(SPCache.getString("show_market_url", "") + "?loginAccount=" + phone);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
