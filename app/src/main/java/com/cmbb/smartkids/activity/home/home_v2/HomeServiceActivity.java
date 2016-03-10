package com.cmbb.smartkids.activity.home.home_v2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home_v2.adapter.ServiceAdapter;
import com.cmbb.smartkids.activity.home.model.ServiceSortModel;
import com.cmbb.smartkids.activity.serve.ActiveDetailActivity;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

import java.util.List;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午4:18
 */
public class HomeServiceActivity extends BaseActivity implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {

    // Bottom
    private TextView tvHome;
    private TextView tvService;
    private TextView tvTopic;
    private TextView tvMe;
    private TextView tvMore;

    // 筛选
    LinearLayout btnCity, btnSmart;//筛选的按钮
    ListPopupWindow mCityListPopupWindow;
    RadioGroup rgServiceType1;
    RadioGroup rgServiceType2;
    RadioGroup rgServiceWay;
    RadioGroup rgServiceSort;
    TextView btnRest;
    TextView btnComfirm;
    PopupWindow mSmartPopupWindow;
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
        initBottom();
        initView();
        onRefresh();
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
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        mSmartRecyclerView.setRefreshListener(this);
    }

    //获取排序
    private void initServiceSort() {
    }

    private void initSortRequest(){
        ServiceSortModel.getServiceSortREquest(new OkHttpClientManager.ResultCallback<ServiceSortModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(ServiceSortModel response) {
                initPopup(response.getData());
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
                    if (response.getData().getRows().size() > 0) {
                        adapter.clear();
                    }
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    /**
     * 初始化Popup
     */
    private void initPopup(final ServiceSortModel.DataEntity dataEntity) {
        btnCity.setOnClickListener(this);
        btnSmart.setOnClickListener(this);
        mCityListPopupWindow = new ListPopupWindow(this);
        mCityListPopupWindow.setAdapter(new ArrayAdapter<ServiceSortModel.DataEntity.ServiceCityEntity>(this, android.R.layout.simple_list_item_1, dataEntity.getServiceCity()));
        //mListPopupWindow.setListSelector(getResources().getDrawable(R.mipmap.ic_launcher));
        mCityListPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mCityListPopupWindow.setAnchorView(btnCity);
        mCityListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCityListPopupWindow.dismiss();
                serviceCity = dataEntity.getServiceCity().get(position).getValue();
                onRefresh();
            }
        });

        //只能赛选
        View view = LayoutInflater.from(this).inflate(R.layout.popup_smart_choise, null);
        rgServiceType2 = (RadioGroup) view.findViewById(R.id.rg_service_type);
        rgServiceType1 = (RadioGroup) view.findViewById(R.id.rg_service_type1);
        ((RadioButton)view.findViewById(R.id.rb_service_01)).setText(dataEntity.getServiceCategroy().get(0).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_02)).setText(dataEntity.getServiceCategroy().get(1).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_03)).setText(dataEntity.getServiceCategroy().get(2).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_04)).setText(dataEntity.getServiceCategroy().get(3).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_05)).setText(dataEntity.getServiceCategroy().get(4).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_06)).setText(dataEntity.getServiceCategroy().get(5).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_07)).setText(dataEntity.getServiceCategroy().get(6).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_08)).setText(dataEntity.getServiceCategroy().get(7).getName());


        rgServiceWay = (RadioGroup) view.findViewById(R.id.rg_service_way);
        ((RadioButton)view.findViewById(R.id.rb_service_way_01)).setText(dataEntity.getServices().get(0).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_way_02)).setText(dataEntity.getServices().get(1).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_way_03)).setText(dataEntity.getServices().get(2).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_way_04)).setText(dataEntity.getServices().get(3).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_way_05)).setText(dataEntity.getServices().get(4).getName());




        rgServiceSort = (RadioGroup) view.findViewById(R.id.rg_service_sort);
        ((RadioButton)view.findViewById(R.id.rb_service_sort_01)).setText(dataEntity.getServiceSortType().get(0).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_sort_02)).setText(dataEntity.getServiceSortType().get(1).getName());
        ((RadioButton)view.findViewById(R.id.rb_service_sort_03)).setText(dataEntity.getServiceSortType().get(2).getName());




        btnRest = (TextView) view.findViewById(R.id.btn_rest);
        btnRest.setOnClickListener(cancelListener);
        btnComfirm = (TextView) view.findViewById(R.id.btn_comfirm);
        btnComfirm.setOnClickListener(selectorListener);
        initRadioGroup(dataEntity);
        mSmartPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mSmartPopupWindow.setOutsideTouchable(true);
        mSmartPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mSmartPopupWindow.setTouchable(true);

    }

    private View.OnClickListener cancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mSmartPopupWindow.dismiss();
        }
    };

    private View.OnClickListener selectorListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mSmartPopupWindow.dismiss();
            onRefresh();
        }
    };

    private void initRadioGroup(final ServiceSortModel.DataEntity dataEntity) {
        rgServiceType1.setOnCheckedChangeListener(typeListener01);
        rgServiceType2.setOnCheckedChangeListener(typeListener02);
        rgServiceType1.setTag(dataEntity.getServiceCategroy());
        rgServiceType2.setTag(dataEntity.getServiceCategroy());
        rgServiceWay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.rb_service_way_01:
                        serviceWay = dataEntity.getServices().get(0).getValue();
                        break;
                    case R.id.rb_service_way_02:
                        serviceWay = dataEntity.getServices().get(1).getValue();
                        break;
                    case R.id.rb_service_way_03:
                        serviceWay = dataEntity.getServices().get(2).getValue();
                        break;
                    case R.id.rb_service_way_04:
                        serviceWay = dataEntity.getServices().get(3).getValue();
                        break;
                    case R.id.rb_service_way_05:
                        serviceWay = dataEntity.getServices().get(4).getValue();
                        break;
                }
            }
        });
        rgServiceSort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.rb_service_sort_01:
                        serviceSortType = dataEntity.getServiceSortType().get(0).getValue();
                        break;
                    case R.id.rb_service_sort_02:
                        serviceSortType = dataEntity.getServiceSortType().get(1).getValue();
                        break;
                    case R.id.rb_service_sort_03:
                        serviceSortType = dataEntity.getServiceSortType().get(2).getValue();
                        break;
                }
            }
        });
    }

    private RadioGroup.OnCheckedChangeListener typeListener01 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            List<ServiceSortModel.DataEntity.ServiceCategroyEntity> categroyEntity = (List<ServiceSortModel.DataEntity.ServiceCategroyEntity>) group.getTag();
            if (checkedId != -1) {
                rgServiceType2.setOnCheckedChangeListener(null);
                rgServiceType2.clearCheck();
                rgServiceType2.setOnCheckedChangeListener(typeListener02);
                // TODO Auto-generated method stub
                switch (checkedId){
                    case R.id.rb_service_05:
                        serviceCategroy = categroyEntity.get(4).getValue();
                        break;
                    case R.id.rb_service_06:
                        serviceCategroy = categroyEntity.get(5).getValue();
                        break;
                    case R.id.rb_service_07:
                        serviceCategroy = categroyEntity.get(6).getValue();
                        break;
                    case R.id.rb_service_08:
                        serviceCategroy = categroyEntity.get(7).getValue();
                        break;
                }
            }
        }
    };


    private RadioGroup.OnCheckedChangeListener typeListener02 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            List<ServiceSortModel.DataEntity.ServiceCategroyEntity> categroyEntity = (List<ServiceSortModel.DataEntity.ServiceCategroyEntity>) group.getTag();
            if (checkedId != -1) {
                rgServiceType1.setOnCheckedChangeListener(null);
                rgServiceType1.clearCheck();
                rgServiceType1.setOnCheckedChangeListener(typeListener01);
                // TODO Auto-generated method stub
                switch (checkedId){
                    case R.id.rb_service_01:
                        serviceCategroy = categroyEntity.get(0).getValue();
                        break;
                    case R.id.rb_service_02:
                        serviceCategroy = categroyEntity.get(1).getValue();
                        break;
                    case R.id.rb_service_03:
                        serviceCategroy = categroyEntity.get(2).getValue();
                        break;
                    case R.id.rb_service_04:
                        serviceCategroy = categroyEntity.get(3).getValue();
                        break;
                }
            }
        }
    };

    private void initBottom() {
        tvHome = (TextView) findViewById(R.id.tv_home);
        tvService = (TextView) findViewById(R.id.tv_service);
        tvService.setSelected(true);
        tvTopic = (TextView) findViewById(R.id.tv_topic);
        tvMe = (TextView) findViewById(R.id.tv_me);
        tvMore = (TextView) findViewById(R.id.tv_more);
        tvHome.setOnClickListener(this);
        tvService.setOnClickListener(this);
        tvTopic.setOnClickListener(this);
        tvMe.setOnClickListener(this);
        tvMore.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home:
                HomeActivity.newIntent(this);
                break;
            case R.id.tv_service:
                HomeServiceActivity.newIntent(this);
                break;
            case R.id.tv_topic:
                HomeTopicActivity.newIntent(this);
                break;
            case R.id.tv_me:
                HomeMeActivity.newIntent(this);
                break;
            case R.id.tv_more:
                HomeMoreActivity.newIntent(this);
                break;
            case R.id.btn_city:// 城市筛选
                mCityListPopupWindow.show();
                break;
            case R.id.btn_smart:// 智能筛选
                mSmartPopupWindow.showAsDropDown(btnSmart);
                break;
        }
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, HomeServiceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        showShortToast("adapter onItemClick is invoke");
        Intent intent = new Intent(this, ActiveDetailActivity.class);
        intent.putExtra("serviceId", adapter.getItem(position).getId());
        startActivity(intent);
    }

}
