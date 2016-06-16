package com.cmbb.smartkids.activity.order.view;

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
public class UnEvaluateOrderListActivity extends BaseOrderListActivity{


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setTitle(getString(R.string.title_unevaluate_order));
    }


    @Override
    public void onLoadMore() {
        pager ++;
        OrderListModel.getOrderListRequest("0", "3", pager, pagerSize, new OkHttpClientManager.ResultCallback<OrderListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(getString(R.string.is_netwrok));
            }

            @Override
            public void onResponse(OrderListModel response) {
                if (response != null) {
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pager = 0;
        OrderListModel.getOrderListRequest("0", "3", pager, pagerSize, new OkHttpClientManager.ResultCallback<OrderListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(getString(R.string.is_netwrok));
            }

            @Override
            public void onResponse(OrderListModel response) {
                if (response != null) {
                    adapter.clear();
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    public static void newInstance(Activity activity){
        activity.startActivity(new Intent(activity, UnEvaluateOrderListActivity.class));
    }






}
