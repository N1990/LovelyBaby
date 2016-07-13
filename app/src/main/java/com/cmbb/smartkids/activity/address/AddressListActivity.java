package com.cmbb.smartkids.activity.address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.address.adapter.AddressListAdapter;
import com.cmbb.smartkids.activity.address.model.DeliveryAddressListModel;
import com.cmbb.smartkids.activity.user.DeliveryAddressDetailActivity;
import com.cmbb.smartkids.activity.user.DeliveryAddressModifyActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/7/11 下午8:15
 * 修改人：N.Sun
 * 修改时间：16/7/11 下午8:15
 * 修改备注：
 */
public class AddressListActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private static final String TAG = AddressListActivity.class.getSimpleName();
    private final int DELIVERY_ADDRESS_DETAIL = 1223;

    protected SmartRecyclerView mSmartRecyclerView;
    protected AddressListAdapter adapter;

    private int pager = 0;
    private int pagerSize = 10;

    @Override
    protected void init(Bundle savedInstanceState) {
        setActionBarRight("添加");
        setTitle(getString(R.string.title_activity_delivery_address_manager));
        initRecyclerView();
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_list_layout;
    }

    private void initRecyclerView() {
        mSmartRecyclerView = (SmartRecyclerView) findViewById(R.id.recyclerView);
        mSmartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressListAdapter(this);
        mSmartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        mSmartRecyclerView.setRefreshListener(this);
    }

    @Override
    public void onItemClick(int position) {
        switch (getIntent().getIntExtra("type", 0)) {
            case 0:
                DeliveryAddressDetailActivity.skipFromActivity(this, adapter.getItem(position).getId(), DELIVERY_ADDRESS_DETAIL);
                break;
            case 1://choose
                getIntent().putExtra("delivery_address", adapter.getItem(position));
                setResult(RESULT_OK, getIntent());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        DeliveryAddressModifyActivity.skipFromActivity(this, "add", DELIVERY_ADDRESS_DETAIL);
    }

    @Override
    public void onLoadMore() {
        pager++;
        DeliveryAddressListModel.addressListRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<DeliveryAddressListModel>() {
            @Override
            public void onError(Request request, Exception e, String response) {
                if (TextUtils.isEmpty(response)) {
                    showToast(getString(R.string.err_network));
                    Log.e(TAG, e.toString());
                } else {
                    showToast(response);
                }
            }

            @Override
            public void onResponse(DeliveryAddressListModel response) {
                if (response != null) {
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pager = 0;
        DeliveryAddressListModel.addressListRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<DeliveryAddressListModel>() {
            @Override
            public void onError(Request request, Exception e, String response) {
                if (TextUtils.isEmpty(response)) {
                    showToast(getString(R.string.err_network));
                    Log.e(TAG, e.toString());
                } else {
                    showToast(response);
                }
            }

            @Override
            public void onResponse(DeliveryAddressListModel response) {
                if (response != null) {
                    adapter.clear();
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == DELIVERY_ADDRESS_DETAIL) {
            onRefresh();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public static void newIntent(BaseActivity baseActivity, int type, int requestCode) {
        Intent intent = new Intent(baseActivity, AddressListActivity.class);
        intent.putExtra("type", type);
        baseActivity.startActivityForResult(intent, requestCode);
    }

    public static void newIntent(Context context, int type) {
        Intent intent = new Intent(context, AddressListActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }
}
