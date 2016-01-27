package com.cmbb.smartkids.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * Package on com.cmbb.smartkids.utils.log
 * Created by javon on 15/11/13.
 */
public class Utils {
    /**
     * 请求数据json
     *
     * @param urlPath cmd Url地址
     * @param token
     * @param params
     * @return
     */
    public static String map2json(String urlPath, String token, Map<String, Object> params) {

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
                    if (e.getValue() instanceof ArrayList){
                        ArrayList temp = (ArrayList) e.getValue();
                        stringBuilder.append(Arrays.toString(temp.toArray()));
                    }else{
//                        stringBuilder.append("\"" + e.getValue() + "\"");
                        String values = null;
                        if (((String) e.getValue()).contains("\n") && ((String) e.getValue()).contains("\"")) {
                            values = ((String) e.getValue()).replace("\n", "\\n");
                            values = values.replace("\"", "\\\"");
                        } else if(((String) e.getValue()).contains("\"")){
                            values = ((String) e.getValue()).replace("\"", "\\\"");
                        }else if(((String) e.getValue()).contains("\n") ){
                            values = ((String) e.getValue()).replace("\n", "\\n");
                        }
                        else {
                            values = (String) e.getValue();
                        }
                        stringBuilder.append("\"" + values + "\"");
                    }
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
}
