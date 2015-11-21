package com.cmbb.smartkids.activity.user;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.model.EvaluateListModel;
import com.cmbb.smartkids.activity.user.adapter.MyPerssionAdapter;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class PerssionListActivity extends BaseActivity {
    private final String TAG = PerssionListActivity.class.getSimpleName();
    private LoadMoreRecyclerView lmrv;
    private MyPerssionAdapter adapter;
    private int isPopman;
    private int pager = 0;
    private int pagerSize = 10;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_perssion_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }



    private void initView() {
        setTitle(getString(R.string.title_activity_perssion_list));
        lmrv = (LoadMoreRecyclerView) findViewById(R.id.lmrv_self);
        lmrv.setLinearLayout();
        adapter = new MyPerssionAdapter();
        adapter.setData(new ArrayList<EvaluateListModel.DataEntity.RowsEntity>());
        lmrv.setAdapter(adapter);
    }

    private void initData() {
        Bundle bundle = null;
        if(getIntent() != null && (bundle = getIntent().getExtras()) != null){
            isPopman = bundle.getInt("isPop");
        }else{
            showShortToast("传参出错~");
            return;
        }
    }

    private void addListener() {
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
        adapter.setOnHeaderListener(itemClick);
    }

    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {
            showWaitDialog();
            handleRequest(pager, pagerSize);

        }

        @Override
        public void onRefresh() {
            pager = 0;
            adapter.clearData();
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
            int userId = (int) object;
            Intent intent = new Intent(PerssionListActivity.this, UserCenterActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }



    private void handleRequest(int pager, int pagerSize){
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("myCenter", 1 + "");
        params.put("isEredar", isPopman + "");
        NetRequest.postRequest(Constants.ServiceInfo.EVALUATE_LIST_REQUEST, BaseApplication.token, params, EvaluateListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                EvaluateListModel data = (EvaluateListModel) object;
                if(data != null && data.getData() != null && data.getData().getRows().size() > 0){
                    lmrv.setHasContent();
                    adapter.addData(data.getData().getRows(), lmrv);
                }
                if(adapter.getDataSize() == 0) {
                    lmrv.setNoContent();
                }
                showShortToast(msg);
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
