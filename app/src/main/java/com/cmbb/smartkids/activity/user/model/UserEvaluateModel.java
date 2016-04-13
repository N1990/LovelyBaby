package com.cmbb.smartkids.activity.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;
import java.util.List;

/**
 * Created by javon on 16/3/21.
 */
public class UserEvaluateModel implements Parcelable {


    /**
     * page : 1
     * records : 1
     * rows : [{"evaluateContent":"1221","evaluateType":1,"evaluateDate":"2015-11-11 12:21:11","title":"萌宝派宝爸宝妈群开通啦","userBasicInfo":{"userId":8,"recommoned":1,"userNike":"fysq","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2015-11-6/image_581076c1bbb4457b89d851364b2bcea0","userBigImg":"","userBigWidth":"","userBigHeight":"","userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-10-26/image_4ac31e00b9d44dcb8f94352c640f0bc0","userSmallWidth":"720","userSmallHeight":"1280","loginAccountType":0,"loginTime":"2015-11-06 17:47:07","loginAccount":"15901718791","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"shshwhw","userPhone":"15901718791","userPhoneVersion":"","province":120000,"provinceText":"","district":120101,"districtText":"","city":120100,"cityText":"","userLevel":1,"userPresentation":"","backImgWidth":"720","backImgHeight":"1280","goldCount":1,"integralCount":1,"attentionCount":2,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户"}],"fans":0,"isAttention":0,"isEredar":0}}]
     * total : 1
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":1,"rows":[{"evaluateContent":"1221","evaluateType":1,"evaluateDate":"2015-11-11 12:21:11","title":"萌宝派宝爸宝妈群开通啦","userBasicInfo":{"userId":8,"recommoned":1,"userNike":"fysq","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2015-11-6/image_581076c1bbb4457b89d851364b2bcea0","userBigImg":"","userBigWidth":"","userBigHeight":"","userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-10-26/image_4ac31e00b9d44dcb8f94352c640f0bc0","userSmallWidth":"720","userSmallHeight":"1280","loginAccountType":0,"loginTime":"2015-11-06 17:47:07","loginAccount":"15901718791","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"shshwhw","userPhone":"15901718791","userPhoneVersion":"","province":120000,"provinceText":"","district":120101,"districtText":"","city":120100,"cityText":"","userLevel":1,"userPresentation":"","backImgWidth":"720","backImgHeight":"1280","goldCount":1,"integralCount":1,"attentionCount":2,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户"}],"fans":0,"isAttention":0,"isEredar":0}}],"total":1,"userdata":""}
     * msg : 数据加载成功
     */

    private String msg;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataEntity implements Parcelable {
        private int page;
        private int records;
        private int total;
        private String userdata;
        /**
         * evaluateContent : 1221
         * evaluateType : 1
         * evaluateDate : 2015-11-11 12:21:11
         * title : 萌宝派宝爸宝妈群开通啦
         * userBasicInfo : {"userId":8,"recommoned":1,"userNike":"fysq","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2015-11-6/image_581076c1bbb4457b89d851364b2bcea0","userBigImg":"","userBigWidth":"","userBigHeight":"","userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-10-26/image_4ac31e00b9d44dcb8f94352c640f0bc0","userSmallWidth":"720","userSmallHeight":"1280","loginAccountType":0,"loginTime":"2015-11-06 17:47:07","loginAccount":"15901718791","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"shshwhw","userPhone":"15901718791","userPhoneVersion":"","province":120000,"provinceText":"","district":120101,"districtText":"","city":120100,"cityText":"","userLevel":1,"userPresentation":"","backImgWidth":"720","backImgHeight":"1280","goldCount":1,"integralCount":1,"attentionCount":2,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户"}],"fans":0,"isAttention":0,"isEredar":0}
         */

        private List<RowsEntity> rows;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getRecords() {
            return records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getUserdata() {
            return userdata;
        }

        public void setUserdata(String userdata) {
            this.userdata = userdata;
        }

        public List<RowsEntity> getRows() {
            return rows;
        }

        public void setRows(List<RowsEntity> rows) {
            this.rows = rows;
        }

        public static class RowsEntity implements Parcelable {
            private String evaluateContent;
            private int evaluateType;
            private String evaluateDate;
            private String title;
            /**
             * userId : 8
             * recommoned : 1
             * userNike : fysq
             * userSex : 1
             * userBirthday :
             * backgroundImg : http://smart.image.alimmdn.com/app/test/2015-11-6/image_581076c1bbb4457b89d851364b2bcea0
             * userBigImg :
             * userBigWidth :
             * userBigHeight :
             * userSmallImg : http://smart.image.alimmdn.com/app/test/2015-10-26/image_4ac31e00b9d44dcb8f94352c640f0bc0
             * userSmallWidth : 720
             * userSmallHeight : 1280
             * loginAccountType : 0
             * loginTime : 2015-11-06 17:47:07
             * loginAccount : 15901718791
             * token :
             * isShutup : 0
             * shutupTime :
             * isBanned : 0
             * userAddress : shshwhw
             * userPhone : 15901718791
             * userPhoneVersion :
             * province : 120000
             * provinceText :
             * district : 120101
             * districtText :
             * city : 120100
             * cityText :
             * userLevel : 1
             * userPresentation :
             * backImgWidth : 720
             * backImgHeight : 1280
             * goldCount : 1
             * integralCount : 1
             * attentionCount : 2
             * userRole : [{"eredarCode":0,"eredarName":"萌宝用户"}]
             * fans : 0
             * isAttention : 0
             * isEredar : 0
             */

            private UserBasicInfoEntity userBasicInfo;

            public String getEvaluateContent() {
                return evaluateContent;
            }

            public void setEvaluateContent(String evaluateContent) {
                this.evaluateContent = evaluateContent;
            }

            public int getEvaluateType() {
                return evaluateType;
            }

            public void setEvaluateType(int evaluateType) {
                this.evaluateType = evaluateType;
            }

            public String getEvaluateDate() {
                return evaluateDate;
            }

            public void setEvaluateDate(String evaluateDate) {
                this.evaluateDate = evaluateDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public UserBasicInfoEntity getUserBasicInfo() {
                return userBasicInfo;
            }

            public void setUserBasicInfo(UserBasicInfoEntity userBasicInfo) {
                this.userBasicInfo = userBasicInfo;
            }

            public static class UserBasicInfoEntity implements Parcelable {
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
                private String backImgWidth;
                private String backImgHeight;
                private int goldCount;
                private int integralCount;
                private int attentionCount;
                private int fans;
                private int isAttention;
                private int isEredar;
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

                public int getProvince() {
                    return province;
                }

                public void setProvince(int province) {
                    this.province = province;
                }

                public String getProvinceText() {
                    return provinceText;
                }

                public void setProvinceText(String provinceText) {
                    this.provinceText = provinceText;
                }

                public int getDistrict() {
                    return district;
                }

                public void setDistrict(int district) {
                    this.district = district;
                }

                public String getDistrictText() {
                    return districtText;
                }

                public void setDistrictText(String districtText) {
                    this.districtText = districtText;
                }

                public int getCity() {
                    return city;
                }

                public void setCity(int city) {
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

                public int getIntegralCount() {
                    return integralCount;
                }

                public void setIntegralCount(int integralCount) {
                    this.integralCount = integralCount;
                }

                public int getAttentionCount() {
                    return attentionCount;
                }

                public void setAttentionCount(int attentionCount) {
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

                public int getIsEredar() {
                    return isEredar;
                }

                public void setIsEredar(int isEredar) {
                    this.isEredar = isEredar;
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
                    dest.writeInt(this.integralCount);
                    dest.writeInt(this.attentionCount);
                    dest.writeInt(this.fans);
                    dest.writeInt(this.isAttention);
                    dest.writeInt(this.isEredar);
                    dest.writeTypedList(userRole);
                }

                public UserBasicInfoEntity() {
                }

                protected UserBasicInfoEntity(Parcel in) {
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
                    this.backImgWidth = in.readString();
                    this.backImgHeight = in.readString();
                    this.goldCount = in.readInt();
                    this.integralCount = in.readInt();
                    this.attentionCount = in.readInt();
                    this.fans = in.readInt();
                    this.isAttention = in.readInt();
                    this.isEredar = in.readInt();
                    this.userRole = in.createTypedArrayList(UserRoleEntity.CREATOR);
                }

                public static final Parcelable.Creator<UserBasicInfoEntity> CREATOR = new Parcelable.Creator<UserBasicInfoEntity>() {
                    @Override
                    public UserBasicInfoEntity createFromParcel(Parcel source) {
                        return new UserBasicInfoEntity(source);
                    }

                    @Override
                    public UserBasicInfoEntity[] newArray(int size) {
                        return new UserBasicInfoEntity[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.evaluateContent);
                dest.writeInt(this.evaluateType);
                dest.writeString(this.evaluateDate);
                dest.writeString(this.title);
                dest.writeParcelable(this.userBasicInfo, flags);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.evaluateContent = in.readString();
                this.evaluateType = in.readInt();
                this.evaluateDate = in.readString();
                this.title = in.readString();
                this.userBasicInfo = in.readParcelable(UserBasicInfoEntity.class.getClassLoader());
            }

            public static final Parcelable.Creator<RowsEntity> CREATOR = new Parcelable.Creator<RowsEntity>() {
                @Override
                public RowsEntity createFromParcel(Parcel source) {
                    return new RowsEntity(source);
                }

                @Override
                public RowsEntity[] newArray(int size) {
                    return new RowsEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.page);
            dest.writeInt(this.records);
            dest.writeInt(this.total);
            dest.writeString(this.userdata);
            dest.writeTypedList(rows);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.page = in.readInt();
            this.records = in.readInt();
            this.total = in.readInt();
            this.userdata = in.readString();
            this.rows = in.createTypedArrayList(RowsEntity.CREATOR);
        }

        public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
            @Override
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            @Override
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
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.msg);
    }

    public UserEvaluateModel() {
    }

    protected UserEvaluateModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<UserEvaluateModel> CREATOR = new Parcelable.Creator<UserEvaluateModel>() {
        @Override
        public UserEvaluateModel createFromParcel(Parcel source) {
            return new UserEvaluateModel(source);
        }

        @Override
        public UserEvaluateModel[] newArray(int size) {
            return new UserEvaluateModel[size];
        }
    };

    public static void getEvaluateUserRequest(String isEredar, int myCenter, String userId, int pager, int pagerSize, OkHttpClientManager.ResultCallback<UserEvaluateModel> callback){
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("myCenter", myCenter+ "");
        params.put("isEredar", isEredar);
        if(myCenter != 1)
            params.put("id", userId);
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.EVALUATE_LIST_REQUEST, params, callback);

    }
}
