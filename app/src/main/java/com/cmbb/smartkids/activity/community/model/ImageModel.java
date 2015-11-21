package com.cmbb.smartkids.activity.community.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/5 10:20
 */
public class ImageModel implements Parcelable {
    private int id;
    private String content;
    private String imgUrl;

    private boolean isCheck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.content);
        dest.writeString(this.imgUrl);
        dest.writeByte(isCheck ? (byte) 1 : (byte) 0);
    }

    public ImageModel() {
    }

    protected ImageModel(Parcel in) {
        this.id = in.readInt();
        this.content = in.readString();
        this.imgUrl = in.readString();
        this.isCheck = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ImageModel> CREATOR = new Parcelable.Creator<ImageModel>() {
        public ImageModel createFromParcel(Parcel source) {
            return new ImageModel(source);
        }

        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };
}
