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
     * data : [{"id":4,"adImg":"http://smart.image.alimmdn.com/system/image/2016-03-07/38bOOOPIC06_MTc5NWE1YjYtZjU1ZS00Yjg4LWIyMGQtNzNjN2UxM2ZlOWE2jpg","imgWidth":"650","imgHeight":"433","adType":"INDEX","redirectType":"INNER","redirectUrl":"12123223332","isEnabled":true,"sortNum":"1","creatorId":1,"createTime":"2016-03-07 15:15:28","modifierId":1,"modifyTime":"2016-03-07 16:38:17","creatorName":"","modifyName":""}]
     * msg : 加载成功
     */

    private String msg;
    /**
     * id : 4
     * adImg : http://smart.image.alimmdn.com/system/image/2016-03-07/38bOOOPIC06_MTc5NWE1YjYtZjU1ZS00Yjg4LWIyMGQtNzNjN2UxM2ZlOWE2jpg
     * imgWidth : 650
     * imgHeight : 433
     * adType : INDEX
     * redirectType : INNER
     * redirectUrl : 12123223332
     * isEnabled : true
     * sortNum : 1
     * creatorId : 1
     * createTime : 2016-03-07 15:15:28
     * modifierId : 1
     * modifyTime : 2016-03-07 16:38:17
     * creatorName :
     * modifyName :
     */

    private List<DataEntity> data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Parcelable {
        private int id;
        private String adImg;
        private String imgWidth;
        private String imgHeight;
        private String adType;
        private String redirectType;
        private String redirectUrl;
        private boolean isEnabled;
        private String sortNum;
        private int creatorId;
        private String createTime;
        private int modifierId;
        private String modifyTime;
        private String creatorName;
        private String modifyName;

        public void setId(int id) {
            this.id = id;
        }

        public void setAdImg(String adImg) {
            this.adImg = adImg;
        }

        public void setImgWidth(String imgWidth) {
            this.imgWidth = imgWidth;
        }

        public void setImgHeight(String imgHeight) {
            this.imgHeight = imgHeight;
        }

        public void setAdType(String adType) {
            this.adType = adType;
        }

        public void setRedirectType(String redirectType) {
            this.redirectType = redirectType;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public void setIsEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
        }

        public void setSortNum(String sortNum) {
            this.sortNum = sortNum;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public void setModifierId(int modifierId) {
            this.modifierId = modifierId;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public void setModifyName(String modifyName) {
            this.modifyName = modifyName;
        }

        public int getId() {
            return id;
        }

        public String getAdImg() {
            return adImg;
        }

        public String getImgWidth() {
            return imgWidth;
        }

        public String getImgHeight() {
            return imgHeight;
        }

        public String getAdType() {
            return adType;
        }

        public String getRedirectType() {
            return redirectType;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public boolean isIsEnabled() {
            return isEnabled;
        }

        public String getSortNum() {
            return sortNum;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public int getModifierId() {
            return modifierId;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public String getModifyName() {
            return modifyName;
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
            dest.writeString(this.adType);
            dest.writeString(this.redirectType);
            dest.writeString(this.redirectUrl);
            dest.writeByte(isEnabled ? (byte) 1 : (byte) 0);
            dest.writeString(this.sortNum);
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
            this.adType = in.readString();
            this.redirectType = in.readString();
            this.redirectUrl = in.readString();
            this.isEnabled = in.readByte() != 0;
            this.sortNum = in.readString();
            this.creatorId = in.readInt();
            this.createTime = in.readString();
            this.modifierId = in.readInt();
            this.modifyTime = in.readString();
            this.creatorName = in.readString();
            this.modifyName = in.readString();
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
        public ManagerAdModel createFromParcel(Parcel source) {
            return new ManagerAdModel(source);
        }

        public ManagerAdModel[] newArray(int size) {
            return new ManagerAdModel[size];
        }
    };

    /**
     *  飞溅页图片获取
     * @param callback
     */
    public static void getSplashImgRequest(OkHttpClientManager.ResultCallback<ManagerAdModel> callback){
        HashMap<String, String> params = new HashMap<>();
        params.put("adType", "WELCOME");
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.AD_MANAGER, params, callback);
    }

    /**
     * 首页广告图片获取
     * @param callback
     */
    public static void getHomeImgRequest(OkHttpClientManager.ResultCallback<ManagerAdModel> callback){
        HashMap<String, String> params = new HashMap<>();
        params.put("adType", "INDEX");
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.AD_MANAGER, params, callback);
    }



}
