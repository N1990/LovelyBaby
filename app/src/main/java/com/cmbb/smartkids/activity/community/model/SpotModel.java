package com.cmbb.smartkids.activity.community.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/19 下午7:11
 */
public class SpotModel implements Parcelable {


    private CommunityDetailModel.DataEntity.SpotListEntity data;


    private String msg;

    public void setData(CommunityDetailModel.DataEntity.SpotListEntity data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CommunityDetailModel.DataEntity.SpotListEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "SpotModel{" +
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

    public SpotModel() {
    }

    protected SpotModel(Parcel in) {
        this.data = in.readParcelable(CommunityDetailModel.DataEntity.SpotListEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<SpotModel> CREATOR = new Parcelable.Creator<SpotModel>() {
        public SpotModel createFromParcel(Parcel source) {
            return new SpotModel(source);
        }

        public SpotModel[] newArray(int size) {
            return new SpotModel[size];
        }
    };
}
