package com.cmbb.smartkids.activity.order.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.adapter.AcceptOrderAdapter;
import com.cmbb.smartkids.activity.order.model.OrderListModel;
import com.cmbb.smartkids.activity.user.MyAcceptActivity;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/15 11:50
 */
public class AcceptOrderFragment extends BaseFragment implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private final String TAG = VerifyOrderFragment.class.getSimpleName();
    private SmartRecyclerView srv;
    private AcceptOrderAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;
    private int serviceId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerview_layout, null);
        srv = (SmartRecyclerView) root.findViewById(R.id.srv_self);
        srv.setLayoutManager(new LinearLayoutManager(getActivity()));
        srv.setRefreshListener(this);
        adapter = new AcceptOrderAdapter(getActivity());
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        srv.setAdapterWithProgress(adapter);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        onRefresh();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null)
            serviceId = bundle.getInt("serviceId", -1);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MyAcceptActivity.VERIFY_ORDER_REQUEST && resultCode == -1) {
            onRefresh();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onLoadMore() {
        pager++;
        OrderListModel.getAcceptOrderListRequest(serviceId, pager, pagerSize, new OkHttpClientManager.ResultCallback<OrderListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
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
        OrderListModel.getAcceptOrderListRequest(serviceId, pager, pagerSize, new OkHttpClientManager.ResultCallback<OrderListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
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
}
