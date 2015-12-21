package com.cmbb.smartkids.activity.diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.adapter.BabyDiaryListAdapter;
import com.cmbb.smartkids.activity.diary.model.BabyDiaryListModel;
import com.cmbb.smartkids.activity.diary.model.BabyListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.utils.log.Log;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class BabyDiaryListActivity extends BaseActivity {
    private final String TAG = BabyDiaryListActivity.class.getSimpleName();
    private final int MODIFY_BABY_MATRIAL = 10112;
    private final int GO_BABY_DETAIL = 10113;
    private final int PUBLISH_BABY_DIARY = 10110;
    private LoadMoreRecyclerView lmrv;
    private BabyListModel.DataEntity.RowsEntity babyInfo;
    private boolean isModify;
    private int myCenter = -1;
    private int userId = -1;
    private BabyDiaryListAdapter adapter;
    private int pager = 0;
    private int pagerSize = 5;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_baby_diary_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView() {
        lmrv = (LoadMoreRecyclerView) findViewById(R.id.lmrv_baby_diary);
        lmrv.setLinearLayout();
        adapter = new BabyDiaryListAdapter();
    }

    private void initData() {
        Bundle bundle = null;
        if(getIntent() != null && (bundle = getIntent().getExtras()) != null){
            babyInfo = bundle.getParcelable("baby_model");
            myCenter = bundle.getInt("my_center");
            userId = bundle.getInt("user_id", -1);
            setTitle(babyInfo.getBabyNike());
            if("1".equals(babyInfo.getBabySex())){
                tvTitle.setCompoundDrawablesWithIntrinsicBounds(tvTitle.getContext().getResources().getDrawable(R.mipmap.btn_male_white_boy_bg), null, null, null);
            }else{
                tvTitle.setCompoundDrawablesWithIntrinsicBounds(tvTitle.getContext().getResources().getDrawable(R.mipmap.btn_male_white_girl_bg), null, null, null);
            }
            if(myCenter == 1)
                setBarRightImg(R.mipmap.btn_modify_baby_matrial);
            adapter.setHeaderData(babyInfo, userId);
            adapter.setData(new ArrayList<BabyDiaryListModel.DataEntity.RowsEntity>());
            lmrv.setAdapter(adapter);
            Log.e(TAG, babyInfo.toString());
        }else{
            showShortToast("传参出错啦~");
        }

    }

    private void addListener() {
        lmrv.setPullLoadMoreListener(new CustomPullLoadMoreListener());
        lmrv.setCanRefresh(false);
        lmrv.setInitializeWithoutPb();
        adapter.setOnFooterTryAgain(this);
        adapter.setOnPublishListener(onPublishListener);
        adapter.setOnItemListener(onItemListener);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent modify = new Intent(BabyDiaryListActivity.this, AddBabyActivity.class);
        modify.putExtra("baby_info", babyInfo);
        startActivityForResult(modify, MODIFY_BABY_MATRIAL);
    }


    private View.OnClickListener onPublishListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent publish = new Intent(BabyDiaryListActivity.this, PublishBabyDiaryActivity.class);
            publish.putExtra("baby_id", babyInfo.getId());
            startActivityForResult(publish, PUBLISH_BABY_DIARY);
        }
    };

    private CustomListener.ItemClickListener onItemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            BabyDiaryListModel.DataEntity.RowsEntity item = (BabyDiaryListModel.DataEntity.RowsEntity) object;
            Intent intentDetail = new Intent(BabyDiaryListActivity.this, BabyDiaryDetailActivity.class);
            intentDetail.putExtra("diary_id", item.getId());
            intentDetail.putExtra("my_center", myCenter);
            startActivityForResult(intentDetail, GO_BABY_DETAIL);

        }
    };

    class CustomPullLoadMoreListener implements LoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onInitialize() {
            handleRequest(pager, pagerSize);
        }

        @Override
        public void onRefresh() {

        }

        @Override
        public void onLoadMore() {
            pager++;
            handleRequest(pager, pagerSize);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MODIFY_BABY_MATRIAL && resultCode == RESULT_OK){
            babyInfo = data.getParcelableExtra("baby_info");
            adapter.setHeaderData(babyInfo, userId);
            if("1".equals(babyInfo.getBabySex())){
                setTitle(babyInfo.getBabyNike());
                tvTitle.setCompoundDrawablesWithIntrinsicBounds(tvTitle.getContext().getResources().getDrawable(R.mipmap.btn_male_white_boy_bg), null, null, null);
            }else{
                setTitle(babyInfo.getBabyNike());
                tvTitle.setCompoundDrawablesWithIntrinsicBounds(tvTitle.getContext().getResources().getDrawable(R.mipmap.btn_male_white_girl_bg), null, null, null);
            }
            isModify = true;
        }else if(requestCode == GO_BABY_DETAIL && resultCode == RESULT_OK){
            adapter.clearData();
            pager = 0;
            handleRequest(pager, pagerSize);
        } else if(requestCode == PUBLISH_BABY_DIARY && resultCode == RESULT_OK){
            adapter.clearData();
            pager = 0;
            handleRequest(pager, pagerSize);
        } else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(isModify){
                    setResult(RESULT_OK);
                    finish();
                }else{
                    onBackPressed();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleRequest(int pager, int pagerSize){
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("myCenter", String.valueOf(myCenter));
        params.put("id", String.valueOf(babyInfo.getId()));
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        if(myCenter != 1)
        params.put("userId", String.valueOf(userId));
        NetRequest.postRequest(Constants.ServiceInfo.BABY_DIARY_LIST, BaseApplication.token, params, BabyDiaryListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                BabyDiaryListModel data = (BabyDiaryListModel) object;
                if(data.getData() != null && data.getData().getRows() != null && data.getData().getRows().size() != 0){
                    adapter.addData(data.getData().getRows(), lmrv);
                }
                Log.e(TAG, "adapter size : " + adapter.getItemCount());
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
}
