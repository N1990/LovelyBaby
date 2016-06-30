package com.cmbb.smartkids.activity.login.model;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/6/29 下午4:06
 * 修改人：N.Sun
 * 修改时间：16/6/29 下午4:06
 * 修改备注：
 */
public class OpenIdModel {

    /**
     * loginAccount : 15901718791
     */

    private DataEntity data;
    /**
     * data : {"loginAccount":"15901718791"}
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
        private String loginAccount;

        public String getLoginAccount() {
            return loginAccount;
        }

        public void setLoginAccount(String loginAccount) {
            this.loginAccount = loginAccount;
        }
    }

    /**
     * 是否绑定过用户
     *
     * @param callback
     */
    public static void handleOpenIdRequest(String openId, OkHttpClientManager.ResultCallback<OpenIdModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("openId", openId);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.OPEN_ID, params, callback);

    }
}
