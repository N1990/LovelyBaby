package com.cmbb.smartkids.activity.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.activity.community.model.ImageModel;

import java.util.ArrayList;

/**
 * Created by javon on 16/1/11.
 */
public class DraftsModel implements Parcelable {
    private int id;
    private int userId;
    private String title;
    private String sort;
    private String sortValue;
    private String content;
    private String time;
    private ArrayList<ImageModel> list = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortValue() {
        return sortValue;
    }

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<ImageModel> getList() {
        return list;
    }

    public void setList(ArrayList<ImageModel> list) {
        this.list = list;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.userId);
        dest.writeString(this.title);
        dest.writeString(this.sort);
        dest.writeString(this.sortValue);
        dest.writeString(this.content);
        dest.writeString(this.time);
        dest.writeTypedList(list);
    }

    public DraftsModel() {
    }

    protected DraftsModel(Parcel in) {
        this.id = in.readInt();
        this.userId = in.readInt();
        this.title = in.readString();
        this.sort = in.readString();
        this.sortValue = in.readString();
        this.content = in.readString();
        this.time = in.readString();
        this.list = in.createTypedArrayList(ImageModel.CREATOR);
    }

    public static final Creator<DraftsModel> CREATOR = new Creator<DraftsModel>() {
        public DraftsModel createFromParcel(Parcel source) {
            return new DraftsModel(source);
        }

        public DraftsModel[] newArray(int size) {
            return new DraftsModel[size];
        }
    };
}
