package com.cmbb.smartkids.utils.log;

public interface LogNode {

    public void println(int priority, String tag, String msg, Throwable tr);

}
