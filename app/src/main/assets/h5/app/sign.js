/**
 * Created by Administrator on 2016/2/26.
 */
var isTodaySign = 0;
var dayTotal = 0;
var date = "";
var signCount = 0;
var dayRows = {};
$(document).on("pageInit","#page-sign",function(e,id,page){
    uDevice();
    $.showPreloader("加载中...");
    var action = config.requestUrl;
    var data;
    if(token){
        data = {
            "cmd": config.cmds.getSign,
            "token":token
        }
        searchSign(action,data);
    }
    else{
        checkParams();
        function checkParams(){
            if(!paramsData){
                setTimeout(function(){
                    checkParams();
                },1000);
            }
            else{
                data = {
                    "cmd": config.cmds.getSign,
                    "token":paramsData.token
                }
                searchSign(action,data);
            }
        }
    }

    function searchSign(action,data){
        uAjax.ajax(action,data,function(result){
            console.log(result);
            setTimeout(function(){
                $.hidePreloader();
                var error = result.error;
                if(error){
                    $.alert(error.errorInfo,function(){
                        if(myBridge){
                            myBridge.callHandler("loginAgain",{"errorInfo":error.errorInfo},function(data,responseData){});
                        }
                    });
                    return;
                }

                $(page).css({display:"block"});
                for(var i=0;i<result.response.signImage.length;i++){
                    $(".swiper-container.sign-header").children(".swiper-wrapper")
                        .append($("<div class='swiper-slide' data-image='"+ JSON.stringify(result.response.signImage[i]) +"'><img src='" + result.response.signImage[i].adImg + "'></div>"))
                }

                if(result.response.signImage.length>1){
                    var mySwiper = new Swiper('.swiper-container.sign-header',{
                        loop:true,
                        autoplay:2000,
                        autoHeight:true,
                        autoplayDisableOnInteraction:false,
                        iOSEdgeSwipeDetection : true,
                    });
                }
                else{
                    var mySwiper = new Swiper('.swiper-container.sign-header',{
                        autoplayDisableOnInteraction:false,
                        iOSEdgeSwipeDetection : true,
                    });
                }


                isTodaySign = parseInt(result.response.isTodaySign,10);
                dayTotal = result.response.dayTotal;
                if(isTodaySign){
                    $(".sign-result").css({display:"block"});
                    if(dayTotal){  //非0
                        $(".sign-price").html("<p>当前奖励:2成长值</br>" +
                            " <span>今日已奖励2成长值，再签" + dayTotal + "日即得20成长值奖励</span></p>");
                    }
                    else{     //0
                        $(".sign-price").append("<p>当前奖励:20成长值</br>" +
                            " <span>已连续签到7天，奖励20成长值</span></p>");
                    }
                    $(".button-sign").attr({"disabled":"disabled"});
                }
                else{
                    $(".sign-result").css({display:"none"});
                }

                date = result.response.date;
                signCount = result.response.signCount;
                $(".sign-date").html(formateShotDate(date));
                $(".sign-days").html("共签到" + signCount +"天");
                $(".table-cell").remove();
                dayRows = result.response.dayRows;
                for(var i=0;i<dayRows.length;i++){
                    var dayItem = dayRows[i];
                    var tableCell = $("<span class='table-cell'></span>");
                    var tableHear;
                    var tableDay;
                    if(checkHeart(dayItem)===0){
                        tableDay = $("<span class='day day-out-of'>" + formate(dayItem.day) + "</span>");
                    }
                    else if(checkHeart(dayItem)>0){
                        tableDay = $("<span class='day'>" + formate(dayItem.day) + "</span>");
                    }
                    if(checkHeart(dayItem)===0||checkHeart(dayItem)===10||checkHeart(dayItem)===13){
                        tableHear = $("<span class='heart'></span>");
                    }
                    else if(checkHeart(dayItem)===3){
                        tableHear = $("<span class='heart heart-red'></span>");
                    }
                    else if(checkHeart(dayItem)===6){
                        tableHear = $("<span class='heart heart-gray'></span>");
                    }
                    else if(checkHeart(dayItem)===7){
                        tableHear = $("<span class='heart heart-current'></span>");
                    }
                    tableCell.append(tableHear);
                    tableCell.append(tableDay);
                    $(".table-body").append(tableCell);
                }
            },2000)
        });
    }

    $(".button-sign").click(function(){
        $.showPreloader();
        data = {
            "cmd": config.cmds.sign,
            "token": (token?token:paramsData.token)
        }
        uAjax.ajax(action,data,function(result) {
            $.hidePreloader();
            if(result.response.msg === "签到成功"){
                $.alert("签到成功");
                isTodaySign = 1;
                dayTotal = dayTotal-1;
                $(".sign-result").css({display:"block"});
                if(dayTotal){  //非0
                    $(".sign-price").append($("<p>当前奖励:2成长值</br>" +
                        " <span>今日已奖励2成长值，再签" + dayTotal + "日即得20成长值奖励</span></p>"));
                }
                else{     //0
                    $(".sign-price").append($("<p>当前奖励:20成长值</br>" +
                        " <span>已连续签到7天，奖励20成长值</span></p>"));
                }
                $(".button-sign").attr({"disabled":"disabled"});

                $(".sign-days").html("共签到" + (signCount+1) +"天");

                for(var i = 0 ; i< dayRows.length; i++){
                    var dayItem = dayRows[i];
                    if(formateShotDate(date) === formateShotDate(dayItem.day)){
                        $($(".table-cell").get(i)).children(".heart").addClass("heart-current");
                        break;
                    }
                    else{
                        continue;
                    }
                }
            }
            else{
                $.alert(result.error);
            }
        });
    });

    //完成对接
    $(document).on("click",".swiper-slide",function(){
        console.log($(this).attr("data-image"));
        var imgData = JSON.parse($(this).attr("data-image"));
        if(myBridge){
            myBridge.callHandler("showAdLink",imgData,function(responsData){});
        }
    });

    function checkHeart(dayItem){
        var flag = 0;  //不是当月
        if(dayItem.isMonth) {
            flag = flag + 1;  //1  当月 并且今天
            if (!dayItem.isToday) {
                flag = flag + 2; //3  //当月不是今天已签到
                if (!dayItem.isSign) {
                    flag = flag + 3; //6  //当月不是今天  今天之前 未签到
                    if(parseInt(formate(date),10)<parseInt(formate(dayItem.day),10)){
                        flag = flag + 4; //10   //当月不是今天  今天之之后 未签到
                    }
                }
            }
            else{   //1  当月 并且今天
                flag = flag + 6 ; //7  当月 并且今天签到
                if(!isTodaySign){
                    flag = flag + 6 ; //13  当月 并且今天未签到
                }
            }
        }
        return flag;
    }

})


$.init();
