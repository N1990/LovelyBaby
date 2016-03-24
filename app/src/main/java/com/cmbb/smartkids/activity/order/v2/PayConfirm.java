package com.cmbb.smartkids.activity.order.v2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.model.GenerateOrderModel;
import com.cmbb.smartkids.activity.order.model.PayWayModel;
import com.cmbb.smartkids.activity.order.model.SubmitOrderModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.model.OrderStatus;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.pay.PayResult;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.okhttp.Request;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/16 上午11:47
 */
public class PayConfirm extends BaseActivity {
    private final String TAG = PayConfirm.class.getSimpleName();

    // 商户PID
    public static final String PARTNER = "2088021604444292";
    // 商户收款账号
    public static final String SELLER = "ios_android_smart@smart-kids.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANBP6Nl0z1xXrGNk0FuC4mjvEva3HwETYL8gQkgLyNwknMvGaiO9G0m44RHu5KgDSAqYJZasBcuH2BK7GBT71Rd5/uIscYliC6lyFK5WQLSSaA397DFu7zTZ/ZJAGlb/GOobwUAuPq86daLe2/j5sP3McP/Fvng99w2fv9+qQFrPAgMBAAECgYEAjIEZoXL0WmiYUgIxt0e0xupadCZXtzYGz2NG0amBNVtMlvWhqbFnsnYM+TeU6u4rrVmqINKupfVefGPNfnnN4+v8AvN5e+of07oC/kYW63mWjJAGerqjch7/kTSCqexodGbJctwTwiVI+QvIdTCXqJxdhx2dfj5BAk/Rwl4BKGECQQDyAn//T2OaZXz69LTBp5PfdLGtwtF2EX9mHb3ZeCkeziMcvB6T2ctGQrehFK0reH1ERmaUl6QaeZNYxYA7q+3lAkEA3Fq4B6wrjOFTxCgybAFun83puPeTn9RAwTas4WMnyf5H3ogTRzE4Cbl+9lPzSOY8K79efvvPvRqO9YdAajo6owJBAM5+QZant5XyyHwcteqSwQKmQEDB/RVgArMv52CaPYPSYXVQMkr3R5GwtZwU11lDGqdZ5ocdCGGqoIXbJvpDTd0CQQC6Uxt5oWPR5FZob3TzTuKzzfHrrazuYRPATPMyQh3K93DeAkIK2NuBnZB1ydbVtZj7hP3qchLe3C41/v/A0yvxAkBrM3KH7XgitJ/WaWsQLaEESZO2RdCgRrmGLwDM+ZiuMw2riuwHR1anfXXZLKrLSQesX7OGMBAOzc+Qxy7iAM5w";
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQT+jZdM9cV6xjZNBbguJo7xL2tx8BE2C/IEJIC8jcJJzLxmojvRtJuOER7uSoA0gKmCWWrAXLh9gSuxgU+9UXef7iLHGJYgupchSuVkC0kmgN/ewxbu802f2SQBpW/xjqG8FALj6vOnWi3tv4+bD9zHD/xb54PfcNn7/fqkBazwIDAQAB";
    private static final int SDK_PAY_FLAG = 1;

    private static final int SDK_CHECK_FLAG = 2;

    private RelativeLayout llHomeServiceItem;
    private SimpleDraweeView ivHomeServiceItem;
    private TextView tvOrderCode;
    private TextView tvTitle;
    private TextView tvAddress;
    private TextView tvPrice;
    private TextView tvCount;
    private RelativeLayout relativeLayout;
    private LinearLayout llContactPerson;
    private TextView tvNickTag;
    private TextView tvNick;
    private TextView tvOperation;
    private LinearLayout llContactPhone;
    private TextView tvPhoneTag;
    private TextView tvPhone;
    private TextView tvPhoneChange;
    private LinearLayout llContactAddress;
    private TextView tvAddressTag;
    private TextView tvAdd;
    private TextView tvAddressManager;
    private TextView tvWholePrice;
    private TextView tvSubmit;
    private TextView tvPayWayTag;
    private ImageView imageView;
    private SubmitOrderModel.DataEntity dataEntity;
    private GenerateOrderModel.DataEntity dataEntity02;
    private String orderCode;
    private PayWayModel payWayModel;

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
                        Toast.makeText(PayConfirm.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = getIntent();
                        intent.putExtra("order_status", OrderStatus.YI_ZHI_FU.getValue());
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayConfirm.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayConfirm.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    Toast.makeText(PayConfirm.this, "检查结果为：" + msg.obj, Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_pay_way));
        initView();
        initData();
    }

    private void initView() {
        llHomeServiceItem = (RelativeLayout) findViewById(R.id.ll_home_service_item);
        ivHomeServiceItem = (SimpleDraweeView) findViewById(R.id.iv_home_service_item);
        tvOrderCode = (TextView) findViewById(R.id.tv_order_code);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvCount = (TextView) findViewById(R.id.tv_count);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        llContactPerson = (LinearLayout) findViewById(R.id.ll_contact_person);
        tvNickTag = (TextView) findViewById(R.id.tv_nick_tag);
        tvNick = (TextView) findViewById(R.id.tv_nick);
        tvOperation = (TextView) findViewById(R.id.tv_operation);
        llContactPhone = (LinearLayout) findViewById(R.id.ll_contact_phone);
        tvPhoneTag = (TextView) findViewById(R.id.tv_phone_tag);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvPhoneChange = (TextView) findViewById(R.id.tv_phone_change);
        llContactAddress = (LinearLayout) findViewById(R.id.ll_contact_address);
        tvAddressTag = (TextView) findViewById(R.id.tv_address_tag);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        tvAddressManager = (TextView) findViewById(R.id.tv_address_manager);
        tvWholePrice = (TextView) findViewById(R.id.tv_whole_price);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(this);
        tvPayWayTag = (TextView) findViewById(R.id.tv_pay_way_tag);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    private void initData() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            dataEntity = getIntent().getParcelableExtra("dataEntity");
            dataEntity02 = getIntent().getParcelableExtra("dataEntity02");
            orderCode = getIntent().getStringExtra("order_code");
            if(dataEntity != null){
                handleRequest(dataEntity.getOrderCode());
                tvOrderCode.setText("订单编号:" + dataEntity.getOrderCode());
                FrescoTool.loadImage(ivHomeServiceItem, dataEntity.getServiceInfo().getServicesImg(), 1.67f);
                tvTitle.setText(dataEntity.getServiceInfo().getTitle());
                tvAddress.setText(dataEntity.getServiceInfo().getCityText());
                tvPrice.setText(dataEntity.getServicePrice());
                tvCount.setText("x" + dataEntity.getBuyCount());
                tvNick.setText(dataEntity.getUserNike());
                tvPhone.setText(dataEntity.getPhone());
                if(!TextUtils.isEmpty(dataEntity.getAddress())){
                    tvAdd.setVisibility(View.VISIBLE);
                    tvAdd.setText(dataEntity.getAddress());
                }else {
                    tvAdd.setVisibility(View.GONE);
                }
                tvWholePrice.setText(dataEntity.getPrice());
            }else if(dataEntity02 != null){
                handleRequest(dataEntity02.getOrderCode());
                tvOrderCode.setText("订单编号:" + dataEntity02.getOrderCode());
                FrescoTool.loadImage(ivHomeServiceItem, dataEntity02.getServiceInfo().getServicesImg(), 1.67f);
                tvTitle.setText(dataEntity02.getServiceInfo().getTitle());
                tvAddress.setText(dataEntity02.getServiceInfo().getCityText());
                tvPrice.setText(dataEntity02.getServicePrice());
                tvCount.setText("x" + dataEntity02.getBuyCount());
                tvNick.setText(dataEntity02.getUserNike());
                tvPhone.setText(dataEntity02.getPhone());
                if(!TextUtils.isEmpty(dataEntity02.getAddress())){
                    tvAdd.setVisibility(View.VISIBLE);
                    tvAdd.setText(dataEntity02.getAddress());
                }else {
                    tvAdd.setVisibility(View.GONE);
                }
                tvWholePrice.setText(dataEntity02.getPrice());
            }else if(!TextUtils.isEmpty(orderCode)){
                handleOrderRequest(orderCode);
            }else{
                showShortToast("数据传错啦~");
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_comfirm_layout;
    }

    @Override
    public void onClick(View v) {
        /**
         * call alipay sdk pay. 调用SDK支付
         */
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(PayConfirm.this)
                    .setTitle("警告")
                    .setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            finish();
                        }
                    }).show();
            return;
        }
        if (payWayModel != null) {
            Log.e(TAG, "paymentData : " + payWayModel.getPayTypes().get(0));
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    // 构造PayTask 对象
                    PayTask alipay = new PayTask(PayConfirm.this);
                    // 调用支付接口，获取支付结果
                    String result = alipay.pay(payWayModel.getPayTypes().get(0).getPaymentData());
                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();
        } else {
            showShortToast("数据出错啦~");
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }

    /**
     * 订单详情请求
     *
     * @param orderCode
     */
    private void handleOrderRequest(String orderCode) {
        showWaitDialog();
        GenerateOrderModel.getOrderDetailRequest(orderCode, new OkHttpClientManager.ResultCallback<GenerateOrderModel>() {
            @Override
            public void onError(Request request, Exception e) {
                hideWaitDialog();
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(GenerateOrderModel response) {
                hideWaitDialog();
                if (response != null && response.getData() != null && response.getData().getServiceInfo() != null) {
                    dataEntity02 = response.getData();
                    handleRequest(dataEntity02.getOrderCode());
                    tvOrderCode.setText("订单编号:" + dataEntity02.getOrderCode());
                    FrescoTool.loadImage(ivHomeServiceItem, dataEntity02.getServiceInfo().getServicesImg(), 1.67f);
                    tvTitle.setText(dataEntity02.getServiceInfo().getTitle());
                    tvAddress.setText(dataEntity02.getServiceInfo().getCityText());
                    tvPrice.setText(dataEntity02.getServicePrice());
                    tvCount.setText("x" + dataEntity02.getBuyCount());
                    tvNick.setText(dataEntity02.getUserNike());
                    tvPhone.setText(dataEntity02.getPhone());
                    if(!TextUtils.isEmpty(dataEntity02.getAddress())){
                        tvAdd.setVisibility(View.VISIBLE);
                        tvAdd.setText(dataEntity02.getAddress());
                    }else {
                        tvAdd.setVisibility(View.GONE);
                    }
                    tvWholePrice.setText(dataEntity02.getPrice());
                }
                showShortToast(response.getMsg());
            }
        });
    }


    /**
     * 支付方式
     * @param orderCode
     */
    private void handleRequest(String orderCode) {
        showWaitDialog();
        PayWayModel.getPayWayRequest(orderCode, new OkHttpClientManager.ResultCallback<PayWayModel>() {
            @Override
            public void onError(Request request, Exception e) {
                hideWaitDialog();
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(PayWayModel response) {
                hideWaitDialog();
                payWayModel = response;
            }
        });

    }


    public static void newInstance(Activity activity, SubmitOrderModel.DataEntity dataEntity, int requestCode) {
        Intent intent = new Intent(activity, PayConfirm.class);
        intent.putExtra("dataEntity", dataEntity);
        activity.startActivityForResult(intent, requestCode);
    }


    public static void newInstance(Activity activity, GenerateOrderModel.DataEntity dataEntity, int requestCode) {
        Intent intent = new Intent(activity, PayConfirm.class);
        intent.putExtra("dataEntity02", dataEntity);
        activity.startActivityForResult(intent, requestCode);
    }


}
