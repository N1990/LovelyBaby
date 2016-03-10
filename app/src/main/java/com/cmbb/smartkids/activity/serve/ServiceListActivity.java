//package com.cmbb.smartkids.activity.serve;
//
//import android.os.Bundle;
//import android.view.View;
//
//import com.cmbb.smartkids.R;
//import com.cmbb.smartkids.activity.serve.model.MyServiceListModel;
//import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
//import com.cmbb.smartkids.activity.user.adapter.MyServiceAdapter;
//import com.cmbb.smartkids.base.BaseActivity;
//import com.cmbb.smartkids.base.BaseApplication;
//import com.cmbb.smartkids.base.Constants;
//import com.cmbb.smartkids.base.CustomListener;
//import com.cmbb.smartkids.network.NetRequest;
//import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// * 项目名称：LovelyBaby
// * 类描述：
// * 创建人：javon
// * 创建时间：2015/9/9 15:35
// */
//public class ServiceListActivity extends BaseActivity {
//    private final String TAG = ServiceListActivity.class.getSimpleName();
//    private LoadMoreRecyclerView lmrv;
//    private MyServiceAdapter adapter;
//    private int userId, myCenter, isPopman;
//    private int pager = 0;
//    private int pagerSize = 5;
//
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_my_service_list;
//    }
//
//    @Override
//    protected void init(Bundle savedInstanceState) {
//        initView();
//        initData();
//        addListener();
//    }
//
//    private void initView() {
//        lmrv = (LoadMoreRecyclerView) findViewById(R.id.lmrv_self);
//        lmrv.setLinearLayout();
//        adapter = new MyServiceAdapter();
//        adapter.setData(new ArrayList<ServiceListModel.DataEntity.RowsEntity>());
//        lmrv.setAdapter(adapter);
//    }
//
//    private void initData() {
//        Bundle bundle = null;
//        if (getIntent() != null && (bundle = getIntent().getExtras()) != null) {
//            userId = bundle.getInt("userId");
//            isPopman = bundle.getInt("isPopman");
//            myCenter = bundle.getInt("myCenter");
//            if (myCenter == 1) {
//                setTitle(getString(R.string.title_activity_my_service));
//            } else {
//                setTitle("他的服务");
//            }
//        } else {
//            showShortToast("传参出错~");
//        }
//
//    }
//
//    private void addListener() {
//        lmrv.setPullLoadMoreListener(lmrvListener);
//        lmrv.setInitializeWithoutPb();
//        adapter.setOnFooterTryAgain(this);
//        adapter.setOnItemClick(itemClick);
//    }
//
//    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
//        @Override
//        public void onInitialize() {
//            showWaitDialog();
//            handleRequest(pager, pagerSize);
//
//        }
//
//        @Override
//        public void onRefresh() {
//            adapter.clearData();
//            pager = 0;
//            handleRequest(pager, pagerSize);
//        }
//
//        @Override
//        public void onLoadMore() {
//            pager++;
//            handleRequest(pager, pagerSize);
//        }
//    };
//
//    private CustomListener.ItemClickListener itemClick = new CustomListener.ItemClickListener() {
//        @Override
//        public void onItemClick(View v, int position, Object object) {
//            showShortToast("position:" + position);
//        }
//    };
//
//    @Override
//    public void onClick(View v) {
//        super.onClick(v);
//    }
//
//    private void handleRequest(int pager, int pagerSize) {
//        HashMap<String, String> params = new HashMap<>();
//        params.put("myCenter", String.valueOf(myCenter));
//        params.put("isEredar", String.valueOf(isPopman));
//        params.put("id", String.valueOf(userId));
//        params.put("pageNo", String.valueOf(pager));
//        params.put("numberOfPerPage", String.valueOf(pagerSize));
//        NetRequest.postRequest(Constants.ServiceInfo.MY_SERVICE_REQUEST, BaseApplication.token, params, MyServiceListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
//            @Override
//            public void onSuccessListener(Object object, String msg) {
//                hideWaitDialog();
//                lmrv.setPullLoadMoreCompleted();
//                MyServiceListModel listModel = (MyServiceListModel) object;
//                if (listModel != null && listModel.getData() != null && listModel.getData().getRecords() != 0) {
//                    adapter.addData(listModel.getData().getRows(), lmrv);
//                } else {
//                    showShortToast(msg);
//                }
//                if (adapter.getDataSize() == 0)
//                    lmrv.setNoContent();
//            }
//
//            @Override
//            public void onErrorListener(String message) {
//                hideWaitDialog();
//                lmrv.setPullLoadMoreCompleted();
//                showShortToast(message);
//            }
//        }));
//    }
//
//
//}
