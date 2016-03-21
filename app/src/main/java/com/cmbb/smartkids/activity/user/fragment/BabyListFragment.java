package com.cmbb.smartkids.activity.user.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.BabyDiaryListActivity;
import com.cmbb.smartkids.activity.diary.model.BabyListModel;
import com.cmbb.smartkids.activity.user.adapter.UserBabyDiaryAdapter;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by javon on 15/12/17.
 */
public class BabyListFragment extends BaseFragment {
    private final String TAG = BabyListFragment.class.getSimpleName();
    private final int BABY_DETAIL_REQUEST = 10020;
    public RecyclerView rv;
    private NestedScrollView nsv;
    private UserBabyDiaryAdapter adapter;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recycler_layout, null);
        return root;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        addListener();
    }

    private void initView(View view) {
        rv = (RecyclerView) view.findViewById(R.id.rv_self);
        nsv = (NestedScrollView) getView().findViewById(R.id.nsv_self);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new UserBabyDiaryAdapter();
        adapter.setData(new ArrayList<BabyListModel.DataEntity.RowsEntity>());
        rv.setAdapter(adapter);
        rv.setVisibility(View.GONE);
        nsv.setVisibility(View.VISIBLE);
    }

    private void initData() {
        Bundle bundle = null;
        if ((bundle = getArguments()) != null) {
            userId = bundle.getString("userId");
            handleBabyListRequest();
        } else {
            showShortToast("传参出错~");
            return;
        }
    }

    private void addListener() {
        adapter.setOnItemListener(new CustomListener.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Object object) {
                BabyListModel.DataEntity.RowsEntity item = (BabyListModel.DataEntity.RowsEntity) object;
                Intent intent = new Intent(getActivity(), BabyDiaryListActivity.class);
                intent.putExtra("baby_model", item);
                intent.putExtra("my_center", 0);
                intent.putExtra("user_id", Integer.parseInt(userId));
                startActivityForResult(intent, BABY_DETAIL_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BABY_DETAIL_REQUEST && resultCode == Activity.RESULT_OK) {
            handleBabyListRequest();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleBabyListRequest() {
//        showWaitsDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("myCenter", "0");
        params.put("id", String.valueOf(userId));
        NetRequest.postRequest(Constants.ServiceInfo.BABY_LIST, BaseApplication.token, params, BabyListModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                BabyListModel data = (BabyListModel) object;
                if (data.getData() != null && data.getData().getRows() != null) {
                    adapter.setData(data.getData().getRows());
                    rv.setVisibility(View.VISIBLE);
                    nsv.setVisibility(View.GONE);
                }

                showShortToast(msg);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }


}
