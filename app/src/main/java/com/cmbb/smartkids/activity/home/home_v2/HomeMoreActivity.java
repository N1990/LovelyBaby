package com.cmbb.smartkids.activity.home.home_v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.more.AboutActivity;
import com.cmbb.smartkids.activity.more.GrownValusActivity;
import com.cmbb.smartkids.activity.more.SuggestActivity;
import com.cmbb.smartkids.base.BaseActivity;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午4:55
 */
public class HomeMoreActivity extends BaseActivity implements View.OnClickListener {


    private TextView tvHome;
    private TextView tvService;
    private TextView tvTopic;
    private TextView tvMe;
    private TextView tvMore;


    @Override
    protected void init(Bundle savedInstanceState) {
        setNoBack();
        initBottom();
    }

    private void initBottom() {
        tvHome = (TextView) findViewById(R.id.tv_home);
        tvService = (TextView) findViewById(R.id.tv_service);
        tvTopic = (TextView) findViewById(R.id.tv_topic);
        tvMe = (TextView) findViewById(R.id.tv_me);
        tvMore = (TextView) findViewById(R.id.tv_more);
        tvMore.setSelected(true);
        tvHome.setOnClickListener(this);
        tvService.setOnClickListener(this);
        tvTopic.setOnClickListener(this);
        tvMe.setOnClickListener(this);
        tvMore.setOnClickListener(this);
        findViewById(R.id.tv_suggest_feek).setOnClickListener(this);
        findViewById(R.id.tv_growth_values).setOnClickListener(this);
        findViewById(R.id.tv_about).setOnClickListener(this);
        findViewById(R.id.tv_clear_cache).setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.tv_home:
                HomeActivity.newIntent(this);
                break;
            case R.id.tv_service:
                HomeServiceActivity.newIntent(this);
                break;
            case R.id.tv_topic:
                HomeTopicActivity.newIntent(this);
                break;
            case R.id.tv_me:
                HomeMeActivity.newIntent(this);
                break;
            case R.id.tv_more:
                HomeMoreActivity.newIntent(this);
                break;
            case R.id.tv_suggest_feek:
                SuggestActivity.newInstance(this);
                break;
            case R.id.tv_growth_values:
                GrownValusActivity.newInstance(this);
                break;
            case R.id.tv_about:
                AboutActivity.newInstance(this);
                break;
            case R.id.tv_clear_cache:

                break;
        }
    }
}
