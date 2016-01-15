package com.cmbb.smartkids.activity.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/22 13:51
 */
public class ActiveDetailModel implements Parcelable {


    /**
     * id : 278
     * title : 新业务测试1
     * startTime : 2015-12-10 11:12:00
     * endTime : 2015-12-10 11:12:00
     * applyStartTime : 2015-12-08 18:50:00
     * applyEndTime : 2015-12-10 11:12:00
     * peoples : 122
     * timeLeft :
     * collectCount : 4
     * realityPeoples : 2
     * province : 110000
     * provinceText : 北京
     * city : 110100
     * cityText : 北京市
     * district : 110102
     * districtText : 西城区
     * address : 11111111
     * price : 0.01
     * type : 202
     * content : xxxxxx
     * servicePhone : 121212
     * status : 2
     * isRecommoned : 1
     * servicesImg : http://smart.image.alimmdn.com/system/image/2015-12-09/servicesImgFile_ZjQwMmY3ZjQtZjQ1ZC00YzRhLThmMWYtZGUyYzU1MGNkNjJj
     * imgWidth : 1024
     * imgHeight : 768
     * sortNum : 2
     * browseNumber : 2
     * isCollect : 0
     * colletCount : 0
     * isReserve : 1
     * surplusTime :
     * userInfoList : [{"userId":61,"uid":121151,"recommoned":2,"userNike":"宗为昀","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/%24%7Byear%7D-%24%7Bmonth%7D-%24%7Bday%7D/0EED2580-F8D5-40B1-9280-5231A6A4B7AB","userBigImg":"","userBigWidth":"","userBigHeight":"","userSmallImg":"http://smart.image.alimmdn.com/app/test/%24%7Byear%7D-%24%7Bmonth%7D-%24%7Bday%7D/FF26AFF0-B3E0-472D-9EC9-ED82C73EF5EC","userSmallWidth":"750","userSmallHeight":"750","loginAccountType":0,"loginTime":"2015-10-30 10:17:19","loginAccount":"13671549695","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"","userPhone":"13671549695","userPhoneVersion":"","province":310000,"provinceText":"","district":310104,"districtText":"","city":310100,"cityText":"","userLevel":1,"userPresentation":"推廣小兒推拿理念給每個家庭，教導家長正確的育兒理念，並培訓家長小兒推拿手法，讓家長可以親手護衛孩子的健康，同時也提供定點的小兒和成人的保健調理服務，成為每個家庭的守護天使","backImgWidth":"750","backImgHeight":"502","goldCount":12,"growthCount":23,"fans":0,"attentionCount":9,"isSign":0,"isAttention":0,"isEredar":0,"isLoginUser":0,"userRole":[{"eredarCode":104,"eredarName":"小儿推拿达人"}]}]
     * serviceImgList : [{"content":"","imgHeight":"768","imgWidth":"1024","imgPath":"http://smart.image.alimmdn.com/system/image/2015-12-08/imageFile_YjFhZmI4YzQtNWIzMC00YTk1LWEzYjQtZWIwMDY1NWRjMmM2"}]
     * eventList :
     */

    private DataEntity data;
    /**
     * data : {"id":278,"title":"新业务测试1","startTime":"2015-12-10 11:12:00","endTime":"2015-12-10 11:12:00","applyStartTime":"2015-12-08 18:50:00","applyEndTime":"2015-12-10 11:12:00","peoples":122,"timeLeft":"","collectCount":4,"realityPeoples":2,"province":110000,"provinceText":"北京","city":110100,"cityText":"北京市","district":110102,"districtText":"西城区","address":"11111111","price":"0.01","type":202,"content":"xxxxxx","servicePhone":"121212","status":2,"isRecommoned":1,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-12-09/servicesImgFile_ZjQwMmY3ZjQtZjQ1ZC00YzRhLThmMWYtZGUyYzU1MGNkNjJj","imgWidth":"1024","imgHeight":"768","sortNum":2,"browseNumber":2,"isCollect":0,"colletCount":0,"isReserve":1,"surplusTime":"","userInfoList":[{"userId":61,"uid":121151,"recommoned":2,"userNike":"宗为昀","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/%24%7Byear%7D-%24%7Bmonth%7D-%24%7Bday%7D/0EED2580-F8D5-40B1-9280-5231A6A4B7AB","userBigImg":"","userBigWidth":"","userBigHeight":"","userSmallImg":"http://smart.image.alimmdn.com/app/test/%24%7Byear%7D-%24%7Bmonth%7D-%24%7Bday%7D/FF26AFF0-B3E0-472D-9EC9-ED82C73EF5EC","userSmallWidth":"750","userSmallHeight":"750","loginAccountType":0,"loginTime":"2015-10-30 10:17:19","loginAccount":"13671549695","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"","userPhone":"13671549695","userPhoneVersion":"","province":310000,"provinceText":"","district":310104,"districtText":"","city":310100,"cityText":"","userLevel":1,"userPresentation":"推廣小兒推拿理念給每個家庭，教導家長正確的育兒理念，並培訓家長小兒推拿手法，讓家長可以親手護衛孩子的健康，同時也提供定點的小兒和成人的保健調理服務，成為每個家庭的守護天使","backImgWidth":"750","backImgHeight":"502","goldCount":12,"growthCount":23,"fans":0,"attentionCount":9,"isSign":0,"isAttention":0,"isEredar":0,"isLoginUser":0,"userRole":[{"eredarCode":104,"eredarName":"小儿推拿达人"}]}],"serviceImgList":[{"content":"","imgHeight":"768","imgWidth":"1024","imgPath":"http://smart.image.alimmdn.com/system/image/2015-12-08/imageFile_YjFhZmI4YzQtNWIzMC00YTk1LWEzYjQtZWIwMDY1NWRjMmM2"}],"eventList":""}
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
        private int id;
        private String title;
        private String startTime;
        private String endTime;
        private String applyStartTime;
        private String applyEndTime;
        private int peoples;
        private String timeLeft;
        private int collectCount;
        private int realityPeoples;
        private int province;
        private String provinceText;
        private int city;
        private String cityText;
        private int district;
        private String districtText;
        private String address;
        private String price;
        private int type;
        private String content;
        private String servicePhone;
        private int status;
        private int isRecommoned;
        private String servicesImg;
        private String imgWidth;
        private String imgHeight;
        private int sortNum;
        private int browseNumber;
        private int isCollect;
        private int colletCount;
        private int isReserve;
        private String surplusTime;
        private String eventList;
        /**
         * userId : 61
         * uid : 121151
         * recommoned : 2
         * userNike : 宗为昀
         * userSex : 1
         * userBirthday :
         * backgroundImg : http://smart.image.alimmdn.com/app/test/%24%7Byear%7D-%24%7Bmonth%7D-%24%7Bday%7D/0EED2580-F8D5-40B1-9280-5231A6A4B7AB
         * userBigImg :
         * userBigWidth :
         * userBigHeight :
         * userSmallImg : http://smart.image.alimmdn.com/app/test/%24%7Byear%7D-%24%7Bmonth%7D-%24%7Bday%7D/FF26AFF0-B3E0-472D-9EC9-ED82C73EF5EC
         * userSmallWidth : 750
         * userSmallHeight : 750
         * loginAccountType : 0
         * loginTime : 2015-10-30 10:17:19
         * loginAccount : 13671549695
         * token :
         * isShutup : 0
         * shutupTime :
         * isBanned : 0
         * userAddress :
         * userPhone : 13671549695
         * userPhoneVersion :
         * province : 310000
         * provinceText :
         * district : 310104
         * districtText :
         * city : 310100
         * cityText :
         * userLevel : 1
         * userPresentation : 推廣小兒推拿理念給每個家庭，教導家長正確的育兒理念，並培訓家長小兒推拿手法，讓家長可以親手護衛孩子的健康，同時也提供定點的小兒和成人的保健調理服務，成為每個家庭的守護天使
         * backImgWidth : 750
         * backImgHeight : 502
         * goldCount : 12
         * growthCount : 23
         * fans : 0
         * attentionCount : 9
         * isSign : 0
         * isAttention : 0
         * isEredar : 0
         * isLoginUser : 0
         * userRole : [{"eredarCode":104,"eredarName":"小儿推拿达人"}]
         */

        private List<UserInfoListEntity> userInfoList;
        /**
         * content :
         * imgHeight : 768
         * imgWidth : 1024
         * imgPath : http://smart.image.alimmdn.com/system/image/2015-12-08/imageFile_YjFhZmI4YzQtNWIzMC00YTk1LWEzYjQtZWIwMDY1NWRjMmM2
         */

        private List<ServiceImgListEntity> serviceImgList;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public void setApplyStartTime(String applyStartTime) {
            this.applyStartTime = applyStartTime;
        }

        public void setApplyEndTime(String applyEndTime) {
            this.applyEndTime = applyEndTime;
        }

        public void setPeoples(int peoples) {
            this.peoples = peoples;
        }

        public void setTimeLeft(String timeLeft) {
            this.timeLeft = timeLeft;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public void setRealityPeoples(int realityPeoples) {
            this.realityPeoples = realityPeoples;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public void setProvinceText(String provinceText) {
            this.provinceText = provinceText;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public void setCityText(String cityText) {
            this.cityText = cityText;
        }

        public void setDistrict(int district) {
            this.district = district;
        }

        public void setDistrictText(String districtText) {
            this.districtText = districtText;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setServicePhone(String servicePhone) {
            this.servicePhone = servicePhone;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setIsRecommoned(int isRecommoned) {
            this.isRecommoned = isRecommoned;
        }

        public void setServicesImg(String servicesImg) {
            this.servicesImg = servicesImg;
        }

        public void setImgWidth(String imgWidth) {
            this.imgWidth = imgWidth;
        }

        public void setImgHeight(String imgHeight) {
            this.imgHeight = imgHeight;
        }

        public void setSortNum(int sortNum) {
            this.sortNum = sortNum;
        }

        public void setBrowseNumber(int browseNumber) {
            this.browseNumber = browseNumber;
        }

        public void setIsCollect(int isCollect) {
            this.isCollect = isCollect;
        }

        public void setColletCount(int colletCount) {
            this.colletCount = colletCount;
        }

        public void setIsReserve(int isReserve) {
            this.isReserve = isReserve;
        }

        public void setSurplusTime(String surplusTime) {
            this.surplusTime = surplusTime;
        }

        public void setEventList(String eventList) {
            this.eventList = eventList;
        }

        public void setUserInfoList(List<UserInfoListEntity> userInfoList) {
            this.userInfoList = userInfoList;
        }

        public void setServiceImgList(List<ServiceImgListEntity> serviceImgList) {
            this.serviceImgList = serviceImgList;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public String getApplyStartTime() {
            return applyStartTime;
        }

        public String getApplyEndTime() {
            return applyEndTime;
        }

        public int getPeoples() {
            return peoples;
        }

        public String getTimeLeft() {
            return timeLeft;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public int getRealityPeoples() {
            return realityPeoples;
        }

        public int getProvince() {
            return province;
        }

        public String getProvinceText() {
            return provinceText;
        }

        public int getCity() {
            return city;
        }

        public String getCityText() {
            return cityText;
        }

        public int getDistrict() {
            return district;
        }

        public String getDistrictText() {
            return districtText;
        }

        public String getAddress() {
            return address;
        }

        public String getPrice() {
            return price;
        }

        public int getType() {
            return type;
        }

        public String getContent() {
            return content;
        }

        public String getServicePhone() {
            return servicePhone;
        }

        public int getStatus() {
            return status;
        }

        public int getIsRecommoned() {
            return isRecommoned;
        }

        public String getServicesImg() {
            return servicesImg;
        }

        public String getImgWidth() {
            return imgWidth;
        }

        public String getImgHeight() {
            return imgHeight;
        }

        public int getSortNum() {
            return sortNum;
        }

        public int getBrowseNumber() {
            return browseNumber;
        }

        public int getIsCollect() {
            return isCollect;
        }

        public int getColletCount() {
            return colletCount;
        }

        public int getIsReserve() {
            return isReserve;
        }

        public String getSurplusTime() {
            return surplusTime;
        }

        public String getEventList() {
            return eventList;
        }

        public List<UserInfoListEntity> getUserInfoList() {
            return userInfoList;
        }

        public List<ServiceImgListEntity> getServiceImgList() {
            return serviceImgList;
        }

        public static class UserInfoListEntity implements Parcelable {
            private int userId;
            private int uid;
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
            private int growthCount;
            private int fans;
            private int attentionCount;
            private int isSign;
            private int isAttention;
            private int isEredar;
            private int isLoginUser;
            /**
             * eredarCode : 104
             * eredarName : 小儿推拿达人
             */

            private List<UserRoleEntity> userRole;

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public void setUid(int uid) {
                this.uid = uid;
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

            public int getUid() {
                return uid;
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

                public static final Creator<UserRoleEntity> CREATOR = new Creator<UserRoleEntity>() {
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

            public UserInfoListEntity() {
            }

            protected UserInfoListEntity(Parcel in) {
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

            public static final Creator<UserInfoListEntity> CREATOR = new Creator<UserInfoListEntity>() {
                public UserInfoListEntity createFromParcel(Parcel source) {
                    return new UserInfoListEntity(source);
                }

                public UserInfoListEntity[] newArray(int size) {
                    return new UserInfoListEntity[size];
                }
            };

            @Override
            public String toString() {
                return "UserInfoListEntity{" +
                        "userId=" + userId +
                        ", uid=" + uid +
                        ", recommoned=" + recommoned +
                        ", userNike='" + userNike + '\'' +
                        ", userSex=" + userSex +
                        ", userBirthday='" + userBirthday + '\'' +
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
                        ", province=" + province +
                        ", provinceText='" + provinceText + '\'' +
                        ", district=" + district +
                        ", districtText='" + districtText + '\'' +
                        ", city=" + city +
                        ", cityText='" + cityText + '\'' +
                        ", userLevel=" + userLevel +
                        ", userPresentation='" + userPresentation + '\'' +
                        ", backImgWidth='" + backImgWidth + '\'' +
                        ", backImgHeight='" + backImgHeight + '\'' +
                        ", goldCount=" + goldCount +
                        ", growthCount=" + growthCount +
                        ", fans=" + fans +
                        ", attentionCount=" + attentionCount +
                        ", isSign=" + isSign +
                        ", isAttention=" + isAttention +
                        ", isEredar=" + isEredar +
                        ", isLoginUser=" + isLoginUser +
                        ", userRole=" + userRole +
                        '}';
            }
        }

        public static class ServiceImgListEntity implements Parcelable {
            private String content;
            private String imgHeight;
            private String imgWidth;
            private String imgPath;

            public void setContent(String content) {
                this.content = content;
            }

            public void setImgHeight(String imgHeight) {
                this.imgHeight = imgHeight;
            }

            public void setImgWidth(String imgWidth) {
                this.imgWidth = imgWidth;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public String getContent() {
                return content;
            }

            public String getImgHeight() {
                return imgHeight;
            }

            public String getImgWidth() {
                return imgWidth;
            }

            public String getImgPath() {
                return imgPath;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.content);
                dest.writeString(this.imgHeight);
                dest.writeString(this.imgWidth);
                dest.writeString(this.imgPath);
            }

            public ServiceImgListEntity() {
            }

            protected ServiceImgListEntity(Parcel in) {
                this.content = in.readString();
                this.imgHeight = in.readString();
                this.imgWidth = in.readString();
                this.imgPath = in.readString();
            }

            public static final Parcelable.Creator<ServiceImgListEntity> CREATOR = new Parcelable.Creator<ServiceImgListEntity>() {
                public ServiceImgListEntity createFromParcel(Parcel source) {
                    return new ServiceImgListEntity(source);
                }

                public ServiceImgListEntity[] newArray(int size) {
                    return new ServiceImgListEntity[size];
                }
            };

            @Override
            public String toString() {
                return "ServiceImgListEntity{" +
                        "content='" + content + '\'' +
                        ", imgHeight='" + imgHeight + '\'' +
                        ", imgWidth='" + imgWidth + '\'' +
                        ", imgPath='" + imgPath + '\'' +
                        '}';
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.title);
            dest.writeString(this.startTime);
            dest.writeString(this.endTime);
            dest.writeString(this.applyStartTime);
            dest.writeString(this.applyEndTime);
            dest.writeInt(this.peoples);
            dest.writeString(this.timeLeft);
            dest.writeInt(this.collectCount);
            dest.writeInt(this.realityPeoples);
            dest.writeInt(this.province);
            dest.writeString(this.provinceText);
            dest.writeInt(this.city);
            dest.writeString(this.cityText);
            dest.writeInt(this.district);
            dest.writeString(this.districtText);
            dest.writeString(this.address);
            dest.writeString(this.price);
            dest.writeInt(this.type);
            dest.writeString(this.content);
            dest.writeString(this.servicePhone);
            dest.writeInt(this.status);
            dest.writeInt(this.isRecommoned);
            dest.writeString(this.servicesImg);
            dest.writeString(this.imgWidth);
            dest.writeString(this.imgHeight);
            dest.writeInt(this.sortNum);
            dest.writeInt(this.browseNumber);
            dest.writeInt(this.isCollect);
            dest.writeInt(this.colletCount);
            dest.writeInt(this.isReserve);
            dest.writeString(this.surplusTime);
            dest.writeString(this.eventList);
            dest.writeTypedList(this.userInfoList);
            dest.writeTypedList(this.serviceImgList);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.id = in.readInt();
            this.title = in.readString();
            this.startTime = in.readString();
            this.endTime = in.readString();
            this.applyStartTime = in.readString();
            this.applyEndTime = in.readString();
            this.peoples = in.readInt();
            this.timeLeft = in.readString();
            this.collectCount = in.readInt();
            this.realityPeoples = in.readInt();
            this.province = in.readInt();
            this.provinceText = in.readString();
            this.city = in.readInt();
            this.cityText = in.readString();
            this.district = in.readInt();
            this.districtText = in.readString();
            this.address = in.readString();
            this.price = in.readString();
            this.type = in.readInt();
            this.content = in.readString();
            this.servicePhone = in.readString();
            this.status = in.readInt();
            this.isRecommoned = in.readInt();
            this.servicesImg = in.readString();
            this.imgWidth = in.readString();
            this.imgHeight = in.readString();
            this.sortNum = in.readInt();
            this.browseNumber = in.readInt();
            this.isCollect = in.readInt();
            this.colletCount = in.readInt();
            this.isReserve = in.readInt();
            this.surplusTime = in.readString();
            this.eventList = in.readString();
            this.userInfoList = in.createTypedArrayList(UserInfoListEntity.CREATOR);
//            this.userInfoList = new ArrayList<UserInfoListEntity>();
//            in.readList(this.userInfoList, List.class.getClassLoader());
            this.serviceImgList = in.createTypedArrayList(ServiceImgListEntity.CREATOR);
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
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", startTime='" + startTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", applyStartTime='" + applyStartTime + '\'' +
                    ", applyEndTime='" + applyEndTime + '\'' +
                    ", peoples=" + peoples +
                    ", timeLeft='" + timeLeft + '\'' +
                    ", collectCount=" + collectCount +
                    ", realityPeoples=" + realityPeoples +
                    ", province=" + province +
                    ", provinceText='" + provinceText + '\'' +
                    ", city=" + city +
                    ", cityText='" + cityText + '\'' +
                    ", district=" + district +
                    ", districtText='" + districtText + '\'' +
                    ", address='" + address + '\'' +
                    ", price='" + price + '\'' +
                    ", type=" + type +
                    ", content='" + content + '\'' +
                    ", servicePhone='" + servicePhone + '\'' +
                    ", status=" + status +
                    ", isRecommoned=" + isRecommoned +
                    ", servicesImg='" + servicesImg + '\'' +
                    ", imgWidth='" + imgWidth + '\'' +
                    ", imgHeight='" + imgHeight + '\'' +
                    ", sortNum=" + sortNum +
                    ", browseNumber=" + browseNumber +
                    ", isCollect=" + isCollect +
                    ", colletCount=" + colletCount +
                    ", isReserve=" + isReserve +
                    ", surplusTime='" + surplusTime + '\'' +
                    ", eventList='" + eventList + '\'' +
                    ", userInfoList=" + userInfoList +
                    ", serviceImgList=" + serviceImgList +
                    '}';
        }
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

    public ActiveDetailModel() {
    }

    protected ActiveDetailModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<ActiveDetailModel> CREATOR = new Parcelable.Creator<ActiveDetailModel>() {
        public ActiveDetailModel createFromParcel(Parcel source) {
            return new ActiveDetailModel(source);
        }

        public ActiveDetailModel[] newArray(int size) {
            return new ActiveDetailModel[size];
        }
    };

    @Override
    public String toString() {
        return "ActiveDetailModel{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
