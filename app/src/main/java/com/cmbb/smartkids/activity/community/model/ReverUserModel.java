package com.cmbb.smartkids.activity.community.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/5 10:25
 */
public class ReverUserModel implements Parcelable {

    private int id;
    private String nickName;
    private String content;
    private String ImgUrl;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nickName);
        dest.writeString(this.content);
        dest.writeString(this.ImgUrl);
        dest.writeString(this.date);
    }

    public ReverUserModel() {
    }

    protected ReverUserModel(Parcel in) {
        this.id = in.readInt();
        this.nickName = in.readString();
        this.content = in.readString();
        this.ImgUrl = in.readString();
        this.date = in.readString();
    }

    public static final Creator<ReverUserModel> CREATOR = new Creator<ReverUserModel>() {
        public ReverUserModel createFromParcel(Parcel source) {
            return new ReverUserModel(source);
        }

        public ReverUserModel[] newArray(int size) {
            return new ReverUserModel[size];
        }
    };

    @Override
    public String toString() {
        return "ReverUserModel{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", content='" + content + '\'' +
                ", ImgUrl='" + ImgUrl + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
