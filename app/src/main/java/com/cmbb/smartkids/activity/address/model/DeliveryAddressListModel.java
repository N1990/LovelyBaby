package com.cmbb.smartkids.activity.address.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;
import java.util.List;

/**
 * Created by javon on 16/1/14.
 */
public class DeliveryAddressListModel implements Parcelable {

    /**
     * page : 1
     * records : 3
     * rows : [{"id":3,"userRelationId":30,"receiveName":"潘小姐","receivePhone":"13514541235","postCode":"541235","province":31,"city":310000,"district":310110,"address":"飞虹路568弄13号","isDefault":0,"isDelete":0,"createDate":"2016-01-14 10:15:37","createUserId":30,"updateDate":"","updateUserId":11,"provinceText":"上海","cityText":"上海市","districtText":"杨浦区"}]
     * total : 1
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":3,"rows":[{"id":3,"userRelationId":30,"receiveName":"潘小姐","receivePhone":"13514541235","postCode":"541235","province":31,"city":310000,"district":310110,"address":"飞虹路568弄13号","isDefault":0,"isDelete":0,"createDate":"2016-01-14 10:15:37","createUserId":30,"updateDate":"","updateUserId":11,"provinceText":"上海","cityText":"上海市","districtText":"杨浦区"}],"total":1,"userdata":""}
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
            private int userRelationId;
            private String receiveName;
            private String receivePhone;
            private String postCode;
            private int province;
            private int city;
            private int district;
            private String address;
            private int isDefault;
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

            public void setUserRelationId(int userRelationId) {
                this.userRelationId = userRelationId;
            }

            public void setReceiveName(String receiveName) {
                this.receiveName = receiveName;
            }

            public void setReceivePhone(String receivePhone) {
                this.receivePhone = receivePhone;
            }

            public void setPostCode(String postCode) {
                this.postCode = postCode;
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

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
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

            public int getUserRelationId() {
                return userRelationId;
            }

            public String getReceiveName() {
                return receiveName;
            }

            public String getReceivePhone() {
                return receivePhone;
            }

            public String getPostCode() {
                return postCode;
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

            public int getIsDefault() {
                return isDefault;
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
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.userRelationId);
                dest.writeString(this.receiveName);
                dest.writeString(this.receivePhone);
                dest.writeString(this.postCode);
                dest.writeInt(this.province);
                dest.writeInt(this.city);
                dest.writeInt(this.district);
                dest.writeString(this.address);
                dest.writeInt(this.isDefault);
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
                this.userRelationId = in.readInt();
                this.receiveName = in.readString();
                this.receivePhone = in.readString();
                this.postCode = in.readString();
                this.province = in.readInt();
                this.city = in.readInt();
                this.district = in.readInt();
                this.address = in.readString();
                this.isDefault = in.readInt();
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

    public DeliveryAddressListModel() {
    }

    protected DeliveryAddressListModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<DeliveryAddressListModel> CREATOR = new Parcelable.Creator<DeliveryAddressListModel>() {
        public DeliveryAddressListModel createFromParcel(Parcel source) {
            return new DeliveryAddressListModel(source);
        }

        public DeliveryAddressListModel[] newArray(int size) {
            return new DeliveryAddressListModel[size];
        }
    };

    public static void addressListRequest(int pager, int pagerSize, OkHttpClientManager.ResultCallback<DeliveryAddressListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("token", BaseApplication.token);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.DELIVERY_ADDRESS_LIST, params, callback);
    }
}
