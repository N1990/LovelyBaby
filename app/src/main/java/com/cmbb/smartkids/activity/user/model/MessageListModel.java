package com.cmbb.smartkids.activity.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Package on com.cmbb.smartkids.activity.user.model
 * Created by javon on 15/11/16.
 */
public class MessageListModel implements Parcelable {


    /**
     * page : 1
     * records : 29
     * rows : [{"id":425,"modual":"order","title":"服务预定通知","status":500,"contents":"您预定了服务【测试评价】，订单号：002203060514600003，服务预定成功！","createDate":"2015-11-12 13:27:32","type":"0","relateField":"457","isRead":0},{"id":424,"modual":"order","title":"验证订单通知","status":500,"contents":"您预定的服务订单【002203054742030002】验证成功，请参加活动！","createDate":"2015-11-12 13:20:04","type":"0","relateField":"456","isRead":0},{"id":423,"modual":"order","title":"服务预定通知","status":500,"contents":"您预定了服务【测试评价】，订单号：002203054742030002，服务预定成功！","createDate":"2015-11-12 13:17:55","type":"0","relateField":"456","isRead":0},{"id":422,"modual":"order","title":"验证订单通知","status":500,"contents":"您预定的服务订单【002202348991160001】验证成功，请参加活动！","createDate":"2015-11-11 17:55:40","type":"0","relateField":"455","isRead":0}]
     * total : 2
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":29,"rows":[{"id":425,"modual":"order","title":"服务预定通知","status":500,"contents":"您预定了服务【测试评价】，订单号：002203060514600003，服务预定成功！","createDate":"2015-11-12 13:27:32","type":"0","relateField":"457","isRead":0},{"id":424,"modual":"order","title":"验证订单通知","status":500,"contents":"您预定的服务订单【002203054742030002】验证成功，请参加活动！","createDate":"2015-11-12 13:20:04","type":"0","relateField":"456","isRead":0},{"id":423,"modual":"order","title":"服务预定通知","status":500,"contents":"您预定了服务【测试评价】，订单号：002203054742030002，服务预定成功！","createDate":"2015-11-12 13:17:55","type":"0","relateField":"456","isRead":0},{"id":422,"modual":"order","title":"验证订单通知","status":500,"contents":"您预定的服务订单【002202348991160001】验证成功，请参加活动！","createDate":"2015-11-11 17:55:40","type":"0","relateField":"455","isRead":0}],"total":2,"userdata":""}
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
         * id : 425
         * modual : order
         * title : 服务预定通知
         * status : 500
         * contents : 您预定了服务【测试评价】，订单号：002203060514600003，服务预定成功！
         * createDate : 2015-11-12 13:27:32
         * type : 0
         * relateField : 457
         * isRead : 0
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
            private String modual;
            private String title;
            private int status;
            private String contents;
            private String createDate;
            private String type;
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

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public void setType(String type) {
                this.type = type;
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

            public String getCreateDate() {
                return createDate;
            }

            public String getType() {
                return type;
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
                dest.writeString(this.createDate);
                dest.writeString(this.type);
                dest.writeString(this.relateField);
                dest.writeInt(this.isRead);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.id = in.readInt();
                this.modual = in.readString();
                this.title = in.readString();
                this.status = in.readInt();
                this.contents = in.readString();
                this.createDate = in.readString();
                this.type = in.readString();
                this.relateField = in.readString();
                this.isRead = in.readInt();
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

    public MessageListModel() {
    }

    protected MessageListModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<MessageListModel> CREATOR = new Parcelable.Creator<MessageListModel>() {
        public MessageListModel createFromParcel(Parcel source) {
            return new MessageListModel(source);
        }

        public MessageListModel[] newArray(int size) {
            return new MessageListModel[size];
        }
    };
}
