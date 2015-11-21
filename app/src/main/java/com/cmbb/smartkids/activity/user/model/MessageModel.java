package com.cmbb.smartkids.activity.user.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/16 14:11
 */
public class MessageModel implements Parcelable {
    private int id;
    private int type;
    private String message;
    private String orderCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.type);
        dest.writeString(this.message);
        dest.writeString(this.orderCode);
    }

    public MessageModel() {
    }

    protected MessageModel(Parcel in) {
        this.id = in.readInt();
        this.type = in.readInt();
        this.message = in.readString();
        this.orderCode = in.readString();
    }

    public static final Parcelable.Creator<MessageModel> CREATOR = new Parcelable.Creator<MessageModel>() {
        public MessageModel createFromParcel(Parcel source) {
            return new MessageModel(source);
        }

        public MessageModel[] newArray(int size) {
            return new MessageModel[size];
        }
    };

    @Override
    public String toString() {
        return "MessageModel{" +
                "id=" + id +
                ", type=" + type +
                ", message='" + message + '\'' +
                ", orderCode='" + orderCode + '\'' +
                '}';
    }
}
