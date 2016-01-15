package com.cmbb.smartkids.activity.more;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.utils.TDevice;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/13 11:05
 */
public class AboutActivity extends BaseActivity {
    private TextView tv;
    private TextView tvAboutTitle;
    private TextView tv_about_content;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_about));
        tv = (TextView) findViewById(R.id.tv_about);
        tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv.getPaint().setAntiAlias(true);//抗锯齿
        tv.setOnClickListener(this);
        tv_about_content = (TextView) findViewById(R.id.tv_about_content);

        tvAboutTitle = (TextView) findViewById(R.id.tv_about_title);
        tvAboutTitle.setText(R.string.app_name);
        tvAboutTitle.append("(" + TDevice.getVersionName() + ")");
    }

    @Override
    public void onClick(View v) {
        CompanyRule.newInstance(this);
    }

}
