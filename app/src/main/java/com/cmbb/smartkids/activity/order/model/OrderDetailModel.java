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
     * status : 1
     * data : {"orderId":null,"userId":9,"orderCode":"8314430613250874470","orderDate":"2015-09-24 10:23:27","servicePrice":0,"phone":"18221365268","userNike":"666","status":2,"isGold":0,"goldCount":null,"discountPrice":null,"price":0,"payment":null,"isInvoice":0,"invoiceTitle":null,"serviceInfo":{"id":14,"title":"萌宝驾到14！","startTime":"2015-09-22 00:00:00","endTime":"2015-11-30 00:00:00","applyStartTime":"2015-08-01 00:00:00","applyEndTime":"2015-09-10 00:00:00","peoples":32,"realityPeoples":10,"province":120000,"provinceText":null,"city":120100,"cityText":null,"district":120101,"districtText":null,"address":"上海市杨浦区飞虹路568弄13号","price":0,"type":null,"content":"减肥索科洛夫结束了发动机是狂风巨浪的数据分类考试的，附近的索科洛夫经历多少","servicePhone":"13818155072","status":0,"isRecommoned":1,"servicesImg":"http://fuck.image.alimmdn.com/2015-9-18/image_2a3320c26e70426cb07a5afc3fe9bf3c","imgWidth":600,"imgHeight":400,"sortNum":null,"isCollect":null,"isReserve":null,"userInfoList":null,"serviceImgList":null}}
     * msg : 数据加载成功
     */

    private int status;
    private DataEntity data;
    private String msg;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public static class DataEntity implements Parcelable {
        /**
         * serviceId : 92
         * orderId : null
         * userId : 9
         * orderCode : 8314430613250874470
         * orderDate : 2015-09-24 10:23:27
         * servicePrice : 0
         * phone : 18221365268
         * userNike : 666
         * status : 2
         * isGold : 0
         * goldCount : null
         * discountPrice : null
         * price : 0
         * payment : null
         * isInvoice : 0
         * invoiceTitle : null
         * serviceInfo : {"id":14,"title":"萌宝驾到14！","startTime":"2015-09-22 00:00:00","endTime":"2015-11-30 00:00:00","applyStartTime":"2015-08-01 00:00:00","applyEndTime":"2015-09-10 00:00:00","peoples":32,"realityPeoples":10,"province":120000,"provinceText":null,"city":120100,"cityText":null,"district":120101,"districtText":null,"address":"上海市杨浦区飞虹路568弄13号","price":0,"type":null,"content":"减肥索科洛夫结束了发动机是狂风巨浪的数据分类考试的，附近的索科洛夫经历多少","servicePhone":"13818155072","status":0,"isRecommoned":1,"servicesImg":"http://fuck.image.alimmdn.com/2015-9-18/image_2a3320c26e70426cb07a5afc3fe9bf3c","imgWidth":600,"imgHeight":400,"sortNum":null,"isCollect":null,"isReserve":null,"userInfoList":null,"serviceImgList":null}
         */
        private String serviceId;
        private String orderId;
        private int userId;
        private String orderCode;
        private String orderDate;
        private String servicePrice;
        private String phone;
        private String userNike;
        private int status;
        private int isGold;
        private String goldCount;
        private String discountPrice;
        private String price;
        private String payment;
        private int isInvoice;
        private String invoiceTitle;
        private ServiceInfoEntity serviceInfo;

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public void setOrderId(String orderId) {
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

        public void setGoldCount(String goldCount) {
            this.goldCount = goldCount;
        }

        public void setDiscountPrice(String discountPrice) {
            this.discountPrice = discountPrice;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setPayment(String payment) {
            this.payment = payment;
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

        public String getOrderId() {
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

        public String getGoldCount() {
            return goldCount;
        }

        public String getDiscountPrice() {
            return discountPrice;
        }

        public String getPrice() {
            return price;
        }

        public String getPayment() {
            return payment;
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

        public static class ServiceInfoEntity implements Parcelable {
            /**
             * id : 14
             * title : 萌宝驾到14！
             * startTime : 2015-09-22 00:00:00
             * endTime : 2015-11-30 00:00:00
             * applyStartTime : 2015-08-01 00:00:00
             * applyEndTime : 2015-09-10 00:00:00
             * peoples : 32
             * realityPeoples : 10
             * province : 120000
             * provinceText : null
             * city : 120100
             * cityText : null
             * district : 120101
             * districtText : null
             * address : 上海市杨浦区飞虹路568弄13号
             * price : 0
             * type : null
             * content : 减肥索科洛夫结束了发动机是狂风巨浪的数据分类考试的，附近的索科洛夫经历多少
             * servicePhone : 13818155072
             * status : 0
             * isRecommoned : 1
             * servicesImg : http://fuck.image.alimmdn.com/2015-9-18/image_2a3320c26e70426cb07a5afc3fe9bf3c
             * imgWidth : 600
             * imgHeight : 400
             * sortNum : null
             * isCollect : null
             * isReserve : null
             * userInfoList : null
             * serviceImgList : null
             */

            private int id;
            private String title;
            private String startTime;
            private String endTime;
            private String applyStartTime;
            private String applyEndTime;
            private int peoples;
            private int realityPeoples;
            private int province;
            private String provinceText;
            private int city;
            private String cityText;
            private int district;
            private String districtText;
            private String address;
            private double price;
            private String type;
            private String content;
            private String servicePhone;
            private int status;
            private int isRecommoned;
            private String servicesImg;
            private String imgWidth;
            private String imgHeight;
            private String sortNum;
            private String isCollect;
            private String isReserve;
            private String userInfoList;
            private String serviceImgList;

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

            public void setPrice(double price) {
                this.price = price;
            }

            public void setType(String type) {
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

            public void setSortNum(String sortNum) {
                this.sortNum = sortNum;
            }

            public void setIsCollect(String isCollect) {
                this.isCollect = isCollect;
            }

            public void setIsReserve(String isReserve) {
                this.isReserve = isReserve;
            }

            public void setUserInfoList(String userInfoList) {
                this.userInfoList = userInfoList;
            }

            public void setServiceImgList(String serviceImgList) {
                this.serviceImgList = serviceImgList;
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

            public double getPrice() {
                return price;
            }

            public String getType() {
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

            public String getSortNum() {
                return sortNum;
            }

            public String getIsCollect() {
                return isCollect;
            }

            public String getIsReserve() {
                return isReserve;
            }

            public String getUserInfoList() {
                return userInfoList;
            }

            public String getServiceImgList() {
                return serviceImgList;
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
                dest.writeInt(this.realityPeoples);
                dest.writeInt(this.province);
                dest.writeString(this.provinceText);
                dest.writeInt(this.city);
                dest.writeString(this.cityText);
                dest.writeInt(this.district);
                dest.writeString(this.districtText);
                dest.writeString(this.address);
                dest.writeDouble(this.price);
                dest.writeString(this.type);
                dest.writeString(this.content);
                dest.writeString(this.servicePhone);
                dest.writeInt(this.status);
                dest.writeInt(this.isRecommoned);
                dest.writeString(this.servicesImg);
                dest.writeString(this.imgWidth);
                dest.writeString(this.imgHeight);
                dest.writeString(this.sortNum);
                dest.writeString(this.isCollect);
                dest.writeString(this.isReserve);
                dest.writeString(this.userInfoList);
                dest.writeString(this.serviceImgList);
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
                this.realityPeoples = in.readInt();
                this.province = in.readInt();
                this.provinceText = in.readString();
                this.city = in.readInt();
                this.cityText = in.readString();
                this.district = in.readInt();
                this.districtText = in.readString();
                this.address = in.readString();
                this.price = in.readDouble();
                this.type = in.readString();
                this.content = in.readString();
                this.servicePhone = in.readString();
                this.status = in.readInt();
                this.isRecommoned = in.readInt();
                this.servicesImg = in.readString();
                this.imgWidth = in.readString();
                this.imgHeight = in.readString();
                this.sortNum = in.readString();
                this.isCollect = in.readString();
                this.isReserve = in.readString();
                this.userInfoList = in.readString();
                this.serviceImgList = in.readString();
            }

            public static final Parcelable.Creator<ServiceInfoEntity> CREATOR = new Parcelable.Creator<ServiceInfoEntity>() {
                public ServiceInfoEntity createFromParcel(Parcel source) {
                    return new ServiceInfoEntity(source);
                }

                public ServiceInfoEntity[] newArray(int size) {
                    return new ServiceInfoEntity[size];
                }
            };

            @Override
            public String toString() {
                return "ServiceInfoEntity{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", startTime='" + startTime + '\'' +
                        ", endTime='" + endTime + '\'' +
                        ", applyStartTime='" + applyStartTime + '\'' +
                        ", applyEndTime='" + applyEndTime + '\'' +
                        ", peoples=" + peoples +
                        ", realityPeoples=" + realityPeoples +
                        ", province=" + province +
                        ", provinceText='" + provinceText + '\'' +
                        ", city=" + city +
                        ", cityText='" + cityText + '\'' +
                        ", district=" + district +
                        ", districtText='" + districtText + '\'' +
                        ", address='" + address + '\'' +
                        ", price=" + price +
                        ", type='" + type + '\'' +
                        ", content='" + content + '\'' +
                        ", servicePhone='" + servicePhone + '\'' +
                        ", status=" + status +
                        ", isRecommoned=" + isRecommoned +
                        ", servicesImg='" + servicesImg + '\'' +
                        ", imgWidth='" + imgWidth + '\'' +
                        ", imgHeight='" + imgHeight + '\'' +
                        ", sortNum='" + sortNum + '\'' +
                        ", isCollect='" + isCollect + '\'' +
                        ", isReserve='" + isReserve + '\'' +
                        ", userInfoList='" + userInfoList + '\'' +
                        ", serviceImgList='" + serviceImgList + '\'' +
                        '}';
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.serviceId);
            dest.writeString(this.orderId);
            dest.writeInt(this.userId);
            dest.writeString(this.orderCode);
            dest.writeString(this.orderDate);
            dest.writeString(this.servicePrice);
            dest.writeString(this.phone);
            dest.writeString(this.userNike);
            dest.writeInt(this.status);
            dest.writeInt(this.isGold);
            dest.writeString(this.goldCount);
            dest.writeString(this.discountPrice);
            dest.writeString(this.price);
            dest.writeString(this.payment);
            dest.writeInt(this.isInvoice);
            dest.writeString(this.invoiceTitle);
            dest.writeParcelable(this.serviceInfo, 0);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.serviceId = in.readString();
            this.orderId = in.readString();
            this.userId = in.readInt();
            this.orderCode = in.readString();
            this.orderDate = in.readString();
            this.servicePrice = in.readString();
            this.phone = in.readString();
            this.userNike = in.readString();
            this.status = in.readInt();
            this.isGold = in.readInt();
            this.goldCount = in.readString();
            this.discountPrice = in.readString();
            this.price = in.readString();
            this.payment = in.readString();
            this.isInvoice = in.readInt();
            this.invoiceTitle = in.readString();
            this.serviceInfo = in.readParcelable(ServiceInfoEntity.class.getClassLoader());
        }

        public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            public DataEntity[] newArray(int size) {
                return new DataEntity[size];
            }
        };

        @Override
        public String toString() {
            return "DataEntity{" +
                    "orderId='" + serviceId + '\'' +
                    "orderId='" + orderId + '\'' +
                    ", userId=" + userId +
                    ", orderCode='" + orderCode + '\'' +
                    ", orderDate='" + orderDate + '\'' +
                    ", servicePrice=" + servicePrice +
                    ", phone='" + phone + '\'' +
                    ", userNike='" + userNike + '\'' +
                    ", status=" + status +
                    ", isGold=" + isGold +
                    ", goldCount='" + goldCount + '\'' +
                    ", discountPrice='" + discountPrice + '\'' +
                    ", price=" + price +
                    ", payment='" + payment + '\'' +
                    ", isInvoice=" + isInvoice +
                    ", invoiceTitle='" + invoiceTitle + '\'' +
                    ", serviceInfo=" + serviceInfo +
                    '}';
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeParcelable(this.data, 0);
        dest.writeString(this.msg);
    }

    public OrderDetailModel() {
    }

    protected OrderDetailModel(Parcel in) {
        this.status = in.readInt();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<OrderDetailModel> CREATOR = new Parcelable.Creator<OrderDetailModel>() {
        public OrderDetailModel createFromParcel(Parcel source) {
            return new OrderDetailModel(source);
        }

        public OrderDetailModel[] newArray(int size) {
            return new OrderDetailModel[size];
        }
    };

    @Override
    public String toString() {
        return "OrderDetailModel{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
