package com.cmbb.smartkids.network;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.Toast;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.ImageModel;
import com.cmbb.smartkids.activity.login.LoginActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.db.DBHelper;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.Tools;
import com.cmbb.smartkids.utils.log.Log;
import com.cmbb.smartkids.utils.log.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.gson.Gson;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/17 下午3:39
 */
public class NetRequest {

    private static OkHttpClient httpClient;
    private static final int maxStale = 60 * 60 * 24 * 7; //
    private static Gson gson;
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    // private final static String BASE_URL = "http://192.168.100.181:8097/cgi"; // 事务请求服务器
    //开发环境
    public static String BASE_URL = "http://192.168.100.174:8081/wine-rest/cgi";
    public static String BASE_URL_PIC = "http://192.168.100.174:8081/wine-rest/";
    //public static String BASE_URL = "http://121.41.61.142:82/wine-rest/cgi";
    //生产环境
//    private final static String BASE_URL = "http://mengbaopai.smart-kids.com:8081/wine-rest/cgi";
    private final static String BASE_IMAGE_URL = "";//保存图片服务器

    static {
        int cacheSize = 20 * 1024 * 1024;
        httpClient = new OkHttpClient();
        gson = new Gson();
        httpClient.setConnectTimeout(3000, TimeUnit.SECONDS);
        httpClient.setReadTimeout(3000, TimeUnit.SECONDS);
        httpClient.setWriteTimeout(3000, TimeUnit.SECONDS);
        httpClient.networkInterceptors().add(new StethoInterceptor());
        httpClient.setCookieHandler(new CookieManager(new PersistentCookieStore(BaseApplication.getContext()), CookiePolicy.ACCEPT_ALL));
        //fresco
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(BaseApplication.getContext(), httpClient)
                .setDownsampleEnabled(true)
                .setResizeAndRotateEnabledForNetwork(true)
                .build();
        Fresco.initialize(BaseApplication.getContext(), config);
        try {
            if (Environment.isExternalStorageEmulated()) {
                File cacheFile = new File(Environment.getExternalStorageDirectory(), Constants.packageName);
                httpClient.setCache(new Cache(cacheFile, cacheSize));
            } else {
                File cacheFile = new File(BaseApplication.getContext().getCacheDir(), "network");
                httpClient.setCache(new Cache(cacheFile, cacheSize));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * post网络请求
     *
     * @param url
     * @param params
     * @param srcClass
     */
    public static <T extends Parcelable> void postRequest(String url, String token, Map<String, String> params, final Class<T> srcClass, final NetHandler netHandler) {
        // 处理网络请求
        if (!TDevice.hasInternet()) {
            Message errorMessage = new Message();
            errorMessage.what = 2;
            errorMessage.obj = BaseApplication.getContext().getString(R.string.is_netwrok);
            netHandler.sendMessage(errorMessage);
        } else {
            if (TextUtils.isEmpty(url)) {
                Message errorMessage = new Message();
                errorMessage.what = 2;
                errorMessage.obj = BaseApplication.getContext().getString(R.string.url_error);
                netHandler.sendMessage(errorMessage);
            } else {
                String json = Tools.map2json(url, token, params);
                Log.e("Netrequest", "request = " + json);
                if (json == null) {
                    Message errorMessage = new Message();
                    errorMessage.what = 2;
                    errorMessage.obj = BaseApplication.getContext().getString(R.string.params_error);
                    netHandler.sendMessage(errorMessage);
                } else {
                    RequestBody body = RequestBody.create(JSON, json);
                    Log.e("ServerUrl", "BASE_URL = " + BASE_URL);

                    Request request = new Request.Builder().url(BASE_URL).post(body).tag(url).build();
                    httpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            netHandler.sendEmptyMessage(3);
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            String responseStr = response.body().string();
                            Log.e("NetResponse", "Response = " + responseStr);
                            if (response.isSuccessful()) {  // && 服务端定义的验证码
                                try {
                                    JSONObject object = new JSONObject(responseStr);
                                    JSONObject errJson = object.optJSONObject("error");
                                    int codeStatus = object.optInt("statusCode");
                                    Log.e("err", "err = " + errJson);
                                    if (null == errJson) {
                                        if (codeStatus >= 200 && codeStatus < 300) {
                                            JSONObject data = object.optJSONObject("response");
                                            if (TextUtils.isEmpty(data.toString()) || data == null)
                                                return;
                                            String msg = data.optString("msg");
                                            // 服务端定义正确的返回码
                                            T obj = null;
                                            try {
                                                obj = gson.fromJson(data.toString(), srcClass);
                                            } catch (Exception e) {
                                                Log.e("GSON", "err = " + e.toString());
                                                e.printStackTrace();
                                            }
                                            // 主线程
                                            Bundle bundle = new Bundle();
                                            bundle.putString("message", msg);
                                            bundle.putParcelable("data", obj);
                                            Message message = new Message();
                                            message.what = 1;
                                            message.obj = bundle;
                                            netHandler.sendMessage(message);
                                        }
                                        /*else if (codeStatus == 403) {
                                            // 403错误信息
                                            String error = errJson.optString("errorInfo");
                                            if (!TextUtils.isEmpty(error)) {
                                                Message errorMessage = new Message();
                                                errorMessage.what = 4;
                                                errorMessage.obj = error;
                                                netHandler.sendMessage(errorMessage);
                                            }
                                        }*/

                                    } else {
                                        int errorCode = errJson.optInt("errorCode");
                                        String errorInfo = errJson.optString("errorInfo");
                                        switch (errorCode) {
                                            case 14:// login agin
                                            case 15:// login agin
                                                changeAccount();
                                                if (!TextUtils.isEmpty(errorInfo)) {
                                                    Message errorMessage = new Message();
                                                    errorMessage.what = 4;
                                                    errorMessage.obj = errorInfo;
                                                    netHandler.sendMessage(errorMessage);
                                                }
                                                break;
                                            default:
                                                if (codeStatus == 403) {
                                                    // 403错误信息
                                                    changeAccount();
                                                    if (!TextUtils.isEmpty(errorInfo)) {
                                                        Message errorMessage = new Message();
                                                        errorMessage.what = 4;
                                                        errorMessage.obj = errorInfo;
                                                        netHandler.sendMessage(errorMessage);
                                                    }
                                                } else {
                                                    Message errorMessage = new Message();
                                                    errorMessage.what = 2;
                                                    errorMessage.obj = errorInfo;
                                                    netHandler.sendMessage(errorMessage);
                                                }
                                                break;
                                        }

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                netHandler.sendEmptyMessage(3);
                            }
                        }
                    });
                }
            }
        }
    }


    /**
     * post网络请求
     *
     * @param url
     * @param params
     * @param srcClass
     */
    public static <T extends Parcelable> void postRequest(String url, String token, int tag, Map<String, Object> params, final Class<T> srcClass, final NetHandler netHandler) {
        // 处理网络请求
        if (!TDevice.hasInternet()) {
            Message errorMessage = new Message();
            errorMessage.what = 2;
            errorMessage.obj = BaseApplication.getContext().getString(R.string.is_netwrok);
            netHandler.sendMessage(errorMessage);
        } else {
            if (TextUtils.isEmpty(url)) {
                Message errorMessage = new Message();
                errorMessage.what = 2;
                errorMessage.obj = BaseApplication.getContext().getString(R.string.url_error);
                netHandler.sendMessage(errorMessage);
            } else {
                String json = Utils.map2json(url, token, params);
                Log.e("Netrequest", "request = " + json);
                if (json == null) {
                    Message errorMessage = new Message();
                    errorMessage.what = 2;
                    errorMessage.obj = BaseApplication.getContext().getString(R.string.params_error);
                    netHandler.sendMessage(errorMessage);
                } else {
                    RequestBody body = RequestBody.create(JSON, json);
                    Log.e("ServerUrl", "BASE_URL = " + BASE_URL);

                    Request request = new Request.Builder().url(BASE_URL).post(body).tag(url).build();
                    httpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            netHandler.sendEmptyMessage(3);
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            String responseStr = response.body().string();
                            Log.e("NetResponse", "Response = " + responseStr);
                            if (response.isSuccessful()) {  // && 服务端定义的验证码
                                try {
                                    JSONObject object = new JSONObject(responseStr);
                                    JSONObject errJson = object.optJSONObject("error");
                                    int codeStatus = object.optInt("statusCode");
                                    Log.e("err", "err = " + errJson);
                                    if (null == errJson) {
                                        if (codeStatus >= 200 && codeStatus < 300) {
                                            JSONObject data = object.optJSONObject("response");
                                            String msg = data.optString("msg");
                                            // 服务端定义正确的返回码
                                            T obj = null;
                                            try {
                                                obj = gson.fromJson(data.toString(), srcClass);
                                            } catch (Exception e) {
                                                Log.e("GSON", "err = " + e.toString());
                                                e.printStackTrace();
                                            }
                                            // 主线程
                                            Bundle bundle = new Bundle();
                                            bundle.putString("message", msg);
                                            bundle.putParcelable("data", obj);
                                            Message message = new Message();
                                            message.what = 1;
                                            message.obj = bundle;
                                            netHandler.sendMessage(message);
                                        }
                                        /*else if (codeStatus == 403) {
                                            // 403错误信息
                                            String error = errJson.optString("errorInfo");
                                            if (!TextUtils.isEmpty(error)) {
                                                Message errorMessage = new Message();
                                                errorMessage.what = 4;
                                                errorMessage.obj = error;
                                                netHandler.sendMessage(errorMessage);
                                            }
                                        }*/

                                    } else {
                                        int errorCode = errJson.optInt("errorCode");
                                        String errorInfo = errJson.optString("errorInfo");
                                        switch (errorCode) {
                                            case 14:// login agin
                                            case 15:// login agin
                                                changeAccount();
                                                if (!TextUtils.isEmpty(errorInfo)) {
                                                    Message errorMessage = new Message();
                                                    errorMessage.what = 4;
                                                    errorMessage.obj = errorInfo;
                                                    netHandler.sendMessage(errorMessage);
                                                }
                                                break;
                                            default:
                                                if (codeStatus == 403) {
                                                    // 403错误信息
                                                    changeAccount();
                                                    if (!TextUtils.isEmpty(errorInfo)) {
                                                        Message errorMessage = new Message();
                                                        errorMessage.what = 4;
                                                        errorMessage.obj = errorInfo;
                                                        netHandler.sendMessage(errorMessage);
                                                    }
                                                } else {
                                                    Message errorMessage = new Message();
                                                    errorMessage.what = 2;
                                                    errorMessage.obj = errorInfo;
                                                    netHandler.sendMessage(errorMessage);
                                                }
                                                break;
                                        }

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                netHandler.sendEmptyMessage(3);
                            }
                        }
                    });
                }
            }
        }
    }


    /**
     * 表单请求 图片
     *
     * @param url
     * @param params
     * @param imageModels
     * @param srcClass
     * @param netHandler
     */
    public static <T extends Parcelable> void postRequestWithFiles(String url, Map<String, String> params, List<ImageModel> imageModels, final Class<T> srcClass, final NetHandler netHandler) {
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.MIXED);
        for (String key : params.keySet()) {
            multipartBuilder.addFormDataPart(key, params.get(key));
            Log.e("param", key + " = " + params.get(key));
        }

        if (imageModels != null && imageModels.size() > 0) {
            for (int i = 0; i < imageModels.size(); i++) {
                multipartBuilder.addFormDataPart("topicImgList", "topicImgList", RequestBody.create(MEDIA_TYPE_PNG, new File(imageModels.get(i).getImgUrl())));
                Log.e("param", "topicImgList" + " = " + imageModels.get(i).getImgUrl());
                if (!TextUtils.isEmpty(imageModels.get(i).getContent())) {
                    multipartBuilder.addFormDataPart("imgText" + i, imageModels.get(i).getContent());
                    Log.e("param", "imgText" + i + " = " + imageModels.get(i).getContent());
                }
            }
        }

        RequestBody formBody = multipartBuilder.build();
        Log.e("param", "url = " + BASE_URL_PIC + url);
        Request request = new Request.Builder()
                .url(BASE_URL_PIC + url)
                .post(formBody)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                netHandler.sendEmptyMessage(3);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String responseStr = response.body().string();
                Log.e("NetResponse", "Response = " + responseStr);
                if (response.isSuccessful()) {  // && 服务端定义的验证码
                    try {
                        JSONObject object = new JSONObject(responseStr);
                        JSONObject errJson = object.optJSONObject("error");
                        int codeStatus = object.optInt("statusCode");
                        Log.e("err", "err = " + errJson);
                        if (null == errJson) {
                            if (codeStatus >= 200 && codeStatus < 300) {
                                JSONObject data = object.optJSONObject("response");
                                String msg = data.optString("msg");
                                // 服务端定义正确的返回码
                                T obj = null;
                                try {
                                    obj = gson.fromJson(data.toString(), srcClass);
                                } catch (Exception e) {
                                    Log.e("GSON", "err = " + e.toString());
                                    e.printStackTrace();
                                }
                                // 主线程
                                Bundle bundle = new Bundle();
                                bundle.putString("message", msg);
                                bundle.putParcelable("data", obj);
                                Message message = new Message();
                                message.what = 1;
                                message.obj = bundle;
                                netHandler.sendMessage(message);
                            }
                                        /*else if (codeStatus == 403) {
                                            // 403错误信息
                                            String error = errJson.optString("errorInfo");
                                            if (!TextUtils.isEmpty(error)) {
                                                Message errorMessage = new Message();
                                                errorMessage.what = 4;
                                                errorMessage.obj = error;
                                                netHandler.sendMessage(errorMessage);
                                            }
                                        }*/

                        } else {
                            int errorCode = errJson.optInt("errorCode");
                            String errorInfo = errJson.optString("errorInfo");
                            switch (errorCode) {
                                case 14:// login agin
                                case 15:// login agin
                                    changeAccount();
                                    if (!TextUtils.isEmpty(errorInfo)) {
                                        Message errorMessage = new Message();
                                        errorMessage.what = 4;
                                        errorMessage.obj = errorInfo;
                                        netHandler.sendMessage(errorMessage);
                                    }
                                    break;
                                default:
                                    if (codeStatus == 403) {
                                        // 403错误信息
                                        changeAccount();
                                        if (!TextUtils.isEmpty(errorInfo)) {
                                            Message errorMessage = new Message();
                                            errorMessage.what = 4;
                                            errorMessage.obj = errorInfo;
                                            netHandler.sendMessage(errorMessage);
                                        }
                                    } else {
                                        Message errorMessage = new Message();
                                        errorMessage.what = 2;
                                        errorMessage.obj = errorInfo;
                                        netHandler.sendMessage(errorMessage);
                                    }
                                    break;
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    netHandler.sendEmptyMessage(3);
                }
            }
        });
    }

    /**
     * 改变账号，需要异步执行
     */
    private static void changeAccount() {
        try {
            boolean flag = BaseApplication.mPushAgent.removeAlias(SPCache.getString(Constants.USER_ID, "") + "_" + TDevice.getDeviceId(BaseApplication.getContext()), "service");
            Log.e("Alias", "Alias remove = " + flag);
            Log.e("Alias", "Alias remove id = " + SPCache.getString(Constants.USER_ID, ""));
            if (flag) {
                SPCache.clear();
                //删除表
                DBHelper dbHelper = new DBHelper(BaseApplication.getContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                dbHelper.delete(db);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class NetHandler extends Handler {

        private WeakReference<Context> context;
        private NetResponseListener listener;

        public NetHandler(Context context, NetResponseListener listener) {
            this.context = new WeakReference<>(context);
            this.listener = listener;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Context _context = context.get();
            if (null != context) {
                Bundle bundle = null;
                try {
                    bundle = (Bundle) msg.obj;
                } catch (Exception e) {

                }
                switch (msg.what) {
                    case 1:
                        listener.onSuccessListener(bundle.getParcelable("data"), bundle.getString("message"));
                        break;
                    case 2:
                        listener.onErrorListener((String) msg.obj);
                        break;
                    case 3:
                        onFailureListener();
                        break;
                    case 4:
                        if (_context instanceof BaseActivity) {
                            BaseActivity a = (BaseActivity) _context;
                            if (a.isWaitShow())
                                a.hideWaitDialog();
                        }
                        Toast.makeText(_context, (String) msg.obj, Toast.LENGTH_LONG).show();
                        _context.startActivity(new Intent(_context, LoginActivity.class));
                        break;
                }
            }
        }


        public void onFailureListener() {
            Toast.makeText(BaseApplication.getContext(), BaseApplication.getContext().getString(R.string.system_error), Toast.LENGTH_LONG).show();
        }
    }

    public interface NetResponseListener {
        /**
         * 网络请求成功
         *
         * @param object
         */
        void onSuccessListener(Object object, String msg);


        /**
         * 网络请求成功，但是返回验证码错误
         *
         * @param message
         */
        void onErrorListener(String message);
    }


    public interface Demo<T extends Parcelable> {
        void get(T A);
    }


}
