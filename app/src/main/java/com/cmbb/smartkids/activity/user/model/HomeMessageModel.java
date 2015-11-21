package com.cmbb.smartkids.activity.user.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Package on com.cmbb.smartkids.activity.user.model
 * Created by javon on 15/11/19.
 */
public class HomeMessageModel implements Parcelable {

    /**
     * id : 430
     * modual : system
     * title : 官方通知
     * status : 500
     * contents : 您预定了服务【测试评价】，订单号：002203950821000004，服务预定成功！
     * type : 0
     * createDate : 2015-11-13 14:11:23
     * relateField :
     * isRead : 0
     */

    private DataEntity data;
    /**
     * data : {"id":430,"modual":"system","title":"官方通知","status":500,"contents":"您预定了服务【测试评价】，订单号：002203950821000004，服务预定成功！","type":"0","createDate":"2015-11-13 14:11:23","relateField":"","isRead":0}
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
        private int id;
        private String modual;
        private String title;
        private int status;
        private String contents;
        private String type;
        private String createDate;
        private String relateField;
        private int isRead;

        public void setId(int id) {
            this.id = id;
        }

        public void setModual(String modual) {
            this.modual = modual;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setRelateField(String relateField) {
            this.relateField = relateField;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public int getId() {
            return id;
        }

        public String getModual() {
            return modual;
        }

        public String getTitle() {
            return title;
        }

        public int getStatus() {
            return status;
        }

        public String getContents() {
            return contents;
        }

        public String getType() {
            return type;
        }

        public String getCreateDate() {
            return createDate;
        }

        public String getRelateField() {
            return relateField;
        }

        public int getIsRead() {
            return isRead;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.modual);
            dest.writeString(this.title);
            dest.writeInt(this.status);
            dest.writeString(this.contents);
            dest.writeString(this.type);
            dest.writeString(this.createDate);
            dest.writeString(this.relateField);
            dest.writeInt(this.isRead);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.id = in.readInt();
            this.modual = in.readString();
            this.title = in.readString();
            this.status = in.readInt();
            this.contents = in.readString();
            this.type = in.readString();
            this.createDate = in.readString();
            this.relateField = in.readString();
            this.isRead = in.readInt();
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

    public HomeMessageModel() {
    }

    protected HomeMessageModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<HomeMessageModel> CREATOR = new Parcelable.Creator<HomeMessageModel>() {
        public HomeMessageModel createFromParcel(Parcel source) {
            return new HomeMessageModel(source);
        }

        public HomeMessageModel[] newArray(int size) {
            return new HomeMessageModel[size];
        }
    };
}
