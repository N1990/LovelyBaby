/**
 * Created by Administrator on 2016/4/5.
 */
var urls = window.location.href.split('#')[0];
var param = urls.split('?')[1];
var title='';
var imgUrl='';
var desc = '';
var link = '';

var url = null;

function initShare(action,type){
    if(type==1){
        url=pagePath;
    }else{
        url = servicePagePath;
    }
    var datas ={
        "cmd":config.cmds.wxJsSign,
        "parameters":{
            "url":url
        }
    };

    uAjax.ajax(action,datas,function(data){
        var jsons = eval(data);
        wx.config({
            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            //appId : "wx5bb398c959489ae4", // 必填，公众号的唯一标识
            appId : "wx5bb398c959489ae4",
            timestamp : jsons.response.timestamp, // 必填，生成签名的时间戳
            nonceStr : jsons.response.nonceStr, // 必填，生成签名的随机串
            signature : jsons.response.signature,// 必填，签名，见附录1
            jsApiList : ['onMenuShareTimeline', 'onMenuShareAppMessage','onMenuShareQQ', 'onMenuShareWeibo'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });

        wx.ready(function(){
            //判断当前客户端版本是否支持指定JS接口
            wx.checkJsApi({
                jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage','onMenuShareQQ', 'onMenuShareWeibo']
            });

            shareItem(title,desc,link,imgUrl);

        });

        wx.error(function (res) {
            //alert('wx.error: '+res.errMsg);
        });
    });
}

function shareItem(title,desc,link,imgUrl){
    shareFriends(title,link,imgUrl);
    shareFriend(title,desc,link,imgUrl);
    shareQQ(title,desc,link,imgUrl);
    shareQQZone(title,desc,link,imgUrl);
}

//分享朋友圈
function shareFriends(title,link,imgUrl){
    wx.onMenuShareTimeline({
        title: title, // 分享标题
        link: link, // 分享链接
        imgUrl: imgUrl, // 分享图标
        success: function () {
            // 用户确认分享后执行的回调函数
            //alert('success');
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
            //alert('cancel');
        }
    });
}

//分享朋友
function shareFriend(title,desc,link,imgUrl){
    wx.onMenuShareAppMessage({
        title: title, // 分享标题
        desc: desc, // 分享描述
        link: link, // 分享链接
        imgUrl: imgUrl, // 分享图标
        success: function () {
            // 用户确认分享后执行的回调函数
            //alert('success');
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
            // alert('cancel');
        }
    });
}

function shareQQ(title,desc,link,imgUrl){
    wx.onMenuShareQQ({
        title: title, // 分享标题
        desc: desc, // 分享描述
        link: link, // 分享链接
        imgUrl: imgUrl, // 分享图标
        success: function () {
            // 用户确认分享后执行的回调函数
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
}

function shareQQZone(title,desc,link,imgUrl){
    wx.onMenuShareWeibo({
        title: title, // 分享标题
        desc: desc, // 分享描述
        link: link, // 分享链接
        imgUrl: imgUrl, // 分享图标
        success: function () {
            // 用户确认分享后执行的回调函数
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
}