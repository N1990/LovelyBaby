package com.cmbb.smartkids.base;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.media.MediaService;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.stetho.Stetho;
import com.iflytek.cloud.SpeechUtility;
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
public class BaseApplication extends MultiDexApplication {
    private static final String TAG = BaseApplication.class.getSimpleName();
    private static Context context;
    public static PushAgent mPushAgent;
    public static String PUSH_ALIAS_ITENTACTION = "com.cmbb.smartkids.alias";
    public static MediaService mediaService;
    public static String token = "";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //讯飞科大
        SpeechUtility.createUtility(this, "appid=56600859");
        // 初始化Alibaba资源服务器
        initAlibabaSDK();
        initLog();
        initSharePreference();
        initStetho();
        initUmengAnalytics();
        //初始化umeng推送
        initPushAgent();

        initFresco();
        // 注册推送别名注册器
        LocalBroadcastManager.getInstance(this).registerReceiver(pushAliasReceiver, new IntentFilter(PUSH_ALIAS_ITENTACTION));
        // 登录状态获取token
        token = SPCache.getString(Constants.TOKEN, "");
    }

    private void initFresco() {
        //fresco
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(BaseApplication.getContext(), OkHttpClientManager.getClinet())
                .setDownsampleEnabled(true)
                .setResizeAndRotateEnabledForNetwork(true)
                .build();
        Fresco.initialize(BaseApplication.getContext(), config);
    }




    /**
     * 初始化 stetho
     */
    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this).enableDumpapp(
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
        MobclickAgent.setDebugMode(false);
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
                        Log.e("MEIZU", "message = " + msg);
                        UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(Constants.INTENT_ACTION_MESSAGE_RECEIVE));
                    }
                });
            }

            @Override
            public Notification getNotification(Context context, UMessage msg) {
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(Constants.INTENT_ACTION_MESSAGE_RECEIVE));
                switch (msg.builder_id) {
                    case 0:
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                                .setLargeIcon(getAppIcon())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle(msg.title)
                                .setCategory(msg.text)
                                .setContentText(msg.text);

                        mBuilder.setAutoCancel(true);
                        Notification mNotification = mBuilder.build();
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
                Log.e("MEIZU", "message1 = " + msg.custom);
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

    private Bitmap getAppIcon() {
        BitmapDrawable bitmapDrawable;
        Bitmap appIcon;
        bitmapDrawable = (BitmapDrawable) getContext().getApplicationInfo().loadIcon(getPackageManager());
        appIcon = bitmapDrawable.getBitmap();
        return appIcon;
    }
}
