package com.cmbb.smartkids.activity.diary.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;
import java.util.List;

/**
 * Created by javon on 15/12/18.
 */
public class BabyListModel implements Parcelable {

    /**
     * page : 1
     * records : 1
     * rows : [{"id":37,"diaryCount":0,"babyImgheigth":"640","babyBirthday":"2014-07-02 00:00:00","age":"1岁5个月15天","babySex":"2","babyNike":"妮妮","babyImg":"http://smart.image.alimmdn.com/oldImage/1bc6d88c570b409ca4fed4486c7d1988.jpg","babyImgWidth":"854"}]
     * total : 1
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":1,"rows":[{"id":37,"diaryCount":0,"babyImgheigth":"640","babyBirthday":"2014-07-02 00:00:00","age":"1岁5个月15天","babySex":"2","babyNike":"妮妮","babyImg":"http://smart.image.alimmdn.com/oldImage/1bc6d88c570b409ca4fed4486c7d1988.jpg","babyImgWidth":"854"}],"total":1,"userdata":""}
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
         * id : 37
         * diaryCount : 0
         * babyImgheigth : 640
         * babyBirthday : 2014-07-02 00:00:00
         * age : 1岁5个月15天
         * babySex : 2
         * babyNike : 妮妮
         * babyImg : http://smart.image.alimmdn.com/oldImage/1bc6d88c570b409ca4fed4486c7d1988.jpg
         * babyImgWidth : 854
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
            private int id;
            private int diaryCount;
            private String babyImgheigth;
            private String babyBirthday;
            private String age;
            private String babySex;
            private String babyNike;
            private String babyImg;
            private String babyImgWidth;

            public void setId(int id) {
                this.id = id;
            }

            public void setDiaryCount(int diaryCount) {
                this.diaryCount = diaryCount;
            }

            public void setBabyImgheigth(String babyImgheigth) {
                this.babyImgheigth = babyImgheigth;
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

            public void setBabyImg(String babyImg) {
                this.babyImg = babyImg;
            }

            public void setBabyImgWidth(String babyImgWidth) {
                this.babyImgWidth = babyImgWidth;
            }

            public int getId() {
                return id;
            }

            public int getDiaryCount() {
                return diaryCount;
            }

            public String getBabyImgheigth() {
                return babyImgheigth;
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

            public String getBabyImg() {
                return babyImg;
            }

            public String getBabyImgWidth() {
                return babyImgWidth;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.diaryCount);
                dest.writeString(this.babyImgheigth);
                dest.writeString(this.babyBirthday);
                dest.writeString(this.age);
                dest.writeString(this.babySex);
                dest.writeString(this.babyNike);
                dest.writeString(this.babyImg);
                dest.writeString(this.babyImgWidth);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.id = in.readInt();
                this.diaryCount = in.readInt();
                this.babyImgheigth = in.readString();
                this.babyBirthday = in.readString();
                this.age = in.readString();
                this.babySex = in.readString();
                this.babyNike = in.readString();
                this.babyImg = in.readString();
                this.babyImgWidth = in.readString();
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
                        "id=" + id +
                        ", diaryCount=" + diaryCount +
                        ", babyImgheigth='" + babyImgheigth + '\'' +
                        ", babyBirthday='" + babyBirthday + '\'' +
                        ", age='" + age + '\'' +
                        ", babySex='" + babySex + '\'' +
                        ", babyNike='" + babyNike + '\'' +
                        ", babyImg='" + babyImg + '\'' +
                        ", babyImgWidth='" + babyImgWidth + '\'' +
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

    public BabyListModel() {
    }

    protected BabyListModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<BabyListModel> CREATOR = new Parcelable.Creator<BabyListModel>() {
        public BabyListModel createFromParcel(Parcel source) {
            return new BabyListModel(source);
        }

        public BabyListModel[] newArray(int size) {
            return new BabyListModel[size];
        }
    };

    @Override
    public String toString() {
        return "BabyListModel{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static void getBabyListRequest(int pager, int pagerSize, OkHttpClientManager.ResultCallback<BabyListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("myCenter", "1");
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("token", BaseApplication.token);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.BABY_LIST, params, callback);
    }

    public static void deleteBabyRequest(int babyId, OkHttpClientManager.ResultCallback<BabyListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(babyId));
        params.put("isDelete", "1");
        params.put("token", BaseApplication.token);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.BABY_DELETE, params, callback);
    }
}
