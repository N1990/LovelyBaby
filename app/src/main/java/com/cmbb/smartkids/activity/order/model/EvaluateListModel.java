package com.cmbb.smartkids.activity.order.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Package on com.cmbb.smartkids.activity.order.model
 * Created by javon on 15/11/13.
 */
public class EvaluateListModel implements Parcelable {


    /**
     * page : 1
     * records : 1
     * rows : [{"evaluateContent":"1221","evaluateType":1,"evaluateDate":"2015-11-11 12:21:11","title":"萌宝派宝爸宝妈群开通啦","userBasicInfo":{"userId":8,"recommoned":3,"userNike":"fysq","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2015-11-6/image_581076c1bbb4457b89d851364b2bcea0","userBigImg":"","userBigWidth":"","userBigHeight":"","userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-10-26/image_4ac31e00b9d44dcb8f94352c640f0bc0","userSmallWidth":"","userSmallHeight":"","loginAccountType":0,"loginTime":"2015-11-06 17:47:07","loginAccount":"15901718791","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"shshwhw","userPhone":"15901718791","userPhoneVersion":"","province":120000,"provinceText":"","district":120101,"districtText":"","city":120100,"cityText":"","userLevel":1,"userPresentation":"","backImgWidth":720,"backImgHeight":1280,"goldCount":2,"integralCount":3,"attentionCount":2,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户"}],"fans":0,"isAttention":0,"isEredar":0}}]
     * total : 1
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":1,"rows":[{"evaluateContent":"1221","evaluateType":1,"evaluateDate":"2015-11-11 12:21:11","title":"萌宝派宝爸宝妈群开通啦","userBasicInfo":{"userId":8,"recommoned":3,"userNike":"fysq","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2015-11-6/image_581076c1bbb4457b89d851364b2bcea0","userBigImg":"","userBigWidth":"","userBigHeight":"","userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-10-26/image_4ac31e00b9d44dcb8f94352c640f0bc0","userSmallWidth":"","userSmallHeight":"","loginAccountType":0,"loginTime":"2015-11-06 17:47:07","loginAccount":"15901718791","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"shshwhw","userPhone":"15901718791","userPhoneVersion":"","province":120000,"provinceText":"","district":120101,"districtText":"","city":120100,"cityText":"","userLevel":1,"userPresentation":"","backImgWidth":720,"backImgHeight":1280,"goldCount":2,"integralCount":3,"attentionCount":2,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户"}],"fans":0,"isAttention":0,"isEredar":0}}],"total":1,"userdata":""}
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
        private int page;
        private int records;
        private int total;
        private String userdata;
        /**
         * evaluateContent : 1221
         * evaluateType : 1
         * evaluateDate : 2015-11-11 12:21:11
         * title : 萌宝派宝爸宝妈群开通啦
         * userBasicInfo : {"userId":8,"recommoned":3,"userNike":"fysq","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2015-11-6/image_581076c1bbb4457b89d851364b2bcea0","userBigImg":"","userBigWidth":"","userBigHeight":"","userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-10-26/image_4ac31e00b9d44dcb8f94352c640f0bc0","userSmallWidth":"","userSmallHeight":"","loginAccountType":0,"loginTime":"2015-11-06 17:47:07","loginAccount":"15901718791","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"shshwhw","userPhone":"15901718791","userPhoneVersion":"","province":120000,"provinceText":"","district":120101,"districtText":"","city":120100,"cityText":"","userLevel":1,"userPresentation":"","backImgWidth":720,"backImgHeight":1280,"goldCount":2,"integralCount":3,"attentionCount":2,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户"}],"fans":0,"isAttention":0,"isEredar":0}
         */

        private List<RowsEntity> rows;

        public void setPage(int page) {
            this.page = page;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public void setUserdata(String userdata) {
            this.userdata = userdata;
        }

        public void setRows(List<RowsEntity> rows) {
            this.rows = rows;
        }

        public int getPage() {
            return page;
        }

        public int getRecords() {
            return records;
        }

        public int getTotal() {
            return total;
        }

        public String getUserdata() {
            return userdata;
        }

        public List<RowsEntity> getRows() {
            return rows;
        }

        public static class RowsEntity implements Parcelable {
            private String evaluateContent;
            private int evaluateType;
            private String evaluateDate;
            private String title;
            /**
             * userId : 8
             * recommoned : 3
             * userNike : fysq
             * userSex : 1
             * userBirthday :
             * backgroundImg : http://smart.image.alimmdn.com/app/test/2015-11-6/image_581076c1bbb4457b89d851364b2bcea0
             * userBigImg :
             * userBigWidth :
             * userBigHeight :
             * userSmallImg : http://smart.image.alimmdn.com/app/test/2015-10-26/image_4ac31e00b9d44dcb8f94352c640f0bc0
             * userSmallWidth :
             * userSmallHeight :
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
             * goldCount : 2
             * integralCount : 3
             * attentionCount : 2
             * userRole : [{"eredarCode":0,"eredarName":"萌宝用户"}]
             * fans : 0
             * isAttention : 0
             * isEredar : 0
             */

            private UserBasicInfoEntity userBasicInfo;

            public void setEvaluateContent(String evaluateContent) {
                this.evaluateContent = evaluateContent;
            }

            public void setEvaluateType(int evaluateType) {
                this.evaluateType = evaluateType;
            }

            public void setEvaluateDate(String evaluateDate) {
                this.evaluateDate = evaluateDate;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setUserBasicInfo(UserBasicInfoEntity userBasicInfo) {
                this.userBasicInfo = userBasicInfo;
            }

            public String getEvaluateContent() {
                return evaluateContent;
            }

            public int getEvaluateType() {
                return evaluateType;
            }

            public String getEvaluateDate() {
                return evaluateDate;
            }

            public String getTitle() {
                return title;
            }

            public UserBasicInfoEntity getUserBasicInfo() {
                return userBasicInfo;
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
                private int backImgWidth;
                private int backImgHeight;
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

                public void setBackImgWidth(int backImgWidth) {
                    this.backImgWidth = backImgWidth;
                }

                public void setBackImgHeight(int backImgHeight) {
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

                public int getBackImgWidth() {
                    return backImgWidth;
                }

                public int getBackImgHeight() {
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
                    dest.writeInt(this.backImgWidth);
                    dest.writeInt(this.backImgHeight);
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
                    this.backImgWidth = in.readInt();
                    this.backImgHeight = in.readInt();
                    this.goldCount = in.readInt();
                    this.integralCount = in.readInt();
                    this.attentionCount = in.readInt();
                    this.fans = in.readInt();
                    this.isAttention = in.readInt();
                    this.isEredar = in.readInt();
                    this.userRole = in.createTypedArrayList(UserRoleEntity.CREATOR);
                }

                public static final Parcelable.Creator<UserBasicInfoEntity> CREATOR = new Parcelable.Creator<UserBasicInfoEntity>() {
                    public UserBasicInfoEntity createFromParcel(Parcel source) {
                        return new UserBasicInfoEntity(source);
                    }

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
                dest.writeParcelable(this.userBasicInfo, 0);
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
                public RowsEntity createFromParcel(Parcel source) {
                    return new RowsEntity(source);
                }

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

    public EvaluateListModel() {
    }

    protected EvaluateListModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<EvaluateListModel> CREATOR = new Parcelable.Creator<EvaluateListModel>() {
        public EvaluateListModel createFromParcel(Parcel source) {
            return new EvaluateListModel(source);
        }

        public EvaluateListModel[] newArray(int size) {
            return new EvaluateListModel[size];
        }
    };
}
