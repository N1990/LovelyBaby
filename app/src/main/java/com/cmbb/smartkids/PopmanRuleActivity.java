package com.cmbb.smartkids;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.utils.Tools;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/12/3 下午3:14
 */
public class PopmanRuleActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("萌宝达人规则");
        String result = Tools.getContentFromRaw(this, R.raw.popman_rule);
        ((TextView) findViewById(R.id.tv_content)).setText(result);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_popman_rule;
    }

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, PopmanRuleActivity.class);
        context.startActivity(intent);
    }
}
