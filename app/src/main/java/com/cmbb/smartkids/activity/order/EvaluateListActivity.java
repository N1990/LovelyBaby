package com.cmbb.smartkids.activity.order;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.order.adapter.EvaluationAdapter;
import com.cmbb.smartkids.activity.order.model.EvaluateModel;
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.activity.user.adapter.ServiceOrderAdapter;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class EvaluateListActivity extends BaseActivity {
    private final String TAG = EvaluateListActivity.class.getSimpleName();
    private RecyclerView rv;
    private EvaluationAdapter adapter;
    private String orderCode;
    private int serviceId;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluate_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_evaluate));
        setActionBarRight("提交");
        rv = (RecyclerView) findViewById(R.id.lmrv_self);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EvaluationAdapter();
    }

    private void initData() {
        Bundle bundle = null;
        if(getIntent() != null && (bundle = getIntent().getExtras()) != null){
            serviceId = bundle.getInt("service_id");
            orderCode = bundle.getString("order_code");
            handleEvaluateListRequest(String.valueOf(serviceId));
        }else{
            showShortToast("传参出错拉~");
        }

    }

    private void addListener() {
        adapter.setOnHeaderListener(onHeaderListener);
        adapter.setOnHandlerListener(onHandlerListener);
    }

    private CustomListener.ItemClickListener onHeaderListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            EvaluateModel.DataEntity item = (EvaluateModel.DataEntity) object;
            int userId = item.getUserId();
            Intent intent = new Intent(EvaluateListActivity.this, UserCenterActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
    };


    private CustomListener.ItemClickListener onHandlerListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            /*int check = (int) object;
            dataList.get(position).setEvaluateLevel(check);
            showShortToast("position:" + check);*/
        }
    };

    @Override
    public void onClick(View v) {
        handleRequest();
    }



    /**
     * 获取评价达人列表
     */
    private void handleEvaluateListRequest(String serviceId){
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("id", serviceId);
        NetRequest.postRequest(Constants.ServiceInfo.EVALUATE_POPMAN_LIST, BaseApplication.token, params, EvaluateModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                EvaluateModel data = (EvaluateModel) object;
//                dataList = new ArrayList<EvaluateModel.DataEntity>();
                if(data != null && data.getData().size() > 0){
//                    dataList.addAll(data.getData());
                    adapter.setData(data.getData());
                    rv.setAdapter(adapter);
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
     * 评价达人
     */
    private void handleRequest(){
        if(TextUtils.isEmpty(orderCode)){
            showShortToast("参数出错");
            return;
        }
        ArrayList<String> json = new ArrayList<>();
        for (int i = 0; i < adapter.getData().size(); i++){
            EvaluateModel.DataEntity item = adapter.getData().get(i);
            if(item.getEvaluateLevel() != 0){
                String itemJson = "{\"eredarId\":" + item.getUserId() + ",\"evaluateType\":" + item.getEvaluateLevel() + ",\"evaluateContent\":" + "\"" + item.getEvaluateContent() + "\"}";
                json.add(itemJson);
            }
        }
        showWaitDialog();
        HashMap<String, Object> params = new HashMap<>();
        params.put("orderCode", orderCode);
        params.put("evaluate", json);
        Log.e(TAG, "json : " + Arrays.toString(json.toArray()));
        NetRequest.postRequest(Constants.ServiceInfo.EVALUATE_POPMAN_REQUEST, BaseApplication.token, 0, params, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                showShortToast(msg);
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }


}
