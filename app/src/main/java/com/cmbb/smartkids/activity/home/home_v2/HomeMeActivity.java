package com.cmbb.smartkids.activity.home.home_v2;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
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
import com.cmbb.smartkids.activity.diary.MyBabyListActivity;
import com.cmbb.smartkids.activity.home.ApplyPopmanActivity;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.message.MessageActivity;
import com.cmbb.smartkids.activity.order.v2.AllOrderListActivity;
import com.cmbb.smartkids.activity.order.v2.ReimburseOrderListActivity;
import com.cmbb.smartkids.activity.order.v2.UnConsumeOrderListActivity;
import com.cmbb.smartkids.activity.order.v2.UnEvaluateOrderListActivity;
import com.cmbb.smartkids.activity.order.v2.UnpayOrderListActivity;
import com.cmbb.smartkids.activity.user.MyCommunityActivity;
import com.cmbb.smartkids.activity.user.MyDraftsActivity;
import com.cmbb.smartkids.activity.user.MyListRedirectActivity;
import com.cmbb.smartkids.activity.user.MyServiceOrderActivity;
import com.cmbb.smartkids.activity.user.MySetActivity;
import com.cmbb.smartkids.activity.user.PerssionListActivity;
import com.cmbb.smartkids.activity.user.StorePointActivity;
import com.cmbb.smartkids.activity.user.model.UserCenterModel;
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
import com.soundcloud.android.crop.Crop;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午4:08
 */
public class HomeMeActivity extends BaseHomeActivity implements View.OnClickListener {
    private static final String TAG = HomeMeActivity.class.getSimpleName();
    private final int PIC_REQUEST_CODE = 1001;
    private SimpleDraweeView ivMyself;
    private SimpleDraweeView ivUserHeader;
    private ImageView ivMessageTag;
    private TextView tvFan, tvNickname, tvIdentity, tvProgress, myUid, myCount;
    private LinearLayout myOrder, myAccept, myGold, myCommunity, myPopman, myBabyDiary, myUID, myDrafts, myCollection, myCare, myPerssion;
    private ProgressBar pb;
    private RatingBar rb;

    private UserCenterModel.DataEntity userInfoEntity;


    @Override
    protected void init(Bundle savedInstanceState) {
        setNoBack();
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
        ivMyself = (SimpleDraweeView) findViewById(R.id.iv_home_myself);
        ivMyself.setOnClickListener(this);
        ivUserHeader = (SimpleDraweeView) findViewById(R.id.iv_home_myself_header);
        ivUserHeader.setOnClickListener(this);
        tvNickname = (TextView) findViewById(R.id.tv_home_myself_nickname);
        tvIdentity = (TextView) findViewById(R.id.tv_home_myself_identity);
        tvFan = (TextView) findViewById(R.id.tv_home_myself_fan);
        tvProgress = (TextView) findViewById(R.id.tv_myself_progress);
        rb = (RatingBar) findViewById(R.id.rb_home_myself_perssion);
        pb = (ProgressBar) findViewById(R.id.pb_home_myself_grow);
        ivMessageTag = (ImageView) findViewById(R.id.iv_message_tag);

        myUid = (TextView) findViewById(R.id.tv_home_uid);
        myCollection = (LinearLayout) findViewById(R.id.tv_home_myself_collect);
        myCollection.setOnClickListener(this);
        myCare = (LinearLayout) findViewById(R.id.tv_home_myself_care);
        myCare.setOnClickListener(this);
        myPerssion = (LinearLayout) findViewById(R.id.tv_home_myself_perssion);
        myPerssion.setOnClickListener(this);
        myOrder = (LinearLayout) findViewById(R.id.ll_home_self_order);
        myUID = (LinearLayout) findViewById(R.id.ll_home_self_uid);
        myUID.setOnClickListener(this);
        myAccept = (LinearLayout) findViewById(R.id.ll_home_self_order_accept);
        myAccept.setOnClickListener(this);
        myGold = (LinearLayout) findViewById(R.id.ll_home_self_gold);
        myCommunity = (LinearLayout) findViewById(R.id.ll_home_self_community);
        myCommunity.setOnClickListener(this);
        myPopman = (LinearLayout) findViewById(R.id.ll_home_self_apply_popman);
        myPopman.setOnClickListener(this);
        myBabyDiary = (LinearLayout) findViewById(R.id.ll_home_self_baby_diary);
        myBabyDiary.setOnClickListener(this);
        myDrafts = (LinearLayout) findViewById(R.id.ll_home_self_drafts);
        myDrafts.setOnClickListener(this);
        myCount = (TextView) findViewById(R.id.tv_home_count);
        findViewById(R.id.tv_order_unpay).setOnClickListener(this);
        findViewById(R.id.tv_order_unuse).setOnClickListener(this);
        findViewById(R.id.tv_order_uncomment).setOnClickListener(this);
        findViewById(R.id.tv_order_reimburse).setOnClickListener(this);
        findViewById(R.id.iv_main_toolbar_left).setOnClickListener(this);
        findViewById(R.id.iv_main_toolbar_right).setOnClickListener(this);
        findViewById(R.id.tv_home_self_gold).setOnClickListener(this);
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
        tvMe.setSelected(true);
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
        FrescoTool.loadImage(ivMyself, userModel.getBackgroundImg(), String.valueOf(TDevice.dip2px(256, this)));

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
        SecurityCodeModel.updateBackgroundImageRequest(path, width, height, BaseApplication.token, new OkHttpClientManager.ResultCallback<SecurityCodeModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
                hideWaitDialog();
            }

            @Override
            public void onResponse(SecurityCodeModel response) {
                hideWaitDialog();
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
                Uri source = Uri.fromFile(new File(tempUrls.get(0)));
                Uri destination = Uri.fromFile(new File(getCacheDir(), "smartkids"));
                Crop.of(source, destination).start(this);
            }
        } else if (resultCode == -1 && requestCode == Crop.REQUEST_CROP) {
            if (Crop.getOutput(data) != null) {
                showWaitDialog();
                final ArrayList<String> tempUrls = new ArrayList<>();
                tempUrls.add(Crop.getOutput(data).getPath());
                ImageUpload.getInstance().uploadImages(this, tempUrls, new UploadListener() {
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
        } else if (resultCode == -1 && requestCode == MY_SET_MODIFY) {
            initData();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public static final int MY_SET_MODIFY = 2001;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            // 设置
            case R.id.iv_main_toolbar_left:
                Intent intentSetting = new Intent(this, MySetActivity.class);
                startActivityForResult(intentSetting, MY_SET_MODIFY);
                break;
            // 信息
            case R.id.iv_main_toolbar_right:
                MessageActivity.newInstance(this);
                break;
            case R.id.iv_home_myself:
                PhotoPickerIntent intent = new PhotoPickerIntent(this);
                intent.setPhotoCount(1);
                startActivityForResult(intent, PIC_REQUEST_CODE);
                break;
            case R.id.tv_home_myself_collect:
                Intent myCollect = new Intent(new Intent(this, MyListRedirectActivity.class));
                myCollect.putExtra("flag", "collect");
                startActivity(myCollect);
                break;
            case R.id.tv_home_myself_care:
                Intent myCare = new Intent(new Intent(this, MyListRedirectActivity.class));
                myCare.putExtra("flag", "care");
                startActivity(myCare);
                break;
            case R.id.tv_home_myself_perssion:
                Intent perssion = new Intent(this, PerssionListActivity.class);
                perssion.putExtra("isPop", userInfoEntity.getIsEredar());
                startActivity(perssion);
                break;
            case R.id.ll_home_self_order_accept:
                if (userInfoEntity.getIsEredar() != 0) {  // 0 普通用户 1 达人
                    startActivity(new Intent(this, MyServiceOrderActivity.class));
                } else {
//                    Intent myService = new Intent(this, ServiceListActivity.class);
//                    myService.putExtra("myCenter", 1);
//                    myService.putExtra("isPopman", 0);
//                    myService.putExtra("userId", userInfoEntity.getUserId());
//                    startActivity(myService);
                }
                break;
            case R.id.ll_home_self_order:
                AllOrderListActivity.newInstance(this);
//                Intent myOrder = new Intent(new Intent(this, MyOrderListActivity.class));
//                startActivity(myOrder);
                break;
            case R.id.ll_home_self_community:
                startActivity(new Intent(this, MyCommunityActivity.class));
                break;
            case R.id.ll_home_self_gold:
                if (userInfoEntity != null)
                    StorePointActivity.IntentStorePointActivity(this, userInfoEntity.getGoldCount());
                break;
            case R.id.ll_home_self_apply_popman:
                startActivityForResult(new Intent(this, ApplyPopmanActivity.class), 2001);
                break;
            case R.id.ll_home_self_baby_diary:
                startActivity(new Intent(this, MyBabyListActivity.class));
                break;
            case R.id.ll_home_self_uid:
                ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                cmb.setText(myUid.getText());
                showShortToast("萌宝UID已经复制到剪切板");
                break;
            case R.id.ll_home_self_drafts:
                MyDraftsActivity.skipFromActivity(this, userInfoEntity.getUserId());
                break;
            case R.id.tv_order_unpay:
                UnpayOrderListActivity.newInstance(this);
                break;
            case R.id.tv_order_unuse:
                UnConsumeOrderListActivity.newInstance(this);
                break;
            case R.id.tv_order_uncomment:
                UnEvaluateOrderListActivity.newInstance(this);
                break;
            case R.id.tv_order_reimburse:
                ReimburseOrderListActivity.newInstance(this);
                break;
            case R.id.tv_home_self_gold:

                break;
        }
    }

    @Override
    protected void netChange() {
        initData();
    }
}
