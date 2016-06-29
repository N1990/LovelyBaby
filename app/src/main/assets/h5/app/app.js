/**
 * Created by Administrator on 2016/2/23.
 */
var config = {
    url:window.location.href,
    requestUrl:"http://mengbaopai.smart-kids.com:82/wine-rest/cgi",
    //requestUrl:"http://erpuat.mengbp.com:8090/wine-rest/cgi",
    requestWXUrl:"http://weixin.mengbp.com/wine-weixin-rest/cgi",
    //requestWXUrl:"http://erpuat.mengbp.com:8090/wine-weixin-rest/cgi",
    cmds : {
        serviceDetail: "smart/services/getServicesDetails",
        serviceEvaluate: "smart/services/getServiceEvaluate",
        getSign:"smart/user/getSign",
        sign:"smart/user/sign",
        wxJsSign:"christ/weixin/setSign"
    }
}

function init(jsName){
    var date = new Date().getTime();
    var script = document.createElement("script");
    script.setAttribute("type","text/javascript");
    script.setAttribute("src","../app/" + jsName + ".js?" + date);
    document.body.appendChild(script);
}


/*Andriod Bridge初始化*/
function connectWebViewJavascriptBridge(callback) {
    if (window.WebViewJavascriptBridge) {
        callback(WebViewJavascriptBridge)
    } else {
        document.addEventListener(
            'WebViewJavascriptBridgeReady'
            , function() {
                callback(WebViewJavascriptBridge)
            },
            false
        );
    }
}

/*IOS Bridge初始化*/
function setupWebViewJavascriptBridge(callback) {
    if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
    if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
    window.WVJBCallbacks = [callback];
    var WVJBIframe = document.createElement('iframe');
    WVJBIframe.style.display = 'none';
    WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
    document.documentElement.appendChild(WVJBIframe);
    setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0)
}



var uAjax = {
    ajax : function(action,data,callback){
        $.ajax({
            type : "GET",
            url:action,
            data:data,
            dataType : "jsonp",
            jsonp: "callback",
            success:callback||function(data){
                $.hidePreloader();
                console.log(data);
            },
            error:function(data){
                $.hidePreloader();
                $.alert("服务器正在维护！");
                console.log(data);
            }
        });
    }
}

var serviceID= purl().param("id");
var token =  purl().param("token");
var myBridge;
var paramsData;
var serviceData;




var uDevice = function(){
    var deviceIsWindowsPhone = navigator.userAgent.indexOf("Windows Phone") >= 0;
    var deviceIsAndroid = navigator.userAgent.indexOf('Android') > 0 && !deviceIsWindowsPhone;
    var deviceIsIOS = /iP(ad|hone|od)/.test(navigator.userAgent) && !deviceIsWindowsPhone;
    var deviceIsIOS4 = deviceIsIOS && (/OS 4_\d(_\d)?/).test(navigator.userAgent);
    var deviceIsIOSWithBadTarget = deviceIsIOS && (/OS [6-7]_\d/).test(navigator.userAgent);
    var deviceIsBlackBerry10 = navigator.userAgent.indexOf('BB10') > 0;

    if(deviceIsAndroid){
        connectWebViewJavascriptBridge(function(bridge) {
            bridge.init();
            myBridge = bridge;
            //完成对接
            myBridge.registerHandler("getParams", function(data, responseCallback) {
                paramsData = JSON.parse(data);
            });
        });
    }

    if(deviceIsIOS){
        setupWebViewJavascriptBridge(function(bridge) {
            myBridge = bridge;
            //完成对接
            myBridge.registerHandler("getParams", function(data, responseCallback) {
                paramsData = data;
            });
            //完成对接
           /* myBridge.registerHandler("getServiceData", function(data, responseCallback) {
                responseCallback(serviceData);
            });*/
        });
    }
}


function formate(day){
    return DateFormat.format.date(day,"d");
}

function formateShotDate(day){
    return DateFormat.format.date(day,"yyyy-MM-dd");
}

function formateShotDateC(day){
    return DateFormat.format.date(day,"yyyy年MM月dd日");
}
$(".page").css({display:"none"});




