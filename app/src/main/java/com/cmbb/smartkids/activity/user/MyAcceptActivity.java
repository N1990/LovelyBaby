package com.cmbb.smartkids.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.view.VerifyOrderActivity;
import com.cmbb.smartkids.activity.order.fragment.AcceptOrderFragment;
import com.cmbb.smartkids.activity.order.fragment.VerifyOrderFragment;
import com.cmbb.smartkids.activity.user.adapter.SelfCommentPagerAdapter;
import com.cmbb.smartkids.base.BaseActivity;

import java.util.ArrayList;

public class MyAcceptActivity extends BaseActivity {
    private final String TAG = MyAcceptActivity.class.getSimpleName();
    public static final int VERIFY_ORDER_REQUEST = 3001;
    private TabLayout tl;
    private ViewPager vpSlef;
    private SelfCommentPagerAdapter adapter;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private VerifyOrderFragment verify;
    private AcceptOrderFragment accept;
    private int serviceId = -1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_redirect;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        if(getIntent() != null && getIntent().getExtras() != null)
            serviceId = getIntent().getIntExtra("serviceId", -1);
        initView();
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_accept_order));
        tl = (TabLayout) findViewById(R.id.tl_self);
        tl.setTabMode(TabLayout.MODE_FIXED);
        vpSlef = (ViewPager) findViewById(R.id.vp_slef);
        adapter = new SelfCommentPagerAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putInt("serviceId", serviceId);
        accept = new AcceptOrderFragment();
        accept.setArguments(bundle);
        fragments.add(accept);
        titles.add("已接单");
        verify = new VerifyOrderFragment();
        verify.setArguments(bundle);
        fragments.add(verify);
        titles.add("已验证");
        adapter.setData(fragments, titles);
        vpSlef.setAdapter(adapter);
        tl.setupWithViewPager(vpSlef);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_verify){
            Intent intent = new Intent(this, VerifyOrderActivity.class);
            intent.putExtra("serviceId", serviceId);
            startActivityForResult(intent, VERIFY_ORDER_REQUEST);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VERIFY_ORDER_REQUEST && resultCode == RESULT_OK){
            verify.onActivityResult(requestCode,resultCode,data);
            accept.onActivityResult(requestCode,resultCode,data);
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
