package com.cmbb.smartkids.activity.diary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.adapter.BabyDiaryAdapter;
import com.cmbb.smartkids.activity.diary.model.BabyListModel;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.mengbottomsheets.BottomSheet;
import com.cmbb.smartkids.network.NetRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class MyBabyListActivity extends BaseActivity {
    private final String TAG = MyBabyListActivity.class.getSimpleName();
    private final int BABY_HANDLER = 10111;
    private RecyclerView rv;
    private BabyDiaryAdapter adapter;
    private BottomSheet sheet;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_baby_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_my_baby_diary));
        initView();
        addListener();
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv_my_baby_diary);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BabyDiaryAdapter();
        adapter.setData(new ArrayList<BabyListModel.DataEntity.RowsEntity>());
        rv.setAdapter(adapter);
        handleRequest();
    }


    private void addListener() {
        adapter.setOnFooterListener(footerListener);
        adapter.setOnItemListener(itemListener);
        adapter.setOnLongItemListener(itemLongListener);
    }

    private View.OnClickListener footerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MyBabyListActivity.this, AddBabyActivity.class);
            startActivityForResult(intent, BABY_HANDLER);
        }
    };

    private CustomListener.ItemClickListener itemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            BabyListModel.DataEntity.RowsEntity item = (BabyListModel.DataEntity.RowsEntity) object;
            Intent intent = new Intent(MyBabyListActivity.this, BabyDiaryListActivity.class);
            intent.putExtra("baby_model", item);
            intent.putExtra("my_center", 1);
            startActivityForResult(intent, BABY_HANDLER);

        }
    };

    private CustomListener.ItemClickListener itemLongListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, final int position, Object object) {
            final BabyListModel.DataEntity.RowsEntity item = (BabyListModel.DataEntity.RowsEntity) object;
            sheet = new BottomSheet.Builder(MyBabyListActivity.this).title("删除宝宝").sheet(R.menu.menu_longlist_bs).listener(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handleDeleteRequest(item.getId()+"", position);
                }
            }).limit(R.integer.bs_initial_list_row).build();
            sheet.show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == BABY_HANDLER && resultCode == RESULT_OK){
            handleRequest();
        } else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void handleRequest(){
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("myCenter", "1");
        NetRequest.postRequest(Constants.ServiceInfo.BABY_LIST, BaseApplication.token, params, BabyListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                BabyListModel data = (BabyListModel) object;
                if(data.getData() != null && data.getData().getRows() != null)
                    adapter.setData(data.getData().getRows());
                showShortToast(msg);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

    private void handleDeleteRequest(String babyId, final int position){
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("id",babyId);
        params.put("isDelete", "1");
        NetRequest.postRequest(Constants.ServiceInfo.BABY_DELETE, BaseApplication.token, params, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                adapter.removeData(position);
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
