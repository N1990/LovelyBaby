package com.cmbb.smartkids.activity.order.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/27 14:52
 */
public class RefundModel implements Parcelable {

    /**
     * status : 200
     * name : 退款中
     */

    private String status;
    private String name;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.name);
    }

    public RefundModel() {
    }

    protected RefundModel(Parcel in) {
        this.status = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<RefundModel> CREATOR = new Parcelable.Creator<RefundModel>() {
        public RefundModel createFromParcel(Parcel source) {
            return new RefundModel(source);
        }

        public RefundModel[] newArray(int size) {
            return new RefundModel[size];
        }
    };

    @Override
    public String toString() {
        return "RefundModel{" +
                "status='" + status + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 申请退款
     *
     * @param orderCode
     * @param callback
     */
    public static void handleApplyRefundRequest(String orderCode, String refundReason, OkHttpClientManager.ResultCallback<RefundModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("orderCode", orderCode);
        params.put("refundReason", refundReason);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.APPLY_REFUND_REQUEST, params, callback);
    }
}
