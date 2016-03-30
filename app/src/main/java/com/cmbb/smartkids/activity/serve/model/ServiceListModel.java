package com.cmbb.smartkids.activity.serve.model;

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
 * 创建时间：2015/9/24 11:53
 */
public class ServiceListModel implements Parcelable {


    /**
     * page : 1
     * records : 4
     * rows : [{"status":1,"statusName":"开始","isNew":false,"servicesImg":"http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl","applyEndTime":"2016-03-04 15:40:00","imgWidth":539,"type":"上门","endTime":"2016-03-18 15:40:00","city":440100,"startTime":"2016-03-04 15:40:00","id":339,"browseNumber":6,"title":"富文本编辑器测试","price":"120","peoples":12,"serviceTime":"3月4日 15:40 - 3月18日 15:40","cityText":"广州","createDate":"2016-03-01 15:43:01","realityPeoples":2,"imgHeight":355,"applyStartTime":"2016-03-01 15:40:00"}]
     * total : 1
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":4,"rows":[{"status":1,"statusName":"开始","isNew":false,"servicesImg":"http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl","applyEndTime":"2016-03-04 15:40:00","imgWidth":539,"type":"上门","endTime":"2016-03-18 15:40:00","city":440100,"startTime":"2016-03-04 15:40:00","id":339,"browseNumber":6,"title":"富文本编辑器测试","price":"120","peoples":12,"serviceTime":"3月4日 15:40 - 3月18日 15:40","cityText":"广州","createDate":"2016-03-01 15:43:01","realityPeoples":2,"imgHeight":355,"applyStartTime":"2016-03-01 15:40:00"}],"total":1,"userdata":""}
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
         * status : 1
         * statusName : 开始
         * isNew : false
         * servicesImg : http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl
         * applyEndTime : 2016-03-04 15:40:00
         * imgWidth : 539
         * type : 上门
         * endTime : 2016-03-18 15:40:00
         * city : 440100
         * startTime : 2016-03-04 15:40:00
         * id : 339
         * browseNumber : 6
         * title : 富文本编辑器测试
         * price : 120
         * peoples : 12
         * serviceTime : 3月4日 15:40 - 3月18日 15:40
         * cityText : 广州
         * createDate : 2016-03-01 15:43:01
         * realityPeoples : 2
         * imgHeight : 355
         * applyStartTime : 2016-03-01 15:40:00
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
            private int status;
            private String statusName;
            private boolean isNew;
            private String servicesImg;
            private String applyEndTime;
            private int imgWidth;
            private String type;
            private String endTime;
            private int city;
            private String startTime;
            private int id;
            private int browseNumber;
            private String title;
            private String price;
            private int peoples;
            private String serviceTime;
            private String cityText;
            private String createDate;
            private int realityPeoples;
            private int imgHeight;
            private String applyStartTime;

            public void setStatus(int status) {
                this.status = status;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public void setIsNew(boolean isNew) {
                this.isNew = isNew;
            }

            public void setServicesImg(String servicesImg) {
                this.servicesImg = servicesImg;
            }

            public void setApplyEndTime(String applyEndTime) {
                this.applyEndTime = applyEndTime;
            }

            public void setImgWidth(int imgWidth) {
                this.imgWidth = imgWidth;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setBrowseNumber(int browseNumber) {
                this.browseNumber = browseNumber;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setPeoples(int peoples) {
                this.peoples = peoples;
            }

            public void setServiceTime(String serviceTime) {
                this.serviceTime = serviceTime;
            }

            public void setCityText(String cityText) {
                this.cityText = cityText;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public void setRealityPeoples(int realityPeoples) {
                this.realityPeoples = realityPeoples;
            }

            public void setImgHeight(int imgHeight) {
                this.imgHeight = imgHeight;
            }

            public void setApplyStartTime(String applyStartTime) {
                this.applyStartTime = applyStartTime;
            }

            public int getStatus() {
                return status;
            }

            public String getStatusName() {
                return statusName;
            }

            public boolean isIsNew() {
                return isNew;
            }

            public String getServicesImg() {
                return servicesImg;
            }

            public String getApplyEndTime() {
                return applyEndTime;
            }

            public int getImgWidth() {
                return imgWidth;
            }

            public String getType() {
                return type;
            }

            public String getEndTime() {
                return endTime;
            }

            public int getCity() {
                return city;
            }

            public String getStartTime() {
                return startTime;
            }

            public int getId() {
                return id;
            }

            public int getBrowseNumber() {
                return browseNumber;
            }

            public String getTitle() {
                return title;
            }

            public String getPrice() {
                return price;
            }

            public int getPeoples() {
                return peoples;
            }

            public String getServiceTime() {
                return serviceTime;
            }

            public String getCityText() {
                return cityText;
            }

            public String getCreateDate() {
                return createDate;
            }

            public int getRealityPeoples() {
                return realityPeoples;
            }

            public int getImgHeight() {
                return imgHeight;
            }

            public String getApplyStartTime() {
                return applyStartTime;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.status);
                dest.writeString(this.statusName);
                dest.writeByte(isNew ? (byte) 1 : (byte) 0);
                dest.writeString(this.servicesImg);
                dest.writeString(this.applyEndTime);
                dest.writeInt(this.imgWidth);
                dest.writeString(this.type);
                dest.writeString(this.endTime);
                dest.writeInt(this.city);
                dest.writeString(this.startTime);
                dest.writeInt(this.id);
                dest.writeInt(this.browseNumber);
                dest.writeString(this.title);
                dest.writeString(this.price);
                dest.writeInt(this.peoples);
                dest.writeString(this.serviceTime);
                dest.writeString(this.cityText);
                dest.writeString(this.createDate);
                dest.writeInt(this.realityPeoples);
                dest.writeInt(this.imgHeight);
                dest.writeString(this.applyStartTime);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.status = in.readInt();
                this.statusName = in.readString();
                this.isNew = in.readByte() != 0;
                this.servicesImg = in.readString();
                this.applyEndTime = in.readString();
                this.imgWidth = in.readInt();
                this.type = in.readString();
                this.endTime = in.readString();
                this.city = in.readInt();
                this.startTime = in.readString();
                this.id = in.readInt();
                this.browseNumber = in.readInt();
                this.title = in.readString();
                this.price = in.readString();
                this.peoples = in.readInt();
                this.serviceTime = in.readString();
                this.cityText = in.readString();
                this.createDate = in.readString();
                this.realityPeoples = in.readInt();
                this.imgHeight = in.readInt();
                this.applyStartTime = in.readString();
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

    public ServiceListModel() {
    }

    protected ServiceListModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<ServiceListModel> CREATOR = new Parcelable.Creator<ServiceListModel>() {
        public ServiceListModel createFromParcel(Parcel source) {
            return new ServiceListModel(source);
        }

        public ServiceListModel[] newArray(int size) {
            return new ServiceListModel[size];
        }
    };

    /**
     * 获取服务列表
     *
     * @param serviceWay
     * @param serviceCity
     * @param serviceCategroy
     * @param serviceSortType
     * @param pageNo
     * @param pagerSize
     * @param callback
     */
    public static void getServiceListRequest(String serviceWay, String serviceCity, String serviceCategroy, String serviceSortType, int pageNo, int pagerSize, OkHttpClientManager.ResultCallback<ServiceListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(serviceCity))
            params.put("city", serviceCity);
        if (!TextUtils.isEmpty(serviceWay))
            params.put("type", serviceWay);
        if (!TextUtils.isEmpty(serviceCategroy))
            params.put("category", serviceCategroy);
        if (!TextUtils.isEmpty(serviceSortType))
            params.put("sortType", serviceSortType);
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.HOME_MAIN_HOT_SERVICE, params, callback);
    }

    /**
     * 获取本周精选
     *
     * @param pageNo
     * @param pagerSize
     * @param callback
     */
    public static void getWeekServiceListRequest(int pageNo, int pagerSize, OkHttpClientManager.ResultCallback<ServiceListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("isWeekChoice", String.valueOf(1));
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.HOME_MAIN_HOT_SERVICE, params, callback);
    }


    /**
     * 获取收藏服务列表
     *
     * @param pager
     * @param pagerSize
     * @param callback
     */
    public static void getCollectServiceRequest(int pager, int pagerSize, OkHttpClientManager.ResultCallback<ServiceListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.COLLECT_SERVICE_REQUEST, params, callback);
    }


    /**
     * 用户中心服务列表
     *
     * @param myCenter
     * @param isPopman
     * @param userId
     * @param pager
     * @param pagerSize
     * @param callback
     */
    public static void getUserCenterServiceRequest(int myCenter, String isPopman, String userId, int pager, int pagerSize, OkHttpClientManager.ResultCallback<ServiceListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("myCenter", String.valueOf(myCenter));
        params.put("isEredar", isPopman);
        params.put("id", userId);
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.MY_SERVICE_REQUEST, params, callback);
    }


}
