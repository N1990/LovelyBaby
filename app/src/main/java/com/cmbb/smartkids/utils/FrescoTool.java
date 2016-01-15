package com.cmbb.smartkids.utils;

import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

/**
 * 项目名称：LovelyBaby
 * 类描述：Fresco 图片加载工具
 * 创建人：N.Sun
 * 创建时间：15/9/21 下午1:29
 */
public class FrescoTool {

    private static final String TAG = FrescoTool.class.getSimpleName();

    /**
     * 按照宽高比例加载图片
     *
     * @param simpleDraweeView Fresco控件
     * @param url              图片地址
     * @param width            图片宽度（px）
     * @param height           图片高度(px)
     */
    public static void loadImage(SimpleDraweeView simpleDraweeView, String url, String width, String height) {
        if (TextUtils.isEmpty(url)) {
            Log.e(TAG, "url is null");
            simpleDraweeView.setImageResource(R.color.placeholder);
            return;
        }

        float w = Float.parseFloat(width);
        float h = Float.parseFloat(height);
        if (w != 0.0 && h != 0.0) {
            float r = w / h;
            simpleDraweeView.setAspectRatio(r);
        }
        Uri uri = Uri.parse(url + "@" + TDevice.getScreenWidth(BaseApplication.getContext()) + "w_1o.jpeg");
        Log.e("Image", "Image = " + uri.toString());
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }


    /**
     * 按照宽高比例加载图片
     *
     * @param simpleDraweeView Fresco控件
     * @param url              图片地址
     * @param ratio            图片比例
     */
    public static void loadImage(SimpleDraweeView simpleDraweeView, String url, float ratio) {
        simpleDraweeView.setMinimumWidth(TDevice.getScreenWidth(BaseApplication.getContext()));
        simpleDraweeView.setAspectRatio(ratio);
        if (TextUtils.isEmpty(url)) {
            Log.e(TAG, "url is null");
            simpleDraweeView.setImageResource(R.color.placeholder);
            return;
        }
        Uri uri = Uri.parse(url + "@" + TDevice.getScreenWidth(BaseApplication.getContext()) + "w_1o.jpeg");
        Log.e("Image", "Image = " + uri.toString());
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }

    /**
     * 按照宽高比例加载图片
     *
     * @param simpleDraweeView Fresco控件
     * @param url              图片地址
     * @param ratio            图片比例
     * @param resid            默认图片
     */
    public static void loadImage(SimpleDraweeView simpleDraweeView, String url, float ratio, int resid) {
        if (TextUtils.isEmpty(url)) {
            Log.e(TAG, "url is null");
            simpleDraweeView.setAspectRatio(ratio);
            return;
        }
        simpleDraweeView.setAspectRatio(ratio);
        Uri uri = Uri.parse(url + "@" + TDevice.getScreenWidth(BaseApplication.getContext()) + "w_1o.jpeg");
        Log.e("Image", "Image = " + uri.toString());
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setLocalThumbnailPreviewsEnabled(true)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }

    public static void loadImage(SimpleDraweeView simpleDraweeView, String url, String height) {
        if (TextUtils.isEmpty(url)) {
            Log.e(TAG, "url is null");
            return;
        }


        if (TextUtils.isEmpty(height)) {
            Log.e(TAG, "height is null");
            return;
        }

        Uri uri = Uri.parse(url + "@" + height + "h_1o.jpeg");
        Log.e("Image", "Image = " + uri.toString());
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }

    /**
     * 加载头像图片（200w）
     *
     * @param simpleDraweeView Fresco控件
     * @param url              网络地址
     */
    public static void loadImage(SimpleDraweeView simpleDraweeView, String url) {
        if (TextUtils.isEmpty(url)) {
            Log.e("FresoTool", "url 不能为空");
            return;
        }else{
            Uri uri = Uri.parse(url + "@100w_1o.jpeg");
            ImageRequest request = ImageRequestBuilder
                    .newBuilderWithSource(uri)
                    .setProgressiveRenderingEnabled(true)
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(simpleDraweeView.getController())
                    .build();
            simpleDraweeView.setController(controller);
        }
    }

    /**
     * 加载本地文件
     *
     * @param simpleDraweeView Fresco控件
     * @param file             本地文件
     */
    public static void loadImage(SimpleDraweeView simpleDraweeView, File file) {
        if (file.exists()) {
            Log.e("FresoTool", " 文件不存在");
            return;
        }
        Uri uri = Uri.fromFile(file);
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }


    /**
     * 加载本地文件
     *
     * @param simpleDraweeView Fresco控件
     * @param fileUrl          本地文件
     */
    public static void loadLocalImage(SimpleDraweeView simpleDraweeView, String fileUrl) {
        if(TextUtils.isEmpty(fileUrl)){
            return;
        }else{
            Uri uri = Uri.parse("file://" + fileUrl);
            ImageRequest request = ImageRequestBuilder
                    .newBuilderWithSource(uri)
                    .setProgressiveRenderingEnabled(true)
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(simpleDraweeView.getController())
                    .build();
            simpleDraweeView.setController(controller);
        }
    }
}
