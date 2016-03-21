package com.cmbb.smartkids.activity.serve.v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.v2.fragment.ServiceDetailFragment;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.utils.log.Log;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/14 下午3:13
 */
public class ServerDetailActivityV2 extends BaseActivity {

    private static final String TAG = ServerDetailActivityV2.class.getSimpleName();

    private int serviceID;

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("服务详情");
        serviceID = getIntent().getIntExtra("serviceID", -1);
        newFragment(serviceID, false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_server_detail_v2;
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
        Log.i(TAG, id+"");
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
}
