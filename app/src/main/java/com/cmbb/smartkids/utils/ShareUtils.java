package com.cmbb.smartkids.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.hardware.SensorEvent;
import android.widget.Toast;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.mengbottomsheets.BottomSheet;
import com.umeng.scrshot.adapter.UMAppAdapter;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sensor.UMSensor;
import com.umeng.socialize.sensor.beans.ShakeMsgType;
import com.umeng.socialize.sensor.controller.UMShakeService;
import com.umeng.socialize.sensor.controller.impl.UMShakeServiceFactory;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javon on 2015/8/3.
 * 使用方法：
 * mController = ShareUtils.instanceOf(this);
 * ShareUtils.configPlatforms();
 * ShareUtils.setShareContent();
 * 摇一摇分享
 * ShareUtils.registerShakeToShare();
 * 授权回调
 * onactivityreslut
 * <p/>
 * 按钮点击事件调share面板
 * ShareUtils.showShareView();
 */
public class ShareUtils {
    private static Activity activity;
    private static final UMSocialService mController = UMServiceFactory.getUMSocialService(Constants.Share.DESCRIPTOR);
    private static final UMShakeService mShakeController = UMShakeServiceFactory.getShakeService(Constants.Share.DESCRIPTOR);

    /**
     * @param activity1
     */
    public static UMSocialService instanceOf(Activity activity1) {
        if (activity1 != null)
            activity = activity1;
        return mController;
    }


    //授权
    public static void configPlatforms() {
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        addQQQZonePlatform();
        addWXPlatform();
    }

    /**
     * QQ QQ空间授权
     *
     * @return
     */
    public static void addQQQZonePlatform() {
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity,
                Constants.Share.QQ_APP_KEY, Constants.Share.QQ_APPSECRET);
        qqSsoHandler.addToSocialSDK();
        // 添加QZone平台
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, Constants.Share.QQ_APP_KEY, Constants.Share.QQ_APPSECRET);
        qZoneSsoHandler.addToSocialSDK();
    }

    /**
     * 微信 朋友圈授权
     *
     * @return
     */
    public static void addWXPlatform() {
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(activity, Constants.Share.WEIXIN_APP_KEY, Constants.Share.WEIXIN_APPSECRET);
        wxHandler.addToSocialSDK();
        // 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(activity, Constants.Share.WEIXIN_APP_KEY, Constants.Share.WEIXIN_APPSECRET);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
    }

    /**
     * 根据不同的平台设置不同的分享内容</br>
     */
    public static void setShareContent(String titile, String content, String url, String imgUrl) {

        // 配置SSO
        WeiXinShareContent weixinContent = new WeiXinShareContent();
        weixinContent.setTitle(titile);
        weixinContent.setShareContent(content);
        weixinContent.setTargetUrl(url);
        weixinContent.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(weixinContent);

        // 设置朋友圈分享的内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setTitle(titile);
        circleMedia.setShareContent(content);
        circleMedia.setTargetUrl(url);
        circleMedia.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(circleMedia);

        // 设置QQ空间分享内容
        QZoneShareContent qzone = new QZoneShareContent();
        qzone.setTitle(titile);
        qzone.setShareContent(content);
        qzone.setTargetUrl(url);
        qzone.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(qzone);

        QQShareContent qqShareContent = new QQShareContent();
        qqShareContent.setTitle(titile);
        qqShareContent.setShareContent(content);
        qqShareContent.setTargetUrl(url);
        qqShareContent.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(qqShareContent);

        SinaShareContent sinaContent = new SinaShareContent();
        sinaContent.setTitle(titile);
        sinaContent.setShareContent(content);
        sinaContent.setTargetUrl(url);
        sinaContent.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(sinaContent);

    }

    public static void setShareContent(String titile, String content, String url, int imgUrl) {

        // 配置SSO
        WeiXinShareContent weixinContent = new WeiXinShareContent();
        weixinContent.setTitle(titile);
        weixinContent.setShareContent(content);
        weixinContent.setTargetUrl(url);
        weixinContent.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(weixinContent);

        // 设置朋友圈分享的内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setTitle(titile);
        circleMedia.setShareContent(content);
        circleMedia.setTargetUrl(url);
        circleMedia.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(circleMedia);

        // 设置QQ空间分享内容
        QZoneShareContent qzone = new QZoneShareContent();
        qzone.setTitle(titile);
        qzone.setShareContent(content);
        qzone.setTargetUrl(url);
        qzone.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(qzone);

        QQShareContent qqShareContent = new QQShareContent();
        qqShareContent.setTitle(titile);
        qqShareContent.setShareContent(content);
        qqShareContent.setTargetUrl(url);
        qqShareContent.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(qqShareContent);

        SinaShareContent sinaContent = new SinaShareContent();
        sinaContent.setTitle(titile);
        sinaContent.setShareContent(content);
        sinaContent.setTargetUrl(url);
        sinaContent.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(sinaContent);

    }


    public static void showShareView() {
        new BottomSheet.Builder(activity).sheet(R.menu.menu_share).listener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case R.id.btn_share_sina:
                        actionShare(SHARE_MEDIA.SINA);
                        break;
                    case R.id.btn_share_qq:
                        actionShare(SHARE_MEDIA.QQ);
                        break;
                    case R.id.btn_share_qzone:
                        actionShare(SHARE_MEDIA.QZONE);
                        break;
                    case R.id.btn_share_wx:
                        actionShare(SHARE_MEDIA.WEIXIN);
                        break;
                    case R.id.btn_share_wxcircle:
                        actionShare(SHARE_MEDIA.WEIXIN_CIRCLE);
                        break;

                }
            }
        }).grid().build().show();
    }

    private static void actionShare(SHARE_MEDIA media) {
        mController.postShare(activity, media, new SocializeListeners.SnsPostListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int status, SocializeEntity socializeEntity) {
                if (status == StatusCode.ST_CODE_SUCCESSED) {
                    Toast.makeText(activity, "分享成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "分享失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void registerShakeToShare() {
        /**
         * 摇一摇截图,直接分享 参数1: 当前所属的Activity 参数2: 截图适配器 参数3: 要用户可选的平台,最多支持五个平台 参数4:
         * 传感器监听器，包括摇一摇完成后的回调函数onActionComplete, 可在此执行类似于暂停游戏、视频等操作;
         * 还有分享完成、取消的回调函数onOauthComplete、onShareCancel。
         */
        UMAppAdapter appAdapter = new UMAppAdapter(activity);
        // 配置平台
        List<SHARE_MEDIA> platforms = new ArrayList<SHARE_MEDIA>();
        platforms.add(SHARE_MEDIA.SINA);
        platforms.add(SHARE_MEDIA.QQ);
        platforms.add(SHARE_MEDIA.QZONE);
        platforms.add(SHARE_MEDIA.WEIXIN);
        platforms.add(SHARE_MEDIA.WEIXIN_CIRCLE);
        // 通过摇一摇控制器来设置文本分享内容
        mShakeController.setShareContent("美好瞬间，摇摇分享——来自友盟社会化组件");
        mShakeController.setShakeMsgType(ShakeMsgType.PLATFORM_SCRSHOT);
        mShakeController.registerShakeListender(activity, appAdapter,
                2000, false, platforms, mSensorListener);
        mShakeController.enableShakeSound(true);
    }

    public static void unregisterShakeListener(Activity activity) {
        mShakeController.unregisterShakeListener(activity);
    }


    /**
     * 传感器监听器
     */
    private static UMSensor.OnSensorListener mSensorListener = new UMSensor.OnSensorListener() {

        @Override
        public void onStart() {

        }

        /**
         * 分享完成后回调 (non-Javadoc)
         *
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int eCode,
                               SocializeEntity entity) {
            Toast.makeText(activity, "分享完成 000000", Toast.LENGTH_SHORT)
                    .show();
        }

        /**
         * (非 Javadoc)
         *
         * @Title: onActionComplete
         * @Description: 摇一摇动作完成后回调 (non-Javadoc)
         * @param event
         * @see com.umeng.socialize.sensor.UMSensor.OnSensorListener#onActionComplete(SensorEvent)
         */
        @Override
        public void onActionComplete(SensorEvent event) {
            Toast.makeText(activity, "游戏暂停", Toast.LENGTH_SHORT).show();
        }

        /**
         * (非 Javadoc)
         *
         * @Title: onButtonClick
         * @Description: 用户点击分享窗口的取消和分享按钮触发的回调
         * @param button
         * @see com.umeng.socialize.sensor.UMSensor.OnSensorListener#onButtonClick(com.umeng.socialize.sensor.UMSensor.WhitchButton)
         */
        @Override
        public void onButtonClick(UMSensor.WhitchButton button) {
            if (button == UMSensor.WhitchButton.BUTTON_CANCEL) {
                Toast.makeText(activity, "取消分享,游戏重新开始", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // 分享中
            }
        }
    };


}
