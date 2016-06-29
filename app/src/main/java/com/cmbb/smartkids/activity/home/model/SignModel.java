package com.cmbb.smartkids.activity.home.model;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/6/20 下午4:18
 * 修改人：N.Sun
 * 修改时间：16/6/20 下午4:18
 * 修改备注：
 */
public class SignModel {

    /**
     * isSign : 0
     */

    private DataEntity data;
    /**
     * data : {"isSign":0}
     * msg : 数据加载成功
     */

    private String msg;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataEntity {
        private int isSign;

        public int getIsSign() {
            return isSign;
        }

        public void setIsSign(int isSign) {
            this.isSign = isSign;
        }
    }

    public static void getSignRequest(OkHttpClientManager.ResultCallback<SignModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.IS_SIGN, params, callback);
    }
}
