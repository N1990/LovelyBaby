package com.cmbb.smartkids.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.CustomListener;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/11 11:55
 */
public class CustomAlertDialog extends Dialog implements View.OnClickListener {
    private TextView tvTitle, tvContent, tvCancel, tvComfirm;
    private LinearLayout llNavigate;
    private View vDivider;
    private CustomListener.DialogListener onAbolishListener;
    private CustomListener.DialogListener onComfirmListener;

    public CustomAlertDialog(Context context) {
        super(context);
        init(context);
    }

    public CustomAlertDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    public CustomAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCanceledOnTouchOutside(false);
        View root = getLayoutInflater().from(context).inflate(R.layout.dialog_custom, null);
        tvTitle = (TextView) root.findViewById(R.id.tv_custom_dialog_title);
        tvContent = (TextView) root.findViewById(R.id.tv_custom_dialog_content);
        llNavigate = (LinearLayout) root.findViewById(R.id.ll_custom_dialog_navigate);
        tvCancel = (TextView) root.findViewById(R.id.tv_custom_dialog_cancel);
        tvCancel.setOnClickListener(this);
        tvComfirm = (TextView) root.findViewById(R.id.tv_custom_dialog_comfirm);
        tvComfirm.setOnClickListener(this);
        vDivider = root.findViewById(R.id.v_custom_dialog_divider);
        setContentView(root);
    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
    }
    
    public void setMessage(String message) {
        if (!TextUtils.isEmpty(message))
            tvContent.setText(message);
    }

    public void setOnAbolishListener(String text, CustomListener.DialogListener onCancelListener) {
        llNavigate.setVisibility(View.VISIBLE);
        tvCancel.setVisibility(View.VISIBLE);
        ifNeedDivider();
        if (!TextUtils.isEmpty(text))
            tvCancel.setText(text);
        this.onAbolishListener = onCancelListener;
    }

    public void setOnComfirmListener(String text, CustomListener.DialogListener onAbolishListener) {
        llNavigate.setVisibility(View.VISIBLE);
        tvComfirm.setVisibility(View.VISIBLE);
        ifNeedDivider();
        if (!TextUtils.isEmpty(text))
            tvComfirm.setText(text);
        this.onComfirmListener = onAbolishListener;
    }

    private void ifNeedDivider() {
        if (tvCancel.getVisibility() == View.VISIBLE && tvComfirm.getVisibility() == View.VISIBLE)
            vDivider.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (v.getId() == R.id.tv_custom_dialog_cancel && onAbolishListener != null) {
            onAbolishListener.onClick(v);
        } else if (v.getId() == R.id.tv_custom_dialog_comfirm && onComfirmListener != null) {
            onComfirmListener.onClick(v);
        }
    }
}
