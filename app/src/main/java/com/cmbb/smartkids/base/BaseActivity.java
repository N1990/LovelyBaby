package com.cmbb.smartkids.base;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.ExitBroadcast;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.log.Log;
import com.cmbb.smartkids.widget.WaitDialog;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;


public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = BaseActivity.class.getSimpleName();
    private long lastToastTime;
    private String lastToast;
    private ActionBar actionbar;
    protected boolean needActionBar = true; //是否需要actionbar
    protected boolean isVisiable;
    private WaitDialog dialog;
    protected TextView tvTitle, tvRight;
    private ImageView ivRight, ivLeft;
    public static PushAgent mPushAgent;

    private BroadcastReceiver existReceiver;// EXIT

    //处理主线程UI更新
    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.CANCEL_WAIT_DIALOG:
                    if (dialog != null && dialog.isShowing()) {
                        dialog.hide();
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        // 存储屏幕宽 高
        TDevice.saveDisplaySize(this);
        if (needActionBar) {
            initActionBar();
        }
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.onAppStart();
        initExit();
        init(savedInstanceState);
    }


    /**
     * 初始化actionbar
     */
    private void initActionBar() {
        try {
            Toolbar v = (Toolbar) findViewById(R.id.tl_main_actionbar);
            tvTitle = (TextView) v.findViewById(R.id.tv_main_toolbar);
            ivLeft = (ImageView) v.findViewById(R.id.iv_main_toolbar_left);
            ivRight = (ImageView) v.findViewById(R.id.iv_main_toolbar_right);
            tvRight = (TextView) v.findViewById(R.id.tv_main_toolbar_right);
            setSupportActionBar(v);
            actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setDisplayShowTitleEnabled(false);
        } catch (Exception e) {
            Log.i(TAG, "actionbar is null");
        }
    }


    protected void setTitle(String title) {
        tvTitle.setText(title);
    }

    protected void setActionBarRight(String text) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setOnClickListener(this);
        tvRight.setText(text);
    }

    protected void setBarRightImg(int resId) {
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setBackgroundResource(resId);
        ivRight.setOnClickListener(this);
    }

    protected void setBarLeftImg(int resId) {
        setNoBack();
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setBackgroundResource(resId);
    }

    protected void hideBarLeftImg() {
        actionbar.setDisplayHomeAsUpEnabled(true);
        ivLeft.setVisibility(View.GONE);
    }


    protected void setNoBack() {
        try {
            actionbar.setDisplayHomeAsUpEnabled(false);
        } catch (NullPointerException e) {
            Log.e("actionbar", "actionbar = " + e);
        }
    }

    protected void hideBarRight() {
        if (tvRight != null) {
            tvRight.setVisibility(View.GONE);
        }
        if (ivRight != null) {
            ivRight.setVisibility(View.GONE);
        }
    }

    protected abstract void init(Bundle savedInstanceState);

    protected abstract int getLayoutId();

    public boolean isVisiable() {
        return isVisiable;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isVisiable = true;
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isVisiable = false;
        MobclickAgent.onPause(this);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 程序退出
     */
    private void initExit() {
        existReceiver = new ExitBroadcast(this);
        IntentFilter filter = new IntentFilter(Constants.INTENT_ACTION_EXIT_APP);
        registerReceiver(existReceiver, filter);
    }


    protected void showToast(String tip) {
        showToast(tip, Toast.LENGTH_LONG);
    }

    protected int showShortToast(String tip) {
        showToast(tip, Toast.LENGTH_SHORT);
        return 0;
    }

    protected void showToast(String tip, int duration) {
        long time = System.currentTimeMillis();
        if (!TextUtils.isEmpty(tip)) {
            if (!tip.equalsIgnoreCase(lastToast) || (time - lastToastTime) > 2000) {
                Toast.makeText(this, tip, duration).show();
                lastToastTime = System.currentTimeMillis();
                lastToast = tip;
            }
        }
    }

    protected void showWaitDialog() {
        showWaitDialog(null);
    }

    protected void showWaitDialog(String tip) {
        if (dialog == null) {
            dialog = new WaitDialog(this);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    cancelRequest();
                }
            });
        }
        if (!TextUtils.isEmpty(tip)) {
            dialog.setMessage(tip);
        }
        dialog.show();
    }

    protected void cancelRequest() {
        NetRequest.httpClient.cancel(Constants.BASE_URL);
        finish();
    }

    public boolean isWaitShow() {
        if (dialog != null && dialog.isShowing()) {
            return true;
        } else {
            return false;
        }
    }

    public void hideWaitDialog() {
        handler.sendEmptyMessage(Constants.CANCEL_WAIT_DIALOG);
    }


    protected void recycleBitmap(ImageView view) {
        if (view == null) return;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) view.getDrawable();
        if (bitmapDrawable != null) {
            view.setImageBitmap(null);
            // 如果图片还未回收，先强制回收该图片
            if (bitmapDrawable.getBitmap() != null && !bitmapDrawable.getBitmap().isRecycled()) {
                //Log.i(TAG, "图片回收");
                bitmapDrawable.getBitmap().recycle();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(existReceiver);
    }
}
