package com.cmbb.smartkids.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.fragment.CommunityFragment;
import com.cmbb.smartkids.activity.home.fragment.HomeFragment;
import com.cmbb.smartkids.activity.home.fragment.MoreFragment;
import com.cmbb.smartkids.activity.home.fragment.ServiceFragment;
import com.cmbb.smartkids.activity.home.fragment.UserFragment;
import com.cmbb.smartkids.activity.user.MySetActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.umeng.update.UmengUpdateAgent;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends BaseActivity {
    private final String TAG = HomeActivity.class.getSimpleName();
    public static final int MY_SET_MODIFY = 2001;
    private Boolean isQuit = false;// 退出应用标识符
    private Timer timer = new Timer();// 程序退出定时器
    private TextView tvMain, tvService, tvCommunity, tvUser, tvMore;
    private Fragment homeFra, activeFra, cityFra, userFra, moreFra;
    private FragmentManager localFraManager;
    private int currIndex = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //关闭wifi
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(this);
        setNoBack();
        initView();
        initData();
        addListener();
    }

    private void initView() {
        localFraManager = getSupportFragmentManager();
        tvMain = (TextView) findViewById(R.id.tv_home_main);
        tvService = (TextView) findViewById(R.id.tv_home_service);
        tvCommunity = (TextView) findViewById(R.id.tv_home_community);
        tvUser = (TextView) findViewById(R.id.tv_home_user);
        tvMore = (TextView) findViewById(R.id.tv_home_more);
    }

    private void initData() {
        selectedFra(0);
    }


    private void addListener() {
        tvMain.setOnClickListener(this);
        tvService.setOnClickListener(this);
        tvCommunity.setOnClickListener(this);
        tvUser.setOnClickListener(this);
        tvMore.setOnClickListener(this);
    }


    private void hideFragment(FragmentTransaction fraTransaction) {
        List<Fragment> fragments = localFraManager.getFragments();
        if (fragments != null && fragments.size() != 0) {
            for (Fragment f : fragments) {
                fraTransaction.hide(f);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_main:
                selectedFra(0);
                break;
            case R.id.tv_home_service:
                selectedFra(1);
                break;
            case R.id.tv_home_community:
                selectedFra(2);
                break;
            case R.id.tv_home_user:
                selectedFra(3);
                break;
            case R.id.tv_home_more:
                selectedFra(4);
                break;
            case R.id.iv_main_toolbar_right:
                showShortToast("还未开通消息功能");
                break;
            case R.id.iv_main_toolbar_left:
                Intent intent = new Intent(HomeActivity.this, MySetActivity.class);
                startActivityForResult(intent, MY_SET_MODIFY);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (currIndex == 2) {
            ((CommunityFragment) cityFra).addDoubleClick();

        }
        if (!isQuit) {
            isQuit = true;
            showShortToast(getString(R.string.quit_app));
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    isQuit = false;
                }
            };
            timer.schedule(task, 2000);
        } else {
            Intent intent = new Intent(Constants.INTENT_ACTION_EXIT_APP);
            sendBroadcast(intent);
        }
    }


    private void selectedFra(int index) {
        currIndex = index;
        FragmentTransaction fraTransaction = localFraManager.beginTransaction();
        hideFragment(fraTransaction);
        switch (index) {
            case 0:
                reflushTab();
                if (homeFra == null) {
                    homeFra = new HomeFragment();
                    fraTransaction.add(R.id.fl_home_content, homeFra);
                }
                fraTransaction.show(homeFra);
                fraTransaction.commit();
                tvMain.setSelected(true);
                break;
            case 1:
                reflushTab();
                if (activeFra == null) {
                    activeFra = new ServiceFragment();
                    fraTransaction.add(R.id.fl_home_content, activeFra);
                }
                fraTransaction.show(activeFra);
                fraTransaction.commit();
                tvService.setSelected(true);
                break;
            case 2:
                reflushTab();
                if (cityFra == null) {
                    cityFra = new CommunityFragment();
                    fraTransaction.add(R.id.fl_home_content, cityFra);
                }
                fraTransaction.show(cityFra);
                fraTransaction.commit();
                tvCommunity.setSelected(true);
                break;
            case 3:
                reflushTab();
                if (userFra == null) {
                    userFra = new UserFragment();
                    fraTransaction.add(R.id.fl_home_content, userFra);
                }
                fraTransaction.show(userFra);
                fraTransaction.commit();
                tvUser.setSelected(true);

                break;
            case 4:
                reflushTab();
                if (moreFra == null) {
                    moreFra = new MoreFragment();
                    fraTransaction.add(R.id.fl_home_content, moreFra);
                }
                fraTransaction.show(moreFra);
                fraTransaction.commit();
                tvMore.setSelected(true);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currIndex", currIndex);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currIndex = savedInstanceState.getInt("currIndex", 0);
        selectedFra(currIndex);
    }

    private void reflushTab() {
        tvMain.setSelected(false);
        tvService.setSelected(false);
        tvCommunity.setSelected(false);
        tvUser.setSelected(false);
        tvMore.setSelected(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_SET_MODIFY && resultCode == RESULT_OK) {
            if (userFra != null)
                userFra.onActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
