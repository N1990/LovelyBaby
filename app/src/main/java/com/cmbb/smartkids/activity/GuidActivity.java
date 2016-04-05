package com.cmbb.smartkids.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home_v2.HomeActivity;
import com.cmbb.smartkids.activity.login.LoginActivity;
import com.cmbb.smartkids.activity.login.VerifyActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.photopicker.widget.indication.CirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;

public class GuidActivity extends BaseActivity {

    private final String TAG = GuidActivity.class.getSimpleName();
    private Boolean isQuit = false;// 退出应用标识符
    private Timer timer = new Timer();// 程序退出定时器
    private ViewPager vp;
    private CirclePageIndicator cpiGuid;
    private boolean skipFlag;
    private LinearLayout ll_bottom;
    private TextView tvIn;

    @Override
    protected int getLayoutId() {
        needActionBar = false;
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_guid;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        addListener();
    }

    private void addListener() {
        findViewById(R.id.tv_guid_login).setOnClickListener(this);
        findViewById(R.id.tv_guid_register).setOnClickListener(this);
        tvIn.setOnClickListener(this);
    }

    private void initView() {
        tvIn = (TextView) findViewById(R.id.tv_in);
        ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
        vp = (ViewPager) findViewById(R.id.vp_guid);
        cpiGuid = (CirclePageIndicator) findViewById(R.id.cpi_guid);
        SimpleAdapter adapter = new SimpleAdapter();
        vp.setAdapter(adapter);
        cpiGuid.setViewPager(vp);
        cpiGuid.setSnap(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_guid_login:
                startActivity(new Intent(GuidActivity.this, LoginActivity.class));
                break;
            case R.id.tv_guid_register:
                Intent intent = new Intent(GuidActivity.this, VerifyActivity.class);
                intent.putExtra("tag", "register");
                startActivity(intent);
                break;
            case R.id.tv_in:
                HomeActivity.newIntent(this);
                break;
        }
    }

    private int[] imgs = {R.mipmap.guide_bac_01, R.mipmap.guide_bac_02, R.mipmap.guide_bac_03};

    class SimpleAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(container.getContext());
            iv.setAdjustViewBounds(true);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setAdjustViewBounds(false);
            int img = imgs[position];
            iv.setImageResource(img);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeViewAt(position);
        }
    }


    @Override
    public void onBackPressed() {
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


    public static void newIntent(Context context) {
        Intent intent = new Intent(context, GuidActivity.class);
        context.startActivity(intent);
    }

}
