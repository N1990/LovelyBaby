package com.cmbb.smartkids.activity.serve.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/25 17:36
 */
public class MyServiceListModel implements Parcelable {


    /**
     * page : 1
     * records : 6
     * rows : [{"id":219,"properties":{},"title":"测试二","startTime":"2015-12-01 02:30","endTime":"2015-12-02 02:30","applyStartTime":"2015-11-11 02:30","applyEndTime":"2015-11-30 02:30","peoples":1212,"realityPeoples":1,"province":120000,"city":120100,"district":120101,"address":"111","price":0.01,"type":203,"content":"首页显示菊花","servicePhone":"1212","status":1,"isRecommoned":0,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-11-09/servicesImgFile_MjdlODkzMGItZjQzYi00NzRjLWI3OWYtZWQ4OTgyNDE3MmVl","imgWidth":1024,"imgHeight":768,"sortNum":0,"isDelete":0,"createDate":"2015-11-09 10:31:25","createUserId":2,"updateDate":"","updateUserId":2,"provinceText":"天津","cityText":"天津市","districtText":"和平区"}]
     * total : 1
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":6,"rows":[{"id":219,"properties":{},"title":"测试二","startTime":"2015-12-01 02:30","endTime":"2015-12-02 02:30","applyStartTime":"2015-11-11 02:30","applyEndTime":"2015-11-30 02:30","peoples":1212,"realityPeoples":1,"province":120000,"city":120100,"district":120101,"address":"111","price":0.01,"type":203,"content":"首页显示菊花","servicePhone":"1212","status":1,"isRecommoned":0,"servicesImg":"http://smart.image.alimmdn.com/system/image/2015-11-09/servicesImgFile_MjdlODkzMGItZjQzYi00NzRjLWI3OWYtZWQ4OTgyNDE3MmVl","imgWidth":1024,"imgHeight":768,"sortNum":0,"isDelete":0,"createDate":"2015-11-09 10:31:25","createUserId":2,"updateDate":"","updateUserId":2,"provinceText":"天津","cityText":"天津市","districtText":"和平区"}],"total":1,"userdata":""}
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
            private double price;
            private int type;
            private String content;
            private String servicePhone;
            private int status;
            private int isRecommoned;
            private String servicesImg;
            private int imgWidth;
            private int imgHeight;
            private int sortNum;
            private int isDelete;
            private String createDate;
            private int createUserId;
            private String updateDate;
            private int updateUserId;
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

            public void setPrice(double price) {
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

            public double getPrice() {
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

            @Override
            public String toString() {
                return "RowsEntity{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", startTime='" + startTime + '\'' +
                        ", endTime='" + endTime + '\'' +
                        ", applyStartTime='" + applyStartTime + '\'' +
                        ", applyEndTime='" + applyEndTime + '\'' +
                        ", peoples=" + peoples +
                        ", realityPeoples=" + realityPeoples +
                        ", province=" + province +
                        ", city=" + city +
                        ", district=" + district +
                        ", address='" + address + '\'' +
                        ", price=" + price +
                        ", type=" + type +
                        ", content='" + content + '\'' +
                        ", servicePhone='" + servicePhone + '\'' +
                        ", status=" + status +
                        ", isRecommoned=" + isRecommoned +
                        ", servicesImg='" + servicesImg + '\'' +
                        ", imgWidth=" + imgWidth +
                        ", imgHeight=" + imgHeight +
                        ", sortNum=" + sortNum +
                        ", isDelete=" + isDelete +
                        ", createDate='" + createDate + '\'' +
                        ", createUserId=" + createUserId +
                        ", updateDate='" + updateDate + '\'' +
                        ", updateUserId=" + updateUserId +
                        ", provinceText='" + provinceText + '\'' +
                        ", cityText='" + cityText + '\'' +
                        ", districtText='" + districtText + '\'' +
                        '}';
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
                dest.writeDouble(this.price);
                dest.writeInt(this.type);
                dest.writeString(this.content);
                dest.writeString(this.servicePhone);
                dest.writeInt(this.status);
                dest.writeInt(this.isRecommoned);
                dest.writeString(this.servicesImg);
                dest.writeInt(this.imgWidth);
                dest.writeInt(this.imgHeight);
                dest.writeInt(this.sortNum);
                dest.writeInt(this.isDelete);
                dest.writeString(this.createDate);
                dest.writeInt(this.createUserId);
                dest.writeString(this.updateDate);
                dest.writeInt(this.updateUserId);
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
                this.price = in.readDouble();
                this.type = in.readInt();
                this.content = in.readString();
                this.servicePhone = in.readString();
                this.status = in.readInt();
                this.isRecommoned = in.readInt();
                this.servicesImg = in.readString();
                this.imgWidth = in.readInt();
                this.imgHeight = in.readInt();
                this.sortNum = in.readInt();
                this.isDelete = in.readInt();
                this.createDate = in.readString();
                this.createUserId = in.readInt();
                this.updateDate = in.readString();
                this.updateUserId = in.readInt();
                this.provinceText = in.readString();
                this.cityText = in.readString();
                this.districtText = in.readString();
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
        public String toString() {
            return "DataEntity{" +
                    "page=" + page +
                    ", records=" + records +
                    ", total=" + total +
                    ", userdata='" + userdata + '\'' +
                    ", rows=" + rows +
                    '}';
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
    public String toString() {
        return "MyServiceListModel{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
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

    public MyServiceListModel() {
    }

    protected MyServiceListModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<MyServiceListModel> CREATOR = new Parcelable.Creator<MyServiceListModel>() {
        public MyServiceListModel createFromParcel(Parcel source) {
            return new MyServiceListModel(source);
        }

        public MyServiceListModel[] newArray(int size) {
            return new MyServiceListModel[size];
        }
    };
}
