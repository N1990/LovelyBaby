package com.cmbb.smartkids.activity.home.home_v2;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
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
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.user.MySetActivity;
import com.cmbb.smartkids.activity.user.model.UserCenterModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.network.image.ImageUpload;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午4:08
 */
public class HomeMeActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = HomeMeActivity.class.getSimpleName();
    // Bottom
    private TextView tvHome;
    private TextView tvService;
    private TextView tvTopic;
    private TextView tvMe;
    private TextView tvMore;


    private final int PIC_REQUEST_CODE = 1001;
    private SimpleDraweeView ivMyself;
    private SimpleDraweeView ivUserHeader;
    private ImageView ivMessageTag;

    private TextView tvFan, tvNickname, tvIdentity, tvProgress, myUid, myCount;
    private LinearLayout myOrder, myAccept, myGold, myCommunity, myPopman, myBabyDiary, myUID, myDrafts, myCollection, myCare, myPerssion;
    private ProgressBar pb;
    private RatingBar rb;

    private UserCenterModel.DataEntity userInfoEntity;

    private ProgressDialog progressDialog;


    @Override
    protected void init(Bundle savedInstanceState) {
        setNoBack();
        initBottom();
        initView();
        initData();
    }

    private void initData() {
        UserCenterModel.getUserInfoRequest(SPCache.getString(Constants.USER_ID, "-1"), 1, BaseApplication.token, new OkHttpClientManager.ResultCallback<UserCenterModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(UserCenterModel response) {
                if (response != null) {
                    userInfoEntity = response.getData();
                    reflushView(userInfoEntity);
                }
            }
        });
    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在处理中...");
        ivMyself = (SimpleDraweeView) findViewById(R.id.iv_home_myself);
        ivUserHeader = (SimpleDraweeView) findViewById(R.id.iv_home_myself_header);
        tvNickname = (TextView) findViewById(R.id.tv_home_myself_nickname);
        tvIdentity = (TextView) findViewById(R.id.tv_home_myself_identity);
        tvFan = (TextView) findViewById(R.id.tv_home_myself_fan);
        tvProgress = (TextView) findViewById(R.id.tv_myself_progress);
        rb = (RatingBar) findViewById(R.id.rb_home_myself_perssion);
        pb = (ProgressBar) findViewById(R.id.pb_home_myself_grow);
        ivMessageTag = (ImageView) findViewById(R.id.iv_message_tag);

        myUid = (TextView) findViewById(R.id.tv_home_uid);
        myCollection = (LinearLayout) findViewById(R.id.tv_home_myself_collect);
        myCare = (LinearLayout) findViewById(R.id.tv_home_myself_care);
        myPerssion = (LinearLayout) findViewById(R.id.tv_home_myself_perssion);
        myOrder = (LinearLayout) findViewById(R.id.ll_home_self_order);
        myUID = (LinearLayout) findViewById(R.id.ll_home_self_uid);
        myAccept = (LinearLayout) findViewById(R.id.ll_home_self_order_accept);
        myGold = (LinearLayout) findViewById(R.id.ll_home_self_gold);
        myCommunity = (LinearLayout) findViewById(R.id.ll_home_self_community);
        myPopman = (LinearLayout) findViewById(R.id.ll_home_self_apply_popman);
        myBabyDiary = (LinearLayout) findViewById(R.id.ll_home_self_baby_diary);
        myDrafts = (LinearLayout) findViewById(R.id.ll_home_self_drafts);
        myCount = (TextView) findViewById(R.id.tv_home_count);
    }

    private void initBottom() {
        tvHome = (TextView) findViewById(R.id.tv_home);
        tvService = (TextView) findViewById(R.id.tv_service);
        tvTopic = (TextView) findViewById(R.id.tv_topic);
        tvMe = (TextView) findViewById(R.id.tv_me);
        tvMe.setSelected(true);
        tvMore = (TextView) findViewById(R.id.tv_more);
        tvHome.setOnClickListener(this);
        tvService.setOnClickListener(this);
        tvTopic.setOnClickListener(this);
        tvMe.setOnClickListener(this);
        tvMore.setOnClickListener(this);
        findViewById(R.id.iv_main_toolbar_left).setOnClickListener(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_me_v2;
    }

    // 收到消息 现实消息提醒
    BroadcastReceiver messageReceiveTagReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ivMessageTag.setVisibility(View.VISIBLE);
        }
    };

    // 关系消息 影藏消息提醒
    BroadcastReceiver messageCancelTagReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ivMessageTag.setVisibility(View.GONE);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiveTagReceiver, new IntentFilter(Constants.INTENT_ACTION_MESSAGE_RECEIVE));
        LocalBroadcastManager.getInstance(this).registerReceiver(messageCancelTagReceiver, new IntentFilter(Constants.INTENT_ACTION_MESSAGE_CANCEL));
    }

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiveTagReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageCancelTagReceiver);
        super.onDestroy();
    }


    public static void newIntent(Context context) {
        Intent intent = new Intent(context, HomeMeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }


    private void reflushView(UserCenterModel.DataEntity userModel) {
        //Fresco
        FrescoTool.loadImage(ivUserHeader, userModel.getUserSmallImg());
        FrescoTool.loadImage(ivMyself, userModel.getBackgroundImg(), String.valueOf(TDevice.dip2px(180, this)));

        if (!TextUtils.isEmpty(userModel.getUserNike())) {
            tvNickname.setVisibility(View.VISIBLE);
            tvNickname.setText(userModel.getUserNike());
        }
        tvIdentity.setText(userModel.getUserRole().get(0).getEredarName());
        rb.setRating(userModel.getUserLevel());
        tvFan.setText("Fans(" + userModel.getFans() + ")");
        myUid.setText(userModel.getUid() + "");
        myCount.setText(userModel.getGoldCount() + "");
        if (userModel.getIsEredar() == 0) {
            findViewById(R.id.ll_home_self_order_accept).setVisibility(View.GONE);
            findViewById(R.id.v_home_self_order_accept).setVisibility(View.GONE);
            rb.setVisibility(View.GONE);
            findViewById(R.id.v_home_self_apply_popman).setVisibility(View.VISIBLE);
            myPopman.setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.v_home_self_apply_popman).setVisibility(View.GONE);
            myPopman.setVisibility(View.GONE);
        }
        // 模拟进度条动画
        pb.setMax(20000);
        int growth = userModel.getGrowthCount();
        tvProgress.setText("当前成长值:" + growth);
        ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", growth);
        animation.setDuration(500); // 0.5 second
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }

    /**
     * 更改背景图片
     *
     * @param path   String
     * @param width  String
     * @param height String
     */
    private void handleUpdateRequest(final String path, final String width, final String height) {
        HashMap<String, String> params = new HashMap<>();
        params.put("imgPath", path);
        params.put("imgWidth", width);
        params.put("imgHeight", height);

        SecurityCodeModel.updateBackgroundImageRequest(path, width, height, new OkHttpClientManager.ResultCallback<SecurityCodeModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(SecurityCodeModel response) {
                if (response != null) {
                    FrescoTool.loadImage(ivMyself, path, width, height);
                    showShortToast(response.getMsg());
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == PIC_REQUEST_CODE) {
            if (data != null) {
                final ArrayList<String> tempUrls = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                progressDialog.show();
                ImageUpload.getInstance().uploadImages(this, tempUrls, new UploadListener() {
                    @Override
                    public void onUploading(UploadTask uploadTask) {

                    }

                    @Override
                    public void onUploadFailed(UploadTask uploadTask, FailReason failReason) {
                        progressDialog.dismiss();
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
        } else if (resultCode == -1 && requestCode == MY_SET_MODIFY) {
            initData();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public static final int MY_SET_MODIFY = 2001;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home:
                HomeActivity.newIntent(this);
                break;
            case R.id.tv_service:
                HomeServiceActivity.newIntent(this);
                break;
            case R.id.tv_topic:
                HomeTopicActivity.newIntent(this);
                break;
            case R.id.tv_me:
                HomeMeActivity.newIntent(this);
                break;
            case R.id.tv_more:
                HomeMoreActivity.newIntent(this);
                break;
            // 设置
            case R.id.iv_main_toolbar_left:
                Intent intentSetting = new Intent(this, MySetActivity.class);
                startActivityForResult(intentSetting, MY_SET_MODIFY);
                break;
            // 信息
            case R.id.iv_main_toolbar_right:
                /*MessageActivity.newInstance(this);*/
                break;
            case R.id.iv_home_myself:
                PhotoPickerIntent intent = new PhotoPickerIntent(this);
                intent.setPhotoCount(1);
                startActivityForResult(intent, PIC_REQUEST_CODE);
                break;
            case R.id.tv_home_myself_collect:
                /*Intent myCollect = new Intent(new Intent(getActivity(), MyListRedirectActivity.class));
                myCollect.putExtra("flag", "collect");
                getActivity().startActivity(myCollect);*/
                break;
            case R.id.tv_home_myself_care:
               /* Intent myCare = new Intent(new Intent(getActivity(), MyListRedirectActivity.class));
                myCare.putExtra("flag", "care");
                getActivity().startActivity(myCare);*/
                break;
            case R.id.tv_home_myself_perssion:
                /*Intent perssion = new Intent(getActivity(), PerssionListActivity.class);
                perssion.putExtra("isPop", userModel.getIsEredar());
                startActivity(perssion);*/
                break;
            case R.id.ll_home_self_order_accept:
                /*if (userModel.getIsEredar() != 0) {  // 0 普通用户 1 达人
                    startActivity(new Intent(getActivity(), MyServiceOrderActivity.class));
                } else {
                    Intent myService = new Intent(getActivity(), ServiceListActivity.class);
                    myService.putExtra("myCenter", 1);
                    myService.putExtra("isPopman", 0);
                    myService.putExtra("userId", userModel.getUserId());
                    startActivity(myService);
                }*/
                break;
            case R.id.ll_home_self_order:
                /*Intent myOrder = new Intent(new Intent(getActivity(), MyOrderListActivity.class));
                getActivity().startActivity(myOrder);*/
                break;
            case R.id.ll_home_self_community:
//                startActivity(new Intent(getActivity(), MyCommunityActivity.class));
                break;
            case R.id.ll_home_self_gold:
                /*if (userModel != null)
                    StorePointActivity.IntentStorePointActivity(getActivity(), userModel.getGoldCount());*/
                break;
            case R.id.ll_home_self_apply_popman:
//                startActivityForResult(new Intent(getActivity(), ApplyPopmanActivity.class), HomeActivity.MY_SET_MODIFY);
                break;
            case R.id.ll_home_self_baby_diary:
//                startActivity(new Intent(getActivity(), MyBabyListActivity.class));
                break;
            case R.id.ll_home_self_uid:
                ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                cmb.setText(myUid.getText());
                showShortToast("萌宝UID已经复制到剪切板");
                break;
            case R.id.ll_home_self_drafts:
//                MyDraftsActivity.skipFromFragment(UserFragment.this, userModel.getUserId());
                break;
        }
    }
}
