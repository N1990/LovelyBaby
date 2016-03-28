package com.cmbb.smartkids.activity.home.home_v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.more.AboutActivity;
import com.cmbb.smartkids.activity.more.GrownValusActivity;
import com.cmbb.smartkids.activity.more.SuggestActivity;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午4:55
 */
public class HomeMoreActivity extends BaseHomeActivity implements View.OnClickListener {


    @Override
    protected void init(Bundle savedInstanceState) {
        setNoBack();
        setTitle("更多");

        findViewById(R.id.tv_suggest_feek).setOnClickListener(this);
        findViewById(R.id.tv_growth_values).setOnClickListener(this);
        findViewById(R.id.tv_about).setOnClickListener(this);
//        findViewById(R.id.tv_clear_cache).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvMore.setSelected(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more_v2;
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, HomeMoreActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_suggest_feek:
                SuggestActivity.newInstance(this);
                break;
            case R.id.tv_growth_values:
                GrownValusActivity.newInstance(this);
                break;
            case R.id.tv_about:
                AboutActivity.newInstance(this);
                break;
            /*case R.id.tv_clear_cache:
                break;*/
        }
    }

    @Override
    protected void netChange() {

    }
}
