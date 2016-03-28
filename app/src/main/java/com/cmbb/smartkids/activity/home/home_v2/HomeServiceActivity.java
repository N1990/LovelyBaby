package com.cmbb.smartkids.activity.home.home_v2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home_v2.adapter.PopuDictAdapter;
import com.cmbb.smartkids.activity.home.home_v2.adapter.ServiceAdapter;
import com.cmbb.smartkids.activity.home.home_v2.holder.servicedict.ServiceDictFooterHolder;
import com.cmbb.smartkids.activity.home.model.ServiceSortModel;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.activity.serve.v2.ServerDetailActivityV2;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.cmbb.smartkids.utils.log.Log;
import com.squareup.okhttp.Request;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午4:18
 */
public class HomeServiceActivity extends BaseHomeActivity implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {

    // 筛选
    LinearLayout btnCity, btnSmart;//筛选的按钮
    ListPopupWindow mCityListPopupWindow;
    PopupWindow mSmartPopupWindow;
    PopuDictAdapter popuDictAdapter;// 智能筛选adapter
    private String serviceWay, serviceCity, serviceCategroy, serviceSortType = "high_price";

    protected SmartRecyclerView mSmartRecyclerView;
    protected ServiceAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_v2;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setNoBack();
        setTitle("服务");
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        tvService.setSelected(true);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("serviceCategroy"))) {
            serviceCategroy = getIntent().getStringExtra("serviceCategroy");
        } else {
            serviceCategroy = "";
        }
        onRefresh();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    private void initView() {
        btnCity = (LinearLayout) findViewById(R.id.btn_city);
        btnSmart = (LinearLayout) findViewById(R.id.btn_smart);
        initSortRequest();
        initServiceSort();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mSmartRecyclerView = (SmartRecyclerView) findViewById(R.id.recyclerView);
        mSmartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ServiceAdapter(this);
        mSmartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        mSmartRecyclerView.setRefreshListener(this);
    }

    //获取排序
    private void initServiceSort() {
    }

    private void initSortRequest() {
        ServiceSortModel.getServiceSortREquest(new OkHttpClientManager.ResultCallback<ServiceSortModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(ServiceSortModel response) {
                if (response != null)
                    response.getData().getServiceCity().add(0, new ServiceSortModel.DataEntity.ServiceCityEntity("全部", ""));
                initPopup(response);
            }
        });
    }

    @Override
    public void onLoadMore() {
        pager++;
        ServiceListModel.getServiceListRequest(serviceWay, serviceCity, serviceCategroy, serviceSortType, pager, pagerSize, new OkHttpClientManager.ResultCallback<ServiceListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(ServiceListModel response) {
                if (response != null) {
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pager = 0;
        ServiceListModel.getServiceListRequest(serviceWay, serviceCity, serviceCategroy, serviceSortType, pager, pagerSize, new OkHttpClientManager.ResultCallback<ServiceListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(ServiceListModel response) {
                if (response != null) {
                    adapter.clear();
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    /**
     * 初始化Popup
     */
    private void initPopup(final ServiceSortModel dataEntity) {
        btnCity.setOnClickListener(this);
        btnSmart.setOnClickListener(this);
        mCityListPopupWindow = new ListPopupWindow(this);
        mCityListPopupWindow.setAdapter(new ArrayAdapter<ServiceSortModel.DataEntity.ServiceCityEntity>(this, android.R.layout.simple_list_item_1, dataEntity.getData().getServiceCity()));
        //mListPopupWindow.setListSelector(getResources().getDrawable(R.mipmap.ic_launcher));
        mCityListPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mCityListPopupWindow.setAnchorView(btnCity);
        mCityListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCityListPopupWindow.dismiss();
                serviceCity = dataEntity.getData().getServiceCity().get(position).getValue();
                PopuCityFlag = false;
                onRefresh();
            }
        });

        //智能筛选
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.popup_smart_dict, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        popuDictAdapter = new PopuDictAdapter(this, dataEntity);
        popuDictAdapter.setOnConfirmClick(onSmartConfirm);
        recyclerView.setAdapter(popuDictAdapter);
        mSmartPopupWindow = new PopupWindow(recyclerView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mSmartPopupWindow.setOutsideTouchable(true);
        mSmartPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mSmartPopupWindow.setTouchable(true);
    }

    //智能筛选回调
    private CustomListener.ItemClickListener onSmartConfirm = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            ServiceDictFooterHolder.ConfirmModel confirmModel = (ServiceDictFooterHolder.ConfirmModel) object;
            if (!TextUtils.isEmpty(confirmModel.getCategory())) {
                serviceCategroy = confirmModel.getCategory();
            } else {
                serviceCategroy = "";
            }
            if (!TextUtils.isEmpty(confirmModel.getSortType())) {
                serviceSortType = confirmModel.getSortType();
            } else {
                serviceSortType = "high_price";
            }
            if (!TextUtils.isEmpty(confirmModel.getType())) {
                serviceWay = confirmModel.getType();
            } else {
                serviceWay = "";
            }
            mSmartPopupWindow.dismiss();
            PopuSmartFlag = false;
            onRefresh();
        }
    };


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_city:// 城市筛选
                if (PopuCityFlag) {
                    PopuCityFlag = false;
                    mCityListPopupWindow.dismiss();
                } else {
                    PopuCityFlag = true;
                    mCityListPopupWindow.show();
                }
                break;
            case R.id.btn_smart:// 智能筛选
                if (PopuSmartFlag) {
                    PopuSmartFlag = false;
                    mSmartPopupWindow.dismiss();
                } else {
                    PopuSmartFlag = true;
                    mSmartPopupWindow.showAsDropDown(btnSmart);
                }
                break;
        }
    }

    @Override
    protected void netChange() {
        onRefresh();
    }

    boolean PopuSmartFlag = false;// popu的关开的标识位
    boolean PopuCityFlag = false;// popu的关开的标识位

    @Override
    public void onBackPressed() {
        if (mSmartPopupWindow != null && mSmartPopupWindow.isShowing()) {
            mSmartPopupWindow.dismiss();
            PopuSmartFlag = false;
        } else if (mCityListPopupWindow != null && mCityListPopupWindow.isShowing()) {
            mCityListPopupWindow.dismiss();
            PopuCityFlag = false;
        } else {
            super.onBackPressed();
        }
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, HomeServiceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    /**
     * 搜索
     *
     * @param context
     * @param serviceCategroy
     */
    public static void newIntent(Context context, String serviceCategroy) {
        Intent intent = new Intent(context, HomeServiceActivity.class);
        intent.putExtra("serviceCategroy", serviceCategroy);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Log.e("id", adapter.getItem(position).getId() + "");
        ServerDetailActivityV2.newIntent(this, adapter.getItem(position).getId());
    }
}
