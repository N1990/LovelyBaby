package com.cmbb.smartkids.activity.order;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;

import java.util.HashMap;

public class VerifyOrderActivity extends BaseActivity implements TextWatcher {
    private EditText etNumber;
    private ImageView ivClear, ivResult;
    private TextView tvResult;
    private int serviceId;
    private int len;
    private int resultCount = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verify_order;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_verify_order));
        etNumber = (EditText) findViewById(R.id.et_verify_order_number);
        ivClear = (ImageView) findViewById(R.id.iv_verify_order_clear);
        ivResult = (ImageView) findViewById(R.id.iv_verify_order_result);
        tvResult = (TextView) findViewById(R.id.tv_verify_order_result);

    }

    private void initData() {
        if (getIntent() != null) {
            serviceId = getIntent().getIntExtra("serviceId", -1);
        }
    }

    private void addListener() {
        etNumber.addTextChangedListener(this);
        ivClear.setOnClickListener(this);
        findViewById(R.id.tv_verify_order_submit).setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        len = s.length();
        if (len == 0) {
            ivClear.setVisibility(View.GONE);
            ivResult.setVisibility(View.GONE);
            tvResult.setVisibility(View.GONE);
        } else {
            ivClear.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (resultCount == 0) {
                    onBackPressed();
                } else {
                    setResult(RESULT_OK);
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_verify_order_clear) {
            etNumber.setText("");
        } else if (id == R.id.tv_verify_order_submit) {
            if (len <= 0) {
                showShortToast("订单号不能为空");
                return;
            }
            handleRequest(etNumber.getText().toString());
        }
    }

    private void handleRequest(final String orderCode) {
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("serviceId", String.valueOf(serviceId));
        params.put("ordercode", orderCode);
        NetRequest.postRequest(Constants.ServiceInfo.VERIFY_ORDER_REQUEST, BaseApplication.token, params, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                SecurityCodeModel result = (SecurityCodeModel) object;
                if (result != null) {
                    ivResult.setVisibility(View.VISIBLE);
                    tvResult.setVisibility(View.VISIBLE);
                    resultCount++;
                    ivResult.setBackgroundResource(R.mipmap.order_verify_success_bg);
                    tvResult.setText("订单号：" + orderCode + "，验证成功~");
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
                ivResult.setBackgroundResource(R.mipmap.order_verify_failure_bg);
                tvResult.setText("订单号：" + orderCode + "，验证失败~");
            }
        }));
    }
}
