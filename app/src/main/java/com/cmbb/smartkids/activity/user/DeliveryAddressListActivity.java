package com.cmbb.smartkids.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.DeliveryAddressAdapter;
import com.cmbb.smartkids.activity.user.model.DeliveryAddressListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.log.Log;

import java.util.ArrayList;

public class DeliveryAddressListActivity extends BaseActivity {
    private final String TAG = DeliveryAddressListActivity.class.getSimpleName();
    private final int DELIVERY_ADDRESS_MANAGER = 1222;
    private NestedScrollView nsv;
    private RecyclerView rv;
    private DeliveryAddressAdapter adapter;
    private int deliveryAddressId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_delivery_address_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initView() {
        nsv = (NestedScrollView) findViewById(R.id.nsv_self);
        rv = (RecyclerView) findViewById(R.id.rv_self);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DeliveryAddressAdapter("check", new ArrayList<DeliveryAddressListModel.DataEntity.RowsEntity>());
        rv.setAdapter(adapter);
    }

    private void initData() {
        if(getIntent() != null){
            deliveryAddressId = getIntent().getIntExtra("delivery_id", 0);
            Log.e(TAG, "deliveryAddressId0 = " + deliveryAddressId);
        }
        handleDeliveryAddressListRequest();
        setActionBarRight("管理");
        setTitle(getString(R.string.title_activity_delivery_address_choose));
        adapter.setOnCheckItemListener(onItemCheckListener);

    }

    @Override
    public void onBackPressed() {
        if(adapter.getChooseData() != null){
            getIntent().putExtra("delivery_address", adapter.getChooseData());
            setResult(RESULT_OK, getIntent());
        }
        finish();
    }

    @Override
    public void onClick(View v) {
        ManagerDeliveryAddressListActivity.skipFromActivity(this, DELIVERY_ADDRESS_MANAGER);
    }


    private CustomListener.ItemClickListener onItemCheckListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            DeliveryAddressListModel.DataEntity.RowsEntity item = (DeliveryAddressListModel.DataEntity.RowsEntity) object;
            adapter.setCheckId(item.getId());
            getIntent().putExtra("delivery_address", item);
            setResult(RESULT_OK, getIntent());
            finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == DELIVERY_ADDRESS_MANAGER) {
            handleDeliveryAddressListRequest();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleDeliveryAddressListRequest() {
        showWaitDialog();
        NetRequest.postRequest(Constants.ServiceInfo.DELIVERY_ADDRESS_LIST, BaseApplication.token, null, DeliveryAddressListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                DeliveryAddressListModel data = (DeliveryAddressListModel) object;
                if (data != null && data.getData() != null) {
                    Log.e(TAG, "deliveryAddressId = " + deliveryAddressId);
                    adapter.setData(data.getData().getRows(), deliveryAddressId);
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

    public static void skipFromActivity(Activity activity, int addressId, int requestCode) {// manager check
        Intent intent = new Intent(activity, DeliveryAddressListActivity.class);
        intent.putExtra("delivery_id", addressId);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void skipFromFragment(Fragment fragment, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), DeliveryAddressListActivity.class);
        fragment.startActivityForResult(intent, requestCode);
    }


}
