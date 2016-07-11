package com.cmbb.smartkids.activity.home.home;

import android.animation.ObjectAnimator;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.media.upload.UploadListener;
import com.alibaba.sdk.android.media.upload.UploadTask;
import com.alibaba.sdk.android.media.utils.FailReason;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.BabyListActivity;
import com.cmbb.smartkids.activity.home.ApplyPopmanActivity;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.message.MessageActivity;
import com.cmbb.smartkids.activity.order.view.AllOrderListActivity;
import com.cmbb.smartkids.activity.order.view.ReimburseOrderListActivity;
import com.cmbb.smartkids.activity.order.view.UnConsumeOrderListActivity;
import com.cmbb.smartkids.activity.order.view.UnEvaluateOrderListActivity;
import com.cmbb.smartkids.activity.order.view.UnpayOrderListActivity;
import com.cmbb.smartkids.activity.user.GoldGrowthListActivity;
import com.cmbb.smartkids.activity.user.MyCommunityActivity;
import com.cmbb.smartkids.activity.user.MyDraftsActivity;
import com.cmbb.smartkids.activity.user.MyListRedirectActivity;
import com.cmbb.smartkids.activity.user.MyServiceOrderActivity;
import com.cmbb.smartkids.activity.user.SettingActivity;
import com.cmbb.smartkids.activity.user.StorePointActivity;
import com.cmbb.smartkids.activity.user.model.UserCenterModel;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.db.DBContent;
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
public class HomeMeActivity extends BaseHomeActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = HomeMeActivity.class.getSimpleName();
    private final int PIC_REQUEST_CODE = 1001;
    private SimpleDraweeView ivMyself;
    private SimpleDraweeView ivUserHeader;
    private TextView tvFan, tvNickname, tvIdentity, tvProgress, tvProgressNext, myUid, myCount;
    private LinearLayout myOrder, myAccept, myGold, myCommunity, myPopman, myBabyDiary, myUID, myDrafts, myCollection, myCare, myPerssion;
    private RelativeLayout rlStars;
    private ProgressBar pb;
    private RatingBar rb;

    private int isEredar;
    private int goldCount;
    private int userId;

    @Override
    protected void init(Bundle savedInstanceState) {
        setNoBack();
        initView();
    }

    private void initView() {
        ivMyself = (SimpleDraweeView) findViewById(R.id.iv_home_myself);
        ivMyself.setOnClickListener(this);
        ivUserHeader = (SimpleDraweeView) findViewById(R.id.iv_home_myself_header);
        ivUserHeader.setOnClickListener(this);
        tvNickname = (TextView) findViewById(R.id.tv_home_myself_nickname);
        rlStars = (RelativeLayout) findViewById(R.id.rl_stars);
        tvIdentity = (TextView) findViewById(R.id.tv_home_myself_identity);
        tvFan = (TextView) findViewById(R.id.tv_home_myself_fan);
        tvProgress = (TextView) findViewById(R.id.tv_myself_progress);
        tvProgressNext = (TextView) findViewById(R.id.tv_myself_progress_next);
        rb = (RatingBar) findViewById(R.id.rb_home_myself_perssion);
        pb = (ProgressBar) findViewById(R.id.pb_home_myself_grow);
        myUid = (TextView) findViewById(R.id.tv_home_uid);
        myCollection = (LinearLayout) findViewById(R.id.tv_home_myself_collect);
        myCollection.setOnClickListener(this);
        myCare = (LinearLayout) findViewById(R.id.tv_home_myself_care);
        myCare.setOnClickListener(this);
        myPerssion = (LinearLayout) findViewById(R.id.tv_home_myself_perssion);
        myPerssion.setOnClickListener(this);
        myOrder = (LinearLayout) findViewById(R.id.ll_home_self_order);
        myOrder.setOnClickListener(this);
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
        findViewById(R.id.rl_stars).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_me_v2;
    }

    @Override
    public void onResume() {
        super.onResume();
        tvMe.setSelected(true);
        hideWaitDialog();
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, HomeMeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
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
            public void onError(Request request, Exception e, String msg) {
                hideWaitDialog();
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
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
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            // 设置
            case R.id.iv_main_toolbar_left:
                SettingActivity.newIntent(this);
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
                Intent perssion = new Intent(this, MyListRedirectActivity.class);
                perssion.putExtra("flag", "evaluate");
                perssion.putExtra("isEredar", isEredar);
                startActivity(perssion);
                break;
            case R.id.ll_home_self_order_accept:
                if (isEredar != 0) {  // 0 普通用户 1 达人
                    startActivity(new Intent(this, MyServiceOrderActivity.class));
                }
                break;
            case R.id.ll_home_self_order:
                AllOrderListActivity.newInstance(this);
                break;
            case R.id.ll_home_self_community:
                startActivity(new Intent(this, MyCommunityActivity.class));
                break;
            case R.id.ll_home_self_gold:
                StorePointActivity.IntentStorePointActivity(this, goldCount);
                break;
            case R.id.ll_home_self_apply_popman:
                startActivityForResult(new Intent(this, ApplyPopmanActivity.class), 2001);
                break;
            case R.id.ll_home_self_baby_diary:
                startActivity(new Intent(this, BabyListActivity.class));
                break;
            case R.id.ll_home_self_uid:
                ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                cmb.setText(myUid.getText());
                showShortToast("萌宝UID已经复制到剪切板");
                break;
            case R.id.ll_home_self_drafts:
                MyDraftsActivity.skipFromActivity(this, userId);
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
            case R.id.rl_stars:
                GoldGrowthListActivity.newIntent(this);
                break;
        }
    }

    @Override
    protected void netChange() {
        getSupportLoaderManager().initLoader(0, null, this);
        showWaitDialog();
        UserCenterModel.getUserInfoRequest(SPCache.getString(Constants.USER_ID, "-1"), 1, BaseApplication.token, new OkHttpClientManager.ResultCallback<UserCenterModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                hideWaitDialog();
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(UserCenterModel user) {
                if (user != null) {
                    //数据库
                    //写入数据库
                    ContentValues valus = new ContentValues();
                    valus.put(DBContent.DBUser.USER_ID, user.getData().getUserId());
                    valus.put(DBContent.DBUser.USER_UID, user.getData().getUid());
                    valus.put(DBContent.DBUser.USER_RECOMMONED, user.getData().getRecommoned());
                    valus.put(DBContent.DBUser.USER_NIKE, user.getData().getUserNike());
                    valus.put(DBContent.DBUser.USER_SEX, user.getData().getUserSex());
                    valus.put(DBContent.DBUser.USER_BIRTHDAY, user.getData().getUserBirthday());
                    valus.put(DBContent.DBUser.USER_BACKGROUNDIMG, user.getData().getBackgroundImg());
                    valus.put(DBContent.DBUser.USER_BIGIMG, user.getData().getUserBigImg());
                    valus.put(DBContent.DBUser.USER_BIGWIDTH, user.getData().getUserBigWidth());
                    valus.put(DBContent.DBUser.USER_BIGHEIGHT, user.getData().getUserBigHeight());
                    valus.put(DBContent.DBUser.USER_SMALLIMG, user.getData().getUserSmallImg());
                    valus.put(DBContent.DBUser.USER_SMALLWIDTH, user.getData().getUserSmallWidth());
                    valus.put(DBContent.DBUser.USER_SMALLHEIGHT, user.getData().getUserSmallHeight());
                    valus.put(DBContent.DBUser.USER_LOGINACCOUNTTYPE, user.getData().getLoginAccountType());
                    valus.put(DBContent.DBUser.USER_LOGINTIME, user.getData().getLoginTime());
                    valus.put(DBContent.DBUser.USER_LOGINACCOUNT, user.getData().getLoginAccount());
                    valus.put(DBContent.DBUser.USER_TOKEN, user.getData().getToken());
                    valus.put(DBContent.DBUser.USER_ISSHUTUP, user.getData().getIsShutup());
                    valus.put(DBContent.DBUser.USER_SHUTUPTIME, user.getData().getShutupTime());
                    valus.put(DBContent.DBUser.USER_ISBANNED, user.getData().getIsBanned());
                    valus.put(DBContent.DBUser.USER_ADDRESS, user.getData().getUserAddress());
                    valus.put(DBContent.DBUser.USER_PHONE, user.getData().getUserPhone());
                    valus.put(DBContent.DBUser.USER_PHONEVERSION, user.getData().getUserPhoneVersion());
                    valus.put(DBContent.DBUser.USER_PROVINCE, user.getData().getProvince());
                    valus.put(DBContent.DBUser.USER_PROVINCETEXT, user.getData().getProvinceText());
                    valus.put(DBContent.DBUser.USER_DISTRICT, user.getData().getDistrict());
                    valus.put(DBContent.DBUser.USER_DISTRICTTEXT, user.getData().getDistrictText());
                    valus.put(DBContent.DBUser.USER_CITY, user.getData().getCity());
                    valus.put(DBContent.DBUser.USER_CITYTEXT, user.getData().getCityText());
                    valus.put(DBContent.DBUser.USER_LEVEL, user.getData().getUserLevel());
                    valus.put(DBContent.DBUser.USER_PRESENTATION, user.getData().getUserPresentation());
                    valus.put(DBContent.DBUser.USER_BACKIMGWIDTH, user.getData().getBackImgWidth());
                    valus.put(DBContent.DBUser.USER_BACKIMGHEIGHT, user.getData().getBackImgHeight());
                    valus.put(DBContent.DBUser.USER_GOLDCOUNT, user.getData().getGoldCount());
                    valus.put(DBContent.DBUser.USER_GROWTHCOUNT, user.getData().getGrowthCount());
                    valus.put(DBContent.DBUser.USER_FANS, user.getData().getFans());
                    valus.put(DBContent.DBUser.USER_ATTENTIONCOUNT, user.getData().getAttentionCount());
                    valus.put(DBContent.DBUser.USER_ISSIGN, user.getData().getIsSign());
                    valus.put(DBContent.DBUser.USER_ISATTENTION, user.getData().getIsAttention());
                    valus.put(DBContent.DBUser.USER_ISEREDAR, user.getData().getIsEredar());
                    valus.put(DBContent.DBUser.USER_ISLOGINUSER, user.getData().getIsLoginUser());
                    if (user.getData().getUserRole() != null && user.getData().getUserRole().size() > 0) {
                        valus.put(DBContent.DBUser.USER_EREDARCODE, user.getData().getUserRole().get(0).getEredarCode());
                        valus.put(DBContent.DBUser.USER_EREDARNAME, user.getData().getUserRole().get(0).getEredarName());
                    }
                    getContentResolver().delete(DBContent.DBUser.CONTENT_URI, " 1 = 1", null);
                    getContentResolver().insert(DBContent.DBUser.CONTENT_URI, valus);
                }
                hideWaitDialog();
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, DBContent.DBUser.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.getCount() > 0 && data.moveToFirst()) {
            isEredar = data.getInt(data.getColumnIndex(DBContent.DBUser.USER_ISEREDAR));
            goldCount = data.getInt(data.getColumnIndex(DBContent.DBUser.USER_GROWTHCOUNT));
            userId = data.getInt(data.getColumnIndex(DBContent.DBUser.USER_ID));
            if (!TextUtils.isEmpty(data.getString(data.getColumnIndex(DBContent.DBUser.USER_SMALLIMG))))
                FrescoTool.loadImage(ivUserHeader, data.getString(data.getColumnIndex(DBContent.DBUser.USER_SMALLIMG)));
            if (!TextUtils.isEmpty(data.getString(data.getColumnIndex(DBContent.DBUser.USER_BACKGROUNDIMG))))
                FrescoTool.loadImage(ivMyself, data.getString(data.getColumnIndex(DBContent.DBUser.USER_BACKGROUNDIMG)), String.valueOf(TDevice.dip2px(256, this)));
            if (!TextUtils.isEmpty(data.getString(data.getColumnIndex(DBContent.DBUser.USER_NIKE)))) {
                tvNickname.setVisibility(View.VISIBLE);
                tvNickname.setText(data.getString(data.getColumnIndex(DBContent.DBUser.USER_NIKE)));
            }
            tvIdentity.setText(data.getString(data.getColumnIndex(DBContent.DBUser.USER_EREDARNAME)));
            rb.setRating(data.getInt(data.getColumnIndex(DBContent.DBUser.USER_LEVEL)));
            tvFan.setText("Fans(" + data.getInt(data.getColumnIndex(DBContent.DBUser.USER_FANS)) + ")");
            myUid.setText(data.getString(data.getColumnIndex(DBContent.DBUser.USER_UID)));
            myCount.setText(data.getInt(data.getColumnIndex(DBContent.DBUser.USER_GOLDCOUNT)) + "");
            if (data.getInt(data.getColumnIndex(DBContent.DBUser.USER_ISEREDAR)) == 0) {
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
            pb.setMax(30000);
            int growth = data.getInt(data.getColumnIndex(DBContent.DBUser.USER_GROWTHCOUNT));
            tvProgress.setText(growth + "/30000");
            tvProgressNext.setText((30000 - growth) + "");
            ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", growth);
            animation.setDuration(500); // 0.5 second
            animation.setInterpolator(new DecelerateInterpolator());
            animation.start();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
