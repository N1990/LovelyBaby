package com.cmbb.smartkids.base;

import android.app.Application;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.media.MediaService;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.stetho.Stetho;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/10 13:07
 */
public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();
    private static BaseApplication instance;
    private static Context context;
    public static PushAgent mPushAgent;
    public static String PUSH_ALIAS_ITENTACTION = "com.cmbb.smartkids.alias";
    public static MediaService mediaService;
    public static String token = "";


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();

        // 初始化Alibaba资源服务器
        initAlibabaSDK();
        initLog();
        initSharePreference();
        //initStetho();
        initUmengAnalytics();
        //初始化umeng推送
        initPushAgent();
        // 注册推送别名注册器
        LocalBroadcastManager.getInstance(this).registerReceiver(pushAliasReceiver, new IntentFilter(PUSH_ALIAS_ITENTACTION));
        // 登录状态获取token
        token = SPCache.getString(Constants.TOKEN, "");
    }


    /**
     * 初始化 stetho
     */
    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this).enableDumpapp(
                        Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 初始化 日志
     */
    private void initLog() {
        Log.init(true);
    }

    /**
     * 初始化sharepreference
     * 用法：
     * int count = SpCache.getInt(ACTIVITY_CREATE_COUNT, 0) + 1;
     * SpCache.putInt(ACTIVITY_CREATE_COUNT, count);
     */
    private void initSharePreference() {
        SPCache.init(this);
    }

    /**
     * Umeng统计，正式版本置为false
     */
    private void initUmengAnalytics() {
        MobclickAgent.setDebugMode(true);
    }

    /**
     * 友盟推送
     */
    private void initPushAgent() {
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.setDebugMode(true);

        /**
         * 该Handler是在IntentService中被调用，故
         * 1. 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * 2. IntentService里的onHandleIntent方法是并不处于主线程中，因此，如果需调用到主线程，需如下所示;
         * 	      或者可以直接启动Service
         * */
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                new Handler(getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public Notification getNotification(Context context, UMessage msg) {   //有待修改
                //            message = {type=0, orderCode=501508892672538789, relationId=90}
                /*msg.extra.put("read", "0");
                if(msg.extra.containsKey("type")){
                    Intent intent = new Intent(getContext(), DBIntentService.class);
                    switch (Integer.parseInt(msg.extra.get("type"))) {
                        case 1:
                            intent.setAction(DBIntentService.ACTION_SYSTEM);
                            break;

                        case 0:
                            intent.setAction(DBIntentService.ACTION_SERVICE);
                            break;

                    }
                    Log.e("push", "message = " + msg.extra);
                    String orderCode = "";
                    if (msg.extra.containsKey("orderCode")) {
                        orderCode = msg.extra.get("orderCode");
                        intent.putExtra("orderCode", orderCode);
                    }
                    intent.putExtra("read", 0);
                    intent.putExtra("type", msg.extra.get("type"));
                    intent.putExtra("relationId", msg.extra.get("relationId"));
                    intent.putExtra("txt", msg.text);
                    startService(intent);
                }*/
                switch (msg.builder_id) {
                    case 0:
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(), R.layout.view_umeng_notification);
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                        myNotificationView.setImageViewResource(R.id.notification_large_icon, R.mipmap.ic_launcher);
                        myNotificationView.setImageViewResource(R.id.notification_small_icon, R.mipmap.ic_launcher);
                        builder.setContent(myNotificationView);
                        builder.setAutoCancel(true);
                        Notification mNotification = builder.build();
                        //由于Android v4包的bug，在2.3及以下系统，Builder创建出来的Notification，并没有设置RemoteView，故需要添加此代码
                        mNotification.contentView = myNotificationView;
                        return mNotification;
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 该Handler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
//                Toast.makeText(context, "i click :" + msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }


    /**
     * 设置用户别名
     */
    private BroadcastReceiver pushAliasReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            Log.e("Alias", "Alias 设置");
            //mPushAgent.onAppStart();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Log.e("Alias", "Alias 设置参数 ＝ " + intent.getStringExtra("umeng_id"));
                        Log.e("Alias", "Alias 设置参数 ＝ " + intent.getStringExtra("umeng_type"));
                        boolean flag = mPushAgent.addAlias(intent.getStringExtra("umeng_id"), intent.getStringExtra("umeng_type"));
                        Log.e("Alias", "Alias 设置是否成功 ＝ " + flag);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            /*mPushAgent.enable(new IUmengRegisterCallback() {
                @Override
                public void onRegistered(String registrationId) {
                    Log.e("Alias", "token:" + registrationId);
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Log.e("Alias", "Alias 设置参数 ＝ " + intent.getStringExtra("umeng_id"));
                                Log.e("Alias", "Alias 设置参数 ＝ " + intent.getStringExtra("umeng_type"));
                                boolean flag = mPushAgent.addAlias(intent.getStringExtra("umeng_id"), intent.getStringExtra("umeng_type"));
                                Log.e("Alias", "Alias 设置是否成功 ＝ " + flag);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            });*/
        }
    };

    public void initAlibabaSDK() {
        AlibabaSDK.asyncInit(this, new InitResultCallback() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "AlibabaSDK   onSuccess");
                mediaService = AlibabaSDK.getService(MediaService.class);
            }

            @Override
            public void onFailure(int code, String msg) {
                Log.e(TAG, "AlibabaSDK onFailure  msg:" + msg + " code:" + code);
            }
        });
    }
}
