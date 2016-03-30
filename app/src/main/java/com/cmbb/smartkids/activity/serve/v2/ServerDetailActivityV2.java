package com.cmbb.smartkids.activity.serve.v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home_v2.HomeActivity;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.serve.v2.fragment.ServiceDetailFragment;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.utils.ShareUtils;
import com.cmbb.smartkids.utils.log.Log;
import com.squareup.okhttp.Request;
import com.umeng.socialize.sso.UMSsoHandler;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/14 下午3:13
 */
public class ServerDetailActivityV2 extends BaseActivity {

    private static final String TAG = ServerDetailActivityV2.class.getSimpleName();

    private int serviceID;
    private boolean isCollected;  // 是否收藏
    private ImageView ivCollect;

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("服务详情");
        ivCollect = (ImageView) findViewById(R.id.iv_active_detail_collect_right);
        ivCollect.setOnClickListener(this);
        ShareUtils.instanceOf(this);
        ShareUtils.configPlatforms();
        serviceID = getIntent().getIntExtra("serviceID", -1);
        newFragment(serviceID, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_active_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            ShareUtils.showShareView();
            return true;
        } else if (id == android.R.id.home) {
            setResult(RESULT_OK);
            finish();
            return true;
        } else if (id == R.id.action_gohome) { // 回到首页
            HomeActivity.newIntent(this);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_server_detail_v2;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_active_detail_collect_right:
                handleCollectRequest(isCollected ? 0 : 1, serviceID);
                break;
        }
    }

    /**
     * 收藏服务
     *
     * @param isCollect int
     */
    private void handleCollectRequest(final int isCollect, int serviceId) {  // 1 收藏  0取消收藏
        showWaitDialog();
        SecurityCodeModel.handleCollectRequest(isCollect, serviceId, new OkHttpClientManager.ResultCallback<SecurityCodeModel>() {
            @Override
            public void onError(Request request, Exception e) {
                hideWaitDialog();
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(SecurityCodeModel response) {
                hideWaitDialog();
                if (response != null) {
                    isCollected = !isCollected;
                    ivCollect.setBackgroundResource(isCollected ? R.mipmap.btn_community_collect_pressed : R.mipmap.btn_community_collect_normal);
                    showShortToast(response.getMsg());
                }
            }
        });
    }

    /**
     * 启动入口
     *
     * @param context Context
     */
    public static void newIntent(Context context, int serviceID) {
        Intent intent = new Intent(context, ServerDetailActivityV2.class);
        intent.putExtra("serviceID", serviceID);
        context.startActivity(intent);
    }


    public void newFragment(int id, boolean addBack) {
        serviceID = id;
        Log.i(TAG, id + "");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, ServiceDetailFragment.newInstance(id), id + "");
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (addBack)
            transaction.addToBackStack(id + "");
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        UMSsoHandler ssoHandler = ShareUtils.mController.getConfig().getSsoHandler(requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
