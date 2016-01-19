package com.cmbb.smartkids.activity.serve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.model.ActiveDetailModel;
import com.cmbb.smartkids.activity.order.OrderDetailActivity;
import com.cmbb.smartkids.activity.serve.adapter.ActiveAdapter;
import com.cmbb.smartkids.activity.serve.model.ServiceOrderModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.widget.wheelview.CustomDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class ActiveDetailMoreActivity extends BaseActivity {
    private final String tag = ActiveDetailMoreActivity.class.getSimpleName();
    private final int ORDER_RESULT = 10002;
    private RecyclerView rv;
    private ActiveAdapter adapter;
    private ArrayList<String> imgs = new ArrayList<>(); //图片缓存
    private CustomDialogBuilder builder;
    private ActiveDetailModel.DataEntity dataEntity;
    private boolean result;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_active_detail_more;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView(){
        setTitle(getString(R.string.title_activity_active));
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ActiveAdapter();
    }


    private void initData(){
        Bundle bundle = null;
        if(getIntent() != null && (bundle = getIntent().getExtras()) != null){
            dataEntity = bundle.getParcelable("more");
            for (ActiveDetailModel.DataEntity.ServiceImgListEntity item : dataEntity.getServiceImgList()){
                imgs.add(item.getImgPath());
            }
            adapter.setData(dataEntity.getServiceImgList(), dataEntity.getTitle(), dataEntity.getContent(), dataEntity.getIsReserve() == 1 ? false : true);
            rv.setAdapter(adapter);
        }else {
            showShortToast("传参出错~");
        }
    }




    private void addListener(){
        adapter.setOnItemImgListener(onItemListener);
        adapter.setOnFooterListener(onFooterListener);
    }

    private CustomListener.ItemClickListener onItemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            PhotoViewActivity.IntentPhotoView(v.getContext(), imgs, position);
        }
    };

    private View.OnClickListener onFooterListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showAlertDialog();
        }
    };

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
    public void onBackPressed() {
        if (builder != null && builder.isShowing()) {
            builder.dismiss();
        } else if(result){
            setResult(RESULT_OK);
            finish();
        }else{
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ORDER_RESULT && resultCode == RESULT_OK) {
            adapter.setIsOrder(true);
            result = true;
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    /**
     * 预定服务
     */
    private void handleOrderRequest() {
        if (builder != null)
            builder.setDialogDismiss();
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("serviceId", String.valueOf(dataEntity.getId()));
        NetRequest.postRequest(Constants.ServiceInfo.HANDLE_ORDER_SERVICE, BaseApplication.token, params, ServiceOrderModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                ServiceOrderModel data = (ServiceOrderModel) object;
                ServiceOrderModel.DataEntity orderDetail = null;
                hideWaitDialog();
                if (data != null && (orderDetail = data.getData()) != null && data.getData().getServiceInfo() != null) {
                    Intent intent = new Intent(ActiveDetailMoreActivity.this, OrderDetailActivity.class);
                    intent.putExtra("orderDetail", orderDetail);
                    intent.putExtra("flag", true);
//                    intent.putExtra("serviceTitle", dataEntity.getTitle());
//                    intent.putExtra("serviceCity", dataEntity.getCityText() + "");
//                    intent.putExtra("serviceTime", dataEntity.getStartTime());
//                    intent.putExtra("serviceAddress", dataEntity.getAddress());
//                    intent.putExtra("serviceImg", dataEntity.getServicesImg());
//                    intent.putExtra("serviceWidth", dataEntity.getImgWidth());
//                    intent.putExtra("serviceHeight", dataEntity.getImgHeight());
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
