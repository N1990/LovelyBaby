package com.cmbb.smartkids.activity.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;
import java.util.List;

/**
 * Package on com.cmbb.smartkids.activity.search
 * Created by javon on 15/11/19.
 */
public class SearchUserModel implements Parcelable {


    /**
     * page : 1
     * records : 2
     * rows : [{"id":4,"isEredar":0,"fans":3,"userSmallWidth":1600,"userLevel":1,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户"}],"userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-11-17/image_b330b29680ff4c3a858d85c7678e1062","userSmallHeight":1280,"userNike":"niesen918"},{"id":34,"isEredar":1,"fans":0,"userSmallWidth":750,"userLevel":2,"userRole":[{"eredarCode":160,"eredarName":"认证机构"}],"userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-10-15/C14CF879-8BBA-46D6-B614-0C2585FC7767","userSmallHeight":750,"userNike":"DIY SWEET 21"}]
     * total : 1
     * userdata :
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":2,"rows":[{"id":4,"isEredar":0,"fans":3,"userSmallWidth":1600,"userLevel":1,"userRole":[{"eredarCode":0,"eredarName":"萌宝用户"}],"userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-11-17/image_b330b29680ff4c3a858d85c7678e1062","userSmallHeight":1280,"userNike":"niesen918"},{"id":34,"isEredar":1,"fans":0,"userSmallWidth":750,"userLevel":2,"userRole":[{"eredarCode":160,"eredarName":"认证机构"}],"userSmallImg":"http://smart.image.alimmdn.com/app/test/2015-10-15/C14CF879-8BBA-46D6-B614-0C2585FC7767","userSmallHeight":750,"userNike":"DIY SWEET 21"}],"total":1,"userdata":""}
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
         * id : 4
         * isEredar : 0
         * fans : 3
         * userSmallWidth : 1600
         * userLevel : 1
         * userRole : [{"eredarCode":0,"eredarName":"萌宝用户"}]
         * userSmallImg : http://smart.image.alimmdn.com/app/test/2015-11-17/image_b330b29680ff4c3a858d85c7678e1062
         * userSmallHeight : 1280
         * userNike : niesen918
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
            private int isEredar;
            private int fans;
            private int userSmallWidth;
            private int userLevel;
            private String userSmallImg;
            private int userSmallHeight;
            private String userNike;
            /**
             * eredarCode : 0
             * eredarName : 萌宝用户
             */

            private List<UserRoleEntity> userRole;

            public void setId(int id) {
                this.id = id;
            }

            public void setIsEredar(int isEredar) {
                this.isEredar = isEredar;
            }

            public void setFans(int fans) {
                this.fans = fans;
            }

            public void setUserSmallWidth(int userSmallWidth) {
                this.userSmallWidth = userSmallWidth;
            }

            public void setUserLevel(int userLevel) {
                this.userLevel = userLevel;
            }

            public void setUserSmallImg(String userSmallImg) {
                this.userSmallImg = userSmallImg;
            }

            public void setUserSmallHeight(int userSmallHeight) {
                this.userSmallHeight = userSmallHeight;
            }

            public void setUserNike(String userNike) {
                this.userNike = userNike;
            }

            public void setUserRole(List<UserRoleEntity> userRole) {
                this.userRole = userRole;
            }

            public int getId() {
                return id;
            }

            public int getIsEredar() {
                return isEredar;
            }

            public int getFans() {
                return fans;
            }

            public int getUserSmallWidth() {
                return userSmallWidth;
            }

            public int getUserLevel() {
                return userLevel;
            }

            public String getUserSmallImg() {
                return userSmallImg;
            }

            public int getUserSmallHeight() {
                return userSmallHeight;
            }

            public String getUserNike() {
                return userNike;
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

                public static final Parcelable.Creator<UserRoleEntity> CREATOR = new Parcelable.Creator<UserRoleEntity>() {
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
                dest.writeInt(this.id);
                dest.writeInt(this.isEredar);
                dest.writeInt(this.fans);
                dest.writeInt(this.userSmallWidth);
                dest.writeInt(this.userLevel);
                dest.writeString(this.userSmallImg);
                dest.writeInt(this.userSmallHeight);
                dest.writeString(this.userNike);
                dest.writeTypedList(userRole);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.id = in.readInt();
                this.isEredar = in.readInt();
                this.fans = in.readInt();
                this.userSmallWidth = in.readInt();
                this.userLevel = in.readInt();
                this.userSmallImg = in.readString();
                this.userSmallHeight = in.readInt();
                this.userNike = in.readString();
                this.userRole = in.createTypedArrayList(UserRoleEntity.CREATOR);
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

    public SearchUserModel() {
    }

    protected SearchUserModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<SearchUserModel> CREATOR = new Parcelable.Creator<SearchUserModel>() {
        public SearchUserModel createFromParcel(Parcel source) {
            return new SearchUserModel(source);
        }

        public SearchUserModel[] newArray(int size) {
            return new SearchUserModel[size];
        }
    };

    /**
     * 获取搜索用户列表
     * @param userNike
     * @param pager
     * @param pagerSize
     * @param callback
     */
    public static void getSearchUserRequest(String userNike, int pager, int pagerSize, OkHttpClientManager.ResultCallback<SearchUserModel> callback){
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("userNike", userNike);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.SEARCH_USER_REQUEST, params, callback);
    }
}
