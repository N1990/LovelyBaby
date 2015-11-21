package com.cmbb.smartkids.activity.user.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.activity.user.adapter.MyCareAdapter;
import com.cmbb.smartkids.activity.user.model.FriendListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 13:11
 */
public class MyCareFriendFragment extends BaseFragment{
    private final String TAG = MyCareFriendFragment.class.getSimpleName();
    private final int USER_CENTER_REQUEST = 1101;
    private LoadMoreRecyclerView lmrv;
    private MyCareAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerview_layout, null);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        addListener();
    }

    private void initView() {
        lmrv = (LoadMoreRecyclerView) getView().findViewById(R.id.lmrv_self);
        lmrv.setLinearLayout();
        adapter = new MyCareAdapter();
        adapter.setData(new ArrayList<FriendListModel.DataEntity.RowsEntity>());// 模拟数据
        lmrv.setAdapter(adapter);
    }

    private void initData() {

    }

    private void addListener() {
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
        adapter.setOnItemClick(itemClick);
    }

    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {
            showWaitsDialog();
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
            adapter.setLoadMore();
            pager ++;
            handleRequest(pager, pagerSize);
        }
    };

    private CustomListener.ItemClickListener itemClick = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            FriendListModel.DataEntity.RowsEntity itemData = (FriendListModel.DataEntity.RowsEntity) object;
            int userId = itemData.getUserId();
            Intent intent = new Intent(getActivity(), UserCenterActivity.class);
            intent.putExtra("userId", userId);
            startActivityForResult(intent, USER_CENTER_REQUEST);
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == USER_CENTER_REQUEST && resultCode == -1){
            adapter.clearData();
            pager = 0;
            showWaitsDialog();
            handleRequest(pager,pagerSize);
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     *
     * 加载好友列表
     * @param pager
     * @param pagerSize
     */
    public void handleRequest(int pager, int pagerSize){
        HashMap<String, String> params = new HashMap<>();
        params.put("typeNum", "0");
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));

        NetRequest.postRequest(Constants.ServiceInfo.CARE_LIST_REQUEST, BaseApplication.token, params, FriendListModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                FriendListModel friend = (FriendListModel) object;
                Log.e(TAG, "friend:" + friend.toString());
                if(friend != null && friend.getData() != null && friend.getData().getRecords() != 0){
                    adapter.addData(friend.getData().getRows(), lmrv);
                }else{
                    showShortToast(msg);
                }
                if(adapter.getDataSize() == 0)
                    lmrv.setNoContent();
            }

            @Override
            public void onErrorListener(String message) {
                Log.e(TAG, "friend error:"+message);
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                showShortToast(message);
            }
        }));
    }



}
