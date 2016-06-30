package com.cmbb.smartkids.activity.serve.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.utils.SPCache;

import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/25 17:36
 */
public class MyServiceListModel implements Parcelable {


    /**
     * page : 1
     * records : 6
     * rows : [{"id":219,"properties":{},"title":"测试二","startTime":"2015-12-01 02:30","endTime":"2015-12-02 02:30","applyStartTime":"2015-11-11 02:30","applyEndTime":"2015-11-30 02:30","peoples":1212,"realityPeoples":1,"province":120000,"city":120100,"district":120101,"address":"111","price":0.01,"type":203,"content":"首页显示菊花","servicePhone":"1212","status":1,"isRecommoned":0,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-11-09/servicesImgFile_MjdlODkzMGItZjQzYi00NzRjLWI3OWYtZWQ4OTgyNDE3MmVl","imgWidth":1024,"imgHeight":768,"sortNum":0,"isDelete":0,"createDate":"2015-11-09 10:31:25","createUserId":2,"updateDate":"","updateUserId":2,"provinceText":"天津","cityText":"天津市","districtText":"和平区"}]
     * total : 1
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":6,"rows":[{"id":219,"properties":{},"title":"测试二","startTime":"2015-12-01 02:30","endTime":"2015-12-02 02:30","applyStartTime":"2015-11-11 02:30","applyEndTime":"2015-11-30 02:30","peoples":1212,"realityPeoples":1,"province":120000,"city":120100,"district":120101,"address":"111","price":0.01,"type":203,"content":"首页显示菊花","servicePhone":"1212","status":1,"isRecommoned":0,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-11-09/servicesImgFile_MjdlODkzMGItZjQzYi00NzRjLWI3OWYtZWQ4OTgyNDE3MmVl","imgWidth":1024,"imgHeight":768,"sortNum":0,"isDelete":0,"createDate":"2015-11-09 10:31:25","createUserId":2,"updateDate":"","updateUserId":2,"provinceText":"天津","cityText":"天津市","districtText":"和平区"}],"total":1,"userdata":""}
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
                dest.writeByte(this.isNew ? (byte) 1 : (byte) 0);
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

            public static final Creator<RowsEntity> CREATOR = new Creator<RowsEntity>() {
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
        public String toString() {
            return "DataEntity{" +
                    "page=" + page +
                    ", records=" + records +
                    ", total=" + total +
                    ", userdata='" + userdata + '\'' +
                    ", rows=" + rows +
                    '}';
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
    public String toString() {
        return "MyServiceListModel{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
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

    public MyServiceListModel() {
    }

    protected MyServiceListModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<MyServiceListModel> CREATOR = new Parcelable.Creator<MyServiceListModel>() {
        public MyServiceListModel createFromParcel(Parcel source) {
            return new MyServiceListModel(source);
        }

        public MyServiceListModel[] newArray(int size) {
            return new MyServiceListModel[size];
        }
    };


    /**
     * 获取服务列表
     *
     * @param pager     int
     * @param pagerSize int
     * @param callback  ResultCallback
     */
    public static void handleMyServiceListRequest(int pager, int pagerSize, OkHttpClientManager.ResultCallback<MyServiceListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("myCenter", "1");
        params.put("isEredar", "1");
        params.put("id", SPCache.getString(Constants.USER_ID, ""));
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("token", BaseApplication.token);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.MY_SERVICE_REQUEST, params, callback);
    }


}
