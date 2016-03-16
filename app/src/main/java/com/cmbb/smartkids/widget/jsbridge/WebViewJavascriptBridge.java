package com.cmbb.smartkids.widget.jsbridge;


public interface WebViewJavascriptBridge {
	
	public void send(String data);
	public void send(String data, CallBackFunction responseCallback);
	
	

}
