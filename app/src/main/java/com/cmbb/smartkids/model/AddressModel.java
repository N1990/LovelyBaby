package com.cmbb.smartkids.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：JavonFZZ-master
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/20 15:17
 */
public class AddressModel implements Parcelable{
    public String id;
    public String code;
    public String name;
    public String pid;
    public String level;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel d, int flags) {
        d.writeString(id);
        d.writeString(code);
        d.writeString(name);
        d.writeString(pid);
        d.writeString(level);
    }

    public static final Creator<AddressModel> CREATOR = new Creator<AddressModel>() {

        @Override
        public AddressModel[] newArray(int size) {
            return new AddressModel[size];
        }

        @Override
        public AddressModel createFromParcel(Parcel s) {
            AddressModel b = new AddressModel();
            b.id = s.readString();
            b.code = s.readString();
            b.name = s.readString();
            b.pid = s.readString();
            b.level = s.readString();
            return b;
        }
    };
}
