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
     * id : 333
     * title : 配送服务测试
     * startTime : 2016-01-30 16:47:00
     * endTime : 2016-01-30 16:47:00
     * applyStartTime : 2016-01-18 16:47:00
     * applyEndTime : 2016-01-30 16:47:00
     * peoples : 10
     * timeLeft :
     * collectCount : 1
     * realityPeoples : 1
     * province : 110000
     * provinceText : 北京
     * city : 110100
     * cityText : 北京市
     * district : 110101
     * districtText : 东城区
     * address : 1234546545645
     * price : 0.01
     * type : 205
     * content : 阿萨德发射点发艾丝凡
     * servicePhone : 121222548
     * status : 2
     * isRecommoned : 1
     * servicesImg : http://smart.image.alimmdn.com/system/image/2016-01-18/servicesImgFile_ODNmYmM5ZDItMGE4MS00NDI4LWFjZGItNjdiZTQwZDc5Njhh
     * imgWidth : 542
     * imgHeight : 408
     * sortNum : 3
     * browseNumber : 70
     * isCollect : 0
     * colletCount : 0
     * isReserve : 0
     * reserveText : 订单已存在，请到我的订单查看详细
     * surplusTime : 报名截止时间5天
     * userInfoList : [{"userId":30,"uid":121141,"recommoned":1,"userNike":"潘晶晶","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2015-10-15/ECDBA90D-DCC3-4EB4-8AEC-A6A65FF5A280","userBigImg":"","userBigWidth":"","userBigHeight":"","userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-10-15/DB3ED9CE-3728-4ED4-A6A1-B4FEB0FBA7FD","userSmallWidth":"750","userSmallHeight":"753","loginAccountType":0,"loginTime":"2016-01-14 17:50:59","loginAccount":"15000284627","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"","userPhone":"15000284627","userPhoneVersion":"","province":310000,"provinceText":"","district":310101,"districtText":"","city":310100,"cityText":"","userLevel":2,"userPresentation":"毕业于上海交大医学院营养专业，国家高级营养师、临床执业营养师，擅长孕妇、产后、婴幼儿及各类慢性病的营养指导和配餐，有丰富的实践经验，曾任慢性病、孕产妇及婴幼儿营养讲座主讲，社区健康报营养主编以及婴幼儿营养书籍的撰写。","backImgWidth":"750","backImgHeight":"753","goldCount":1,"growthCount":2,"fans":5,"attentionCount":1,"isSign":0,"isAttention":0,"isEredar":0,"isLoginUser":0,"userRole":[{"eredarCode":100,"eredarName":"营养达人"}]}]
     * serviceImgList : [{"content":"","imgHeight":"697","imgWidth":"990","imgPath":"http://smart.image.alimmdn.com/system/image/2016-01-18/imageFile_MWE0YmViODQtOGMyMy00ZWI3LWJlNzUtYjc0N2ViOGZlOTUw"}]
     * eventList :
     */

    private DataEntity data;
    /**
     * data : {"id":333,"title":"配送服务测试","startTime":"2016-01-30 16:47:00","endTime":"2016-01-30 16:47:00","applyStartTime":"2016-01-18 16:47:00","applyEndTime":"2016-01-30 16:47:00","peoples":10,"timeLeft":"","collectCount":1,"realityPeoples":1,"province":110000,"provinceText":"北京","city":110100,"cityText":"北京市","district":110101,"districtText":"东城区","address":"1234546545645","price":"0.01","type":205,"content":"阿萨德发射点发艾丝凡","servicePhone":"121222548","status":2,"isRecommoned":1,"servicesImg":"http://smart.image.alimmdn.com/system/image/2016-01-18/servicesImgFile_ODNmYmM5ZDItMGE4MS00NDI4LWFjZGItNjdiZTQwZDc5Njhh","imgWidth":542,"imgHeight":408,"sortNum":3,"browseNumber":70,"isCollect":0,"colletCount":0,"isReserve":0,"reserveText":"订单已存在，请到我的订单查看详细","surplusTime":"报名截止时间5天","userInfoList":[{"userId":30,"uid":121141,"recommoned":1,"userNike":"潘晶晶","userSex":1,"userBirthday":"","backgroundImg":"http://smart.image.alimmdn.com/app/test/2015-10-15/ECDBA90D-DCC3-4EB4-8AEC-A6A65FF5A280","userBigImg":"","userBigWidth":"","userBigHeight":"","userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-10-15/DB3ED9CE-3728-4ED4-A6A1-B4FEB0FBA7FD","userSmallWidth":"750","userSmallHeight":"753","loginAccountType":0,"loginTime":"2016-01-14 17:50:59","loginAccount":"15000284627","token":"","isShutup":0,"shutupTime":"","isBanned":0,"userAddress":"","userPhone":"15000284627","userPhoneVersion":"","province":310000,"provinceText":"","district":310101,"districtText":"","city":310100,"cityText":"","userLevel":2,"userPresentation":"毕业于上海交大医学院营养专业，国家高级营养师、临床执业营养师，擅长孕妇、产后、婴幼儿及各类慢性病的营养指导和配餐，有丰富的实践经验，曾任慢性病、孕产妇及婴幼儿营养讲座主讲，社区健康报营养主编以及婴幼儿营养书籍的撰写。","backImgWidth":"750","backImgHeight":"753","goldCount":1,"growthCount":2,"fans":5,"attentionCount":1,"isSign":0,"isAttention":0,"isEredar":0,"isLoginUser":0,"userRole":[{"eredarCode":100,"eredarName":"营养达人"}]}],"serviceImgList":[{"content":"","imgHeight":"697","imgWidth":"990","imgPath":"http://smart.image.alimmdn.com/system/image/2016-01-18/imageFile_MWE0YmViODQtOGMyMy00ZWI3LWJlNzUtYjc0N2ViOGZlOTUw"}],"eventList":""}
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
        private int imgWidth;
        private int imgHeight;
        private int sortNum;
        private int browseNumber;
        private int isCollect;
        private int colletCount;
        private int isReserve;
        private String reserveText;
        private String surplusTime;
        private String eventList;
        /**
         * userId : 30
         * uid : 121141
         * recommoned : 1
         * userNike : 潘晶晶
         * userSex : 1
         * userBirthday :
         * backgroundImg : http://smart.image.alimmdn.com/app/test/2015-10-15/ECDBA90D-DCC3-4EB4-8AEC-A6A65FF5A280
         * userBigImg :
         * userBigWidth :
         * userBigHeight :
         * userSmallImg : http://smart.image.alimmdn.com/app/test/2015-10-15/DB3ED9CE-3728-4ED4-A6A1-B4FEB0FBA7FD
         * userSmallWidth : 750
         * userSmallHeight : 753
         * loginAccountType : 0
         * loginTime : 2016-01-14 17:50:59
         * loginAccount : 15000284627
         * token :
         * isShutup : 0
         * shutupTime :
         * isBanned : 0
         * userAddress :
         * userPhone : 15000284627
         * userPhoneVersion :
         * province : 310000
         * provinceText :
         * district : 310101
         * districtText :
         * city : 310100
         * cityText :
         * userLevel : 2
         * userPresentation : 毕业于上海交大医学院营养专业，国家高级营养师、临床执业营养师，擅长孕妇、产后、婴幼儿及各类慢性病的营养指导和配餐，有丰富的实践经验，曾任慢性病、孕产妇及婴幼儿营养讲座主讲，社区健康报营养主编以及婴幼儿营养书籍的撰写。
         * backImgWidth : 750
         * backImgHeight : 753
         * goldCount : 1
         * growthCount : 2
         * fans : 5
         * attentionCount : 1
         * isSign : 0
         * isAttention : 0
         * isEredar : 0
         * isLoginUser : 0
         * userRole : [{"eredarCode":100,"eredarName":"营养达人"}]
         */

        private List<UserInfoListEntity> userInfoList;
        /**
         * content :
         * imgHeight : 697
         * imgWidth : 990
         * imgPath : http://smart.image.alimmdn.com/system/image/2016-01-18/imageFile_MWE0YmViODQtOGMyMy00ZWI3LWJlNzUtYjc0N2ViOGZlOTUw
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

        public void setImgWidth(int imgWidth) {
            this.imgWidth = imgWidth;
        }

        public void setImgHeight(int imgHeight) {
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

        public void setReserveText(String reserveText) {
            this.reserveText = reserveText;
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

        public int getImgWidth() {
            return imgWidth;
        }

        public int getImgHeight() {
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

        public String getReserveText() {
            return reserveText;
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
             * eredarCode : 100
             * eredarName : 营养达人
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

            public static final Creator<ServiceImgListEntity> CREATOR = new Creator<ServiceImgListEntity>() {
                public ServiceImgListEntity createFromParcel(Parcel source) {
                    return new ServiceImgListEntity(source);
                }

                public ServiceImgListEntity[] newArray(int size) {
                    return new ServiceImgListEntity[size];
                }
            };
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
            dest.writeInt(this.imgWidth);
            dest.writeInt(this.imgHeight);
            dest.writeInt(this.sortNum);
            dest.writeInt(this.browseNumber);
            dest.writeInt(this.isCollect);
            dest.writeInt(this.colletCount);
            dest.writeInt(this.isReserve);
            dest.writeString(this.reserveText);
            dest.writeString(this.surplusTime);
            dest.writeString(this.eventList);
            dest.writeTypedList(userInfoList);
            dest.writeTypedList(serviceImgList);
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
            this.imgWidth = in.readInt();
            this.imgHeight = in.readInt();
            this.sortNum = in.readInt();
            this.browseNumber = in.readInt();
            this.isCollect = in.readInt();
            this.colletCount = in.readInt();
            this.isReserve = in.readInt();
            this.reserveText = in.readString();
            this.surplusTime = in.readString();
            this.eventList = in.readString();
            this.userInfoList = in.createTypedArrayList(UserInfoListEntity.CREATOR);
            this.serviceImgList = in.createTypedArrayList(ServiceImgListEntity.CREATOR);
        }

        public static final Creator<DataEntity> CREATOR = new Creator<DataEntity>() {
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

    public ActiveDetailModel() {
    }

    protected ActiveDetailModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Creator<ActiveDetailModel> CREATOR = new Creator<ActiveDetailModel>() {
        public ActiveDetailModel createFromParcel(Parcel source) {
            return new ActiveDetailModel(source);
        }

        public ActiveDetailModel[] newArray(int size) {
            return new ActiveDetailModel[size];
        }
    };
}
