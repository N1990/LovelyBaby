package com.cmbb.smartkids.activity.community.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.CommunityDetailActivity;
import com.cmbb.smartkids.activity.community.ReverCommentActivity;
import com.cmbb.smartkids.activity.community.adapter.CommentAdapter;
import com.cmbb.smartkids.activity.community.model.CommunityReplayModel;
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.widget.wheelview.CustomDialogBuilder;
import com.javon.library.PtrRecyclerView;
import com.javon.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.HashMap;


/**
 */
public class CommentListTwoFragment extends CommunityBaseFragment {
    private static final String TOPIC_ID = "topic_id";
    private static final String PAGER = "pager";
    private static final String PAGER_SIZE = "pager_size";


    private PtrRecyclerView prt;


    private CustomListener.FragmentInterface mListener;

    public CommentListTwoFragment() {
        // Required empty public constructor
    }

    public static CommentListTwoFragment newInstance(Parcelable data) {
        CommentListTwoFragment fragment = new CommentListTwoFragment();
        Bundle args = new Bundle();
        args.putParcelable("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment_list_one, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        addListener();
    }

    private void initData() {
        if (getArguments() != null) {
            resultReplay = getArguments().getParcelable("data");
            adapterNext.setData(resultReplay);
            prt.setAdapter(adapterNext);
        }
    }

    private void initView() {
        prt = (PtrRecyclerView) getView().findViewById(R.id.pull_refresh_recycler_comment_one);
        adapterNext = new CommentAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        prt.setLayoutManager(manager);
        prt.setMode(PullToRefreshBase.Mode.BOTH);
    }


    private void addListener() {
        prt.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
                if (mListener != null)
                    mListener.onUpPager(refreshView);
            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
                if (mListener != null)
                    mListener.onDownPager(refreshView);
            }
        });

        adapterNext.setOnCommentHeaderListener(onCommentHeaderListener);
        adapterNext.setOnReverListener(onReverListener);
        adapterNext.setOnMoreListener(onMoreListener);
        adapterNext.setOnPreCommentListener(onCommentPreListener);
        adapterNext.setOnReverDeepListener(onDeepReverListener);
        adapterNext.setOnReverDelListener(onReverDelListener);
        adapterNext.setOnPreReverListener(onReverPreListener);
        adapterNext.setOnMoreReverListener(moreReverListener);
        adapterNext.setOnReverNickListener(onCommentNickListener);
        adapterNext.setOnEveryListener(new CustomListener.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Object object) {
                ((CommunityDetailActivity) getActivity()).clearFocus();
            }
        });
    }


    /**
     * 评论用户头像
     */
    private CustomListener.ItemClickListener onCommentHeaderListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            int userId = (int) v.getTag();
            UserCenterActivity.newIntent(getActivity(), userId);
        }
    };


    /**
     * 评论回复
     */
    private CustomListener.ItemClickListener onReverListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            // 回复楼中楼
            Intent intent = new Intent(getActivity(), ReverCommentActivity.class);
            int replayId = (int) v.getTag();
            intent.putExtra("replayId", replayId);
            startActivity(intent);
        }
    };


    CustomDialogBuilder builder;
    /**
     * 评论更多
     */
    private CustomListener.ItemClickListener onMoreListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            final ArrayList<Integer> more = (ArrayList<Integer>) object;
            switch (more.get(1)) {
                case 1:// delete
                    if (builder != null)
                        builder.setDialogDismiss();
                    builder = CustomDialogBuilder.getInstance(v.getContext()).withTitle("操作")
                            .withMessage("您确认要删除您的回复吗？")
                            .withComfirmText("确认", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null) builder.setDialogDismiss();
                                    ((CommunityDetailActivity) getActivity()).handleDeleteReplayRequest(more.get(0));
                                }
                            })
                            .withCancelText("取消", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null) builder.setDialogDismiss();
                                }
                            });
                    builder.show();
                    break;
                case 0:// report
                    if (builder != null)
                        builder.setDialogDismiss();
                    builder = CustomDialogBuilder.getInstance(v.getContext()).withTitle("操作")
                            .withMessage("您确认要举报此回复吗？")
                            .withComfirmText("确认", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.setDialogDismiss();

                                    ((CommunityDetailActivity) getActivity()).handleReportReplayRequest(more.get(0));
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
    private CustomListener.ItemClickListener onCommentPreListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            String image = (String) v.getTag();
            PhotoViewActivity.IntentPhotoView(v.getContext(), image);

        }
    };

    /**
     * 回复的回复
     */
    private CustomListener.ItemClickListener onDeepReverListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            int id = (int) v.getTag();
            showShortToast("回复你的评论~" + id);
        }
    };

    /**
     * 删除回复内容
     */
    private CustomListener.ItemClickListener onReverDelListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            int replyId = (int) v.getTag();
            ((CommunityDetailActivity) getActivity()).handleDeleteReplayRequest(replyId);
        }
    };

    /**
     * 评论用户昵称
     */
    private CustomListener.ItemClickListener onCommentNickListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            CommunityReplayModel.DataEntity.RowsEntity.ChildReplyEntity data = (CommunityReplayModel.DataEntity.RowsEntity.ChildReplyEntity) v.getTag();
            UserCenterActivity.newIntent(getActivity(), data.getUserBasicInfo().getUserId());
        }
    };

    private CustomListener.ItemClickListener onReverPreListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            CommunityReplayModel.DataEntity.RowsEntity.ChildReplyEntity data = (CommunityReplayModel.DataEntity.RowsEntity.ChildReplyEntity) v.getTag();
            String image = data.getImg();
            PhotoViewActivity.IntentPhotoView(v.getContext(), image);

        }
    };

    /**
     * 更多回复内容
     */
    private CustomListener.ItemClickListener moreReverListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            Intent intent = new Intent(getActivity(), ReverCommentActivity.class);
            int replayId = (int) v.getTag();
            intent.putExtra("replayId", replayId);
            startActivity(intent);
        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CustomListener.FragmentInterface) {
            mListener = (CustomListener.FragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        if(builder != null)
            builder.setDialogDismiss();
//        super.onDestroy();
    }


    private void handleTopicReplayRequest(int topicId, int pager, int pagerSize) {
        HashMap<String, String> params = new HashMap<>();
        if (topicId == -1) return;
        params.put("id", topicId + "");
        params.put("pageNo", String.valueOf(pager));
        params.put("replyType", 1 + "");
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        showWaitsDialog();
        NetRequest.postRequest(Constants.Community.TOPIC_REPLAY, BaseApplication.token, params, CommunityReplayModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String msg) {
                hideWaitDialog();
                resultReplay = (CommunityReplayModel) object;
                adapterNext.setData(resultReplay);
                prt.setAdapter(adapterNext);
            }

            @Override
            public void onErrorListener(String message) {
                hideWaitDialog();
                showShortToast(message);
            }
        }));
    }

}
