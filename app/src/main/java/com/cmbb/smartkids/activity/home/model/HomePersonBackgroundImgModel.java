package com.cmbb.smartkids.activity.home.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/16 下午7:16
 */
public class HomePersonBackgroundImgModel implements Parcelable {

    /**
     * status : 1
     * msg : 背景图片上传成功
     */

    private int status;
    private String msg;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "HomePersonBackgroundImgModel{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.msg);
    }

    public HomePersonBackgroundImgModel() {
    }

    protected HomePersonBackgroundImgModel(Parcel in) {
        this.status = in.readInt();
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<HomePersonBackgroundImgModel> CREATOR = new Parcelable.Creator<HomePersonBackgroundImgModel>() {
        public HomePersonBackgroundImgModel createFromParcel(Parcel source) {
            return new HomePersonBackgroundImgModel(source);
        }

        public HomePersonBackgroundImgModel[] newArray(int size) {
            return new HomePersonBackgroundImgModel[size];
        }
    };
}
