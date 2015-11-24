package com.cmbb.smartkids.activity.user.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.CommunityDetailActivity;
import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.user.adapter.MyCommunityAdapter;
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
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/12 19:15
 */
public class CommunityFragment extends BaseFragment {
    private final String TAG = MyCollectCommunityFragment.class.getSimpleName();
    private final int COMMUNITY_DETAIL_REQUEST = 10020;
    public LoadMoreRecyclerView lmrv;
    private NestedScrollView nsv;
    private MyCommunityAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;
    private String userId;
    private List<TopicListModel.DataEntity.RowsEntity> cacheList = new ArrayList<>();
    private int cachePager = -1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerview_layout, null);
        return root;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        addListener();
    }

    private void initView() {
        lmrv = (LoadMoreRecyclerView) getView().findViewById(R.id.lmrv_self);
        nsv = (NestedScrollView) getView().findViewById(R.id.nsv_self);
        lmrv.setLinearLayout();
        adapter = new MyCommunityAdapter();
        adapter.setData(cacheList);
        lmrv.setAdapter(adapter);
    }

    private void initData() {
        Bundle bundle = null;
        if((bundle = getArguments()) != null){
            userId = bundle.getString("userId");
            showWaitsDialog();
            handleCommunityListRequest();
        }else{
            showShortToast("传参出错~");
            return;
        }
    }

    private void addListener() {
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
        adapter.setOnItemListener(itemClick);
    }

    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {

        }

        @Override
        public void onRefresh() {
            adapter.clearData();
            pager = 0;
            handleCommunityListRequest();
        }

        @Override
        public void onLoadMore() {
            pager ++;
            handleCommunityListRequest();
        }
    };
    private CustomListener.ItemClickListener itemClick = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            TopicListModel.DataEntity.RowsEntity data = (TopicListModel.DataEntity.RowsEntity) object;
            Intent intent = new Intent(getActivity(), CommunityDetailActivity.class);
            intent.putExtra("id", data.getId());
            startActivityForResult(intent, COMMUNITY_DETAIL_REQUEST);
        }
    };

    /**
     * 获取话题收藏列表
     */
    private void handleCommunityListRequest() {
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", String.valueOf(pager));
        params.put("id", userId);
        params.put("myCenter", "0");
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.ServiceInfo.MY_COMMUNITY_LIST, BaseApplication.token, params, TopicListModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                TopicListModel data = (TopicListModel) object;
                if (data != null && data.getData().getRows() != null && data.getData().getRows().size() > 0 && cachePager != pager) {
                    lmrv.setVisibility(View.VISIBLE);
                    nsv.setVisibility(View.GONE);
                    cacheList.addAll(data.getData().getRows());
                    adapter.notifyDataSetChanged();
                }
                if (adapter.getDataSize() == 0){
                    lmrv.setVisibility(View.GONE);
                    nsv.setVisibility(View.VISIBLE);
                }
                showShortToast(msg);

            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                showShortToast(message);
            }
        }));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == COMMUNITY_DETAIL_REQUEST) {
            // 刷新页面
            adapter.clearData();
            pager = 0;
            showWaitsDialog();
            handleCommunityListRequest();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        cachePager = pager;
    }
}
