package com.cmbb.smartkids.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.message.model.MessageCountModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.log.Log;

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

    private TextView officialCount;
    private TextView orderCount;
    private TextView replayCount;
    private TextView serverCount;

    private TextView tvSystemContent;
    private TextView tvOrderContent;
    private TextView tvTopicContent;
    private TextView tvServiceContent;

    private MessageCountModel messageCountModel;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("消  息");
        messageOfficial = (RelativeLayout) findViewById(R.id.message_official);
        messageOrder = (RelativeLayout) findViewById(R.id.message_order);
        messageReplay = (RelativeLayout) findViewById(R.id.message_replay);
        messageServer = (RelativeLayout) findViewById(R.id.message_server);
        officialCount = (TextView) findViewById(R.id.official_count);
        orderCount = (TextView) findViewById(R.id.order_count);
        replayCount = (TextView) findViewById(R.id.replay_count);
        serverCount = (TextView) findViewById(R.id.server_count);
        tvSystemContent = (TextView) findViewById(R.id.tv_system_content);
        tvOrderContent = (TextView) findViewById(R.id.tv_order_content);
        tvTopicContent = (TextView) findViewById(R.id.tv_topic_content);
        tvServiceContent = (TextView) findViewById(R.id.tv_service_content);
        messageOfficial.setOnClickListener(this);
        messageOrder.setOnClickListener(this);
        messageReplay.setOnClickListener(this);
        messageServer.setOnClickListener(this);
        handleMessageCount(true);
    }

    /**
     * 获取消息的数量
     */
    private void handleMessageCount(boolean showDialog) {
        if (showDialog) {
            showWaitDialog();
        }
        NetRequest.postRequest(Constants.ServiceInfo.MESSAGE_GET_TYPE, BaseApplication.token, null, MessageCountModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                if (object != null) {
                    LocalBroadcastManager.getInstance(MessageActivity.this).sendBroadcast(new Intent(Constants.INTENT_ACTION_MESSAGE_CANCEL));
                    messageCountModel = (MessageCountModel) object;
                    if (messageCountModel.getData().get(0).getNoticeCount() == 0) {
                        officialCount.setVisibility(View.GONE);
                    } else {
                        officialCount.setVisibility(View.VISIBLE);
                        officialCount.setText(String.valueOf(messageCountModel.getData().get(0).getNoticeCount()));
                        tvSystemContent.setText(messageCountModel.getData().get(0).getNoticeContent());
                    }
                    if (messageCountModel.getData().get(1).getNoticeCount() == 0) {
                        orderCount.setVisibility(View.GONE);
                    } else {
                        orderCount.setVisibility(View.VISIBLE);
                        orderCount.setText(String.valueOf(messageCountModel.getData().get(1).getNoticeCount()));
                        tvOrderContent.setText(messageCountModel.getData().get(1).getNoticeContent());
                    }
                    if (messageCountModel.getData().get(2).getNoticeCount() == 0) {
                        replayCount.setVisibility(View.GONE);
                    } else {
                        replayCount.setVisibility(View.VISIBLE);
                        replayCount.setText(String.valueOf(messageCountModel.getData().get(2).getNoticeCount()));
                        tvTopicContent.setText(messageCountModel.getData().get(2).getNoticeContent());
                    }
                    if (messageCountModel.getData().get(3).getNoticeCount() == 0) {
                        serverCount.setVisibility(View.GONE);
                    } else {
                        serverCount.setVisibility(View.VISIBLE);
                        serverCount.setText(String.valueOf(messageCountModel.getData().get(3).getNoticeCount()));
                        tvServiceContent.setText(messageCountModel.getData().get(3).getNoticeContent());
                    }
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        Log.i("MessageActivity", "id = " + v.getId());
        switch (v.getId()) {
            case R.id.message_official:
                if (messageCountModel != null)
                    MessageOfficialListActivity.newInstance(MessageActivity.this, messageCountModel.getData().get(0));
                break;
            case R.id.message_order:
                if (messageCountModel != null)
                    MessageOfficialListActivity.newInstance(MessageActivity.this, messageCountModel.getData().get(1));
                break;
            case R.id.message_replay:
                if (messageCountModel != null)
                    MessageReverListActivity.newInstance(MessageActivity.this, messageCountModel.getData().get(2));
                break;
            case R.id.message_server:
                if (messageCountModel != null)
                    MessageServiceListActivity.newInstance(MessageActivity.this, messageCountModel.getData().get(3));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        handleMessageCount(false);
    }
}
