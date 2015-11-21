package com.cmbb.smartkids.activity.user.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.model.EvaluateListModel;
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.activity.user.adapter.MyPerssionAdapter;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/12 19:15
 */
public class EvaluateFragment extends BaseFragment {
    private final String TAG = EvaluateFragment.class.getSimpleName();
    public LoadMoreRecyclerView lmrv;
    private NestedScrollView nsv;
    private MyPerssionAdapter adapter;
    private String isPopman, userId;
    private int pager = 0;
    private int pagerSize = 10;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_evaluate, null);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        addListener();
    }

    private void initView(){
        lmrv = (LoadMoreRecyclerView) getView().findViewById(R.id.lmrv_self);
        nsv = (NestedScrollView) getView().findViewById(R.id.nsv_self);
        lmrv.setLinearLayout();
        adapter = new MyPerssionAdapter();
        adapter.setData(new ArrayList<EvaluateListModel.DataEntity.RowsEntity>());
        lmrv.setAdapter(adapter);
    }

    private void initData(){
        Bundle bundle = null;
        if((bundle = getArguments()) != null){
            isPopman = bundle.getString("isPopman");
            userId = bundle.getString("userId");
        }else{
            showShortToast("传参出错~");
            return;
        }
    }

    private void addListener(){
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
        adapter.setOnHeaderListener(itemClick);
    }

    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {
            showWaitsDialog();
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
            Intent intent = new Intent(getActivity(), UserCenterActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
    };

    private void handleRequest(int pager, int pagerSize){
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("myCenter", 0 + "");
        params.put("id", userId + "");
        params.put("isEredar", isPopman + "");
        NetRequest.postRequest(Constants.ServiceInfo.EVALUATE_LIST_REQUEST, BaseApplication.token, params, EvaluateListModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                EvaluateListModel data = (EvaluateListModel) object;
                if (data != null && data.getData() != null && data.getData().getRows().size() > 0) {
                    lmrv.setVisibility(View.VISIBLE);
                    nsv.setVisibility(View.GONE);
                    adapter.addData(data.getData().getRows(), lmrv);
                }
                if (adapter.getDataSize() == 0) {
                    lmrv.setVisibility(View.GONE);
                    nsv.setVisibility(View.VISIBLE);
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
