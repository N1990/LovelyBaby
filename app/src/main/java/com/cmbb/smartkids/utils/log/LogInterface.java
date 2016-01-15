package com.cmbb.smartkids.utils.log;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/22 下午12:49
 */
public interface LogInterface {
    String DEFAULT_MESSAGE = "execute";
    String LINE_SEPARATOR = System.getProperty("line.separator");
    String NULL_TIPS = "Log with null object";
    int JSON_INDENT = 4;

    int V = 0x1;
    int D = 0x2;
    int I = 0x3;
    int W = 0x4;
    int E = 0x5;
    int A = 0x6;
    int JSON = 0x7;
}
