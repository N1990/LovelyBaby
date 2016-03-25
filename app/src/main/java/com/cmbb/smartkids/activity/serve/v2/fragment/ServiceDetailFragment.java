package com.cmbb.smartkids.activity.serve.v2.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.model.RankEredarModel;
import com.cmbb.smartkids.activity.order.v2.ConfirmOrder;
import com.cmbb.smartkids.activity.serve.v2.PopuGridAdapter;
import com.cmbb.smartkids.activity.serve.v2.ServerDetailActivityV2;
import com.cmbb.smartkids.activity.serve.v2.model.H5ServiceDetailModel;
import com.cmbb.smartkids.activity.serve.v2.model.ReserveModel;
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.cmbb.smartkids.utils.log.Log;
import com.cmbb.smartkids.widget.jsbridge.BridgeHandler;
import com.cmbb.smartkids.widget.jsbridge.BridgeWebView;
import com.cmbb.smartkids.widget.jsbridge.CallBackFunction;
import com.cmbb.smartkids.widget.jsbridge.DefaultHandler;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;

public class ServiceDetailFragment extends BaseFragment {

    private static final String TAG = ServiceDetailFragment.class.getSimpleName();
    BridgeWebView webView;
    TextView tvReserve;

    PopupWindow mEditPopupWindow;// 编辑的popup
    private RelativeLayout rlBg;
    private TextView tvType;
    private SmartRecyclerView editRecyclerView;
    private TextView tvEditTitle;
    private TextView tvEditPrice;
    private TextView tvEditJian;
    private TextView tvEditCount;
    private TextView tvEditAdd;
    private TextView tvWholePrice;
    H5ServiceDetailModel h5ServiceDetailModel;
    PopuGridAdapter popuGridAdapter;
    H5ServiceDetailModel.PriceListEntity priceListEntity;//选中的对象

    private static final String ID_PARAM = "id";

    private int serviceID;


    public ServiceDetailFragment() {
        // Required empty public constructor
    }

    public static ServiceDetailFragment newInstance(int id) {
        ServiceDetailFragment fragment = new ServiceDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ID_PARAM, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        if (getArguments() != null) {
            serviceID = getArguments().getInt(ID_PARAM);
        }
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        if (this.view == null) {
            this.view = inflater.inflate(R.layout.fragment_service_detail, container, false);
            tvReserve = (TextView) view.findViewById(R.id.tv_reserve);
            tvReserve.setOnClickListener(this);
            webView = (BridgeWebView) view.findViewById(R.id.webView);
            webView.setDefaultHandler(new DefaultHandler());
            webView.setWebChromeClient(new WebChromeClient() {

                @SuppressWarnings("unused")
                public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
                    this.openFileChooser(uploadMsg);
                }

                @SuppressWarnings("unused")
                public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
                    this.openFileChooser(uploadMsg);
                }

                public void openFileChooser(ValueCallback<Uri> uploadMsg) {

                }
            });
            webView.loadUrl(Constants.H5.SMART_SERVICE_DETAIL + "?" + SystemClock.currentThreadTimeMillis());
            //点击电话
            webView.registerHandler("callPhone", new BridgeHandler() {
                @Override
                public void handler(String data, CallBackFunction function) {
                    if (!TextUtils.isEmpty(data)) {
                        H5PhoneModel h5PhoneModel = new Gson().fromJson(data, H5PhoneModel.class);
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + h5PhoneModel.getPhone()));
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        getActivity().startActivity(intent);
                    }
                }

            });
            //图片资源
            webView.registerHandler("getImage", new BridgeHandler() {
                @Override
                public void handler(String data, CallBackFunction function) {
                    if (!TextUtils.isEmpty(data)) {
                        H5Image h5Image = new Gson().fromJson(data, H5Image.class);
                        PhotoViewActivity.IntentPhotoView(getActivity(), h5Image.getImgUrl());
                    }
                }
            });

            //头像点击
            webView.registerHandler("clickHead", new BridgeHandler() {
                @Override
                public void handler(String data, CallBackFunction function) {
                    if (!TextUtils.isEmpty(data)) {
                        RankEredarModel rankEredarModel = new Gson().fromJson(data, RankEredarModel.class);
                        UserCenterActivity.newIntent(getActivity(), rankEredarModel.getId());
                    }
                }
            });
            //订购数据
            webView.registerHandler("getServiceData", new BridgeHandler() {
                @Override
                public void handler(String data, CallBackFunction function) {
                    if (!TextUtils.isEmpty(data)) {
                        Log.i("getServiceData", data);
                        h5ServiceDetailModel = new Gson().fromJson(data, H5ServiceDetailModel.class);
                        initPopup(h5ServiceDetailModel);
                        tvReserve.setVisibility(View.VISIBLE);
                    }
                }

            });

            //获取新的ID
            webView.registerHandler("getOtherService", new BridgeHandler() {
                @Override
                public void handler(String data, CallBackFunction function) {
                    if (!TextUtils.isEmpty(data)) {
                        Log.i("getOtherService", data);
                        H5OtherService h5OtherService = new Gson().fromJson(data, H5OtherService.class);
                        ((ServerDetailActivityV2) getActivity()).newFragment(h5OtherService.getId(), true);
                    }
                }
            });

            // 发送服务ID 数据
            H5SignInitModel h5SignInitModel = new H5SignInitModel();
            h5SignInitModel.setToken(BaseApplication.token);
            h5SignInitModel.setServiceID(serviceID);
            String sendSiginInit = new Gson().toJson(h5SignInitModel);
            webView.callHandler("getParams", sendSiginInit, new CallBackFunction() {
                @Override
                public void onCallBack(String data) {

                }
            });
            webView.send("hello");
        }
        return view;
    }

    /**
     * popuwindow
     *
     * @param data
     */
    private void initPopup(final H5ServiceDetailModel data) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popup_order_edit, null);
        tvType = (TextView) view.findViewById(R.id.tv_type);
        editRecyclerView = (SmartRecyclerView) view.findViewById(R.id.edit_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        editRecyclerView.setLayoutManager(gridLayoutManager);
        popuGridAdapter = new PopuGridAdapter(getActivity());
        popuGridAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                for (H5ServiceDetailModel.PriceListEntity entity : data.getPriceList()) {
                    entity.setSelected(false);
                }
                priceListEntity = popuGridAdapter.getItem(position);
                priceListEntity.setSelected(true);
                // 设置界面
                tvEditTitle.setText(priceListEntity.getSpecification());
                tvEditCount.setText(priceListEntity.getCount() + "");
                tvEditPrice.setText("￥ " + priceListEntity.getPrice());
                tvWholePrice.setText("￥ " + priceListEntity.getAll());
                popuGridAdapter.notifyDataSetChanged();
            }
        });
        popuGridAdapter.clear();
        popuGridAdapter.addAll(data.getPriceList());
        editRecyclerView.setAdapter(popuGridAdapter);
        rlBg = (RelativeLayout) view.findViewById(R.id.rl_bg);
        rlBg.setOnClickListener(this);//取消popu
        tvEditTitle = (TextView) view.findViewById(R.id.tv_edit_title);
        tvEditPrice = (TextView) view.findViewById(R.id.tv_edit_price);
        tvEditJian = (TextView) view.findViewById(R.id.tv_edit_jian);
        tvEditCount = (TextView) view.findViewById(R.id.tv_edit_count);
        tvWholePrice = (TextView) view.findViewById(R.id.tv_edit_whole_price);
        view.findViewById(R.id.tv_edit_submit).setOnClickListener(this);
        tvEditJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 数量减小
                if (Integer.parseInt(tvEditCount.getText().toString()) > 0) {
                    tvEditCount.setText(Integer.parseInt(tvEditCount.getText().toString()) - 1 + "");
                    priceListEntity.setCount(Integer.parseInt(tvEditCount.getText().toString()));
                    tvWholePrice.setText("￥ " + Integer.parseInt(tvEditCount.getText().toString()) * priceListEntity.getPrice());
                } else {
                    tvEditCount.setText("0");
                    priceListEntity.setCount(0);
                    tvWholePrice.setText("￥ 0");
                }
            }
        });
        tvEditAdd = (TextView) view.findViewById(R.id.tv_edit_add);
        tvEditAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数量增加
                if (priceListEntity != null) {
                    if (Integer.parseInt(tvEditCount.getText().toString()) < priceListEntity.getBuyLimit()) {
                        tvEditCount.setText(Integer.parseInt(tvEditCount.getText().toString()) + 1 + "");
                        priceListEntity.setCount(Integer.parseInt(tvEditCount.getText().toString()));
                        if (!tvEditCount.getText().toString().equals("0")) {
                            tvWholePrice.setText("￥ " + Integer.parseInt(tvEditCount.getText().toString()) * priceListEntity.getPrice());
                        } else {
                            tvWholePrice.setText("￥ 0");
                        }
                    }
                }
            }
        });
        mEditPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mEditPopupWindow.setOutsideTouchable(true);
        mEditPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mEditPopupWindow.setTouchable(true);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_reserve:
                if (h5ServiceDetailModel != null) {
                    int[] location = new int[2];
                    tvReserve.getLocationOnScreen(location);
                    mEditPopupWindow.showAtLocation(tvReserve, Gravity.NO_GRAVITY, location[0], location[1] - mEditPopupWindow.getHeight());
                }
                break;
            case R.id.rl_bg:
                mEditPopupWindow.dismiss();
                break;
            case R.id.tv_edit_submit:
                // 预定订单
                if (priceListEntity == null) {
                    showShortToast("请选择规格");
                    return;
                }
                if (priceListEntity.getCount() == 0) {
                    showShortToast("请选择数量");
                    return;
                }
                showWaitsDialog();
                ReserveModel.handleReserveRequest(h5ServiceDetailModel.getId() + "", priceListEntity.getPrice(), priceListEntity.getId() + "", priceListEntity.getCount() + "", BaseApplication.token, new OkHttpClientManager.ResultCallback<ReserveModel>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        hideWaitDialog();
                        if(mEditPopupWindow.isShowing())
                            mEditPopupWindow.dismiss();
                        showShortToast(e.toString());
                    }

                    @Override
                    public void onResponse(ReserveModel response) {
                        hideWaitDialog();
                        if (response != null) {
                            if(mEditPopupWindow.isShowing())
                                mEditPopupWindow.dismiss();
                            ConfirmOrder.newIntent(getActivity(), h5ServiceDetailModel, priceListEntity, response.getData());
                        }
                    }
                });
                break;
        }
    }

    class H5SignInitModel {
        String token;
        int serviceID;

        public int getServiceID() {
            return serviceID;
        }

        public void setServiceID(int serviceID) {
            this.serviceID = serviceID;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    class H5PhoneModel {
        String phone;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    class H5Image {
        String imgUrl;
        String imgWidth;
        String imgHeight;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImgWidth() {
            return imgWidth;
        }

        public void setImgWidth(String imgWidth) {
            this.imgWidth = imgWidth;
        }

        public String getImgHeight() {
            return imgHeight;
        }

        public void setImgHeight(String imgHeight) {
            this.imgHeight = imgHeight;
        }
    }

    class H5OtherService {

        /**
         * id : 337
         * title : 服务标题
         * imgWidth : 1920
         * serviceImg : http://smart.image.alimmdn.com/system/image/2016-02-29/servicesImgFile_Y2QzZTRiYzQtODUzYS00NDU2LWIwMmMtYzhjMTY0OTM2YzNl
         * imgHeight : 1080
         */

        private int id;
        private String title;
        private int imgWidth;
        private String serviceImg;
        private int imgHeight;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getImgWidth() {
            return imgWidth;
        }

        public void setImgWidth(int imgWidth) {
            this.imgWidth = imgWidth;
        }

        public String getServiceImg() {
            return serviceImg;
        }

        public void setServiceImg(String serviceImg) {
            this.serviceImg = serviceImg;
        }

        public int getImgHeight() {
            return imgHeight;
        }

        public void setImgHeight(int imgHeight) {
            this.imgHeight = imgHeight;
        }
    }
}
