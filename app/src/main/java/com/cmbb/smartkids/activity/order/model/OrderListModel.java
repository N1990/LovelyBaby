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
     * records : 1
     * rows : [{"buyCount":1,"title":"服务测试121","price":"0.01","serviceId":354,"status":1,"orderCode":"003543024857570006","statusName":"未支付","servicesImg":"http://smart.image.alimmdn.com/system/image/2016-03-28/servicesImgFile_NDI5N2NhNWEtNjQ1Ni00MWU5LTk5NmUtOWUwNjVmZmUwOTMy","orderDate":"2016-03-30 09:48:06","specifications":"统一类别","orderId":191}]
     * total : 1
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":1,"rows":[{"buyCount":1,"title":"服务测试121","price":"0.01","serviceId":354,"status":1,"orderCode":"003543024857570006","statusName":"未支付","servicesImg":"http://smart.image.alimmdn.com/system/image/2016-03-28/servicesImgFile_NDI5N2NhNWEtNjQ1Ni00MWU5LTk5NmUtOWUwNjVmZmUwOTMy","orderDate":"2016-03-30 09:48:06","specifications":"统一类别","orderId":191}],"total":1,"userdata":""}
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
         * buyCount : 1
         * title : 服务测试121
         * price : 0.01
         * serviceId : 354
         * status : 1
         * orderCode : 003543024857570006
         * statusName : 未支付
         * servicesImg : http://smart.image.alimmdn.com/system/image/2016-03-28/servicesImgFile_NDI5N2NhNWEtNjQ1Ni00MWU5LTk5NmUtOWUwNjVmZmUwOTMy
         * orderDate : 2016-03-30 09:48:06
         * specifications : 统一类别
         * orderId : 191
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
            private int buyCount;
            private String title;
            private String price;
            private int serviceId;
            private int status;
            private String orderCode;
            private String statusName;
            private String servicesImg;
            private String orderDate;
            private String specifications;
            private int orderId;

            public int getBuyCount() {
                return buyCount;
            }

            public void setBuyCount(int buyCount) {
                this.buyCount = buyCount;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getServiceId() {
                return serviceId;
            }

            public void setServiceId(int serviceId) {
                this.serviceId = serviceId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public String getServicesImg() {
                return servicesImg;
            }

            public void setServicesImg(String servicesImg) {
                this.servicesImg = servicesImg;
            }

            public String getOrderDate() {
                return orderDate;
            }

            public void setOrderDate(String orderDate) {
                this.orderDate = orderDate;
            }

            public String getSpecifications() {
                return specifications;
            }

            public void setSpecifications(String specifications) {
                this.specifications = specifications;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
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
                dest.writeString(this.statusName);
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
                this.statusName = in.readString();
                this.servicesImg = in.readString();
                this.orderDate = in.readString();
                this.specifications = in.readString();
                this.orderId = in.readInt();
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

    public OrderListModel() {
    }

    protected OrderListModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<OrderListModel> CREATOR = new Parcelable.Creator<OrderListModel>() {
        @Override
        public OrderListModel createFromParcel(Parcel source) {
            return new OrderListModel(source);
        }

        @Override
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

    /**
     * @param pager
     * @param pagerSize
     * @param callback
     */
    public static void getAcceptOrderListRequest(int serviceId, int pager, int pagerSize, OkHttpClientManager.ResultCallback<OrderListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("queryType", "1");
        params.put("status", "2,4");
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("serviceId", String.valueOf(serviceId));
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.ORDER_LIST_REQUEST, params, callback);
    }


    public static void getVerifyOrderListRequest(int serviceId, int pager, int pagerSize, OkHttpClientManager.ResultCallback<OrderListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("queryType", "1");
        params.put("status", "3,5");
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("serviceId", String.valueOf(serviceId));
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.ORDER_LIST_REQUEST, params, callback);
    }
}
