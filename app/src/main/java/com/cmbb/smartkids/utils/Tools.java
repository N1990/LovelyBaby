package com.cmbb.smartkids.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * 项目名称：JavonFZZ-master
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/20 15:23
 */
public class Tools {
    /***
     * 读取本地文件中JSON字符串
     *
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    context.getAssets().open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 请求数据json
     *
     * @param urlPath cmd Url地址
     * @param token
     * @param params
     * @return
     */
    public static String map2json(String urlPath, String token, Map<String, String> params) {

        if (TextUtils.isEmpty(urlPath)) {
            try {
                throw new Exception("cmd path is null");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (null != params) {
            StringBuilder stringBuilder = new StringBuilder("{");
            stringBuilder.append("\"cmd\":" + "\"" + urlPath + "\"" + ",");
            stringBuilder.append("\"parameters\":" + "{");
            if (params.size() > 0) {
                for (Iterator it = params.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry e = (Map.Entry) it.next();
                    stringBuilder.append("\"" + e.getKey() + "\":");
                    stringBuilder.append("\"" + e.getValue() + "\"");
                    stringBuilder.append(",");
                }
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            stringBuilder.append("}");

            if (TextUtils.isEmpty(token)) {
                stringBuilder.append("}");
            } else {
                stringBuilder.append(",");
                stringBuilder.append("\"token\":");
                stringBuilder.append("\"" + token + "\"");
                stringBuilder.append("}");
            }
            return stringBuilder.toString();
        } else {
            StringBuilder stringBuilder = new StringBuilder("{");
            stringBuilder.append("\"cmd\":" + "\"" + urlPath + "\"");

            if (TextUtils.isEmpty(token)) {
                stringBuilder.append("}");
            } else {
                stringBuilder.append(",");
                stringBuilder.append("\"token\":");
                stringBuilder.append("\"" + token + "\"");
                stringBuilder.append("}");
            }
            return stringBuilder.toString();
        }
    }


    /**
     * 日期精确
     * @param data
     * @param format
     * @return
     * @throws ParseException
     */
    public static String DataToString(String data, String format) throws ParseException {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat(format);
        Date date = sdf.parse(data);
        result = sdf2.format(date);
        return result;
    }
}
