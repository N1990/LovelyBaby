package com.cmbb.smartkids.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.cmbb.smartkids.R;

/**
 * Created by javon on 2015/8/7.
 */
public class WaitDialog extends Dialog {
    private TextView tvMessage;


    public WaitDialog(Context context) {
        super(context);
        init();
    }

    private void init() {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View root = getLayoutInflater().inflate(R.layout.custom_progress_bar, null);
        tvMessage = (TextView) root.findViewById(R.id.tv_wait_message);
        setContentView(root);
    }

    public void setMessage(String message) {
        if (tvMessage != null) {
            tvMessage.setText(message);
        }
    }

}
