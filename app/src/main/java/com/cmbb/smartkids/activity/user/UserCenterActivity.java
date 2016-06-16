package com.cmbb.smartkids.activity.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.user.adapter.UserCenterAdapter;
import com.cmbb.smartkids.activity.user.fragment.BabyListFragment;
import com.cmbb.smartkids.activity.user.fragment.CommunityFragment;
import com.cmbb.smartkids.activity.user.fragment.MyEvaluateUserFragment;
import com.cmbb.smartkids.activity.user.fragment.ServiceFragment;
import com.cmbb.smartkids.activity.user.model.UserCenterModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;

public class UserCenterActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {
    private final String TAG = UserCenterActivity.class.getSimpleName();
    private SimpleDraweeView ivCenterBg, ivHeader;
    private TextView tvCare;
    private TextView tvNickename, tvIdentity, tvFan, tvIntroduction;// tvGroup
    private AppBarLayout abl;
    private RatingBar rb;
    //    private ProgressBar pb;
    private TabLayout tl;
    private ViewPager vp;
    private UserCenterAdapter adapter;
    private int userId;
    private UserCenterModel.DataEntity userModel;
    private boolean careFlag;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;

    private ServiceFragment serviceFra;
    private CommunityFragment communityFra;
    private MyEvaluateUserFragment evaluateFra;
    private BabyListFragment diaryFra;

    @Override
    protected int getLayoutId() {
        needActionBar = false;
        return R.layout.activity_user_center;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }


    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_user_center);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        abl = (AppBarLayout) findViewById(R.id.appbar);
        ivCenterBg = (SimpleDraweeView) findViewById(R.id.iv_user_center_bg);
        tvCare = (TextView) findViewById(R.id.tv_main_toolbar_right);
        ivHeader = (SimpleDraweeView) findViewById(R.id.iv_user_center_header);
        tvNickename = (TextView) findViewById(R.id.tv_user_center_nickename);
        rb = (RatingBar) findViewById(R.id.rb_user_center_perssion);
//        pb = (ProgressBar) findViewById(R.id.pb_home_myself_grow);
//        tvGroup = (TextView) findViewById(R.id.tv_myself_progress);
        tvIdentity = (TextView) findViewById(R.id.tv_user_center_identity);
        tvFan = (TextView) findViewById(R.id.tv_user_center_fan);
        tvIntroduction = (TextView) findViewById(R.id.tv_user_center_introduction);
        tl = (TabLayout) findViewById(R.id.tl_user_center);
        vp = (ViewPager) findViewById(R.id.vp_user_center);
        adapter = new UserCenterAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        serviceFra = new ServiceFragment();
        communityFra = new CommunityFragment();
        evaluateFra = new MyEvaluateUserFragment();
        diaryFra = new BabyListFragment();
    }

    private void initData() {
        Bundle bundle = null;
        if (getIntent() != null && (bundle = getIntent().getExtras()) != null) {
            userId = bundle.getInt("userId", -1);
        }
        hanleRequest();
    }


    private void addListener() {
        tvCare.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main_toolbar_right:
                handleCareRequest();
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_OK);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 获取用户详细信息
     */
    private void hanleRequest() {
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("userAttentionId", String.valueOf(userId));
        params.put("myCenter", "0");
        NetRequest.postRequest(Constants.ServiceInfo.USER_INFO_REQUEST, BaseApplication.token, params, UserCenterModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                UserCenterModel userCenter = (UserCenterModel) object;
                if (userCenter != null && userCenter.getData() != null) {
                    userModel = userCenter.getData();
                    findViewById(R.id.appbar).setVisibility(View.VISIBLE);
                    findViewById(R.id.vp_user_center).setVisibility(View.VISIBLE);
                    FrescoTool.loadImage(ivCenterBg, userModel.getBackgroundImg(), userModel.getBackImgWidth(), userModel.getBackImgHeight());
                    FrescoTool.loadImage(ivHeader, userModel.getUserSmallImg());
                    tvNickename.setText(userModel.getUserNike());
                    tvIdentity.setText(userModel.getUserRole().get(0).getEredarName());
                    tvFan.setText("Fans(" + userModel.getFans() + ")");
                    rb.setRating(userModel.getUserLevel());

                    if (userModel.getIsLoginUser() == 0) {
                        tvCare.setVisibility(View.VISIBLE);
                        if (userModel.getIsAttention() == 0) {
                            careFlag = false;
                            tvCare.setText("关注");
                        } else {
                            careFlag = true;
                            tvCare.setText("取消关注");
                        }
                    } else {
                        tvCare.setVisibility(View.GONE);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("userId", String.valueOf(userId));
                    bundle.putString("isPopman", String.valueOf(userModel.getIsEredar()));
                    serviceFra.setArguments(bundle);
                    communityFra.setArguments(bundle);
                    diaryFra.setArguments(bundle);
                    if (userModel.getIsEredar() != 0) {
                        bundle.putInt("mycenter", 0);
                        evaluateFra.setArguments(bundle);
                        if (!TextUtils.isEmpty(userModel.getUserPresentation())) {
                            findViewById(R.id.ll_user_center_introduce).setVisibility(View.VISIBLE);
                            tvIntroduction.setText(userModel.getUserPresentation());
                        } else {
                            findViewById(R.id.ll_user_center_introduce).setVisibility(View.GONE);
                        }
                        titles.add("Ta的服务");
                        fragments.add(serviceFra);
                        titles.add("话题");
                        fragments.add(communityFra);
                        titles.add("评价");
                        fragments.add(evaluateFra);
                    } else {
                        findViewById(R.id.ll_user_center_introduce).setVisibility(View.GONE);
                        rb.setVisibility(View.GONE);
                        titles.add("参与服务");
                        fragments.add(serviceFra);
                        titles.add("话题");
                        fragments.add(communityFra);
                    }
                    titles.add("TA的宝宝");
                    fragments.add(diaryFra);
                    adapter.setData(fragments, titles);
                    vp.setAdapter(adapter);
                    vp.setOffscreenPageLimit(3);
                    tl.setTabMode(TabLayout.MODE_FIXED);
                    tl.setupWithViewPager(vp);
                } else {
                    hideWaitDialog();
                    showShortToast(msg);
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));

    }

    /**
     * 处理关注和取消关注操作
     */
    private void handleCareRequest() {
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("userAttentionId", String.valueOf(userId));
        NetRequest.postRequest(Constants.ServiceInfo.HANDLE_CARE_REQUEST, BaseApplication.token, params, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                if (careFlag) {
                    careFlag = false;
                    tvCare.setText("关注");
                } else {
                    careFlag = true;
                    tvCare.setText("取消关注");
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


    @Override
    protected void onResume() {
        super.onResume();
        abl.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        abl.removeOnOffsetChangedListener(this);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        try {
            if (i == 0) {
                switch (vp.getCurrentItem()) {
                    case 0:
                        serviceFra.smartRecyclerView.getSwipeToRefresh().setEnabled(true);
                        break;
                    case 1:
                        communityFra.smartRecyclerView.getSwipeToRefresh().setEnabled(true);
                        break;
                    case 2:
                        evaluateFra.srv.getSwipeToRefresh().setEnabled(true);
                        break;
                }
            } else {
                switch (vp.getCurrentItem()) {
                    case 0:
                        serviceFra.smartRecyclerView.getSwipeToRefresh().setEnabled(true);
                        break;
                    case 1:
                        communityFra.smartRecyclerView.getSwipeToRefresh().setEnabled(false);
                        break;
                    case 2:
                        evaluateFra.srv.getSwipeToRefresh().setEnabled(false);
                        break;
                }
            }
        } catch (NullPointerException e) {

        }
    }

    public static void newIntent(Context context, int userId) {
        Intent intent = new Intent(context, UserCenterActivity.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }

    public static void newIntent(AppCompatActivity context, int userId, int requestCode) {
        Intent intent = new Intent(context, UserCenterActivity.class);
        intent.putExtra("userId", userId);
        context.startActivityForResult(intent, requestCode);
    }
}
