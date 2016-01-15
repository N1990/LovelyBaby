package com.cmbb.smartkids.activity.diary.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by javon on 15/12/19.
 */
public class BabyDiaryDetailModel implements Parcelable {


    /**
     * isDelete : 0
     * id : 16
     * updateUserId : 1
     * babyBasicInfo : {"id":3,"babyBirthday":"2015-04-24 00:00:00","age":"7个月25天","babySex":"1","babyNike":"1","babyImgWidth":"","babyImg":"","createDate":"2015-04-24 09:51:03","babyImgHeight":""}
     * diaryDate : 2015-04-24 09:51:12
     * diaryImg : [{"img":"http://smart.image.alimmdn.com/oldImage/12bf5673ffc74757b74bf6afae4d6000.jpg","contents":"","imgHeight":"800","imgWidth":"1280"}]
     * privacy : 0
     * createDate : 2015-04-24 09:51:12
     * updateDate :
     * createUserId : 3
     */

    private DataEntity data;
    /**
     * data : {"isDelete":0,"id":16,"updateUserId":1,"babyBasicInfo":{"id":3,"babyBirthday":"2015-04-24 00:00:00","age":"7个月25天","babySex":"1","babyNike":"1","babyImgWidth":"","babyImg":"","createDate":"2015-04-24 09:51:03","babyImgHeight":""},"diaryDate":"2015-04-24 09:51:12","diaryImg":[{"img":"http://smart.image.alimmdn.com/oldImage/12bf5673ffc74757b74bf6afae4d6000.jpg","contents":"","imgHeight":"800","imgWidth":"1280"}],"privacy":0,"createDate":"2015-04-24 09:51:12","updateDate":"","createUserId":3}
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
        private int isDelete;
        private int id;
        private int updateUserId;
        /**
         * id : 3
         * babyBirthday : 2015-04-24 00:00:00
         * age : 7个月25天
         * babySex : 1
         * babyNike : 1
         * babyImgWidth :
         * babyImg :
         * createDate : 2015-04-24 09:51:03
         * babyImgHeight :
         */

        private BabyBasicInfoEntity babyBasicInfo;
        private String diaryDate;
        private int privacy;
        private String createDate;
        private String updateDate;
        private int createUserId;
        /**
         * img : http://smart.image.alimmdn.com/oldImage/12bf5673ffc74757b74bf6afae4d6000.jpg
         * contents :
         * imgHeight : 800
         * imgWidth : 1280
         */

        private List<DiaryImgEntity> diaryImg;

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUpdateUserId(int updateUserId) {
            this.updateUserId = updateUserId;
        }

        public void setBabyBasicInfo(BabyBasicInfoEntity babyBasicInfo) {
            this.babyBasicInfo = babyBasicInfo;
        }

        public void setDiaryDate(String diaryDate) {
            this.diaryDate = diaryDate;
        }

        public void setPrivacy(int privacy) {
            this.privacy = privacy;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public void setDiaryImg(List<DiaryImgEntity> diaryImg) {
            this.diaryImg = diaryImg;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public int getId() {
            return id;
        }

        public int getUpdateUserId() {
            return updateUserId;
        }

        public BabyBasicInfoEntity getBabyBasicInfo() {
            return babyBasicInfo;
        }

        public String getDiaryDate() {
            return diaryDate;
        }

        public int getPrivacy() {
            return privacy;
        }

        public String getCreateDate() {
            return createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public List<DiaryImgEntity> getDiaryImg() {
            return diaryImg;
        }

        public static class BabyBasicInfoEntity implements Parcelable {
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

            public BabyBasicInfoEntity() {
            }

            protected BabyBasicInfoEntity(Parcel in) {
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

            public static final Parcelable.Creator<BabyBasicInfoEntity> CREATOR = new Parcelable.Creator<BabyBasicInfoEntity>() {
                public BabyBasicInfoEntity createFromParcel(Parcel source) {
                    return new BabyBasicInfoEntity(source);
                }

                public BabyBasicInfoEntity[] newArray(int size) {
                    return new BabyBasicInfoEntity[size];
                }
            };

            @Override
            public String toString() {
                return "BabyBasicInfoEntity{" +
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

        public static class DiaryImgEntity implements Parcelable {
            private String img;
            private String contents;
            private String imgHeight;
            private String imgWidth;

            public void setImg(String img) {
                this.img = img;
            }

            public void setContents(String contents) {
                this.contents = contents;
            }

            public void setImgHeight(String imgHeight) {
                this.imgHeight = imgHeight;
            }

            public void setImgWidth(String imgWidth) {
                this.imgWidth = imgWidth;
            }

            public String getImg() {
                return img;
            }

            public String getContents() {
                return contents;
            }

            public String getImgHeight() {
                return imgHeight;
            }

            public String getImgWidth() {
                return imgWidth;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.img);
                dest.writeString(this.contents);
                dest.writeString(this.imgHeight);
                dest.writeString(this.imgWidth);
            }

            public DiaryImgEntity() {
            }

            protected DiaryImgEntity(Parcel in) {
                this.img = in.readString();
                this.contents = in.readString();
                this.imgHeight = in.readString();
                this.imgWidth = in.readString();
            }

            public static final Parcelable.Creator<DiaryImgEntity> CREATOR = new Parcelable.Creator<DiaryImgEntity>() {
                public DiaryImgEntity createFromParcel(Parcel source) {
                    return new DiaryImgEntity(source);
                }

                public DiaryImgEntity[] newArray(int size) {
                    return new DiaryImgEntity[size];
                }
            };

            @Override
            public String toString() {
                return "DiaryImgEntity{" +
                        "img='" + img + '\'' +
                        ", contents='" + contents + '\'' +
                        ", imgHeight='" + imgHeight + '\'' +
                        ", imgWidth='" + imgWidth + '\'' +
                        '}';
            }
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.isDelete);
            dest.writeInt(this.id);
            dest.writeInt(this.updateUserId);
            dest.writeParcelable(this.babyBasicInfo, 0);
            dest.writeString(this.diaryDate);
            dest.writeInt(this.privacy);
            dest.writeString(this.createDate);
            dest.writeString(this.updateDate);
            dest.writeInt(this.createUserId);
            dest.writeTypedList(diaryImg);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.isDelete = in.readInt();
            this.id = in.readInt();
            this.updateUserId = in.readInt();
            this.babyBasicInfo = in.readParcelable(BabyBasicInfoEntity.class.getClassLoader());
            this.diaryDate = in.readString();
            this.privacy = in.readInt();
            this.createDate = in.readString();
            this.updateDate = in.readString();
            this.createUserId = in.readInt();
            this.diaryImg = in.createTypedArrayList(DiaryImgEntity.CREATOR);
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
                    "isDelete=" + isDelete +
                    ", id=" + id +
                    ", updateUserId=" + updateUserId +
                    ", babyBasicInfo=" + babyBasicInfo +
                    ", diaryDate='" + diaryDate + '\'' +
                    ", privacy=" + privacy +
                    ", createDate='" + createDate + '\'' +
                    ", updateDate='" + updateDate + '\'' +
                    ", createUserId=" + createUserId +
                    ", diaryImg=" + diaryImg +
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

    public BabyDiaryDetailModel() {
    }

    protected BabyDiaryDetailModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<BabyDiaryDetailModel> CREATOR = new Parcelable.Creator<BabyDiaryDetailModel>() {
        public BabyDiaryDetailModel createFromParcel(Parcel source) {
            return new BabyDiaryDetailModel(source);
        }

        public BabyDiaryDetailModel[] newArray(int size) {
            return new BabyDiaryDetailModel[size];
        }
    };

    @Override
    public String toString() {
        return "BabyDiaryDetailModel{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
