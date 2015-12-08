package com.cmbb.smartkids.activity.user;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.SelfCommentPagerAdapter;
import com.cmbb.smartkids.activity.user.fragment.MyCareFriendFragment;
import com.cmbb.smartkids.activity.user.fragment.MyCarePopmanFragment;
import com.cmbb.smartkids.activity.user.fragment.MyCollectCommunityFragment;
import com.cmbb.smartkids.activity.user.fragment.MyCollectServiceFragment;
import com.cmbb.smartkids.activity.user.fragment.MyServiceMessageFragement;
import com.cmbb.smartkids.activity.user.fragment.MySystemMessageFragement;
import com.cmbb.smartkids.base.BaseActivity;

import java.util.ArrayList;

public class MyListRedirectActivity extends BaseActivity {
    private final String TAG = MyListRedirectActivity.class.getSimpleName();
    private TabLayout tl;
    private ViewPager vpSlef;
    private SelfCommentPagerAdapter adapter;
    private String flag;
    ArrayList<Fragment> fragments;
    ArrayList<String> titles;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_redirect;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        tl = (TabLayout) findViewById(R.id.tl_self);
        tl.setTabMode(TabLayout.MODE_FIXED);
        vpSlef = (ViewPager) findViewById(R.id.vp_slef);
        adapter = new SelfCommentPagerAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        selectView();
    }


    private void selectView(){
        Bundle bundle = null;
        if(getIntent() != null && (bundle = getIntent().getExtras()) != null)
            flag = bundle.getString("flag");
        if("service".equals(flag)){
            setTitle("我的服务");
//            myServiceView();
        }else if("community".equals(flag)){
            setTitle("我的话题");
        }else if("care".equals(flag)){
            setTitle("我的关注");
            myCareView();
        }else if("message".equals(flag)){
            setTitle("我的消息");
            myMessageView();
        }else if("collect".equals(flag)){
            setTitle("我的收藏");
            myColletView();
        }

    }



    private void myCareView(){
        Fragment friendFra = new MyCareFriendFragment();
        titles.add("朋友");
        fragments.add(friendFra);
        Fragment popmanFra = new MyCarePopmanFragment();
        titles.add("达人");
        fragments.add(popmanFra);
        adapter.setData(fragments, titles);
        vpSlef.setAdapter(adapter);
        tl.setupWithViewPager(vpSlef);
    }

    private void myMessageView(){
        Fragment serviceFra = new MyServiceMessageFragement();
        titles.add("服务");
        fragments.add(serviceFra);
        Fragment systemFra = new MySystemMessageFragement();
        titles.add("系统");
        fragments.add(systemFra);
        adapter.setData(fragments, titles);
        vpSlef.setAdapter(adapter);
        tl.setupWithViewPager(vpSlef);
    }

    private void myColletView(){
        Fragment friendFra = new MyCollectServiceFragment();
        titles.add("服务");
        fragments.add(friendFra);
        Fragment popmanFra = new MyCollectCommunityFragment();
        titles.add("话题");
        fragments.add(popmanFra);
        adapter.setData(fragments, titles);
        vpSlef.setAdapter(adapter);
        tl.setupWithViewPager(vpSlef);
    }

}
