package com.cmbb.smartkids.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;

public class CancelOrderResultActivity extends BaseActivity implements TextWatcher {
    private final String TAG = CancelOrderResultActivity.class.getSimpleName();
    private final int ORDER_RESULT = 10001;
    private EditText etResult;
    private TextView tvLimit, tvNotify;
    private RadioGroup rgCancel;
    private RadioButton rbCause1, rbCause2, rbCause3;
    private int realLen = 0;
    private String orderId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_cancel_order_result;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_cancel_order_result));
        etResult = (EditText) findViewById(R.id.et_cancel_order_result);
        tvLimit = (TextView) findViewById(R.id.tv_cancel_order_limit);
        tvNotify = (TextView) findViewById(R.id.tv_cancel_order_notify);
        rgCancel = (RadioGroup) findViewById(R.id.rg_cancel_order_item);
        rbCause1 = (RadioButton) findViewById(R.id.rb_cancel_order_one_item);
        rbCause2 = (RadioButton) findViewById(R.id.rb_cancel_order_two_item);
        rbCause3 = (RadioButton) findViewById(R.id.rb_cancel_order_three_item);
    }

    private void initData(){
        if(getIntent() != null){
            orderId = getIntent().getStringExtra("orderId");
        }
    }

    private void addListener(){
        findViewById(R.id.tv_cancel_order_submit).setOnClickListener(this);
        tvNotify.setOnClickListener(this);
        rgCancel.setOnCheckedChangeListener(radioGropListener);
        etResult.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        realLen = s.length();
        if(realLen <= 200){
            tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvLimit.setText(realLen + "/200");
        }else if(realLen < 200 && realLen > 150){
            tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvLimit.setText("还剩余" + (200 - realLen) + "个");
        }else if(realLen == 200){
            tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvLimit.setText("文字已输满");
        }else{
            tvLimit.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            tvLimit.setText("超过规定字数"+(realLen - 200) + "个");
        }
    }

    private RadioGroup.OnCheckedChangeListener radioGropListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel_order_notify:
                showShortToast("赶快看~");
                break;
            case R.id.tv_cancel_order_submit:
                if(realLen > 150 || realLen == 0){
                    showShortToast("输入的字数不符合要求");
                }else{
                    showShortToast("提交啦~");
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ORDER_RESULT && resultCode == RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

   /* private void handleCancelRequest(){
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("orderCode", orderId);
        NetRequest.postRequest(Constants.ServiceInfo.CANCEL_ORDER_REQUEST, token, params, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                SecurityCodeModel result = (SecurityCodeModel) object;
                Intent intent = new Intent(CancelOrderResultActivity.this, OrderResultActivity.class);
                if (Integer.valueOf(result.getStatus()) == 1) {
                    intent.putExtra("flag", true);
                    intent.putExtra("tip", "订单取消成功~");
                } else {
                    intent.putExtra("flag", false);
                    intent.putExtra("tip", "订单取消失败~");
                    showShortToast(msg);
                }
                startActivityForResult(intent, ORDER_RESULT);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }*/
}
