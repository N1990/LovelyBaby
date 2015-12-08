package com.cmbb.smartkids.activity.user.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/10/13 上午11:52
 */
public class LogoutModel implements Parcelable {


    /**
     * status : 1
     * msg : 注销成功
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
        return "LogoutModel{" +
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

    public LogoutModel() {
    }

    protected LogoutModel(Parcel in) {
        this.status = in.readInt();
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<LogoutModel> CREATOR = new Parcelable.Creator<LogoutModel>() {
        public LogoutModel createFromParcel(Parcel source) {
            return new LogoutModel(source);
        }

        public LogoutModel[] newArray(int size) {
            return new LogoutModel[size];
        }
    };
}

