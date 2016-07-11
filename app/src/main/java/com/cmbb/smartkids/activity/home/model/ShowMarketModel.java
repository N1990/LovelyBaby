package com.cmbb.smartkids.activity.home.model;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/7/8 下午2:19
 * 修改人：N.Sun
 * 修改时间：16/7/8 下午2:19
 * 修改备注：
 */
public class ShowMarketModel {

    private DataEntity data;

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
        private boolean isShow;
        private String showUrl;

        public boolean isIsShow() {
            return isShow;
        }

        public void setIsShow(boolean isShow) {
            this.isShow = isShow;
        }

        public String getShowUrl() {
            return showUrl;
        }

        public void setShowUrl(String showUrl) {
            this.showUrl = showUrl;
        }
    }

    public static void isShowMarket(OkHttpClientManager.ResultCallback<ShowMarketModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        OkHttpClientManager.postAsyn(Constants.H5.IS_SHOW_MARKET, params, callback);
    }
}
