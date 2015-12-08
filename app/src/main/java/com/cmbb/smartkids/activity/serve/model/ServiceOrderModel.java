package com.cmbb.smartkids.activity.serve.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/23 10:59
 */
public class ServiceOrderModel implements Parcelable {

    /**
     * status : 1
     * data : {"serviceId":14,"userId":9,"orderCode":"1214429771144164066","orderDate":"2015-09-23 10:58:34","servicePrice":0,"phone":"18221365268","userNike":"666","status":null,"isGold":null,"goldCount":null,"discountPrice":null,"price":null,"payment":null,"isInvoice":0,"invoiceTitle":null}
     * msg : 操作成功
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
         * serviceId : 14
         * orderId: null
         * userId : 9
         * orderCode : 1214429771144164066
         * orderDate : 2015-09-23 10:58:34
         * servicePrice : 0
         * phone : 18221365268
         * userNike : 666
         * status : null
         * isGold : null
         * goldCount : null
         * discountPrice : null
         * price : null
         * payment : null
         * isInvoice : 0
         * invoiceTitle : null
         */

        private int serviceId;
        private String orderId;
        private int userId;
        private String orderCode;
        private String orderDate;
        private String servicePrice;
        private String phone;
        private String userNike;
        private String status;
        private String isGold;
        private String goldCount;
        private String discountPrice;
        private String price;
        private String payment;
        private int isInvoice;
        private String invoiceTitle;

        public void setServiceId(int serviceId) {
            this.serviceId = serviceId;
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

        public int getServiceId() {
            return serviceId;
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

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
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
            dest.writeString(this.orderDate);
            dest.writeString(this.servicePrice);
            dest.writeString(this.phone);
            dest.writeString(this.userNike);
            dest.writeString(this.status);
            dest.writeString(this.isGold);
            dest.writeString(this.goldCount);
            dest.writeString(this.discountPrice);
            dest.writeString(this.price);
            dest.writeString(this.payment);
            dest.writeInt(this.isInvoice);
            dest.writeString(this.invoiceTitle);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.serviceId = in.readInt();
            this.orderId = in.readString();
            this.userId = in.readInt();
            this.orderCode = in.readString();
            this.orderDate = in.readString();
            this.servicePrice = in.readString();
            this.phone = in.readString();
            this.userNike = in.readString();
            this.status = in.readString();
            this.isGold = in.readString();
            this.goldCount = in.readString();
            this.discountPrice = in.readString();
            this.price = in.readString();
            this.payment = in.readString();
            this.isInvoice = in.readInt();
            this.invoiceTitle = in.readString();
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
                    "serviceId=" + serviceId +
                    ", userId=" + orderId +
                    ", userId=" + userId +
                    ", orderCode='" + orderCode + '\'' +
                    ", orderDate='" + orderDate + '\'' +
                    ", servicePrice=" + servicePrice +
                    ", phone='" + phone + '\'' +
                    ", userNike='" + userNike + '\'' +
                    ", status='" + status + '\'' +
                    ", isGold='" + isGold + '\'' +
                    ", goldCount='" + goldCount + '\'' +
                    ", discountPrice='" + discountPrice + '\'' +
                    ", price='" + price + '\'' +
                    ", payment='" + payment + '\'' +
                    ", isInvoice=" + isInvoice +
                    ", invoiceTitle='" + invoiceTitle + '\'' +
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

    public ServiceOrderModel() {
    }

    protected ServiceOrderModel(Parcel in) {
        this.status = in.readInt();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<ServiceOrderModel> CREATOR = new Parcelable.Creator<ServiceOrderModel>() {
        public ServiceOrderModel createFromParcel(Parcel source) {
            return new ServiceOrderModel(source);
        }

        public ServiceOrderModel[] newArray(int size) {
            return new ServiceOrderModel[size];
        }
    };

    @Override
    public String toString() {
        return "ServiceOrderModel{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
