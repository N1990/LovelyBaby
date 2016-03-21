package com.cmbb.smartkids.activity.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/11 上午9:57
 */
public class RankEredarRootModel implements Parcelable {

    /**
     * status : 1
     * data : [{"userId":3,"recommoned":null,"userNike":"N.Sun","userSex":null,"userBigImg":null,"userBigWidth":null,"userBigHeight":null,"userSmallImg":null,"userSmallWidth":null,"userSmallHeight":null,"loginAccountType":0,"loginTime":"2015-09-10 17:20:14","loginAccount":"13818155072","token":null,"isShutup":0,"shutupTime":null,"isBanned":0,"userAddress":null,"userPhone":"13818155072","userPhoneVersion":null,"province":310000,"district":310115,"city":null,"userLevel":1,"userPresentation":null,"goldCount":null,"integralCount":null,"attentionCount":null,"userRole":null}]
     * msg : 数据加载成功
     */

    private int status;
    private String msg;
    private ArrayList<RankEredarModel> data;

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

    public ArrayList<RankEredarModel> getData() {
        return data;
    }

    public void setData(ArrayList<RankEredarModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RankEredarRootModel{" +
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

    public RankEredarRootModel() {
    }

    protected RankEredarRootModel(Parcel in) {
        this.status = in.readInt();
        this.msg = in.readString();
        this.data = in.createTypedArrayList(RankEredarModel.CREATOR);
    }

    public static final Parcelable.Creator<RankEredarRootModel> CREATOR = new Parcelable.Creator<RankEredarRootModel>() {
        public RankEredarRootModel createFromParcel(Parcel source) {
            return new RankEredarRootModel(source);
        }

        public RankEredarRootModel[] newArray(int size) {
            return new RankEredarRootModel[size];
        }
    };


    /**
     * 获取推荐达人
     *
     * @param callback
     */
    public static void getRankEredarRequest(int pageNo, int numberOfPerPage,OkHttpClientManager.ResultCallback<RankEredarRootModel> callback) {
        HashMap<String, String> bodyEredar = new HashMap<>();
        bodyEredar.put("isRecommoned", "1");
       /* bodyEredar.put("numberOfPerPage", String.valueOf(numberOfPerPage));
        bodyEredar.put("pageNo", String.valueOf(pageNo));*/
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.HOEM_MAIN_POPMAN_REQUEST, bodyEredar, callback);
    }
}
