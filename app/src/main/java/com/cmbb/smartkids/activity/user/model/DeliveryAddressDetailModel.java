package com.cmbb.smartkids.activity.user.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by javon on 16/1/14.
 */
public class DeliveryAddressDetailModel implements Parcelable {

    /**
     * id : 1
     * isDefault : 0
     * address : 飞虹路568弄13号
     * districtText : 杨浦区
     * province : 31
     * postCode : 541235
     * receiveName : 潘小姐
     * receivePhone : 13514541235
     * district : 310110
     * cityText : 上海市
     * provinceText : 上海
     * city : 310000
     */

    private DataEntity data;
    /**
     * data : {"id":1,"isDefault":0,"address":"飞虹路568弄13号","districtText":"杨浦区","province":31,"postCode":"541235","receiveName":"潘小姐","receivePhone":"13514541235","district":310110,"cityText":"上海市","provinceText":"上海","city":310000}
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
        private int isDefault;
        private String address;
        private String districtText;
        private int province;
        private String postCode;
        private String receiveName;
        private String receivePhone;
        private int district;
        private String cityText;
        private String provinceText;
        private int city;

        public void setId(int id) {
            this.id = id;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setDistrictText(String districtText) {
            this.districtText = districtText;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public void setReceiveName(String receiveName) {
            this.receiveName = receiveName;
        }

        public void setReceivePhone(String receivePhone) {
            this.receivePhone = receivePhone;
        }

        public void setDistrict(int district) {
            this.district = district;
        }

        public void setCityText(String cityText) {
            this.cityText = cityText;
        }

        public void setProvincteTxt(String provinceText) {
            this.provinceText = provinceText;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getId() {
            return id;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public String getAddress() {
            return address;
        }

        public String getDistrictText() {
            return districtText;
        }

        public int getProvince() {
            return province;
        }

        public String getPostCode() {
            return postCode;
        }

        public String getReceiveName() {
            return receiveName;
        }

        public String getReceivePhone() {
            return receivePhone;
        }

        public int getDistrict() {
            return district;
        }

        public String getCityText() {
            return cityText;
        }

        public String getProvincteTxt() {
            return provinceText;
        }

        public int getCity() {
            return city;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.isDefault);
            dest.writeString(this.address);
            dest.writeString(this.districtText);
            dest.writeInt(this.province);
            dest.writeString(this.postCode);
            dest.writeString(this.receiveName);
            dest.writeString(this.receivePhone);
            dest.writeInt(this.district);
            dest.writeString(this.cityText);
            dest.writeString(this.provinceText);
            dest.writeInt(this.city);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.id = in.readInt();
            this.isDefault = in.readInt();
            this.address = in.readString();
            this.districtText = in.readString();
            this.province = in.readInt();
            this.postCode = in.readString();
            this.receiveName = in.readString();
            this.receivePhone = in.readString();
            this.district = in.readInt();
            this.cityText = in.readString();
            this.provinceText = in.readString();
            this.city = in.readInt();
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

    public DeliveryAddressDetailModel() {
    }

    protected DeliveryAddressDetailModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<DeliveryAddressDetailModel> CREATOR = new Parcelable.Creator<DeliveryAddressDetailModel>() {
        public DeliveryAddressDetailModel createFromParcel(Parcel source) {
            return new DeliveryAddressDetailModel(source);
        }

        public DeliveryAddressDetailModel[] newArray(int size) {
            return new DeliveryAddressDetailModel[size];
        }
    };
}
