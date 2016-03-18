package com.cmbb.smartkids.activity.order.v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.squareup.okhttp.Request;

/**
 * Created by javon on 16/3/17.
 */
public class UnpayOrderListActivity extends BaseOrderListActivity{


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setTitle(getString(R.string.title_unpay_order));
    }


    @Override
    public void onLoadMore() {
        pager ++;
        OrderListModel.getOrderListRequest("0", "1", pager, pagerSize, new OkHttpClientManager.ResultCallback<OrderListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(OrderListModel response) {
                if (response != null && response.getData() != null && response.getData().getRecords() != 0) {
                    adapter.addAll(response.getData().getRows());
                } else {
                    showShortToast(response.getMsg());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pager = 0;
        OrderListModel.getOrderListRequest("0", "1", pager, pagerSize, new OkHttpClientManager.ResultCallback<OrderListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(OrderListModel response) {
                if (response != null) {
                    adapter.clear();
                    adapter.addAll(response.getData().getRows());
                } else {
                    showShortToast(response.getMsg());
                }
            }
        });
    }

    public static void newInstance(Activity activity){
        activity.startActivity(new Intent(activity, UnpayOrderListActivity.class));
    }

}
