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

    private DataEntity data;
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
            private int status;
            private String statusName;
            private boolean isNew;
            private String servicesImg;
            private String introduce;
            private String applyEndTime;
            private String imgWidth;
            private int type;
            private String endTime;
            private int city;
            private String startTime;
            private int id;
            private int browseNumber;
            private String title;
            private String price;
            private String surplusTime;
            private int peoples;
            private String serviceTime;
            private String typeText;
            private String cityText;
            private String createDate;
            private int realityPeoples;
            private String imgHeight;
            private String applyStartTime;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public boolean isIsNew() {
                return isNew;
            }

            public void setIsNew(boolean isNew) {
                this.isNew = isNew;
            }

            public String getServicesImg() {
                return servicesImg;
            }

            public void setServicesImg(String servicesImg) {
                this.servicesImg = servicesImg;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getApplyEndTime() {
                return applyEndTime;
            }

            public void setApplyEndTime(String applyEndTime) {
                this.applyEndTime = applyEndTime;
            }

            public String getImgWidth() {
                return imgWidth;
            }

            public void setImgWidth(String imgWidth) {
                this.imgWidth = imgWidth;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getCity() {
                return city;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getBrowseNumber() {
                return browseNumber;
            }

            public void setBrowseNumber(int browseNumber) {
                this.browseNumber = browseNumber;
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

            public String getSurplusTime() {
                return surplusTime;
            }

            public void setSurplusTime(String surplusTime) {
                this.surplusTime = surplusTime;
            }

            public int getPeoples() {
                return peoples;
            }

            public void setPeoples(int peoples) {
                this.peoples = peoples;
            }

            public String getServiceTime() {
                return serviceTime;
            }

            public void setServiceTime(String serviceTime) {
                this.serviceTime = serviceTime;
            }

            public String getTypeText() {
                return typeText;
            }

            public void setTypeText(String typeText) {
                this.typeText = typeText;
            }

            public String getCityText() {
                return cityText;
            }

            public void setCityText(String cityText) {
                this.cityText = cityText;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getRealityPeoples() {
                return realityPeoples;
            }

            public void setRealityPeoples(int realityPeoples) {
                this.realityPeoples = realityPeoples;
            }

            public String getImgHeight() {
                return imgHeight;
            }

            public void setImgHeight(String imgHeight) {
                this.imgHeight = imgHeight;
            }

            public String getApplyStartTime() {
                return applyStartTime;
            }

            public void setApplyStartTime(String applyStartTime) {
                this.applyStartTime = applyStartTime;
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
                dest.writeString(this.introduce);
                dest.writeString(this.applyEndTime);
                dest.writeString(this.imgWidth);
                dest.writeInt(this.type);
                dest.writeString(this.endTime);
                dest.writeInt(this.city);
                dest.writeString(this.startTime);
                dest.writeInt(this.id);
                dest.writeInt(this.browseNumber);
                dest.writeString(this.title);
                dest.writeString(this.price);
                dest.writeString(this.surplusTime);
                dest.writeInt(this.peoples);
                dest.writeString(this.serviceTime);
                dest.writeString(this.typeText);
                dest.writeString(this.cityText);
                dest.writeString(this.createDate);
                dest.writeInt(this.realityPeoples);
                dest.writeString(this.imgHeight);
                dest.writeString(this.applyStartTime);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.status = in.readInt();
                this.statusName = in.readString();
                this.isNew = in.readByte() != 0;
                this.servicesImg = in.readString();
                this.introduce = in.readString();
                this.applyEndTime = in.readString();
                this.imgWidth = in.readString();
                this.type = in.readInt();
                this.endTime = in.readString();
                this.city = in.readInt();
                this.startTime = in.readString();
                this.id = in.readInt();
                this.browseNumber = in.readInt();
                this.title = in.readString();
                this.price = in.readString();
                this.surplusTime = in.readString();
                this.peoples = in.readInt();
                this.serviceTime = in.readString();
                this.typeText = in.readString();
                this.cityText = in.readString();
                this.createDate = in.readString();
                this.realityPeoples = in.readInt();
                this.imgHeight = in.readString();
                this.applyStartTime = in.readString();
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

    public ServiceListModel() {
    }

    protected ServiceListModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<ServiceListModel> CREATOR = new Parcelable.Creator<ServiceListModel>() {
        @Override
        public ServiceListModel createFromParcel(Parcel source) {
            return new ServiceListModel(source);
        }

        @Override
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
    public static void getServiceListRequest(String serviceWay, String serviceCity, String serviceCategroy, String serviceSortType, String serviceStatus, int pageNo, int pagerSize, OkHttpClientManager.ResultCallback<ServiceListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(serviceCity))
            params.put("city", serviceCity);
        if (!TextUtils.isEmpty(serviceWay))
            params.put("type", serviceWay);
        if (!TextUtils.isEmpty(serviceCategroy))
            params.put("category", serviceCategroy);
        if (!TextUtils.isEmpty(serviceSortType))
            params.put("sortType", serviceSortType);
        if (!TextUtils.isEmpty(serviceStatus))
            params.put("status", serviceStatus);
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


    /**
     * 搜索服务列表
     *
     * @param content
     * @param pager
     * @param pagerSize
     * @param callback
     */
    public static void getSearchServiceRequest(String content, int pager, int pagerSize, OkHttpClientManager.ResultCallback<ServiceListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("contents", content);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.SEARCH_SERVICE_REQUEST, params, callback);
    }
}
