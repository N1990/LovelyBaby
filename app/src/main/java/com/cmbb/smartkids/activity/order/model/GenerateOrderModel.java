package com.cmbb.smartkids.activity.order.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;

/**
 * Created by javon on 16/3/17.
 */
public class GenerateOrderModel implements Parcelable {


    /**
     * serviceId : 339
     * orderId : 103
     * userId : 104312
     * orderCode : 003391327381460014
     * validCode :
     * orderDate : 2016-03-16 20:52:18
     * servicePrice : 120
     * participate : 201
     * phone : 18221365268
     * userNike : javon
     * status : 1
     * isGold : 0
     * goldCount : 1
     * discountPrice :
     * refundTatio : 20
     * priceId : 2
     * specifications : 中份
     * buyCount : 2
     * price : 299.98
     * payment : 1
     * payDate :
     * isInvoice : 0
     * invoiceTitle :
     * serviceInfo : {"id":339,"title":"富文本编辑器测试","serviceTime":"","startTime":"2016-03-04 15:40:00","endTime":"2016-03-18 15:40:00","applyStartTime":"2016-03-01 15:40:00","applyEndTime":"2016-03-04 15:40:00","peoples":12,"timeLeft":"","collectCount":1,"realityPeoples":1,"sellNum":23,"province":440000,"provinceText":"","city":440100,"cityText":"广州","district":440103,"districtText":"","address":"23234阿萨德发射点发","mark":"支持退款,支持支付宝付款,微信付款,支持信付款,支持微付款,支持微信款","price":"120","priceDesc":"份","category":"","type":201,"specifications":"","content":"啊啊啊啊啊啊啊啊啊啊啊","notice":"<p>啊速度发射点发射掉<\/p><p>撒旦发射点发射点发速读法规划法规<\/p><p><br/><\/p><p>啊点发射点发速读法电视发射点发点发速读法速度分<\/p><p><br/><\/p><p><br/><\/p><p>啊速度发射点发射点发速读法速度发射<\/p>","servicePhone":"121212","status":1,"isRecommoned":1,"servicesImg":"http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl","imgWidth":"539","imgHeight":"355","sortNum":2,"browseNumber":6,"isCollect":1,"colletCount":1,"isReserve":1,"reserveText":"","surplusTime":"","userInfoList":"","serviceImgList":"","eventList":"","recommonedServiceList":"","priceList":""}
     * addressId : 1
     * receiveName : javonLiu
     * receivePhone : 18221365268
     * postCode : 111111
     * address : 辽宁省沈阳市和平区dsfommdghfsnddssa
     */

    private DataEntity data;
    /**
     * data : {"serviceId":339,"orderId":103,"userId":104312,"orderCode":"003391327381460014","validCode":"","orderDate":"2016-03-16 20:52:18","servicePrice":"120","participate":201,"phone":"18221365268","userNike":"javon","status":1,"isGold":0,"goldCount":1,"discountPrice":"","refundTatio":20,"priceId":2,"specifications":"中份","buyCount":2,"price":"299.98","payment":1,"payDate":"","isInvoice":0,"invoiceTitle":"","serviceInfo":{"id":339,"title":"富文本编辑器测试","serviceTime":"","startTime":"2016-03-04 15:40:00","endTime":"2016-03-18 15:40:00","applyStartTime":"2016-03-01 15:40:00","applyEndTime":"2016-03-04 15:40:00","peoples":12,"timeLeft":"","collectCount":1,"realityPeoples":1,"sellNum":23,"province":440000,"provinceText":"","city":440100,"cityText":"广州","district":440103,"districtText":"","address":"23234阿萨德发射点发","mark":"支持退款,支持支付宝付款,微信付款,支持信付款,支持微付款,支持微信款","price":"120","priceDesc":"份","category":"","type":201,"specifications":"","content":"啊啊啊啊啊啊啊啊啊啊啊","notice":"<p>啊速度发射点发射掉<\/p><p>撒旦发射点发射点发速读法规划法规<\/p><p><br/><\/p><p>啊点发射点发速读法电视发射点发点发速读法速度分<\/p><p><br/><\/p><p><br/><\/p><p>啊速度发射点发射点发速读法速度发射<\/p>","servicePhone":"121212","status":1,"isRecommoned":1,"servicesImg":"http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl","imgWidth":"539","imgHeight":"355","sortNum":2,"browseNumber":6,"isCollect":1,"colletCount":1,"isReserve":1,"reserveText":"","surplusTime":"","userInfoList":"","serviceImgList":"","eventList":"","recommonedServiceList":"","priceList":""},"addressId":1,"receiveName":"javonLiu","receivePhone":"18221365268","postCode":"111111","address":"辽宁省沈阳市和平区dsfommdghfsnddssa"}
     * msg : 数据加载成功
     */

    private String msg;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public static class DataEntity implements Parcelable {
        private int serviceId;
        private int orderId;
        private int userId;
        private String orderCode;
        private String validCode;
        private String orderDate;
        private String servicePrice;
        private int participate;
        private String phone;
        private String userNike;
        private int status;
        private int isGold;
        private int goldCount;
        private String discountPrice;
        private int refundTatio;
        private int priceId;
        private String specifications;
        private int buyCount;
        private String price;
        private int payment;
        private String payDate;
        private int isInvoice;
        private String invoiceTitle;
        /**
         * id : 339
         * title : 富文本编辑器测试
         * serviceTime :
         * startTime : 2016-03-04 15:40:00
         * endTime : 2016-03-18 15:40:00
         * applyStartTime : 2016-03-01 15:40:00
         * applyEndTime : 2016-03-04 15:40:00
         * peoples : 12
         * timeLeft :
         * collectCount : 1
         * realityPeoples : 1
         * sellNum : 23
         * province : 440000
         * provinceText :
         * city : 440100
         * cityText : 广州
         * district : 440103
         * districtText :
         * address : 23234阿萨德发射点发
         * mark : 支持退款,支持支付宝付款,微信付款,支持信付款,支持微付款,支持微信款
         * price : 120
         * priceDesc : 份
         * category :
         * type : 201
         * specifications :
         * content : 啊啊啊啊啊啊啊啊啊啊啊
         * notice : <p>啊速度发射点发射掉</p><p>撒旦发射点发射点发速读法规划法规</p><p><br/></p><p>啊点发射点发速读法电视发射点发点发速读法速度分</p><p><br/></p><p><br/></p><p>啊速度发射点发射点发速读法速度发射</p>
         * servicePhone : 121212
         * status : 1
         * isRecommoned : 1
         * servicesImg : http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl
         * imgWidth : 539
         * imgHeight : 355
         * sortNum : 2
         * browseNumber : 6
         * isCollect : 1
         * colletCount : 1
         * isReserve : 1
         * reserveText :
         * surplusTime :
         * userInfoList :
         * serviceImgList :
         * eventList :
         * recommonedServiceList :
         * priceList :
         */

        private ServiceInfoEntity serviceInfo;
        private int addressId;
        private String receiveName;
        private String receivePhone;
        private String postCode;
        private String address;

        public void setServiceId(int serviceId) {
            this.serviceId = serviceId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public void setValidCode(String validCode) {
            this.validCode = validCode;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public void setServicePrice(String servicePrice) {
            this.servicePrice = servicePrice;
        }

        public void setParticipate(int participate) {
            this.participate = participate;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setUserNike(String userNike) {
            this.userNike = userNike;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setIsGold(int isGold) {
            this.isGold = isGold;
        }

        public void setGoldCount(int goldCount) {
            this.goldCount = goldCount;
        }

        public void setDiscountPrice(String discountPrice) {
            this.discountPrice = discountPrice;
        }

        public void setRefundTatio(int refundTatio) {
            this.refundTatio = refundTatio;
        }

        public void setPriceId(int priceId) {
            this.priceId = priceId;
        }

        public void setSpecifications(String specifications) {
            this.specifications = specifications;
        }

        public void setBuyCount(int buyCount) {
            this.buyCount = buyCount;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setPayment(int payment) {
            this.payment = payment;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public void setIsInvoice(int isInvoice) {
            this.isInvoice = isInvoice;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }

        public void setServiceInfo(ServiceInfoEntity serviceInfo) {
            this.serviceInfo = serviceInfo;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public void setReceiveName(String receiveName) {
            this.receiveName = receiveName;
        }

        public void setReceivePhone(String receivePhone) {
            this.receivePhone = receivePhone;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getServiceId() {
            return serviceId;
        }

        public int getOrderId() {
            return orderId;
        }

        public int getUserId() {
            return userId;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public String getValidCode() {
            return validCode;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public String getServicePrice() {
            return servicePrice;
        }

        public int getParticipate() {
            return participate;
        }

        public String getPhone() {
            return phone;
        }

        public String getUserNike() {
            return userNike;
        }

        public int getStatus() {
            return status;
        }

        public int getIsGold() {
            return isGold;
        }

        public int getGoldCount() {
            return goldCount;
        }

        public String getDiscountPrice() {
            return discountPrice;
        }

        public int getRefundTatio() {
            return refundTatio;
        }

        public int getPriceId() {
            return priceId;
        }

        public String getSpecifications() {
            return specifications;
        }

        public int getBuyCount() {
            return buyCount;
        }

        public String getPrice() {
            return price;
        }

        public int getPayment() {
            return payment;
        }

        public String getPayDate() {
            return payDate;
        }

        public int getIsInvoice() {
            return isInvoice;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public ServiceInfoEntity getServiceInfo() {
            return serviceInfo;
        }

        public int getAddressId() {
            return addressId;
        }

        public String getReceiveName() {
            return receiveName;
        }

        public String getReceivePhone() {
            return receivePhone;
        }

        public String getPostCode() {
            return postCode;
        }

        public String getAddress() {
            return address;
        }

        public static class ServiceInfoEntity implements Parcelable {
            private int id;
            private String title;
            private String serviceTime;
            private String startTime;
            private String endTime;
            private String applyStartTime;
            private String applyEndTime;
            private int peoples;
            private String timeLeft;
            private int collectCount;
            private int realityPeoples;
            private int sellNum;
            private int province;
            private String provinceText;
            private int city;
            private String cityText;
            private int district;
            private String districtText;
            private String address;
            private String mark;
            private String price;
            private String priceDesc;
            private String category;
            private int type;
            private String specifications;
            private String content;
            private String notice;
            private String servicePhone;
            private int status;
            private int isRecommoned;
            private String servicesImg;
            private String imgWidth;
            private String imgHeight;
            private int sortNum;
            private int browseNumber;
            private int isCollect;
            private int colletCount;
            private int isReserve;
            private String reserveText;
            private String surplusTime;
            private String userInfoList;
            private String serviceImgList;
            private String eventList;
            private String recommonedServiceList;
            private String priceList;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setServiceTime(String serviceTime) {
                this.serviceTime = serviceTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public void setApplyStartTime(String applyStartTime) {
                this.applyStartTime = applyStartTime;
            }

            public void setApplyEndTime(String applyEndTime) {
                this.applyEndTime = applyEndTime;
            }

            public void setPeoples(int peoples) {
                this.peoples = peoples;
            }

            public void setTimeLeft(String timeLeft) {
                this.timeLeft = timeLeft;
            }

            public void setCollectCount(int collectCount) {
                this.collectCount = collectCount;
            }

            public void setRealityPeoples(int realityPeoples) {
                this.realityPeoples = realityPeoples;
            }

            public void setSellNum(int sellNum) {
                this.sellNum = sellNum;
            }

            public void setProvince(int province) {
                this.province = province;
            }

            public void setProvinceText(String provinceText) {
                this.provinceText = provinceText;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public void setCityText(String cityText) {
                this.cityText = cityText;
            }

            public void setDistrict(int district) {
                this.district = district;
            }

            public void setDistrictText(String districtText) {
                this.districtText = districtText;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setPriceDesc(String priceDesc) {
                this.priceDesc = priceDesc;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setSpecifications(String specifications) {
                this.specifications = specifications;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public void setServicePhone(String servicePhone) {
                this.servicePhone = servicePhone;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setIsRecommoned(int isRecommoned) {
                this.isRecommoned = isRecommoned;
            }

            public void setServicesImg(String servicesImg) {
                this.servicesImg = servicesImg;
            }

            public void setImgWidth(String imgWidth) {
                this.imgWidth = imgWidth;
            }

            public void setImgHeight(String imgHeight) {
                this.imgHeight = imgHeight;
            }

            public void setSortNum(int sortNum) {
                this.sortNum = sortNum;
            }

            public void setBrowseNumber(int browseNumber) {
                this.browseNumber = browseNumber;
            }

            public void setIsCollect(int isCollect) {
                this.isCollect = isCollect;
            }

            public void setColletCount(int colletCount) {
                this.colletCount = colletCount;
            }

            public void setIsReserve(int isReserve) {
                this.isReserve = isReserve;
            }

            public void setReserveText(String reserveText) {
                this.reserveText = reserveText;
            }

            public void setSurplusTime(String surplusTime) {
                this.surplusTime = surplusTime;
            }

            public void setUserInfoList(String userInfoList) {
                this.userInfoList = userInfoList;
            }

            public void setServiceImgList(String serviceImgList) {
                this.serviceImgList = serviceImgList;
            }

            public void setEventList(String eventList) {
                this.eventList = eventList;
            }

            public void setRecommonedServiceList(String recommonedServiceList) {
                this.recommonedServiceList = recommonedServiceList;
            }

            public void setPriceList(String priceList) {
                this.priceList = priceList;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getServiceTime() {
                return serviceTime;
            }

            public String getStartTime() {
                return startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public String getApplyStartTime() {
                return applyStartTime;
            }

            public String getApplyEndTime() {
                return applyEndTime;
            }

            public int getPeoples() {
                return peoples;
            }

            public String getTimeLeft() {
                return timeLeft;
            }

            public int getCollectCount() {
                return collectCount;
            }

            public int getRealityPeoples() {
                return realityPeoples;
            }

            public int getSellNum() {
                return sellNum;
            }

            public int getProvince() {
                return province;
            }

            public String getProvinceText() {
                return provinceText;
            }

            public int getCity() {
                return city;
            }

            public String getCityText() {
                return cityText;
            }

            public int getDistrict() {
                return district;
            }

            public String getDistrictText() {
                return districtText;
            }

            public String getAddress() {
                return address;
            }

            public String getMark() {
                return mark;
            }

            public String getPrice() {
                return price;
            }

            public String getPriceDesc() {
                return priceDesc;
            }

            public String getCategory() {
                return category;
            }

            public int getType() {
                return type;
            }

            public String getSpecifications() {
                return specifications;
            }

            public String getContent() {
                return content;
            }

            public String getNotice() {
                return notice;
            }

            public String getServicePhone() {
                return servicePhone;
            }

            public int getStatus() {
                return status;
            }

            public int getIsRecommoned() {
                return isRecommoned;
            }

            public String getServicesImg() {
                return servicesImg;
            }

            public String getImgWidth() {
                return imgWidth;
            }

            public String getImgHeight() {
                return imgHeight;
            }

            public int getSortNum() {
                return sortNum;
            }

            public int getBrowseNumber() {
                return browseNumber;
            }

            public int getIsCollect() {
                return isCollect;
            }

            public int getColletCount() {
                return colletCount;
            }

            public int getIsReserve() {
                return isReserve;
            }

            public String getReserveText() {
                return reserveText;
            }

            public String getSurplusTime() {
                return surplusTime;
            }

            public String getUserInfoList() {
                return userInfoList;
            }

            public String getServiceImgList() {
                return serviceImgList;
            }

            public String getEventList() {
                return eventList;
            }

            public String getRecommonedServiceList() {
                return recommonedServiceList;
            }

            public String getPriceList() {
                return priceList;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.title);
                dest.writeString(this.serviceTime);
                dest.writeString(this.startTime);
                dest.writeString(this.endTime);
                dest.writeString(this.applyStartTime);
                dest.writeString(this.applyEndTime);
                dest.writeInt(this.peoples);
                dest.writeString(this.timeLeft);
                dest.writeInt(this.collectCount);
                dest.writeInt(this.realityPeoples);
                dest.writeInt(this.sellNum);
                dest.writeInt(this.province);
                dest.writeString(this.provinceText);
                dest.writeInt(this.city);
                dest.writeString(this.cityText);
                dest.writeInt(this.district);
                dest.writeString(this.districtText);
                dest.writeString(this.address);
                dest.writeString(this.mark);
                dest.writeString(this.price);
                dest.writeString(this.priceDesc);
                dest.writeString(this.category);
                dest.writeInt(this.type);
                dest.writeString(this.specifications);
                dest.writeString(this.content);
                dest.writeString(this.notice);
                dest.writeString(this.servicePhone);
                dest.writeInt(this.status);
                dest.writeInt(this.isRecommoned);
                dest.writeString(this.servicesImg);
                dest.writeString(this.imgWidth);
                dest.writeString(this.imgHeight);
                dest.writeInt(this.sortNum);
                dest.writeInt(this.browseNumber);
                dest.writeInt(this.isCollect);
                dest.writeInt(this.colletCount);
                dest.writeInt(this.isReserve);
                dest.writeString(this.reserveText);
                dest.writeString(this.surplusTime);
                dest.writeString(this.userInfoList);
                dest.writeString(this.serviceImgList);
                dest.writeString(this.eventList);
                dest.writeString(this.recommonedServiceList);
                dest.writeString(this.priceList);
            }

            public ServiceInfoEntity() {
            }

            protected ServiceInfoEntity(Parcel in) {
                this.id = in.readInt();
                this.title = in.readString();
                this.serviceTime = in.readString();
                this.startTime = in.readString();
                this.endTime = in.readString();
                this.applyStartTime = in.readString();
                this.applyEndTime = in.readString();
                this.peoples = in.readInt();
                this.timeLeft = in.readString();
                this.collectCount = in.readInt();
                this.realityPeoples = in.readInt();
                this.sellNum = in.readInt();
                this.province = in.readInt();
                this.provinceText = in.readString();
                this.city = in.readInt();
                this.cityText = in.readString();
                this.district = in.readInt();
                this.districtText = in.readString();
                this.address = in.readString();
                this.mark = in.readString();
                this.price = in.readString();
                this.priceDesc = in.readString();
                this.category = in.readString();
                this.type = in.readInt();
                this.specifications = in.readString();
                this.content = in.readString();
                this.notice = in.readString();
                this.servicePhone = in.readString();
                this.status = in.readInt();
                this.isRecommoned = in.readInt();
                this.servicesImg = in.readString();
                this.imgWidth = in.readString();
                this.imgHeight = in.readString();
                this.sortNum = in.readInt();
                this.browseNumber = in.readInt();
                this.isCollect = in.readInt();
                this.colletCount = in.readInt();
                this.isReserve = in.readInt();
                this.reserveText = in.readString();
                this.surplusTime = in.readString();
                this.userInfoList = in.readString();
                this.serviceImgList = in.readString();
                this.eventList = in.readString();
                this.recommonedServiceList = in.readString();
                this.priceList = in.readString();
            }

            public static final Parcelable.Creator<ServiceInfoEntity> CREATOR = new Parcelable.Creator<ServiceInfoEntity>() {
                public ServiceInfoEntity createFromParcel(Parcel source) {
                    return new ServiceInfoEntity(source);
                }

                public ServiceInfoEntity[] newArray(int size) {
                    return new ServiceInfoEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.serviceId);
            dest.writeInt(this.orderId);
            dest.writeInt(this.userId);
            dest.writeString(this.orderCode);
            dest.writeString(this.validCode);
            dest.writeString(this.orderDate);
            dest.writeString(this.servicePrice);
            dest.writeInt(this.participate);
            dest.writeString(this.phone);
            dest.writeString(this.userNike);
            dest.writeInt(this.status);
            dest.writeInt(this.isGold);
            dest.writeInt(this.goldCount);
            dest.writeString(this.discountPrice);
            dest.writeInt(this.refundTatio);
            dest.writeInt(this.priceId);
            dest.writeString(this.specifications);
            dest.writeInt(this.buyCount);
            dest.writeString(this.price);
            dest.writeInt(this.payment);
            dest.writeString(this.payDate);
            dest.writeInt(this.isInvoice);
            dest.writeString(this.invoiceTitle);
            dest.writeParcelable(this.serviceInfo, 0);
            dest.writeInt(this.addressId);
            dest.writeString(this.receiveName);
            dest.writeString(this.receivePhone);
            dest.writeString(this.postCode);
            dest.writeString(this.address);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.serviceId = in.readInt();
            this.orderId = in.readInt();
            this.userId = in.readInt();
            this.orderCode = in.readString();
            this.validCode = in.readString();
            this.orderDate = in.readString();
            this.servicePrice = in.readString();
            this.participate = in.readInt();
            this.phone = in.readString();
            this.userNike = in.readString();
            this.status = in.readInt();
            this.isGold = in.readInt();
            this.goldCount = in.readInt();
            this.discountPrice = in.readString();
            this.refundTatio = in.readInt();
            this.priceId = in.readInt();
            this.specifications = in.readString();
            this.buyCount = in.readInt();
            this.price = in.readString();
            this.payment = in.readInt();
            this.payDate = in.readString();
            this.isInvoice = in.readInt();
            this.invoiceTitle = in.readString();
            this.serviceInfo = in.readParcelable(ServiceInfoEntity.class.getClassLoader());
            this.addressId = in.readInt();
            this.receiveName = in.readString();
            this.receivePhone = in.readString();
            this.postCode = in.readString();
            this.address = in.readString();
        }

        public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

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
        dest.writeParcelable(this.data, 0);
        dest.writeString(this.msg);
    }

    public GenerateOrderModel() {
    }

    protected GenerateOrderModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<GenerateOrderModel> CREATOR = new Parcelable.Creator<GenerateOrderModel>() {
        public GenerateOrderModel createFromParcel(Parcel source) {
            return new GenerateOrderModel(source);
        }

        public GenerateOrderModel[] newArray(int size) {
            return new GenerateOrderModel[size];
        }
    };


    /**
     * 获取订单详情
     * @param orderCode
     * @param callback
     */
    public static void getOrderDetailRequest(String orderCode, OkHttpClientManager.ResultCallback<GenerateOrderModel> callback){
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("orderCode", orderCode);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.ORDER_DETAIL_REQUEST, params, callback);
    }
}
