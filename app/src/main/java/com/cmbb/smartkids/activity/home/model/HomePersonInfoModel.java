package com.cmbb.smartkids.activity.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/16 下午7:33
 */
public class HomePersonInfoModel implements Parcelable {

    /**
     * status : 1
     * data : {"userId":4,"recommoned":null,"userNike":null,"userSex":null,"backgroundImg":"http://fuck.image.alimmdn.com/14-44-26.jpeg/image_6bfc1eb834a04982a3754225b524965c?t=1442385866000","userBigImg":null,"userBigWidth":null,"userBigHeight":null,"userSmallImg":null,"userSmallWidth":null,"userSmallHeight":null,"loginAccountType":0,"loginTime":"2015-09-16 18:55:41","loginAccount":"15201921714","token":"7b84b58b0edd482dbc94abc82a17fb43","isShutup":0,"shutupTime":null,"isBanned":0,"userAddress":null,"userPhone":null,"userPhoneVersion":null,"province":null,"district":null,"city":null,"userLevel":1,"userPresentation":null,"goldCount":null,"integralCount":null,"attentionCount":null,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户","createDate":null,"createUserId":null,"updateDate":null,"updateUserId":null}],"fans":0,"isAttention":0,"isLogin":0}
     * msg : 更新成功
     */

    private int status;
    private DataEntity data;
    private String msg;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "HomePersonInfoModel{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }



    public static class DataEntity implements Parcelable {
        /**
         * userId : 4
         * recommoned : null
         * userNike : null
         * userSex : null
         * backgroundImg : http://fuck.image.alimmdn.com/14-44-26.jpeg/image_6bfc1eb834a04982a3754225b524965c?t=1442385866000
         * userBigImg : null
         * userBigWidth : null
         * userBigHeight : null
         * userSmallImg : null
         * userSmallWidth : null
         * userSmallHeight : null
         * loginAccountType : 0
         * loginTime : 2015-09-16 18:55:41
         * loginAccount : 15201921714
         * token : 7b84b58b0edd482dbc94abc82a17fb43
         * isShutup : 0
         * shutupTime : null
         * isBanned : 0
         * userAddress : null
         * userPhone : null
         * userPhoneVersion : null
         * province : null
         * district : null
         * city : null
         * userLevel : 1
         * userPresentation : null
         * goldCount : null
         * integralCount : null
         * attentionCount : null
         * userRole : [{"eredarCode":0,"eredarName":"萌宝用户","createDate":null,"createUserId":null,"updateDate":null,"updateUserId":null}]
         * fans : 0
         * isAttention : 0
         * isLogin : 0
         */

        private int userId;
        private int recommoned;
        private String userNike;
        private int userSex;
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
        private String province;
        private String district;
        private String city;
        private int userLevel;
        private String userPresentation;
        private String goldCount;
        private String integralCount;
        private String attentionCount;
        private int fans;
        private int isAttention;
        private int isLogin;
        private List<UserRoleEntity> userRole;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
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

        public String getGoldCount() {
            return goldCount;
        }

        public void setGoldCount(String goldCount) {
            this.goldCount = goldCount;
        }

        public String getIntegralCount() {
            return integralCount;
        }

        public void setIntegralCount(String integralCount) {
            this.integralCount = integralCount;
        }

        public String getAttentionCount() {
            return attentionCount;
        }

        public void setAttentionCount(String attentionCount) {
            this.attentionCount = attentionCount;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getIsAttention() {
            return isAttention;
        }

        public void setIsAttention(int isAttention) {
            this.isAttention = isAttention;
        }

        public int getIsLogin() {
            return isLogin;
        }

        public void setIsLogin(int isLogin) {
            this.isLogin = isLogin;
        }

        public List<UserRoleEntity> getUserRole() {
            return userRole;
        }

        public void setUserRole(List<UserRoleEntity> userRole) {
            this.userRole = userRole;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "userId=" + userId +
                    ", recommoned=" + recommoned +
                    ", userNike='" + userNike + '\'' +
                    ", userSex=" + userSex +
                    ", backgroundImg='" + backgroundImg + '\'' +
                    ", userBigImg='" + userBigImg + '\'' +
                    ", userBigWidth='" + userBigWidth + '\'' +
                    ", userBigHeight='" + userBigHeight + '\'' +
                    ", userSmallImg='" + userSmallImg + '\'' +
                    ", userSmallWidth='" + userSmallWidth + '\'' +
                    ", userSmallHeight='" + userSmallHeight + '\'' +
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
                    ", province='" + province + '\'' +
                    ", district='" + district + '\'' +
                    ", city='" + city + '\'' +
                    ", userLevel=" + userLevel +
                    ", userPresentation='" + userPresentation + '\'' +
                    ", goldCount='" + goldCount + '\'' +
                    ", integralCount='" + integralCount + '\'' +
                    ", attentionCount='" + attentionCount + '\'' +
                    ", fans=" + fans +
                    ", isAttention=" + isAttention +
                    ", isLogin=" + isLogin +
                    ", userRole=" + userRole +
                    '}';
        }


        public static class UserRoleEntity implements Parcelable {
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
            private String createUserId;
            private String updateDate;
            private String updateUserId;

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
            public String toString() {
                return "UserRoleEntity{" +
                        "eredarCode=" + eredarCode +
                        ", eredarName='" + eredarName + '\'' +
                        ", createDate='" + createDate + '\'' +
                        ", createUserId='" + createUserId + '\'' +
                        ", updateDate='" + updateDate + '\'' +
                        ", updateUserId='" + updateUserId + '\'' +
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
                dest.writeString(this.createUserId);
                dest.writeString(this.updateDate);
                dest.writeString(this.updateUserId);
            }

            public UserRoleEntity() {
            }

            protected UserRoleEntity(Parcel in) {
                this.eredarCode = in.readInt();
                this.eredarName = in.readString();
                this.createDate = in.readString();
                this.createUserId = in.readString();
                this.updateDate = in.readString();
                this.updateUserId = in.readString();
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
            dest.writeInt(this.recommoned);
            dest.writeString(this.userNike);
            dest.writeInt(this.userSex);
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
            dest.writeString(this.province);
            dest.writeString(this.district);
            dest.writeString(this.city);
            dest.writeInt(this.userLevel);
            dest.writeString(this.userPresentation);
            dest.writeString(this.goldCount);
            dest.writeString(this.integralCount);
            dest.writeString(this.attentionCount);
            dest.writeInt(this.fans);
            dest.writeInt(this.isAttention);
            dest.writeInt(this.isLogin);
            dest.writeTypedList(userRole);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.userId = in.readInt();
            this.recommoned = in.readInt();
            this.userNike = in.readString();
            this.userSex = in.readInt();
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
            this.province = in.readString();
            this.district = in.readString();
            this.city = in.readString();
            this.userLevel = in.readInt();
            this.userPresentation = in.readString();
            this.goldCount = in.readString();
            this.integralCount = in.readString();
            this.attentionCount = in.readString();
            this.fans = in.readInt();
            this.isAttention = in.readInt();
            this.isLogin = in.readInt();
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
        dest.writeInt(this.status);
        dest.writeParcelable(this.data, 0);
        dest.writeString(this.msg);
    }

    public HomePersonInfoModel() {
    }

    protected HomePersonInfoModel(Parcel in) {
        this.status = in.readInt();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<HomePersonInfoModel> CREATOR = new Parcelable.Creator<HomePersonInfoModel>() {
        public HomePersonInfoModel createFromParcel(Parcel source) {
            return new HomePersonInfoModel(source);
        }

        public HomePersonInfoModel[] newArray(int size) {
            return new HomePersonInfoModel[size];
        }
    };
}
