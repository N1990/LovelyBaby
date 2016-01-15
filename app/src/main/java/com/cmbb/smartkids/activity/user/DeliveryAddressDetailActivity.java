package com.cmbb.smartkids.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.user.model.DeliveryAddressDetailModel;
import com.cmbb.smartkids.activity.user.model.DeliveryAddressListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.widget.wheelview.CustomDialogBuilder;

import java.util.HashMap;
import java.util.Map;

public class DeliveryAddressDetailActivity extends BaseActivity {

    private final String TAG = DeliveryAddressDetailActivity.class.getSimpleName();
    private final int MODIFY_DELIVERY_ADDRESS = 22330;
    private NestedScrollView nvs;
    private TextView tvDeliveryAddressDetailName;
    private TextView tvDeliveryAddressDetailPhone;
    private TextView tvDeliveryAddressDetailPostcode;
    private TextView tvDeliveryAddressDetailLocal;
    private TextView tvDeliveryAddressDetailAddress;
    private TextView tvDeliveryAddressDetailDelete;
    private TextView tvDeliveryAddressDetailDefault;
    private CustomDialogBuilder builder;
    private int id;
    private DeliveryAddressDetailModel.DataEntity dataEntity;
    private boolean isModify;





    @Override
    protected int getLayoutId() {
        return R.layout.activity_delivery_address_detail;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        addListener();
        if(getIntent() != null){
            nvs.setVisibility(View.VISIBLE);
            id = getIntent().getIntExtra("address_id",  -1);
            handleDeliveryAddressDetailRequest();
        }else{
            showShortToast("传参出错啦~");
        }
    }

    private void initView(){
        setTitle(getString(R.string.title_activity_delivery_address_detail));
        setActionBarRight("修改");
        nvs = (NestedScrollView) findViewById(R.id.nsv_delivery_address_detail);
        tvDeliveryAddressDetailName = (TextView) findViewById(R.id.tv_delivery_address_detail_name);
        tvDeliveryAddressDetailPhone = (TextView) findViewById(R.id.tv_delivery_address_detail_phone);
        tvDeliveryAddressDetailPostcode = (TextView) findViewById(R.id.tv_delivery_address_detail_postcode);
        tvDeliveryAddressDetailLocal = (TextView) findViewById(R.id.tv_delivery_address_detail_local);
        tvDeliveryAddressDetailAddress = (TextView) findViewById(R.id.tv_delivery_address_detail_address);
        tvDeliveryAddressDetailDelete = (TextView) findViewById(R.id.tv_delivery_address_detail_delete);
        tvDeliveryAddressDetailDefault = (TextView) findViewById(R.id.tv_delivery_address_detail_default);
    }

    private void initData(){
        tvDeliveryAddressDetailName.setText(dataEntity.getReceiveName());
        tvDeliveryAddressDetailPhone.setText(dataEntity.getReceivePhone());
        tvDeliveryAddressDetailPostcode.setText(dataEntity.getPostCode());
        tvDeliveryAddressDetailLocal.setText(dataEntity.getProvincteTxt() + dataEntity.getCityText() + dataEntity.getDistrictText());
        tvDeliveryAddressDetailAddress.setText(dataEntity.getAddress());
        tvDeliveryAddressDetailDefault.setVisibility(dataEntity.getIsDefault() == 1 ? View.GONE : View.VISIBLE);

    }

    private void addListener(){
        tvDeliveryAddressDetailDelete.setOnClickListener(this);
        tvDeliveryAddressDetailDefault.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_main_toolbar_right:
                DeliveryAddressModifyActivity.skipFromActivity(DeliveryAddressDetailActivity.this, "modify", MODIFY_DELIVERY_ADDRESS, dataEntity);
                break;
            case R.id.tv_delivery_address_detail_delete:
                showAlertDialog();
                break;
            case R.id.tv_delivery_address_detail_default:
                handleSetDefalutAddressRequest();
                break;

        }
    }
    private void showAlertDialog() {
        int width = TDevice.getScreenWidth(this) * 3 / 4;
        builder = CustomDialogBuilder.getInstance(this).setDialogWindows(width, ViewGroup.LayoutParams.WRAP_CONTENT)
                .isCancelableOnTouchOutside(false)
                .withTitle("删除配送地址")
                .withMessage("您确定要删除该配送地址,删除之后不可恢复？")
                .withCancelText("取消", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                }).withComfirmText("确定", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        handleDeleteAddressRequest();
                    }
                });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MODIFY_DELIVERY_ADDRESS && resultCode == RESULT_OK){
            isModify = true;
            handleDeliveryAddressDetailRequest();
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onBackPressed() {
        if (builder != null && builder.isShowing()) {
            builder.dismiss();
        }
        if(isModify){
            setResult(RESULT_OK);
            finish();
        }else{
            super.onBackPressed();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (builder != null) {
            builder.setDialogDismiss();
        }
    }


    /**
     * 获取配送地址详情
     */
    private void handleDeliveryAddressDetailRequest(){
        showWaitDialog();
        Map<String, String> param = new HashMap<String, String>();
        param.put("id", String.valueOf(id));
        NetRequest.postRequest(Constants.ServiceInfo.DELIVERY_ADDRESS_LIST, BaseApplication.token, param, DeliveryAddressDetailModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                DeliveryAddressDetailModel data = (DeliveryAddressDetailModel) object;
                if (data != null && data.getData() != null) {
                    dataEntity = data.getData();
                    initData();
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
     * 删除配送地址
     */
    private void handleDeleteAddressRequest(){
        showWaitDialog();
        Map<String, String> param = new HashMap<String, String>();
        param.put("id", String.valueOf(id));
        NetRequest.postRequest(Constants.ServiceInfo.DELETE_DELIVERY_ADDRESS, BaseApplication.token, param, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                showShortToast(msg);
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

    /**
     * 设为默认地址
     */
    private void handleSetDefalutAddressRequest(){
        showWaitDialog();
        Map<String, String> param = new HashMap<String, String>();
        param.put("id", String.valueOf(id));
        NetRequest.postRequest(Constants.ServiceInfo.SET_DEFAULT_DELIVERY_ADDRESS, BaseApplication.token, param, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                showShortToast(msg);
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }



    public static void skipFromActivity(Activity activity, int id, int requestCode){// manager check
        Intent intent =  new Intent(activity, DeliveryAddressDetailActivity.class);
        intent.putExtra("address_id", id);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void skipFromFragment(Fragment fragment, int id, int requestCode){
        Intent intent =  new Intent(fragment.getContext(), DeliveryAddressDetailActivity.class);
        intent.putExtra("address_id", id);
        fragment.startActivityForResult(intent, requestCode);
    }
}
