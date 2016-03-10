package com.cmbb.smartkids.activity.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;
import java.util.List;

/**
 * Package on com.cmbb.smartkids.activity.home.model
 * Created by javon on 15/11/19.
 */
public class ServiceSortModel implements Parcelable {


    private DataEntity data;
    /**
     * data : {"services":[{"name":"上门","value":"201"},{"name":"到店","value":"202"},{"name":"培训","value":"203"},{"name":"在线","value":"204"},{"name":"配送","value":"205"}],"serviceCity":[{"name":"北京","value":"110100"},{"name":"上海","value":"310100"},{"name":"广州","value":"440100"}],"serviceCategroy":[{"name":"萌宝独家","value":"MBDJ"},{"name":"好好学习","value":"HHXX"},{"name":"最爱户外","value":"ZAHW"},{"name":"艺海寻师","value":"YHXS"},{"name":"醉心定制","value":"ZXDZ"},{"name":"私家美食","value":"SJMS"},{"name":"国医妙手","value":"GYMS"},{"name":"辣妈速递","value":"LMSD"}],"serviceSortType":[{"name":"价格最低","value":"high_price"},{"name":"价格最高","value":"lowe_pirce"},{"name":"最新发布","value":"new_publish"}]}
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
         * name : 萌宝独家
         * value : MBDJ
         */

        private List<ServiceCategroyEntity> serviceCategroy;
        /**
         * name : 价格最低
         * value : high_price
         */

        private List<ServiceSortTypeEntity> serviceSortType;

        public void setServices(List<ServicesEntity> services) {
            this.services = services;
        }

        public void setServiceCity(List<ServiceCityEntity> serviceCity) {
            this.serviceCity = serviceCity;
        }

        public void setServiceCategroy(List<ServiceCategroyEntity> serviceCategroy) {
            this.serviceCategroy = serviceCategroy;
        }

        public void setServiceSortType(List<ServiceSortTypeEntity> serviceSortType) {
            this.serviceSortType = serviceSortType;
        }

        public List<ServicesEntity> getServices() {
            return services;
        }

        public List<ServiceCityEntity> getServiceCity() {
            return serviceCity;
        }

        public List<ServiceCategroyEntity> getServiceCategroy() {
            return serviceCategroy;
        }

        public List<ServiceSortTypeEntity> getServiceSortType() {
            return serviceSortType;
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

            @Override
            public String toString() {
                return name;
            }
        }

        public static class ServiceCategroyEntity implements Parcelable {
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

            public ServiceCategroyEntity() {
            }

            protected ServiceCategroyEntity(Parcel in) {
                this.name = in.readString();
                this.value = in.readString();
            }

            public static final Parcelable.Creator<ServiceCategroyEntity> CREATOR = new Parcelable.Creator<ServiceCategroyEntity>() {
                public ServiceCategroyEntity createFromParcel(Parcel source) {
                    return new ServiceCategroyEntity(source);
                }

                public ServiceCategroyEntity[] newArray(int size) {
                    return new ServiceCategroyEntity[size];
                }
            };
        }

        public static class ServiceSortTypeEntity implements Parcelable {
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

            public ServiceSortTypeEntity() {
            }

            protected ServiceSortTypeEntity(Parcel in) {
                this.name = in.readString();
                this.value = in.readString();
            }

            public static final Parcelable.Creator<ServiceSortTypeEntity> CREATOR = new Parcelable.Creator<ServiceSortTypeEntity>() {
                public ServiceSortTypeEntity createFromParcel(Parcel source) {
                    return new ServiceSortTypeEntity(source);
                }

                public ServiceSortTypeEntity[] newArray(int size) {
                    return new ServiceSortTypeEntity[size];
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
            dest.writeTypedList(serviceCategroy);
            dest.writeTypedList(serviceSortType);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.services = in.createTypedArrayList(ServicesEntity.CREATOR);
            this.serviceCity = in.createTypedArrayList(ServiceCityEntity.CREATOR);
            this.serviceCategroy = in.createTypedArrayList(ServiceCategroyEntity.CREATOR);
            this.serviceSortType = in.createTypedArrayList(ServiceSortTypeEntity.CREATOR);
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

    public static void getServiceSortREquest(OkHttpClientManager.ResultCallback<ServiceSortModel> callback){
        HashMap<String, String> params = new HashMap<>();
        params.put("typeCode", "serviceCity,serviceCategroy,services,serviceSortType");
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.SERVICE_SORT_REQUEST, params, callback);
    }

}
