package com.cmbb.smartkids.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.fragment.AllOrderFragment;
import com.cmbb.smartkids.activity.order.fragment.UnEvaluateOrderFragment;
import com.cmbb.smartkids.activity.order.fragment.UnConsumeOrderFragment;
import com.cmbb.smartkids.activity.order.fragment.ReimburseOrderFragment;
import com.cmbb.smartkids.activity.order.fragment.UnpayOrderFragment;
import com.cmbb.smartkids.activity.user.adapter.SelfCommentPagerAdapter;
import com.cmbb.smartkids.base.BaseActivity;

import java.util.ArrayList;

public class MyOrderListActivity extends BaseActivity {
    private final String TAG = MyOrderListActivity.class.getSimpleName();
    private TabLayout tl;
    private ViewPager vpSlef;
    private SelfCommentPagerAdapter adapter;
    ArrayList<Fragment> fragments;
    ArrayList<String> titles;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_redirect;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_my_order_list));
        tl = (TabLayout) findViewById(R.id.tl_self);
        tl.setTabMode(TabLayout.MODE_FIXED);
        vpSlef = (ViewPager) findViewById(R.id.vp_slef);
        adapter = new SelfCommentPagerAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
    }

    private void initData() {
        AllOrderFragment all = new AllOrderFragment();
        fragments.add(all);
        titles.add("全部");
        UnpayOrderFragment unpay = new UnpayOrderFragment();
        fragments.add(unpay);
        titles.add("未付款");
        UnConsumeOrderFragment unConsume = new UnConsumeOrderFragment();
        fragments.add(unConsume);
        titles.add("未消费");
        UnEvaluateOrderFragment unEvaluate = new UnEvaluateOrderFragment();
        fragments.add(unEvaluate);
        titles.add("待评价");
        ReimburseOrderFragment reimburse = new ReimburseOrderFragment();
        fragments.add(reimburse);
        titles.add("退款");
        adapter.setData(fragments, titles);
        vpSlef.setAdapter(adapter);
        tl.setupWithViewPager(vpSlef);
    }

}
