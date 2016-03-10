//package com.cmbb.smartkids.activity.home.fragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.cmbb.smartkids.R;
//import com.cmbb.smartkids.activity.home.adapter.ServiceCityAdapter;
//import com.cmbb.smartkids.activity.home.adapter.ServiceFraAdapter;
//import com.cmbb.smartkids.activity.home.adapter.ServiceSortAdapter;
//import com.cmbb.smartkids.activity.home.adapter.ServiceStatusAdapter;
//import com.cmbb.smartkids.activity.home.model.ServiceSortModel;
//import com.cmbb.smartkids.activity.search.SearchActivity;
//import com.cmbb.smartkids.activity.serve.ActiveDetailActivity;
//import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
//import com.cmbb.smartkids.base.BaseApplication;
//import com.cmbb.smartkids.base.BaseFragment;
//import com.cmbb.smartkids.base.Constants;
//import com.cmbb.smartkids.base.CustomListener;
//import com.cmbb.smartkids.network.NetRequest;
//import com.cmbb.smartkids.widget.spinner.NiceSpinner;
//import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// * 项目名称：LovelyBaby
// * 类描述：
// * 创建人：javon
// * 创建时间：2015/8/24 10:44
// */
//public class ServiceFragment extends BaseFragment {
////    private final String TAG = ServiceFragment.class.getSimpleName();
////    private LoadMoreRecyclerView lmRc;
////    private NiceSpinner citySp, sortSp, statusSp;
////    private ServiceFraAdapter adapter;
////    private int pager = 0;
////    private int pagerSize = 5;
////    private ServiceCityAdapter cityAdapter;
////    private ServiceSortAdapter sortAdapter;
////    private ServiceStatusAdapter statusAdapter;
////    private int cityValues = 0, sortValues = 0, statusValues = 0;
////    private int cityPos = 0, typePos = 0, statusPos = 0;
////    private boolean first = true;  // 控制第一次进入页面 sp不刷页面
////
////    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////        View root = inflater.inflate(R.layout.fragment_service, null);
////        return root;
////    }
////
////    @Override
////    public void onActivityCreated(Bundle savedInstanceState) {
////        super.onActivityCreated(savedInstanceState);
////        initActionBar();
////        initView();
////        initData();
////        addListener();
////    }
////
////
////    private ActionBar actionbar;
////    private TextView tvTitle;
////    private ImageView ivRight, ivLeft;
////
////    /**
////     * 初始化actionbar
////     */
////    private void initActionBar() {
////        try {
////            Toolbar v = (Toolbar) getView().findViewById(R.id.tl_main_actionbar);
////            tvTitle = (TextView) v.findViewById(R.id.tv_main_toolbar);
////            tvTitle.setText("服务");
////            ivLeft = (ImageView) v.findViewById(R.id.iv_main_toolbar_left);
////            ivRight = (ImageView) v.findViewById(R.id.iv_main_toolbar_right);
////            ivRight.setVisibility(View.VISIBLE);
////            ivRight.setImageResource(R.mipmap.btn_search_bg);
////            ivRight.setOnClickListener(this);
////            ((AppCompatActivity) getActivity()).setSupportActionBar(v);
////            actionbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
////            actionbar.setDisplayHomeAsUpEnabled(false);
////            actionbar.setDisplayShowTitleEnabled(false);
////        } catch (Exception e) {
////            com.cmbb.smartkids.utils.log.Log.i(TAG, "actionbar is null");
////        }
////    }
////
////    private void initView() {
////        lmRc = (LoadMoreRecyclerView) getView().findViewById(R.id.plmrc_home_service);
////        lmRc.setLinearLayout();
////        adapter = new ServiceFraAdapter();
////        adapter.setData(new ArrayList<ServiceListModel.DataEntity.RowsEntity>());
////        lmRc.setAdapter(adapter);
////        citySp = (NiceSpinner) getView().findViewById(R.id.spinner_home_service_city);
////        sortSp = (NiceSpinner) getView().findViewById(R.id.spinner_home_service_sort);
////        statusSp = (NiceSpinner) getView().findViewById(R.id.spinner_home_service_status);
////    }
////
////    private void initData() {
////        cityAdapter = new ServiceCityAdapter(getActivity());
////        sortAdapter = new ServiceSortAdapter(getActivity());
////        statusAdapter = new ServiceStatusAdapter(getActivity());
////        handleServiceSort();
////    }
////
////    private void addListener() {
////        lmRc.setPullLoadMoreListener(new CustomPullLoadMoreListener());
////        lmRc.setInitializeWithoutPb();
////        adapter.setOnFooterTryAgain(this);
////        adapter.setOnItemClick(onItemListener);
////        citySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
////            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                if (cityPos != position && !first) {
////                    adapter.clearData();
////                    cityValues = Integer.parseInt(cityAdapter.getValues(position));
////                    cityPos = position;
////                    pager = 0;
////                    showWaitsDialog();
////                    handleRequest(pager, pagerSize);
////                }
////            }
////
////            @Override
////            public void onNothingSelected(AdapterView<?> parent) {
////
////            }
////        });
////        sortSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
////            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                if (typePos != position && !first) {
////                    adapter.clearData();
////                    sortValues = Integer.parseInt(sortAdapter.getValues(position));
////                    typePos = position;
////                    pager = 0;
////                    showWaitsDialog();
////                    handleRequest(pager, pagerSize);
////                }
////            }
////
////            @Override
////            public void onNothingSelected(AdapterView<?> parent) {
////
////            }
////        });
////        statusSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
////            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                if (statusPos != position && !first) {
////                    adapter.clearData();
////                    statusValues = Integer.parseInt(statusAdapter.getValues(position));
////                    statusPos = position;
////                    pager = 0;
////                    showWaitsDialog();
////                    handleRequest(pager, pagerSize);
////                }
////            }
////
////            @Override
////            public void onNothingSelected(AdapterView<?> parent) {
////
////            }
////        });
////    }
////
////    @Override
////    public void onClick(View v) {
////        startActivity(new Intent(getActivity(), SearchActivity.class));
////    }
////
////    private CustomListener.ItemClickListener onItemListener = new CustomListener.ItemClickListener() {
////        @Override
////        public void onItemClick(View v, int position, Object object) {
////            Intent intent = new Intent(getActivity(), ActiveDetailActivity.class);
////            ServiceListModel.DataEntity.RowsEntity item = (ServiceListModel.DataEntity.RowsEntity) object;
////            intent.putExtra("serviceId", item.getId());
////            getActivity().startActivity(intent);
////        }
////    };
////
////
////    class CustomPullLoadMoreListener implements LoadMoreRecyclerView.PullLoadMoreListener {
////        @Override
////        public void onInitialize() {
////            handleRequest(pager, pagerSize);
////        }
////
////        @Override
////        public void onRefresh() {
////            adapter.clearData();
////            pager = 0;
////            handleRequest(pager, pagerSize);
////        }
////
////        @Override
////        public void onLoadMore() {
////            pager++;
////            handleRequest(pager, pagerSize);
////        }
////    }
////
////    /**
////     * 服务列表
////     *
////     * @param pager
////     * @param pagerSize
////     */
////    private void handleRequest(int pager, int pagerSize) {
////        HashMap<String, String> params = new HashMap<>();
////        if (cityValues != 0)
////            params.put("city", String.valueOf(cityValues));
////        if (sortValues != 0)
////            params.put("type", String.valueOf(sortValues));
////        if (statusValues != 0)
////            params.put("status", String.valueOf(statusValues));
////        params.put("pageNo", String.valueOf(pager));
////        params.put("numberOfPerPage", String.valueOf(pagerSize));
////        NetRequest.postRequest(Constants.ServiceInfo.HOME_MAIN_HOT_SERVICE, BaseApplication.token, params, ServiceListModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
////            @Override
////            public void onSuccessListener(Object object, String msg) {
////                hideWaitDialog();
////                lmRc.setPullLoadMoreCompleted();
////                if (first)
////                    first = false;
////                ServiceListModel result = (ServiceListModel) object;
////                if (result != null && result.getData() != null && result.getData().getRecords() != 0) {
////                    lmRc.setHasContent();
////                    adapter.addData(result.getData().getRows(), lmRc);
////                }
////                showShortToast(msg);
////                if (adapter.getDataSize() == 0)
////                    lmRc.setNoContent();
////            }
////
////            @Override
////            public void onErrorListener(String message) {
////                hideWaitDialog();
////                lmRc.setPullLoadMoreCompleted();
////                showShortToast(message);
////                if (first)
////                    first = false;
////            }
////        }));
////    }
////
////    /**
////     * 服务列表字典
////     */
////    private void handleServiceSort() {
////        showWaitsDialog();
////        HashMap<String, String> params = new HashMap<>();
////        params.put("typeCode", "serviceCity,services,serviceStatus");
////        NetRequest.postRequest(Constants.ServiceInfo.SERVICE_SORT_REQUEST, BaseApplication.token, params, ServiceSortModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
////            @Override
////            public void onSuccessListener(Object object, String msg) {
////                ServiceSortModel result = (ServiceSortModel) object;
////                if (result != null && result.getData() != null) {
////                    ServiceSortModel.DataEntity.ServiceCityEntity allCity = new ServiceSortModel.DataEntity.ServiceCityEntity();
////                    allCity.setName("全部");
////                    allCity.setValue("0");
////                    ServiceSortModel.DataEntity.ServicesEntity allSort = new ServiceSortModel.DataEntity.ServicesEntity();
////                    allSort.setName("全部");
////                    allSort.setValue("0");
////                    ServiceSortModel.DataEntity.ServiceStatusEntity allStatus = new ServiceSortModel.DataEntity.ServiceStatusEntity();
////                    allStatus.setName("全部");
////                    allStatus.setValue("0");
////                    result.getData().getServiceCity().add(0, allCity);
////                    result.getData().getServices().add(0, allSort);
////                    result.getData().getServiceStatus().add(0, allStatus);
////                    cityAdapter.setData(result.getData().getServiceCity());
////                    citySp.setAdapter(cityAdapter);
////                    sortAdapter.setData(result.getData().getServices());
////                    sortSp.setAdapter(sortAdapter);
////                    statusAdapter.setData(result.getData().getServiceStatus());
////                    statusSp.setAdapter(statusAdapter);
////                }
////            }
////
////            @Override
////            public void onErrorListener(String message) {
////                hideWaitDialog();
////                showShortToast(message);
////            }
////        }));
////    }
//
//}
