package com.cmbb.smartkids.activity.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.CommunityDetailActivity;
import com.cmbb.smartkids.activity.home.adapter.HomeFraAdapter;
import com.cmbb.smartkids.activity.home.model.BannerModel;
import com.cmbb.smartkids.activity.home.model.HomePageRootModel;
import com.cmbb.smartkids.activity.home.model.RecommonedEredarModel;
import com.cmbb.smartkids.activity.home.model.RecommonedEredarRootModel;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.serve.ActiveDetailActivity;
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：主页
 * 创建人：javon
 * 创建时间：2015/8/24 10:44
 */
public class HomeFragment extends BaseFragment {
    private final String TAG = HomeFragment.class.getSimpleName();
    private LoadMoreRecyclerView lmrc;
    private HomeFraAdapter adapter;
    private List<HomePageRootModel.DataEntity.RowsEntity> data;
    private List<BannerModel.DataEntity> adData;
    private ArrayList<RecommonedEredarModel> popManData;
    private int pager = 0;
    private int pagerSize = 5;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_recyclerview_layout, null);
        lmrc = (LoadMoreRecyclerView) root.findViewById(R.id.lmrv_self);
        lmrc.setLinearLayout();
        adapter = new HomeFraAdapter();
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initActionBar();
        data = new ArrayList<>();
        adapter.setData(data);
        lmrc.setAdapter(adapter);
        initData();
        addListener();
        sendHomeRequest(); // 网络请求
    }

    private ActionBar actionbar;
    private TextView tvTitle, tvRight;
    private ImageView ivRight, ivLeft;

    /**
     * 初始化actionbar
     */
    private void initActionBar() {
        try {
            Toolbar v = (Toolbar) getView().findViewById(R.id.tl_main_actionbar);
            tvTitle = (TextView) v.findViewById(R.id.tv_main_toolbar);
            tvTitle.setText("首页");
            tvRight = (TextView) v.findViewById(R.id.tv_main_toolbar_right);
            tvRight.setText("签到");
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setOnClickListener(this);
            ivLeft = (ImageView) v.findViewById(R.id.iv_main_toolbar_left);
            /*ivRight = (ImageView) v.findViewById(R.id.iv_main_toolbar_right);
            ivRight.setVisibility(View.VISIBLE);
            ivRight.setBackgroundResource(R.mipmap.btn_sign_bg);
            ivRight.setOnClickListener(this);*/
            ((AppCompatActivity) getActivity()).setSupportActionBar(v);
            actionbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(false);
            actionbar.setDisplayShowTitleEnabled(false);
        } catch (Exception e) {
            com.cmbb.smartkids.utils.log.Log.i(TAG, "actionbar is null");
        }
    }

    private void initData() {
        data = new ArrayList<>();
        adData = new ArrayList<>();
        popManData = new ArrayList<>();
    }

    private void addListener() {
        lmrc.setPullLoadMoreListener(lmrcListener);
        lmrc.setInitializeWithoutPb();
        adapter.setOnAdItemClick(adListener);
        adapter.setOnGridItemClick(gridListener);
        adapter.setOnItemClick(itemListener);
    }

    private void sendHomeRequest() {

    }


    private LoadMoreRecyclerView.PullLoadMoreListener lmrcListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {
            showWaitsDialog();
            sendRequest();
        }

        @Override
        public void onRefresh() {
            adapter.clearData();
            data.clear();
            popManData.clear();
            pager = 0;
            sendRequest();
        }

        @Override
        public void onLoadMore() {
            pager++;
            // 热门
            HashMap<String, String> bodyHot = new HashMap<>();
            bodyHot.put("isRecommoned", "1");
            bodyHot.put("numberOfPerPage", String.valueOf(pagerSize));
            bodyHot.put("pageNo", String.valueOf(pager));
            NetRequest.postRequest(Constants.ServiceInfo.HOME_MAIN_HOT_SERVICE, "", bodyHot, HomePageRootModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
                @Override
                public void onSuccessListener(Object ob, String msg) {
                    com.cmbb.smartkids.utils.log.Log.i(TAG, "message = " + msg);
                    HomePageRootModel object = (HomePageRootModel) ob;
                    if (null != object && object.getData() != null && object.getData().getRecords() != 0) {
                        Log.i(TAG, "HomePageRootModel = " + object.toString());
                        data = object.getData().getRows();
                        adapter.addData(data, lmrc);
                        lmrc.setPullLoadMoreCompleted();
                    } else {
                        hideWaitDialog();
                        lmrc.setPullLoadMoreCompleted();
                        showShortToast(msg);
                    }
                }

                @Override
                public void onErrorListener(String message) {
                    hideWaitDialog();
                    lmrc.setPullLoadMoreCompleted();
                    showShortToast(message);
                }
            }));
        }
    };

    //监听事件ad
    private CustomListener.ItemClickListener adListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            BannerModel.DataEntity banner = (BannerModel.DataEntity) object;    //监听事件grid
            Intent intent = new Intent(getActivity(), CommunityDetailActivity.class);
            intent.putExtra("id", banner.getId());
            startActivity(intent);
        }
    };
    //推荐达人监听事件
    private CustomListener.ItemClickListener gridListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            RecommonedEredarModel itemData = (RecommonedEredarModel) object;
            int userId = itemData.getId();
            Intent intent = new Intent(getActivity(), UserCenterActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
    };
    //监听事件item
    private CustomListener.ItemClickListener itemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            HomePageRootModel.DataEntity.RowsEntity itemData = (HomePageRootModel.DataEntity.RowsEntity) object;
            Intent intent = new Intent(getActivity(), ActiveDetailActivity.class);
            intent.putExtra("serviceId", itemData.getId());
            startActivity(intent);
        }
    };


    private void sendRequest() {
        // 达人
        HashMap<String, String> bodyEredar = new HashMap<>();
        bodyEredar.put("isRecommoned", "1");
        // 热门
        HashMap<String, String> bodyHot = new HashMap<>();
        bodyHot.put("isRecommoned", "1");
        bodyHot.put("numberOfPerPage", String.valueOf(pagerSize));
        bodyHot.put("pageNo", String.valueOf(pager));

        NetRequest.postRequest(Constants.ServiceInfo.HOME_MAIN_HOT_SERVICE, "", bodyHot, HomePageRootModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object ob, String msg) {
                com.cmbb.smartkids.utils.log.Log.i(TAG, "message = " + msg);
                HomePageRootModel object = (HomePageRootModel) ob;
                if (null != object && object.getData() != null && object.getData().getRecords() != 0) {
                    Log.i(TAG, "HomePageRootModel = " + object.toString());
                    data = object.getData().getRows();
                    reflushView();
                } else {
                    hideWaitDialog();
                    lmrc.setPullLoadMoreCompleted();
                    showShortToast(msg);
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                lmrc.setPullLoadMoreCompleted();
                showShortToast(message);
            }
        }));
        NetRequest.postRequest(Constants.ServiceInfo.HOEM_MAIN_POPMAN_REQUEST, "", bodyEredar, RecommonedEredarRootModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                RecommonedEredarRootModel obj = (RecommonedEredarRootModel) object;
                if (null != obj && obj.getData() != null && obj.getData().size() > 0) {
                    popManData = obj.getData();
                    reflushView();
                } else {
                    hideWaitDialog();
                    lmrc.setPullLoadMoreCompleted();
                    showShortToast(msg);
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                lmrc.setPullLoadMoreCompleted();
                showShortToast(message);
            }
        }));
        //广告
        NetRequest.postRequest(Constants.ServiceInfo.HOME_MAIN_AD, BaseApplication.token, null, BannerModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                BannerModel obj = (BannerModel) object;
                if (null != obj && obj.getData() != null && obj.getData().size() > 0) {
                    adData = obj.getData();
                    reflushView();
                } else {
                    hideWaitDialog();
                    lmrc.setPullLoadMoreCompleted();
                    //showShortToast(msg);
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                lmrc.setPullLoadMoreCompleted();
                showShortToast(message);
            }
        }));
    }

    private void reflushView() {
        if (popManData.size() > 0 && data.size() > 0) {
            hideWaitDialog();
            adapter.setHeaderData(adData, popManData);
            adapter.addData(data, lmrc);
            lmrc.setPullLoadMoreCompleted();
        }
    }

    @Override
    public void onClick(View v) {
        showWaitsDialog();
        NetRequest.postRequest(Constants.ServiceInfo.SIGN_ARRIVE_REQUEST, BaseApplication.token, null, SecurityCodeModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                showShortToast(msg);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }
}


