package com.cmbb.smartkids.activity.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/12/9 下午4:54
 */
public class OfficialMessageActivity extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("官方消息");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_official;
    }

    /**
     * 启动入口
     *
     * @param context Context
     */
    public static void newInstance(Context context) {
        Intent intent = new Intent(context, OfficialMessageActivity.class);
        context.startActivity(intent);
    }
}
