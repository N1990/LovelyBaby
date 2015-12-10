package com.cmbb.smartkids.activity.message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.MyMsgAdapter;
import com.cmbb.smartkids.base.BaseActivity;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

public class MessageListActivity extends BaseActivity {
    private final String TAG = MessageListActivity.class.getSimpleName();
    private LoadMoreRecyclerView lmrv;
    private MyMsgAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }


    private void initView() {

    }

    private void initData() {


    }

   private void addListener(){



   }


}
