package com.cmbb.smartkids.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cmbb.smartkids.utils.log.Log;

public class ExitBroadcast extends BroadcastReceiver {
    private static final String TAG = ExitBroadcast.class.getSimpleName();
    Activity activity = null;

    public ExitBroadcast(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "程序退出");
        if (null != activity) {
            activity.finish();
        }
    }
}
