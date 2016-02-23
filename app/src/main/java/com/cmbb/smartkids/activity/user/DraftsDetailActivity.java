package com.cmbb.smartkids.activity.user;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.adapter.PublicCommunityAdapter;
import com.cmbb.smartkids.activity.community.model.ImageModel;
import com.cmbb.smartkids.activity.community.model.TopicTypeModel;
import com.cmbb.smartkids.activity.serve.model.ServiceListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.db.DBContent;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.utils.FullyLinearLayoutManager;
import com.cmbb.smartkids.widget.spinner.NiceSpinner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DraftsDetailActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private final String TAG = DraftsDetailActivity.class.getSimpleName();
    private final int PIC_REQUEST_CODE = 1001;
    private EditText etTitle, etContent;
    private TextView tvLimit;
    private EditText etImgDescri;
    private ImageView ivTitleClear;
    private NiceSpinner ns;
    private RecyclerView rv;
    private PublicCommunityAdapter adapter;
    private final int picNumber = 10;
    private int contentRealLen = 0;

    private List<String> topic_type_name = new ArrayList<>();
    private List<String> topic_type_value = new ArrayList<>();
    private String type_value;
    private String type;
    private int topicId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_community;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        setTitle(getString(R.string.title_activity_public_community));
        topicId = getIntent().getIntExtra("topic_id", -1);
        Log.e("cursor", "topicId  = " + topicId);
        getSupportLoaderManager().initLoader(0, null, this);
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_public_community));
        setActionBarRight("提交");
        etTitle = (EditText) findViewById(R.id.et_public_community_title);
        ivTitleClear = (ImageView) findViewById(R.id.iv_public_community_title_clear);
        etContent = (EditText) findViewById(R.id.et_public_community_cotent);
        tvLimit = (TextView) findViewById(R.id.tv_public_community_content_limit);
        etImgDescri = (EditText) findViewById(R.id.et_public_community_img_describle);
        ns = (NiceSpinner) findViewById(R.id.nv_public_community);
        rv = (RecyclerView) findViewById(R.id.rv_public_community_img);

        FullyLinearLayoutManager manager = new FullyLinearLayoutManager(this);
        manager.setSmoothScrollbarEnabled(true);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(manager);

        adapter = new PublicCommunityAdapter();
        adapter.setData(new ArrayList<ImageModel>());
        rv.setAdapter(adapter);
    }


    private void addListener() {
        adapter.setOnItemListener(onItemListener);
        adapter.setOnFootListener(onAddListener);
        adapter.setOnItemDeleteListener(onItemDeleteListener);
        adapter.setOnItemZoomListener(onItemZoomListener);
        ns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type_value = topic_type_value.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                contentRealLen = s.length();
                if (contentRealLen <= 450) {
                    tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    tvLimit.setText(contentRealLen + "/500");
                } else if (contentRealLen < 500 && contentRealLen > 450) {
                    tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    tvLimit.setText("还剩余" + (500 - contentRealLen) + "个");
                } else if (contentRealLen == 500) {
                    tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    tvLimit.setText("文字已输满");
                } else {
                    tvLimit.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    tvLimit.setText("超过规定字数" + (contentRealLen - 500) + "个");
                }
            }
        });

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
            PhotoPickerIntent intent = new PhotoPickerIntent(DraftsDetailActivity.this);
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
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_main_toolbar_right:// 提交
                String title = etTitle.getText().toString().trim();
                String content = etContent.getText().toString();
                handlePublicRequest(title, type_value, content, adapter.getData());
                break;
        }
    }


    //==============

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, DBContent.DBTopic.CONTENT_URI, null, "_id = " + topicId, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // 获取草稿箱内容
        if (data != null && data.getCount() > 0 && data.moveToFirst()) {
            String title = data.getString(data.getColumnIndex(DBContent.DBTopic.TOPIC_TITLE));
            if (!TextUtils.isEmpty(title)) {
                etTitle.setText(title);
            }
            String sort = data.getString(data.getColumnIndex(DBContent.DBTopic.TOPIC_SORT));
            String sortValue = data.getString(data.getColumnIndex(DBContent.DBTopic.TOPIC_SORT_VALUE));
            if (!TextUtils.isEmpty(sort)) {
                ns.setText(sort);
                type = sort;
                type_value = sortValue;

            }
            String content = data.getString(data.getColumnIndex(DBContent.DBTopic.TOPIC_CONTENT));
            if (!TextUtils.isEmpty(content)) {
                etContent.setText(content);
            }

            String teletextStr = data.getString(data.getColumnIndex(DBContent.DBTopic.TOPIC_TELETEXTS));
            if (!TextUtils.isEmpty(teletextStr)) {
                Gson gson = new Gson();
                ArrayList<ImageModel> realImgs = new ArrayList<>();
                ArrayList<ImageModel> imageModels = gson.fromJson(teletextStr, new TypeToken<ArrayList<ImageModel>>() {
                }.getType());
                for (ImageModel img : imageModels) {
                    File file = new File(img.getImgUrl());
                    if (file.exists())
                        realImgs.add(img);
                }
                if (realImgs.size() > 0) {
                    etImgDescri.setVisibility(View.VISIBLE);
                    adapter.setData(realImgs);
                    if (!TextUtils.isEmpty(realImgs.get(0).getContent())) {
                        etImgDescri.setText(realImgs.get(0).getContent());
                    }
                }
            }
            handleTopicTypeRequest();
            addListener();
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onBackPressed() {
        ContentValues values = new ContentValues();
        values.put(DBContent.DBTopic.TOPIC_TITLE, etTitle.getText().toString());
        values.put(DBContent.DBTopic.TOPIC_SORT, ns.getText().toString());
        values.put(DBContent.DBTopic.TOPIC_SORT_VALUE, type_value);
        values.put(DBContent.DBTopic.TOPIC_CONTENT, etContent.getText().toString());
        if (adapter.getData() != null) {
            Gson gson = new Gson();
            String images = gson.toJson(adapter.getData());
            values.put(DBContent.DBTopic.TOPIC_TELETEXTS, images);
        } else {
            values.put(DBContent.DBTopic.TOPIC_TELETEXTS, "");
        }
        getContentResolver().update(ContentUris.withAppendedId(DBContent.DBTopic.CONTENT_URI, topicId), values, null, null);
        finish();
    }

    /**
     * 获取话题类型
     */
    private void handleTopicTypeRequest() {
        HashMap<String, String> params = new HashMap<>();
        params.put("typeCode", "topicType");
        showWaitDialog();
        NetRequest.postRequest(Constants.Community.TOPIC_PUBLISH_TYPE, BaseApplication.token, params, TopicTypeModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                TopicTypeModel result = (TopicTypeModel) object;
                if (result.getData().size() > 0) {
                    for (TopicTypeModel.DataEntity dataEntity : result.getData()) {
                        topic_type_name.add(dataEntity.getName());
                        topic_type_value.add(dataEntity.getValue());
                    }
                    ns.attachDataSource(topic_type_name);
                    // 初始值
                    if (!TextUtils.isEmpty(type)) {
                        ns.setText(type);
                    } else {
                        type_value = topic_type_value.get(0);
                    }
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
     * 发布话题
     *
     * @param title
     * @param topicType
     * @param contents
     */
    private void handlePublicRequest(String title, String topicType, String contents, ArrayList<ImageModel> files) {
        if (TextUtils.isEmpty(topicType)) {
            showShortToast("请选择话题类型");
            return;
        }

        if (TextUtils.isEmpty(title)) {
            showShortToast("请输入标题");
            return;
        }

        if (contentRealLen > 500) {
            showShortToast("话题的内容不超过500个字");
            return;
        }

        if (TextUtils.isEmpty(contents) && (null == files || files.size() == 0)) {
            showShortToast("请输入内容或者图片");
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("topicType", topicType);
        params.put("contents", contents);
        params.put("token", BaseApplication.token);
        showWaitDialog();
        NetRequest.postRequestWithFiles(Constants.Community.TOPIC_PUBLISH, params, "topicImgList", "imgText", files, ServiceListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                showShortToast(msg);
                getContentResolver().delete(ContentUris.withAppendedId(DBContent.DBTopic.CONTENT_URI, topicId), null, null);
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
