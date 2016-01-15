package com.cmbb.smartkids.activity.login.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/9 下午2:26
 */
public class SecurityCodeModel implements Parcelable {

    /**
     * 此类修改慎重  多个地方引用
     * status : 100
     * msg : 发送成功
     */

    private String status;
    private String msg;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.msg);
    }

    public SecurityCodeModel() {
    }

    protected SecurityCodeModel(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<SecurityCodeModel> CREATOR = new Parcelable.Creator<SecurityCodeModel>() {
        public SecurityCodeModel createFromParcel(Parcel source) {
            return new SecurityCodeModel(source);
        }

        public SecurityCodeModel[] newArray(int size) {
            return new SecurityCodeModel[size];
        }
    };

    @Override
    public String toString() {
        return "SecurityCodeModel{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
