package com.cmbb.smartkids.activity.order.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/24 17:29
 */
public class OrderListModel implements Parcelable {


    /**
     * page : 1
     * records : 14
     * rows : [{"buyCount":2,"title":"富文本编辑器测试","price":"299.98","serviceId":339,"status":1,"orderCode":"003391327381460014","servicesImg":"http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl","orderDate":"2016-03-16 20:52:18","specifications":"中份","orderId":103}]
     * total : 2
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":14,"rows":[{"buyCount":2,"title":"富文本编辑器测试","price":"299.98","serviceId":339,"status":1,"orderCode":"003391327381460014","servicesImg":"http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl","orderDate":"2016-03-16 20:52:18","specifications":"中份","orderId":103}],"total":2,"userdata":""}
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
        private int page;
        private int records;
        private int total;
        private String userdata;
        /**
         * buyCount : 2
         * title : 富文本编辑器测试
         * price : 299.98
         * serviceId : 339
         * status : 1
         * orderCode : 003391327381460014
         * servicesImg : http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl
         * orderDate : 2016-03-16 20:52:18
         * specifications : 中份
         * orderId : 103
         */

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
            private int buyCount;
            private String title;
            private String price;
            private int serviceId;
            private int status;
            private String orderCode;
            private String servicesImg;
            private String orderDate;
            private String specifications;
            private int orderId;

            public void setBuyCount(int buyCount) {
                this.buyCount = buyCount;
            }

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

            public void setSpecifications(String specifications) {
                this.specifications = specifications;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getBuyCount() {
                return buyCount;
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

            public String getSpecifications() {
                return specifications;
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
                dest.writeInt(this.buyCount);
                dest.writeString(this.title);
                dest.writeString(this.price);
                dest.writeInt(this.serviceId);
                dest.writeInt(this.status);
                dest.writeString(this.orderCode);
                dest.writeString(this.servicesImg);
                dest.writeString(this.orderDate);
                dest.writeString(this.specifications);
                dest.writeInt(this.orderId);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.buyCount = in.readInt();
                this.title = in.readString();
                this.price = in.readString();
                this.serviceId = in.readInt();
                this.status = in.readInt();
                this.orderCode = in.readString();
                this.servicesImg = in.readString();
                this.orderDate = in.readString();
                this.specifications = in.readString();
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

    public OrderListModel() {
    }

    protected OrderListModel(Parcel in) {
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


    /**
     * 获取订单列表
     * @param queryType
     * @param status
     * @param pager
     * @param pagerSize
     * @param callback
     */
    public static void getOrderListRequest(String queryType, String status, int pager, int pagerSize, OkHttpClientManager.ResultCallback<OrderListModel> callback){
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("queryType", queryType);
        if(!TextUtils.isEmpty(status))
            params.put("status", status);
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.ORDER_LIST_REQUEST, params, callback);
    }

}
