package com.cmbb.smartkids.activity.home.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/10 下午3:53
 */
public class RecommonedEredarModel implements Parcelable {


    private int userId;
    private String userNike;
    private String userSex;
    private String userBigImg;
    private String userBigWidth;
    private String userBigHeight;
    private String userSmallImg;
    private String userSmallWidth;
    private String userSmallHeight;
    private int loginAccountType;
    private String loginTime;
    private String loginAccount;
    private String loginPassword;
    private String loginAccessToken;
    private String loginToken;
    private int isShutup;
    private String shutupTime;
    private int isBanned;
    private String userAddress;
    private String userPhone;
    private String userPhoneVersion;
    private int province;
    private int district;
    private String city;
    private int userLevel;
    private String userPresentation;
    private int isRecommoned;
    private String createDate;
    private String createUserId;
    private String updateDate;
    private String updateUserId;

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public String getUserNike() {
        return userNike;
    }

    public void setUserNike(String userNike) {
        this.userNike = userNike;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserBigImg() {
        return userBigImg;
    }

    public void setUserBigImg(String userBigImg) {
        this.userBigImg = userBigImg;
    }

    public String getUserBigWidth() {
        return userBigWidth;
    }

    public void setUserBigWidth(String userBigWidth) {
        this.userBigWidth = userBigWidth;
    }

    public String getUserBigHeight() {
        return userBigHeight;
    }

    public void setUserBigHeight(String userBigHeight) {
        this.userBigHeight = userBigHeight;
    }

    public String getUserSmallImg() {
        return userSmallImg;
    }

    public void setUserSmallImg(String userSmallImg) {
        this.userSmallImg = userSmallImg;
    }

    public String getUserSmallWidth() {
        return userSmallWidth;
    }

    public void setUserSmallWidth(String userSmallWidth) {
        this.userSmallWidth = userSmallWidth;
    }

    public String getUserSmallHeight() {
        return userSmallHeight;
    }

    public void setUserSmallHeight(String userSmallHeight) {
        this.userSmallHeight = userSmallHeight;
    }

    public int getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(int loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginAccessToken() {
        return loginAccessToken;
    }

    public void setLoginAccessToken(String loginAccessToken) {
        this.loginAccessToken = loginAccessToken;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public int getIsShutup() {
        return isShutup;
    }

    public void setIsShutup(int isShutup) {
        this.isShutup = isShutup;
    }

    public String getShutupTime() {
        return shutupTime;
    }

    public void setShutupTime(String shutupTime) {
        this.shutupTime = shutupTime;
    }

    public int getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPhoneVersion() {
        return userPhoneVersion;
    }

    public void setUserPhoneVersion(String userPhoneVersion) {
        this.userPhoneVersion = userPhoneVersion;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserPresentation() {
        return userPresentation;
    }

    public void setUserPresentation(String userPresentation) {
        this.userPresentation = userPresentation;
    }

    public int getIsRecommoned() {
        return isRecommoned;
    }

    public void setIsRecommoned(int isRecommoned) {
        this.isRecommoned = isRecommoned;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.userNike);
        dest.writeString(this.userSex);
        dest.writeString(this.userBigImg);
        dest.writeString(this.userBigWidth);
        dest.writeString(this.userBigHeight);
        dest.writeString(this.userSmallImg);
        dest.writeString(this.userSmallWidth);
        dest.writeString(this.userSmallHeight);
        dest.writeInt(this.loginAccountType);
        dest.writeString(this.loginTime);
        dest.writeString(this.loginAccount);
        dest.writeString(this.loginPassword);
        dest.writeString(this.loginAccessToken);
        dest.writeString(this.loginToken);
        dest.writeInt(this.isShutup);
        dest.writeString(this.shutupTime);
        dest.writeInt(this.isBanned);
        dest.writeString(this.userAddress);
        dest.writeString(this.userPhone);
        dest.writeString(this.userPhoneVersion);
        dest.writeInt(this.province);
        dest.writeInt(this.district);
        dest.writeString(this.city);
        dest.writeInt(this.userLevel);
        dest.writeString(this.userPresentation);
        dest.writeInt(this.isRecommoned);
        dest.writeString(this.createDate);
        dest.writeString(this.createUserId);
        dest.writeString(this.updateDate);
        dest.writeString(this.updateUserId);
    }

    public RecommonedEredarModel() {
    }

    protected RecommonedEredarModel(Parcel in) {
        this.userId = in.readInt();
        this.userNike = in.readString();
        this.userSex = in.readString();
        this.userBigImg = in.readString();
        this.userBigWidth = in.readString();
        this.userBigHeight = in.readString();
        this.userSmallImg = in.readString();
        this.userSmallWidth = in.readString();
        this.userSmallHeight = in.readString();
        this.loginAccountType = in.readInt();
        this.loginTime = in.readString();
        this.loginAccount = in.readString();
        this.loginPassword = in.readString();
        this.loginAccessToken = in.readString();
        this.loginToken = in.readString();
        this.isShutup = in.readInt();
        this.shutupTime = in.readString();
        this.isBanned = in.readInt();
        this.userAddress = in.readString();
        this.userPhone = in.readString();
        this.userPhoneVersion = in.readString();
        this.province = in.readInt();
        this.district = in.readInt();
        this.city = in.readString();
        this.userLevel = in.readInt();
        this.userPresentation = in.readString();
        this.isRecommoned = in.readInt();
        this.createDate = in.readString();
        this.createUserId = in.readString();
        this.updateDate = in.readString();
        this.updateUserId = in.readString();
    }

    public static final Parcelable.Creator<RecommonedEredarModel> CREATOR = new Parcelable.Creator<RecommonedEredarModel>() {
        public RecommonedEredarModel createFromParcel(Parcel source) {
            return new RecommonedEredarModel(source);
        }

        public RecommonedEredarModel[] newArray(int size) {
            return new RecommonedEredarModel[size];
        }
    };

    @Override
    public String toString() {
        return "RecommonedEredarModel{" +
                "id=" + userId +
                ", userNike='" + userNike + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBigImg='" + userBigImg + '\'' +
                ", userBigWidth='" + userBigWidth + '\'' +
                ", userBigHeight='" + userBigHeight + '\'' +
                ", userSmallImg='" + userSmallImg + '\'' +
                ", userSmallWidth='" + userSmallWidth + '\'' +
                ", userSmallHeight='" + userSmallHeight + '\'' +
                ", loginAccountType=" + loginAccountType +
                ", loginTime='" + loginTime + '\'' +
                ", loginAccount='" + loginAccount + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", loginAccessToken='" + loginAccessToken + '\'' +
                ", loginToken='" + loginToken + '\'' +
                ", isShutup=" + isShutup +
                ", shutupTime='" + shutupTime + '\'' +
                ", isBanned=" + isBanned +
                ", userAddress='" + userAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPhoneVersion='" + userPhoneVersion + '\'' +
                ", province=" + province +
                ", district=" + district +
                ", city='" + city + '\'' +
                ", userLevel=" + userLevel +
                ", userPresentation='" + userPresentation + '\'' +
                ", isRecommoned=" + isRecommoned +
                ", createDate='" + createDate + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateUserId='" + updateUserId + '\'' +
                '}';
    }
}
