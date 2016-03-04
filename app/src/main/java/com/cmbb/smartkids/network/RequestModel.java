package com.cmbb.smartkids.network;

import java.util.Map;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 上午10:23
 */
public class RequestModel {

    private String cmd;
    private Map<String, String> parameters;
    private String token;

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public String getCmd() {
        return cmd;
    }


    public String getToken() {
        return token;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
