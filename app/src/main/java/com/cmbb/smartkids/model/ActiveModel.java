package com.cmbb.smartkids.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 15:06
 */
public class ActiveModel implements Parcelable {
    private long id;
    private String title;
    private String city;
    private int price;
    private ImageModel img;

    //模拟数据
    public ActiveModel() {
    }

    public ActiveModel(String title, String city, int price, ImageModel img) {
        this.title = title;
        this.city = city;
        this.price = price;
        this.img = img;
    }
    //++++++++++++++++++++++++++++++++++++++++

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ImageModel getImg() {
        return img;
    }

    public void setImg(ImageModel img) {
        this.img = img;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.city);
        dest.writeInt(this.price);
        dest.writeParcelable(this.img, 0);
    }

    protected ActiveModel(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.city = in.readString();
        this.price = in.readInt();
        this.img = in.readParcelable(ImageModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<ActiveModel> CREATOR = new Parcelable.Creator<ActiveModel>() {
        public ActiveModel createFromParcel(Parcel source) {
            return new ActiveModel(source);
        }

        public ActiveModel[] newArray(int size) {
            return new ActiveModel[size];
        }
    };
}
