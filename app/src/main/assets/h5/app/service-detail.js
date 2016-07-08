/**
 * Created by Administrator on 2016/2/26.
 */
function loading(){
   $.showPreloader("加载中...");
}


$(document).on("pageInit","#page-service-detail",function(e,id,page){
   uDevice();
   //$.showPreloader("加载中...");
   var action = config.requestUrl;
   var data;
   if(serviceID){
      data = {
         "cmd": config.cmds.serviceDetail,
         "parameters": {
            "id":serviceID
         }
      }
      searchService(action,data);
   }
   else{
      checkParams();
      function checkParams() {
         if (!paramsData) {
            setTimeout(function () {
               checkParams();
            }, 1000);
         }
         else{
            data = {
               "cmd": config.cmds.serviceDetail,
               "parameters": {
                  "id":paramsData.serviceID
               }
            }
            searchService(action,data);
         }
      }
   }
   var loading = false;
   var numberOfPerPage = 5;
   var pageNo = 0;
   var cachePage = 0;
   var evaluateList = [];
   var tabIndex = 0;
   var listRows = 0;
   var totalPage = 0;
   var firstInfiniteFlag = true;

   $(page).on("infinite", function() {
      if (loading) return;
      loading = true;
      if($(".tabs").find(".active").attr("id")=="tab-service-detail"){
         tabIndex = 0;
      }
      else if($(".tabs").find(".active").attr("id")=="tab-service-buy"){
         tabIndex = 1;
      }
      else{
         tabIndex = 2;
      }
      if(tabIndex!=2){
         loading = false;
         return;
      }

      if(!firstInfiniteFlag){
         if(pageNo == totalPage){
            $('.infinite-scroll-preloader').css({"display":"none"});
            return;
         }
      }

      $('.infinite-scroll-preloader').css({"visibility":"visible"});
      //$('.infinite-scroll-preloader').css({"display":"block"});
      var action = config.requestUrl;
      data = {
         "cmd": config.cmds.serviceEvaluate,
         "parameters": {
            "id": (serviceID ? serviceID : paramsData.serviceID),
            "numberOfPerPage":numberOfPerPage,
            "pageNo":pageNo
         }
      }
      uAjax.ajax(action,data,function(result){
         console.log(result);
         loading = false;
         firstInfiniteFlag = false;
         $('.infinite-scroll-preloader').css({"visibility":"hidden"});
         //$('.infinite-scroll-preloader').css({"display":"none"});
         cachePage = pageNo;
         totalPage = result.response.data.total;
         if(result.response.data.rows.length == 0){
            pageNo = cachePage;
         }
         else{
            pageNo = result.response.data.page;
            var rows = result.response.data.rows;
            for(var i = 0 ;i < rows.length ; i++){
               var row = rows[i];
               var evaluteItem = $("<li class='item-content'></li>");
               var evaluteUserHeader = $("<div class='item-media round user-header judge-user-header' data-image='" + JSON.stringify(row.userBasicInfo) +"'><img src='" + row.userBasicInfo.userSmallImg  + "'></div>");
               var evaluteInner = $("<div class='item-inner'></div>");
               var evaluteTitle = $("<div class='item-title-row'><div class='item-title'>" + row.userBasicInfo.userNike +
                   "</div><div class='item-after'>" + formateShotDateC(row.evaluateDate) + "</div>");
               var evaluteSubtitle = $("<div class='item-subtitle'></div>");
               evaluteSubtitle.append(addUserLevels(parseInt(row.evaluateType,10)));
               var evaluteDescirption = $("<div class='item-descirption'></div>");
               var evaluateContent = $("<p>" + row.evaluateContent + "</p>");
               var evaluteDescirptionImage = $("<div class='description-image-group'></div>");
               evaluteItem.append(evaluteUserHeader);
               evaluteItem.append(evaluteInner);
               evaluteInner.append(evaluteTitle);
               evaluteInner.append(evaluteSubtitle);
               if(row.evaluateContent){
                  evaluteDescirption.append(evaluateContent);
               }
               evaluteInner.append(evaluteDescirption);

               if(row.firstImg){
                  var imgData = {"imgUrl":row.firstImg,"imgWidth":row.firstImgWidth,"imgHeight":row.firstImgHeight};
                  evaluteDescirptionImage.append($("<div class='description-image' data-image='" + JSON.stringify(imgData) + "'><img src='" + row.firstImg + "'></div>"));
                  evaluteDescirption.append(evaluteDescirptionImage);
               }
               if(row.secondImg){
                  var imgData = {"imgUrl":row.secondImg,"imgWidth":row.secondImgWidth,"imgHeight":row.secondImgHeight};
                  evaluteDescirptionImage.append($("<div class='description-image' data-image='" + JSON.stringify(imgData) + "'><img src='" + row.secondImg + "'></div>"));
                  evaluteDescirption.append(evaluteDescirptionImage);
               }
               if(row.thirdImg){
                  var imgData = {"imgUrl":row.thirdImg,"imgWidth":row.thirdImgWidth,"imgHeight":row.thirdImgHeight};
                  evaluteDescirptionImage.append($("<div class='description-image' data-image='" + JSON.stringify(imgData) + "'><img src='" + row.thirdImg + "'></div>"));
                  evaluteDescirption.append(evaluteDescirptionImage);
               }
               $(".evaluate-group").append(evaluteItem);

               evaluteDescirptionImage.children(".description-image").children("img").each(function(){
                  var self = this;
                  var image = new Image();
                  var parentWidth = $(self).parent().width();
                  var parentHeight =$(self).parent().height();
                  image.onload = function(){
                     var imageWidth = image.width;
                     var imageHeight = image.height;
                     var scale,left,top;
                     if(imageWidth>imageHeight){
                        scale =  imageWidth/imageHeight;
                        imageHeight = parentHeight;
                        imageWidth = scale * imageHeight;
                     }
                     else{
                        scale = imageHeight/imageWidth;
                        imageWidth = parentWidth;
                        imageHeight = scale * imageWidth;
                     }
                     left = (parentWidth - imageWidth )/2;
                     top = (parentHeight- imageHeight)/2;
                     $(self).css({"width":imageWidth+"px","height":imageHeight+"px","margin-left":left+"px","margin-top":top+"px"});
                  }
                  image.src = $(self).attr("src");
               })
            }
         }
      });
   });

   function searchService(action,data){
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
            var response = result.response.data;
            //完成对接
            if(myBridge){
               myBridge.callHandler("getServiceData", response, function(data, responseCallback) {});
            }

            $(".service-detail-header>img").attr({"src":response.servicesImg,"data-height":response.imgHeight,"data-width":response.imgWidth});
            $(".service-title").text(response.title);
            $(".service-price").text(response.price + "元/" + response.priceDesc);
            $(".service-sell-total").text("已售" + (response.sellNum || 0) +"件");
            var remarks = response.mark.split(",");
            for(var i=0;i<remarks.length;i++){
               var remark = $("<span class='remark'><i class='icon iconfont icon-roundcheck'></i>" + remarks[i] + "</span>");
               $(".service-remark").append(remark);
            }
            var userInfoList = response.userInfoList[0];

            $(".service-own-user-group").attr({"data-user": JSON.stringify(userInfoList)})
            $(".service-own-user>img").attr({"src": userInfoList.userSmallImg});
            $(".user-name").text(userInfoList.userNike);
            $(".user-remark").text(userInfoList.userRole[0].eredarName);
            $(".service-own-star").append(addUserLevels(userInfoList.userLevel));
            $(".service-own-time").html("<span><i class='icon iconfont icon-qiandaoshijian'></i>" + response.serviceTime + "</span>")
            if(checkAddressFlag(parseInt(response.type),10)){
               $(".addressFlag").css({display:"block"});
               $(".service-own-address").html("<span><i class='icon iconfont icon-weizhi1'></i>" + response.address + "</span>");
               $(".service-own-address-group").attr({"data-address":response.address});
            }
            else{
               $(".addressFlag").css({display:"none"});
            }
            $(".service-own-phone").html("<span><i class='icon iconfont icon-dianhua'></i>" + response.servicePhone + "</span>");
            $(".service-own-phone-group").attr({"data-phone":response.servicePhone});


            imgLoad(".topImg", function() {
               $.initFixedTab();
            })

            var regText = /(\r\n)/g;
            var resultContent = response.content.replace(regText,'<br/>');

            $(".service-content").html(resultContent);
            $(".service-content img").each(function(){
               $(this).css({"width":"100%","height":"auto"})
            })
            var resultNotice = response.notice.replace(regText,'<br/>');
            $(".service-buy-notice").html(resultNotice);

            var serviceList = response.recommonedServiceList;
            if(checkServiceList(serviceList)){
               $(".service-detail-part-four").css({display:"block"});
            }
            else{
               $(".service-detail-part-four").css({display:"none"});
            }
            $(".service-detail-recommendation-group").append(addServiceList(serviceList));
         },2000)
      });
   }

   function imgLoad(img, callback) {
      var timer = setInterval(function() {
         if ($(img).attr("complete")) {
            callback(img)
            clearInterval(timer)
         }
      }, 50)
   }

   function noFindImg(target){
      $(target).attr({"src":""});
   }

   function addUserLevels(levels){
      var stars = "";
      for(var i=0;i<5;i++){
         if(i<levels){
            stars = stars + "<i  class='icon iconfont star icon-favfill'></i>"
         }
         else{
            stars = stars + "<i  class='icon iconfont star icon-fav'></i>"
         }
      }
      return $(stars);
   }

   function checkAddressFlag(type){
      switch (type){
         case 201:
         case 205:
            return false;
            break;
         default:
            return true;
            break;
      }
   }

   function checkServiceList(serviceList){
      if(serviceList.length>0){
         return true;
      }
      return false;
   }

   function addServiceList(serviceList){
      var serviceItems = "";
      for(var i=0;i<serviceList.length;i++){
         serviceItems = serviceItems + "<li class='recommendation-item' data-image='" + JSON.stringify(serviceList[i]) + "'><div><img src='" + serviceList[i].serviceImg + "'></div><p>" + serviceList[i].title +"</p></li>";
      }
      return $(serviceItems);
   }

   //推荐服务点击事件

   $(".service-detail-recommendation-group").on("click",".recommendation-item",function(){
      //var id = JSON.parse($(this).attr("data-image")).id;
      ///注意这里id需要动态改变
      var service = JSON.parse($(this).attr("data-image"));
      if(myBridge){
         myBridge.callHandler("getOtherService",service,function(responseData){});
      }
   });

   //完成对接
   $(".service-own-user-group").on("click",function(){
      if(myBridge){
         myBridge.callHandler("clickHead",JSON.parse($(this).attr("data-user")),function(responseData){});
      }
   })

   //完成对接
   $(document).on("click",".judge-user-header",function(){
      if(myBridge){
         myBridge.callHandler("clickHead",JSON.parse($(this).attr("data-image")),function(responseData){});
      }
   })

   $(".service-own-address-group").on("click",function(){
      //$.alert($(this).attr("data-address"));
   })

   //完成对接
   $(".service-own-phone-group").on("click",function(){
      var phone = {"phone":$(this).attr("data-phone")};
      if(myBridge){
         myBridge.callHandler("callPhone",phone,function(responsData){});
      }
      /*else{
       $.alert($(this).attr("data-phone"));
       }*/
   })

   //完成对接
   $(document).on("click",".btnShowPhoto",function(){
      var imgData = {"imgUrl":$(this).attr("src"),"imgWidth":$(this).attr("data-width"),"imgHeight":$(this).attr("data-height")};
      if(myBridge){
         myBridge.callHandler("getImage",imgData,function(responsData){});
      }
   });

   //完成对接
   $(document).on("click",".description-image",function(){
      var imgData = JSON.parse($(this).attr("data-image"));
      if(myBridge){
         myBridge.callHandler("getImage",imgData,function(responsData){});
      }
   });

   $(document).on("click","p>img",function(){
   });

});

$.init();
