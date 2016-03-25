package com.cmbb.smartkids.activity.message.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/12/17 下午4:32
 */
public class MessageCountModel implements Parcelable {


    private String msg;

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
        private String noticeContent;
        private int id;
        private String modual;
        private int noticeCount;

        public void setNoticeContent(String noticeContent) {
            this.noticeContent = noticeContent;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setModual(String modual) {
            this.modual = modual;
        }

        public void setNoticeCount(int noticeCount) {
            this.noticeCount = noticeCount;
        }

        public String getNoticeContent() {
            return noticeContent;
        }

        public int getId() {
            return id;
        }

        public String getModual() {
            return modual;
        }

        public int getNoticeCount() {
            return noticeCount;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.noticeContent);
            dest.writeInt(this.id);
            dest.writeString(this.modual);
            dest.writeInt(this.noticeCount);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.noticeContent = in.readString();
            this.id = in.readInt();
            this.modual = in.readString();
            this.noticeCount = in.readInt();
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

    public MessageCountModel() {
    }

    protected MessageCountModel(Parcel in) {
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataEntity.CREATOR);
    }

    public static final Parcelable.Creator<MessageCountModel> CREATOR = new Parcelable.Creator<MessageCountModel>() {
        public MessageCountModel createFromParcel(Parcel source) {
            return new MessageCountModel(source);
        }

        public MessageCountModel[] newArray(int size) {
            return new MessageCountModel[size];
        }
    };


    /**
     * 至空消息
     *
     * @param id 消息ID
     */
    public static void handleEmptyMessageRequest(String id, OkHttpClientManager.ResultCallback<MessageCountModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("token", BaseApplication.token);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.MESSAGE_SET_MESSAGE_TYPE, params, callback);
    }
}
