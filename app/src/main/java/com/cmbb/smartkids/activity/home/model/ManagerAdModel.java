package com.cmbb.smartkids.activity.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;
import java.util.List;

/**
 * Created by javon on 16/3/8.
 */
public class ManagerAdModel implements Parcelable {


    /**
     * data : [{"id":38,"adImg":"http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl","imgWidth":"539","imgHeight":"355","systemType":"MBP_APP","adType":"SIGN","redirectType":"INNER","innerRedirectType":"APP_TOPIC","relateId":339,"redirectUrl":"","isEnabled":true,"sortNum":3,"remark":"广告备注","creatorId":9,"createTime":"2016-03-11 17:20:52","modifierId":2,"modifyTime":"","creatorName":"","modifyName":""}]
     * msg : 数据加载成功
     */

    private String msg;
    /**
     * id : 38
     * adImg : http://smart.image.alimmdn.com/system/image/2016-03-01/servicesImgFile_N2EwOGZhMGQtNTI1My00MjA3LTgwMzEtZjdjMjM5NzJhNDRl
     * imgWidth : 539
     * imgHeight : 355
     * systemType : MBP_APP
     * adType : SIGN
     * redirectType : INNER
     * innerRedirectType : APP_TOPIC
     * relateId : 339
     * redirectUrl :
     * isEnabled : true
     * sortNum : 3
     * remark : 广告备注
     * creatorId : 9
     * createTime : 2016-03-11 17:20:52
     * modifierId : 2
     * modifyTime :
     * creatorName :
     * modifyName :
     */

    private List<DataEntity> data;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }


    public static class DataEntity implements Parcelable {
        private int id;
        private String adImg;
        private String imgWidth;
        private String imgHeight;
        private String systemType;
        private String adType;
        private String redirectType;
        private String innerRedirectType;
        private int relateId;
        private String redirectUrl;
        private boolean isEnabled;
        private int sortNum;
        private String remark;
        private int creatorId;
        private String createTime;
        private int modifierId;
        private String modifyTime;
        private String creatorName;
        private String modifyName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAdImg() {
            return adImg;
        }

        public void setAdImg(String adImg) {
            this.adImg = adImg;
        }

        public String getImgWidth() {
            return imgWidth;
        }

        public void setImgWidth(String imgWidth) {
            this.imgWidth = imgWidth;
        }

        public String getImgHeight() {
            return imgHeight;
        }

        public void setImgHeight(String imgHeight) {
            this.imgHeight = imgHeight;
        }

        public String getSystemType() {
            return systemType;
        }

        public void setSystemType(String systemType) {
            this.systemType = systemType;
        }

        public String getAdType() {
            return adType;
        }

        public void setAdType(String adType) {
            this.adType = adType;
        }

        public String getRedirectType() {
            return redirectType;
        }

        public void setRedirectType(String redirectType) {
            this.redirectType = redirectType;
        }

        public String getInnerRedirectType() {
            return innerRedirectType;
        }

        public void setInnerRedirectType(String innerRedirectType) {
            this.innerRedirectType = innerRedirectType;
        }

        public int getRelateId() {
            return relateId;
        }

        public void setRelateId(int relateId) {
            this.relateId = relateId;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public boolean isIsEnabled() {
            return isEnabled;
        }

        public void setIsEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
        }

        public int getSortNum() {
            return sortNum;
        }

        public void setSortNum(int sortNum) {
            this.sortNum = sortNum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getModifierId() {
            return modifierId;
        }

        public void setModifierId(int modifierId) {
            this.modifierId = modifierId;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public String getModifyName() {
            return modifyName;
        }

        public void setModifyName(String modifyName) {
            this.modifyName = modifyName;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.adImg);
            dest.writeString(this.imgWidth);
            dest.writeString(this.imgHeight);
            dest.writeString(this.systemType);
            dest.writeString(this.adType);
            dest.writeString(this.redirectType);
            dest.writeString(this.innerRedirectType);
            dest.writeInt(this.relateId);
            dest.writeString(this.redirectUrl);
            dest.writeByte(isEnabled ? (byte) 1 : (byte) 0);
            dest.writeInt(this.sortNum);
            dest.writeString(this.remark);
            dest.writeInt(this.creatorId);
            dest.writeString(this.createTime);
            dest.writeInt(this.modifierId);
            dest.writeString(this.modifyTime);
            dest.writeString(this.creatorName);
            dest.writeString(this.modifyName);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.id = in.readInt();
            this.adImg = in.readString();
            this.imgWidth = in.readString();
            this.imgHeight = in.readString();
            this.systemType = in.readString();
            this.adType = in.readString();
            this.redirectType = in.readString();
            this.innerRedirectType = in.readString();
            this.relateId = in.readInt();
            this.redirectUrl = in.readString();
            this.isEnabled = in.readByte() != 0;
            this.sortNum = in.readInt();
            this.remark = in.readString();
            this.creatorId = in.readInt();
            this.createTime = in.readString();
            this.modifierId = in.readInt();
            this.modifyTime = in.readString();
            this.creatorName = in.readString();
            this.modifyName = in.readString();
        }

        public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
            @Override
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            @Override
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
        dest.writeString(this.msg);
        dest.writeTypedList(data);
    }

    public ManagerAdModel() {
    }

    protected ManagerAdModel(Parcel in) {
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataEntity.CREATOR);
    }

    public static final Parcelable.Creator<ManagerAdModel> CREATOR = new Parcelable.Creator<ManagerAdModel>() {
        @Override
        public ManagerAdModel createFromParcel(Parcel source) {
            return new ManagerAdModel(source);
        }

        @Override
        public ManagerAdModel[] newArray(int size) {
            return new ManagerAdModel[size];
        }
    };

    /**
     * 飞溅页图片获取
     *
     * @param callback
     */
    public static void getSplashImgRequest(OkHttpClientManager.ResultCallback<ManagerAdModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("adType", "WELCOME");
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.AD_MANAGER, params, callback);
    }

    /**
     * 首页广告图片获取
     *
     * @param callback
     */
    public static void getHomeImgRequest(OkHttpClientManager.ResultCallback<ManagerAdModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("adType", "INDEX");
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.AD_MANAGER, params, callback);
    }

    public static void getWonderfulImgRequest(OkHttpClientManager.ResultCallback<ManagerAdModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("adType", "REVIEW");
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.AD_MANAGER, params, callback);
    }
}
