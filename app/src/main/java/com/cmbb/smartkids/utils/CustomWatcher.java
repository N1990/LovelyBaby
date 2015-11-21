package com.cmbb.smartkids.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/18 16:02
 */
public class CustomWatcher implements TextWatcher, View.OnClickListener {
    private EditText etText;
    private ImageView ivClear;

    public CustomWatcher(EditText etText, ImageView ivClear) {
        this.etText = etText;
        this.ivClear = ivClear;
        this.etText.addTextChangedListener(this);
        this.ivClear.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(s)) {
            ivClear.setVisibility(View.VISIBLE);
            ivClear.setEnabled(true);
        } else {
            ivClear.setVisibility(View.INVISIBLE);
            ivClear.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        String text = etText.getText().toString();
        if (!TextUtils.isEmpty(text) && v.getVisibility() == View.VISIBLE) {
            etText.setText("");
            etText.requestFocus();
            ivClear.setVisibility(View.INVISIBLE);
            ivClear.setEnabled(false);
        }
    }
}
