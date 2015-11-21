package com.cmbb.smartkids.activity.more;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/13 11:05
 */
public class AboutActivity extends BaseActivity {
    private TextView tv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_about));
        tv = (TextView) findViewById(R.id.tv_about);
        tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
        tv.getPaint().setAntiAlias(true);//抗锯齿
        tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showShortToast("暂无内容");
    }
}
