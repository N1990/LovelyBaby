package com.cmbb.smartkids.activity.order.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/24 17:29
 */
public class OrderListModel implements Parcelable {


    /**
     * status : 1
     * data : {"page":1,"records":1,"rows":[{"title":"萌宝驾到21！","price":22,"serviceId":21,"status":1,"orderCode":"0314430868119668378","servicesImg":"http://fuck.image.alimmdn.com/2015-9-18/image_2a3320c26e70426cb07a5afc3fe9bf3c","orderDate":"2015-09-24 17:27:01","orderId":31}],"total":1,"userdata":null}
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
         * page : 1
         * records : 1
         * rows : [{"title":"萌宝驾到21！","price":22,"serviceId":21,"status":1,"orderCode":"0314430868119668378","servicesImg":"http://fuck.image.alimmdn.com/2015-9-18/image_2a3320c26e70426cb07a5afc3fe9bf3c","orderDate":"2015-09-24 17:27:01","orderId":31}]
         * total : 1
         * userdata : null
         */

        private int page;
        private int records;
        private int total;
        private String userdata;
        private List<RowsEntity> rows;

        public void setPage(int page) {
            this.page = page;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public void setUserdata(String userdata) {
            this.userdata = userdata;
        }

        public void setRows(List<RowsEntity> rows) {
            this.rows = rows;
        }

        public int getPage() {
            return page;
        }

        public int getRecords() {
            return records;
        }

        public int getTotal() {
            return total;
        }

        public String getUserdata() {
            return userdata;
        }

        public List<RowsEntity> getRows() {
            return rows;
        }

        public static class RowsEntity implements Parcelable {
            /**
             * title : 萌宝驾到21！
             * price : 22
             * serviceId : 21
             * status : 1
             * orderCode : 0314430868119668378
             * servicesImg : http://fuck.image.alimmdn.com/2015-9-18/image_2a3320c26e70426cb07a5afc3fe9bf3c
             * orderDate : 2015-09-24 17:27:01
             * orderId : 31
             */

            private String title;
            private String price;
            private int serviceId;
            private int status;
            private String orderCode;
            private String servicesImg;
            private String orderDate;
            private int orderId;

            public void setTitle(String title) {
                this.title = title;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setServiceId(int serviceId) {
                this.serviceId = serviceId;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public void setServicesImg(String servicesImg) {
                this.servicesImg = servicesImg;
            }

            public void setOrderDate(String orderDate) {
                this.orderDate = orderDate;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getTitle() {
                return title;
            }

            public String getPrice() {
                return price;
            }

            public int getServiceId() {
                return serviceId;
            }

            public int getStatus() {
                return status;
            }

            public String getOrderCode() {
                return orderCode;
            }

            public String getServicesImg() {
                return servicesImg;
            }

            public String getOrderDate() {
                return orderDate;
            }

            public int getOrderId() {
                return orderId;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.title);
                dest.writeString(this.price);
                dest.writeInt(this.serviceId);
                dest.writeInt(this.status);
                dest.writeString(this.orderCode);
                dest.writeString(this.servicesImg);
                dest.writeString(this.orderDate);
                dest.writeInt(this.orderId);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.title = in.readString();
                this.price = in.readString();
                this.serviceId = in.readInt();
                this.status = in.readInt();
                this.orderCode = in.readString();
                this.servicesImg = in.readString();
                this.orderDate = in.readString();
                this.orderId = in.readInt();
            }

            public static final Parcelable.Creator<RowsEntity> CREATOR = new Parcelable.Creator<RowsEntity>() {
                public RowsEntity createFromParcel(Parcel source) {
                    return new RowsEntity(source);
                }

                public RowsEntity[] newArray(int size) {
                    return new RowsEntity[size];
                }
            };

            @Override
            public String toString() {
                return "RowsEntity{" +
                        "title='" + title + '\'' +
                        ", price=" + price +
                        ", serviceId=" + serviceId +
                        ", status=" + status +
                        ", orderCode='" + orderCode + '\'' +
                        ", servicesImg='" + servicesImg + '\'' +
                        ", orderDate='" + orderDate + '\'' +
                        ", orderId=" + orderId +
                        '}';
            }
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
                    "page=" + page +
                    ", records=" + records +
                    ", total=" + total +
                    ", userdata='" + userdata + '\'' +
                    ", rows=" + rows +
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

    public OrderListModel() {
    }

    protected OrderListModel(Parcel in) {
        this.status = in.readInt();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<OrderListModel> CREATOR = new Parcelable.Creator<OrderListModel>() {
        public OrderListModel createFromParcel(Parcel source) {
            return new OrderListModel(source);
        }

        public OrderListModel[] newArray(int size) {
            return new OrderListModel[size];
        }
    };

    @Override
    public String toString() {
        return "OrderListModel{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
