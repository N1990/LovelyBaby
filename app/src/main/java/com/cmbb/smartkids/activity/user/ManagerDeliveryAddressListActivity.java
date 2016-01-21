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

import java.util.ArrayList;

public class ManagerDeliveryAddressListActivity extends BaseActivity {
    private final String TAG = ManagerDeliveryAddressListActivity.class.getSimpleName();
    private final int DELIVERY_ADDRESS_DETAIL = 1223;
    private NestedScrollView nsv;
    private RecyclerView rv;
    private DeliveryAddressAdapter adapter;
    private boolean isModify;

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
        adapter = new DeliveryAddressAdapter("manager", new ArrayList<DeliveryAddressListModel.DataEntity.RowsEntity>());
        rv.setAdapter(adapter);
    }

    private void initData() {
        handleDeliveryAddressListRequest();
        setTitle(getString(R.string.title_activity_delivery_address_manager));
        setActionBarRight("添加");
        adapter.setOnManagerItemListener(onItemManagerListener);

    }


    private CustomListener.ItemClickListener onItemManagerListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            DeliveryAddressListModel.DataEntity.RowsEntity item = (DeliveryAddressListModel.DataEntity.RowsEntity) object;
            DeliveryAddressDetailActivity.skipFromActivity(ManagerDeliveryAddressListActivity.this, item.getId(), DELIVERY_ADDRESS_DETAIL);

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == DELIVERY_ADDRESS_DETAIL) {
            isModify = true;
            handleDeliveryAddressListRequest();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        DeliveryAddressModifyActivity.skipFromActivity(this, "add", DELIVERY_ADDRESS_DETAIL);
    }

    @Override
    public void onBackPressed() {
        if (isModify) {
            setResult(RESULT_OK);
            finish();
        } else {
            super.onBackPressed();
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
                    adapter.setData(data.getData().getRows());
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

    public static void skipFromActivity(Activity activity, int requestCode) {// manager check
        Intent intent = new Intent(activity, ManagerDeliveryAddressListActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }
    public static void skipFromActivity(Activity activity) {// manager check
        Intent intent = new Intent(activity, ManagerDeliveryAddressListActivity.class);
        activity.startActivity(intent);
    }

    public static void skipFromFragment(Fragment fragment, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), ManagerDeliveryAddressListActivity.class);
        fragment.startActivityForResult(intent, requestCode);
    }


}
