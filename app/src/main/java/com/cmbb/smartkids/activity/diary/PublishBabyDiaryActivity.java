package com.cmbb.smartkids.activity.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.adapter.PublicCommunityAdapter;
import com.cmbb.smartkids.activity.community.model.ImageModel;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.utils.FullyLinearLayoutManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class PublishBabyDiaryActivity extends BaseActivity {
    private final String TAG = PublishBabyDiaryActivity.class.getSimpleName();
    private final int PIC_REQUEST_CODE = 1001;
    private final int CHOOSE_AUTHORITY_CODE = 1011;
    private RelativeLayout rlAuthority;
    private TextView tvAuthority;
    private EditText etImgDescri;
    private RecyclerView rv;
    private PublicCommunityAdapter adapter;
    private final int picNumber = 6;
    private int contentRealLen = 0;
    private int babyId = -1;
    private int privacy = 2;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_baby_diary;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv_public_baby_diary_img);
        rlAuthority = (RelativeLayout) findViewById(R.id.rl_publish_diary_authority);
        tvAuthority = (TextView) findViewById(R.id.tv_publish_diary_authority);
        etImgDescri = (EditText) findViewById(R.id.et_publish_baby_diary_img_describle);

    }

    private void initData() {
        setTitle(getString(R.string.title_activity_publish_baby_diary));
        setActionBarRight("提交");
        if(getIntent() != null){
            babyId = getIntent().getIntExtra("baby_id", -1);
            FullyLinearLayoutManager manager = new FullyLinearLayoutManager(this);
            manager.setSmoothScrollbarEnabled(true);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv.setLayoutManager(manager);

            adapter = new PublicCommunityAdapter();
            adapter.setData(new ArrayList<ImageModel>());
            rv.setAdapter(adapter);
        }else{
            showShortToast("传参出错啦~");
        }

    }

    private void addListener() {
        rlAuthority.setOnClickListener(this);
        adapter.setOnItemListener(onItemListener);
        adapter.setOnFootListener(onAddListener);
        adapter.setOnItemDeleteListener(onItemDeleteListener);
        adapter.setOnItemZoomListener(onItemZoomListener);
        etImgDescri.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("position", "position = " + adapter.getCurPos());
                try {
                    adapter.getData().get(adapter.getCurPos()).setContent(s.toString());
                } catch (IndexOutOfBoundsException e) {

                }
            }
        });
    }

    private CustomListener.ItemClickListener onItemListener = new CustomListener.ItemClickListener() {

        @Override
        public void onItemClick(View v, int position, Object object) {
            //更新上一个选中的对象
            if (adapter.getCurPos() < adapter.getItemCount() - 1) {
                adapter.updateData(adapter.getCurPos(), adapter.getData().get(adapter.getCurPos()));
            }
            adapter.setCurPos(position);

            ImageModel item = (ImageModel) object;
            if (!TextUtils.isEmpty(item.getContent())) {
                etImgDescri.setText(item.getContent());
            } else {
                etImgDescri.setText("");
            }
            etImgDescri.setVisibility(View.VISIBLE);
        }
    };


    private CustomListener.ItemClickListener onItemDeleteListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            adapter.removeData(position);
            if (adapter.getItemCount() - 1 == 0) {
                etImgDescri.setVisibility(View.GONE);
                adapter.setCurPos(0);
                etImgDescri.setText("");
            } else if (position == 0) {
                adapter.setCurPos(0);
                etImgDescri.setText(adapter.getData().get(adapter.getCurPos()).getContent());
            } else {
                adapter.setCurPos(position - 1);
                etImgDescri.setText(adapter.getData().get(adapter.getCurPos()).getContent());
            }
            adapter.notifyDataSetChanged();
        }
    };

    private View.OnClickListener onAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PhotoPickerIntent intent = new PhotoPickerIntent(PublishBabyDiaryActivity.this);
            intent.setPhotoCount(picNumber - adapter.getItemCount() + 1);
            startActivityForResult(intent, PIC_REQUEST_CODE);
        }
    };

    private CustomListener.ItemClickListener onItemZoomListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            ArrayList<ImageModel> data = (ArrayList<ImageModel>) object;
            ArrayList<String> imgs = new ArrayList<>();
            for (ImageModel item : data) {
                imgs.add(item.getImgUrl());
            }
            PhotoViewActivity.IntentPhotoView(v.getContext(), imgs, position);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PIC_REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> tempUrls = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                for (String str : tempUrls) {
                    ImageModel model = new ImageModel();
                    model.setImgUrl(str);
                    adapter.addData(model);
                }
                if (adapter.getItemCount() > 1 && adapter.getCurPos() == 0) {
                    etImgDescri.setVisibility(View.VISIBLE);
                }
            }
        }else if(requestCode == CHOOSE_AUTHORITY_CODE && resultCode == RESULT_OK){
            privacy = data.getIntExtra("privacy_value", 0);
            String privacyStr = data.getStringExtra("privacy_name");
            tvAuthority.setText(privacyStr);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_publish_diary_authority:
                Intent intent = new Intent(PublishBabyDiaryActivity.this, DiaryChooseModelActivity.class);
                intent.putExtra("privacy", privacy);
                startActivityForResult(intent, CHOOSE_AUTHORITY_CODE);
                break;
            case R.id.tv_main_toolbar_right:// 提交
                handlePublicRequest(adapter.getData());
                break;
        }
    }

    private void handlePublicRequest(ArrayList<ImageModel> files){
        HashMap<String, String> params = new HashMap<>();
        params.put("babyId", String.valueOf(babyId));
        params.put("privacy", String.valueOf(privacy));
        params.put("token", BaseApplication.token);
        showWaitDialog();
        NetRequest.postRequestWithFiles(Constants.ServiceInfo.PUBLISH_BABY_DIARY, params, "diaryImgList", "imgText", files, SecurityCodeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
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
                showShortToast(message);
            }
        }));
    }



}
