package com.cmbb.smartkids.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 15:19
 */
public class ImageModel implements Parcelable {
    private long id;
    private String imgUrl;
    private String url;
    private String title;
    private int width;
    private int height;

    //模拟数据
    public ImageModel(){}

    public ImageModel(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ImageModel(String imgUrl, String title, String url){
        this.imgUrl = imgUrl;
        this.title = title;
        this.url = url;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.imgUrl);
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    protected ImageModel(Parcel in) {
        this.id = in.readLong();
        this.imgUrl = in.readString();
        this.url = in.readString();
        this.title = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        public ImageModel createFromParcel(Parcel source) {
            return new ImageModel(source);
        }

        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };
}
