package com.cmbb.smartkids.activity.order.v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.order.model.RefundModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.squareup.okhttp.Request;

public class ReimburseActivity extends BaseActivity {

    private RadioGroup rgReimburse;
    private RadioButton rbReimburse1;
    private RadioButton rbReimburse2;
    private RadioButton rbReimburse3;
    private FrameLayout flReimburse;
    private EditText etReimburse;
    private TextView tvReimburseSubmit;
    private String reason;
    private String orderCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reimburse;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_reimburse_activity));
        rgReimburse = (RadioGroup) findViewById(R.id.rg_reimburse);
        rbReimburse1 = (RadioButton) findViewById(R.id.rb_reimburse_1);
        rbReimburse2 = (RadioButton) findViewById(R.id.rb_reimburse_2);
        rbReimburse3 = (RadioButton) findViewById(R.id.rb_reimburse_3);
        flReimburse = (FrameLayout) findViewById(R.id.fl_reimburse);
        etReimburse = (EditText) findViewById(R.id.et_reimburse);
        tvReimburseSubmit = (TextView) findViewById(R.id.tv_reimburse_submit);
        rgReimburse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_reimburse_1:
                        flReimburse.setVisibility(View.GONE);
                        reason = rbReimburse1.getText().toString();
                        break;
                    case R.id.rb_reimburse_2:
                        flReimburse.setVisibility(View.GONE);
                        reason = rbReimburse2.getText().toString();
                        break;
                    case R.id.rb_reimburse_3:
                        flReimburse.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        if (getIntent() != null) {
            orderCode = getIntent().getStringExtra("orderCode");
            tvReimburseSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flReimburse.getVisibility() == View.VISIBLE) {
                        reason = etReimburse.getText().toString();
                    }
                    if (TextUtils.isEmpty(reason)) {
                        showShortToast("请填写退款原因");
                        return;
                    }
                    RefundModel.handleApplyRefundRequest(orderCode, reason, new OkHttpClientManager.ResultCallback<RefundModel>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            hideWaitDialog();
                            showShortToast(e.toString());
                        }

                        @Override
                        public void onResponse(RefundModel response) {
                            hideWaitDialog();
                            showShortToast("申请退款成功");
//                            Intent intent = getIntent();
//                            intent.putExtra("order_status", OrderStatus.TUI_KUAN_ZHONG.getValue());
                            setResult(RESULT_OK);
                            finish();
                        }
                    });
                }
            });
        } else {
            showShortToast("传参出错~");
        }
    }


    public static void newInstance(Activity activity, String orderCode, int requestCode) {
        Intent intent = new Intent(activity, ReimburseActivity.class);
        intent.putExtra("orderCode", orderCode);
        activity.startActivityForResult(intent, requestCode);
    }


}
