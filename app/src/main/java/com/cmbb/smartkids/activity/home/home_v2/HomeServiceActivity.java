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
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.home_v2.adapter.ServiceAdapter;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

import java.util.ArrayList;

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
    ArrayList<String> sortModels;


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
        initPopup();
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

    @Override
    public void onLoadMore() {
        pager++;
        ServiceListModel.getServiceListRequest(0, 0, 0, pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<ServiceListModel>() {
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
        ServiceListModel.getServiceListRequest(0, 0, 0, pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<ServiceListModel>() {
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
    private void initPopup() {
        btnCity.setOnClickListener(this);
        btnSmart.setOnClickListener(this);
        mCityListPopupWindow = new ListPopupWindow(this);
        sortModels = new ArrayList<>();
        sortModels.add("上海");
        sortModels.add("北京");
        sortModels.add("广州");
        sortModels.add("深圳");
        mCityListPopupWindow.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sortModels));
        //mListPopupWindow.setListSelector(getResources().getDrawable(R.mipmap.ic_launcher));
        mCityListPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mCityListPopupWindow.setAnchorView(btnCity);
        mCityListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        //只能赛选
        View view = LayoutInflater.from(this).inflate(R.layout.popup_smart_choise, null);
        rgServiceType2 = (RadioGroup) view.findViewById(R.id.rg_service_type);
        rgServiceType1 = (RadioGroup) view.findViewById(R.id.rg_service_type1);
        rgServiceWay = (RadioGroup) view.findViewById(R.id.rg_service_way);
        rgServiceSort = (RadioGroup) view.findViewById(R.id.rg_service_sort);
        btnRest = (TextView) view.findViewById(R.id.btn_rest);
        btnComfirm = (TextView) view.findViewById(R.id.btn_comfirm);
        initRadioGroup();
        mSmartPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mSmartPopupWindow.setOutsideTouchable(true);
        mSmartPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mSmartPopupWindow.setTouchable(true);

    }

    private void initRadioGroup() {
        rgServiceType1.setOnCheckedChangeListener(typeListener01);
        rgServiceType2.setOnCheckedChangeListener(typeListener02);
        rgServiceWay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
            }
        });
        rgServiceSort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
            }
        });
    }

    private RadioGroup.OnCheckedChangeListener typeListener01 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rgServiceType2.setOnCheckedChangeListener(null);
                rgServiceType2.clearCheck();
                rgServiceType2.setOnCheckedChangeListener(typeListener02);
                // TODO Auto-generated method stub
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener typeListener02 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rgServiceType1.setOnCheckedChangeListener(null);
                rgServiceType1.clearCheck();
                rgServiceType1.setOnCheckedChangeListener(typeListener01);
                // TODO Auto-generated method stub
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

    }
}
