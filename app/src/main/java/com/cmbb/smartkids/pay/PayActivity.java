package com.cmbb.smartkids.pay;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.adapter.PayWayAdapter;
import com.cmbb.smartkids.activity.order.model.PayWayModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class PayActivity extends BaseActivity {
    private final String TAG = PayActivity.class.getSimpleName();

    // 商户PID
    public static final String PARTNER = "2088021604444292";
    // 商户收款账号
    public static final String SELLER = "ios_android_smart@smart-kids.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANBP6Nl0z1xXrGNk0FuC4mjvEva3HwETYL8gQkgLyNwknMvGaiO9G0m44RHu5KgDSAqYJZasBcuH2BK7GBT71Rd5/uIscYliC6lyFK5WQLSSaA397DFu7zTZ/ZJAGlb/GOobwUAuPq86daLe2/j5sP3McP/Fvng99w2fv9+qQFrPAgMBAAECgYEAjIEZoXL0WmiYUgIxt0e0xupadCZXtzYGz2NG0amBNVtMlvWhqbFnsnYM+TeU6u4rrVmqINKupfVefGPNfnnN4+v8AvN5e+of07oC/kYW63mWjJAGerqjch7/kTSCqexodGbJctwTwiVI+QvIdTCXqJxdhx2dfj5BAk/Rwl4BKGECQQDyAn//T2OaZXz69LTBp5PfdLGtwtF2EX9mHb3ZeCkeziMcvB6T2ctGQrehFK0reH1ERmaUl6QaeZNYxYA7q+3lAkEA3Fq4B6wrjOFTxCgybAFun83puPeTn9RAwTas4WMnyf5H3ogTRzE4Cbl+9lPzSOY8K79efvvPvRqO9YdAajo6owJBAM5+QZant5XyyHwcteqSwQKmQEDB/RVgArMv52CaPYPSYXVQMkr3R5GwtZwU11lDGqdZ5ocdCGGqoIXbJvpDTd0CQQC6Uxt5oWPR5FZob3TzTuKzzfHrrazuYRPATPMyQh3K93DeAkIK2NuBnZB1ydbVtZj7hP3qchLe3C41/v/A0yvxAkBrM3KH7XgitJ/WaWsQLaEESZO2RdCgRrmGLwDM+ZiuMw2riuwHR1anfXXZLKrLSQesX7OGMBAOzc+Qxy7iAM5w";
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQT+jZdM9cV6xjZNBbguJo7xL2tx8BE2C/IEJIC8jcJJzLxmojvRtJuOER7uSoA0gKmCWWrAXLh9gSuxgU+9UXef7iLHGJYgupchSuVkC0kmgN/ewxbu802f2SQBpW/xjqG8FALj6vOnWi3tv4+bD9zHD/xb54PfcNn7/fqkBazwIDAQAB";
    private static final int SDK_PAY_FLAG = 1;

    private static final int SDK_CHECK_FLAG = 2;

    private RecyclerView rv;
    private PayWayAdapter adapter;
    private ArrayList<String> headerData;
    private ArrayList<PayWayModel.PayTypesEntity> data;
    private String orderCode;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("order_code", orderCode);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    Toast.makeText(PayActivity.this, "检查结果为：" + msg.obj, Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_way;
    }



    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }


    private void initView(){
        setTitle(getString(R.string.title_activity_pay_way));
        setActionBarRight("check");
        rv = (RecyclerView) findViewById(R.id.rv_order_pay);
        adapter = new PayWayAdapter();
        headerData = new ArrayList<>();
        data = new ArrayList<>();
        adapter.setData(headerData, data);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private void initData(){
        Bundle bundle = null;
        if(getIntent() != null && (bundle = getIntent().getExtras()) != null){
            String title = bundle.getString("order_title");
            String price = bundle.getString("order_price");
            orderCode = bundle.getString("orderCode");
            if(!TextUtils.isEmpty(title)) headerData.add(title);
            if(!TextUtils.isEmpty(price)) headerData.add(price);
            if(!TextUtils.isEmpty(orderCode)) {
                handleRequest(orderCode);
            }else{
                showShortToast("传参出错~");
            }
        }else{
            showShortToast("传参出错~");
        }
    }


    private void addListener(){
        adapter.setOnBottomListener(onBottomListener);
        adapter.setOnItemClick(onItemListener);
    }

    private CustomListener.ItemClickListener onItemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            adapter.setSelectPos(position);
        }
    };


    private View.OnClickListener onBottomListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /**
             * call alipay sdk pay. 调用SDK支付
             */
            if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
                new AlertDialog.Builder(PayActivity.this)
                        .setTitle("警告")
                        .setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialoginterface, int i) {
                                //
                                finish();
                            }
                        }).show();
                return;
            }
            // 订单
           /* String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");

            // 对订单做RSA 签名
            String sign = sign(orderInfo);
            Log.e("sign", "sign = " + sign);
            try {
                // 仅需对sign 做URL编码
                sign = URLEncoder.encode(sign, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // 完整的符合支付宝参数规范的订单信息
            final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();*/

            Log.e(TAG, "paymentData : " + data.get(adapter.getSelectPos()).getPaymentData());

            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    // 构造PayTask 对象
                    PayTask alipay = new PayTask(PayActivity.this);
                    // 调用支付接口，获取支付结果
                    String result = alipay.pay(data.get(adapter.getSelectPos()).getPaymentData());
                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };

            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();

        }
    };


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.tv_main_toolbar_right){
            /**
             * check whether the device has authentication alipay account.
             * 查询终端设备是否存在支付宝认证账户
             */
            Runnable checkRunnable = new Runnable() {

                @Override
                public void run() {
                    // 构造PayTask 对象
                    PayTask payTask = new PayTask(PayActivity.this);
                    // 调用查询接口，获取查询结果
                    boolean isExist = payTask.checkAccountIfExist();

                    Message msg = new Message();
                    msg.what = SDK_CHECK_FLAG;
                    msg.obj = isExist;
                    mHandler.sendMessage(msg);
                }
            };

            Thread checkThread = new Thread(checkRunnable);
            checkThread.start();
        }
    }

    /**
     * get the sdk version. 获取SDK版本号
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * create the order info. 创建订单信息
     */
    public String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    public String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
                Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    public String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }





    private void handleRequest(String orderCode){
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("orderCode", orderCode);
        params.put("paymentTypeId", "1");
        NetRequest.postRequest(Constants.ServiceInfo.PAY_WAY_LIST, BaseApplication.token, params, PayWayModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                PayWayModel payModel = (PayWayModel) object;
                data.addAll(payModel.getPayTypes());
                adapter.setData(headerData, data);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

}
