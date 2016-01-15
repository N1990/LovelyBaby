package com.cmbb.smartkids.activity.message.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/12/15 下午8:09
 */
public class MessageListModel implements Parcelable {


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
        /**
         * id : 37
         * modual : topic
         * title : DOTA 回复了您的话题「测试」
         * contents : hj
         * img :
         * imgWidth :
         * createDate : 2015-12-16 14:59:46
         * relateField : 21249
         * imgHeight :
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

        public List<RowsEntity> getRows() {
            return rows;
        }

        public static class RowsEntity implements Parcelable {
            private int id;
            private String modual;
            private String title;
            private String contents;
            private String img;
            private String imgWidth;
            private String createDate;
            private String relateField;
            private String imgHeight;

            public void setId(int id) {
                this.id = id;
            }

            public void setModual(String modual) {
                this.modual = modual;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setContents(String contents) {
                this.contents = contents;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setImgWidth(String imgWidth) {
                this.imgWidth = imgWidth;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public void setRelateField(String relateField) {
                this.relateField = relateField;
            }

            public void setImgHeight(String imgHeight) {
                this.imgHeight = imgHeight;
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

            public String getContents() {
                return contents;
            }

            public String getImg() {
                return img;
            }

            public String getImgWidth() {
                return imgWidth;
            }

            public String getCreateDate() {
                return createDate;
            }

            public String getRelateField() {
                return relateField;
            }

            public String getImgHeight() {
                return imgHeight;
            }

            @Override
            public String toString() {
                return "RowsEntity{" +
                        "id=" + id +
                        ", modual='" + modual + '\'' +
                        ", title='" + title + '\'' +
                        ", contents='" + contents + '\'' +
                        ", img='" + img + '\'' +
                        ", imgWidth='" + imgWidth + '\'' +
                        ", createDate='" + createDate + '\'' +
                        ", relateField='" + relateField + '\'' +
                        ", imgHeight='" + imgHeight + '\'' +
                        '}';
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
                dest.writeString(this.contents);
                dest.writeString(this.img);
                dest.writeString(this.imgWidth);
                dest.writeString(this.createDate);
                dest.writeString(this.relateField);
                dest.writeString(this.imgHeight);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.id = in.readInt();
                this.modual = in.readString();
                this.title = in.readString();
                this.contents = in.readString();
                this.img = in.readString();
                this.imgWidth = in.readString();
                this.createDate = in.readString();
                this.relateField = in.readString();
                this.imgHeight = in.readString();
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
            dest.writeTypedList(rows);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.page = in.readInt();
            this.records = in.readInt();
            this.total = in.readInt();
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
        return "MessageListModel{" +
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
