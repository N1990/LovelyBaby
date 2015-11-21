package com.cmbb.smartkids.activity.community.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/10 上午10:51
 */
public class TopicTypeModel implements Parcelable {


    /**
     * data : [{"name":"Family话题","value":"FAMILY"},{"name":"潮宝趣图","value":"TREASURE"},{"name":"萌宝厨房","value":"HEALTHCATE"},{"name":"二手精品","value":"ESJPSC"},{"name":"萌宝尖货","value":"BABYSHOPPING"},{"name":"亲子时光","value":"PARENTCHILDTIME"},{"name":"萌宝活动","value":"BABYACTIVITY"}]
     * msg : 操作成功
     */

    private String msg;
    /**
     * name : Family话题
     * value : FAMILY
     */

    private List<DataEntity> data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Parcelable {
        private String name;
        private String value;

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.value);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.name = in.readString();
            this.value = in.readString();
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
        return "TopicTypeModel{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeTypedList(data);
    }

    public TopicTypeModel() {
    }

    protected TopicTypeModel(Parcel in) {
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataEntity.CREATOR);
    }

    public static final Parcelable.Creator<TopicTypeModel> CREATOR = new Parcelable.Creator<TopicTypeModel>() {
        public TopicTypeModel createFromParcel(Parcel source) {
            return new TopicTypeModel(source);
        }

        public TopicTypeModel[] newArray(int size) {
            return new TopicTypeModel[size];
        }
    };
}
