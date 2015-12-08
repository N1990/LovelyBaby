package com.cmbb.smartkids.activity.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/11 上午9:57
 */
public class RecommonedEredarRootModel implements Parcelable {

    /**
     * status : 1
     * data : [{"userId":3,"recommoned":null,"userNike":"N.Sun","userSex":null,"userBigImg":null,"userBigWidth":null,"userBigHeight":null,"userSmallImg":null,"userSmallWidth":null,"userSmallHeight":null,"loginAccountType":0,"loginTime":"2015-09-10 17:20:14","loginAccount":"13818155072","token":null,"isShutup":0,"shutupTime":null,"isBanned":0,"userAddress":null,"userPhone":"13818155072","userPhoneVersion":null,"province":310000,"district":310115,"city":null,"userLevel":1,"userPresentation":null,"goldCount":null,"integralCount":null,"attentionCount":null,"userRole":null}]
     * msg : 数据加载成功
     */

    private int status;
    private String msg;
    private ArrayList<RecommonedEredarModel> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<RecommonedEredarModel> getData() {
        return data;
    }

    public void setData(ArrayList<RecommonedEredarModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RecommonedEredarRootModel{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.msg);
        dest.writeTypedList(data);
    }

    public RecommonedEredarRootModel() {
    }

    protected RecommonedEredarRootModel(Parcel in) {
        this.status = in.readInt();
        this.msg = in.readString();
        this.data = in.createTypedArrayList(RecommonedEredarModel.CREATOR);
    }

    public static final Parcelable.Creator<RecommonedEredarRootModel> CREATOR = new Parcelable.Creator<RecommonedEredarRootModel>() {
        public RecommonedEredarRootModel createFromParcel(Parcel source) {
            return new RecommonedEredarRootModel(source);
        }

        public RecommonedEredarRootModel[] newArray(int size) {
            return new RecommonedEredarRootModel[size];
        }
    };
}
