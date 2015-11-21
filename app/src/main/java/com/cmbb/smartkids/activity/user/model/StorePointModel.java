package com.cmbb.smartkids.activity.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/19 下午3:59
 */
public class StorePointModel implements Parcelable {

    private DataEntity data;

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
            private int goldCount;
            private String validDate;
            private String remarks;
            private String createDate;

            public void setGoldCount(int goldCount) {
                this.goldCount = goldCount;
            }

            public void setValidDate(String validDate) {
                this.validDate = validDate;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getGoldCount() {
                return goldCount;
            }

            public String getValidDate() {
                return validDate;
            }

            public String getRemarks() {
                return remarks;
            }

            public String getCreateDate() {
                return createDate;
            }

            @Override
            public String toString() {
                return "RowsEntity{" +
                        "goldCount=" + goldCount +
                        ", validDate='" + validDate + '\'' +
                        ", remarks='" + remarks + '\'' +
                        ", createDate='" + createDate + '\'' +
                        '}';
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.goldCount);
                dest.writeString(this.validDate);
                dest.writeString(this.remarks);
                dest.writeString(this.createDate);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.goldCount = in.readInt();
                this.validDate = in.readString();
                this.remarks = in.readString();
                this.createDate = in.readString();
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
        return "StorePointModel{" +
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

    public StorePointModel() {
    }

    protected StorePointModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<StorePointModel> CREATOR = new Parcelable.Creator<StorePointModel>() {
        public StorePointModel createFromParcel(Parcel source) {
            return new StorePointModel(source);
        }

        public StorePointModel[] newArray(int size) {
            return new StorePointModel[size];
        }
    };
}
