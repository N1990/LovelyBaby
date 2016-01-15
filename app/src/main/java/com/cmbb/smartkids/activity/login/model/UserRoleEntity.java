package com.cmbb.smartkids.activity.login.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/10 上午10:52
 */
public  class UserRoleEntity implements Parcelable {
    /**
     * eredarCode : 0
     * eredarName : 萌宝用户
     * createDate : null
     * createUserId : null
     * updateDate : null
     * updateUserId : null
     */

    private int eredarCode;
    private String eredarName;
    private String createDate;
    private int createUserId;
    private String updateDate;
    private int updateUserId;

    public int getEredarCode() {
        return eredarCode;
    }

    public void setEredarCode(int eredarCode) {
        this.eredarCode = eredarCode;
    }

    public String getEredarName() {
        return eredarName;
    }

    public void setEredarName(String eredarName) {
        this.eredarName = eredarName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "eredarCode=" + eredarCode +
                ", eredarName='" + eredarName + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createUserId=" + createUserId +
                ", updateDate='" + updateDate + '\'' +
                ", updateUserId=" + updateUserId +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.eredarCode);
        dest.writeString(this.eredarName);
        dest.writeString(this.createDate);
        dest.writeInt(this.createUserId);
        dest.writeString(this.updateDate);
        dest.writeInt(this.updateUserId);
    }

    public UserRoleEntity() {
    }

    protected UserRoleEntity(Parcel in) {
        this.eredarCode = in.readInt();
        this.eredarName = in.readString();
        this.createDate = in.readString();
        this.createUserId = in.readInt();
        this.updateDate = in.readString();
        this.updateUserId = in.readInt();
    }

    public static final Parcelable.Creator<UserRoleEntity> CREATOR = new Parcelable.Creator<UserRoleEntity>() {
        public UserRoleEntity createFromParcel(Parcel source) {
            return new UserRoleEntity(source);
        }

        public UserRoleEntity[] newArray(int size) {
            return new UserRoleEntity[size];
        }
    };
}