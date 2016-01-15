package com.cmbb.smartkids.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.HomeActivity;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.order.model.OrderDetailModel;
import com.cmbb.smartkids.activity.order.model.RefundModel;
import com.cmbb.smartkids.activity.serve.ActiveDetailActivity;
import com.cmbb.smartkids.activity.serve.model.ServiceOrderModel;
import com.cmbb.smartkids.activity.user.DeliveryAddressListActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.model.OrderStatus;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.pay.PayActivity;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.Tools;
import com.cmbb.smartkids.utils.log.Log;
import com.cmbb.smartkids.widget.wheelview.CustomDialogBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;

public class OrderDetailActivity extends BaseActivity {
    private final String TAG = OrderDetailActivity.class.getSimpleName();
    private final int ORDER_RESULT = 10001;
    private final int HANDLER_ORDER_REQUEST = 11116;
    private final int MODIFY_ORDER_PHONE = 11119;
    private TextView tvTitle, tvCity, tvPrice, tvTime, tvName, tvPhone, tvNumber,
            tvActiveTime, tvActiveLocal, tvActivePrice, tvStatus, tvHandle, tvGoHome;
    private ImageView ivModifyPhone, ivChooseDelivery;
    private SimpleDraweeView ivBg;
    private RelativeLayout rlStatus, rlNumber;
    private View vNumberDivider;
    //    private CollapsingToolbarLayout ctl;
    private boolean flag;
    private ServiceOrderModel.DataEntity data;
    private String serviceTitle, serviceCity, serviceTime, serviceAddress, serviceImg, serviceWidth, serviceHeight;
    private String orderCode, phone;
    private CustomDialogBuilder builder;
    boolean orderResult;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView() {
//        ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setTitle(getString(R.string.title_activity_order_detail));
//        ctl.setExpandedTitleTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
        ivBg = (SimpleDraweeView) findViewById(R.id.iv_order_detail);
        tvNumber = (TextView) findViewById(R.id.tv_order_detail_number);
        tvTitle = (TextView) findViewById(R.id.tv_order_detail_title);
        tvCity = (TextView) findViewById(R.id.tv_order_detail_city);
        tvPrice = (TextView) findViewById(R.id.tv_order_detail_price);
        tvTime = (TextView) findViewById(R.id.tv_order_detail_time);
        tvName = (TextView) findViewById(R.id.tv_order_detail_name);
        tvPhone = (TextView) findViewById(R.id.tv_order_detail_phone);
        tvActiveTime = (TextView) findViewById(R.id.tv_order_detail_active_time);
        tvActiveLocal = (TextView) findViewById(R.id.tv_order_detail_active_local);
        tvActivePrice = (TextView) findViewById(R.id.tv_order_detail_active_price);
        rlNumber = (RelativeLayout) findViewById(R.id.ll_order_detail_number);
        rlStatus = (RelativeLayout) findViewById(R.id.ll_order_detail_status);
        vNumberDivider = findViewById(R.id.v_order_detail_number_divider);
        tvStatus = (TextView) findViewById(R.id.tv_order_detail_status);
        tvHandle = (TextView) findViewById(R.id.tv_order_detail_handle);
        tvGoHome = (TextView) findViewById(R.id.tv_order_detail_gohome);
        ivModifyPhone = (ImageView) findViewById(R.id.iv_order_detail_modify_phone);
        ivChooseDelivery = (ImageView) findViewById(R.id.iv_order_detail_delivery_address);
    }

    private void initData() {
        Bundle bundle = null;
        if (getIntent() != null && (bundle = getIntent().getExtras()) != null) {
            data = bundle.getParcelable("orderDetail");
            serviceTitle = bundle.getString("serviceTitle");
            serviceCity = bundle.getString("serviceCity");
            serviceTime = bundle.getString("serviceTime");
            serviceAddress = bundle.getString("serviceAddress");
            serviceImg = bundle.getString("serviceImg");
            serviceWidth = bundle.getString("serviceWidth");
            serviceHeight = bundle.getString("serviceHeight");
            flag = bundle.getBoolean("flag", false);
            orderCode = bundle.getString("orderCode");
        }
        if (serviceTitle == null)
            Log.e(TAG, "service data is null");
        if (data == null)
            Log.e(TAG, "data is null");
        if (data != null && serviceTitle != null) {
            orderCode = data.getOrderCode();
            reflushView();
        } else if (!TextUtils.isEmpty(orderCode)) {
            showWaitDialog();
            handleOrderRequest(orderCode);
            ivBg.setOnClickListener(this);
        } else {
            showShortToast("传参出错~");
        }
    }

    private void addListener() {
        tvHandle.setOnClickListener(this);
        ivModifyPhone.setOnClickListener(this);
        ivChooseDelivery.setOnClickListener(this);
        tvGoHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_order_detail_handle) {
            if (flag) {
                if (TextUtils.isEmpty(phone)) {
                    showShortToast("请修改手机号码");
                    return;
                }
                hanleSubmitRequest();
            } else {
                Object object = v.getTag();
                OrderStatus status = null;
                String orderCode = "";
                String orderTitle = "";
                double price = 0;
                if (object instanceof OrderDetailModel.DataEntity) {
                    int statusFlag = ((OrderDetailModel.DataEntity) object).getStatus();
                    status = OrderStatus.getStatusByValue(statusFlag);
                    orderCode = ((OrderDetailModel.DataEntity) object).getOrderCode();
                    price = Double.valueOf(((OrderDetailModel.DataEntity) object).getPrice());
                    orderTitle = ((OrderDetailModel.DataEntity) object).getServiceInfo().getTitle();
                } else if (object instanceof ServiceOrderModel.DataEntity) {
                    String statusFlag = ((ServiceOrderModel.DataEntity) object).getStatus();
                    status = OrderStatus.getStatusByValue(Integer.valueOf(statusFlag));
                    orderCode = ((ServiceOrderModel.DataEntity) object).getOrderCode();
                    String priceStr = ((ServiceOrderModel.DataEntity) object).getPrice();
                    price = Double.valueOf(priceStr);
                    orderTitle = serviceTitle;
                }
                handleBottomCase(orderTitle, status, orderCode, price);
            }
        } else if (id == R.id.iv_order_detail) {
            int serviceId = (int) v.getTag();
            Intent intent = new Intent(OrderDetailActivity.this, ActiveDetailActivity.class);
            intent.putExtra("serviceId", serviceId);
            startActivity(intent);
        } else if (id == R.id.iv_order_detail_modify_phone) {
            Intent modify = new Intent(OrderDetailActivity.this, OrderModifyPhoneActivity.class);
            startActivityForResult(modify, MODIFY_ORDER_PHONE);
        } else if (id == R.id.tv_order_detail_gohome) {
            Intent intent = new Intent(OrderDetailActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }else if(id == R.id.iv_order_detail_delivery_address){
            showShortToast("选择配送地址");
//            DeliveryAddressListActivity.skipFromActivity(OrderDetailActivity.this, "check");
        }
    }

    /**
     * 处理底部按钮事件
     *
     * @param status
     * @param orderCode
     */
    public void handleBottomCase(String title, OrderStatus status, String orderCode, double price) {
        switch (status) {
            case WEI_ZHI_FU:
                Intent pay = new Intent(OrderDetailActivity.this, PayActivity.class);
                pay.putExtra("order_title", title);
                pay.putExtra("order_price", String.valueOf(price));
                pay.putExtra("orderCode", orderCode);
                startActivityForResult(pay, HANDLER_ORDER_REQUEST);
                break;
            case YI_ZHI_FU:
                if (price != 0) {
                    showRefundDialog(orderCode);
                } else {
                    showCustomDialog(orderCode);
                }
                break;
            case YI_GUO_QI:
                showRefundDialog(orderCode);
                break;
            case YI_CAN_JIA:
                showShortToast("评价功能尚未开通...");
                break;
            case YI_PING_JIA:
            case YI_TUI_KUAN:
                break;

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (builder != null)
            builder.setDialogDismiss();
    }


    private void showRefundDialog(final String orderCode) {
        builder = CustomDialogBuilder.getInstance(this).withTitle("申请退款")
                .withMessage("您确认要申请退款吗？退款之后您需重新预订此服务...")
                .withComfirmText("确认", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        if (builder != null)
                            builder.dismiss();
                        handleApplyRefund(orderCode);
                    }
                })
                .withCancelText("取消", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        if (builder != null)
                            builder.dismiss();
                    }
                });
        builder.show();
    }


    private void showCustomDialog(final String orderCode) {
        builder = CustomDialogBuilder.getInstance(this).withTitle("取消订单")
                .withMessage("您确认要取消此订单吗？取消之后将不会在订单列表中出现，您需重新预订此服务...")
                .withComfirmText("确认", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        if (builder != null)
                            handleCancelRequest(orderCode);
                    }
                })
                .withCancelText("取消", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        if (builder != null)
                            builder.dismiss();
                    }
                });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ORDER_RESULT && resultCode == RESULT_OK) {
           /* flag = false;
            boolean back = data.getBooleanExtra("back", false);
            if(!back){
                String orderCode = data.getStringExtra("orderCode");
                orderResult = data.getBooleanExtra("flag", false);
                handleOrderRequest(orderCode);
                setResult(RESULT_OK);
            }else{
                setResult(RESULT_OK);
                finish();
            }*/

        } else if (requestCode == HANDLER_ORDER_REQUEST && resultCode == RESULT_OK) {
            String orderCode = data.getStringExtra("order_code");
            handleOrderRequest(orderCode);
        } else if (requestCode == MODIFY_ORDER_PHONE && resultCode == RESULT_OK) {
            phone = data.getStringExtra("phone");
            tvPhone.setText(phone);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 从活动详情入口 刷新页面
     */
    private void reflushView() {
        findViewById(R.id.cv_order_detail_content).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_empty_data).setVisibility(View.GONE);
        FrescoTool.loadImage(ivBg, serviceImg, serviceWidth, serviceHeight);
        tvTitle.setText(serviceTitle);
        if (TextUtils.isEmpty(serviceCity))
            tvCity.setVisibility(View.GONE);
        tvCity.setText(serviceCity);
        tvPrice.setText(Double.valueOf(data.getServicePrice()) == 0 ? "免费" : "￥" + data.getServicePrice());
        try {
            tvTime.setText( Tools.DataToString(data.getOrderDate(), "yyyy.MM.dd HH:mm"));
            tvActiveTime.setText(Tools.DataToString(serviceTime, "yyyy.MM.dd HH:mm"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvName.setText(data.getUserNike());
        phone = data.getPhone();
        if (TextUtils.isEmpty(phone)) {
            tvPhone.setText("");
        } else {
            tvPhone.setText(phone);
        }
        tvActiveLocal.setText(serviceAddress);
        tvActivePrice.setText(data.getPrice() == null || Double.valueOf(data.getPrice()) == 0 ? "￥" + "0.00" : "￥" + data.getPrice());
        if (flag) {
            rlStatus.setVisibility(View.GONE);
            tvHandle.setText("提交订单");
        } else {
            if (!TextUtils.isEmpty(data.getStatus())) {
                reflushBottomView(data);
            } else {
                Log.e(TAG, "订单状态为空");
                rlStatus.setVisibility(View.GONE);
                tvHandle.setVisibility(View.GONE);
                ivModifyPhone.setVisibility(View.GONE);
                ivChooseDelivery.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 从订单详情 网络请求刷新页面
     *
     * @param data
     */
    private void reflushView(OrderDetailModel.DataEntity data) {
        findViewById(R.id.cv_order_detail_content).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_empty_data).setVisibility(View.GONE);
        rlNumber.setVisibility(View.VISIBLE);
        vNumberDivider.setVisibility(View.VISIBLE);
        if (data.getServiceInfo() != null)
            ivBg.setTag(data.getServiceInfo().getId());
        OrderDetailModel.DataEntity.ServiceInfoEntity sData = data.getServiceInfo();
        FrescoTool.loadImage(ivBg, sData.getServicesImg(), sData.getImgWidth(), sData.getImgHeight());
        tvNumber.setText(data.getOrderCode());
        tvTitle.setText(sData.getTitle());
        if (TextUtils.isEmpty(sData.getCityText()))
            tvCity.setVisibility(View.GONE);
        tvCity.setText(sData.getCityText());
        tvPrice.setText(Double.valueOf(data.getServicePrice()) == 0 ? "免费" : "￥" + data.getServicePrice());
        try {
            tvTime.setText(Tools.DataToString(data.getOrderDate(), "yyyy.MM.dd HH:mm"));
            tvActiveTime.setText(Tools.DataToString(sData.getStartTime(), "yyyy.MM.dd HH:mm"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvName.setText(data.getUserNike());
        phone = data.getPhone();
        if (TextUtils.isEmpty(phone)) {
            tvPhone.setText("");
        } else {
            tvPhone.setText(phone);
        }
        tvActiveLocal.setText(sData.getAddress());
        tvActivePrice.setText(Double.valueOf(data.getPrice()) == 0 ? "￥" + "0.00" : "￥" + data.getPrice());
        if (orderResult) {
            OrderStatus status = OrderStatus.getStatusByValue(data.getStatus());
            if (status == OrderStatus.WEI_ZHI_FU) {
                rlStatus.setVisibility(View.GONE);
                tvHandle.setVisibility(View.VISIBLE);
                tvHandle.setText("立即支付");
                tvHandle.setTag(data);
                tvGoHome.setVisibility(View.GONE);
            } else {
                rlStatus.setVisibility(View.VISIBLE);
                tvGoHome.setVisibility(View.VISIBLE);
                tvGoHome.setText("返回首页");
                tvHandle.setVisibility(View.GONE);
            }
        } else if (flag) {
            rlStatus.setVisibility(View.GONE);
            tvHandle.setVisibility(View.VISIBLE);
            tvHandle.setText("提交订单");
            tvGoHome.setVisibility(View.GONE);
        } else {
            reflushBottomView(data);
        }
    }

    /**
     * 刷新底部状态与按钮
     *
     * @param data
     */
    private void reflushBottomView(OrderDetailModel.DataEntity data) {
        ivModifyPhone.setVisibility(View.GONE);
        ivChooseDelivery.setVisibility(View.GONE);
        OrderStatus status = OrderStatus.getStatusByValue(data.getStatus());
        tvHandle.setTag(data);
        switch (status) {
            case WEI_ZHI_FU:
                rlStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("订单状态：" + status.toString());
                tvHandle.setText("立即支付");
                break;
            case YI_ZHI_FU:
                rlStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("订单状态：" + status.toString());
                double price = Double.valueOf(data.getPrice());
                if (price != 0) {
                    tvHandle.setText("申请退款");
                } else {
                    tvHandle.setText("取消订单");
                }
                break;
            case YI_GUO_QI:
                rlStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("订单状态：" + status.toString());
                tvHandle.setText("申请退款");
                break;
            case YI_CAN_JIA:
                rlStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("订单状态：" + status.toString());
                tvHandle.setText("立即评价");
                break;
            case YI_PING_JIA:
            case YI_TUI_KUAN:
            case YI_QU_XIAO:
            case TUI_KUAN_ZHONG:
                rlStatus.setVisibility(View.GONE);
                tvHandle.setVisibility(View.GONE);
                break;

        }
    }

    /**
     * 刷新底部状态与按钮
     *
     * @param data
     */
    private void reflushBottomView(ServiceOrderModel.DataEntity data) {
        ivModifyPhone.setVisibility(View.GONE);
        ivChooseDelivery.setVisibility(View.GONE);
        OrderStatus status = OrderStatus.getStatusByValue(Integer.valueOf(data.getStatus()));
        tvHandle.setTag(data);
        switch (status) {
            case WEI_ZHI_FU:
                rlStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("订单状态：" + status.toString());
                tvHandle.setText("立即支付");
                break;
            case YI_ZHI_FU:
                rlStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("订单状态：" + status.toString());
                double price = Double.valueOf(data.getPrice());
                if (price != 0) {
                    tvHandle.setText("申请退款");
                } else {
                    tvHandle.setText("取消订单");
                }
                break;
            case YI_GUO_QI:
                rlStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("订单状态：" + status.toString());
                tvHandle.setText("申请退款");
                break;
            case YI_CAN_JIA:
                rlStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("订单状态：" + status.toString());
                tvHandle.setText("立即评价");
                break;
            case YI_PING_JIA:
            case YI_TUI_KUAN:
            case YI_QU_XIAO:
            case TUI_KUAN_ZHONG:
                rlStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("订单状态：" + status.toString());
//                rlStatus.setVisibility(View.GONE);
                tvHandle.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (orderResult)
                    setResult(RESULT_OK);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 提交订单请求
     */
    private void hanleSubmitRequest() {
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("serviceId", String.valueOf(data.getServiceId()));
        params.put("phone", String.valueOf(phone));
        NetRequest.postRequest(Constants.ServiceInfo.SUBMIT_ORDER_REQUEST, BaseApplication.token, params, ServiceOrderModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                ServiceOrderModel result = (ServiceOrderModel) object;
                String tip = "";
                if (result != null && result.getData() != null) {
                    flag = false;
                    orderResult = true;
                    tip = "订单提交成功~";
                    showShortToast(tip);
                    handleOrderRequest(result.getData().getOrderCode());
                } else {
                    hideWaitDialog();
                    tip = "订单提交失败~";
                    showShortToast(tip);
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }


    /**
     * 订单详情请求
     *
     * @param orderCode
     */
    private void handleOrderRequest(String orderCode) {
        HashMap<String, String> params = new HashMap<>();
        params.put("orderCode", orderCode);
        NetRequest.postRequest(Constants.ServiceInfo.ORDER_DETAIL_REQUEST, BaseApplication.token, params, OrderDetailModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                OrderDetailModel data = (OrderDetailModel) object;
                if (data != null && data.getData() != null && data.getData().getServiceInfo() != null) {
                    reflushView(data.getData());
                }
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
     * 取消订单
     *
     * @param orderCode
     */
    private void handleCancelRequest(String orderCode) {
        if (builder != null)
            builder.setDialogDismiss();
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("orderCode", orderCode);
        NetRequest.postRequest(Constants.ServiceInfo.CANCEL_ORDER_REQUEST, BaseApplication.token, params, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                SecurityCodeModel result = (SecurityCodeModel) object;
                if (result != null) {
                    showShortToast(result.getMsg());
                    setResult(RESULT_OK);
                    finish();
                } else {
                    showShortToast("订单取消失败~");
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }


    /**
     * 申请退款
     *
     * @param orderCode
     */
    private void handleApplyRefund(String orderCode) {
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("orderCode", orderCode);
        NetRequest.postRequest(Constants.ServiceInfo.APPLY_REFUND_REQUEST, BaseApplication.token, params, RefundModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                RefundModel result = (RefundModel) object;
                if (result != null) {
                    setResult(RESULT_OK);
                    finish();
                } else {
                    showShortToast("退款中...");
//                    showShortToast(msg);
                }
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
     * @param context   Context
     * @param orderCode 订单号
     */
    public static void newInstance(Context context, String orderCode) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("orderCode", orderCode);
        context.startActivity(intent);
    }

}
