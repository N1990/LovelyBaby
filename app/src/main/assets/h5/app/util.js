/**
 * Created by Administrator on 2016/2/25.
 */
var UtilResetImage = function(element,option,imgUrl,flag){
    flag = flag===undefined?true:flag;
    //var parent = $(element);
    //parent.each(function(){
        var parentWidth = $(element).width()||option.parentWidth;
        var parentHeight = option.scale * parentWidth;
        $(element).height(parentHeight);
        var childImg = $(element).children("img");
        var image = new Image();
        image.onload = function(){
            var imgWidth = image.width;
            var imgHeight = image.height;
            var imgScale = imgHeight/ imgWidth;

            if(option.scale==1){
                if(imgWidth>imgHeight){
                    imgHeight = parentHeight;
                    imgWidth = imgHeight / imgScale
                    childImg.css({height:imgHeight+"px",width:imgWidth+"px"});
                }
                else{
                    imgWidth = parentWidth;
                    imgHeight = imgWidth*imgScale;
                    childImg.css({width:imgWidth+"px",height:imgHeight+"px"});

                }
                childImg.attr({src:imgUrl});
                childImg.css({"margin-top":(parentHeight-imgHeight)/2+"px","margin-left":(parentWidth-imgWidth)/2+"px"});
                return;
            }
            else{
                if(flag){
                    if(option.scale>=imgScale){
                        imgHeight = parentHeight;
                        childImg.css({height:imgHeight+"px",width:"auto"});
                    }
                    else{
                        imgWidth = parentWidth;
                        childImg.css({width:imgWidth+"px",height:"auto"});
                    }
                }
                else{
                    if(option.scale<=imgScale){
                        imgHeight = parentHeight;
                        childImg.css({width:"auto",height:imgHeight+"px"});
                    }
                    else{
                        imgWidth = parentWidth;
                        childImg.css({width:imgWidth+"px",height:"auto"});
                    }
                }
            }
            childImg.attr({src:imgUrl});
            imgWidth = childImg.width();
            imgHeight = childImg.height();
            childImg.css({"margin-top":(parentHeight-imgHeight)/2+"px","margin-left":(parentWidth-imgWidth)/2+"px"});
        }
        image.src = imgUrl;
    //});
}


