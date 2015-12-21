package com.cmbb.smartkids.activity.diary.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by javon on 15/12/18.
 */
public class BabyDiaryListModel implements Parcelable {


    /**
     * page : 1
     * records : 2
     * rows : [{"privacy":0,"diaryDate":"2015-05-15 14:21:43","id":57,"diaryImg":[{"img":"http://smart.image.alimmdn.com/oldImage/043135e71c7b40e084b39a1429e4ba36.jpg","contents":"","imgHeight":"711","imgWidth":"600"}],"age":"7个月2天"}]
     * total : 1
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":2,"rows":[{"privacy":0,"diaryDate":"2015-05-15 14:21:43","id":57,"diaryImg":[{"img":"http://smart.image.alimmdn.com/oldImage/043135e71c7b40e084b39a1429e4ba36.jpg","contents":"","imgHeight":"711","imgWidth":"600"}],"age":"7个月2天"}],"total":1,"userdata":""}
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
         * privacy : 0
         * diaryDate : 2015-05-15 14:21:43
         * id : 57
         * diaryImg : [{"img":"http://smart.image.alimmdn.com/oldImage/043135e71c7b40e084b39a1429e4ba36.jpg","contents":"","imgHeight":"711","imgWidth":"600"}]
         * age : 7个月2天
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
            private int privacy;
            private String diaryDate;
            private int id;
            private String age;
            /**
             * img : http://smart.image.alimmdn.com/oldImage/043135e71c7b40e084b39a1429e4ba36.jpg
             * contents :
             * imgHeight : 711
             * imgWidth : 600
             */

            private List<DiaryImgEntity> diaryImg;

            public void setPrivacy(int privacy) {
                this.privacy = privacy;
            }

            public void setDiaryDate(String diaryDate) {
                this.diaryDate = diaryDate;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public void setDiaryImg(List<DiaryImgEntity> diaryImg) {
                this.diaryImg = diaryImg;
            }

            public int getPrivacy() {
                return privacy;
            }

            public String getDiaryDate() {
                return diaryDate;
            }

            public int getId() {
                return id;
            }

            public String getAge() {
                return age;
            }

            public List<DiaryImgEntity> getDiaryImg() {
                return diaryImg;
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
                dest.writeInt(this.privacy);
                dest.writeString(this.diaryDate);
                dest.writeInt(this.id);
                dest.writeString(this.age);
                dest.writeTypedList(diaryImg);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.privacy = in.readInt();
                this.diaryDate = in.readString();
                this.id = in.readInt();
                this.age = in.readString();
                this.diaryImg = in.createTypedArrayList(DiaryImgEntity.CREATOR);
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
                        "privacy=" + privacy +
                        ", diaryDate='" + diaryDate + '\'' +
                        ", id=" + id +
                        ", age='" + age + '\'' +
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

    public BabyDiaryListModel() {
    }

    protected BabyDiaryListModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<BabyDiaryListModel> CREATOR = new Parcelable.Creator<BabyDiaryListModel>() {
        public BabyDiaryListModel createFromParcel(Parcel source) {
            return new BabyDiaryListModel(source);
        }

        public BabyDiaryListModel[] newArray(int size) {
            return new BabyDiaryListModel[size];
        }
    };

    @Override
    public String toString() {
        return "BabyDiaryListModel{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
