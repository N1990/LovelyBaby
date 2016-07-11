package com.cmbb.smartkids.activity.diary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.adapter.BabyListAdapter;
import com.cmbb.smartkids.activity.diary.model.BabyListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.mengbottomsheets.BottomSheet;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/7/11 下午4:23
 * 修改人：N.Sun
 * 修改时间：16/7/11 下午4:23
 * 修改备注：
 */
public class BabyListActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {

    private static final String TAG = BabyListActivity.class.getSimpleName();

    private final int BABY_HANDLER = 10111;

    protected SmartRecyclerView mSmartRecyclerView;
    protected BabyListAdapter adapter;
    private BottomSheet sheet;

    private int pager = 0;
    private int pagerSize = 10;

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_my_baby_diary));
        initRecyclerView();
        onRefresh();
    }

    private void initRecyclerView() {
        mSmartRecyclerView = (SmartRecyclerView) findViewById(R.id.recyclerView);
        mSmartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BabyListAdapter(this);
        mSmartRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, this);
        //        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemClick(final int position) {
                sheet = new BottomSheet.Builder(BabyListActivity.this).title("删除宝宝").sheet(R.menu.menu_longlist_bs).listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BabyListModel.deleteBabyRequest(adapter.getItem(position).getId(), new OkHttpClientManager.ResultCallback<BabyListModel>() {
                            @Override
                            public void onError(Request request, Exception e, String response) {
                                if (TextUtils.isEmpty(response)) {
                                    showToast(getString(R.string.err_network));
                                    Log.e("err", e.toString());
                                } else {
                                    showToast(response);
                                }
                            }

                            @Override
                            public void onResponse(BabyListModel response) {
                                if (response != null) {
                                    showToast(response.getMsg());
                                }
                            }
                        });
                    }
                }).limit(R.integer.bs_initial_list_row).build();
                sheet.show();
                return true;
            }
        });
        mSmartRecyclerView.setRefreshListener(this);
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                LinearLayout header = (LinearLayout) LayoutInflater.from(BabyListActivity.this).inflate(R.layout.list_baby_footer, null);
                header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return header;
            }

            @Override
            public void onBindView(View headerView) {
                headerView.findViewById(R.id.tv_my_baby_diary_footer).setOnClickListener(BabyListActivity.this);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_baby_list_layout;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_my_baby_diary_footer:
                Intent intent = new Intent(this, AddBabyActivity.class);
                startActivityForResult(intent, BABY_HANDLER);
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, BabyDiaryListActivity.class);
        intent.putExtra("baby_model", adapter.getItem(position));
        intent.putExtra("my_center", 1);
        startActivityForResult(intent, BABY_HANDLER);
    }

    @Override
    public void onLoadMore() {
        pager++;
        BabyListModel.getBabyListRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<BabyListModel>() {
            @Override
            public void onError(Request request, Exception e, String response) {
                if (TextUtils.isEmpty(response)) {
                    showToast(getString(R.string.err_network));
                    Log.e("err", e.toString());
                } else {
                    showToast(response);
                }
            }

            @Override
            public void onResponse(BabyListModel response) {
                if (response != null) {
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pager = 0;
        BabyListModel.getBabyListRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<BabyListModel>() {
            @Override
            public void onError(Request request, Exception e, String response) {
                if (TextUtils.isEmpty(response)) {
                    showToast(getString(R.string.err_network));
                    Log.e("err", e.toString());
                } else {
                    showToast(response);
                }
            }

            @Override
            public void onResponse(BabyListModel response) {
                if (response != null) {
                    adapter.clear();
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BABY_HANDLER && resultCode == RESULT_OK) {
            onRefresh();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
