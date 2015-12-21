package com.cmbb.smartkids.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.support.annotation.RawRes;
import android.text.TextUtils;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.utils.log.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by javon on 2015/7/28.
 */
public class Tools {

    /**
     * 获得当前进程号
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 获取设备基本信息
     *
     * @param context
     * @return
     */
    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 手机号码验证
     *
     * @param phone
     * @return
     */
    public static boolean isMobileNo(String phone) {
        String match = "^((13|15|18|17|14)\\d{9})|147\\d{8}$";
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * 身份证正则
     *
     * @param id
     * @return
     */
    public static boolean isID(String id) {
        String match = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(id.trim());
        return matcher.matches();
    }


    /***
     * 读取本地文件中JSON字符串
     *
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
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
                    String values = null;
                    if (((String) e.getValue()).contains("\n")) {
                        values = ((String) e.getValue()).replace("\n", "\\n");
                    } else {
                        values = (String) e.getValue();
                    }
                    stringBuilder.append("\"" + values + "\"");
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
     *
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

    /**
     * @param time
     * @param format
     * @return
     */
    public static String DateToString(long time, String format){
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        result = sdf.format(time);
        return result;
    }

    /**
     * Raw 获取资源文件
     *
     * @param context
     * @param res
     * @return
     */
    public static String getContentFromRaw(Context context, @RawRes int res) {
        InputStream in = null;
        String temp = "";
        try {
            in = context.getResources().openRawResource(R.raw.popman_rule);
            byte[] buff = new byte[1024];// 缓存
            int rd = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((rd = in.read(buff)) != -1) {
                baos.write(buff, 0, rd);
                temp = new String(baos.toByteArray(), "UTF-8");
            }
            baos.close();
        } catch (Exception e) {
            Log.e(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return temp;
        }
    }


}
