package com.cmbb.smartkids.activity.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/21 16:23
 */
public class ModifyUserModel implements Parcelable {

    /**
     * status : 1
     * data : {"userId":9,"recommoned":null,"userNike":"999","userSex":null,"backgroundImg":null,"userBigImg":"http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf","userBigWidth":null,"userBigHeight":null,"userSmallImg":"http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf","userSmallWidth":null,"userSmallHeight":null,"loginAccountType":0,"loginTime":"2015-09-21 16:06:03","loginAccount":"18221365268","token":"d33bcdfc632b4ce2b462229c5a0c70a7","isShutup":0,"shutupTime":null,"isBanned":0,"userAddress":null,"userPhone":"18221365268","userPhoneVersion":null,"province":320000,"district":320102,"city":null,"userLevel":1,"userPresentation":null,"goldCount":null,"integralCount":null,"attentionCount":null,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户","createDate":null,"createUserId":null,"updateDate":null,"updateUserId":null}],"fans":0,"isAttention":0,"isLogin":0}
     * msg : 手机号与验证码不匹配
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

    public static class DataEntity implements Parcelable {
        /**
         * userId : 9
         * recommoned : null
         * userNike : 999
         * userSex : null
         * backgroundImg : null
         * userBigImg : http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf
         * userBigWidth : null
         * userBigHeight : null
         * userSmallImg : http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf
         * userSmallWidth : null
         * userSmallHeight : null
         * loginAccountType : 0
         * loginTime : 2015-09-21 16:06:03
         * loginAccount : 18221365268
         * token : d33bcdfc632b4ce2b462229c5a0c70a7
         * isShutup : 0
         * shutupTime : null
         * isBanned : 0
         * userAddress : null
         * userPhone : 18221365268
         * userPhoneVersion : null
         * province : 320000
         * district : 320102
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
        private String recommoned;
        private String userNike;
        private String userSex;
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
        private int district;
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

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setRecommoned(String recommoned) {
            this.recommoned = recommoned;
        }

        public void setUserNike(String userNike) {
            this.userNike = userNike;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
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

        public void setDistrict(int district) {
            this.district = district;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

        public void setUserPresentation(String userPresentation) {
            this.userPresentation = userPresentation;
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

        public void setIsLogin(int isLogin) {
            this.isLogin = isLogin;
        }

        public void setUserRole(List<UserRoleEntity> userRole) {
            this.userRole = userRole;
        }

        public int getUserId() {
            return userId;
        }

        public String getRecommoned() {
            return recommoned;
        }

        public String getUserNike() {
            return userNike;
        }

        public String getUserSex() {
            return userSex;
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

        public int getDistrict() {
            return district;
        }

        public String getCity() {
            return city;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public String getUserPresentation() {
            return userPresentation;
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

        public int getIsLogin() {
            return isLogin;
        }

        public List<UserRoleEntity> getUserRole() {
            return userRole;
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

            public void setEredarCode(int eredarCode) {
                this.eredarCode = eredarCode;
            }

            public void setEredarName(String eredarName) {
                this.eredarName = eredarName;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public void setCreateUserId(String createUserId) {
                this.createUserId = createUserId;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public void setUpdateUserId(String updateUserId) {
                this.updateUserId = updateUserId;
            }

            public int getEredarCode() {
                return eredarCode;
            }

            public String getEredarName() {
                return eredarName;
            }

            public String getCreateDate() {
                return createDate;
            }

            public String getCreateUserId() {
                return createUserId;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public String getUpdateUserId() {
                return updateUserId;
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

            @Override
            public String toString() {
                return "UserRoleEntity{" +
                        "eredarCode=" + eredarCode +
                        ", eredarName='" + eredarName + '\'' +
                        ", createDate=" + createDate +
                        ", createUserId=" + createUserId +
                        ", updateDate=" + updateDate +
                        ", updateUserId=" + updateUserId +
                        '}';
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.userId);
            dest.writeString(this.recommoned);
            dest.writeString(this.userNike);
            dest.writeString(this.userSex);
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
            dest.writeInt(this.district);
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
            this.recommoned = in.readString();
            this.userNike = in.readString();
            this.userSex = in.readString();
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
            this.district = in.readInt();
            this.city = in.readString();
            this.userLevel = in.readInt();
            this.userPresentation = in.readString();
            this.goldCount = in.readParcelable(Object.class.getClassLoader());
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

        @Override
        public String toString() {
            return "DataEntity{" +
                    "userId=" + userId +
                    ", recommoned=" + recommoned +
                    ", userNike='" + userNike + '\'' +
                    ", userSex=" + userSex +
                    ", backgroundImg=" + backgroundImg +
                    ", userBigImg='" + userBigImg + '\'' +
                    ", userBigWidth=" + userBigWidth +
                    ", userBigHeight=" + userBigHeight +
                    ", userSmallImg='" + userSmallImg + '\'' +
                    ", userSmallWidth=" + userSmallWidth +
                    ", userSmallHeight=" + userSmallHeight +
                    ", loginAccountType=" + loginAccountType +
                    ", loginTime='" + loginTime + '\'' +
                    ", loginAccount='" + loginAccount + '\'' +
                    ", token='" + token + '\'' +
                    ", isShutup=" + isShutup +
                    ", shutupTime=" + shutupTime +
                    ", isBanned=" + isBanned +
                    ", userAddress=" + userAddress +
                    ", userPhone='" + userPhone + '\'' +
                    ", userPhoneVersion=" + userPhoneVersion +
                    ", province=" + province +
                    ", district=" + district +
                    ", city=" + city +
                    ", userLevel=" + userLevel +
                    ", userPresentation=" + userPresentation +
                    ", goldCount=" + goldCount +
                    ", integralCount=" + integralCount +
                    ", attentionCount=" + attentionCount +
                    ", fans=" + fans +
                    ", isAttention=" + isAttention +
                    ", isLogin=" + isLogin +
                    ", userRole=" + userRole +
                    '}';
        }
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

    public ModifyUserModel() {
    }

    protected ModifyUserModel(Parcel in) {
        this.status = in.readInt();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<ModifyUserModel> CREATOR = new Parcelable.Creator<ModifyUserModel>() {
        public ModifyUserModel createFromParcel(Parcel source) {
            return new ModifyUserModel(source);
        }

        public ModifyUserModel[] newArray(int size) {
            return new ModifyUserModel[size];
        }
    };

    @Override
    public String toString() {
        return "ModifyUserModel{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
