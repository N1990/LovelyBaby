package com.cmbb.smartkids.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cmbb.smartkids.utils.log.Log;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/29 9:33
 */
public class DBHelper extends SQLiteOpenHelper{
    private final String TAG = DBHelper.class.getSimpleName();

    public DBHelper(Context context) {
        super(context, DBContent.DB_NAME, null, DBContent.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "DB Create");
        db.execSQL(DBContent.DBUser.SQL);
        db.execSQL(DBContent.DBAddress.SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "DB Update");
        db.execSQL(DBContent.DBUser.DELETE_TABLE);
        db.execSQL(DBContent.DBAddress.DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void delete(SQLiteDatabase db){
        Log.i(TAG, "table delete");
        db.execSQL(DBContent.DBUser.DELETE_TABLE);
        db.execSQL(DBContent.DBAddress.DELETE_TABLE);
        onCreate(db);
    }
}
