package com.cmbb.smartkids.activity.community.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/5 10:16
 */
public class UserModel implements Parcelable {

    private int id;
    private String header;
    private String nickName;
    private String identify;
    private String power;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.header);
        dest.writeString(this.nickName);
        dest.writeString(this.identify);
        dest.writeString(this.power);
    }

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        this.id = in.readInt();
        this.header = in.readString();
        this.nickName = in.readString();
        this.identify = in.readString();
        this.power = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", nickName='" + nickName + '\'' +
                ", identify='" + identify + '\'' +
                ", power='" + power + '\'' +
                '}';
    }
}
