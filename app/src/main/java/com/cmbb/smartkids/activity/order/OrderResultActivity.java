package com.cmbb.smartkids.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;

public class OrderResultActivity extends BaseActivity {
    private ImageView iv;
    private TextView tvTip;
    private boolean flag;
    private String orderCode;
    private String tip;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_result;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_order_result));
        initView();
        initData();
    }

    private void initView(){
        iv = (ImageView) findViewById(R.id.iv_order_result);
        tvTip = (TextView) findViewById(R.id.tv_order_result);
    }

    private void initData(){
        Bundle bundle = null;
        if(getIntent() != null && (bundle = getIntent().getExtras()) != null){
            flag = bundle.getBoolean("flag", false);
            orderCode = bundle.getString("orderCode");
            tip = bundle.getString("tip");
        }
        if(flag){
            iv.setBackgroundResource(R.mipmap.order_result_success_bg);
            if(!TextUtils.isEmpty(tip))
                tvTip.setText(tip);
        }else{
            findViewById(R.id.action_check).setVisibility(View.GONE);
            iv.setBackgroundResource(R.mipmap.order_result_failure_bg);
            if(!TextUtils.isEmpty(tip))
                tvTip.setText(tip);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = new Intent(); //OrderResultActivity.this, OrderDetailActivity.class
        if (id == R.id.action_check) {
            intent.putExtra("orderCode", orderCode);
            intent.putExtra("flag", true);
            intent.putExtra("back", false);
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }else if(id == android.R.id.home){
            intent.putExtra("back", true);
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("back", true);
        setResult(RESULT_OK, intent);
        finish();
    }
}
