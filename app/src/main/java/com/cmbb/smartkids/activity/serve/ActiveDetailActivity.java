package com.cmbb.smartkids.activity.serve;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home_v2.HomeActivity;
import com.cmbb.smartkids.activity.home.model.ActiveDetailModel;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.order.OrderDetailActivity;
import com.cmbb.smartkids.activity.serve.model.ServiceOrderModel;
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.ShareUtils;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.Tools;
import com.cmbb.smartkids.widget.wheelview.CustomDialogBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.socialize.sso.UMSsoHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActiveDetailActivity extends BaseActivity {
    private final String TAG = ActiveDetailActivity.class.getSimpleName();
    private final int ORDER_RESULT = 10001;
    private ScrollView nsv;
    private TextView tvJoinNum, tvUserNick, tvUserIdentity, tvContent, tvActiveLocal,
            tvContact, tvOrder, tvPrice, tvMore,
            tvTime, tvTitle, tvPreColle, tvEndTime;
    private SimpleDraweeView ivAd, ivUserHeader;
    private ImageView ivCollect, ivShare, ivRight;
    private LinearLayout llOrder;
    private RatingBar rb;
    private CustomDialogBuilder builder;
    private int serviceId;
    private ActiveDetailModel.DataEntity realData;
    private List<ActiveDetailModel.DataEntity.UserInfoListEntity> userList;
    private boolean isCollected;  // 是否收藏
    private boolean isOrder;  // 是否预定
//    private ArrayList<String> imgs = new ArrayList<>(); //图片缓存

    @Override
    protected int getLayoutId() {
        return R.layout.activity_active;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void addListener() {
        ivAd.setOnClickListener(this);
        tvUserNick.setOnClickListener(this);
        tvContact.setOnClickListener(this);
        ivUserHeader.setOnClickListener(this);
        ivCollect.setOnClickListener(this);
        tvMore.setOnClickListener(this);
        tvOrder.setOnClickListener(this);
    }

    private void initData() {
        ShareUtils.instanceOf(this);
        ShareUtils.configPlatforms();
        if (getIntent() != null) {
            serviceId = getIntent().getIntExtra("serviceId", -1);
            handleRequest();
        } else {
            showShortToast("参数出错~");
        }
    }

    private void initView() {
        setTitle("服务详情");
        ivCollect = (ImageView) findViewById(R.id.iv_active_detail_collect_right);
        nsv = (ScrollView) findViewById(R.id.nsv_active_detail);
        ivAd = (SimpleDraweeView) findViewById(R.id.tv_active_detail_ad);
        tvEndTime = (TextView) findViewById(R.id.tv_active_detail_emdtime);
        tvJoinNum = (TextView) findViewById(R.id.tv_active_detail_num);
        tvTitle = (TextView) findViewById(R.id.tv_active_detail_title);
        tvPreColle = (TextView) findViewById(R.id.tv_active_preview_and_collect);
        tvPrice = (TextView) findViewById(R.id.tv_active_detail_price);
        ivUserHeader = (SimpleDraweeView) findViewById(R.id.iv_user_center_header);
        tvUserNick = (TextView) findViewById(R.id.tv_user_center_nickename);
        tvUserIdentity = (TextView) findViewById(R.id.tv_user_center_identity);
        rb = (RatingBar) findViewById(R.id.rb_user_center_perssion);
        ivRight = (ImageView) findViewById(R.id.iv_user_base_right);
        tvTime = (TextView) findViewById(R.id.tv_active_detail_time);
        tvActiveLocal = (TextView) findViewById(R.id.tv_active_detail_local);
        tvContact = (TextView) findViewById(R.id.tv_active_detail_contact);
        tvContent = (TextView) findViewById(R.id.tv_active_detail_content);

        llOrder = (LinearLayout) findViewById(R.id.ll_active_detail_order);
        tvOrder = (TextView) findViewById(R.id.tv_active_detail_order);

        tvMore = (TextView) findViewById(R.id.tv_active_detail_more);
    }

    private void showAlertDialog() {
        int width = TDevice.getScreenWidth(this) * 3 / 4;
        builder = CustomDialogBuilder.getInstance(this).setDialogWindows(width, ViewGroup.LayoutParams.WRAP_CONTENT)
                .isCancelableOnTouchOutside(false)
                .withTitle("预定")
                .withMessage("您确定要预定该活动吗？")
                .withCancelText("取消", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                }).withComfirmText("确定", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        handleOrderRequest();
                    }
                });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_active_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            ShareUtils.showShareView();

            return true;
        } else if (id == android.R.id.home) {
            setResult(RESULT_OK);
            finish();
            return true;
        } else if (id == R.id.action_gohome) { // 回到首页
            Intent intent = new Intent(ActiveDetailActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_center_header:
            case R.id.tv_user_center_nickename:
                if (userList != null) {
                    UserCenterActivity.newIntent(ActiveDetailActivity.this, userList.get(0).getUserId());
                }
                break;
            case R.id.rl_user_center_users:
                Intent serUser = new Intent(ActiveDetailActivity.this, ServiceUserListActivity.class);
                serUser.putParcelableArrayListExtra("users", (ArrayList) userList);
                startActivity(serUser);
                break;
            case R.id.tv_active_detail_contact:
                if (realData != null) {
                    Intent callPh = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + realData.getServicePhone()));
                    startActivity(callPh);
                }
                break;
            case R.id.iv_active_detail_collect_right:
                handleCollectRequest(isCollected ? 0 : 1);
                break;
            case R.id.tv_active_detail_order:
                showAlertDialog();
                break;
            case R.id.tv_active_detail_more:
                Intent more = new Intent(ActiveDetailActivity.this, ActiveDetailMoreActivity.class);
                more.putExtra("more", realData);
                startActivityForResult(more, ORDER_RESULT);
                break;
//            case R.id.iv_active_detail_item:
//                int position = (int) v.getTag();
//                PhotoViewActivity.IntentPhotoView(v.getContext(), imgs, position + 1);
//                break;
//            case R.id.tv_active_detail_ad:
//                PhotoViewActivity.IntentPhotoView(v.getContext(), imgs, 0);
//                break;
        }

    }

    @Override
    public void onBackPressed() {
        if (builder != null && builder.isShowing()) {
            builder.dismiss();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (builder != null) {
            builder.setDialogDismiss();
        }
    }

    private void reflushView() {
        nsv.setVisibility(View.VISIBLE);
        llOrder.setVisibility(View.VISIBLE);
        findViewById(R.id.ll_empty_data).setVisibility(View.GONE);
//        FrescoTool.loadImage(ivAd, realData.getServicesImg(), String.valueOf(TDevice.dip2px(200, ivAd.getContext())));
        FrescoTool.loadImage(ivAd, realData.getServicesImg(), 1.67f);
//        imgs.add(realData.getServicesImg());
        String time01 = "";
//        String time02 = "";
        try {
            time01 = Tools.DataToString(realData.getStartTime(), "yy/MM/dd hh:mm");
//            time02 = Tools.DataToString(realData.getEndTime(), "MM/dd hh:mm");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvTime.setText(time01);
        String startTime = realData.getSurplusTime();
        if (TextUtils.isEmpty(startTime)) {
            tvEndTime.setVisibility(View.GONE);
        } else {
            SpannableString ss = new SpannableString(startTime);
            tvEndTime.setVisibility(View.VISIBLE);
            for (int i = 0; i < ss.length(); i++) {
                char temp = startTime.charAt(i);
                if (temp >= 48 && temp <= 57) { //判断是否是数字
                    ss.setSpan(new ForegroundColorSpan(Color.RED), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else {
                    ss.setSpan(new ForegroundColorSpan(Color.WHITE), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            tvEndTime.setText(ss);
        }
        tvTitle.setText(realData.getTitle());
        tvPreColle.setText("浏览" + realData.getBrowseNumber() + "次  收藏" + realData.getColletCount() + "次");
        tvJoinNum.setText("已参加 " + realData.getRealityPeoples() + "/" + realData.getPeoples());
        double price = Double.valueOf(realData.getPrice());
        tvPrice.setText(price == 0 ? "免费" : "￥" + realData.getPrice());
        tvActiveLocal.setText("服务地点：" + realData.getAddress());
        tvContact.setText(realData.getServicePhone());
        isCollected = realData.getIsCollect() == 1 ? true : false;
        ivCollect.setBackgroundResource(isCollected ? R.mipmap.btn_community_collect_pressed : R.mipmap.btn_community_collect_normal);
        isOrder = realData.getIsReserve() == 0 ? true : false;
        if (isOrder) {
            tvOrder.setEnabled(false);
            tvOrder.setBackgroundResource(R.drawable.btn_service_detail_order_disenable);
            tvOrder.setText(realData.getReserveText());
        } else {
            tvOrder.setEnabled(true);
            tvOrder.setBackgroundResource(R.drawable.btn_login_selector);
            tvOrder.setText("预定");
        }
        if (realData.getUserInfoList() != null && realData.getUserInfoList().size() > 0) {
            if (realData.getUserInfoList().size() <= 1) {
                ivRight.setVisibility(View.INVISIBLE);
            } else {
                ivRight.setVisibility(View.VISIBLE);
                findViewById(R.id.rl_user_center_users).setOnClickListener(this);
            }
            userList = realData.getUserInfoList();
            FrescoTool.loadImage(ivUserHeader, userList.get(0).getUserSmallImg());
            tvUserNick.setText(userList.get(0).getUserNike());
            tvUserIdentity.setText(userList.get(0).getUserRole().get(0).getEredarName());
        } else {
            findViewById(R.id.ll_active_detail_userinfo).setVisibility(View.GONE);
        }
        tvContent.setText(realData.getContent());
//        if (realData.getServiceImgList() != null) {
//            List<ActiveDetailModel.DataEntity.ServiceImgListEntity> imgsList = realData.getServiceImgList();
//            int postion = 0;
//            for (ActiveDetailModel.DataEntity.ServiceImgListEntity img : imgsList) {
//
//                View child = getLayoutInflater().inflate(R.layout.list_active_detail_item, null);
//                TextView tvContent = (TextView) child.findViewById(R.id.tv_active_detail_item);
//                SimpleDraweeView iv = (SimpleDraweeView) child.findViewById(R.id.iv_active_detail_item);
//                iv.setOnClickListener(this);
//                iv.setTag(postion);
//                tvContent.setTextIsSelectable(true);
//                tvContent.setText(img.getContent());
//                FrescoTool.loadImage(iv, img.getImgPath(), img.getImgWidth() + "", img.getImgHeight() + "");
//                postion++;
//                imgs.add(img.getImgPath());
//                llContainer.addView(child);
//            }
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        UMSsoHandler ssoHandler = ShareUtils.mController.getConfig().getSsoHandler(requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        if (requestCode == ORDER_RESULT && resultCode == RESULT_OK) {
            showWaitDialog();
            handleRequest();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 获取服务详情
     */
    private void handleRequest() {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(serviceId));
        NetRequest.postRequest(Constants.ServiceInfo.SERVICE_DETAIL_REQUEST, BaseApplication.token, params, ActiveDetailModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                ActiveDetailModel data = (ActiveDetailModel) object;
                try {
                    if (data != null && data.getData() != null) {
                        realData = data.getData();
                        if (!TextUtils.isEmpty(realData.getServicesImg()))
                            ShareUtils.setShareContent(realData.getTitle(), realData.getContent(), Constants.Share.getServerShareUrl(realData.getId()), realData.getServicesImg());
                        reflushView();
                    } else {
                        showShortToast(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
     * 收藏服务
     *
     * @param isCollect
     */
    private void handleCollectRequest(final int isCollect) {  // 1 收藏  0取消收藏
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(serviceId));
        params.put("isCollect", String.valueOf(isCollect));
        NetRequest.postRequest(Constants.ServiceInfo.HANDLE_COLLECT_SERVICE, BaseApplication.token, params, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                SecurityCodeModel data = (SecurityCodeModel) object;
                if (data != null) {
                    isCollected = isCollected ? false : true;
                    ivCollect.setBackgroundResource(isCollected ? R.mipmap.btn_community_collect_pressed : R.mipmap.btn_community_collect_normal);
                }
                showShortToast(msg);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

    /**
     * 预定服务
     */
    private void handleOrderRequest() {
        if (builder != null)
            builder.setDialogDismiss();
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("serviceId", String.valueOf(serviceId));
        NetRequest.postRequest(Constants.ServiceInfo.HANDLE_ORDER_SERVICE, BaseApplication.token, params, ServiceOrderModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                ServiceOrderModel data = (ServiceOrderModel) object;
                ServiceOrderModel.DataEntity orderDetail = null;
                hideWaitDialog();
                if (data != null && (orderDetail = data.getData()) != null) {
                    Intent intent = new Intent(ActiveDetailActivity.this, OrderDetailActivity.class);
                    intent.putExtra("orderDetail", orderDetail);
                    intent.putExtra("serviceTitle", realData.getTitle());
                    intent.putExtra("serviceCity", realData.getCityText() + "");
                    intent.putExtra("serviceTime", realData.getStartTime());
                    intent.putExtra("serviceAddress", realData.getAddress());
                    intent.putExtra("serviceImg", realData.getServicesImg());
                    intent.putExtra("serviceWidth", realData.getImgWidth());
                    intent.putExtra("serviceHeight", realData.getImgHeight());
                    intent.putExtra("service_type", realData.getType());
                    intent.putExtra("flag", true);
                    startActivityForResult(intent, ORDER_RESULT);
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
}
