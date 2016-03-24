package com.cmbb.smartkids.activity.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;
import java.util.List;

/**
 * Created by javon on 16/3/21.
 */
public class EvaluateServiceModel implements Parcelable {

    /**
     * page : 1
     * records : 20
     * rows : [{"id":24,"properties":{},"serviceBasicInfo":{"id":347,"properties":{},"title":"富文本编辑二次测试","introduce":"讲座、沙龙、培训","serviceTime":"","isCycle":0,"startTime":"2016-03-04 15:40:00","endTime":"2016-03-18 15:40:00","applyStartTime":"2016-03-01 15:40:00","applyEndTime":"2016-03-04 15:40:00","peoples":12,"realityPeoples":1,"sellNum":23,"province":440000,"city":440100,"district":440103,"address":"23234阿萨德发射点发","mark":"支持退款,支持支付宝付款,微信付款,支持信付款,支持微付款,支持微信款","refundTatio":20,"price":"20","priceDesc":"份","category":"HHXX","serviceType":1010001,"type":201,"content":"<p><span style=\"color: rgb(0, 176, 80);\">的撒旦发射点发速读法<\/span><\/p><p><br/><\/p><p><span style=\"color: rgb(0, 176, 80);\">阿萨德发射点发速读法<\/span><\/p><p><br/><\/p><p><br/><\/p><p><span style=\"border: 1px solid rgb(0, 0, 0);\">啊速度发射点发射掉<\/span><\/p>","notice":"<p><span style=\"color: rgb(255, 192, 0);\">啊速度发射点发射掉<\/span><\/p><p><span style=\"color: rgb(255, 192, 0);\">撒旦发射点发射点发速读法规划法规<\/span><\/p><p><br/><\/p><p>啊点发射点发速读法电视发射点发点发速读法速度分<\/p><p><br/><\/p><p><br/><\/p><p>啊速度发射点发射点发速读法速度发射<span style=\"color: rgb(255, 192, 0);\"><\/span><\/p>","servicePhone":"121212","status":1,"isRecommoned":1,"isWeekChoice":0,"servicesImg":"http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl","imgWidth":"539","imgHeight":"355","sortNum":2,"browseNumber":6,"isDelete":0,"createDate":"2016-03-01 15:43:01","createUserId":1,"updateDate":"2016-03-17 12:03:19","updateUserId":1,"provinceText":"","cityText":"","districtText":"","surplusTime":""},"orderCode":"003470146352600001","evaluateType":1,"evaluateContent":"嘎哈哈哈斤斤计较沟沟壑壑斤斤计较刚刚不会拒绝家给 v 个 v 和环境健康哈哈哈哈哈哈嘎哈哈哈斤斤计较沟沟壑壑斤斤计较刚刚不会拒绝家给 v 个 v 和环境健康哈哈哈哈哈哈嘎哈哈哈斤斤计较沟沟壑壑斤斤计较刚刚不会拒绝","evaluateDate":"2016-03-18 10:39:03","evaluateUserId":100010,"firstImg":"http://smart.image.alimmdn.com/system/image/2016-03-18/evaluateImgList_Yzk5YzZkZGUtZDQ1NS00ZjhkLWJhYTctZDRiZWQ4NzQ0ZmM0","firstImgWidth":"256","firstImgHeight":"456","secondImg":"http://smart.image.alimmdn.com/system/image/2016-03-18/evaluateImgList_YjliNDk2MzctZDc5My00OWRjLTk2MTAtNjUzOTA4YjFjNDA0","secondImgWidth":"342","secondImgHeight":"256","thirdImg":"http://smart.image.alimmdn.com/system/image/2016-03-18/evaluateImgList_NDI3M2IyNWItZjcyMi00MGIyLTg3ZWUtNjk5NTZmZDE5NjI4","thirdImgWidth":"342","thirdImgHeight":"256","userBasicInfo":{"userId":100010,"uid":121168,"recommoned":1,"userNike":"CharlesWl哈哈","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2016-3-18/233A2171-16FC-446A-B64A-66660DDFD8DA","userBigImg":"http://smart.image.alimmdn.com/oldImage/41355adc8eca493baa57a3474bc477e8.jpg","userBigWidth":"750","userBigHeight":"360","userSmallImg":"http://smart.image.alimmdn.com/app/test/2016-2-25/B63F9EE5-717D-4E74-84FB-746F5F3C2510","userSmallWidth":"750","userSmallHeight":"750","loginAccountType":0,"loginTime":"2016-03-17 09:51:56","loginAccount":"18939963309","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"","userPhone":"18939963309","userPhoneVersion":"","province":310000,"provinceText":"","district":310110,"districtText":"","city":310100,"cityText":"","userLevel":1,"userPresentation":"","backImgWidth":3024,"backImgHeight":4032,"goldCount":1,"growthCount":1,"fans":0,"attentionCount":1,"isSign":0,"isAttention":0,"isEredar":0,"isLoginUser":0,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户"}]}}]
     * total : 4
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":20,"rows":[{"id":24,"properties":{},"serviceBasicInfo":{"id":347,"properties":{},"title":"富文本编辑二次测试","introduce":"讲座、沙龙、培训","serviceTime":"","isCycle":0,"startTime":"2016-03-04 15:40:00","endTime":"2016-03-18 15:40:00","applyStartTime":"2016-03-01 15:40:00","applyEndTime":"2016-03-04 15:40:00","peoples":12,"realityPeoples":1,"sellNum":23,"province":440000,"city":440100,"district":440103,"address":"23234阿萨德发射点发","mark":"支持退款,支持支付宝付款,微信付款,支持信付款,支持微付款,支持微信款","refundTatio":20,"price":"20","priceDesc":"份","category":"HHXX","serviceType":1010001,"type":201,"content":"<p><span style=\"color: rgb(0, 176, 80);\">的撒旦发射点发速读法<\/span><\/p><p><br/><\/p><p><span style=\"color: rgb(0, 176, 80);\">阿萨德发射点发速读法<\/span><\/p><p><br/><\/p><p><br/><\/p><p><span style=\"border: 1px solid rgb(0, 0, 0);\">啊速度发射点发射掉<\/span><\/p>","notice":"<p><span style=\"color: rgb(255, 192, 0);\">啊速度发射点发射掉<\/span><\/p><p><span style=\"color: rgb(255, 192, 0);\">撒旦发射点发射点发速读法规划法规<\/span><\/p><p><br/><\/p><p>啊点发射点发速读法电视发射点发点发速读法速度分<\/p><p><br/><\/p><p><br/><\/p><p>啊速度发射点发射点发速读法速度发射<span style=\"color: rgb(255, 192, 0);\"><\/span><\/p>","servicePhone":"121212","status":1,"isRecommoned":1,"isWeekChoice":0,"servicesImg":"http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl","imgWidth":"539","imgHeight":"355","sortNum":2,"browseNumber":6,"isDelete":0,"createDate":"2016-03-01 15:43:01","createUserId":1,"updateDate":"2016-03-17 12:03:19","updateUserId":1,"provinceText":"","cityText":"","districtText":"","surplusTime":""},"orderCode":"003470146352600001","evaluateType":1,"evaluateContent":"嘎哈哈哈斤斤计较沟沟壑壑斤斤计较刚刚不会拒绝家给 v 个 v 和环境健康哈哈哈哈哈哈嘎哈哈哈斤斤计较沟沟壑壑斤斤计较刚刚不会拒绝家给 v 个 v 和环境健康哈哈哈哈哈哈嘎哈哈哈斤斤计较沟沟壑壑斤斤计较刚刚不会拒绝","evaluateDate":"2016-03-18 10:39:03","evaluateUserId":100010,"firstImg":"http://smart.image.alimmdn.com/system/image/2016-03-18/evaluateImgList_Yzk5YzZkZGUtZDQ1NS00ZjhkLWJhYTctZDRiZWQ4NzQ0ZmM0","firstImgWidth":"256","firstImgHeight":"456","secondImg":"http://smart.image.alimmdn.com/system/image/2016-03-18/evaluateImgList_YjliNDk2MzctZDc5My00OWRjLTk2MTAtNjUzOTA4YjFjNDA0","secondImgWidth":"342","secondImgHeight":"256","thirdImg":"http://smart.image.alimmdn.com/system/image/2016-03-18/evaluateImgList_NDI3M2IyNWItZjcyMi00MGIyLTg3ZWUtNjk5NTZmZDE5NjI4","thirdImgWidth":"342","thirdImgHeight":"256","userBasicInfo":{"userId":100010,"uid":121168,"recommoned":1,"userNike":"CharlesWl哈哈","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2016-3-18/233A2171-16FC-446A-B64A-66660DDFD8DA","userBigImg":"http://smart.image.alimmdn.com/oldImage/41355adc8eca493baa57a3474bc477e8.jpg","userBigWidth":"750","userBigHeight":"360","userSmallImg":"http://smart.image.alimmdn.com/app/test/2016-2-25/B63F9EE5-717D-4E74-84FB-746F5F3C2510","userSmallWidth":"750","userSmallHeight":"750","loginAccountType":0,"loginTime":"2016-03-17 09:51:56","loginAccount":"18939963309","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"","userPhone":"18939963309","userPhoneVersion":"","province":310000,"provinceText":"","district":310110,"districtText":"","city":310100,"cityText":"","userLevel":1,"userPresentation":"","backImgWidth":3024,"backImgHeight":4032,"goldCount":1,"growthCount":1,"fans":0,"attentionCount":1,"isSign":0,"isAttention":0,"isEredar":0,"isLoginUser":0,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户"}]}}],"total":4,"userdata":""}
     * msg : 数据加载成功
     */

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

    public static class DataEntity implements Parcelable {
        private int page;
        private int records;
        private int total;
        private String userdata;
        /**
         * id : 24
         * properties : {}
         * serviceBasicInfo : {"id":347,"properties":{},"title":"富文本编辑二次测试","introduce":"讲座、沙龙、培训","serviceTime":"","isCycle":0,"startTime":"2016-03-04 15:40:00","endTime":"2016-03-18 15:40:00","applyStartTime":"2016-03-01 15:40:00","applyEndTime":"2016-03-04 15:40:00","peoples":12,"realityPeoples":1,"sellNum":23,"province":440000,"city":440100,"district":440103,"address":"23234阿萨德发射点发","mark":"支持退款,支持支付宝付款,微信付款,支持信付款,支持微付款,支持微信款","refundTatio":20,"price":"20","priceDesc":"份","category":"HHXX","serviceType":1010001,"type":201,"content":"<p><span style=\"color: rgb(0, 176, 80);\">的撒旦发射点发速读法<\/span><\/p><p><br/><\/p><p><span style=\"color: rgb(0, 176, 80);\">阿萨德发射点发速读法<\/span><\/p><p><br/><\/p><p><br/><\/p><p><span style=\"border: 1px solid rgb(0, 0, 0);\">啊速度发射点发射掉<\/span><\/p>","notice":"<p><span style=\"color: rgb(255, 192, 0);\">啊速度发射点发射掉<\/span><\/p><p><span style=\"color: rgb(255, 192, 0);\">撒旦发射点发射点发速读法规划法规<\/span><\/p><p><br/><\/p><p>啊点发射点发速读法电视发射点发点发速读法速度分<\/p><p><br/><\/p><p><br/><\/p><p>啊速度发射点发射点发速读法速度发射<span style=\"color: rgb(255, 192, 0);\"><\/span><\/p>","servicePhone":"121212","status":1,"isRecommoned":1,"isWeekChoice":0,"servicesImg":"http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl","imgWidth":"539","imgHeight":"355","sortNum":2,"browseNumber":6,"isDelete":0,"createDate":"2016-03-01 15:43:01","createUserId":1,"updateDate":"2016-03-17 12:03:19","updateUserId":1,"provinceText":"","cityText":"","districtText":"","surplusTime":""}
         * orderCode : 003470146352600001
         * evaluateType : 1
         * evaluateContent : 嘎哈哈哈斤斤计较沟沟壑壑斤斤计较刚刚不会拒绝家给 v 个 v 和环境健康哈哈哈哈哈哈嘎哈哈哈斤斤计较沟沟壑壑斤斤计较刚刚不会拒绝家给 v 个 v 和环境健康哈哈哈哈哈哈嘎哈哈哈斤斤计较沟沟壑壑斤斤计较刚刚不会拒绝
         * evaluateDate : 2016-03-18 10:39:03
         * evaluateUserId : 100010
         * firstImg : http://smart.image.alimmdn.com/system/image/2016-03-18/evaluateImgList_Yzk5YzZkZGUtZDQ1NS00ZjhkLWJhYTctZDRiZWQ4NzQ0ZmM0
         * firstImgWidth : 256
         * firstImgHeight : 456
         * secondImg : http://smart.image.alimmdn.com/system/image/2016-03-18/evaluateImgList_YjliNDk2MzctZDc5My00OWRjLTk2MTAtNjUzOTA4YjFjNDA0
         * secondImgWidth : 342
         * secondImgHeight : 256
         * thirdImg : http://smart.image.alimmdn.com/system/image/2016-03-18/evaluateImgList_NDI3M2IyNWItZjcyMi00MGIyLTg3ZWUtNjk5NTZmZDE5NjI4
         * thirdImgWidth : 342
         * thirdImgHeight : 256
         * userBasicInfo : {"userId":100010,"uid":121168,"recommoned":1,"userNike":"CharlesWl哈哈","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2016-3-18/233A2171-16FC-446A-B64A-66660DDFD8DA","userBigImg":"http://smart.image.alimmdn.com/oldImage/41355adc8eca493baa57a3474bc477e8.jpg","userBigWidth":"750","userBigHeight":"360","userSmallImg":"http://smart.image.alimmdn.com/app/test/2016-2-25/B63F9EE5-717D-4E74-84FB-746F5F3C2510","userSmallWidth":"750","userSmallHeight":"750","loginAccountType":0,"loginTime":"2016-03-17 09:51:56","loginAccount":"18939963309","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"","userPhone":"18939963309","userPhoneVersion":"","province":310000,"provinceText":"","district":310110,"districtText":"","city":310100,"cityText":"","userLevel":1,"userPresentation":"","backImgWidth":3024,"backImgHeight":4032,"goldCount":1,"growthCount":1,"fans":0,"attentionCount":1,"isSign":0,"isAttention":0,"isEredar":0,"isLoginUser":0,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户"}]}
         */

        private List<RowsEntity> rows;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getRecords() {
            return records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getUserdata() {
            return userdata;
        }

        public void setUserdata(String userdata) {
            this.userdata = userdata;
        }

        public List<RowsEntity> getRows() {
            return rows;
        }

        public void setRows(List<RowsEntity> rows) {
            this.rows = rows;
        }

        public static class RowsEntity implements Parcelable {
            private int id;
            /**
             * id : 347
             * properties : {}
             * title : 富文本编辑二次测试
             * introduce : 讲座、沙龙、培训
             * serviceTime :
             * isCycle : 0
             * startTime : 2016-03-04 15:40:00
             * endTime : 2016-03-18 15:40:00
             * applyStartTime : 2016-03-01 15:40:00
             * applyEndTime : 2016-03-04 15:40:00
             * peoples : 12
             * realityPeoples : 1
             * sellNum : 23
             * province : 440000
             * city : 440100
             * district : 440103
             * address : 23234阿萨德发射点发
             * mark : 支持退款,支持支付宝付款,微信付款,支持信付款,支持微付款,支持微信款
             * refundTatio : 20
             * price : 20
             * priceDesc : 份
             * category : HHXX
             * serviceType : 1010001
             * type : 201
             * content : <p><span style="color: rgb(0, 176, 80);">的撒旦发射点发速读法</span></p><p><br/></p><p><span style="color: rgb(0, 176, 80);">阿萨德发射点发速读法</span></p><p><br/></p><p><br/></p><p><span style="border: 1px solid rgb(0, 0, 0);">啊速度发射点发射掉</span></p>
             * notice : <p><span style="color: rgb(255, 192, 0);">啊速度发射点发射掉</span></p><p><span style="color: rgb(255, 192, 0);">撒旦发射点发射点发速读法规划法规</span></p><p><br/></p><p>啊点发射点发速读法电视发射点发点发速读法速度分</p><p><br/></p><p><br/></p><p>啊速度发射点发射点发速读法速度发射<span style="color: rgb(255, 192, 0);"></span></p>
             * servicePhone : 121212
             * status : 1
             * isRecommoned : 1
             * isWeekChoice : 0
             * servicesImg : http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl
             * imgWidth : 539
             * imgHeight : 355
             * sortNum : 2
             * browseNumber : 6
             * isDelete : 0
             * createDate : 2016-03-01 15:43:01
             * createUserId : 1
             * updateDate : 2016-03-17 12:03:19
             * updateUserId : 1
             * provinceText :
             * cityText :
             * districtText :
             * surplusTime :
             */

            private ServiceBasicInfoEntity serviceBasicInfo;
            private String orderCode;
            private int evaluateType;
            private String evaluateContent;
            private String evaluateDate;
            private int evaluateUserId;
            private String firstImg;
            private String firstImgWidth;
            private String firstImgHeight;
            private String secondImg;
            private String secondImgWidth;
            private String secondImgHeight;
            private String thirdImg;
            private String thirdImgWidth;
            private String thirdImgHeight;
            /**
             * userId : 100010
             * uid : 121168
             * recommoned : 1
             * userNike : CharlesWl哈哈
             * userSex : 1
             * userBirthday :
             * backgroundImg : http://smart.image.alimmdn.com/app/test/2016-3-18/233A2171-16FC-446A-B64A-66660DDFD8DA
             * userBigImg : http://smart.image.alimmdn.com/oldImage/41355adc8eca493baa57a3474bc477e8.jpg
             * userBigWidth : 750
             * userBigHeight : 360
             * userSmallImg : http://smart.image.alimmdn.com/app/test/2016-2-25/B63F9EE5-717D-4E74-84FB-746F5F3C2510
             * userSmallWidth : 750
             * userSmallHeight : 750
             * loginAccountType : 0
             * loginTime : 2016-03-17 09:51:56
             * loginAccount : 18939963309
             * token :
             * isShutup : 0
             * shutupTime :
             * isBanned : 0
             * userAddress :
             * userPhone : 18939963309
             * userPhoneVersion :
             * province : 310000
             * provinceText :
             * district : 310110
             * districtText :
             * city : 310100
             * cityText :
             * userLevel : 1
             * userPresentation :
             * backImgWidth : 3024
             * backImgHeight : 4032
             * goldCount : 1
             * growthCount : 1
             * fans : 0
             * attentionCount : 1
             * isSign : 0
             * isAttention : 0
             * isEredar : 0
             * isLoginUser : 0
             * userRole : [{"eredarCode":0,"eredarName":"萌宝用户"}]
             */

            private UserBasicInfoEntity userBasicInfo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public ServiceBasicInfoEntity getServiceBasicInfo() {
                return serviceBasicInfo;
            }

            public void setServiceBasicInfo(ServiceBasicInfoEntity serviceBasicInfo) {
                this.serviceBasicInfo = serviceBasicInfo;
            }

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public int getEvaluateType() {
                return evaluateType;
            }

            public void setEvaluateType(int evaluateType) {
                this.evaluateType = evaluateType;
            }

            public String getEvaluateContent() {
                return evaluateContent;
            }

            public void setEvaluateContent(String evaluateContent) {
                this.evaluateContent = evaluateContent;
            }

            public String getEvaluateDate() {
                return evaluateDate;
            }

            public void setEvaluateDate(String evaluateDate) {
                this.evaluateDate = evaluateDate;
            }

            public int getEvaluateUserId() {
                return evaluateUserId;
            }

            public void setEvaluateUserId(int evaluateUserId) {
                this.evaluateUserId = evaluateUserId;
            }

            public String getFirstImg() {
                return firstImg;
            }

            public void setFirstImg(String firstImg) {
                this.firstImg = firstImg;
            }

            public String getFirstImgWidth() {
                return firstImgWidth;
            }

            public void setFirstImgWidth(String firstImgWidth) {
                this.firstImgWidth = firstImgWidth;
            }

            public String getFirstImgHeight() {
                return firstImgHeight;
            }

            public void setFirstImgHeight(String firstImgHeight) {
                this.firstImgHeight = firstImgHeight;
            }

            public String getSecondImg() {
                return secondImg;
            }

            public void setSecondImg(String secondImg) {
                this.secondImg = secondImg;
            }

            public String getSecondImgWidth() {
                return secondImgWidth;
            }

            public void setSecondImgWidth(String secondImgWidth) {
                this.secondImgWidth = secondImgWidth;
            }

            public String getSecondImgHeight() {
                return secondImgHeight;
            }

            public void setSecondImgHeight(String secondImgHeight) {
                this.secondImgHeight = secondImgHeight;
            }

            public String getThirdImg() {
                return thirdImg;
            }

            public void setThirdImg(String thirdImg) {
                this.thirdImg = thirdImg;
            }

            public String getThirdImgWidth() {
                return thirdImgWidth;
            }

            public void setThirdImgWidth(String thirdImgWidth) {
                this.thirdImgWidth = thirdImgWidth;
            }

            public String getThirdImgHeight() {
                return thirdImgHeight;
            }

            public void setThirdImgHeight(String thirdImgHeight) {
                this.thirdImgHeight = thirdImgHeight;
            }

            public UserBasicInfoEntity getUserBasicInfo() {
                return userBasicInfo;
            }

            public void setUserBasicInfo(UserBasicInfoEntity userBasicInfo) {
                this.userBasicInfo = userBasicInfo;
            }

            public static class ServiceBasicInfoEntity implements Parcelable {
                private int id;
                private String title;
                private String introduce;
                private String serviceTime;
                private int isCycle;
                private String startTime;
                private String endTime;
                private String applyStartTime;
                private String applyEndTime;
                private int peoples;
                private int realityPeoples;
                private int sellNum;
                private int province;
                private int city;
                private int district;
                private String address;
                private String mark;
                private int refundTatio;
                private String price;
                private String priceDesc;
                private String category;
                private int serviceType;
                private int type;
                private String content;
                private String notice;
                private String servicePhone;
                private int status;
                private int isRecommoned;
                private int isWeekChoice;
                private String servicesImg;
                private String imgWidth;
                private String imgHeight;
                private int sortNum;
                private int browseNumber;
                private int isDelete;
                private String createDate;
                private int createUserId;
                private String updateDate;
                private int updateUserId;
                private String provinceText;
                private String cityText;
                private String districtText;
                private String surplusTime;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIntroduce() {
                    return introduce;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public String getServiceTime() {
                    return serviceTime;
                }

                public void setServiceTime(String serviceTime) {
                    this.serviceTime = serviceTime;
                }

                public int getIsCycle() {
                    return isCycle;
                }

                public void setIsCycle(int isCycle) {
                    this.isCycle = isCycle;
                }

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public String getApplyStartTime() {
                    return applyStartTime;
                }

                public void setApplyStartTime(String applyStartTime) {
                    this.applyStartTime = applyStartTime;
                }

                public String getApplyEndTime() {
                    return applyEndTime;
                }

                public void setApplyEndTime(String applyEndTime) {
                    this.applyEndTime = applyEndTime;
                }

                public int getPeoples() {
                    return peoples;
                }

                public void setPeoples(int peoples) {
                    this.peoples = peoples;
                }

                public int getRealityPeoples() {
                    return realityPeoples;
                }

                public void setRealityPeoples(int realityPeoples) {
                    this.realityPeoples = realityPeoples;
                }

                public int getSellNum() {
                    return sellNum;
                }

                public void setSellNum(int sellNum) {
                    this.sellNum = sellNum;
                }

                public int getProvince() {
                    return province;
                }

                public void setProvince(int province) {
                    this.province = province;
                }

                public int getCity() {
                    return city;
                }

                public void setCity(int city) {
                    this.city = city;
                }

                public int getDistrict() {
                    return district;
                }

                public void setDistrict(int district) {
                    this.district = district;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getMark() {
                    return mark;
                }

                public void setMark(String mark) {
                    this.mark = mark;
                }

                public int getRefundTatio() {
                    return refundTatio;
                }

                public void setRefundTatio(int refundTatio) {
                    this.refundTatio = refundTatio;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getPriceDesc() {
                    return priceDesc;
                }

                public void setPriceDesc(String priceDesc) {
                    this.priceDesc = priceDesc;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public int getServiceType() {
                    return serviceType;
                }

                public void setServiceType(int serviceType) {
                    this.serviceType = serviceType;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getNotice() {
                    return notice;
                }

                public void setNotice(String notice) {
                    this.notice = notice;
                }

                public String getServicePhone() {
                    return servicePhone;
                }

                public void setServicePhone(String servicePhone) {
                    this.servicePhone = servicePhone;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getIsRecommoned() {
                    return isRecommoned;
                }

                public void setIsRecommoned(int isRecommoned) {
                    this.isRecommoned = isRecommoned;
                }

                public int getIsWeekChoice() {
                    return isWeekChoice;
                }

                public void setIsWeekChoice(int isWeekChoice) {
                    this.isWeekChoice = isWeekChoice;
                }

                public String getServicesImg() {
                    return servicesImg;
                }

                public void setServicesImg(String servicesImg) {
                    this.servicesImg = servicesImg;
                }

                public String getImgWidth() {
                    return imgWidth;
                }

                public void setImgWidth(String imgWidth) {
                    this.imgWidth = imgWidth;
                }

                public String getImgHeight() {
                    return imgHeight;
                }

                public void setImgHeight(String imgHeight) {
                    this.imgHeight = imgHeight;
                }

                public int getSortNum() {
                    return sortNum;
                }

                public void setSortNum(int sortNum) {
                    this.sortNum = sortNum;
                }

                public int getBrowseNumber() {
                    return browseNumber;
                }

                public void setBrowseNumber(int browseNumber) {
                    this.browseNumber = browseNumber;
                }

                public int getIsDelete() {
                    return isDelete;
                }

                public void setIsDelete(int isDelete) {
                    this.isDelete = isDelete;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }

                public int getCreateUserId() {
                    return createUserId;
                }

                public void setCreateUserId(int createUserId) {
                    this.createUserId = createUserId;
                }

                public String getUpdateDate() {
                    return updateDate;
                }

                public void setUpdateDate(String updateDate) {
                    this.updateDate = updateDate;
                }

                public int getUpdateUserId() {
                    return updateUserId;
                }

                public void setUpdateUserId(int updateUserId) {
                    this.updateUserId = updateUserId;
                }

                public String getProvinceText() {
                    return provinceText;
                }

                public void setProvinceText(String provinceText) {
                    this.provinceText = provinceText;
                }

                public String getCityText() {
                    return cityText;
                }

                public void setCityText(String cityText) {
                    this.cityText = cityText;
                }

                public String getDistrictText() {
                    return districtText;
                }

                public void setDistrictText(String districtText) {
                    this.districtText = districtText;
                }

                public String getSurplusTime() {
                    return surplusTime;
                }

                public void setSurplusTime(String surplusTime) {
                    this.surplusTime = surplusTime;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.id);
                    dest.writeString(this.title);
                    dest.writeString(this.introduce);
                    dest.writeString(this.serviceTime);
                    dest.writeInt(this.isCycle);
                    dest.writeString(this.startTime);
                    dest.writeString(this.endTime);
                    dest.writeString(this.applyStartTime);
                    dest.writeString(this.applyEndTime);
                    dest.writeInt(this.peoples);
                    dest.writeInt(this.realityPeoples);
                    dest.writeInt(this.sellNum);
                    dest.writeInt(this.province);
                    dest.writeInt(this.city);
                    dest.writeInt(this.district);
                    dest.writeString(this.address);
                    dest.writeString(this.mark);
                    dest.writeInt(this.refundTatio);
                    dest.writeString(this.price);
                    dest.writeString(this.priceDesc);
                    dest.writeString(this.category);
                    dest.writeInt(this.serviceType);
                    dest.writeInt(this.type);
                    dest.writeString(this.content);
                    dest.writeString(this.notice);
                    dest.writeString(this.servicePhone);
                    dest.writeInt(this.status);
                    dest.writeInt(this.isRecommoned);
                    dest.writeInt(this.isWeekChoice);
                    dest.writeString(this.servicesImg);
                    dest.writeString(this.imgWidth);
                    dest.writeString(this.imgHeight);
                    dest.writeInt(this.sortNum);
                    dest.writeInt(this.browseNumber);
                    dest.writeInt(this.isDelete);
                    dest.writeString(this.createDate);
                    dest.writeInt(this.createUserId);
                    dest.writeString(this.updateDate);
                    dest.writeInt(this.updateUserId);
                    dest.writeString(this.provinceText);
                    dest.writeString(this.cityText);
                    dest.writeString(this.districtText);
                    dest.writeString(this.surplusTime);
                }

                public ServiceBasicInfoEntity() {
                }

                protected ServiceBasicInfoEntity(Parcel in) {
                    this.id = in.readInt();
                    this.title = in.readString();
                    this.introduce = in.readString();
                    this.serviceTime = in.readString();
                    this.isCycle = in.readInt();
                    this.startTime = in.readString();
                    this.endTime = in.readString();
                    this.applyStartTime = in.readString();
                    this.applyEndTime = in.readString();
                    this.peoples = in.readInt();
                    this.realityPeoples = in.readInt();
                    this.sellNum = in.readInt();
                    this.province = in.readInt();
                    this.city = in.readInt();
                    this.district = in.readInt();
                    this.address = in.readString();
                    this.mark = in.readString();
                    this.refundTatio = in.readInt();
                    this.price = in.readString();
                    this.priceDesc = in.readString();
                    this.category = in.readString();
                    this.serviceType = in.readInt();
                    this.type = in.readInt();
                    this.content = in.readString();
                    this.notice = in.readString();
                    this.servicePhone = in.readString();
                    this.status = in.readInt();
                    this.isRecommoned = in.readInt();
                    this.isWeekChoice = in.readInt();
                    this.servicesImg = in.readString();
                    this.imgWidth = in.readString();
                    this.imgHeight = in.readString();
                    this.sortNum = in.readInt();
                    this.browseNumber = in.readInt();
                    this.isDelete = in.readInt();
                    this.createDate = in.readString();
                    this.createUserId = in.readInt();
                    this.updateDate = in.readString();
                    this.updateUserId = in.readInt();
                    this.provinceText = in.readString();
                    this.cityText = in.readString();
                    this.districtText = in.readString();
                    this.surplusTime = in.readString();
                }

                public static final Parcelable.Creator<ServiceBasicInfoEntity> CREATOR = new Parcelable.Creator<ServiceBasicInfoEntity>() {
                    @Override
                    public ServiceBasicInfoEntity createFromParcel(Parcel source) {
                        return new ServiceBasicInfoEntity(source);
                    }

                    @Override
                    public ServiceBasicInfoEntity[] newArray(int size) {
                        return new ServiceBasicInfoEntity[size];
                    }
                };
            }

            public static class UserBasicInfoEntity implements Parcelable {
                private int userId;
                private int uid;
                private int recommoned;
                private String userNike;
                private int userSex;
                private String userBirthday;
                private String backgroundImg;
                private String userBigImg;
                private String userBigWidth;
                private String userBigHeight;
                private String userSmallImg;
                private String userSmallWidth;
                private String userSmallHeight;
                private int loginAccountType;
                private String loginTime;
                private String loginAccount;
                private String token;
                private int isShutup;
                private String shutupTime;
                private int isBanned;
                private String userAddress;
                private String userPhone;
                private String userPhoneVersion;
                private int province;
                private String provinceText;
                private int district;
                private String districtText;
                private int city;
                private String cityText;
                private int userLevel;
                private String userPresentation;
                private int backImgWidth;
                private int backImgHeight;
                private int goldCount;
                private int growthCount;
                private int fans;
                private int attentionCount;
                private int isSign;
                private int isAttention;
                private int isEredar;
                private int isLoginUser;
                /**
                 * eredarCode : 0
                 * eredarName : 萌宝用户
                 */

                private List<UserRoleEntity> userRole;

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }

                public int getRecommoned() {
                    return recommoned;
                }

                public void setRecommoned(int recommoned) {
                    this.recommoned = recommoned;
                }

                public String getUserNike() {
                    return userNike;
                }

                public void setUserNike(String userNike) {
                    this.userNike = userNike;
                }

                public int getUserSex() {
                    return userSex;
                }

                public void setUserSex(int userSex) {
                    this.userSex = userSex;
                }

                public String getUserBirthday() {
                    return userBirthday;
                }

                public void setUserBirthday(String userBirthday) {
                    this.userBirthday = userBirthday;
                }

                public String getBackgroundImg() {
                    return backgroundImg;
                }

                public void setBackgroundImg(String backgroundImg) {
                    this.backgroundImg = backgroundImg;
                }

                public String getUserBigImg() {
                    return userBigImg;
                }

                public void setUserBigImg(String userBigImg) {
                    this.userBigImg = userBigImg;
                }

                public String getUserBigWidth() {
                    return userBigWidth;
                }

                public void setUserBigWidth(String userBigWidth) {
                    this.userBigWidth = userBigWidth;
                }

                public String getUserBigHeight() {
                    return userBigHeight;
                }

                public void setUserBigHeight(String userBigHeight) {
                    this.userBigHeight = userBigHeight;
                }

                public String getUserSmallImg() {
                    return userSmallImg;
                }

                public void setUserSmallImg(String userSmallImg) {
                    this.userSmallImg = userSmallImg;
                }

                public String getUserSmallWidth() {
                    return userSmallWidth;
                }

                public void setUserSmallWidth(String userSmallWidth) {
                    this.userSmallWidth = userSmallWidth;
                }

                public String getUserSmallHeight() {
                    return userSmallHeight;
                }

                public void setUserSmallHeight(String userSmallHeight) {
                    this.userSmallHeight = userSmallHeight;
                }

                public int getLoginAccountType() {
                    return loginAccountType;
                }

                public void setLoginAccountType(int loginAccountType) {
                    this.loginAccountType = loginAccountType;
                }

                public String getLoginTime() {
                    return loginTime;
                }

                public void setLoginTime(String loginTime) {
                    this.loginTime = loginTime;
                }

                public String getLoginAccount() {
                    return loginAccount;
                }

                public void setLoginAccount(String loginAccount) {
                    this.loginAccount = loginAccount;
                }

                public String getToken() {
                    return token;
                }

                public void setToken(String token) {
                    this.token = token;
                }

                public int getIsShutup() {
                    return isShutup;
                }

                public void setIsShutup(int isShutup) {
                    this.isShutup = isShutup;
                }

                public String getShutupTime() {
                    return shutupTime;
                }

                public void setShutupTime(String shutupTime) {
                    this.shutupTime = shutupTime;
                }

                public int getIsBanned() {
                    return isBanned;
                }

                public void setIsBanned(int isBanned) {
                    this.isBanned = isBanned;
                }

                public String getUserAddress() {
                    return userAddress;
                }

                public void setUserAddress(String userAddress) {
                    this.userAddress = userAddress;
                }

                public String getUserPhone() {
                    return userPhone;
                }

                public void setUserPhone(String userPhone) {
                    this.userPhone = userPhone;
                }

                public String getUserPhoneVersion() {
                    return userPhoneVersion;
                }

                public void setUserPhoneVersion(String userPhoneVersion) {
                    this.userPhoneVersion = userPhoneVersion;
                }

                public int getProvince() {
                    return province;
                }

                public void setProvince(int province) {
                    this.province = province;
                }

                public String getProvinceText() {
                    return provinceText;
                }

                public void setProvinceText(String provinceText) {
                    this.provinceText = provinceText;
                }

                public int getDistrict() {
                    return district;
                }

                public void setDistrict(int district) {
                    this.district = district;
                }

                public String getDistrictText() {
                    return districtText;
                }

                public void setDistrictText(String districtText) {
                    this.districtText = districtText;
                }

                public int getCity() {
                    return city;
                }

                public void setCity(int city) {
                    this.city = city;
                }

                public String getCityText() {
                    return cityText;
                }

                public void setCityText(String cityText) {
                    this.cityText = cityText;
                }

                public int getUserLevel() {
                    return userLevel;
                }

                public void setUserLevel(int userLevel) {
                    this.userLevel = userLevel;
                }

                public String getUserPresentation() {
                    return userPresentation;
                }

                public void setUserPresentation(String userPresentation) {
                    this.userPresentation = userPresentation;
                }

                public int getBackImgWidth() {
                    return backImgWidth;
                }

                public void setBackImgWidth(int backImgWidth) {
                    this.backImgWidth = backImgWidth;
                }

                public int getBackImgHeight() {
                    return backImgHeight;
                }

                public void setBackImgHeight(int backImgHeight) {
                    this.backImgHeight = backImgHeight;
                }

                public int getGoldCount() {
                    return goldCount;
                }

                public void setGoldCount(int goldCount) {
                    this.goldCount = goldCount;
                }

                public int getGrowthCount() {
                    return growthCount;
                }

                public void setGrowthCount(int growthCount) {
                    this.growthCount = growthCount;
                }

                public int getFans() {
                    return fans;
                }

                public void setFans(int fans) {
                    this.fans = fans;
                }

                public int getAttentionCount() {
                    return attentionCount;
                }

                public void setAttentionCount(int attentionCount) {
                    this.attentionCount = attentionCount;
                }

                public int getIsSign() {
                    return isSign;
                }

                public void setIsSign(int isSign) {
                    this.isSign = isSign;
                }

                public int getIsAttention() {
                    return isAttention;
                }

                public void setIsAttention(int isAttention) {
                    this.isAttention = isAttention;
                }

                public int getIsEredar() {
                    return isEredar;
                }

                public void setIsEredar(int isEredar) {
                    this.isEredar = isEredar;
                }

                public int getIsLoginUser() {
                    return isLoginUser;
                }

                public void setIsLoginUser(int isLoginUser) {
                    this.isLoginUser = isLoginUser;
                }

                public List<UserRoleEntity> getUserRole() {
                    return userRole;
                }

                public void setUserRole(List<UserRoleEntity> userRole) {
                    this.userRole = userRole;
                }

                public static class UserRoleEntity implements Parcelable {
                    private int eredarCode;
                    private String eredarName;

                    public int getEredarCode() {
                        return eredarCode;
                    }

                    public void setEredarCode(int eredarCode) {
                        this.eredarCode = eredarCode;
                    }

                    public String getEredarName() {
                        return eredarName;
                    }

                    public void setEredarName(String eredarName) {
                        this.eredarName = eredarName;
                    }


                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeInt(this.eredarCode);
                        dest.writeString(this.eredarName);
                    }

                    public UserRoleEntity() {
                    }

                    protected UserRoleEntity(Parcel in) {
                        this.eredarCode = in.readInt();
                        this.eredarName = in.readString();
                    }

                    public static final Parcelable.Creator<UserRoleEntity> CREATOR = new Parcelable.Creator<UserRoleEntity>() {
                        @Override
                        public UserRoleEntity createFromParcel(Parcel source) {
                            return new UserRoleEntity(source);
                        }

                        @Override
                        public UserRoleEntity[] newArray(int size) {
                            return new UserRoleEntity[size];
                        }
                    };
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.userId);
                    dest.writeInt(this.uid);
                    dest.writeInt(this.recommoned);
                    dest.writeString(this.userNike);
                    dest.writeInt(this.userSex);
                    dest.writeString(this.userBirthday);
                    dest.writeString(this.backgroundImg);
                    dest.writeString(this.userBigImg);
                    dest.writeString(this.userBigWidth);
                    dest.writeString(this.userBigHeight);
                    dest.writeString(this.userSmallImg);
                    dest.writeString(this.userSmallWidth);
                    dest.writeString(this.userSmallHeight);
                    dest.writeInt(this.loginAccountType);
                    dest.writeString(this.loginTime);
                    dest.writeString(this.loginAccount);
                    dest.writeString(this.token);
                    dest.writeInt(this.isShutup);
                    dest.writeString(this.shutupTime);
                    dest.writeInt(this.isBanned);
                    dest.writeString(this.userAddress);
                    dest.writeString(this.userPhone);
                    dest.writeString(this.userPhoneVersion);
                    dest.writeInt(this.province);
                    dest.writeString(this.provinceText);
                    dest.writeInt(this.district);
                    dest.writeString(this.districtText);
                    dest.writeInt(this.city);
                    dest.writeString(this.cityText);
                    dest.writeInt(this.userLevel);
                    dest.writeString(this.userPresentation);
                    dest.writeInt(this.backImgWidth);
                    dest.writeInt(this.backImgHeight);
                    dest.writeInt(this.goldCount);
                    dest.writeInt(this.growthCount);
                    dest.writeInt(this.fans);
                    dest.writeInt(this.attentionCount);
                    dest.writeInt(this.isSign);
                    dest.writeInt(this.isAttention);
                    dest.writeInt(this.isEredar);
                    dest.writeInt(this.isLoginUser);
                    dest.writeTypedList(userRole);
                }

                public UserBasicInfoEntity() {
                }

                protected UserBasicInfoEntity(Parcel in) {
                    this.userId = in.readInt();
                    this.uid = in.readInt();
                    this.recommoned = in.readInt();
                    this.userNike = in.readString();
                    this.userSex = in.readInt();
                    this.userBirthday = in.readString();
                    this.backgroundImg = in.readString();
                    this.userBigImg = in.readString();
                    this.userBigWidth = in.readString();
                    this.userBigHeight = in.readString();
                    this.userSmallImg = in.readString();
                    this.userSmallWidth = in.readString();
                    this.userSmallHeight = in.readString();
                    this.loginAccountType = in.readInt();
                    this.loginTime = in.readString();
                    this.loginAccount = in.readString();
                    this.token = in.readString();
                    this.isShutup = in.readInt();
                    this.shutupTime = in.readString();
                    this.isBanned = in.readInt();
                    this.userAddress = in.readString();
                    this.userPhone = in.readString();
                    this.userPhoneVersion = in.readString();
                    this.province = in.readInt();
                    this.provinceText = in.readString();
                    this.district = in.readInt();
                    this.districtText = in.readString();
                    this.city = in.readInt();
                    this.cityText = in.readString();
                    this.userLevel = in.readInt();
                    this.userPresentation = in.readString();
                    this.backImgWidth = in.readInt();
                    this.backImgHeight = in.readInt();
                    this.goldCount = in.readInt();
                    this.growthCount = in.readInt();
                    this.fans = in.readInt();
                    this.attentionCount = in.readInt();
                    this.isSign = in.readInt();
                    this.isAttention = in.readInt();
                    this.isEredar = in.readInt();
                    this.isLoginUser = in.readInt();
                    this.userRole = in.createTypedArrayList(UserRoleEntity.CREATOR);
                }

                public static final Parcelable.Creator<UserBasicInfoEntity> CREATOR = new Parcelable.Creator<UserBasicInfoEntity>() {
                    @Override
                    public UserBasicInfoEntity createFromParcel(Parcel source) {
                        return new UserBasicInfoEntity(source);
                    }

                    @Override
                    public UserBasicInfoEntity[] newArray(int size) {
                        return new UserBasicInfoEntity[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeParcelable(this.serviceBasicInfo, flags);
                dest.writeString(this.orderCode);
                dest.writeInt(this.evaluateType);
                dest.writeString(this.evaluateContent);
                dest.writeString(this.evaluateDate);
                dest.writeInt(this.evaluateUserId);
                dest.writeString(this.firstImg);
                dest.writeString(this.firstImgWidth);
                dest.writeString(this.firstImgHeight);
                dest.writeString(this.secondImg);
                dest.writeString(this.secondImgWidth);
                dest.writeString(this.secondImgHeight);
                dest.writeString(this.thirdImg);
                dest.writeString(this.thirdImgWidth);
                dest.writeString(this.thirdImgHeight);
                dest.writeParcelable(this.userBasicInfo, flags);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.id = in.readInt();
                this.serviceBasicInfo = in.readParcelable(ServiceBasicInfoEntity.class.getClassLoader());
                this.orderCode = in.readString();
                this.evaluateType = in.readInt();
                this.evaluateContent = in.readString();
                this.evaluateDate = in.readString();
                this.evaluateUserId = in.readInt();
                this.firstImg = in.readString();
                this.firstImgWidth = in.readString();
                this.firstImgHeight = in.readString();
                this.secondImg = in.readString();
                this.secondImgWidth = in.readString();
                this.secondImgHeight = in.readString();
                this.thirdImg = in.readString();
                this.thirdImgWidth = in.readString();
                this.thirdImgHeight = in.readString();
                this.userBasicInfo = in.readParcelable(UserBasicInfoEntity.class.getClassLoader());
            }

            public static final Parcelable.Creator<RowsEntity> CREATOR = new Parcelable.Creator<RowsEntity>() {
                @Override
                public RowsEntity createFromParcel(Parcel source) {
                    return new RowsEntity(source);
                }

                @Override
                public RowsEntity[] newArray(int size) {
                    return new RowsEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.page);
            dest.writeInt(this.records);
            dest.writeInt(this.total);
            dest.writeString(this.userdata);
            dest.writeTypedList(rows);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.page = in.readInt();
            this.records = in.readInt();
            this.total = in.readInt();
            this.userdata = in.readString();
            this.rows = in.createTypedArrayList(RowsEntity.CREATOR);
        }

        public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
            @Override
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            @Override
            public DataEntity[] newArray(int size) {
                return new DataEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.msg);
    }

    public EvaluateServiceModel() {
    }

    protected EvaluateServiceModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<EvaluateServiceModel> CREATOR = new Parcelable.Creator<EvaluateServiceModel>() {
        @Override
        public EvaluateServiceModel createFromParcel(Parcel source) {
            return new EvaluateServiceModel(source);
        }

        @Override
        public EvaluateServiceModel[] newArray(int size) {
            return new EvaluateServiceModel[size];
        }
    };


    public static void getEvaluateServiceRequest(int pager, int pagerSize, OkHttpClientManager.ResultCallback<EvaluateServiceModel> callback){
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.EVALUATE_SERVICE_LIST_REQUEST, params, callback);
    }

}
