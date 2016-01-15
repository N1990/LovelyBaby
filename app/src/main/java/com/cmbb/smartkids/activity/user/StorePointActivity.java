package com.cmbb.smartkids.activity.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.StorePointAdapter;
import com.cmbb.smartkids.activity.user.model.StorePointModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/19 下午2:34
 */
public class StorePointActivity extends BaseActivity {

    private static final String TAG = StorePointActivity.class.getSimpleName();
    private LoadMoreRecyclerView lmrv;
    private StorePointAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initListener();
    }

    private void initListener() {
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_store_point));
        setActionBarRight("积分说明");
        lmrv = (LoadMoreRecyclerView) findViewById(R.id.lmrv_self);
        lmrv.setLinearLayout();
        adapter = new StorePointAdapter(getIntent().getIntExtra("gold", -1));
        adapter.setData(new ArrayList<StorePointModel.DataEntity.RowsEntity>());
        lmrv.setAdapter(adapter);
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
            pager++;
            handleRequest(pager, pagerSize);
        }
    };

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, ScoreComplainActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_storepoint;
    }

    public static void IntentStorePointActivity(Context context, int gold) {
        Intent intent = new Intent(context, StorePointActivity.class);
        intent.putExtra("gold", gold);
        context.startActivity(intent);
    }

    /**
     * 积分列表
     *
     * @param pager
     * @param pagerSize
     */
    private void handleRequest(int pager, int pagerSize) {
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.ServiceInfo.USER_GOLD_LIST, BaseApplication.token, params, StorePointModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                StorePointModel result = (StorePointModel) object;
                if (result.getData() != null && result.getData().getRows() != null && result.getData().getRows().size() > 0) {
                    lmrv.setHasContent();
                    adapter.addData(result.getData().getRows(), lmrv);
                }
                if (adapter.getDataSize() == 0) {
                    lmrv.setNoContent();
                }
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
