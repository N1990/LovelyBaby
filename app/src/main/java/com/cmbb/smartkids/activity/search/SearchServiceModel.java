package com.cmbb.smartkids.activity.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;
import java.util.List;

/**
 * Created by javon on 16/1/13.
 */
public class SearchServiceModel implements Parcelable {


    /**
     * page : 1
     * records : 1
     * rows : [{"id":314,"properties":"","title":"2016丙申话流年，找对方向、等风来!","startTime":"2015-12-18 19:00:00","endTime":"2015-12-18 21:00:00","applyStartTime":"2015-12-16 16:36:00","applyEndTime":"2015-12-18 14:00:00","peoples":10,"realityPeoples":12,"province":310000,"city":310100,"district":310101,"address":"上海天目西路218号太平洋百货一楼 inWE因味茶","price":"38","serviceType":1030005,"type":202,"content":"先机在握、顺势而为，事半功倍\r\n马有千里之程，无骑不能自往；人有冲天之志，非运不能自通\r\n\u2014宋\u2022吕蒙正（名臣，历任三朝宰相）\r\n\r\n所谓顺逆，不过天时地利人和否，\r\n不过从心所欲不逾矩\r\n尊重宇宙之运行规律，\r\n明了自己的境况与机遇\r\n把时间和精力放对地方\r\n留给对的人/事/物\r\n为自己赢得又一年精彩\r\n\r\n中华传统所谓\u201c命者，立之于己，而受之于天\u201d。每个人的生命轨迹都是由无数个时空点+选择交织而成。无论周易八卦，河图洛书还是奇门遁甲，都是人类力求解读命理规律，让自己尽量处于天时地利人和的状态，把生命过得更有品质。","servicePhone":"","status":3,"isRecommoned":0,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-12-16/servicesImgFile_NmM3YTA0YzUtYTRiNC00NDkyLWJiZDgtNzY2Y2U4ODQ1ZGFk","imgWidth":"520","imgHeight":"366","sortNum":0,"browseNumber":4,"isDelete":0,"createDate":"2015-12-16 16:40:55","createUserId":0,"updateDate":"","updateUserId":1,"provinceText":"","cityText":"上海市","districtText":"","surplusTime":""}]
     * total : 1
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":1,"rows":[{"id":314,"properties":"","title":"2016丙申话流年，找对方向、等风来!","startTime":"2015-12-18 19:00:00","endTime":"2015-12-18 21:00:00","applyStartTime":"2015-12-16 16:36:00","applyEndTime":"2015-12-18 14:00:00","peoples":10,"realityPeoples":12,"province":310000,"city":310100,"district":310101,"address":"上海天目西路218号太平洋百货一楼 inWE因味茶","price":"38","serviceType":1030005,"type":202,"content":"先机在握、顺势而为，事半功倍\r\n马有千里之程，无骑不能自往；人有冲天之志，非运不能自通\r\n\u2014宋\u2022吕蒙正（名臣，历任三朝宰相）\r\n\r\n所谓顺逆，不过天时地利人和否，\r\n不过从心所欲不逾矩\r\n尊重宇宙之运行规律，\r\n明了自己的境况与机遇\r\n把时间和精力放对地方\r\n留给对的人/事/物\r\n为自己赢得又一年精彩\r\n\r\n中华传统所谓\u201c命者，立之于己，而受之于天\u201d。每个人的生命轨迹都是由无数个时空点+选择交织而成。无论周易八卦，河图洛书还是奇门遁甲，都是人类力求解读命理规律，让自己尽量处于天时地利人和的状态，把生命过得更有品质。","servicePhone":"","status":3,"isRecommoned":0,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-12-16/servicesImgFile_NmM3YTA0YzUtYTRiNC00NDkyLWJiZDgtNzY2Y2U4ODQ1ZGFk","imgWidth":"520","imgHeight":"366","sortNum":0,"browseNumber":4,"isDelete":0,"createDate":"2015-12-16 16:40:55","createUserId":0,"updateDate":"","updateUserId":1,"provinceText":"","cityText":"上海市","districtText":"","surplusTime":""}],"total":1,"userdata":""}
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
         * id : 314
         * properties :
         * title : 2016丙申话流年，找对方向、等风来!
         * startTime : 2015-12-18 19:00:00
         * endTime : 2015-12-18 21:00:00
         * applyStartTime : 2015-12-16 16:36:00
         * applyEndTime : 2015-12-18 14:00:00
         * peoples : 10
         * realityPeoples : 12
         * province : 310000
         * city : 310100
         * district : 310101
         * address : 上海天目西路218号太平洋百货一楼 inWE因味茶
         * price : 38
         * serviceType : 1030005
         * type : 202
         * content : 先机在握、顺势而为，事半功倍
         马有千里之程，无骑不能自往；人有冲天之志，非运不能自通
         —宋•吕蒙正（名臣，历任三朝宰相）

         所谓顺逆，不过天时地利人和否，
         不过从心所欲不逾矩
         尊重宇宙之运行规律，
         明了自己的境况与机遇
         把时间和精力放对地方
         留给对的人/事/物
         为自己赢得又一年精彩

         中华传统所谓“命者，立之于己，而受之于天”。每个人的生命轨迹都是由无数个时空点+选择交织而成。无论周易八卦，河图洛书还是奇门遁甲，都是人类力求解读命理规律，让自己尽量处于天时地利人和的状态，把生命过得更有品质。
         * servicePhone :
         * status : 3
         * isRecommoned : 0
         * servicesImg : http://smart.image.alimmdn.com/system/image/2015-12-16/servicesImgFile_NmM3YTA0YzUtYTRiNC00NDkyLWJiZDgtNzY2Y2U4ODQ1ZGFk
         * imgWidth : 520
         * imgHeight : 366
         * sortNum : 0
         * browseNumber : 4
         * isDelete : 0
         * createDate : 2015-12-16 16:40:55
         * createUserId : 0
         * updateDate :
         * updateUserId : 1
         * provinceText :
         * cityText : 上海市
         * districtText :
         * surplusTime :
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
            private int id;
            private String title;
            private String startTime;
            private String endTime;
            private String applyStartTime;
            private String applyEndTime;
            private int peoples;
            private int realityPeoples;
            private int province;
            private int city;
            private int district;
            private String address;
            private String price;
            private int serviceType;
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
            private int isDelete;
            private String createDate;
            private int createUserId;
            private String updateDate;
            private int updateUserId;
            private String provinceText;
            private String cityText;
            private String districtText;
            private String surplusTime;

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

            public void setRealityPeoples(int realityPeoples) {
                this.realityPeoples = realityPeoples;
            }

            public void setProvince(int province) {
                this.province = province;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public void setDistrict(int district) {
                this.district = district;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setServiceType(int serviceType) {
                this.serviceType = serviceType;
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

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public void setUpdateUserId(int updateUserId) {
                this.updateUserId = updateUserId;
            }

            public void setProvinceText(String provinceText) {
                this.provinceText = provinceText;
            }

            public void setCityText(String cityText) {
                this.cityText = cityText;
            }

            public void setDistrictText(String districtText) {
                this.districtText = districtText;
            }

            public void setSurplusTime(String surplusTime) {
                this.surplusTime = surplusTime;
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

            public int getRealityPeoples() {
                return realityPeoples;
            }

            public int getProvince() {
                return province;
            }

            public int getCity() {
                return city;
            }

            public int getDistrict() {
                return district;
            }

            public String getAddress() {
                return address;
            }

            public String getPrice() {
                return price;
            }

            public int getServiceType() {
                return serviceType;
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

            public int getIsDelete() {
                return isDelete;
            }

            public String getCreateDate() {
                return createDate;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public int getUpdateUserId() {
                return updateUserId;
            }

            public String getProvinceText() {
                return provinceText;
            }

            public String getCityText() {
                return cityText;
            }

            public String getDistrictText() {
                return districtText;
            }

            public String getSurplusTime() {
                return surplusTime;
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
                dest.writeInt(this.realityPeoples);
                dest.writeInt(this.province);
                dest.writeInt(this.city);
                dest.writeInt(this.district);
                dest.writeString(this.address);
                dest.writeString(this.price);
                dest.writeInt(this.serviceType);
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
                dest.writeInt(this.isDelete);
                dest.writeString(this.createDate);
                dest.writeInt(this.createUserId);
                dest.writeString(this.updateDate);
                dest.writeInt(this.updateUserId);
                dest.writeString(this.provinceText);
                dest.writeString(this.cityText);
                dest.writeString(this.districtText);
                dest.writeString(this.surplusTime);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.id = in.readInt();
                this.title = in.readString();
                this.startTime = in.readString();
                this.endTime = in.readString();
                this.applyStartTime = in.readString();
                this.applyEndTime = in.readString();
                this.peoples = in.readInt();
                this.realityPeoples = in.readInt();
                this.province = in.readInt();
                this.city = in.readInt();
                this.district = in.readInt();
                this.address = in.readString();
                this.price = in.readString();
                this.serviceType = in.readInt();
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
                this.isDelete = in.readInt();
                this.createDate = in.readString();
                this.createUserId = in.readInt();
                this.updateDate = in.readString();
                this.updateUserId = in.readInt();
                this.provinceText = in.readString();
                this.cityText = in.readString();
                this.districtText = in.readString();
                this.surplusTime = in.readString();
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

    public SearchServiceModel() {
    }

    protected SearchServiceModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<SearchServiceModel> CREATOR = new Parcelable.Creator<SearchServiceModel>() {
        public SearchServiceModel createFromParcel(Parcel source) {
            return new SearchServiceModel(source);
        }

        public SearchServiceModel[] newArray(int size) {
            return new SearchServiceModel[size];
        }
    };
    public static void getSearchServiceRequest(String content, int pager, int pagerSize, OkHttpClientManager.ResultCallback<SearchServiceModel> callback){
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("contents", content);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.SEARCH_SERVICE_REQUEST, params, callback);
    }
}
