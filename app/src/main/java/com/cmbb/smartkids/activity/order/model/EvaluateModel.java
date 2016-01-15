package com.cmbb.smartkids.activity.order.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/16 17:48
 */
public class EvaluateModel implements Parcelable {

    /**
     * data : [{"userId":3,"recommoned":2,"userNike":"N.Sun","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2015-11-5/image_6c56349ebf1b429fb02d8c3ad7fbcea5","userBigImg":"","userBigWidth":"","userBigHeight":"","userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-10-20/image_513d5c3863024f6aa35c41d2cae7a8dd","userSmallWidth":"","userSmallHeight":"","loginAccountType":0,"loginTime":"2015-11-11 17:43:26","loginAccount":"13818155072","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"而头疼","userPhone":"13818155072","userPhoneVersion":"","province":310000,"provinceText":"","district":310110,"districtText":"","city":310100,"cityText":"","userLevel":1,"userPresentation":"Perfect Is Shit ","backImgWidth":500,"backImgHeight":162,"goldCount":3,"integralCount":2,"attentionCount":1,"userRole":[{"eredarCode":100,"eredarName":"美术达人"}],"fans":8,"isAttention":0,"isEredar":0}]
     * msg : 数据加载成功
     */

    private String msg;
    /**
     * userId : 3
     * recommoned : 2
     * userNike : N.Sun
     * userSex : 1
     * userBirthday :
     * backgroundImg : http://smart.image.alimmdn.com/app/test/2015-11-5/image_6c56349ebf1b429fb02d8c3ad7fbcea5
     * userBigImg :
     * userBigWidth :
     * userBigHeight :
     * userSmallImg : http://smart.image.alimmdn.com/app/test/2015-10-20/image_513d5c3863024f6aa35c41d2cae7a8dd
     * userSmallWidth :
     * userSmallHeight :
     * loginAccountType : 0
     * loginTime : 2015-11-11 17:43:26
     * loginAccount : 13818155072
     * token :
     * isShutup : 0
     * shutupTime :
     * isBanned : 0
     * userAddress : 而头疼
     * userPhone : 13818155072
     * userPhoneVersion :
     * province : 310000
     * provinceText :
     * district : 310110
     * districtText :
     * city : 310100
     * cityText :
     * userLevel : 1
     * userPresentation : Perfect Is Shit
     * backImgWidth : 500.0
     * backImgHeight : 162.0
     * goldCount : 3
     * integralCount : 2
     * attentionCount : 1
     * userRole : [{"eredarCode":100,"eredarName":"美术达人"}]
     * fans : 8
     * isAttention : 0
     * isEredar : 0
     */

    private List<DataEntity> data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Parcelable {
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
        private double backImgWidth;
        private double backImgHeight;
        private int goldCount;
        private int integralCount;
        private int attentionCount;
        private int fans;
        private int isAttention;
        private int isEredar;
        private int evaluateLevel; //评价标识
        private String evaluateContent; //评价内容
        /**
         * eredarCode : 100
         * eredarName : 美术达人
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

        public void setBackImgWidth(double backImgWidth) {
            this.backImgWidth = backImgWidth;
        }

        public void setBackImgHeight(double backImgHeight) {
            this.backImgHeight = backImgHeight;
        }

        public void setGoldCount(int goldCount) {
            this.goldCount = goldCount;
        }

        public void setIntegralCount(int integralCount) {
            this.integralCount = integralCount;
        }

        public void setAttentionCount(int attentionCount) {
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

        public double getBackImgWidth() {
            return backImgWidth;
        }

        public double getBackImgHeight() {
            return backImgHeight;
        }

        public int getGoldCount() {
            return goldCount;
        }

        public int getIntegralCount() {
            return integralCount;
        }

        public int getAttentionCount() {
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

        public int getEvaluateLevel() {
            return evaluateLevel;
        }

        public void setEvaluateLevel(int evaluateLevel) {
            this.evaluateLevel = evaluateLevel;
        }

        public String getEvaluateContent() {
            return evaluateContent;
        }

        public void setEvaluateContent(String evaluateContent) {
            this.evaluateContent = evaluateContent;
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
            dest.writeDouble(this.backImgWidth);
            dest.writeDouble(this.backImgHeight);
            dest.writeInt(this.goldCount);
            dest.writeInt(this.integralCount);
            dest.writeInt(this.attentionCount);
            dest.writeInt(this.fans);
            dest.writeInt(this.isAttention);
            dest.writeInt(this.isEredar);
            dest.writeInt(this.evaluateLevel);
            dest.writeString(this.evaluateContent);
            dest.writeTypedList(userRole);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
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
            this.backImgWidth = in.readDouble();
            this.backImgHeight = in.readDouble();
            this.goldCount = in.readInt();
            this.integralCount = in.readInt();
            this.attentionCount = in.readInt();
            this.fans = in.readInt();
            this.isAttention = in.readInt();
            this.isEredar = in.readInt();
            this.evaluateLevel = in.readInt();
            this.evaluateContent = in.readString();
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
        dest.writeString(this.msg);
        dest.writeTypedList(data);
    }

    public EvaluateModel() {
    }

    protected EvaluateModel(Parcel in) {
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataEntity.CREATOR);
    }

    public static final Parcelable.Creator<EvaluateModel> CREATOR = new Parcelable.Creator<EvaluateModel>() {
        public EvaluateModel createFromParcel(Parcel source) {
            return new EvaluateModel(source);
        }

        public EvaluateModel[] newArray(int size) {
            return new EvaluateModel[size];
        }
    };
}
