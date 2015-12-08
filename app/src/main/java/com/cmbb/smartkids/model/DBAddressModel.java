package com.cmbb.smartkids.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by javon on 15/11/25.
 */
public class DBAddressModel implements Parcelable {


    /**
     * id : 110000
     * code : 010
     * name : 北京
     * pid : 0
     * level : 1
     * cities : [{"id":"110100","code":"010","name":"北京市","pid":"110000","level":"2","counties":[{"id":"110101","code":"010","name":"东城区","pid":"110100","level":"3"},{"id":"110102","code":"010","name":"西城区","pid":"110100","level":"3"},{"id":"110105","code":"010","name":"朝阳区","pid":"110100","level":"3"},{"id":"110106","code":"010","name":"丰台区","pid":"110100","level":"3"},{"id":"110107","code":"010","name":"石景山区","pid":"110100","level":"3"},{"id":"110108","code":"010","name":"海淀区","pid":"110100","level":"3"},{"id":"110109","code":"010","name":"门头沟区","pid":"110100","level":"3"},{"id":"110111","code":"010","name":"房山区","pid":"110100","level":"3"},{"id":"110112","code":"010","name":"通州区","pid":"110100","level":"3"},{"id":"110113","code":"010","name":"顺义区","pid":"110100","level":"3"},{"id":"110114","code":"010","name":"昌平区","pid":"110100","level":"3"},{"id":"110115","code":"010","name":"大兴区","pid":"110100","level":"3"},{"id":"110116","code":"010","name":"怀柔区","pid":"110100","level":"3"},{"id":"110117","code":"010","name":"平谷区","pid":"110100","level":"3"},{"id":"110228","code":"010","name":"密云县","pid":"110100","level":"3"},{"id":"110229","code":"010","name":"延庆县","pid":"110100","level":"3"}]}]
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Parcelable {
        private String id;
        private String code;
        private String name;
        private String pid;
        private String level;
        /**
         * id : 110100
         * code : 010
         * name : 北京市
         * pid : 110000
         * level : 2
         * counties : [{"id":"110101","code":"010","name":"东城区","pid":"110100","level":"3"},{"id":"110102","code":"010","name":"西城区","pid":"110100","level":"3"},{"id":"110105","code":"010","name":"朝阳区","pid":"110100","level":"3"},{"id":"110106","code":"010","name":"丰台区","pid":"110100","level":"3"},{"id":"110107","code":"010","name":"石景山区","pid":"110100","level":"3"},{"id":"110108","code":"010","name":"海淀区","pid":"110100","level":"3"},{"id":"110109","code":"010","name":"门头沟区","pid":"110100","level":"3"},{"id":"110111","code":"010","name":"房山区","pid":"110100","level":"3"},{"id":"110112","code":"010","name":"通州区","pid":"110100","level":"3"},{"id":"110113","code":"010","name":"顺义区","pid":"110100","level":"3"},{"id":"110114","code":"010","name":"昌平区","pid":"110100","level":"3"},{"id":"110115","code":"010","name":"大兴区","pid":"110100","level":"3"},{"id":"110116","code":"010","name":"怀柔区","pid":"110100","level":"3"},{"id":"110117","code":"010","name":"平谷区","pid":"110100","level":"3"},{"id":"110228","code":"010","name":"密云县","pid":"110100","level":"3"},{"id":"110229","code":"010","name":"延庆县","pid":"110100","level":"3"}]
         */

        private List<CitiesEntity> cities;

        public void setId(String id) {
            this.id = id;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public void setCities(List<CitiesEntity> cities) {
            this.cities = cities;
        }

        public String getId() {
            return id;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getPid() {
            return pid;
        }

        public String getLevel() {
            return level;
        }

        public List<CitiesEntity> getCities() {
            return cities;
        }

        public static class CitiesEntity implements Parcelable {
            private String id;
            private String code;
            private String name;
            private String pid;
            private String level;
            /**
             * id : 110101
             * code : 010
             * name : 东城区
             * pid : 110100
             * level : 3
             */

            private List<CountiesEntity> counties;

            public void setId(String id) {
                this.id = id;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public void setCounties(List<CountiesEntity> counties) {
                this.counties = counties;
            }

            public String getId() {
                return id;
            }

            public String getCode() {
                return code;
            }

            public String getName() {
                return name;
            }

            public String getPid() {
                return pid;
            }

            public String getLevel() {
                return level;
            }

            public List<CountiesEntity> getCounties() {
                return counties;
            }

            public static class CountiesEntity implements Parcelable {
                private String id;
                private String code;
                private String name;
                private String pid;
                private String level;

                public void setId(String id) {
                    this.id = id;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getId() {
                    return id;
                }

                public String getCode() {
                    return code;
                }

                public String getName() {
                    return name;
                }

                public String getPid() {
                    return pid;
                }

                public String getLevel() {
                    return level;
                }


                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.id);
                    dest.writeString(this.code);
                    dest.writeString(this.name);
                    dest.writeString(this.pid);
                    dest.writeString(this.level);
                }

                public CountiesEntity() {
                }

                protected CountiesEntity(Parcel in) {
                    this.id = in.readString();
                    this.code = in.readString();
                    this.name = in.readString();
                    this.pid = in.readString();
                    this.level = in.readString();
                }

                public static final Parcelable.Creator<CountiesEntity> CREATOR = new Parcelable.Creator<CountiesEntity>() {
                    public CountiesEntity createFromParcel(Parcel source) {
                        return new CountiesEntity(source);
                    }

                    public CountiesEntity[] newArray(int size) {
                        return new CountiesEntity[size];
                    }
                };

                @Override
                public String toString() {
                    return "CountiesEntity{" +
                            "id='" + id + '\'' +
                            ", code='" + code + '\'' +
                            ", name='" + name + '\'' +
                            ", pid='" + pid + '\'' +
                            ", level='" + level + '\'' +
                            '}';
                }
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.code);
                dest.writeString(this.name);
                dest.writeString(this.pid);
                dest.writeString(this.level);
                dest.writeTypedList(counties);
            }

            public CitiesEntity() {
            }

            protected CitiesEntity(Parcel in) {
                this.id = in.readString();
                this.code = in.readString();
                this.name = in.readString();
                this.pid = in.readString();
                this.level = in.readString();
                this.counties = in.createTypedArrayList(CountiesEntity.CREATOR);
            }

            public static final Parcelable.Creator<CitiesEntity> CREATOR = new Parcelable.Creator<CitiesEntity>() {
                public CitiesEntity createFromParcel(Parcel source) {
                    return new CitiesEntity(source);
                }

                public CitiesEntity[] newArray(int size) {
                    return new CitiesEntity[size];
                }
            };

            @Override
            public String toString() {
                return "CitiesEntity{" +
                        "id='" + id + '\'' +
                        ", code='" + code + '\'' +
                        ", name='" + name + '\'' +
                        ", pid='" + pid + '\'' +
                        ", level='" + level + '\'' +
                        ", counties=" + counties +
                        '}';
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.code);
            dest.writeString(this.name);
            dest.writeString(this.pid);
            dest.writeString(this.level);
            dest.writeTypedList(cities);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.id = in.readString();
            this.code = in.readString();
            this.name = in.readString();
            this.pid = in.readString();
            this.level = in.readString();
            this.cities = in.createTypedArrayList(CitiesEntity.CREATOR);
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
                    "id='" + id + '\'' +
                    ", code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", pid='" + pid + '\'' +
                    ", level='" + level + '\'' +
                    ", cities=" + cities +
                    '}';
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
    }

    public DBAddressModel() {
    }

    protected DBAddressModel(Parcel in) {
        this.data = in.createTypedArrayList(DataEntity.CREATOR);
    }

    public static final Parcelable.Creator<DBAddressModel> CREATOR = new Parcelable.Creator<DBAddressModel>() {
        public DBAddressModel createFromParcel(Parcel source) {
            return new DBAddressModel(source);
        }

        public DBAddressModel[] newArray(int size) {
            return new DBAddressModel[size];
        }
    };

    @Override
    public String toString() {
        return "DBAddressModel{" +
                "data=" + data +
                '}';
    }
}
