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
    public static int VERSION = 316;

    public static class DBUser {
        public static final String TABLE_NAME = "user";

        public static final String USER_ID = "userId";
        public static final String USER_UID = "uid";
        public static final String USER_RECOMMONED = "recommoned";
        public static final String USER_NIKE = "userNike";
        public static final String USER_SEX = "userSex";
        public static final String USER_BIRTHDAY = "userBirthday";
        public static final String USER_BACKGROUNDIMG = "backgroundImg";
        public static final String USER_BIGIMG = "userBigImg";
        public static final String USER_BIGWIDTH = "userBigWidth";
        public static final String USER_BIGHEIGHT = "userBigHeight";
        public static final String USER_SMALLIMG = "userSmallImg";
        public static final String USER_SMALLWIDTH = "userSmallWidth";
        public static final String USER_SMALLHEIGHT = "userSmallHeight";
        public static final String USER_LOGINACCOUNTTYPE = "loginAccountType";
        public static final String USER_LOGINTIME = "loginTime";
        public static final String USER_LOGINACCOUNT = "loginAccount";
        public static final String USER_TOKEN = "token";
        public static final String USER_ISSHUTUP = "isShutup";
        public static final String USER_SHUTUPTIME = "shutupTime";
        public static final String USER_ISBANNED = "isBanned";
        public static final String USER_ADDRESS = "userAddress";
        public static final String USER_PHONE = "userPhone";
        public static final String USER_PHONEVERSION = "userPhoneVersion";
        public static final String USER_PROVINCE = "province";
        public static final String USER_PROVINCETEXT = "provinceText";
        public static final String USER_DISTRICT = "district";
        public static final String USER_DISTRICTTEXT = "districtText";
        public static final String USER_CITY = "city";
        public static final String USER_CITYTEXT = "cityText";
        public static final String USER_LEVEL = "userLevel";
        public static final String USER_PRESENTATION = "userPresentation";
        public static final String USER_BACKIMGWIDTH = "backImgWidth";
        public static final String USER_BACKIMGHEIGHT = "backImgHeight";
        public static final String USER_GOLDCOUNT = "goldCount";
        public static final String USER_GROWTHCOUNT = "growthCount";
        public static final String USER_FANS = "fans";
        public static final String USER_ATTENTIONCOUNT = "attentionCount";
        public static final String USER_ISSIGN = "isSign";
        public static final String USER_ISATTENTION = "isAttention";
        public static final String USER_ISEREDAR = "isEredar";
        public static final String USER_ISLOGINUSER = "isLoginUser";
        public static final String USER_EREDARCODE = "eredarCode";
        public static final String USER_EREDARNAME = "eredarName";

        public static String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( \n" +
                _ID + " INTEGER PRIMARY KEY," + "\n" +
                USER_ID + INTEGER_TYPE + "\n" +
                USER_UID + TEXT_TYPE + "\n" +
                USER_RECOMMONED + INTEGER_TYPE + "\n" +
                USER_NIKE + TEXT_TYPE + "\n" +
                USER_SEX + INTEGER_TYPE + "\n" +
                USER_BIRTHDAY + TEXT_TYPE + "\n" +
                USER_BACKGROUNDIMG + TEXT_TYPE + "\n" +
                USER_BIGIMG + TEXT_TYPE + "\n" +
                USER_BIGWIDTH + TEXT_TYPE + "\n" +
                USER_BIGHEIGHT + TEXT_TYPE + "\n" +
                USER_SMALLIMG + TEXT_TYPE + "\n" +
                USER_SMALLWIDTH + TEXT_TYPE + "\n" +
                USER_SMALLHEIGHT + TEXT_TYPE + "\n" +
                USER_LOGINACCOUNTTYPE + INTEGER_TYPE + "\n" +
                USER_LOGINTIME + TEXT_TYPE + "\n" +
                USER_LOGINACCOUNT + TEXT_TYPE + "\n" +
                USER_TOKEN + TEXT_TYPE + "\n" +
                USER_ISSHUTUP + INTEGER_TYPE + "\n" +
                USER_SHUTUPTIME + TEXT_TYPE + "\n" +
                USER_ISBANNED + INTEGER_TYPE + "\n" +
                USER_ADDRESS + TEXT_TYPE + "\n" +
                USER_PHONE + TEXT_TYPE + "\n" +
                USER_PHONEVERSION + TEXT_TYPE + "\n" +
                USER_PROVINCE + TEXT_TYPE + "\n" +
                USER_PROVINCETEXT + TEXT_TYPE + "\n" +
                USER_DISTRICT + TEXT_TYPE + "\n" +
                USER_DISTRICTTEXT + TEXT_TYPE + "\n" +
                USER_CITY + TEXT_TYPE + "\n" +
                USER_CITYTEXT + TEXT_TYPE + "\n" +
                USER_LEVEL + INTEGER_TYPE + "\n" +
                USER_PRESENTATION + TEXT_TYPE + "\n" +
                USER_BACKIMGWIDTH + TEXT_TYPE + "\n" +
                USER_BACKIMGHEIGHT + TEXT_TYPE + "\n" +
                USER_GOLDCOUNT + INTEGER_TYPE + "\n" +
                USER_GROWTHCOUNT + INTEGER_TYPE + "\n" +
                USER_FANS + INTEGER_TYPE + "\n" +
                USER_ATTENTIONCOUNT + INTEGER_TYPE + "\n" +
                USER_ISSIGN + INTEGER_TYPE + "\n" +
                USER_ISATTENTION + INTEGER_TYPE + "\n" +
                USER_ISEREDAR + INTEGER_TYPE + "\n" +
                USER_ISLOGINUSER + INTEGER_TYPE + "\n" +
                USER_EREDARCODE + TEXT_TYPE + "\n" +
                USER_EREDARNAME + " TEXT" + "\n" +
                " );";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS USER";

        public static final String AUTHORITY = "com.cmbb.smartkids.useraccount";

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY;

        public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd." + AUTHORITY;

        public static final int USERS = 1;

        public static final int USER = 2;

    }

    public static class DBAddress {
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

    public static class DBTopic {
        public static final String TABLE_NAME = "topic";
        public static final String TOPIC_USER_ID = "topic_user_id";
        public static final String TOPIC_TITLE = "topic_title";
        public static final String TOPIC_SORT = "topic_sort";
        public static final String TOPIC_SORT_VALUE = "topic_sort_value";
        public static final String TOPIC_CONTENT = "topic_content";
        public static final String TOPIC_TIME = "topic_time";
        public static final String TOPIC_TELETEXTS = "topic_teletext";
        //        public static final String TOPIC_TEXT = "topic_text";

        //        public static final String TOPIC_IMG_2 = "topic_img2";
        //        public static final String TOPIC_TEXT_2 = "topic_text2";
        //
        //        public static final String TOPIC_IMG_3 = "topic_img3";
        //        public static final String TOPIC_TEXT_3 = "topic_text3";
        //
        //        public static final String TOPIC_IMG_4 = "topic_img4";
        //        public static final String TOPIC_TEXT_4 = "topic_text4";
        //
        //        public static final String TOPIC_IMG_5 = "topic_img5";
        //        public static final String TOPIC_TEXT_5 = "topic_text5";
        //
        //        public static final String TOPIC_IMG_6 = "topic_img6";
        //        public static final String TOPIC_TEXT_6 = "topic_text6";
        //
        //        public static final String TOPIC_IMG_7 = "topic_img7";
        //        public static final String TOPIC_TEXT_7 = "topic_text7";
        //
        //        public static final String TOPIC_IMG_8 = "topic_img8";
        //        public static final String TOPIC_TEXT_8 = "topic_text8";
        //
        //        public static final String TOPIC_IMG_9 = "topic_img9";
        //        public static final String TOPIC_TEXT_9 = "topic_text9";
        //
        //        public static final String TOPIC_IMG_10 = "topic_img10";
        //        public static final String TOPIC_TEXT_10= "topic_text10";

        public static String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( \n" +
                _ID + " INTEGER PRIMARY KEY," + "\n" +
                TOPIC_USER_ID + INTEGER_TYPE + "\n" +
                TOPIC_TITLE + TEXT_TYPE + "\n" +
                TOPIC_SORT + TEXT_TYPE + "\n" +
                TOPIC_SORT_VALUE + TEXT_TYPE + "\n" +
                TOPIC_CONTENT + TEXT_TYPE + "\n" +
                TOPIC_TIME + TEXT_TYPE + "\n" +

                //                TOPIC_IMG_1 + TEXT_TYPE + "\n" +
                //                TOPIC_TEXT_1 + TEXT_TYPE + "\n" +
                //
                //                TOPIC_IMG_2 + TEXT_TYPE + "\n" +
                //                TOPIC_TEXT_2 + TEXT_TYPE + "\n" +
                //
                //                TOPIC_IMG_3 + TEXT_TYPE + "\n" +
                //                TOPIC_TEXT_3 + TEXT_TYPE + "\n" +
                //
                //                TOPIC_IMG_4 + TEXT_TYPE + "\n" +
                //                TOPIC_TEXT_4 + TEXT_TYPE + "\n" +
                //
                //                TOPIC_IMG_5 + TEXT_TYPE + "\n" +
                //                TOPIC_TEXT_5 + TEXT_TYPE + "\n" +
                //
                //                TOPIC_IMG_6 + TEXT_TYPE + "\n" +
                //                TOPIC_TEXT_6 + TEXT_TYPE + "\n" +
                //
                //                TOPIC_IMG_7 + TEXT_TYPE + "\n" +
                //                TOPIC_TEXT_7 + TEXT_TYPE + "\n" +
                //
                //                TOPIC_IMG_8 + TEXT_TYPE + "\n" +
                //                TOPIC_TEXT_8 + TEXT_TYPE + "\n" +
                //
                //                TOPIC_IMG_9 + TEXT_TYPE + "\n" +
                //                TOPIC_TEXT_9 + TEXT_TYPE + "\n" +

                //                TOPIC_TELETEXTS + TEXT_TYPE + "\n" +
                TOPIC_TELETEXTS + " TEXT " + "\n" +
                " );";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS TOPIC";

        public static final String AUTHORITY = "com.cmbb.smartkids.topiclist";

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY;

        public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd." + AUTHORITY;

        public static final int TOPICS = 1;

        public static final int TOPIC = 2;

    }

}
