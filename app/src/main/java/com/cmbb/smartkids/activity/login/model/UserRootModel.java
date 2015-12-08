package com.cmbb.smartkids.activity.login.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/10 上午9:43
 */
public class UserRootModel implements Parcelable {


    /**
     * userId : 3
     * recommoned : 1
     * userNike : N.Sun
     * userSex : 1
     * userBirthday :
     * backgroundImg : http://smart.image.alimmdn.com/app/test/2015-10-21/image_9d71c71e9c3f4fb592b68b7255e69418
     * userBigImg :
     * userBigWidth :
     * userBigHeight :
     * userSmallImg : http://smart.image.alimmdn.com/app/test/2015-10-20/image_513d5c3863024f6aa35c41d2cae7a8dd
     * userSmallWidth : 1080.0
     * userSmallHeight : 960.0
     * loginAccountType : 0
     * loginTime : 2015-10-26 19:29:06
     * loginAccount : 13818155072
     * token : M2ZjYzUzZjktMGI5Ni00NmM1LWExYTktMmVkNjU5ZmY5YjNh
     * isShutup : 0
     * shutupTime :
     * isBanned : 0
     * userAddress : 而头疼
     * userPhone : 13818155072
     * userPhoneVersion :
     * province : 310000
     * district : 310110
     * city : 310100
     * userLevel : 1
     * userPresentation : Perfect Is Shit
     * backImgWidth : 980.0
     * backImgHeight : 1225.0
     * goldCount :
     * integralCount :
     * attentionCount :
     * userRole : [{"eredarCode":102,"eredarName":"健康达人"},{"eredarCode":100,"eredarName":"美术达人"}]
     * fans : 0
     * isAttention : 0
     * isEredar : 1
     */

    private int userId;
    private int recommoned;
    private String userNike;
    private int userSex;
    private String userBirthday;
    private String backgroundImg;
    private String userBigImg;
    private String userBigWidth;
    private String userBigHeight;
    private String userSmallImg;
    private double userSmallWidth;
    private double userSmallHeight;
    private int loginAccountType;
    private String loginTime;
    private String loginAccount;
    private String token;
    private int isShutup;
    private String shutupTime;
    private int isBanned;
    private String userAddress;
    private String userPhone;
    private String userPhoneVersion;
    private int province;
    private int district;
    private int city;
    private int userLevel;
    private String userPresentation;
    private double backImgWidth;
    private double backImgHeight;
    private String goldCount;
    private String integralCount;
    private String attentionCount;
    private int fans;
    private int isAttention;
    private int isEredar;
    /**
     * eredarCode : 102
     * eredarName : 健康达人
     */

    private List<UserRoleEntity> userRole;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRecommoned(int recommoned) {
        this.recommoned = recommoned;
    }

    public void setUserNike(String userNike) {
        this.userNike = userNike;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public void setUserBigImg(String userBigImg) {
        this.userBigImg = userBigImg;
    }

    public void setUserBigWidth(String userBigWidth) {
        this.userBigWidth = userBigWidth;
    }

    public void setUserBigHeight(String userBigHeight) {
        this.userBigHeight = userBigHeight;
    }

    public void setUserSmallImg(String userSmallImg) {
        this.userSmallImg = userSmallImg;
    }

    public void setUserSmallWidth(double userSmallWidth) {
        this.userSmallWidth = userSmallWidth;
    }

    public void setUserSmallHeight(double userSmallHeight) {
        this.userSmallHeight = userSmallHeight;
    }

    public void setLoginAccountType(int loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setIsShutup(int isShutup) {
        this.isShutup = isShutup;
    }

    public void setShutupTime(String shutupTime) {
        this.shutupTime = shutupTime;
    }

    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserPhoneVersion(String userPhoneVersion) {
        this.userPhoneVersion = userPhoneVersion;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public void setUserPresentation(String userPresentation) {
        this.userPresentation = userPresentation;
    }

    public void setBackImgWidth(double backImgWidth) {
        this.backImgWidth = backImgWidth;
    }

    public void setBackImgHeight(double backImgHeight) {
        this.backImgHeight = backImgHeight;
    }

    public void setGoldCount(String goldCount) {
        this.goldCount = goldCount;
    }

    public void setIntegralCount(String integralCount) {
        this.integralCount = integralCount;
    }

    public void setAttentionCount(String attentionCount) {
        this.attentionCount = attentionCount;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public void setIsAttention(int isAttention) {
        this.isAttention = isAttention;
    }

    public void setIsEredar(int isEredar) {
        this.isEredar = isEredar;
    }

    public void setUserRole(List<UserRoleEntity> userRole) {
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public int getRecommoned() {
        return recommoned;
    }

    public String getUserNike() {
        return userNike;
    }

    public int getUserSex() {
        return userSex;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public String getUserBigImg() {
        return userBigImg;
    }

    public String getUserBigWidth() {
        return userBigWidth;
    }

    public String getUserBigHeight() {
        return userBigHeight;
    }

    public String getUserSmallImg() {
        return userSmallImg;
    }

    public double getUserSmallWidth() {
        return userSmallWidth;
    }

    public double getUserSmallHeight() {
        return userSmallHeight;
    }

    public int getLoginAccountType() {
        return loginAccountType;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public String getToken() {
        return token;
    }

    public int getIsShutup() {
        return isShutup;
    }

    public String getShutupTime() {
        return shutupTime;
    }

    public int getIsBanned() {
        return isBanned;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserPhoneVersion() {
        return userPhoneVersion;
    }

    public int getProvince() {
        return province;
    }

    public int getDistrict() {
        return district;
    }

    public int getCity() {
        return city;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public String getUserPresentation() {
        return userPresentation;
    }

    public double getBackImgWidth() {
        return backImgWidth;
    }

    public double getBackImgHeight() {
        return backImgHeight;
    }

    public String getGoldCount() {
        return goldCount;
    }

    public String getIntegralCount() {
        return integralCount;
    }

    public String getAttentionCount() {
        return attentionCount;
    }

    public int getFans() {
        return fans;
    }

    public int getIsAttention() {
        return isAttention;
    }

    public int getIsEredar() {
        return isEredar;
    }

    public List<UserRoleEntity> getUserRole() {
        return userRole;
    }

    public static class UserRoleEntity implements Parcelable {
        private int eredarCode;
        private String eredarName;

        public void setEredarCode(int eredarCode) {
            this.eredarCode = eredarCode;
        }

        public void setEredarName(String eredarName) {
            this.eredarName = eredarName;
        }

        public int getEredarCode() {
            return eredarCode;
        }

        public String getEredarName() {
            return eredarName;
        }

        @Override
        public String toString() {
            return "UserRoleEntity{" +
                    "eredarCode=" + eredarCode +
                    ", eredarName='" + eredarName + '\'' +
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
        }

        public UserRoleEntity() {
        }

        protected UserRoleEntity(Parcel in) {
            this.eredarCode = in.readInt();
            this.eredarName = in.readString();
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

    @Override
    public String toString() {
        return "UserRootModel{" +
                "userId=" + userId +
                ", recommoned=" + recommoned +
                ", userNike='" + userNike + '\'' +
                ", userSex=" + userSex +
                ", userBirthday='" + userBirthday + '\'' +
                ", backgroundImg='" + backgroundImg + '\'' +
                ", userBigImg='" + userBigImg + '\'' +
                ", userBigWidth='" + userBigWidth + '\'' +
                ", userBigHeight='" + userBigHeight + '\'' +
                ", userSmallImg='" + userSmallImg + '\'' +
                ", userSmallWidth=" + userSmallWidth +
                ", userSmallHeight=" + userSmallHeight +
                ", loginAccountType=" + loginAccountType +
                ", loginTime='" + loginTime + '\'' +
                ", loginAccount='" + loginAccount + '\'' +
                ", token='" + token + '\'' +
                ", isShutup=" + isShutup +
                ", shutupTime='" + shutupTime + '\'' +
                ", isBanned=" + isBanned +
                ", userAddress='" + userAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPhoneVersion='" + userPhoneVersion + '\'' +
                ", province=" + province +
                ", district=" + district +
                ", city=" + city +
                ", userLevel=" + userLevel +
                ", userPresentation='" + userPresentation + '\'' +
                ", backImgWidth=" + backImgWidth +
                ", backImgHeight=" + backImgHeight +
                ", goldCount='" + goldCount + '\'' +
                ", integralCount='" + integralCount + '\'' +
                ", attentionCount='" + attentionCount + '\'' +
                ", fans=" + fans +
                ", isAttention=" + isAttention +
                ", isEredar=" + isEredar +
                ", userRole=" + userRole +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeInt(this.recommoned);
        dest.writeString(this.userNike);
        dest.writeInt(this.userSex);
        dest.writeString(this.userBirthday);
        dest.writeString(this.backgroundImg);
        dest.writeString(this.userBigImg);
        dest.writeString(this.userBigWidth);
        dest.writeString(this.userBigHeight);
        dest.writeString(this.userSmallImg);
        dest.writeDouble(this.userSmallWidth);
        dest.writeDouble(this.userSmallHeight);
        dest.writeInt(this.loginAccountType);
        dest.writeString(this.loginTime);
        dest.writeString(this.loginAccount);
        dest.writeString(this.token);
        dest.writeInt(this.isShutup);
        dest.writeString(this.shutupTime);
        dest.writeInt(this.isBanned);
        dest.writeString(this.userAddress);
        dest.writeString(this.userPhone);
        dest.writeString(this.userPhoneVersion);
        dest.writeInt(this.province);
        dest.writeInt(this.district);
        dest.writeInt(this.city);
        dest.writeInt(this.userLevel);
        dest.writeString(this.userPresentation);
        dest.writeDouble(this.backImgWidth);
        dest.writeDouble(this.backImgHeight);
        dest.writeString(this.goldCount);
        dest.writeString(this.integralCount);
        dest.writeString(this.attentionCount);
        dest.writeInt(this.fans);
        dest.writeInt(this.isAttention);
        dest.writeInt(this.isEredar);
        dest.writeTypedList(userRole);
    }

    public UserRootModel() {
    }

    protected UserRootModel(Parcel in) {
        this.userId = in.readInt();
        this.recommoned = in.readInt();
        this.userNike = in.readString();
        this.userSex = in.readInt();
        this.userBirthday = in.readString();
        this.backgroundImg = in.readString();
        this.userBigImg = in.readString();
        this.userBigWidth = in.readString();
        this.userBigHeight = in.readString();
        this.userSmallImg = in.readString();
        this.userSmallWidth = in.readDouble();
        this.userSmallHeight = in.readDouble();
        this.loginAccountType = in.readInt();
        this.loginTime = in.readString();
        this.loginAccount = in.readString();
        this.token = in.readString();
        this.isShutup = in.readInt();
        this.shutupTime = in.readString();
        this.isBanned = in.readInt();
        this.userAddress = in.readString();
        this.userPhone = in.readString();
        this.userPhoneVersion = in.readString();
        this.province = in.readInt();
        this.district = in.readInt();
        this.city = in.readInt();
        this.userLevel = in.readInt();
        this.userPresentation = in.readString();
        this.backImgWidth = in.readDouble();
        this.backImgHeight = in.readDouble();
        this.goldCount = in.readString();
        this.integralCount = in.readString();
        this.attentionCount = in.readString();
        this.fans = in.readInt();
        this.isAttention = in.readInt();
        this.isEredar = in.readInt();
        this.userRole = in.createTypedArrayList(UserRoleEntity.CREATOR);
    }

    public static final Parcelable.Creator<UserRootModel> CREATOR = new Parcelable.Creator<UserRootModel>() {
        public UserRootModel createFromParcel(Parcel source) {
            return new UserRootModel(source);
        }

        public UserRootModel[] newArray(int size) {
            return new UserRootModel[size];
        }
    };
}
