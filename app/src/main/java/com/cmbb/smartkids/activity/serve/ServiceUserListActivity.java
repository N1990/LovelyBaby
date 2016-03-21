package com.cmbb.smartkids.activity.serve;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.model.ActiveDetailModel;
import com.cmbb.smartkids.activity.serve.adapter.ServiceUserAdapter;
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.CustomListener;

import java.util.ArrayList;

public class ServiceUserListActivity extends BaseActivity {
    private final String TAG = ServiceUserListActivity.class.getSimpleName();
    private RecyclerView rv;
    private ServiceUserAdapter adapter;
    private ArrayList<ActiveDetailModel.DataEntity.UserInfoListEntity> data;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_user_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();

    }

    private void initView() {
        setTitle(getString(R.string.title_activity_popman));
        rv = (RecyclerView) findViewById(R.id.lmrv_self);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        if (getIntent() != null) {
            data = getIntent().getParcelableArrayListExtra("users");
            adapter = new ServiceUserAdapter(data);
            rv.setAdapter(adapter);
        } else {
            showShortToast("传参出错~");
        }
    }

    private void addListener() {
        adapter.setOnItemListener(itemListener);
    }

    private CustomListener.ItemClickListener itemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            ActiveDetailModel.DataEntity.UserInfoListEntity itemData = (ActiveDetailModel.DataEntity.UserInfoListEntity) object;
            UserCenterActivity.newIntent(ServiceUserListActivity.this, itemData.getUserId());
        }
    };

}
