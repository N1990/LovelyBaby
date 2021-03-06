package com.cmbb.smartkids.activity.login.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/10 下午5:18
 */
public class UserAssistModel implements Parcelable {


    /**
     * status : 1
     * data : {"token":"d860ea1fa2b243228eb06a54b8961e04/33ad7e90c3664443b9ae0e721551e05f","userId":8,"userRole":[{"id":3,"properties":{},"userBasicInfo":{"id":8,"properties":{},"userNike":"niesen918","userSex":null,"userBigImg":null,"userBigWidth":null,"userBigHeight":null,"userSmallImg":null,"userSmallWidth":null,"userSmallHeight":null,"loginAccountType":0,"loginTime":"2015-09-10 09:42:21","loginAccount":"15201921714","loginPassword":"e10adc3949ba59abbe56e057f20f883e","loginAccessToken":null,"loginToken":"d860ea1fa2b243228eb06a54b8961e04","isShutup":0,"shutupTime":null,"isBanned":0,"userAddress":null,"userPhone":"15201921714","userPhoneVersion":null,"province":320000,"district":320102,"userLevel":1,"userPresentation":null,"isRecommoned":null,"createDate":"2015-09-10 09:42:21","createUserId":null,"updateDate":null,"updateUserId":null,"city":null},"eredarCode":0,"eredarName":"萌宝用户","createDate":"2015-09-10 09:42:21","createUserId":null,"updateDate":null,"updateUserId":null}]}
     * msg : 登录成功
     */

    private int status;
    private UserRootModel data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserRootModel getData() {
        return data;
    }

    public void setData(UserRootModel data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public UserAssistModel() {
    }

    protected UserAssistModel(Parcel in) {
        this.status = in.readInt();
        this.data = in.readParcelable(UserRootModel.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<UserAssistModel> CREATOR = new Parcelable.Creator<UserAssistModel>() {
        public UserAssistModel createFromParcel(Parcel source) {
            return new UserAssistModel(source);
        }

        public UserAssistModel[] newArray(int size) {
            return new UserAssistModel[size];
        }
    };

    @Override
    public String toString() {
        return "UserAssistModel{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
