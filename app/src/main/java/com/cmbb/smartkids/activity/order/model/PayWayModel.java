package com.cmbb.smartkids.activity.order.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.google.gson.annotations.SerializedName;

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
     * orderId : 120
     * payTypes : [{"paymentData":"partner=\"2088021604444292\"&seller_id=\"ios_android_smart@smart-kids.com\"&out_trade_no=\"003497990949460013_1458817403504\"&subject=\"服务活动-ERP服务测试\"&body=\"服务活动-ERP服务测试\"&total_fee=\"0.02\"&notify_url=\"http%3A%2F%2F192.168.100.188%3A8081%2Fwine-rest%2Fnotify%2Falipay%2Fpay\"&service=\"mobile.securitypay.pay\"&_input_charset=\"UTF-8\"&payment_type=\"1\"&it_b_pay=\"1m\"&sign=\"960b2a638d78e8ab2f0cffce9dba560c\"&sign_type=\"MD5\"","paymentTypeId":"1","name":"支付宝","weixinData":{"timestamp":"1458817404","sign":"205315C437E0430F1AECF97944279C89","retcode":0,"partnerid":"1324792601","noncestr":"a11ce019e96a4c60832eadd755a17a58","prepayid":"wx20160324190321f3bb4637bf0316719553","package":"Sign=WXPay","retmsg":"OK","appid":"wx766b807ef51aa8da"}}]
     */

    private String orderId;
    /**
     * paymentData : partner="2088021604444292"&seller_id="ios_android_smart@smart-kids.com"&out_trade_no="003497990949460013_1458817403504"&subject="服务活动-ERP服务测试"&body="服务活动-ERP服务测试"&total_fee="0.02"&notify_url="http%3A%2F%2F192.168.100.188%3A8081%2Fwine-rest%2Fnotify%2Falipay%2Fpay"&service="mobile.securitypay.pay"&_input_charset="UTF-8"&payment_type="1"&it_b_pay="1m"&sign="960b2a638d78e8ab2f0cffce9dba560c"&sign_type="MD5"
     * paymentTypeId : 1
     * name : 支付宝
     * weixinData : {"timestamp":"1458817404","sign":"205315C437E0430F1AECF97944279C89","retcode":0,"partnerid":"1324792601","noncestr":"a11ce019e96a4c60832eadd755a17a58","prepayid":"wx20160324190321f3bb4637bf0316719553","package":"Sign=WXPay","retmsg":"OK","appid":"wx766b807ef51aa8da"}
     */

    private List<PayTypesEntity> payTypes;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<PayTypesEntity> getPayTypes() {
        return payTypes;
    }

    public void setPayTypes(List<PayTypesEntity> payTypes) {
        this.payTypes = payTypes;
    }

    public static class PayTypesEntity implements Parcelable {
        private String paymentData;
        private String paymentTypeId;
        private String name;
        /**
         * timestamp : 1458817404
         * sign : 205315C437E0430F1AECF97944279C89
         * retcode : 0
         * partnerid : 1324792601
         * noncestr : a11ce019e96a4c60832eadd755a17a58
         * prepayid : wx20160324190321f3bb4637bf0316719553
         * package : Sign=WXPay
         * retmsg : OK
         * appid : wx766b807ef51aa8da
         */

        private WeixinDataEntity weixinData;

        public String getPaymentData() {
            return paymentData;
        }

        public void setPaymentData(String paymentData) {
            this.paymentData = paymentData;
        }

        public String getPaymentTypeId() {
            return paymentTypeId;
        }

        public void setPaymentTypeId(String paymentTypeId) {
            this.paymentTypeId = paymentTypeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public WeixinDataEntity getWeixinData() {
            return weixinData;
        }

        public void setWeixinData(WeixinDataEntity weixinData) {
            this.weixinData = weixinData;
        }

        public static class WeixinDataEntity implements Parcelable {
            @Override
            public String toString() {
                return "WeixinDataEntity{" +
                        "timestamp='" + timestamp + '\'' +
                        ", sign='" + sign + '\'' +
                        ", retcode=" + retcode +
                        ", partnerid='" + partnerid + '\'' +
                        ", noncestr='" + noncestr + '\'' +
                        ", prepayid='" + prepayid + '\'' +
                        ", packageX='" + packageX + '\'' +
                        ", retmsg='" + retmsg + '\'' +
                        ", appid='" + appid + '\'' +
                        '}';
            }

            private String timestamp;
            private String sign;
            private int retcode;
            private String partnerid;
            private String noncestr;
            private String prepayid;
            @SerializedName("package")
            private String packageX;
            private String retmsg;
            private String appid;

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public int getRetcode() {
                return retcode;
            }

            public void setRetcode(int retcode) {
                this.retcode = retcode;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getRetmsg() {
                return retmsg;
            }

            public void setRetmsg(String retmsg) {
                this.retmsg = retmsg;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.timestamp);
                dest.writeString(this.sign);
                dest.writeInt(this.retcode);
                dest.writeString(this.partnerid);
                dest.writeString(this.noncestr);
                dest.writeString(this.prepayid);
                dest.writeString(this.packageX);
                dest.writeString(this.retmsg);
                dest.writeString(this.appid);
            }

            public WeixinDataEntity() {
            }

            protected WeixinDataEntity(Parcel in) {
                this.timestamp = in.readString();
                this.sign = in.readString();
                this.retcode = in.readInt();
                this.partnerid = in.readString();
                this.noncestr = in.readString();
                this.prepayid = in.readString();
                this.packageX = in.readString();
                this.retmsg = in.readString();
                this.appid = in.readString();
            }

            public static final Parcelable.Creator<WeixinDataEntity> CREATOR = new Parcelable.Creator<WeixinDataEntity>() {
                @Override
                public WeixinDataEntity createFromParcel(Parcel source) {
                    return new WeixinDataEntity(source);
                }

                @Override
                public WeixinDataEntity[] newArray(int size) {
                    return new WeixinDataEntity[size];
                }
            };
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
            dest.writeParcelable(this.weixinData, flags);
        }

        public PayTypesEntity() {
        }

        protected PayTypesEntity(Parcel in) {
            this.paymentData = in.readString();
            this.paymentTypeId = in.readString();
            this.name = in.readString();
            this.weixinData = in.readParcelable(WeixinDataEntity.class.getClassLoader());
        }

        public static final Parcelable.Creator<PayTypesEntity> CREATOR = new Parcelable.Creator<PayTypesEntity>() {
            @Override
            public PayTypesEntity createFromParcel(Parcel source) {
                return new PayTypesEntity(source);
            }

            @Override
            public PayTypesEntity[] newArray(int size) {
                return new PayTypesEntity[size];
            }
        };
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
        @Override
        public PayWayModel createFromParcel(Parcel source) {
            return new PayWayModel(source);
        }

        @Override
        public PayWayModel[] newArray(int size) {
            return new PayWayModel[size];
        }
    };

    public static void getPayWayRequest(String orderCode, OkHttpClientManager.ResultCallback<PayWayModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", BaseApplication.token);
        params.put("orderCode", orderCode);
        params.put("paymentTypeId", "1,2");
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.PAY_WAY_LIST, params, callback);
    }
}
