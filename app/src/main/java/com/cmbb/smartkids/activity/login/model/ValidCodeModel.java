package com.cmbb.smartkids.activity.login.model;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/7/5 上午9:47
 * 修改人：N.Sun
 * 修改时间：16/7/5 上午9:47
 * 修改备注：
 */
public class ValidCodeModel {

    /**
     * hasPassword : false
     */

    private DataEntity data;
    /**
     * data : {"hasPassword":"false"}
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
        private String hasPassword;

        public String getHasPassword() {
            return hasPassword;
        }

        public void setHasPassword(String hasPassword) {
            this.hasPassword = hasPassword;
        }
    }

    /**
     * 验证是否有密码
     *
     * @param loginAccount loginAccount
     * @param securityCode securityCode
     * @param callback     ResultCallback
     */
    public static void handleValidCodeRequest(String loginAccount, String securityCode, OkHttpClientManager.ResultCallback<ValidCodeModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("loginAccount", loginAccount);
        params.put("securityCode", securityCode);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.VALID_CODE, params, callback);
    }
}
