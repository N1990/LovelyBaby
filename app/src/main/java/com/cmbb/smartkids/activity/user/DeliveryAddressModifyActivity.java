package com.cmbb.smartkids.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.model.DeliveryAddressDetailModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.widget.wheelview.LocationSelectorDialogBuilder;

import java.util.HashMap;
import java.util.Map;

public class DeliveryAddressModifyActivity extends BaseActivity implements LocationSelectorDialogBuilder.OnSaveLocationLister{
    private final String TAG = DeliveryAddressModifyActivity.class.getSimpleName();
    private EditText etName, etPhone, etPostCode, etAddress;
    private TextView tvLocal, tvSave;
    private String flag = ""; // modify add
    private DeliveryAddressDetailModel.DataEntity dataEntity;
    private LocationSelectorDialogBuilder locationBuilder;
    private String provinceId;
    private String cityId;
    private String areaId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_delivery_address_modify;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        addListener();
        Bundle bundle = null;
        if(getIntent() != null && (bundle = getIntent().getExtras()) != null){
            flag = bundle.getString("modify_flag");
            dataEntity = bundle.getParcelable("delivery_address");
        }
        if("modify".equals(flag) && dataEntity != null){
            setTitle(getString(R.string.title_activity_delivery_address_modify));
            initData();
        }else{
            setTitle(getString(R.string.title_activity_delivery_address_add));
        }
    }

    private void initView() {
        etName = (EditText) findViewById(R.id.et_delivery_address_modify_name);
        etPhone = (EditText) findViewById(R.id.et_delivery_address_modify_phone);
        etPostCode = (EditText) findViewById(R.id.et_delivery_address_modify_postcode);
        etAddress = (EditText) findViewById(R.id.et_delivery_address_modify_address);
        tvLocal = (TextView) findViewById(R.id.et_delivery_address_modify_local);
        tvSave = (TextView) findViewById(R.id.tv_delivery_address_detail_save);
    }

    private void initData(){
        etName.setText(dataEntity.getReceiveName());
        etPhone.setText(dataEntity.getReceivePhone());
        etPostCode.setText(dataEntity.getPostCode());
        tvLocal.setText(dataEntity.getProvincteTxt() + dataEntity.getCityText() + dataEntity.getDistrictText());
        etAddress.setText(dataEntity.getAddress());
    }

    private void addListener(){
        tvLocal.setOnClickListener(this);
        tvSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         int id = v.getId();
        if(id == R.id.et_delivery_address_modify_local){
            if (locationBuilder == null) {
                locationBuilder = LocationSelectorDialogBuilder.getInstance(this);
                locationBuilder.setOnSaveLocationLister(this);
            }
            locationBuilder.show();
        }else if(id == R.id.tv_delivery_address_detail_save) {
            handleSaveDeliveryAddress();
        }
    }

    @Override
    public void onSaveLocation(String local, String province, String city, String area, String provinceId, String cityId, String areaId) {
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.areaId = areaId;
        tvLocal.setText(local);
    }

    @Override
    protected void onDestroy() {
        if (locationBuilder != null)
            locationBuilder.setDialogDismiss();
        super.onDestroy();
    }

    private void handleSaveDeliveryAddress(){
        String name = etName.getText().toString().toString();
        String phone = etPhone.getText().toString().toString();
        String postCode = etPostCode.getText().toString().toString();
        String address = etAddress.getText().toString().toString();
        if(TextUtils.isEmpty(name)){
            showShortToast("姓名不能为空~");
            return;
        }
        if(TextUtils.isEmpty(phone)){
            showShortToast("手机号码不能为空~");
            return;
        }
        if(TextUtils.isEmpty(postCode)){
            showShortToast("邮编不能为空~");
            return;
        }
        if(TextUtils.isEmpty(address)){
            showShortToast("详细地址不能为空~");
            return;
        }
        if(TextUtils.isEmpty(provinceId) || TextUtils.isEmpty(cityId) || TextUtils.isEmpty(areaId)){
            showShortToast("请选择省市区");
            return;
        }


        Map<String, String> param = new HashMap<String, String>();
        if(dataEntity != null){
            param.put("id", String.valueOf(dataEntity.getId()));
        }
        param.put("receiveName", name);
        param.put("receivePhone", phone);
        param.put("postCode", postCode);
        param.put("province", String.valueOf(provinceId));
        param.put("city", String.valueOf(cityId));
        param.put("district", String.valueOf(areaId));
        param.put("address", address);
        showWaitDialog();
        NetRequest.postRequest(Constants.ServiceInfo.MODIFY_DELIVERY_ADDRESS, BaseApplication.token, param, DeliveryAddressDetailModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                DeliveryAddressDetailModel data = (DeliveryAddressDetailModel) object;
                if (data != null && data.getData() != null) {
                    setResult(RESULT_OK);
                    finish();
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

    public static void skipFromActivity(Activity activity, String flag, int requestCode, DeliveryAddressDetailModel.DataEntity dataEntity){// modify add
        Intent intent =  new Intent(activity, DeliveryAddressModifyActivity.class);
        intent.putExtra("modify_flag", flag);
        intent.putExtra("delivery_address", dataEntity);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void skipFromActivity(Activity activity, String flag, int requestCode){// modify add
        Intent intent =  new Intent(activity, DeliveryAddressModifyActivity.class);
        intent.putExtra("modify_flag", flag);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void skipFromFragment(Fragment fragment, String flag,  int requestCode, DeliveryAddressDetailModel.DataEntity dataEntity){
        Intent intent =  new Intent(fragment.getContext(), DeliveryAddressModifyActivity.class);
        intent.putExtra("modify_flag", flag);
        intent.putExtra("delivery_address", dataEntity);
        fragment.startActivityForResult(intent, requestCode);
    }


}
