package com.cmbb.smartkids.activity.diary.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by javon on 15/12/18.
 */
public class BabyInfoModel implements Parcelable {


    /**
     * id : 3
     * babyBirthday : 2015-04-24 00:00:00
     * age : 1岁5个月15天
     * babySex : 1
     * babyNike : 1
     * babyImgWidth :
     * babyImg :
     * createDate : 2015-04-24 09:51:03
     * babyImgHeight :
     */

    private DataEntity data;
    /**
     * data : {"id":3,"babyBirthday":"2015-04-24 00:00:00","age":"1岁5个月15天","babySex":"1","babyNike":"1","babyImgWidth":"","babyImg":"","createDate":"2015-04-24 09:51:03","babyImgHeight":""}
     * msg : 操作成功
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
        private String babyBirthday;
        private String age;
        private String babySex;
        private String babyNike;
        private String babyImgWidth;
        private String babyImg;
        private String createDate;
        private String babyImgHeight;

        public void setId(int id) {
            this.id = id;
        }

        public void setBabyBirthday(String babyBirthday) {
            this.babyBirthday = babyBirthday;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setBabySex(String babySex) {
            this.babySex = babySex;
        }

        public void setBabyNike(String babyNike) {
            this.babyNike = babyNike;
        }

        public void setBabyImgWidth(String babyImgWidth) {
            this.babyImgWidth = babyImgWidth;
        }

        public void setBabyImg(String babyImg) {
            this.babyImg = babyImg;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setBabyImgHeight(String babyImgHeight) {
            this.babyImgHeight = babyImgHeight;
        }

        public int getId() {
            return id;
        }

        public String getBabyBirthday() {
            return babyBirthday;
        }

        public String getAge() {
            return age;
        }

        public String getBabySex() {
            return babySex;
        }

        public String getBabyNike() {
            return babyNike;
        }

        public String getBabyImgWidth() {
            return babyImgWidth;
        }

        public String getBabyImg() {
            return babyImg;
        }

        public String getCreateDate() {
            return createDate;
        }

        public String getBabyImgHeight() {
            return babyImgHeight;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.babyBirthday);
            dest.writeString(this.age);
            dest.writeString(this.babySex);
            dest.writeString(this.babyNike);
            dest.writeString(this.babyImgWidth);
            dest.writeString(this.babyImg);
            dest.writeString(this.createDate);
            dest.writeString(this.babyImgHeight);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.id = in.readInt();
            this.babyBirthday = in.readString();
            this.age = in.readString();
            this.babySex = in.readString();
            this.babyNike = in.readString();
            this.babyImgWidth = in.readString();
            this.babyImg = in.readString();
            this.createDate = in.readString();
            this.babyImgHeight = in.readString();
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
                    "id=" + id +
                    ", babyBirthday='" + babyBirthday + '\'' +
                    ", age='" + age + '\'' +
                    ", babySex='" + babySex + '\'' +
                    ", babyNike='" + babyNike + '\'' +
                    ", babyImgWidth='" + babyImgWidth + '\'' +
                    ", babyImg='" + babyImg + '\'' +
                    ", createDate='" + createDate + '\'' +
                    ", babyImgHeight='" + babyImgHeight + '\'' +
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

    public BabyInfoModel() {
    }

    protected BabyInfoModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<BabyInfoModel> CREATOR = new Parcelable.Creator<BabyInfoModel>() {
        public BabyInfoModel createFromParcel(Parcel source) {
            return new BabyInfoModel(source);
        }

        public BabyInfoModel[] newArray(int size) {
            return new BabyInfoModel[size];
        }
    };

    @Override
    public String toString() {
        return "BabyInfoModel{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
