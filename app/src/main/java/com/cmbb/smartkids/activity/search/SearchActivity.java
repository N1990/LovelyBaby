package com.cmbb.smartkids.activity.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.cmbb.smartkids.activity.home.home.adapter.ServiceAdapter;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.activity.serve.view.ServerDetailActivity;
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.cmbb.smartkids.utils.log.Log;
import com.squareup.okhttp.Request;

public class SearchActivity extends BaseActivity implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {

    private static final String TAG = SearchActivity.class.getSimpleName();

    private Toolbar tlMainActionbar;
    private RelativeLayout rlLeft;
    private AppCompatSpinner spinnerSearch;
    private EditText etSearch;
    private AppCompatTextView tvSearch;
    private AppCompatTextView btnSearch;
    private SmartRecyclerView srv_search;
    private SearchTopicAdapter adapter_topic;
    private SearchUserAdapter adapter_user;
    private ServiceAdapter adapter_service;
    private SearchHotAdapter adapter_hot;
    private String search; //搜索文本

    private int pager_topic = 0;
    private int pager_user = 0;
    private int pager_service = 0;
    private int pagerSize = 10;

    private int type = 0;// 0:代表服务 1：代表用户 2：代表话题

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
        btnSearch = (AppCompatTextView) findViewById(R.id.btn_search);
        srv_search = (SmartRecyclerView) findViewById(R.id.srv_search);
        adapter_topic = new SearchTopicAdapter(this);
        adapter_topic.setMore(R.layout.view_more, this);
        adapter_topic.setNoMore(R.layout.view_nomore);
        adapter_topic.setOnItemClickListener(this);
        adapter_user = new SearchUserAdapter(this);
        adapter_user.setMore(R.layout.view_more, this);
        adapter_user.setNoMore(R.layout.view_nomore);
        adapter_user.setOnItemClickListener(this);
        adapter_service = new ServiceAdapter(this);
        adapter_service.setMore(R.layout.view_more, this);
        adapter_service.setNoMore(R.layout.view_nomore);
        adapter_service.setOnItemClickListener(this);
        adapter_hot = new SearchHotAdapter(this);
        srv_search.setRefreshListener(this);
        srv_search.setAdapterWithProgress(adapter_hot);
        srv_search.setRefreshing(false);
        adapter_hot.setOnItemClickListener(this);
        handleHotRequest();
    }

    private void addListener() {
        btnSearch.setOnClickListener(this);
        spinnerSearch.setOnItemSelectedListener(itemSelectedListener);
        etSearch.addTextChangedListener(textWatcher);
        etSearch.setOnEditorActionListener(onEditorListener);
    }

    @Override
    public void onItemClick(int position) {
        RecyclerView.Adapter adapter = srv_search.getAdapter();
        if (adapter instanceof SearchHotAdapter) { //热词adapter监听事件
            TopicTypeModel.DataEntity item = adapter_hot.getItem(position);
            search = item.getValue();
            etSearch.setText(search);
            srv_search.setRefreshing(true);
            pager_topic = 0;
            showWaitDialog();
            adapter_topic.clear();
            srv_search.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
            srv_search.setAdapter(adapter_topic);
            handleSearchTopic(pager_topic, pagerSize, search, true);
        } else if (adapter instanceof SearchTopicAdapter) { // 话题adapter监听事件
            TopicListModel.DataEntity.RowsEntity item = adapter_topic.getItem(position);
            int id = item.getId();
            Intent intent = new Intent(SearchActivity.this, CommunityDetailActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        } else if (adapter instanceof ServiceAdapter) { //服务adapter监听事件
            ServiceListModel.DataEntity.RowsEntity item = adapter_service.getItem(position);
            ServerDetailActivity.newIntent(this, item.getId());
        } else if (adapter instanceof SearchUserAdapter) { //用户adapter监听事件
            SearchUserModel.DataEntity.RowsEntity item = adapter_user.getItem(position);
            int id = item.getId();
            UserCenterActivity.newIntent(SearchActivity.this, id);
        }
    }

    @Override
    public void onLoadMore() {
        switch (type) {
            case 0:
                pager_service++;
                handleSearchServiceRequest(pager_service, pagerSize, search, false);
                break;
            case 1:
                pager_user++;
                handelSearchUserRequest(pager_user, pagerSize, search, false);
                break;
            case 2:
                pager_topic++;
                handleSearchTopic(pager_topic, pagerSize, search, false);
                break;
        }
    }

    @Override
    public void onRefresh() {
        switch (type) {
            case 0:
                pager_service = 0;
                handleSearchServiceRequest(pager_service, pagerSize, search, true);
                break;
            case 1:
                pager_user = 0;
                handelSearchUserRequest(pager_user, pagerSize, search, true);
                break;
            case 2:
                pager_topic = 0;
                handleSearchTopic(pager_topic, pagerSize, search, true);
                break;
        }
    }

    private TextView.OnEditorActionListener onEditorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // 先隐藏键盘
                ((InputMethodManager) etSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                srv_search.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                //后续操作
                search = etSearch.getText().toString();
                if (TextUtils.isEmpty(search)) {
                    showShortToast("请输入您的搜索内容~");
                    return true;
                }
                showWaitDialog();
                if (type == 0) {
                    pager_topic = 0;
                    srv_search.setAdapter(adapter_service);
                    onRefresh();
                } else if (type == 1) {
                    pager_user = 0;
                    srv_search.setAdapter(adapter_user);
                    onRefresh();
                } else if (type == 2) {
                    pager_service = 0;
                    srv_search.setAdapter(adapter_topic);
                    onRefresh();
                }
                return true;
            }
            return false;
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_search:
                // 先隐藏键盘
                ((InputMethodManager) etSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                srv_search.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                //后续操作
                search = etSearch.getText().toString();
                if (TextUtils.isEmpty(search)) {
                    showShortToast("请输入您的搜索内容~");
                    return;
                }
                showWaitDialog();
                if (type == 0) {
                    pager_topic = 0;
                    srv_search.setAdapter(adapter_service);
                    onRefresh();
                } else if (type == 1) {
                    pager_user = 0;
                    srv_search.setAdapter(adapter_user);
                    onRefresh();
                } else if (type == 2) {
                    pager_service = 0;
                    srv_search.setAdapter(adapter_topic);
                    onRefresh();
                }
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
            etSearch.setText("");
            adapter_topic.clear();
            adapter_user.clear();
            adapter_service.clear();
            adapter_hot.clear();
            switch (position) {
                case 0:
                    type = 0;
                    break;
                case 1:
                    type = 1;
                    break;
                case 2:
                    type = 2;
                    handleHotRequest();
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
            if (type == 2 && TextUtils.isEmpty(s.toString())) {
                srv_search.setLayoutManager(new GridLayoutManager(SearchActivity.this, 3));
                srv_search.setAdapter(adapter_hot);
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

    /**
     * 热词搜索
     */
    private void handleHotRequest() {
        showWaitDialog();
        srv_search.setRefreshing(false);
        srv_search.setLayoutManager(new GridLayoutManager(this, 3));
        TopicTypeModel.getSearchHotRequest(new OkHttpClientManager.ResultCallback<TopicTypeModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                hideWaitDialog();
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(TopicTypeModel response) {
                hideWaitDialog();
                if (response != null && response.getData() != null && response.getData().size() > 0) {
                    adapter_hot.addAll(response.getData());
                    srv_search.setAdapter(adapter_hot);// 默认Adapter
                }
            }
        });
    }

    /**
     * 搜索话题
     *
     * @param pager
     * @param pagerSize
     * @param text
     */
    private void handleSearchTopic(int pager, int pagerSize, String text, final boolean clear) {
        TopicListModel.getSearchTopicListRequest(text, pager, pagerSize, new OkHttpClientManager.ResultCallback<TopicListModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                hideWaitDialog();
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(TopicListModel response) {
                hideWaitDialog();
                if (response != null) {
                    if (clear)
                        adapter_hot.clear();
                    adapter_topic.addAll(response.getData().getRows());
                }
            }
        });
    }

    /**
     * 搜索用户
     *
     * @param pager
     * @param pagerSize
     * @param text
     */
    private void handelSearchUserRequest(int pager, int pagerSize, String text, final boolean clear) {
        SearchUserModel.getSearchUserRequest(text, pager, pagerSize, new OkHttpClientManager.ResultCallback<SearchUserModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                hideWaitDialog();
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(SearchUserModel response) {
                hideWaitDialog();
                if (response != null) {
                    if (clear)
                        adapter_user.clear();
                    adapter_user.addAll(response.getData().getRows());
                }
            }
        });
    }

    /**
     * 搜索服务
     *
     * @param pager
     * @param pagerSize
     * @param text
     */
    private void handleSearchServiceRequest(int pager, int pagerSize, String text, final boolean clear) {
        ServiceListModel.getSearchServiceRequest(text, pager, pagerSize, new OkHttpClientManager.ResultCallback<ServiceListModel>() {
            @Override
            public void onError(Request request, Exception e, String msg) {
                hideWaitDialog();
                if (TextUtils.isEmpty(msg)) {
                    showShortToast(getString(R.string.is_netwrok));
                } else {
                    showShortToast(msg);
                }
            }

            @Override
            public void onResponse(ServiceListModel response) {
                hideWaitDialog();
                if (response != null) {
                    if (clear)
                        adapter_service.clear();
                    adapter_service.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
}
