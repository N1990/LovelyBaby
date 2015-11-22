package com.cmbb.smartkids.db;

import android.app.IntentService;
import android.content.Intent;


public class DBIntentService extends IntentService {
    public static final String ACTION_SERVICE = "com.cmbb.smartkids.db.action.message.service";
    public static final String ACTION_SYSTEM = "com.cmbb.smartkids.db.action.message.system";

    public DBIntentService() {
        super("DBIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {  //有待修改
        /*if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SERVICE.equals(action)) {
                try{
                    Log.e("IntentService", "IntentService = " + intent.getExtras().toString());
                    String type = intent.getStringExtra("type");
                    String id = intent.getStringExtra("relationId");
                    String readFlag = intent.getStringExtra("read");
                    if(TextUtils.isEmpty(readFlag))
                        readFlag = "0";
                    ContentValues values = new ContentValues();
                    if (!TextUtils.isEmpty(intent.getStringExtra("orderCode"))) {
                        values.put(DBContent.DBNotify.NOTIFY_ORDER_CODE, intent.getStringExtra("orderCode"));
                    }
                    values.put(DBContent.DBNotify.NOTIFY_TYPE, Integer.valueOf(type));
                    values.put(DBContent.DBNotify.NOTIFY_ID, Integer.valueOf(id));
                    values.put(DBContent.DBNotify.NOTIFY_READ_FLAG, Integer.valueOf(readFlag));
                    values.put(DBContent.DBNotify.NOTIFY_MESSAGE, intent.getStringExtra("txt"));
                    getContentResolver().insert(DBContent.DBNotify.CONTENT_URI, values);
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else if (ACTION_SYSTEM.equals(action)) {
            }
        }*/
    }
}
