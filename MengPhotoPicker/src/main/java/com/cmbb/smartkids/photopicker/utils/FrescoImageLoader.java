package com.cmbb.smartkids.photopicker.utils;

import android.net.Uri;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/20 上午9:31
 */
public class FrescoImageLoader {

    public static void bindCommonPic(SimpleDraweeView view, String uri) {
        bindCommonPic(view, uri, 0, 0);
    }

    public static void bindCommonPic(SimpleDraweeView view, String uri, int width, int height) {
        if (width <= 0) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            if (lp != null && lp.width > 0) {
                width = lp.width;
            }

            Log.e("Fresco", "width = " + width);
        }

        if (height <= 0) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            if (lp != null && lp.height > 0) {
                height = lp.height;
            }
            Log.e("Fresco", "height = " + height);
        }

        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                .setLocalThumbnailPreviewsEnabled(true)
                .setResizeOptions(new ResizeOptions(width, height))
                .setAutoRotateEnabled(false)
                .build();
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(view.getController())
                .build();
        view.setController(draweeController);
    }
}
