package com.cmbb.smartkids.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseArray;

import com.cmbb.smartkids.utils.log.Log;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/29 10:32
 */
public class UserProvider extends ContentProvider{
    private final String TAG = UserProvider.class.getSimpleName();
    private DBHelper helper;
    private static final UriMatcher matcher;
    private static final SparseArray<String> MIME_TYPE;
    static{
        matcher =  new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(DBContent.DBUser.AUTHORITY, DBContent.DBUser.TABLE_NAME, DBContent.DBUser.USERS);
        matcher.addURI(DBContent.DBUser.AUTHORITY, DBContent.DBUser.TABLE_NAME + "/#", DBContent.DBUser.USER);
        MIME_TYPE = new SparseArray<>();
        MIME_TYPE.put(DBContent.DBUser.USERS, DBContent.DBUser.CONTENT_TYPE + "." + DBContent.DBUser.TABLE_NAME);
        MIME_TYPE.put(DBContent.DBUser.USER, DBContent.DBUser.CONTENT_TYPE_ITEM + "." + DBContent.DBUser.TABLE_NAME);
    }

    @Override
    public boolean onCreate() {
        helper = new DBHelper(getContext());
        Log.e(TAG, "DBUser provider is created");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = helper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DBContent.DBUser.TABLE_NAME);
        if(matcher.match(uri) == DBContent.DBUser.USER){
            builder.appendWhere("id = " + uri.getLastPathSegment());
        }
        Cursor cursor = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return MIME_TYPE.get(matcher.match(uri));
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = helper.getWritableDatabase();
        if(matcher.match(uri) == DBContent.DBUser.USERS){
            long id = db.insert(DBContent.DBUser.TABLE_NAME, null, values);
            if (id != -1){
                getContext().getContentResolver().notifyChange(uri, null);
                return uri.withAppendedPath(uri, Long.toString(id));
            }else{
                throw new SQLiteException("Insert error:" + uri);
            }

        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowDelete = 0;
        if(matcher.match(uri) == DBContent.DBUser.USERS){
            rowDelete = db.delete(DBContent.DBUser.TABLE_NAME, selection, selectionArgs);
        }else if(matcher.match(uri) == DBContent.DBUser.USER){
            if (TextUtils.isEmpty(selection)) {
                rowDelete = db.delete(DBContent.DBUser.TABLE_NAME, "id = " + uri.getLastPathSegment(), null);
            } else {
                rowDelete = db.delete(DBContent.DBUser.TABLE_NAME,"id = " + uri.getLastPathSegment() + " and " + selection, null);
            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowDelete;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowUpdata = 0;
        if(matcher.match(uri) == DBContent.DBUser.USERS){
            rowUpdata = db.update(DBContent.DBUser.TABLE_NAME, values, selection, selectionArgs);
        }else if(matcher.match(uri) == DBContent.DBUser.USER){
            if (TextUtils.isEmpty(selection)) {
                rowUpdata = db.update(DBContent.DBUser.TABLE_NAME, values, "id = " + uri.getLastPathSegment(), null);
            } else {
                rowUpdata = db.update(DBContent.DBUser.TABLE_NAME, values, "id = " + uri.getLastPathSegment() + " and " + selection, null);
            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowUpdata;
    }
}
