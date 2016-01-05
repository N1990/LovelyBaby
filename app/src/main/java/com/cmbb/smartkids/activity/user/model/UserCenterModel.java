package com.cmbb.smartkids.activity.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/21 11:22
 */
public class UserCenterModel implements Parcelable {


    /**
     * userId : 9
     * recommoned : 2
     * userNike : 998
     * userSex : 1
     * userBirthday :
     * backgroundImg : http://smart.image.alimmdn.com/app/test/2015-11-18/image_185291737ffd482a85872a4958858dd3
     * userBigImg :
     * userBigWidth :
     * userBigHeight :
     * userSmallImg : http://smart.image.alimmdn.com/app/test/2015-10-16/image_f1c049b4c8874cc8a4b77f15bffd3a42
     * userSmallWidth : 1184.0
     * userSmallHeight : 2112.0
     * loginAccountType : 0
     * loginTime : 2015-11-19 19:39:28
     * loginAccount : 18221365268
     * token : MGI4ZDI0OGYtYzgzOS00M2UwLTk4MjgtMDg0NDUwNDIxNzMx
     * isShutup : 0
     * shutupTime :
     * isBanned : 0
     * userAddress : ghkjnbbj
     * userPhone : 18221365268
     * userPhoneVersion :
     * province : 230000
     * provinceText :
     * district : 230102
     * districtText :
     * city : 230100
     * cityText :
     * userLevel : 1
     * userPresentation :
     * backImgWidth : 2322.0
     * backImgHeight : 2322.0
     * goldCount : 0
     * growthCount : 1
     * fans : 0
     * attentionCount : 3
     * isSign : 0
     * isAttention : 0
     * isEredar : 1
     * isLoginUser : 0
     * userRole : [{"eredarCode":105,"eredarName":"早教达人"}]
     */

    private DataEntity data;
    /**
     * data : {"userId":9,"recommoned":2,"userNike":"998","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2015-11-18/image_185291737ffd482a85872a4958858dd3","userBigImg":"","userBigWidth":"","userBigHeight":"","userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-10-16/image_f1c049b4c8874cc8a4b77f15bffd3a42","userSmallWidth":"1184.0","userSmallHeight":"2112.0","loginAccountType":0,"loginTime":"2015-11-19 19:39:28","loginAccount":"18221365268","token":"MGI4ZDI0OGYtYzgzOS00M2UwLTk4MjgtMDg0NDUwNDIxNzMx","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"ghkjnbbj","userPhone":"18221365268","userPhoneVersion":"","province":230000,"provinceText":"","district":230102,"districtText":"","city":230100,"cityText":"","userLevel":1,"userPresentation":"","backImgWidth":"2322.0","backImgHeight":"2322.0","goldCount":0,"growthCount":1,"fans":0,"attentionCount":3,"isSign":0,"isAttention":0,"isEredar":1,"isLoginUser":0,"userRole":[{"eredarCode":105,"eredarName":"早教达人"}]}
     * msg : 数据加载成功
     */

    private String msg;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public static class DataEntity implements Parcelable {
        private int userId;
        private int recommoned;
        private int uid;
        private String userNike;
        private int userSex;
        private String userBirthday;
        private String backgroundImg;
        private String userBigImg;
        private String userBigWidth;
        private String userBigHeight;
        private String userSmallImg;
        private String userSmallWidth;
        private String userSmallHeight;
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
        private String provinceText;
        private int district;
        private String districtText;
        private int city;
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
         * eredarCode : 105
         * eredarName : 早教达人
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

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setUserSmallWidth(String userSmallWidth) {
            this.userSmallWidth = userSmallWidth;
        }

        public void setUserSmallHeight(String userSmallHeight) {
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

        public void setProvinceText(String provinceText) {
            this.provinceText = provinceText;
        }

        public void setDistrict(int district) {
            this.district = district;
        }

        public void setDistrictText(String districtText) {
            this.districtText = districtText;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public void setCityText(String cityText) {
            this.cityText = cityText;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

        public void setUserPresentation(String userPresentation) {
            this.userPresentation = userPresentation;
        }

        public void setBackImgWidth(String backImgWidth) {
            this.backImgWidth = backImgWidth;
        }

        public void setBackImgHeight(String backImgHeight) {
            this.backImgHeight = backImgHeight;
        }

        public void setGoldCount(int goldCount) {
            this.goldCount = goldCount;
        }

        public void setGrowthCount(int growthCount) {
            this.growthCount = growthCount;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public void setAttentionCount(int attentionCount) {
            this.attentionCount = attentionCount;
        }

        public void setIsSign(int isSign) {
            this.isSign = isSign;
        }

        public void setIsAttention(int isAttention) {
            this.isAttention = isAttention;
        }

        public void setIsEredar(int isEredar) {
            this.isEredar = isEredar;
        }

        public void setIsLoginUser(int isLoginUser) {
            this.isLoginUser = isLoginUser;
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

        public String getUserSmallWidth() {
            return userSmallWidth;
        }

        public String getUserSmallHeight() {
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

        public String getProvinceText() {
            return provinceText;
        }

        public int getDistrict() {
            return district;
        }

        public String getDistrictText() {
            return districtText;
        }

        public int getCity() {
            return city;
        }

        public String getCityText() {
            return cityText;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public String getUserPresentation() {
            return userPresentation;
        }

        public String getBackImgWidth() {
            return backImgWidth;
        }

        public String getBackImgHeight() {
            return backImgHeight;
        }

        public int getGoldCount() {
            return goldCount;
        }

        public int getGrowthCount() {
            return growthCount;
        }

        public int getFans() {
            return fans;
        }

        public int getAttentionCount() {
            return attentionCount;
        }

        public int getIsSign() {
            return isSign;
        }

        public int getIsAttention() {
            return isAttention;
        }

        public int getIsEredar() {
            return isEredar;
        }

        public int getIsLoginUser() {
            return isLoginUser;
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
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.userId);
            dest.writeInt(this.uid);
            dest.writeInt(this.recommoned);
            dest.writeString(this.userNike);
            dest.writeInt(this.userSex);
            dest.writeString(this.userBirthday);
            dest.writeString(this.backgroundImg);
            dest.writeString(this.userBigImg);
            dest.writeString(this.userBigWidth);
            dest.writeString(this.userBigHeight);
            dest.writeString(this.userSmallImg);
            dest.writeString(this.userSmallWidth);
            dest.writeString(this.userSmallHeight);
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
            dest.writeString(this.provinceText);
            dest.writeInt(this.district);
            dest.writeString(this.districtText);
            dest.writeInt(this.city);
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
            dest.writeTypedList(userRole);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.userId = in.readInt();
            this.uid = in.readInt();
            this.recommoned = in.readInt();
            this.userNike = in.readString();
            this.userSex = in.readInt();
            this.userBirthday = in.readString();
            this.backgroundImg = in.readString();
            this.userBigImg = in.readString();
            this.userBigWidth = in.readString();
            this.userBigHeight = in.readString();
            this.userSmallImg = in.readString();
            this.userSmallWidth = in.readString();
            this.userSmallHeight = in.readString();
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
            this.provinceText = in.readString();
            this.district = in.readInt();
            this.districtText = in.readString();
            this.city = in.readInt();
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

        public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            public DataEntity[] newArray(int size) {
                return new DataEntity[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, 0);
        dest.writeString(this.msg);
    }

    public UserCenterModel() {
    }

    protected UserCenterModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<UserCenterModel> CREATOR = new Parcelable.Creator<UserCenterModel>() {
        public UserCenterModel createFromParcel(Parcel source) {
            return new UserCenterModel(source);
        }

        public UserCenterModel[] newArray(int size) {
            return new UserCenterModel[size];
        }
    };
}
