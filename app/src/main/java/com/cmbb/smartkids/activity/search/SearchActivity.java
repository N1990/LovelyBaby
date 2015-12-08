package com.cmbb.smartkids.activity.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.CommunityDetailActivity;
import com.cmbb.smartkids.activity.community.model.TopicListModel;
import com.cmbb.smartkids.activity.community.model.TopicTypeModel;
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.log.Log;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends BaseActivity {

    private static final String TAG = SearchActivity.class.getSimpleName();

    private Toolbar tlMainActionbar;
    private RelativeLayout rlLeft;
    private AppCompatSpinner spinnerSearch;
    private EditText etSearch;
    private AppCompatTextView tvSearch;
    private AppCompatTextView tvCancel;
    private LoadMoreRecyclerView lmrlSearch;
    private SearchTopicAdapter adapter_topic;
    private SearchUserAdapter adapter_user;
    private SearchHotAdapter adapter_hot;
    private String search; //搜索文本

    private int pager_topic = 0;
    private int pager_user = 0;
    private int pagerSize = 10;

    private int type = 0;//

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        addListener();
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tlMainActionbar = (Toolbar) findViewById(R.id.tl_main_actionbar);
        rlLeft = (RelativeLayout) findViewById(R.id.rl_left);
        spinnerSearch = (AppCompatSpinner) findViewById(R.id.spinner_search);
        etSearch = (EditText) findViewById(R.id.et_search);
        tvSearch = (AppCompatTextView) findViewById(R.id.tv_search);
        tvCancel = (AppCompatTextView) findViewById(R.id.tv_cancel);
        lmrlSearch = (LoadMoreRecyclerView) findViewById(R.id.lmrl_search);
        lmrlSearch.setPullLoadMoreListener(lmrvListener);
        lmrlSearch.setCanRefresh(false);
        adapter_topic = new SearchTopicAdapter();
        adapter_topic.setData(new ArrayList<TopicListModel.DataEntity.RowsEntity>());
        adapter_user = new SearchUserAdapter();
        adapter_user.setData(new ArrayList<SearchUserModel.DataEntity.RowsEntity>());
        adapter_hot = new SearchHotAdapter();
        handleHotRequest();
    }


    private void addListener() {
        tvCancel.setOnClickListener(this);
        spinnerSearch.setOnItemSelectedListener(itemSelectedListener);
        etSearch.addTextChangedListener(textWatcher);
        adapter_hot.setOnItemListener(onHotItemListener);
        adapter_topic.setOnItemListener(onTopicItemListener);
        adapter_user.setOnItemListener(onUserItemListener);
        etSearch.setOnEditorActionListener(onEditorListener);
    }

    /**
     * 热词adapter监听事件
     */
    private CustomListener.ItemClickListener onHotItemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            TopicTypeModel.DataEntity item = (TopicTypeModel.DataEntity) object;
            search = item.getValue();
            etSearch.setText(search);
            lmrlSearch.setPullRefreshEnable(true);
            pager_topic = 0;
            showWaitDialog();
            adapter_topic.clearData();
            lmrlSearch.setLinearLayout();
            lmrlSearch.setAdapter(adapter_topic);
            handleSearchTopic(pager_topic, pagerSize, search);
        }
    };

    /**
     * 话题adapter监听事件
     */
    private CustomListener.ItemClickListener onTopicItemListener = new CustomListener.ItemClickListener() {

        @Override
        public void onItemClick(View v, int position, Object object) {
            TopicListModel.DataEntity.RowsEntity item = (TopicListModel.DataEntity.RowsEntity) object;
            int id = item.getId();
            Intent intent = new Intent(SearchActivity.this, CommunityDetailActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    };

    /**
     * 用户adapter监听事件
     */
    private CustomListener.ItemClickListener onUserItemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            SearchUserModel.DataEntity.RowsEntity item = (SearchUserModel.DataEntity.RowsEntity) object;
            int id = item.getId();
            Intent intent = new Intent(SearchActivity.this, UserCenterActivity.class);
            intent.putExtra("userId", id);
            startActivity(intent);
        }
    };


    private TextView.OnEditorActionListener onEditorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            Log.i(TAG, "onEditorAction ----------- actionId:" + actionId);
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // 先隐藏键盘
                ((InputMethodManager) etSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                lmrlSearch.setLinearLayout();
                //后续操作
                search = etSearch.getText().toString();
                if (TextUtils.isEmpty(search)) {
                    showShortToast("请输入您的搜索内容~");
                    return true;
                }
                showWaitDialog();
                if (type == 0) {
                    pager_topic = 0;
                    adapter_topic.clearData();
                    lmrlSearch.setAdapter(adapter_topic);
                    handleSearchTopic(pager_topic, pagerSize, search);
                } else {
                    pager_user = 0;
                    lmrlSearch.setAdapter(adapter_user);
                    adapter_user.clearData();
                    handelSearchUserRequest(pager_user, pagerSize, search);
                }
                showShortToast("开始搜索...");
                return true;
            }
            return false;
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_cancel:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!TextUtils.isEmpty(search))
            etSearch.setText(search);
    }

    private AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.e(TAG, "i come here" + position);
            adapter_topic.clearData();
            adapter_user.clearData();
            adapter_hot.clearData();
            switch (position) {
                case 0:
                    type = 0;
                    handleHotRequest();
                    break;
                case 1:
                    type = 1;
                    break;

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Log.e("spinner", "spinner = " + type);
        }
    };

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            if (type == 0 && TextUtils.isEmpty(s.toString())) {
                Log.e("watcher", "watcher = " + type);
                lmrlSearch.setGridRow(3);
                lmrlSearch.setGridLayout();
                lmrlSearch.setAdapter(adapter_hot);
            }

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.e("TextWatcher", "beforeTextChanged = " + s.toString());
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }


    };

    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {


        }

        @Override
        public void onRefresh() {
            switch (type) {
                case 0:
                    pager_topic = 0;
                    adapter_hot.clearData();
                    handleSearchTopic(pager_topic, pagerSize, search);
                    break;
                case 1:
                    pager_user = 0;
                    adapter_user.clearData();
                    handelSearchUserRequest(pager_user, pagerSize, search);
                    break;
            }

        }

        @Override
        public void onLoadMore() {

            switch (type) {
                case 0:
                    pager_topic++;
                    handleSearchTopic(pager_topic, pagerSize, search);
                    break;
                case 1:
                    pager_user++;
                    handelSearchUserRequest(pager_user, pagerSize, search);
                    break;

            }
        }
    };


    /**
     * 热词搜索
     */
    private void handleHotRequest() {
        showWaitDialog();
        lmrlSearch.setPullRefreshEnable(false);
        lmrlSearch.setGridRow(3);
        lmrlSearch.setGridLayout();
        HashMap<String, String> params = new HashMap<>();
        params.put("typeCode", "topicHotWords");
        NetRequest.postRequest(Constants.ServiceInfo.SMARTS_SORT_REQUEST, BaseApplication.token, params, TopicTypeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                TopicTypeModel result = (TopicTypeModel) object;
                if (result != null && result.getData() != null && result.getData().size() > 0) {
                    lmrlSearch.setHasContent();
                    adapter_hot.setData(result.getData());
                    lmrlSearch.setAdapter(adapter_hot);// 默认Adapter
                }
                if (adapter_hot.getDataSize() == 0)
                    lmrlSearch.setNoContent();
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
     * 搜索话题
     *
     * @param pager
     * @param pagerSize
     * @param text
     */
    private void handleSearchTopic(int pager, int pagerSize, String text) {
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("contents", text);
        NetRequest.postRequest(Constants.Community.TOPIC_LIST, BaseApplication.token, params, TopicListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrlSearch.setPullLoadMoreCompleted();
                TopicListModel data = (TopicListModel) object;
                if (data != null && data.getData().getRows() != null && data.getData().getRows().size() > 0) {
                    lmrlSearch.setHasContent();
                    adapter_topic.addData(data.getData().getRows(), lmrlSearch);
                }
                if (adapter_topic.getDataSize() == 0)
                    lmrlSearch.setNoContent();

            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                lmrlSearch.setPullLoadMoreCompleted();
                showShortToast(message);
            }
        }));
    }


    /**
     * 搜索用户
     *
     * @param pager
     * @param pagerSize
     * @param text
     */
    private void handelSearchUserRequest(int pager, int pagerSize, String text) {
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("userNike", text);
        NetRequest.postRequest(Constants.ServiceInfo.SEARCH_USER_REQUEST, BaseApplication.token, params, SearchUserModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrlSearch.setPullLoadMoreCompleted();
                SearchUserModel result = (SearchUserModel) object;
                if (result != null && result.getData() != null && result.getData().getRows() != null) {
                    lmrlSearch.setHasContent();
                    adapter_user.addData(result.getData().getRows(), lmrlSearch);
                }
                if (adapter_user.getDataSize() == 0)
                    lmrlSearch.setNoContent();
                showWaitDialog(msg);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                lmrlSearch.setPullLoadMoreCompleted();
                showShortToast(message);

            }
        }));

    }


}
