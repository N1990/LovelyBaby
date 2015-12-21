package com.cmbb.smartkids.activity.diary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.adapter.BabyDiaryDetailAdapter;
import com.cmbb.smartkids.activity.diary.model.BabyDiaryDetailModel;
import com.cmbb.smartkids.activity.home.model.ActiveDetailModel;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.widget.wheelview.CustomDialogBuilder;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class BabyDiaryDetailActivity extends BaseActivity {
    private final String TAG = BabyDiaryDetailActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private BabyDiaryDetailAdapter adapter;
    private CustomDialogBuilder builder;
    private ArrayList<String> imgs = new ArrayList<>(); //图片缓存
    private int diaryId;
    private int myCenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_baby_diary_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_baby_diary_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BabyDiaryDetailAdapter();
    }

    private void initData() {
        Bundle bundle = null;
        setTitle(getString(R.string.title_activity_baby_diary_detail));
        if(getIntent() != null && (bundle = getIntent().getExtras()) != null){
            diaryId = bundle.getInt("diary_id", -1);
            myCenter = bundle.getInt("my_center", -1);
            if(myCenter == 1)
                setBarRightImg(R.mipmap.btn_baby_diary_delete_bg);
            handleRequest();
        }else{
            showShortToast("传参出错啦~");
        }
    }

    private void addListener() {
        adapter.setItemImgListener(new CustomListener.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Object object) {
                PhotoViewActivity.IntentPhotoView(v.getContext(), imgs, position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        handleDeleteRequest();
    }

    private void showAlertDialog() {
        int width = TDevice.getScreenWidth(this) * 3 / 4;
        builder = CustomDialogBuilder.getInstance(this).setDialogWindows(width, ViewGroup.LayoutParams.WRAP_CONTENT)
                .isCancelableOnTouchOutside(false)
                .withTitle("删除日记")
                .withMessage("删除该日记之后不能还原,您确定要删除吗？")
                .withCancelText("取消", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                }).withComfirmText("确定", new CustomListener.DialogListener() {
                    @Override
                    public void onClick(View v) {
                        handleDeleteRequest();
                    }
                });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        if (builder != null && builder.isShowing()) {
            builder.dismiss();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (builder != null) {
            builder.setDialogDismiss();
        }
    }

    private void handleRequest(){
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(diaryId));
        NetRequest.postRequest(Constants.ServiceInfo.BABY_DIARY_DETAIL, BaseApplication.token, params, BabyDiaryDetailModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                BabyDiaryDetailModel data = (BabyDiaryDetailModel) object;
                if(data != null && data.getData() != null && data.getData().getBabyBasicInfo() != null){
                   /* if("1".equals(data.getData().getBabyBasicInfo().getBabySex())){
                        tvTitle.setCompoundDrawablesWithIntrinsicBounds(tvTitle.getContext().getResources().getDrawable(R.mipmap.btn_male_white_boy_bg), null, null, null);
                    }else{
                        tvTitle.setCompoundDrawablesWithIntrinsicBounds(tvTitle.getContext().getResources().getDrawable(R.mipmap.btn_male_white_girl_bg), null, null, null);
                    }*/
                    for (BabyDiaryDetailModel.DataEntity.DiaryImgEntity item : data.getData().getDiaryImg()){
                        imgs.add(item.getImg());
                    }
                    adapter.setData(data.getData().getBabyBasicInfo(), data.getData().getDiaryImg(), data.getData().getDiaryDate());
                    recyclerView.setAdapter(adapter);
                }
                showShortToast(msg);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

    private void handleDeleteRequest(){
        showWaitDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(diaryId));
        params.put("isDelete", "1");
        NetRequest.postRequest(Constants.ServiceInfo.DELETE_BABY_DIARY, BaseApplication.token, params, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                showShortToast(msg);
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showWaitDialog(message);
            }
        }));

    }


}
