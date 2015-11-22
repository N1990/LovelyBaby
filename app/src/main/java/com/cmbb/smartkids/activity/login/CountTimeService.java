package com.cmbb.smartkids.activity.login;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.cmbb.smartkids.base.Constants;

import java.util.Timer;
import java.util.TimerTask;


/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/20 10:18
 */
public class CountTimeService extends Service {
    private final String TAG = CountTimeService.class.getSimpleName();
    private int SECONDS = 60;
    private Timer timer;
    private Intent intent;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        intent = new Intent(Constants.Broadcast.COUNT_TIME);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SECONDS -= 1;
                CountTimeService.this.intent.putExtra("second", SECONDS);
                sendBroadcast(CountTimeService.this.intent);
                if (SECONDS <= 0) {
                    this.cancel();
                    timer.cancel();
                    CountTimeService.this.stopSelf();
                }
            }
        }, 0, 1000);
        return START_STICKY_COMPATIBILITY;
    }

}
