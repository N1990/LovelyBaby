package com.cmbb.smartkids.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.model.MyServiceListModel;
import com.cmbb.smartkids.activity.user.adapter.ServiceOrderAdapter;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.SPCache;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/20 14:26
 */
public class MyServiceOrderActivity extends BaseActivity{
    private final String TAG = MyServiceOrderActivity.class.getSimpleName();
    private LoadMoreRecyclerView lmrv;
    private ServiceOrderAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_service_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();

    }

    private void initView() {
        setTitle(getString(R.string.title_activity_my_service_order));
        lmrv = (LoadMoreRecyclerView) findViewById(R.id.lmrv_self);
        lmrv.setLinearLayout();
        adapter = new ServiceOrderAdapter();
        adapter.setData(new ArrayList<MyServiceListModel.DataEntity.RowsEntity>());
        lmrv.setAdapter(adapter);
    }

    private void initData() {

    }

    private void addListener() {
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
        adapter.setOnItemListener(itemClick);
    }

    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {
            showWaitDialog();
            handleRequest(pager, pagerSize);

        }

        @Override
        public void onRefresh() {
            adapter.clearData();
            pager = 0;
            handleRequest(pager, pagerSize);
        }

        @Override
        public void onLoadMore() {
            pager ++;
            handleRequest(pager, pagerSize);
        }
    };

    private CustomListener.ItemClickListener itemClick = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            MyServiceListModel.DataEntity.RowsEntity itemData = (MyServiceListModel.DataEntity.RowsEntity) object;
            Intent intent = new Intent(MyServiceOrderActivity.this, MyAcceptActivity.class);
            intent.putExtra("serviceId", itemData.getId());
            startActivity(intent);
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void handleRequest(int pager, int pagerSize){
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("myCenter", "1");
        params.put("isEredar", "1");
        params.put("id", SPCache.getString(Constants.USER_ID, ""));
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.ServiceInfo.MY_SERVICE_REQUEST, BaseApplication.token, params, MyServiceListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                MyServiceListModel listModel = (MyServiceListModel) object;
                if(listModel != null && listModel.getData() != null && listModel.getData().getRecords() != 0){
                    adapter.addData(listModel.getData().getRows(), lmrv);
                }else{
                    showShortToast(msg);
                }
                if(adapter.getDataSize() == 0)
                    lmrv.setNoContent();
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                showShortToast(message);
            }
        }));
    }


}
