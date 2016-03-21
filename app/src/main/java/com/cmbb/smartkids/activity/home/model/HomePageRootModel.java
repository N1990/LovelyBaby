package com.cmbb.smartkids.activity.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/16 下午5:11
 */
public class HomePageRootModel implements Parcelable {


    /**
     * page : 1
     * records : 2
     * rows : [{"id":277,"properties":{},"title":"界面服务1212","startTime":"2015-12-09 12:00:00","endTime":"2015-12-09 15:00:00","applyStartTime":"2015-12-07 12:00:00","applyEndTime":"2015-12-09 12:00:00","peoples":12,"realityPeoples":1,"province":310000,"city":310100,"district":310101,"address":"123123123","price":"0.01","type":201,"content":"23312","servicePhone":"12121212","status":2,"isRecommoned":1,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-12-07/servicesImgFile_ZjFiZDc2YzItNmZiMS00NDJiLWEwMmItYWVjMTc1NWI3YWI1","imgWidth":"1024","imgHeight":"768","sortNum":2,"browseNumber":24,"isDelete":0,"createDate":"2015-12-07 12:01:21","createUserId":1,"updateDate":"","updateUserId":12,"provinceText":"","cityText":"上海市","districtText":"","surplusTime":"报名截止时间21小时"}]
     * total : 1
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":2,"rows":[{"id":277,"properties":{},"title":"界面服务1212","startTime":"2015-12-09 12:00:00","endTime":"2015-12-09 15:00:00","applyStartTime":"2015-12-07 12:00:00","applyEndTime":"2015-12-09 12:00:00","peoples":12,"realityPeoples":1,"province":310000,"city":310100,"district":310101,"address":"123123123","price":"0.01","type":201,"content":"23312","servicePhone":"12121212","status":2,"isRecommoned":1,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-12-07/servicesImgFile_ZjFiZDc2YzItNmZiMS00NDJiLWEwMmItYWVjMTc1NWI3YWI1","imgWidth":"1024","imgHeight":"768","sortNum":2,"browseNumber":24,"isDelete":0,"createDate":"2015-12-07 12:01:21","createUserId":1,"updateDate":"","updateUserId":12,"provinceText":"","cityText":"上海市","districtText":"","surplusTime":"报名截止时间21小时"}],"total":1,"userdata":""}
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
         * id : 277
         * properties : {}
         * title : 界面服务1212
         * startTime : 2015-12-09 12:00:00
         * endTime : 2015-12-09 15:00:00
         * applyStartTime : 2015-12-07 12:00:00
         * applyEndTime : 2015-12-09 12:00:00
         * peoples : 12
         * realityPeoples : 1
         * province : 310000
         * city : 310100
         * district : 310101
         * address : 123123123
         * price : 0.01
         * type : 201
         * content : 23312
         * servicePhone : 12121212
         * status : 2
         * isRecommoned : 1
         * servicesImg : http://smart.image.alimmdn.com/system/image/2015-12-07/servicesImgFile_ZjFiZDc2YzItNmZiMS00NDJiLWEwMmItYWVjMTc1NWI3YWI1
         * imgWidth : 1024
         * imgHeight : 768
         * sortNum : 2
         * browseNumber : 24
         * isDelete : 0
         * createDate : 2015-12-07 12:01:21
         * createUserId : 1
         * updateDate :
         * updateUserId : 12
         * provinceText :
         * cityText : 上海市
         * districtText :
         * surplusTime : 报名截止时间21小时
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
            private String introduce;
            private String applyEndTime;
            private int imgWidth;
            private int type;
            private String endTime;
            private int city;
            private String startTime;
            private int id;
            private int browseNumber;
            private String title;
            private double price;
            private int peoples;
            private String serviceTime;
            private String typeText;
            private String cityText;
            private String createDate;
            private String realityPeoples;
            private int imgHeight;
            private String applyStartTime;
            private String surplusTime;

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getSurplusTime() {
                return surplusTime;
            }

            public void setSurplusTime(String surplusTime) {
                this.surplusTime = surplusTime;
            }

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

            public String getApplyEndTime() {
                return applyEndTime;
            }

            public void setApplyEndTime(String applyEndTime) {
                this.applyEndTime = applyEndTime;
            }

            public int getImgWidth() {
                return imgWidth;
            }

            public void setImgWidth(int imgWidth) {
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

            public double getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
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

            public String getRealityPeoples() {
                return realityPeoples;
            }

            public void setRealityPeoples(String realityPeoples) {
                this.realityPeoples = realityPeoples;
            }

            public int getImgHeight() {
                return imgHeight;
            }

            public void setImgHeight(int imgHeight) {
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
                dest.writeInt(this.imgWidth);
                dest.writeInt(this.type);
                dest.writeString(this.endTime);
                dest.writeInt(this.city);
                dest.writeString(this.startTime);
                dest.writeInt(this.id);
                dest.writeInt(this.browseNumber);
                dest.writeString(this.title);
                dest.writeDouble(this.price);
                dest.writeInt(this.peoples);
                dest.writeString(this.serviceTime);
                dest.writeString(this.typeText);
                dest.writeString(this.cityText);
                dest.writeString(this.createDate);
                dest.writeString(this.realityPeoples);
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
                this.introduce = in.readString();
                this.applyEndTime = in.readString();
                this.imgWidth = in.readInt();
                this.type = in.readInt();
                this.endTime = in.readString();
                this.city = in.readInt();
                this.startTime = in.readString();
                this.id = in.readInt();
                this.browseNumber = in.readInt();
                this.title = in.readString();
                this.price = in.readDouble();
                this.peoples = in.readInt();
                this.serviceTime = in.readString();
                this.typeText = in.readString();
                this.cityText = in.readString();
                this.createDate = in.readString();
                this.realityPeoples = in.readParcelable(Object.class.getClassLoader());
                this.imgHeight = in.readInt();
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
        dest.writeParcelable(this.data, 0);
        dest.writeString(this.msg);
    }

    public HomePageRootModel() {
    }

    protected HomePageRootModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<HomePageRootModel> CREATOR = new Parcelable.Creator<HomePageRootModel>() {
        public HomePageRootModel createFromParcel(Parcel source) {
            return new HomePageRootModel(source);
        }

        public HomePageRootModel[] newArray(int size) {
            return new HomePageRootModel[size];
        }
    };

    @Override
    public String toString() {
        return "HomePageRootModel{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    /**
     * 获取主页热门服务
     *
     * @param numberOfPerPage 页面显示数量
     * @param pageNo          页数
     * @param callback        ResultCallback
     */
    public static void getHomeHotServiceRequest(int numberOfPerPage, int pageNo, OkHttpClientManager.ResultCallback<HomePageRootModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("isRecommoned", "1");
        params.put("sortType", "home");
        params.put("numberOfPerPage", String.valueOf(numberOfPerPage));
        params.put("pageNo", String.valueOf(pageNo));
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.HOME_MAIN_HOT_SERVICE, params, callback);
    }
}
