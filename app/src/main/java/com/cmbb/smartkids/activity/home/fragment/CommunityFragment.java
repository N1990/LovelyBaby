package com.cmbb.smartkids.activity.home.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.CommunityDetailActivity;
import com.cmbb.smartkids.activity.community.PublishCommunityActivity;
import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.community.model.TopicTypeModel;
import com.cmbb.smartkids.activity.home.adapter.CommunityAdapter;
import com.cmbb.smartkids.activity.home.adapter.TopicAdapter;
import com.cmbb.smartkids.activity.search.SearchActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.log.Log;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 10:44
 */
public class CommunityFragment extends BaseFragment {
    private final String TAG = CommunityFragment.class.getSimpleName();
    private final int COMMUNITY_DETAIL_REQUEST = 10101;
    private RelativeLayout rlTopic;
    private TextView tvTopic;
    private ImageView ivRight;
    private LoadMoreRecyclerView lmrv;
    private RecyclerView rvTopic;
    private NestedScrollView nsv;
    private CommunityAdapter adapter;
    private TopicAdapter topAdapter;
    private int doubleClick;
    private int pager = 0;
    private int pagerSize = 10;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_community, null);
        lmrv = (LoadMoreRecyclerView) v.findViewById(R.id.lmrv_home_community);
        rlTopic = (RelativeLayout) v.findViewById(R.id.rl_all_community_topic_tag);
        rvTopic = (RecyclerView) v.findViewById(R.id.rv_all_community_topic);
        tvTopic = (TextView) v.findViewById(R.id.tv_community_toolbar);
        ivRight = (ImageView) v.findViewById(R.id.iv_community_toolbar_right);
        nsv = (NestedScrollView) v.findViewById(R.id.nsv_home_community);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initActionBar();
        initData();
        addListener();
    }

    /**
     * 初始化actionbar
     */
    private void initActionBar() {
        try {
            Toolbar v = (Toolbar) getView().findViewById(R.id.tl_community_actionbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(v);
            ActionBar actionbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(false);
            actionbar.setDisplayShowTitleEnabled(false);
        } catch (Exception e) {
            Log.i(TAG, "actionbar is null");
        }
    }

    private void initData() {
        lmrv.setLinearLayout();
        adapter = new CommunityAdapter();
        adapter.setData(new ArrayList<TopicListModel.DataEntity.RowsEntity>());
        lmrv.setAdapter(adapter);
        topAdapter = new TopicAdapter();
        List<TopicTypeModel.DataEntity> data = new ArrayList<>();
        TopicTypeModel.DataEntity allTopic = new TopicTypeModel.DataEntity();
        allTopic.setName("全部话题");
        allTopic.setValue(null);
        data.add(allTopic);
        topAdapter.setData(data);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 3);
        rvTopic.setLayoutManager(manager);
        rvTopic.setAdapter(topAdapter);
        handleTopicTypeRequest();
        handleTopicListRequest();
    }

    private void addListener() {
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
        adapter.setOnItemListener(itemClick);
        adapter.setOnHeaderListener(onHeaderListener);
        tvTopic.setOnClickListener(this);
        ivRight.setOnClickListener(this);
        topAdapter.setOnItemListener(onTopListener);
    }

    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {

        }

        @Override
        public void onRefresh() {
            adapter.clearData();
            pager = 0;
            handleTopicListRequest();

        }

        @Override
        public void onLoadMore() {
            pager++;
            handleTopicListRequest();
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

    private View.OnClickListener onHeaderListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), SearchActivity.class));
        }
    };

    private CustomListener.ItemClickListener onTopListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            topAdapter.setSelectIndex(position);
            TopicTypeModel.DataEntity data = (TopicTypeModel.DataEntity) object;
            tvTopic.setText(data.getName());
            doubleClick++;
            rlTopic.setVisibility(View.GONE);
            pager = 0;
            adapter.clearData();
            showWaitsDialog();
            handleTopicListRequest();
        }
    };

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_community_toolbar:
                doubleClick++;
                if (doubleClick % 2 == 0) {
                    rlTopic.setVisibility(View.GONE);
                } else {
                    rlTopic.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_community_toolbar_right:
                Intent intent = new Intent(getActivity(), PublishCommunityActivity.class);
                startActivityForResult(intent, 11);
                break;
        }
    }

    public void addDoubleClick() {
        doubleClick++;
        if (doubleClick % 2 == 0) {
            rlTopic.setVisibility(View.GONE);
        }
    }


    /**
     * 获取话题类型
     */
    private void handleTopicTypeRequest() {
        showWaitsDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("typeCode", "topicType");
        NetRequest.postRequest(Constants.Community.TOPIC_TYPE, BaseApplication.token, params, TopicTypeModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                TopicTypeModel result = (TopicTypeModel) object;
                if (result.getData() != null && result.getData().size() > 0) {
                    topAdapter.addData(result.getData());
                    tvTopic.setText(topAdapter.getSelectData().getName());
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


    /**
     * 获取话题列表
     */
    private void handleTopicListRequest() {
        HashMap<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(topAdapter.getSelectData().getValue())) {
            params.put("topicType", topAdapter.getSelectData().getValue());
        }
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.Community.TOPIC_LIST, BaseApplication.token, params, TopicListModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                TopicListModel data = (TopicListModel) object;
                if (data != null && data.getData().getRows() != null && data.getData().getRows().size() > 0) {
                    lmrv.setHasContent();
                    adapter.addData(data.getData().getRows(), lmrv);
                }
                if (adapter.getDataSize() == 0)
                    lmrv.setNoContent();

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
            handleTopicListRequest();
        }
    }
}
