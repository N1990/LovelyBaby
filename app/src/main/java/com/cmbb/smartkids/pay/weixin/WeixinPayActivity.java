package com.cmbb.smartkids.pay.weixin;

import android.os.Bundle;
import android.widget.Toast;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.model.PayWayModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.squareup.okhttp.Request;
import com.tencent.mm.sdk.openapi.IWXAPI;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/24 下午7:19
 */
public class WeixinPayActivity extends BaseActivity {

    private IWXAPI api;
    private PayWayModel payWayModel;


    @Override
    protected void init(Bundle savedInstanceState) {
        String url = "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android";
        Toast.makeText(WeixinPayActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }


    /**
     * 支付方式
     *
     * @param orderCode String
     */
    private void handleRequest(String orderCode) {
        showWaitDialog();
        PayWayModel.getPayWayRequest(orderCode, new OkHttpClientManager.ResultCallback<PayWayModel>() {
            @Override
            public void onError(Request request, Exception e) {
                hideWaitDialog();
                showShortToast(getString(R.string.is_netwrok));
            }

            @Override
            public void onResponse(PayWayModel response) {
                hideWaitDialog();
                payWayModel = response;
            }
        });

    }


}
