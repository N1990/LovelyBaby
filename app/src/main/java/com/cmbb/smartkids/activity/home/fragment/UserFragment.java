package com.cmbb.smartkids.activity.home.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alibaba.sdk.android.media.upload.UploadListener;
import com.alibaba.sdk.android.media.upload.UploadTask;
import com.alibaba.sdk.android.media.utils.FailReason;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.ApplyPopmanActivity;
import com.cmbb.smartkids.activity.home.HomeActivity;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.order.MyOrderListActivity;
import com.cmbb.smartkids.activity.serve.ServiceListActivity;
import com.cmbb.smartkids.activity.user.MyCommunityActivity;
import com.cmbb.smartkids.activity.user.MyListRedirectActivity;
import com.cmbb.smartkids.activity.user.MyMessageActivity;
import com.cmbb.smartkids.activity.user.MyServiceOrderActivity;
import com.cmbb.smartkids.activity.user.MySetActivity;
import com.cmbb.smartkids.activity.user.PerssionListActivity;
import com.cmbb.smartkids.activity.user.StorePointActivity;
import com.cmbb.smartkids.activity.user.model.UserCenterModel;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.network.image.ImageUpload;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 10:44
 */
public class UserFragment extends BaseFragment {
    private final String TAG = UserFragment.class.getSimpleName();
    private final int PIC_REQUEST_CODE = 1001;
    private SimpleDraweeView ivMyself;
    private SimpleDraweeView ivUserHeader;
    private TextView tvFan, tvNickname, tvIdentity, tvProgress, myCollection, myCare, myPerssion;
    private LinearLayout myOrder, myAccept, myGold, myCommunity, myPopman;
    private ProgressBar pb;
    private RatingBar rb;
    private FloatingActionButton fab;
    private UserCenterModel.DataEntity userModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_myself, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initActionBar();
        initData();
        addListener();
    }

    private void initView(View view) {
        ivMyself = (SimpleDraweeView) view.findViewById(R.id.iv_home_myself);
        ivUserHeader = (SimpleDraweeView) view.findViewById(R.id.iv_home_myself_header);
        tvNickname = (TextView) view.findViewById(R.id.tv_home_myself_nickname);
        tvIdentity = (TextView) view.findViewById(R.id.tv_home_myself_identity);
        tvFan = (TextView) view.findViewById(R.id.tv_home_myself_fan);
        tvProgress = (TextView) view.findViewById(R.id.tv_myself_progress);
        rb = (RatingBar) view.findViewById(R.id.rb_home_myself_perssion);
        pb = (ProgressBar) view.findViewById(R.id.pb_home_myself_grow);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        myCollection = (TextView) view.findViewById(R.id.tv_home_myself_collect);
        myCare = (TextView) view.findViewById(R.id.tv_home_myself_care);
        myPerssion = (TextView) view.findViewById(R.id.tv_home_myself_perssion);
        myOrder = (LinearLayout) view.findViewById(R.id.ll_home_self_order);
        myAccept = (LinearLayout) view.findViewById(R.id.ll_home_self_order_accept);
        myGold = (LinearLayout) view.findViewById(R.id.ll_home_self_gold);
        myCommunity = (LinearLayout) view.findViewById(R.id.ll_home_self_community);
        myPopman = (LinearLayout) view.findViewById(R.id.ll_home_self_apply_popman);
    }

    private ActionBar actionbar;
    private TextView tvTitle, tvRight;
    private ImageView ivRight, ivLeft;

    /**
     * 初始化actionbar
     */
    private void initActionBar() {
        try {
            Toolbar v = (Toolbar) getView().findViewById(R.id.tl_main_actionbar);
            tvTitle = (TextView) v.findViewById(R.id.tv_main_toolbar);
            ivLeft = (ImageView) v.findViewById(R.id.iv_main_toolbar_left);
            ivRight = (ImageView) v.findViewById(R.id.iv_main_toolbar_right);
            tvRight = (TextView) v.findViewById(R.id.tv_main_toolbar_right);
            ((AppCompatActivity) getActivity()).setSupportActionBar(v);
            actionbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(false);
            actionbar.setDisplayShowTitleEnabled(false);
        } catch (Exception e) {
            Log.i(TAG, "actionbar is null");
        }
    }

    private void initData() {
        handleRequest();
    }

    private void addListener() {
        fab.setOnClickListener(this);
        ivUserHeader.setOnClickListener(this);
        ivMyself.setOnClickListener(this);
        myCollection.setOnClickListener(this);
        myCare.setOnClickListener(this);
        myPerssion.setOnClickListener(this);
        myOrder.setOnClickListener(this);
        myAccept.setOnClickListener(this);
        myGold.setOnClickListener(this);
        myCommunity.setOnClickListener(this);
        myPopman.setOnClickListener(this);
        ivLeft.setOnClickListener(this);
        ivRight.setOnClickListener(this);
    }

    public static final int MY_SET_MODIFY = 2001;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 设置
            case R.id.iv_main_toolbar_left:
                Intent intentSetting = new Intent(getActivity(), MySetActivity.class);
                startActivityForResult(intentSetting, MY_SET_MODIFY);
                break;
            // 信息
            case R.id.iv_main_toolbar_right:
//                Intent message = new Intent(getActivity(), MyListRedirectActivity.class);
//                message.putExtra("flag", "message");
//                startActivity(message);
                startActivity(new Intent(getActivity(), MyMessageActivity.class));
//                MessageActivity.newInstance(this);
                break;
            case R.id.iv_home_myself:
                PhotoPickerIntent intent = new PhotoPickerIntent(getActivity());
                intent.setPhotoCount(1);
                startActivityForResult(intent, PIC_REQUEST_CODE);
                break;
            case R.id.fab:
                showShortToast("签到功能尚未开通，敬请期待~");
                break;
            case R.id.tv_home_myself_collect:
                Intent myCollect = new Intent(new Intent(getActivity(), MyListRedirectActivity.class));
                myCollect.putExtra("flag", "collect");
                getActivity().startActivity(myCollect);
                break;
            case R.id.tv_home_myself_care:
                Intent myCare = new Intent(new Intent(getActivity(), MyListRedirectActivity.class));
                myCare.putExtra("flag", "care");
                getActivity().startActivity(myCare);
                break;
            case R.id.tv_home_myself_perssion:
                Intent perssion = new Intent(getActivity(), PerssionListActivity.class);
                perssion.putExtra("isPop", userModel.getIsEredar());
                startActivity(perssion);
                break;
            case R.id.ll_home_self_order_accept:
                if (userModel.getIsEredar() != 0) {  // 0 普通用户 1 达人
                    startActivity(new Intent(getActivity(), MyServiceOrderActivity.class));
                } else {
                    Intent myService = new Intent(getActivity(), ServiceListActivity.class);
                    myService.putExtra("myCenter", 1);
                    myService.putExtra("isPopman", 0);
                    myService.putExtra("userId", userModel.getUserId());
                    startActivity(myService);
                }
                break;
            case R.id.ll_home_self_order:
                Intent myOrder = new Intent(new Intent(getActivity(), MyOrderListActivity.class));
                getActivity().startActivity(myOrder);
                break;
            case R.id.ll_home_self_community:
                startActivity(new Intent(getActivity(), MyCommunityActivity.class));
                break;
            case R.id.ll_home_self_gold:
                if (userModel != null)
                    StorePointActivity.IntentStorePointActivity(getActivity(), userModel.getGoldCount());
                break;
            case R.id.ll_home_self_apply_popman:
                startActivityForResult(new Intent(getActivity(), ApplyPopmanActivity.class), HomeActivity.MY_SET_MODIFY);
//                showShortToast("申请达人功能暂未开放");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == PIC_REQUEST_CODE) {
            if (data != null) {
                final ArrayList<String> tempUrls = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                showWaitsDialog();
                ImageUpload.getInstance().uploadImages(getActivity(), tempUrls, new UploadListener() {
                    @Override
                    public void onUploading(UploadTask uploadTask) {

                    }

                    @Override
                    public void onUploadFailed(UploadTask uploadTask, FailReason failReason) {
                        hideWaitDialog();
                        showShortToast(failReason.getMessage());
                    }

                    @Override
                    public void onUploadComplete(UploadTask uploadTask) {
                        try {
                            JSONObject object = new JSONObject(uploadTask.getResult().message);
                            String url = object.optString("url");
                            String width = object.optString("returnBody").split("_")[0];
                            String height = object.optString("returnBody").split("_")[1];
                            Log.i(TAG, "uploadTask = " + uploadTask.getResult().message);
                            Log.e(TAG, "url : " + url + ", width : " + width + ", height : " + height);
                            Log.i("uploadTask", "uploadTask = " + tempUrls.size());
                            handleUpdateRequest(url, width, height);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onUploadCancelled(UploadTask uploadTask) {

                    }
                });
            }
        } else if (resultCode == -1 && requestCode == HomeActivity.MY_SET_MODIFY) {
            initData();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void reflushView(UserCenterModel.DataEntity userModel) {
        //Fresco
        FrescoTool.loadImage(ivUserHeader, userModel.getUserSmallImg());
        FrescoTool.loadImage(ivMyself, userModel.getBackgroundImg(), TDevice.dip2px(180, getActivity()) + "");

        if (!TextUtils.isEmpty(userModel.getUserNike())) {
            tvNickname.setVisibility(View.VISIBLE);
            tvNickname.setText(userModel.getUserNike());
            tvTitle.setText("我的");
        }
        tvIdentity.setText(userModel.getUserRole().get(0).getEredarName());
        rb.setRating(userModel.getUserLevel());
        tvFan.setText("Fans(" + userModel.getFans() + ")");
        if (userModel.getIsEredar() == 0) {
            getView().findViewById(R.id.ll_home_self_order_accept).setVisibility(View.GONE);
            getView().findViewById(R.id.v_home_self_order_accept).setVisibility(View.GONE);
            rb.setVisibility(View.GONE);
            getView().findViewById(R.id.v_home_self_apply_popman).setVisibility(View.VISIBLE);
            myPopman.setVisibility(View.VISIBLE);
        } else {
            getView().findViewById(R.id.v_home_self_apply_popman).setVisibility(View.GONE);

            myPopman.setVisibility(View.GONE);
        }
        // 模拟进度条动画
        pb.setMax(20000);
        int grownth = userModel.getGrowthCount();
        tvProgress.setText("当前成长值:" + grownth);
        ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", grownth);
        animation.setDuration(500); // 0.5 second
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }

    private void handleRequest() {
        showWaitsDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("userAttentionId", SPCache.getString(Constants.USER_ID, "-1"));
        params.put("myCenter", "1");
        NetRequest.postRequest(Constants.ServiceInfo.USER_INFO_REQUEST, BaseApplication.token, params, UserCenterModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                UserCenterModel userCenter = (UserCenterModel) object;
                if (userCenter != null && userCenter.getData() != null) {
                    userModel = userCenter.getData();
                    reflushView(userModel);
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

    /**
     * 更改背景图片
     *
     * @param path
     * @param width
     * @param height
     */
    private void handleUpdateRequest(final String path, final String width, final String height) {
        Log.e("path", "path = " + path);
        HashMap<String, String> params = new HashMap<>();
        params.put("imgPath", path);
        params.put("imgWidth", width);
        params.put("imgHeight", height);
        NetRequest.postRequest(Constants.ServiceInfo.UPDATA_IMG_FOR_USER, BaseApplication.token, params, SecurityCodeModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                SecurityCodeModel data = (SecurityCodeModel) object;
                FrescoTool.loadImage(ivMyself, path, width, height);
                showShortToast(data.getMsg());
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

}
