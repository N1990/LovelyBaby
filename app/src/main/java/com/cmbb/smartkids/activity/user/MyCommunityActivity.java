package com.cmbb.smartkids.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.CommunityDetailActivity;
import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.home.adapter.CommunityAdapter;
import com.cmbb.smartkids.activity.user.adapter.MyCommunityAdapter;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyCommunityActivity extends BaseActivity {
    private final String TAG = MyCommunityActivity.class.getSimpleName();
    private final int COMMUNITY_DETAIL_REQUEST = 10102;
    private LoadMoreRecyclerView lmrv;
    private MyCommunityAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_community;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        addListener();
    }

    private void initView(){
        setTitle(getString(R.string.title_activity_my_community));
        lmrv = (LoadMoreRecyclerView) findViewById(R.id.lmrv_my_community);
        lmrv.setLinearLayout();
        adapter = new MyCommunityAdapter();
        adapter.setData(new ArrayList<TopicListModel.DataEntity.RowsEntity>());
        lmrv.setAdapter(adapter);
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
            showWaitDialog();
            handleTopicListRequest();
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
            Intent intent = new Intent(MyCommunityActivity.this, CommunityDetailActivity.class);
            intent.putExtra("id", data.getId());
            startActivityForResult(intent, COMMUNITY_DETAIL_REQUEST);
        }
    };

    /**
     * 获取话题列表
     */
    private void handleTopicListRequest() {
        HashMap<String, String> params = new HashMap<>();
        params.put("myCenter", "1");
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        NetRequest.postRequest(Constants.ServiceInfo.MY_COMMUNITY_LIST, BaseApplication.token, params, TopicListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
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
            showWaitDialog();
            handleTopicListRequest();
        }
    }


}
