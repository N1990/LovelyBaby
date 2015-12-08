package com.cmbb.smartkids.network.image;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.sdk.android.media.upload.UploadListener;
import com.alibaba.sdk.android.media.upload.UploadOptions;
import com.alibaba.sdk.android.media.utils.StringUtils;
import com.cmbb.smartkids.base.BaseApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/22 上午11:28
 */
public class ImageUpload {
    private static final String TAG = ImageUpload.class.getSimpleName();
    private static ImageUpload ourInstance = new ImageUpload();
    private HashMap<String, String> returnBody;
    private BroadcastReceiver batchUploadReceiver;
    public final ExecutorService SERVICE;
    private static ArrayList<String> _taskIds = new ArrayList<>();//记录上传图片TaskId

    public static ImageUpload getInstance() {
        return ourInstance;
    }

    private ImageUpload() {
        SERVICE = Executors.newFixedThreadPool(5);
        returnBody = new HashMap<>();
        returnBody.put("returnBody", "${width}_${height}");
        // 测试数据 test 目录
        /*options = new UploadOptions.Builder()
                .tag(String.valueOf(SystemClock.elapsedRealtime()))
                .policyMap(returnBody)
                .dir("app/test/${year}-${month}-${day}")
                .aliases("image_" + StringUtils.getUUID()).build();*/
    }


    public void uploadImages(final Context context, final ArrayList<String> images, final UploadListener uploadListener) {
        if (null == images || images.size() == 0) {
            Log.e(TAG, "images is null or size is 0");
            return;
        }
        _taskIds.clear();
        for (int i = 0; i < images.size(); i++) {
            SERVICE.submit(new ComRunnable(i) {
                @Override
                public void run() {
                    //byte[] data = BitmapUtils.getSmallBitmapBytes(images.get(index), 720, 1280, 90);
                    File file = new File(images.get(index));
                    if (file.exists()) {
                        if (-1 == index) {
                            Toast.makeText(context, "图片处理失败", Toast.LENGTH_LONG).show();
                        } else {
                            UploadOptions options = new UploadOptions.Builder()
                                    .tag(String.valueOf(SystemClock.elapsedRealtime()))
                                    .policyMap(returnBody)
                                    .dir("app/test/${year}-${month}-${day}")
                                    .aliases("image_" + StringUtils.getUUID()).build();
                            String mTaskId = BaseApplication.mediaService.upload(file, "smart", options, uploadListener);
                            _taskIds.add(mTaskId);
                        }
                    }
                }
            });
        }
    }

    /**
     * 取消所有图片上传
     */
    public void cancelAllUpload() {
        if (_taskIds.size() <= 0) return;

        for (int i = 0; i < _taskIds.size(); i++) {
            BaseApplication.mediaService.cancelUpload(_taskIds.get(i));
        }
    }

    class ComRunnable implements Runnable {

        public int index = -1;

        public ComRunnable(int i) {
            this.index = i;
        }

        @Override
        public void run() {
        }
    }
}
