package com.cmbb.smartkids.activity.serve.v2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/16 上午10:48
 */
public class ReserveModel implements Parcelable {

    private DataEntity data;

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
        private String orderId;
        private int userId;
        private String orderCode;
        private String validCode;
        private String orderDate;
        private int servicePrice;
        private String participate;
        private String phone;
        private String userNike;
        private String status;
        private String isGold;
        private String goldCount;
        private String discountPrice;
        private String refundTatio;
        private int priceId;
        private String specifications;
        private int buyCount;
        private double price;
        private String payment;
        private String payDate;
        private int isInvoice;
        private String invoiceTitle;


        private ServiceInfoEntity serviceInfo;
        private String addressId;
        private String receiveName;
        private String receivePhone;
        private String postCode;
        private String address;

        public void setServiceId(int serviceId) {
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

        public void setValidCode(String validCode) {
            this.validCode = validCode;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public void setServicePrice(int servicePrice) {
            this.servicePrice = servicePrice;
        }

        public void setParticipate(String participate) {
            this.participate = participate;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setUserNike(String userNike) {
            this.userNike = userNike;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setIsGold(String isGold) {
            this.isGold = isGold;
        }

        public void setGoldCount(String goldCount) {
            this.goldCount = goldCount;
        }

        public void setDiscountPrice(String discountPrice) {
            this.discountPrice = discountPrice;
        }

        public void setRefundTatio(String refundTatio) {
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

        public void setPrice(double price) {
            this.price = price;
        }

        public void setPayment(String payment) {
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

        public void setAddressId(String addressId) {
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

        public Object getOrderId() {
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

        public int getServicePrice() {
            return servicePrice;
        }

        public String getParticipate() {
            return participate;
        }

        public String getPhone() {
            return phone;
        }

        public String getUserNike() {
            return userNike;
        }

        public String getStatus() {
            return status;
        }

        public String getIsGold() {
            return isGold;
        }

        public String getGoldCount() {
            return goldCount;
        }

        public String getDiscountPrice() {
            return discountPrice;
        }

        public String getRefundTatio() {
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

        public double getPrice() {
            return price;
        }

        public Object getPayment() {
            return payment;
        }

        public Object getPayDate() {
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

        public String getAddressId() {
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
            private String collectCount;
            private String realityPeoples;
            private int sellNum;
            private int province;
            private String provinceText;
            private int city;
            private int district;
            private String districtText;
            private String address;
            private String mark;
            private int price;
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
            private int imgWidth;
            private int imgHeight;
            private int sortNum;
            private int browseNumber;
            private String isCollect;
            private String colletCount;
            private String isReserve;
            private String reserveText;
            private String surplusTime;

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

            public void setCollectCount(String collectCount) {
                this.collectCount = collectCount;
            }

            public void setRealityPeoples(String realityPeoples) {
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

            public void setPrice(int price) {
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

            public void setImgWidth(int imgWidth) {
                this.imgWidth = imgWidth;
            }

            public void setImgHeight(int imgHeight) {
                this.imgHeight = imgHeight;
            }

            public void setSortNum(int sortNum) {
                this.sortNum = sortNum;
            }

            public void setBrowseNumber(int browseNumber) {
                this.browseNumber = browseNumber;
            }

            public void setIsCollect(String isCollect) {
                this.isCollect = isCollect;
            }

            public void setColletCount(String colletCount) {
                this.colletCount = colletCount;
            }

            public void setIsReserve(String isReserve) {
                this.isReserve = isReserve;
            }

            public void setReserveText(String reserveText) {
                this.reserveText = reserveText;
            }

            public void setSurplusTime(String surplusTime) {
                this.surplusTime = surplusTime;
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

            public String getCollectCount() {
                return collectCount;
            }

            public String getRealityPeoples() {
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

            public int getPrice() {
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

            public int getImgWidth() {
                return imgWidth;
            }

            public int getImgHeight() {
                return imgHeight;
            }

            public int getSortNum() {
                return sortNum;
            }

            public int getBrowseNumber() {
                return browseNumber;
            }

            public String getIsCollect() {
                return isCollect;
            }

            public String getColletCount() {
                return colletCount;
            }

            public String getIsReserve() {
                return isReserve;
            }

            public String getReserveText() {
                return reserveText;
            }

            public String getSurplusTime() {
                return surplusTime;
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
                dest.writeString(this.collectCount);
                dest.writeString(this.realityPeoples);
                dest.writeInt(this.sellNum);
                dest.writeInt(this.province);
                dest.writeString(this.provinceText);
                dest.writeInt(this.city);
                dest.writeInt(this.district);
                dest.writeString(this.districtText);
                dest.writeString(this.address);
                dest.writeString(this.mark);
                dest.writeInt(this.price);
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
                dest.writeInt(this.imgWidth);
                dest.writeInt(this.imgHeight);
                dest.writeInt(this.sortNum);
                dest.writeInt(this.browseNumber);
                dest.writeString(this.isCollect);
                dest.writeString(this.colletCount);
                dest.writeString(this.isReserve);
                dest.writeString(this.reserveText);
                dest.writeString(this.surplusTime);
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
                this.collectCount = in.readString();
                this.realityPeoples = in.readString();
                this.sellNum = in.readInt();
                this.province = in.readInt();
                this.provinceText = in.readString();
                this.city = in.readInt();
                this.district = in.readInt();
                this.districtText = in.readString();
                this.address = in.readString();
                this.mark = in.readString();
                this.price = in.readInt();
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
                this.imgWidth = in.readInt();
                this.imgHeight = in.readInt();
                this.sortNum = in.readInt();
                this.browseNumber = in.readInt();
                this.isCollect = in.readString();
                this.colletCount = in.readString();
                this.isReserve = in.readString();
                this.reserveText = in.readString();
                this.surplusTime = in.readString();
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
            dest.writeString(this.orderId);
            dest.writeInt(this.userId);
            dest.writeString(this.orderCode);
            dest.writeString(this.validCode);
            dest.writeString(this.orderDate);
            dest.writeInt(this.servicePrice);
            dest.writeString(this.participate);
            dest.writeString(this.phone);
            dest.writeString(this.userNike);
            dest.writeString(this.status);
            dest.writeString(this.isGold);
            dest.writeString(this.goldCount);
            dest.writeString(this.discountPrice);
            dest.writeString(this.refundTatio);
            dest.writeInt(this.priceId);
            dest.writeString(this.specifications);
            dest.writeInt(this.buyCount);
            dest.writeDouble(this.price);
            dest.writeString(this.payment);
            dest.writeString(this.payDate);
            dest.writeInt(this.isInvoice);
            dest.writeString(this.invoiceTitle);
            dest.writeParcelable(this.serviceInfo, 0);
            dest.writeString(this.addressId);
            dest.writeString(this.receiveName);
            dest.writeString(this.receivePhone);
            dest.writeString(this.postCode);
            dest.writeString(this.address);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.serviceId = in.readInt();
            this.orderId = in.readString();
            this.userId = in.readInt();
            this.orderCode = in.readString();
            this.validCode = in.readString();
            this.orderDate = in.readString();
            this.servicePrice = in.readInt();
            this.participate = in.readString();
            this.phone = in.readString();
            this.userNike = in.readString();
            this.status = in.readString();
            this.isGold = in.readString();
            this.goldCount = in.readString();
            this.discountPrice = in.readString();
            this.refundTatio = in.readString();
            this.priceId = in.readInt();
            this.specifications = in.readString();
            this.buyCount = in.readInt();
            this.price = in.readDouble();
            this.payment = in.readString();
            this.payDate = in.readString();
            this.isInvoice = in.readInt();
            this.invoiceTitle = in.readString();
            this.serviceInfo = in.readParcelable(ServiceInfoEntity.class.getClassLoader());
            this.addressId = in.readString();
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

    public ReserveModel() {
    }

    protected ReserveModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<ReserveModel> CREATOR = new Parcelable.Creator<ReserveModel>() {
        public ReserveModel createFromParcel(Parcel source) {
            return new ReserveModel(source);
        }

        public ReserveModel[] newArray(int size) {
            return new ReserveModel[size];
        }
    };

    /**
     * 约定接口
     *
     * @param serviceId
     * @param priceId
     * @param buyCount
     * @param token
     * @param callback
     */
    public static void handleReserveRequest(String serviceId, String priceId, String buyCount, String token, OkHttpClientManager.ResultCallback<ReserveModel> callback) {
        HashMap<String, String> maps = new HashMap<>();
        maps.put("serviceId", serviceId);
        maps.put("priceId", priceId);
        maps.put("priceId", priceId);
        maps.put("buyCount", buyCount);
        maps.put("token", token);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.SERVICES_RESERVE, maps, callback);
    }
}
