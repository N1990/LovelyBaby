package com.cmbb.smartkids.activity.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Package on com.cmbb.smartkids.activity.home.model
 * Created by javon on 15/11/19.
 */
public class ServiceSortModel implements Parcelable {


    private DataEntity data;
    /**
     * data : {"services":[{"name":"上门","value":"201"},{"name":"到店","value":"202"},{"name":"培训","value":"203"}],"serviceCity":[{"name":"北京","value":"110100"},{"name":"上海","value":"310100"},{"name":"广州","value":"440100"}],"serviceStatus":[{"name":"开始","value":"1"},{"name":"预定","value":"2"},{"name":"结束","value":"3"}]}
     * msg : 操作成功
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
        /**
         * name : 上门
         * value : 201
         */

        private List<ServicesEntity> services;
        /**
         * name : 北京
         * value : 110100
         */

        private List<ServiceCityEntity> serviceCity;
        /**
         * name : 开始
         * value : 1
         */

        private List<ServiceStatusEntity> serviceStatus;

        public void setServices(List<ServicesEntity> services) {
            this.services = services;
        }

        public void setServiceCity(List<ServiceCityEntity> serviceCity) {
            this.serviceCity = serviceCity;
        }

        public void setServiceStatus(List<ServiceStatusEntity> serviceStatus) {
            this.serviceStatus = serviceStatus;
        }

        public List<ServicesEntity> getServices() {
            return services;
        }

        public List<ServiceCityEntity> getServiceCity() {
            return serviceCity;
        }

        public List<ServiceStatusEntity> getServiceStatus() {
            return serviceStatus;
        }

        public static class ServicesEntity implements Parcelable {
            private String name;
            private String value;

            public void setName(String name) {
                this.name = name;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public String getValue() {
                return value;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.name);
                dest.writeString(this.value);
            }

            public ServicesEntity() {
            }

            protected ServicesEntity(Parcel in) {
                this.name = in.readString();
                this.value = in.readString();
            }

            public static final Parcelable.Creator<ServicesEntity> CREATOR = new Parcelable.Creator<ServicesEntity>() {
                public ServicesEntity createFromParcel(Parcel source) {
                    return new ServicesEntity(source);
                }

                public ServicesEntity[] newArray(int size) {
                    return new ServicesEntity[size];
                }
            };
        }

        public static class ServiceCityEntity implements Parcelable {
            private String name;
            private String value;

            public void setName(String name) {
                this.name = name;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public String getValue() {
                return value;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.name);
                dest.writeString(this.value);
            }

            public ServiceCityEntity() {
            }

            protected ServiceCityEntity(Parcel in) {
                this.name = in.readString();
                this.value = in.readString();
            }

            public static final Parcelable.Creator<ServiceCityEntity> CREATOR = new Parcelable.Creator<ServiceCityEntity>() {
                public ServiceCityEntity createFromParcel(Parcel source) {
                    return new ServiceCityEntity(source);
                }

                public ServiceCityEntity[] newArray(int size) {
                    return new ServiceCityEntity[size];
                }
            };
        }

        public static class ServiceStatusEntity implements Parcelable {
            private String name;
            private String value;

            public void setName(String name) {
                this.name = name;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public String getValue() {
                return value;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.name);
                dest.writeString(this.value);
            }

            public ServiceStatusEntity() {
            }

            protected ServiceStatusEntity(Parcel in) {
                this.name = in.readString();
                this.value = in.readString();
            }

            public static final Parcelable.Creator<ServiceStatusEntity> CREATOR = new Parcelable.Creator<ServiceStatusEntity>() {
                public ServiceStatusEntity createFromParcel(Parcel source) {
                    return new ServiceStatusEntity(source);
                }

                public ServiceStatusEntity[] newArray(int size) {
                    return new ServiceStatusEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(services);
            dest.writeTypedList(serviceCity);
            dest.writeTypedList(serviceStatus);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.services = in.createTypedArrayList(ServicesEntity.CREATOR);
            this.serviceCity = in.createTypedArrayList(ServiceCityEntity.CREATOR);
            this.serviceStatus = in.createTypedArrayList(ServiceStatusEntity.CREATOR);
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

    public ServiceSortModel() {
    }

    protected ServiceSortModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<ServiceSortModel> CREATOR = new Parcelable.Creator<ServiceSortModel>() {
        public ServiceSortModel createFromParcel(Parcel source) {
            return new ServiceSortModel(source);
        }

        public ServiceSortModel[] newArray(int size) {
            return new ServiceSortModel[size];
        }
    };
}
