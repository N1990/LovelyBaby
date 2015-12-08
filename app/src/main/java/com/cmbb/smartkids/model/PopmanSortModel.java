package com.cmbb.smartkids.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 19:30
 */
public class PopmanSortModel implements Parcelable {


    /**
     * data : [{"name":"营养达人","value":"100"},{"name":"辅食制作达人","value":"101"},{"name":"健康育儿达人","value":"102"},{"name":"中医育儿达人","value":"103"},{"name":"小儿推拿达人","value":"104"},{"name":"早教达人","value":"105"},{"name":"感统训练达人","value":"106"},{"name":"亲子阅读达人","value":"107"},{"name":"手工DIY达人","value":"108"},{"name":"艺术教育达人","value":"109"},{"name":"月子服务达人","value":"110"},{"name":"心理咨询达人","value":"111"},{"name":"美食达人","value":"112"},{"name":"妈妈养生达人","value":"113"},{"name":"亲子乐活达人","value":"114"},{"name":"认证机构","value":"115"},{"name":"官方机构","value":"116"}]
     * msg : 操作成功
     */

    private String msg;
    /**
     * name : 营养达人
     * value : 100
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

        @Override
        public String toString() {
            return "DataEntity{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
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

    public PopmanSortModel() {
    }

    protected PopmanSortModel(Parcel in) {
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataEntity.CREATOR);
    }

    public static final Parcelable.Creator<PopmanSortModel> CREATOR = new Parcelable.Creator<PopmanSortModel>() {
        public PopmanSortModel createFromParcel(Parcel source) {
            return new PopmanSortModel(source);
        }

        public PopmanSortModel[] newArray(int size) {
            return new PopmanSortModel[size];
        }
    };

    @Override
    public String toString() {
        return "PopmanSortModel{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
