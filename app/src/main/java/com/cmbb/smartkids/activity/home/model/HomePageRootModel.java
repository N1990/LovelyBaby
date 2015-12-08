package com.cmbb.smartkids.activity.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/16 下午5:11
 */
public class HomePageRootModel implements Parcelable {

    /**
     * status : 1
     * data : {"page":1,"records":5,"rows":[{"id":90,"properties":{},"title":"萌宝派宝爸宝妈群开通啦","startTime":"2015-10-18 05:39:36","endTime":"2015-10-24 05:39:41","applyStartTime":"2015-10-14 05:39:28","applyEndTime":"2015-10-17 05:39:32","peoples":200,"realityPeoples":null,"province":310000,"city":310100,"district":310104,"address":"飞虹路568弄13号","price":0,"type":200,"content":"萌宝派宝爸宝妈群开通啦.....","servicePhone":"13818155072","status":1,"isRecommoned":1,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-10-13/servicesImgFile_ZWNmMmYwZjgtMTNhOC00YmFjLTgyMjUtNjVlMzQ5YTlkNjVm","imgWidth":1242,"imgHeight":484,"sortNum":5,"isDelete":0,"createDate":"2015-10-13 17:40:25","createUserId":null,"updateDate":null,"updateUserId":null,"provinceText":"上海","cityText":"上海市","districtText":"徐汇区"},{"id":91,"properties":{},"title":"国庆总动员  晒照其祝福","startTime":"2015-10-18 05:45:50","endTime":"2015-10-19 05:45:52","applyStartTime":"2015-10-14 05:45:43","applyEndTime":"2015-10-17 05:45:47","peoples":800,"realityPeoples":null,"province":310000,"city":310100,"district":310101,"address":"飞虹路569弄13号","price":0,"type":200,"content":"国庆总动员  晒照其祝福啦。。。","servicePhone":"13818155072","status":1,"isRecommoned":1,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-10-13/servicesImgFile_ZjliNjg2ODgtZWY0NS00NWJmLTlmY2MtMWQwYzVkOTZlMDM4","imgWidth":1242,"imgHeight":484,"sortNum":4,"isDelete":0,"createDate":"2015-10-13 17:50:06","createUserId":null,"updateDate":null,"updateUserId":null,"provinceText":"上海","cityText":"上海市","districtText":"黄浦区"}],"total":3,"userdata":null}
     * msg : 数据加载成功
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
         * page : 1
         * records : 5
         * rows : [{"id":90,"properties":{},"title":"萌宝派宝爸宝妈群开通啦","startTime":"2015-10-18 05:39:36","endTime":"2015-10-24 05:39:41","applyStartTime":"2015-10-14 05:39:28","applyEndTime":"2015-10-17 05:39:32","peoples":200,"realityPeoples":null,"province":310000,"city":310100,"district":310104,"address":"飞虹路568弄13号","price":0,"type":200,"content":"萌宝派宝爸宝妈群开通啦.....","servicePhone":"13818155072","status":1,"isRecommoned":1,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-10-13/servicesImgFile_ZWNmMmYwZjgtMTNhOC00YmFjLTgyMjUtNjVlMzQ5YTlkNjVm","imgWidth":1242,"imgHeight":484,"sortNum":5,"isDelete":0,"createDate":"2015-10-13 17:40:25","createUserId":null,"updateDate":null,"updateUserId":null,"provinceText":"上海","cityText":"上海市","districtText":"徐汇区"},{"id":91,"properties":{},"title":"国庆总动员  晒照其祝福","startTime":"2015-10-18 05:45:50","endTime":"2015-10-19 05:45:52","applyStartTime":"2015-10-14 05:45:43","applyEndTime":"2015-10-17 05:45:47","peoples":800,"realityPeoples":null,"province":310000,"city":310100,"district":310101,"address":"飞虹路569弄13号","price":0,"type":200,"content":"国庆总动员  晒照其祝福啦。。。","servicePhone":"13818155072","status":1,"isRecommoned":1,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-10-13/servicesImgFile_ZjliNjg2ODgtZWY0NS00NWJmLTlmY2MtMWQwYzVkOTZlMDM4","imgWidth":1242,"imgHeight":484,"sortNum":4,"isDelete":0,"createDate":"2015-10-13 17:50:06","createUserId":null,"updateDate":null,"updateUserId":null,"provinceText":"上海","cityText":"上海市","districtText":"黄浦区"}]
         * total : 3
         * userdata : null
         */

        private int page;
        private int records;
        private int total;
        private String userdata;
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
            /**
             * id : 90
             * properties : {}
             * title : 萌宝派宝爸宝妈群开通啦
             * startTime : 2015-10-18 05:39:36
             * endTime : 2015-10-24 05:39:41
             * applyStartTime : 2015-10-14 05:39:28
             * applyEndTime : 2015-10-17 05:39:32
             * peoples : 200
             * realityPeoples : null
             * province : 310000
             * city : 310100
             * district : 310104
             * address : 飞虹路568弄13号
             * price : 0.0
             * type : 200
             * content : 萌宝派宝爸宝妈群开通啦.....
             * servicePhone : 13818155072
             * status : 1
             * isRecommoned : 1
             * servicesImg : http://smart.image.alimmdn.com/system/image/2015-10-13/servicesImgFile_ZWNmMmYwZjgtMTNhOC00YmFjLTgyMjUtNjVlMzQ5YTlkNjVm
             * imgWidth : 1242.0
             * imgHeight : 484.0
             * sortNum : 5
             * isDelete : 0
             * createDate : 2015-10-13 17:40:25
             * createUserId : null
             * updateDate : null
             * updateUserId : null
             * provinceText : 上海
             * cityText : 上海市
             * districtText : 徐汇区
             */

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
            private String type;
            private String content;
            private String servicePhone;
            private int status;
            private int isRecommoned;
            private String servicesImg;
            private String imgWidth;
            private String imgHeight;
            private String sortNum;
            private int isDelete;
            private String createDate;
            private int createUserId;
            private String updateDate;
            private String updateUserId;
            private String provinceText;
            private String cityText;
            private String districtText;

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

            public void setType(String type) {
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

            public void setSortNum(String sortNum) {
                this.sortNum = sortNum;
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

            public void setUpdateUserId(String updateUserId) {
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

            public String getType() {
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

            public String getSortNum() {
                return sortNum;
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

            public String getUpdateUserId() {
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
                dest.writeString(this.type);
                dest.writeString(this.content);
                dest.writeString(this.servicePhone);
                dest.writeInt(this.status);
                dest.writeInt(this.isRecommoned);
                dest.writeString(this.servicesImg);
                dest.writeString(this.imgWidth);
                dest.writeString(this.imgHeight);
                dest.writeString(this.sortNum);
                dest.writeInt(this.isDelete);
                dest.writeString(this.createDate);
                dest.writeInt(this.createUserId);
                dest.writeString(this.updateDate);
                dest.writeString(this.updateUserId);
                dest.writeString(this.provinceText);
                dest.writeString(this.cityText);
                dest.writeString(this.districtText);
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
                this.type = in.readString();
                this.content = in.readString();
                this.servicePhone = in.readString();
                this.status = in.readInt();
                this.isRecommoned = in.readInt();
                this.servicesImg = in.readString();
                this.imgWidth = in.readString();
                this.imgHeight = in.readString();
                this.sortNum = in.readString();
                this.isDelete = in.readInt();
                this.createDate = in.readString();
                this.createUserId = in.readInt();
                this.updateDate = in.readString();
                this.updateUserId = in.readString();
                this.provinceText = in.readString();
                this.cityText = in.readString();
                this.districtText = in.readString();
            }

            public static final Creator<RowsEntity> CREATOR = new Creator<RowsEntity>() {
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
        dest.writeInt(this.status);
        dest.writeParcelable(this.data, 0);
        dest.writeString(this.msg);
    }

    public HomePageRootModel() {
    }

    protected HomePageRootModel(Parcel in) {
        this.status = in.readInt();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Creator<HomePageRootModel> CREATOR = new Creator<HomePageRootModel>() {
        public HomePageRootModel createFromParcel(Parcel source) {
            return new HomePageRootModel(source);
        }

        public HomePageRootModel[] newArray(int size) {
            return new HomePageRootModel[size];
        }
    };
}
