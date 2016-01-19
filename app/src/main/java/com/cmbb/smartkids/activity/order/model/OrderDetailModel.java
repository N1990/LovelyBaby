package com.cmbb.smartkids.activity.order.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/23 15:14
 */
public class OrderDetailModel implements Parcelable {


    /**
     * serviceId : 278
     * orderId : 32
     * userId : 104312
     * orderCode : 002786584177090005
     * orderDate : 2015-12-09 18:53:38
     * servicePrice : 0.01
     * phone : 18221365268
     * userNike : javon
     * status : 7
     * isGold : 1
     * goldCount : 12
     * discountPrice :
     * price : 0.01
     * payment : 1
     * payDate :
     * isInvoice : 0
     * invoiceTitle :
     * serviceInfo : {"id":278,"title":"新业务测试1","startTime":"2015-12-10 11:12:00","endTime":"2015-12-10 11:12:00","applyStartTime":"2015-12-08 18:50:00","applyEndTime":"2015-12-10 11:12:00","peoples":122,"timeLeft":"","collectCount":12,"realityPeoples":2,"province":110000,"provinceText":"北京","city":110100,"cityText":"北京市","district":110102,"districtText":"西城区","address":"11111111","price":"0.01","type":202,"content":"xxxxxx","servicePhone":"121212","status":3,"isRecommoned":0,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-12-09/servicesImgFile_ZjQwMmY3ZjQtZjQ1ZC00YzRhLThmMWYtZGUyYzU1MGNkNjJj","imgWidth":"1024","imgHeight":"768","sortNum":0,"browseNumber":17,"isCollect":1,"colletCount":12,"isReserve":12,"surplusTime":"","userInfoList":"","serviceImgList":"","eventList":""}
     * addressId : 1
     * receiveName :
     * receivePhone :
     * postCode :
     * address :
     */

    private DataEntity data;
    /**
     * data : {"serviceId":278,"orderId":32,"userId":104312,"orderCode":"002786584177090005","orderDate":"2015-12-09 18:53:38","servicePrice":"0.01","phone":"18221365268","userNike":"javon","status":7,"isGold":1,"goldCount":12,"discountPrice":"","price":"0.01","payment":1,"payDate":"","isInvoice":0,"invoiceTitle":"","serviceInfo":{"id":278,"title":"新业务测试1","startTime":"2015-12-10 11:12:00","endTime":"2015-12-10 11:12:00","applyStartTime":"2015-12-08 18:50:00","applyEndTime":"2015-12-10 11:12:00","peoples":122,"timeLeft":"","collectCount":12,"realityPeoples":2,"province":110000,"provinceText":"北京","city":110100,"cityText":"北京市","district":110102,"districtText":"西城区","address":"11111111","price":"0.01","type":202,"content":"xxxxxx","servicePhone":"121212","status":3,"isRecommoned":0,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-12-09/servicesImgFile_ZjQwMmY3ZjQtZjQ1ZC00YzRhLThmMWYtZGUyYzU1MGNkNjJj","imgWidth":"1024","imgHeight":"768","sortNum":0,"browseNumber":17,"isCollect":1,"colletCount":12,"isReserve":12,"surplusTime":"","userInfoList":"","serviceImgList":"","eventList":""},"addressId":1,"receiveName":"","receivePhone":"","postCode":"","address":""}
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
        private String orderDate;
        private String servicePrice;
        private String phone;
        private String userNike;
        private int status;
        private int isGold;
        private int goldCount;
        private String discountPrice;
        private String price;
        private int payment;
        private String payDate;
        private int isInvoice;
        private String invoiceTitle;
        /**
         * id : 278
         * title : 新业务测试1
         * startTime : 2015-12-10 11:12:00
         * endTime : 2015-12-10 11:12:00
         * applyStartTime : 2015-12-08 18:50:00
         * applyEndTime : 2015-12-10 11:12:00
         * peoples : 122
         * timeLeft :
         * collectCount : 12
         * realityPeoples : 2
         * province : 110000
         * provinceText : 北京
         * city : 110100
         * cityText : 北京市
         * district : 110102
         * districtText : 西城区
         * address : 11111111
         * price : 0.01
         * type : 202
         * content : xxxxxx
         * servicePhone : 121212
         * status : 3
         * isRecommoned : 0
         * servicesImg : http://smart.image.alimmdn.com/system/image/2015-12-09/servicesImgFile_ZjQwMmY3ZjQtZjQ1ZC00YzRhLThmMWYtZGUyYzU1MGNkNjJj
         * imgWidth : 1024
         * imgHeight : 768
         * sortNum : 0
         * browseNumber : 17
         * isCollect : 1
         * colletCount : 12
         * isReserve : 12
         * surplusTime :
         * userInfoList :
         * serviceImgList :
         * eventList :
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

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public void setServicePrice(String servicePrice) {
            this.servicePrice = servicePrice;
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

        public String getOrderDate() {
            return orderDate;
        }

        public String getServicePrice() {
            return servicePrice;
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
            private String startTime;
            private String endTime;
            private String applyStartTime;
            private String applyEndTime;
            private int peoples;
            private String timeLeft;
            private int collectCount;
            private int realityPeoples;
            private int province;
            private String provinceText;
            private int city;
            private String cityText;
            private int district;
            private String districtText;
            private String address;
            private String price;
            private int type;
            private String content;
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
            private String surplusTime;
            private String userInfoList;
            private String serviceImgList;
            private String eventList;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public void setPrice(String price) {
                this.price = price;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setContent(String content) {
                this.content = content;
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

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
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

            public String getPrice() {
                return price;
            }

            public int getType() {
                return type;
            }

            public String getContent() {
                return content;
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


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.title);
                dest.writeString(this.startTime);
                dest.writeString(this.endTime);
                dest.writeString(this.applyStartTime);
                dest.writeString(this.applyEndTime);
                dest.writeInt(this.peoples);
                dest.writeString(this.timeLeft);
                dest.writeInt(this.collectCount);
                dest.writeInt(this.realityPeoples);
                dest.writeInt(this.province);
                dest.writeString(this.provinceText);
                dest.writeInt(this.city);
                dest.writeString(this.cityText);
                dest.writeInt(this.district);
                dest.writeString(this.districtText);
                dest.writeString(this.address);
                dest.writeString(this.price);
                dest.writeInt(this.type);
                dest.writeString(this.content);
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
                dest.writeString(this.surplusTime);
                dest.writeString(this.userInfoList);
                dest.writeString(this.serviceImgList);
                dest.writeString(this.eventList);
            }

            public ServiceInfoEntity() {
            }

            protected ServiceInfoEntity(Parcel in) {
                this.id = in.readInt();
                this.title = in.readString();
                this.startTime = in.readString();
                this.endTime = in.readString();
                this.applyStartTime = in.readString();
                this.applyEndTime = in.readString();
                this.peoples = in.readInt();
                this.timeLeft = in.readString();
                this.collectCount = in.readInt();
                this.realityPeoples = in.readInt();
                this.province = in.readInt();
                this.provinceText = in.readString();
                this.city = in.readInt();
                this.cityText = in.readString();
                this.district = in.readInt();
                this.districtText = in.readString();
                this.address = in.readString();
                this.price = in.readString();
                this.type = in.readInt();
                this.content = in.readString();
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
                this.surplusTime = in.readString();
                this.userInfoList = in.readString();
                this.serviceImgList = in.readString();
                this.eventList = in.readString();
            }

            public static final Creator<ServiceInfoEntity> CREATOR = new Creator<ServiceInfoEntity>() {
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
            dest.writeString(this.orderDate);
            dest.writeString(this.servicePrice);
            dest.writeString(this.phone);
            dest.writeString(this.userNike);
            dest.writeInt(this.status);
            dest.writeInt(this.isGold);
            dest.writeInt(this.goldCount);
            dest.writeString(this.discountPrice);
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
            this.orderDate = in.readString();
            this.servicePrice = in.readString();
            this.phone = in.readString();
            this.userNike = in.readString();
            this.status = in.readInt();
            this.isGold = in.readInt();
            this.goldCount = in.readInt();
            this.discountPrice = in.readString();
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

        public static final Creator<DataEntity> CREATOR = new Creator<DataEntity>() {
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

    public OrderDetailModel() {
    }

    protected OrderDetailModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Creator<OrderDetailModel> CREATOR = new Creator<OrderDetailModel>() {
        public OrderDetailModel createFromParcel(Parcel source) {
            return new OrderDetailModel(source);
        }

        public OrderDetailModel[] newArray(int size) {
            return new OrderDetailModel[size];
        }
    };
}
