package com.cmbb.smartkids.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseFragment;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/12/9 下午2:54
 */
public class MessageActivity extends BaseActivity {
    private RelativeLayout messageOfficial;
    private RelativeLayout messageOrder;
    private RelativeLayout messageReplay;
    private RelativeLayout messageServer;

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("消  息");
        messageOfficial = (RelativeLayout) findViewById(R.id.message_official);
        messageOrder = (RelativeLayout) findViewById(R.id.message_order);
        messageReplay = (RelativeLayout) findViewById(R.id.message_replay);
        messageServer = (RelativeLayout) findViewById(R.id.message_server);
        messageOfficial.setOnClickListener(this);
        messageOrder.setOnClickListener(this);
        messageReplay.setOnClickListener(this);
        messageServer.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.message_official:
                MessageListActivity.newInstance(MessageActivity.this, 1);
                break;
            case R.id.message_order:
                MessageListActivity.newInstance(MessageActivity.this, 2);
                break;
            case R.id.message_replay:
                MessageListActivity.newInstance(MessageActivity.this, 3);
                break;
            case R.id.message_server:
                MessageListActivity.newInstance(MessageActivity.this, 4);
                break;
        }
    }

    /**
     * Activity 启动
     *
     * @param activity BaseActivity
     */
    public static void newInstance(BaseActivity activity) {
        Intent intent = new Intent(activity, MessageActivity.class);
        activity.startActivity(intent);
    }

    /**
     * Fragment 启动
     *
     * @param fragment BaseFragment
     */
    public static void newInstance(BaseFragment fragment) {
        Intent intent = new Intent(fragment.getActivity(), MessageActivity.class);
        fragment.startActivity(intent);
    }
}
