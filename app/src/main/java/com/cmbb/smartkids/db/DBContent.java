package com.cmbb.smartkids.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/29 10:51
 */
public class DBContent implements BaseColumns {
    public static final String DB_NAME = "smarts.db";
    public static final String INTEGER_TYPE = " INTEGER,";
    public static final String TEXT_TYPE = " TEXT,";
    public static int VERSION = 300;


    public static class DBUser {
        public static final String TABLE_NAME = "user";
        public static final String USER_TOKEN = "token";
        public static final String USER_NICK_NAME = "nick_name";
        public static final String USER_HEAD_IMG = "header_img";
        public static final String USER_IMG_WIDTH = "width";
        public static final String USER_IMG_HEIGHT = "height";
        public static final String USER_PHONE = "phone";
        public static final String USER_MALE = "male";
        public static final String USER_PROVINCE_ID = "province_id";
        public static final String USER_CITY_ID = "city_id";
        public static final String USER_AREA_ID = "area_id";
        public static final String USER_PROVINCE = "province";
        public static final String USER_CITY = "city";
        public static final String USER_AREA = "area";
        public static final String USER_ADDRESS = "address";
        public static final String USER_INTRODUCE = "introduce";
        public static final String USER_IS_POPMAN = "popman";
        public static final String USER_BIRTHDAY = "userBirthday";

        public static final String USER_ID = "user_id";
        public static String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( \n" +
                _ID + " INTEGER PRIMARY KEY," + "\n" +
                USER_TOKEN + TEXT_TYPE + "\n" +
                USER_HEAD_IMG + TEXT_TYPE + "\n" +
                USER_IMG_WIDTH + INTEGER_TYPE + "\n" +
                USER_IMG_HEIGHT + INTEGER_TYPE + "\n" +
                USER_NICK_NAME + TEXT_TYPE + "\n" +
                USER_PHONE + TEXT_TYPE + "\n" +
                USER_MALE + INTEGER_TYPE + "\n" +
                USER_PROVINCE_ID + INTEGER_TYPE + "\n" +
                USER_CITY_ID + INTEGER_TYPE + "\n" +
                USER_AREA_ID + INTEGER_TYPE + "\n" +
                USER_PROVINCE + TEXT_TYPE + "\n" +
                USER_CITY + TEXT_TYPE + "\n" +
                USER_AREA + TEXT_TYPE + "\n" +
                USER_ID + INTEGER_TYPE + "\n" +
                USER_ADDRESS + TEXT_TYPE + "\n" +
                USER_INTRODUCE + TEXT_TYPE + "\n" +
                USER_BIRTHDAY + TEXT_TYPE + "\n" +
                USER_IS_POPMAN + " INTEGER" + "\n" +
                " );";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS USER";

        public static final String AUTHORITY = "com.cmbb.smartkids.useraccount";

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY;

        public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd." + AUTHORITY;

        public static final int USERS = 1;

        public static final int USER = 2;

    }

    public static class DBAddress{
        public static final String TABLE_NAME = "address";
        public static final String ADDRESS_ID = "address_id";
        public static final String ADDRESS_TEXT = "area";
        public static final String CITY_TEXT = "city";
        public static final String PROVINCE_TEXT = "province";

        public static String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( \n" +
                _ID + " INTEGER PRIMARY KEY," + "\n" +
                ADDRESS_ID + INTEGER_TYPE + "\n" +
                ADDRESS_TEXT + TEXT_TYPE + "\n" +
                CITY_TEXT + TEXT_TYPE + "\n" +
                PROVINCE_TEXT + " TEXT " + "\n" +
                " );";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS ADDRESS";

        public static final String AUTHORITY = "com.cmbb.smartkids.addresslist";

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY;

        public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd." + AUTHORITY;

        public static final int ADDRESSES = 1;

        public static final int ADDRESS = 2;

    }


}
