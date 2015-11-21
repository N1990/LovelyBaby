package com.cmbb.smartkids.activity.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/20 16:34
 */
public class FriendListModel implements Parcelable {

    /**
     * status : 1
     * data : {"page":1,"records":2,"rows":[{"userId":1,"userNike":"黄河","userSex":1,"userBigImg":"http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf","userBigWidth":null,"userBigHeight":null,"userSmallImg":"http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf","userSmallWidth":null,"userSmallHeight":null,"userRole":[{"eredarName":"萌宝用户","eredarCode":0}]},{"userId":2,"userNike":"心静如水","userSex":null,"userBigImg":"http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf","userBigWidth":null,"userBigHeight":null,"userSmallImg":"http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf","userSmallWidth":null,"userSmallHeight":null,"userRole":[{"eredarName":"萌宝用户","eredarCode":0}]}],"total":1,"userdata":null}
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
         * records : 2
         * rows : [{"userId":1,"userNike":"黄河","userSex":1,"userBigImg":"http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf","userBigWidth":null,"userBigHeight":null,"userSmallImg":"http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf","userSmallWidth":null,"userSmallHeight":null,"userRole":[{"eredarName":"萌宝用户","eredarCode":0}]},{"userId":2,"userNike":"心静如水","userSex":null,"userBigImg":"http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf","userBigWidth":null,"userBigHeight":null,"userSmallImg":"http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf","userSmallWidth":null,"userSmallHeight":null,"userRole":[{"eredarName":"萌宝用户","eredarCode":0}]}]
         * total : 1
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
             * userId : 1
             * userNike : 黄河
             * userSex : 1
             * userBigImg : http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf
             * userBigWidth : null
             * userBigHeight : null
             * userSmallImg : http://fuck.image.alimmdn.com/2015-9-16/image_3c20b91934a04fa394fa5550f7aea1cf
             * userSmallWidth : null
             * userSmallHeight : null
             * userRole : [{"eredarName":"萌宝用户","eredarCode":0}]
             */

            private int userId;
            private String userNike;
            private int userSex;
            private String userBigImg;
            private String userBigWidth;
            private String userBigHeight;
            private String userSmallImg;
            private String userSmallWidth;
            private String userSmallHeight;
            private List<UserRoleEntity> userRole;

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public void setUserNike(String userNike) {
                this.userNike = userNike;
            }

            public void setUserSex(int userSex) {
                this.userSex = userSex;
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

            public void setUserRole(List<UserRoleEntity> userRole) {
                this.userRole = userRole;
            }

            public int getUserId() {
                return userId;
            }

            public String getUserNike() {
                return userNike;
            }

            public int getUserSex() {
                return userSex;
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

            public List<UserRoleEntity> getUserRole() {
                return userRole;
            }

            public static class UserRoleEntity implements Parcelable {
                /**
                 * eredarName : 萌宝用户
                 * eredarCode : 0
                 */

                private String eredarName;
                private int eredarCode;

                public void setEredarName(String eredarName) {
                    this.eredarName = eredarName;
                }

                public void setEredarCode(int eredarCode) {
                    this.eredarCode = eredarCode;
                }

                public String getEredarName() {
                    return eredarName;
                }

                public int getEredarCode() {
                    return eredarCode;
                }


                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.eredarName);
                    dest.writeInt(this.eredarCode);
                }

                public UserRoleEntity() {
                }

                protected UserRoleEntity(Parcel in) {
                    this.eredarName = in.readString();
                    this.eredarCode = in.readInt();
                }

                public static final Parcelable.Creator<UserRoleEntity> CREATOR = new Parcelable.Creator<UserRoleEntity>() {
                    public UserRoleEntity createFromParcel(Parcel source) {
                        return new UserRoleEntity(source);
                    }

                    public UserRoleEntity[] newArray(int size) {
                        return new UserRoleEntity[size];
                    }
                };

                @Override
                public String toString() {
                    return "UserRoleEntity{" +
                            "eredarName='" + eredarName + '\'' +
                            ", eredarCode=" + eredarCode +
                            '}';
                }
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.userId);
                dest.writeString(this.userNike);
                dest.writeInt(this.userSex);
                dest.writeString(this.userBigImg);
                dest.writeString(this.userBigWidth);
                dest.writeString(this.userBigHeight);
                dest.writeString(this.userSmallImg);
                dest.writeString(this.userSmallWidth);
                dest.writeString(this.userSmallHeight);
                dest.writeTypedList(userRole);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.userId = in.readInt();
                this.userNike = in.readString();
                this.userSex = in.readInt();
                this.userBigImg = in.readString();
                this.userBigWidth = in.readString();
                this.userBigHeight = in.readString();
                this.userSmallImg = in.readString();
                this.userSmallWidth = in.readString();
                this.userSmallHeight = in.readString();
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

            @Override
            public String toString() {
                return "RowsEntity{" +
                        "userId=" + userId +
                        ", userNike='" + userNike + '\'' +
                        ", userSex=" + userSex +
                        ", userBigImg='" + userBigImg + '\'' +
                        ", userBigWidth=" + userBigWidth +
                        ", userBigHeight=" + userBigHeight +
                        ", userSmallImg='" + userSmallImg + '\'' +
                        ", userSmallWidth=" + userSmallWidth +
                        ", userSmallHeight=" + userSmallHeight +
                        ", userRole=" + userRole +
                        '}';
            }
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

        @Override
        public String toString() {
            return "DataEntity{" +
                    "page=" + page +
                    ", records=" + records +
                    ", total=" + total +
                    ", userdata=" + userdata +
                    ", rows=" + rows +
                    '}';
        }
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

    public FriendListModel() {
    }

    protected FriendListModel(Parcel in) {
        this.status = in.readInt();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<FriendListModel> CREATOR = new Parcelable.Creator<FriendListModel>() {
        public FriendListModel createFromParcel(Parcel source) {
            return new FriendListModel(source);
        }

        public FriendListModel[] newArray(int size) {
            return new FriendListModel[size];
        }
    };

    @Override
    public String toString() {
        return "FriendListModel{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
