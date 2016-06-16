package com.cmbb.smartkids.wxapi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.utils.SPCache;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private static final String TAG = WXPayEntryActivity.class.getSimpleName();

    private IWXAPI api;

    private TextView tvPayResult;

    private ImageView ivPayResult;

    private boolean isSuccessful;

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_payresult));
        ivPayResult = (ImageView) findViewById(R.id.iv_pay_result);
        tvPayResult = (TextView) findViewById(R.id.tv_pay_result);

        api = WXAPIFactory.createWXAPI(this, SPCache.getString(Constants.APP_ID, ""));
        api.registerApp(SPCache.getString(Constants.APP_ID, ""));
        Log.e(TAG, SPCache.getString(Constants.APP_ID, ""));
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pay_result;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


	/*13818155072
    smart12345*/

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int result = R.string.errcode_unknown;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_tip);
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    isSuccessful = true;
                    result = R.string.errcode_success;
                    ivPayResult.setImageResource(R.mipmap.btn_success_bg);
                    tvPayResult.setText("支付成功");
                    showShortToast(getString(result));
                    return;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    isSuccessful = false;
                    ivPayResult.setImageResource(R.mipmap.btn_failure_bg);
                    tvPayResult.setText("取消支付");
                    result = R.string.errcode_cancel;
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    isSuccessful = false;
                    ivPayResult.setImageResource(R.mipmap.btn_failure_bg);
                    tvPayResult.setText("支付失败");
                    result = R.string.errcode_deny;
                    break;
                default:
                    isSuccessful = false;
                    break;
            }
            builder.setMessage(getString(result));
            builder.show();
        }
    }

    @Override
    public void onBackPressed() {
        if (isSuccessful) {
            Intent intent = new Intent("com.cbmm.smartkids.pay");
            sendBroadcast(intent);
            finish();
        } else {
            super.onBackPressed();
        }

    }
}