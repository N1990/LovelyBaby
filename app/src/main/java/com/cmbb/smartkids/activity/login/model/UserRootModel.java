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
     * userId : 103218
     * uid : 124361
     * recommoned : 1
     * userNike : 晶_晶
     * userSex : 1
     * userBirthday :
     * backgroundImg :
     * userBigImg : http://smart.image.alimmdn.com/oldImage/6ef324ab236b4ef8bcb87a3c2f27c7c6mage
     * userBigWidth : 884
     * userBigHeight : 360
     * userSmallImg : http://smart.image.alimmdn.com/oldImage/6ef324ab236b4ef8bcb87a3c2f27c7c6mage
     * userSmallWidth : 884
     * userSmallHeight : 360
     * loginAccountType : 2
     * loginTime : 2016-06-28 17:18:30
     * loginAccount :
     * token : Mjg0NGY3YzYtYWFlYy00OWE3LWJkNGUtOGI5YTljZTk5NmRi
     * isShutup : 0
     * shutupTime :
     * isBanned : 0
     * userAddress :
     * userPhone :
     * userPhoneVersion :
     * province :
     * provinceText :
     * district :
     * districtText :
     * city :
     * cityText :
     * userLevel : 0
     * userPresentation :
     * backImgWidth :
     * backImgHeight :
     * goldCount : 1
     * growthCount : 1
     * fans : 0
     * attentionCount : 1
     * isSign : 0
     * isAttention : 0
     * isEredar : 0
     * isLoginUser : 0
     * userRole : [{"eredarCode":0,"eredarName":"萌宝用户"}]
     */

    private int userId;
    private String uid;
    private int recommoned;
    private String userNike;
    private int userSex;
    private String userBirthday;
    private String backgroundImg;
    private String userBigImg;
    private int userBigWidth;
    private int userBigHeight;
    private String userSmallImg;
    private int userSmallWidth;
    private int userSmallHeight;
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
    private String province;
    private String provinceText;
    private String district;
    private String districtText;
    private String city;
    private String cityText;
    private int userLevel;
    private String userPresentation;
    private String backImgWidth;
    private String backImgHeight;
    private int goldCount;
    private int growthCount;
    private int fans;
    private int attentionCount;
    private int isSign;
    private int isAttention;
    private int isEredar;
    private int isLoginUser;
    /**
     * eredarCode : 0
     * eredarName : 萌宝用户
     */

    private List<UserRoleEntity> userRole;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getRecommoned() {
        return recommoned;
    }

    public void setRecommoned(int recommoned) {
        this.recommoned = recommoned;
    }

    public String getUserNike() {
        return userNike;
    }

    public void setUserNike(String userNike) {
        this.userNike = userNike;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public String getUserBigImg() {
        return userBigImg;
    }

    public void setUserBigImg(String userBigImg) {
        this.userBigImg = userBigImg;
    }

    public int getUserBigWidth() {
        return userBigWidth;
    }

    public void setUserBigWidth(int userBigWidth) {
        this.userBigWidth = userBigWidth;
    }

    public int getUserBigHeight() {
        return userBigHeight;
    }

    public void setUserBigHeight(int userBigHeight) {
        this.userBigHeight = userBigHeight;
    }

    public String getUserSmallImg() {
        return userSmallImg;
    }

    public void setUserSmallImg(String userSmallImg) {
        this.userSmallImg = userSmallImg;
    }

    public int getUserSmallWidth() {
        return userSmallWidth;
    }

    public void setUserSmallWidth(int userSmallWidth) {
        this.userSmallWidth = userSmallWidth;
    }

    public int getUserSmallHeight() {
        return userSmallHeight;
    }

    public void setUserSmallHeight(int userSmallHeight) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceText() {
        return provinceText;
    }

    public void setProvinceText(String provinceText) {
        this.provinceText = provinceText;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictText() {
        return districtText;
    }

    public void setDistrictText(String districtText) {
        this.districtText = districtText;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityText() {
        return cityText;
    }

    public void setCityText(String cityText) {
        this.cityText = cityText;
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

    public String getBackImgWidth() {
        return backImgWidth;
    }

    public void setBackImgWidth(String backImgWidth) {
        this.backImgWidth = backImgWidth;
    }

    public String getBackImgHeight() {
        return backImgHeight;
    }

    public void setBackImgHeight(String backImgHeight) {
        this.backImgHeight = backImgHeight;
    }

    public int getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(int goldCount) {
        this.goldCount = goldCount;
    }

    public int getGrowthCount() {
        return growthCount;
    }

    public void setGrowthCount(int growthCount) {
        this.growthCount = growthCount;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(int attentionCount) {
        this.attentionCount = attentionCount;
    }

    public int getIsSign() {
        return isSign;
    }

    public void setIsSign(int isSign) {
        this.isSign = isSign;
    }

    public int getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(int isAttention) {
        this.isAttention = isAttention;
    }

    public int getIsEredar() {
        return isEredar;
    }

    public void setIsEredar(int isEredar) {
        this.isEredar = isEredar;
    }

    public int getIsLoginUser() {
        return isLoginUser;
    }

    public void setIsLoginUser(int isLoginUser) {
        this.isLoginUser = isLoginUser;
    }

    public List<UserRoleEntity> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<UserRoleEntity> userRole) {
        this.userRole = userRole;
    }

    public static class UserRoleEntity implements Parcelable {
        private int eredarCode;
        private String eredarName;

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
            @Override
            public UserRoleEntity createFromParcel(Parcel source) {
                return new UserRoleEntity(source);
            }

            @Override
            public UserRoleEntity[] newArray(int size) {
                return new UserRoleEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.uid);
        dest.writeInt(this.recommoned);
        dest.writeString(this.userNike);
        dest.writeInt(this.userSex);
        dest.writeString(this.userBirthday);
        dest.writeString(this.backgroundImg);
        dest.writeString(this.userBigImg);
        dest.writeInt(this.userBigWidth);
        dest.writeInt(this.userBigHeight);
        dest.writeString(this.userSmallImg);
        dest.writeInt(this.userSmallWidth);
        dest.writeInt(this.userSmallHeight);
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
        dest.writeString(this.province);
        dest.writeString(this.provinceText);
        dest.writeString(this.district);
        dest.writeString(this.districtText);
        dest.writeString(this.city);
        dest.writeString(this.cityText);
        dest.writeInt(this.userLevel);
        dest.writeString(this.userPresentation);
        dest.writeString(this.backImgWidth);
        dest.writeString(this.backImgHeight);
        dest.writeInt(this.goldCount);
        dest.writeInt(this.growthCount);
        dest.writeInt(this.fans);
        dest.writeInt(this.attentionCount);
        dest.writeInt(this.isSign);
        dest.writeInt(this.isAttention);
        dest.writeInt(this.isEredar);
        dest.writeInt(this.isLoginUser);
        dest.writeTypedList(this.userRole);
    }

    public UserRootModel() {
    }

    protected UserRootModel(Parcel in) {
        this.userId = in.readInt();
        this.uid = in.readString();
        this.recommoned = in.readInt();
        this.userNike = in.readString();
        this.userSex = in.readInt();
        this.userBirthday = in.readString();
        this.backgroundImg = in.readString();
        this.userBigImg = in.readString();
        this.userBigWidth = in.readInt();
        this.userBigHeight = in.readInt();
        this.userSmallImg = in.readString();
        this.userSmallWidth = in.readInt();
        this.userSmallHeight = in.readInt();
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
        this.province = in.readString();
        this.provinceText = in.readString();
        this.district = in.readString();
        this.districtText = in.readString();
        this.city = in.readString();
        this.cityText = in.readString();
        this.userLevel = in.readInt();
        this.userPresentation = in.readString();
        this.backImgWidth = in.readString();
        this.backImgHeight = in.readString();
        this.goldCount = in.readInt();
        this.growthCount = in.readInt();
        this.fans = in.readInt();
        this.attentionCount = in.readInt();
        this.isSign = in.readInt();
        this.isAttention = in.readInt();
        this.isEredar = in.readInt();
        this.isLoginUser = in.readInt();
        this.userRole = in.createTypedArrayList(UserRoleEntity.CREATOR);
    }

    public static final Parcelable.Creator<UserRootModel> CREATOR = new Parcelable.Creator<UserRootModel>() {
        @Override
        public UserRootModel createFromParcel(Parcel source) {
            return new UserRootModel(source);
        }

        @Override
        public UserRootModel[] newArray(int size) {
            return new UserRootModel[size];
        }
    };
}
