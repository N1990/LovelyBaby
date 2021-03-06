package com.cmbb.smartkids.network;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
import com.cmbb.smartkids.utils.Utils;
import com.cmbb.smartkids.utils.log.Log;
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

import java.io.ByteArrayOutputStream;
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

    public static OkHttpClient httpClient;
    private static final int maxStale = 60 * 60 * 24 * 7; //
    private static Gson gson;
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static {
        int cacheSize = 20 * 1024 * 1024;
        httpClient = new OkHttpClient();
        gson = new Gson();
        httpClient.setConnectTimeout(3000, TimeUnit.SECONDS);
        httpClient.setReadTimeout(6000, TimeUnit.SECONDS);
        httpClient.setWriteTimeout(6000, TimeUnit.SECONDS);
        httpClient.setCookieHandler(new CookieManager(new PersistentCookieStore(BaseApplication.getContext()), CookiePolicy.ACCEPT_ALL));

        try {
            if (Environment.isExternalStorageEmulated()) {
                File cacheFile = new File(Environment.getExternalStorageDirectory(), Constants.PACKAGE_NAME);
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
     * @param url      URL地址
     * @param params   参数
     * @param srcClass T
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
                Log.json("RequestParams", json);
                if (json == null) {
                    Message errorMessage = new Message();
                    errorMessage.what = 2;
                    errorMessage.obj = BaseApplication.getContext().getString(R.string.params_error);
                    netHandler.sendMessage(errorMessage);
                } else {
                    RequestBody body = RequestBody.create(JSON, json);
                    Log.e("ServerUrl", "BASE_URL = " + Constants.BASE_URL);
                    Request request = new Request.Builder().url(Constants.BASE_URL).post(body).tag(Constants.BASE_URL).build();
                    httpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            netHandler.sendEmptyMessage(3);
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            String responseStr = response.body().string();
                            Log.json("Response", responseStr);
                            if (response.isSuccessful()) {  // && 服务端定义的验证码
                                try {
                                    JSONObject object = new JSONObject(responseStr);
                                    JSONObject errJson = object.optJSONObject("error");
                                    int codeStatus = object.optInt("statusCode");

                                    if (errJson != null)
                                        Log.json("err", errJson.toString());
                                    if (null == errJson) {
                                        if (codeStatus >= 200 && codeStatus < 300) {
                                            JSONObject data = object.optJSONObject("response");
                                            if (data == null || TextUtils.isEmpty(data.toString()))
                                                return;
                                            String msg = data.optString("msg");
                                            // 服务端定义正确的返回码
                                            T obj = null;
                                            try {
                                                obj = gson.fromJson(data.toString(), srcClass);
                                            } catch (Exception e) {
                                                Log.e("GSON", e.toString());
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
                Log.json("RequestParams", json);
                if (json == null) {
                    Message errorMessage = new Message();
                    errorMessage.what = 2;
                    errorMessage.obj = BaseApplication.getContext().getString(R.string.params_error);
                    netHandler.sendMessage(errorMessage);
                } else {
                    RequestBody body = RequestBody.create(JSON, json);
                    Log.e("ServerUrl", "BASE_URL = " + Constants.BASE_URL);

                    Request request = new Request.Builder().url(Constants.BASE_URL).post(body).tag(Constants.BASE_URL).build();
                    httpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            netHandler.sendEmptyMessage(3);
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            String responseStr = response.body().string();
                            Log.json("Response", responseStr);
                            if (response.isSuccessful()) {  // && 服务端定义的验证码
                                try {
                                    JSONObject object = new JSONObject(responseStr);
                                    JSONObject errJson = object.optJSONObject("error");
                                    int codeStatus = object.optInt("statusCode");
                                    if (errJson != null)
                                        Log.json("err", errJson.toString());

                                    if (null == errJson) {
                                        if (codeStatus >= 200 && codeStatus < 300) {
                                            JSONObject data = object.optJSONObject("response");
                                            String msg = data.optString("msg");
                                            // 服务端定义正确的返回码
                                            T obj = null;
                                            try {
                                                obj = gson.fromJson(data.toString(), srcClass);
                                            } catch (Exception e) {
                                                Log.e("GSON", e.toString());
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
     * @param filekey
     * @param textKey
     */

    public static <T extends Parcelable> void postRequestWithFiles(final String url, Map<String, String> params, final String filekey, final String textKey, final List<ImageModel> imageModels, final Class<T> srcClass, final NetHandler netHandler) {
        new AsyncTask<Object, Void, RequestBody>() {

            @Override
            protected RequestBody doInBackground(Object... params) {
                MultipartBuilder multipartBuilder = new MultipartBuilder();
                multipartBuilder.type(MultipartBuilder.MIXED);
                for (String key : ((Map<String, String>) params[1]).keySet()) {
                    String values = ((Map<String, String>) params[1]).get(key);
                    multipartBuilder.addFormDataPart(key, values);
                    Log.e("param", key + " = " + values);
                }

                if (params[4] != null && ((List<ImageModel>) params[4]).size() > 0) {
                    for (int i = 0; i < ((List<ImageModel>) params[4]).size(); i++) {
                        //Compress Image
                        Bitmap bmp = BitmapFactory.decodeFile(((List<ImageModel>) params[4]).get(i).getImgUrl());
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bmp.compress(Bitmap.CompressFormat.JPEG, 70, bos);
                        //multipartBuilder.addFormDataPart(filekey, filekey, RequestBody.create(MEDIA_TYPE_PNG, new File(imageModels.get(i).getImgUrl())));
                        multipartBuilder.addFormDataPart((String) params[2], (String) params[2], RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray()));
                        Log.e("param", (String) params[2] + " = " + ((List<ImageModel>) params[4]).get(i).getImgUrl());
                        if (!TextUtils.isEmpty(((List<ImageModel>) params[4]).get(i).getContent())) {
                            String values = ((List<ImageModel>) params[4]).get(i).getContent();
                            multipartBuilder.addFormDataPart((String) params[3] + i, values);
                            Log.e("param", (String) params[3] + i + " = " + values);
                        }
                    }
                }
                return multipartBuilder.build();
            }

            @Override
            protected void onPostExecute(RequestBody formBody) {
                super.onPostExecute(formBody);
                Log.e("param", "url = " + Constants.BASE + url);
                Request request = new Request.Builder()
                        .url(Constants.BASE + url)
                        .post(formBody)
                        .build();
                httpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Log.e("Response", e.toString());
                        netHandler.sendEmptyMessage(3);
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        Log.e("Response", response.toString());
                        String responseStr = response.body().string();
                        Log.json("Response", responseStr);
                        if (response.isSuccessful()) {  // && 服务端定义的验证码
                            try {
                                JSONObject object = new JSONObject(responseStr);
                                JSONObject errJson = object.optJSONObject("error");
                                int codeStatus = object.optInt("statusCode");
                                if (errJson != null)
                                    Log.e("err", errJson.toString());
                                if (errJson == null) {
                                    if (codeStatus >= 200 && codeStatus < 300) {
                                        JSONObject data = object.optJSONObject("response");
                                        String msg = data.optString("msg");
                                        // 服务端定义正确的返回码
                                        T obj = null;
                                        try {
                                            obj = gson.fromJson(data.toString(), srcClass);
                                        } catch (Exception e) {
                                            Log.e("GSON", e.toString());
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
        }.execute(url, params, filekey, textKey, imageModels);
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
            listener.onErrorListener(BaseApplication.getContext().getString(R.string.system_error));
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
