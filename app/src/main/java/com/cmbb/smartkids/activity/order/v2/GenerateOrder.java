package com.cmbb.smartkids.activity.order.v2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.order.model.GenerateOrderModel;
import com.cmbb.smartkids.activity.order.model.OrderDetailModel;
import com.cmbb.smartkids.activity.serve.v2.ServerDetailActivityV2;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.model.OrderStatus;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.Tools;
import com.cmbb.smartkids.utils.log.Log;
import com.cmbb.smartkids.widget.wheelview.CustomDialogBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.okhttp.Request;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/16 上午11:45
 */
public class GenerateOrder extends BaseActivity {
    private final String TAG = GenerateOrder.class.getSimpleName();
    private final int HANDLER_ORDER_REQUEST = 10129;
    private TextView tvOrderNum;
    private SimpleDraweeView ivHomeServiceItem;
    private TextView tvTitle;
    private TextView tvAddress;
    private TextView tvPrice;
    private TextView tvCount;
    private TextView tvNick;
    private TextView tvPhone;
    private LinearLayout llContactAddress;
    private TextView tvAdd;
    private TextView tvToatalPrice;
    private TextView tvAppointment;
    private TextView tvReimburse;
    private LinearLayout llVerifyCode;
    private TextView tvVerifyCode;
    private GenerateOrderModel.DataEntity dataEntity;
    private boolean isReimburse;
    private boolean isConfirmOrder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_generate_order_layout;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        Bundle bundle = null;
        if (getIntent() != null && (bundle = getIntent().getExtras()) != null) {
            String orderCode = bundle.getString("orderCode");
            isConfirmOrder = bundle.getBoolean("isConfirmOrder", false);
            handleOrderRequest(orderCode);
        }else{
            Log.e(TAG, "data is null");
        }
    }

    private void initView() {
        setTitle(getString(R.string.title_ordere_detail));
        tvOrderNum = (TextView) findViewById(R.id.tv_order_num);
        ivHomeServiceItem = (SimpleDraweeView) findViewById(R.id.iv_home_service_item);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvCount = (TextView) findViewById(R.id.tv_count);
        tvNick = (TextView) findViewById(R.id.tv_nick);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        llContactAddress = (LinearLayout) findViewById(R.id.ll_contact_address);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        tvAppointment = (TextView) findViewById(R.id.tv_appointment);
        tvReimburse = (TextView) findViewById(R.id.tv_reimburse);
        tvToatalPrice = (TextView) findViewById(R.id.tv_total_price);
        llVerifyCode = (LinearLayout) findViewById(R.id.ll_verify_order);
        tvVerifyCode = (TextView) findViewById(R.id.tv_verify_order);
        tvAppointment.setOnClickListener(this);
        tvReimburse.setOnClickListener(this);
        ivHomeServiceItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home_service_item:
                ServerDetailActivityV2.newIntent(GenerateOrder.this, dataEntity.getServiceId());
                break;
            case R.id.tv_appointment:
                OrderStatus status = OrderStatus.getStatusByValue(dataEntity.getStatus());
                Log.e("orderStatus", status.toString());
                switch (status) {
                    case WEI_ZHI_FU:
                        PayConfirm.newInstance(GenerateOrder.this, dataEntity, HANDLER_ORDER_REQUEST);
                        break;
                    case YI_ZHI_FU:
                        if(TextUtils.isEmpty(dataEntity.getServiceInfo().getServicePhone())){
                            showShortToast("商家没有留下任何联系方式");
                        }else{
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            Uri data = Uri.parse("tel:" + dataEntity.getServiceInfo().getServicePhone());
                            intent.setData(data);
                            startActivity(intent);
                        }
                        break;
                    case YI_CAN_JIA:
                        EvaluateOrderActivity.newInstance(GenerateOrder.this, dataEntity.getServiceId(), dataEntity.getOrderCode(), HANDLER_ORDER_REQUEST);
                        break;
                    case YI_YU_DING:
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + dataEntity.getServiceInfo().getServicePhone());
                        intent.setData(data);
                        startActivity(intent);
                        break;
                }
                break;
            case R.id.tv_reimburse:
                ReimburseActivity.newInstance(GenerateOrder.this, dataEntity.getOrderCode(), HANDLER_ORDER_REQUEST);
                break;
        }
    }

    /**
     * 从订单详情 网络请求刷新页面
     *
     */
    private void reflushView() {
        tvOrderNum.setText("订单编号:" + dataEntity.getOrderCode());
        GenerateOrderModel.DataEntity.ServiceInfoEntity sData = dataEntity.getServiceInfo();
        FrescoTool.loadImage(ivHomeServiceItem, sData.getServicesImg(), 1.67f);
        tvTitle.setText(sData.getTitle());
        tvAddress.setText(sData.getCityText());
        tvPrice.setText(dataEntity.getServicePrice());
        tvCount.setText("x" + dataEntity.getBuyCount());
        tvToatalPrice.setText(dataEntity.getPrice());
        tvNick.setText(dataEntity.getUserNike());
        tvPhone.setText(dataEntity.getPhone());

        if(dataEntity.getServiceInfo() != null && dataEntity.getServiceInfo().getType() == 205){
            llContactAddress.setVisibility(View.VISIBLE);
            tvAdd.setText(dataEntity.getAddress());
            tvAdd.setTag(dataEntity.getAddressId());
        }else {
            llContactAddress.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(dataEntity.getValidCode())){
            llVerifyCode.setVisibility(View.VISIBLE);
            tvVerifyCode.setText(dataEntity.getValidCode());
        }else {
            llVerifyCode.setVisibility(View.GONE);
        }

        int status = dataEntity.getStatus();
        reflushBottomView(status);
    }
    /**
     * 刷新底部状态与按钮
     *
     */
    private void reflushBottomView(int statusCode) {
        OrderStatus status = OrderStatus.getStatusByValue(statusCode);
        switch (status) {
            case WEI_ZHI_FU:
                tvReimburse.setVisibility(View.GONE);
                tvAppointment.setVisibility(View.VISIBLE);
                tvAppointment.setText("立即支付");
                break;
            case YI_ZHI_FU:
                tvAppointment.setVisibility(View.VISIBLE);
                tvAppointment.setText("立即预约");
                tvReimburse.setVisibility(View.VISIBLE);
                tvReimburse.setText("申请退款");
                break;
            case YI_GUO_QI:
                tvReimburse.setVisibility(View.VISIBLE);
                tvReimburse.setText("申请退款");
                tvAppointment.setVisibility(View.GONE);
                break;
            case YI_CAN_JIA:
                tvReimburse.setVisibility(View.GONE);
                tvAppointment.setVisibility(View.VISIBLE);
                tvAppointment.setText("立即评价");
                break;
            case YI_PING_JIA:
                tvReimburse.setVisibility(View.GONE);
                tvAppointment.setVisibility(View.VISIBLE);
                tvAppointment.setBackgroundColor(Color.GRAY);
                tvAppointment.setEnabled(false);
                tvAppointment.setText("已评价");
                break;
            case YI_TUI_KUAN:
                tvReimburse.setVisibility(View.GONE);
                tvAppointment.setVisibility(View.VISIBLE);
                tvAppointment.setBackgroundColor(Color.GRAY);
                tvAppointment.setEnabled(false);
                tvAppointment.setText("已退款");
                break;
            case YI_QU_XIAO:
                tvReimburse.setVisibility(View.GONE);
                tvAppointment.setVisibility(View.VISIBLE);
                tvAppointment.setBackgroundColor(Color.GRAY);
                tvAppointment.setEnabled(false);
                tvAppointment.setText("已取消");
                break;
            case TUI_KUAN_ZHONG:
                tvReimburse.setVisibility(View.GONE);
                tvAppointment.setVisibility(View.VISIBLE);
                tvAppointment.setBackgroundColor(Color.GRAY);
                tvAppointment.setEnabled(false);
                tvAppointment.setText("退款中");
                break;
            case YI_YU_DING:
                tvReimburse.setVisibility(View.GONE);
                tvAppointment.setVisibility(View.VISIBLE);
                tvAppointment.setText("立即预约");
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == HANDLER_ORDER_REQUEST && resultCode == RESULT_OK){ //&& data != null
            isReimburse = true;
//            int status = data.getIntExtra("order_status", 0);
//            dataEntity.setStatus(status);
//            reflushBottomView(status);
            handleOrderRequest(dataEntity.getOrderCode());
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onBackPressed() {
        if(isConfirmOrder || isReimburse){
            setResult(RESULT_OK);
            finish();
        }else{
            super.onBackPressed();
        }
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
                    dataEntity = response.getData();
                    reflushView();
                }
                showShortToast(response.getMsg());
            }
        });
    }

    public static void newInstance(Activity activity, String orderCode){
        Intent intent = new Intent(activity, GenerateOrder.class);
        intent.putExtra("orderCode", orderCode);
        activity.startActivity(intent);
    }



    public static void newInstance(Activity activity, String orderCode, int requestCode){
        Intent intent = new Intent(activity, GenerateOrder.class);
        intent.putExtra("orderCode", orderCode);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 免费订单,支付成功提交直接到订单详情入口
     * @param activity
     * @param orderCode
     * @param isConfirmOrder
     * @param requestCode
     */
    public static void newInstance(Activity activity, String orderCode, boolean isConfirmOrder, int requestCode){
        Intent intent = new Intent(activity, GenerateOrder.class);
        intent.putExtra("orderCode", orderCode);
        intent.putExtra("isConfirmOrder", isConfirmOrder);
        activity.startActivityForResult(intent, requestCode);
    }
}
