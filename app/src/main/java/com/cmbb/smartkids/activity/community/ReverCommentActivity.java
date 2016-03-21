package com.cmbb.smartkids.activity.community;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.media.upload.UploadListener;
import com.alibaba.sdk.android.media.upload.UploadTask;
import com.alibaba.sdk.android.media.utils.FailReason;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.adapter.ReverCommentAdapter;
import com.cmbb.smartkids.activity.community.model.CommunityReplayModel;
import com.cmbb.smartkids.activity.community.model.ReplayDetailListModel;
import com.cmbb.smartkids.activity.community.model.ReplayDetailModel;
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.network.image.ImageUpload;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.log.Log;
import com.cmbb.smartkids.widget.wheelview.CustomDialogBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReverCommentActivity extends BaseActivity {

    private EditText etRever;
    private RelativeLayout root, rlBottomRever;
    private TextView tvSend;
    private SimpleDraweeView ivImage;
    private LoadMoreRecyclerView lmrv;
    private ReverCommentAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;
    private int replayId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rever_comment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_rever_comment));
        initView();
        initData();
        addListener();
    }


    private void initView() {
        ivImage = (SimpleDraweeView) findViewById(R.id.iv_community_comment_rever_upload);
        root = (RelativeLayout) findViewById(R.id.rl_rever_root);
        rlBottomRever = (RelativeLayout) findViewById(R.id.rl_community_comment_rever);
        etRever = (EditText) findViewById(R.id.et_comment_rever);
        tvSend = (TextView) findViewById(R.id.tv_community_comment_rever_publish);
        lmrv = (LoadMoreRecyclerView) findViewById(R.id.rv_comment_rever);
        lmrv.setLinearLayout();
        adapter = new ReverCommentAdapter();
        lmrv.setAdapter(adapter);
    }


    private void initData() {
        replayId = getIntent().getIntExtra("replayId", -1);
    }


    private void addListener() {
        lmrv.setPullLoadMoreListener(new CustomPullLoadMoreListener());
        lmrv.setInitializeWithoutPb();
        adapter.setOnHeaderListener(onCommentHeaderListener);
        adapter.setOnReverListener(onReverListener);
        adapter.setOnMoreListener(onMoreListener);
        adapter.setOnReverPreListener(onReverPreListener);
        adapter.setOnDeleteListener(onReverDelListener);
        adapter.setOnPreListener(onCommentPreListener);
        adapter.setOnEveryListener(onEveryListener);
        etRever.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    etRever.setMinHeight(TDevice.dip2px(100, ReverCommentActivity.this));
                    rlBottomRever.setVisibility(View.VISIBLE);
                } else {
                    etRever.setMinHeight(TDevice.dip2px(36, ReverCommentActivity.this));
                    rlBottomRever.setVisibility(View.GONE);
                }
            }
        });

        tvSend.setOnClickListener(this);
        ivImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_community_comment_rever_upload://选取图片
                PhotoPickerIntent intent = new PhotoPickerIntent(this);
                intent.setPhotoCount(1);
                startActivityForResult(intent, PIC_REQUEST_CODE);
                break;
            case R.id.tv_community_comment_rever_publish:
                // 发送评论
                if (TextUtils.isEmpty(etRever.getText().toString()) && (tempImages == null || tempImages.size() == 0)) {
                    showShortToast("请输入回复内容");
                } else if (tempImages.size() > 0) {
                    showWaitDialog();
                    ImageUpload.getInstance().uploadImages(this, tempImages, new UploadListener() {
                        @Override
                        public void onUploading(UploadTask uploadTask) {

                        }

                        @Override
                        public void onUploadFailed(UploadTask uploadTask, FailReason failReason) {
                            hideWaitDialog();
                            showShortToast(failReason.getMessage());
                        }

                        @Override
                        public void onUploadComplete(UploadTask uploadTask) {
                            try {
                                JSONObject object = new JSONObject(uploadTask.getResult().message);
                                String url = object.optString("url");
                                String width = object.optString("returnBody").split("_")[0];
                                String height = object.optString("returnBody").split("_")[1];
                                handleSendReportRequest(replayId, etRever.getText().toString(), url, width, height);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onUploadCancelled(UploadTask uploadTask) {

                        }
                    });
                } else {
                    handleSendReportRequest(replayId, etRever.getText().toString(), null, null, null);
                }

                break;
        }
    }

    /**
     * 取消输入框
     */
    private View.OnClickListener onEveryListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            etRever.clearFocus();
        }
    };

    /**
     * 评论用户头像
     */
    private View.OnClickListener onCommentHeaderListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int userId = (int) v.getTag();
            UserCenterActivity.newIntent(ReverCommentActivity.this, userId);
        }
    };


    /**
     * 评论回复
     */
    private View.OnClickListener onReverListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showShortToast("回复你的评论~");
        }
    };


    CustomDialogBuilder builder;

    /**
     * 评论更多
     */
    private View.OnClickListener onMoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final ReplayDetailModel data = (ReplayDetailModel) v.getTag();
            switch (data.getData().getUserBasicInfo().getIsLoginUser()) {
                case 1:// delete
                    builder = CustomDialogBuilder.getInstance(ReverCommentActivity.this).withTitle("操作")
                            .withMessage("您确认要删除您的回复吗？")
                            .withComfirmText("确认", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.setDialogDismiss();
                                    handleDeleteReplayRequest(data.getData().getId());
                                }
                            })
                            .withCancelText("取消", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.setDialogDismiss();
                                }
                            });
                    builder.show();

                    break;
                case 0://
                    // report
                    builder = CustomDialogBuilder.getInstance(ReverCommentActivity.this).withTitle("操作")
                            .withMessage("您确认要举报此回复吗？")
                            .withComfirmText("确认", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.setDialogDismiss();
                                    handleReportReplayRequest(data.getData().getId());
                                }
                            })
                            .withCancelText("取消", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.setDialogDismiss();
                                }
                            });
                    builder.show();
                    break;
            }
        }
    };


    /**
     * 评论图片预览
     */
    private View.OnClickListener onCommentPreListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            String image = (String) v.getTag();
            PhotoViewActivity.IntentPhotoView(v.getContext(), image);
        }
    };

    /**
     * 删除回复内容
     */
    private CustomListener.ItemClickListener onReverDelListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            int replyId = (int) v.getTag();
            handleDeleteReplayRequest(replyId);
        }
    };


    private CustomListener.ItemClickListener onReverPreListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            String image = (String) v.getTag();
            PhotoViewActivity.IntentPhotoView(v.getContext(), image);

        }
    };

    class CustomPullLoadMoreListener implements LoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onInitialize() {
            showWaitDialog();
            handleReplayDetailRequest(replayId);
            handleInitReplayListRequest(replayId, pager, pagerSize);
        }

        @Override
        public void onRefresh() {
            pager = 0;
            handleReplayDetailRequest(replayId);
            handleInitReplayListRequest(replayId, pager, pagerSize);
        }

        @Override
        public void onLoadMore() {
            pager++;
            handleReplayListRequest(replayId, pager, pagerSize);
        }
    }


    ReplayDetailModel resultReplayDetail;

    private void handleReplayDetailRequest(int replyId) {
        HashMap<String, String> params = new HashMap<>();
        if (replyId == -1) return;
        params.put("replyId", replyId + "");
        NetRequest.postRequest(Constants.Community.REPLAY_DETAIL, BaseApplication.token, params, ReplayDetailModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {

                resultReplayDetail = (ReplayDetailModel) object;
                if (null != resultReplayList && resultReplayDetail != null) {
                    hideWaitDialog();
                    setData(resultReplayDetail, resultReplayList.getData().getRows());
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

    ReplayDetailListModel resultReplayList;

    private void handleInitReplayListRequest(int replyId, int pager, int pagerSize) {
        HashMap<String, String> params = new HashMap<>();
        if (replyId == -1) return;
        params.put("id", replyId + "");
        params.put("replyType", 0 + "");
        params.put("pageNo", pager + "");
        params.put("numberOfPerPage", pagerSize + "");
        NetRequest.postRequest(Constants.Community.TOPIC_REPLAY, BaseApplication.token, params, ReplayDetailListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                lmrv.setPullLoadMoreCompleted();
                resultReplayList = (ReplayDetailListModel) object;
                if (null != resultReplayList && resultReplayDetail != null) {
                    hideWaitDialog();
                    setData(resultReplayDetail, resultReplayList.getData().getRows());
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                showShortToast(message);
            }
        }));
    }

    private void handleReplayListRequest(int replyId, int pager, int pagerSize) {
        HashMap<String, String> params = new HashMap<>();
        if (replyId == -1) return;
        params.put("id", replyId + "");
        params.put("replyType", 0 + "");
        params.put("pageNo", pager + "");
        params.put("numberOfPerPage", pagerSize + "");
        NetRequest.postRequest(Constants.Community.TOPIC_REPLAY, BaseApplication.token, params, ReplayDetailListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                resultReplayList = (ReplayDetailListModel) object;
                if (null != resultReplayList) {
                    adapter.addData(resultReplayList.getData().getRows(), lmrv);
                }
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                lmrv.setPullLoadMoreCompleted();
                showShortToast(message);
            }
        }));
    }

    private void setData(ReplayDetailModel resultReplayDetail, List<ReplayDetailListModel.DataEntity.RowsEntity> resultReplayList) {
        adapter.setData(resultReplayDetail, resultReplayList);
        lmrv.setPullLoadMoreCompleted();
    }


    /**
     * 发送评论
     */
    private void handleSendReportRequest(int parentId, String contents, String img, String imgWidth, String imgHeight) {
        HashMap<String, String> params = new HashMap<>();
        if (parentId == -1) return;
        params.put("parentId", parentId + "");
        params.put("replyType", 1 + "");
        if (!TextUtils.isEmpty(contents)) {
            params.put("contents", contents);
        }
        if (!TextUtils.isEmpty(img))
            params.put("img", img);
        if (!TextUtils.isEmpty(imgWidth))
            params.put("imgWidth", imgWidth);
        if (!TextUtils.isEmpty(imgHeight))
            params.put("imgHeight", imgHeight);
        showWaitDialog();
        NetRequest.postRequest(Constants.Community.TOPIC_REPLYTOPIC, BaseApplication.token, params, CommunityReplayModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String message) {
                hideWaitDialog();
                showShortToast(message);
                etRever.clearFocus();
                etRever.setText("");
                ivImage.setImageURI(null);
                pager = 0;
                handleReplayDetailRequest(replayId);
                handleInitReplayListRequest(replayId, pager, pagerSize);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

    private void handleDeleteReplayRequest(int replyId) {
        HashMap<String, String> params = new HashMap<>();
        if (replyId == -1) return;
        params.put("replyId", replyId + "");
        params.put("isDelete", 1 + "");
        NetRequest.postRequest(Constants.Community.TOPIC_DELETEREPLY, BaseApplication.token, params, ReplayDetailListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                showShortToast(msg);
                pager = 0;
                handleInitReplayListRequest(replayId, pager, pagerSize);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

    /**
     * 举报
     *
     * @param replyId
     */
    public void handleReportReplayRequest(int replyId) {
        HashMap<String, String> params = new HashMap<>();
        if (replyId == -1) return;
        params.put("replyId", replyId + "");
        params.put("isReport", 1 + "");
        NetRequest.postRequest(Constants.Community.TOPIC_REPORTREPLY, BaseApplication.token, params, ReplayDetailListModel.class, new NetRequest.NetHandler(this, new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                showShortToast(msg);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

    private ArrayList<String> tempImages = new ArrayList<>();
    private final int PIC_REQUEST_CODE = 1001;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == PIC_REQUEST_CODE) {
            if (data != null) {
                tempImages = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                Log.e("ImagePath", "ImagePaTH = " + tempImages.get(0));
                ivImage.setImageURI(Uri.parse("file://" + tempImages.get(0)));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
