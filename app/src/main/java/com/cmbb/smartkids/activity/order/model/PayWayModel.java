package com.cmbb.smartkids.activity.order.model;

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
 * 创建人：javon
 * 创建时间：2015/10/22 19:25
 */
public class PayWayModel implements Parcelable {


    /**
     * orderId : 285
     * payTypes : [{"paymentData":"partner=\"2088021604444292\"&seller_id=\"ios_android_smart@smart-kids.com\"&out_trade_no=\"dev_act_330845135674944242_1445918085819\"&subject=\"服务活动-付费测试服务（请不要预定）\"&body=\"服务活动-付费测试服务（请不要预定）\"&total_fee=\"12\"&notify_url=\"http%3A%2F%2F121.41.61.142%3A8081%2Fwine-rest%2Fnotify%2Falipay%2Fpay\"&service=\"mobile.securitypay.pay\"&_input_charset=\"UTF-8\"&payment_type=\"1\"&it_b_pay=\"1m\"&sign=\"8b9cb6b5af8b4759e0b0441fc20b1073\"&sign_type=\"MD5\"","paymentTypeId":"1","name":"支付宝"}]
     */

    private String orderId;
    /**
     * paymentData : partner="2088021604444292"&seller_id="ios_android_smart@smart-kids.com"&out_trade_no="dev_act_330845135674944242_1445918085819"&subject="服务活动-付费测试服务（请不要预定）"&body="服务活动-付费测试服务（请不要预定）"&total_fee="12"&notify_url="http%3A%2F%2F121.41.61.142%3A8081%2Fwine-rest%2Fnotify%2Falipay%2Fpay"&service="mobile.securitypay.pay"&_input_charset="UTF-8"&payment_type="1"&it_b_pay="1m"&sign="8b9cb6b5af8b4759e0b0441fc20b1073"&sign_type="MD5"
     * paymentTypeId : 1
     * name : 支付宝
     */

    private List<PayTypesEntity> payTypes;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setPayTypes(List<PayTypesEntity> payTypes) {
        this.payTypes = payTypes;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<PayTypesEntity> getPayTypes() {
        return payTypes;
    }

    public static class PayTypesEntity implements Parcelable {
        private String paymentData;
        private String paymentTypeId;
        private String name;

        public void setPaymentData(String paymentData) {
            this.paymentData = paymentData;
        }

        public void setPaymentTypeId(String paymentTypeId) {
            this.paymentTypeId = paymentTypeId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPaymentData() {
            return paymentData;
        }

        public String getPaymentTypeId() {
            return paymentTypeId;
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
            dest.writeString(this.paymentData);
            dest.writeString(this.paymentTypeId);
            dest.writeString(this.name);
        }

        public PayTypesEntity() {
        }

        protected PayTypesEntity(Parcel in) {
            this.paymentData = in.readString();
            this.paymentTypeId = in.readString();
            this.name = in.readString();
        }

        public static final Parcelable.Creator<PayTypesEntity> CREATOR = new Parcelable.Creator<PayTypesEntity>() {
            public PayTypesEntity createFromParcel(Parcel source) {
                return new PayTypesEntity(source);
            }

            public PayTypesEntity[] newArray(int size) {
                return new PayTypesEntity[size];
            }
        };

        @Override
        public String toString() {
            return "PayTypesEntity{" +
                    "paymentData='" + paymentData + '\'' +
                    ", paymentTypeId='" + paymentTypeId + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderId);
        dest.writeTypedList(payTypes);
    }

    public PayWayModel() {
    }

    protected PayWayModel(Parcel in) {
        this.orderId = in.readString();
        this.payTypes = in.createTypedArrayList(PayTypesEntity.CREATOR);
    }

    public static final Parcelable.Creator<PayWayModel> CREATOR = new Parcelable.Creator<PayWayModel>() {
        public PayWayModel createFromParcel(Parcel source) {
            return new PayWayModel(source);
        }

        public PayWayModel[] newArray(int size) {
            return new PayWayModel[size];
        }
    };

    @Override
    public String toString() {
        return "PayWayModel{" +
                "orderId='" + orderId + '\'' +
                ", payTypes=" + payTypes +
                '}';
    }


    public static void getPayWayRequest(String orderCode, OkHttpClientManager.ResultCallback<PayWayModel> callback){
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("orderCode", orderCode);
        params.put("paymentTypeId", "1");
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.PAY_WAY_LIST, params, callback);
    }
}
