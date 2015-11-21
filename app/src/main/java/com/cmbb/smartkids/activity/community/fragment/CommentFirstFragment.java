package com.cmbb.smartkids.activity.community.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.CommunityDetailActivity;
import com.cmbb.smartkids.activity.community.ReverCommentActivity;
import com.cmbb.smartkids.activity.community.adapter.CommunityDetailAdapter;
import com.cmbb.smartkids.activity.community.model.CommunityDetailModel;
import com.cmbb.smartkids.activity.community.model.CommunityReplayModel;
import com.cmbb.smartkids.activity.community.model.ImageTagModel;
import com.cmbb.smartkids.activity.community.model.SpotModel;
import com.cmbb.smartkids.activity.user.UserCenterActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.NetRequest;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.utils.log.Log;
import com.cmbb.smartkids.widget.wheelview.CustomDialogBuilder;
import com.javon.library.PtrRecyclerView;
import com.javon.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.HashMap;


public class CommentFirstFragment extends CommunityBaseFragment {
    private final String TAG = CommentFirstFragment.class.getSimpleName();
    private static final String TOPIC_ID = "topic_id";
    private static final String PAGER = "pager";
    private static final String PAGER_SIZE = "pager_size";


    private PtrRecyclerView prt;


    private CustomListener.FragmentInterface mListener;


    public CommentFirstFragment() {
        // Required empty public constructor
    }


    public static CommentFirstFragment newInstance(CommunityDetailModel resultDetail, CommunityReplayModel resultReplay) {
        CommentFirstFragment fragment = new CommentFirstFragment();
        Bundle args = new Bundle();
        args.putParcelable("resultDetail", resultDetail);
        args.putParcelable("resultReplay", resultReplay);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment_first, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prt = (PtrRecyclerView) getView().findViewById(R.id.pull_refresh_recycler_comment_first);
        adapterFirst = new CommunityDetailAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        prt.setLayoutManager(manager);
        prt.setMode(PullToRefreshBase.Mode.BOTH);
        addListener();
        if (getArguments() != null) {
            resultDetail = getArguments().getParcelable("resultDetail");
            resultReplay = getArguments().getParcelable("resultReplay");
            ((CommunityDetailActivity) getActivity()).setCollectionView(resultDetail.getData().getIsCollect());
            ((CommunityDetailActivity) getActivity()).setTotal(resultReplay.getData().getTotal());
            adapterFirst.setDate(resultDetail, resultReplay);
            prt.setAdapter(adapterFirst);
        }
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


        adapterFirst.setOnHeaderListener(onHeaderListener);
        adapterFirst.setOnTeletextListener(onTeletextListener);
        adapterFirst.setOnPraiseListener(onPraiseListener);
        adapterFirst.setOnPreUserListener(onUsersPreListener);
        adapterFirst.setOnCommentHeaderListener(onCommentHeaderListener);
        adapterFirst.setOnReverListener(onReverListener);
        adapterFirst.setOnMoreListener(onMoreListener);
        adapterFirst.setOnPreCommentListener(onCommentPreListener);
        adapterFirst.setOnReverDeepListener(onDeepReverListener);
        adapterFirst.setOnReverDelListener(onReverDelListener);
        adapterFirst.setOnPreReverListener(onReverPreListener);
        adapterFirst.setOnMoreReverListener(moreReverListener);
        adapterFirst.setOnReverNickListener(onCommentNickListener);
        adapterFirst.setOnEveryListener(new CustomListener.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Object object) {
                ((CommunityDetailActivity) getActivity()).clearFocus();
            }
        });
    }


    /**
     * 创建话题人头像
     */
    private View.OnClickListener onHeaderListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int userId = (int) v.getTag();
            Intent intent = new Intent(getActivity(), UserCenterActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
    };

    /**
     * 话题图文
     */
    private CustomListener.ItemClickListener onTeletextListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            ImageTagModel imageTagModel = (ImageTagModel) v.getTag();
            PhotoViewActivity.IntentPhotoView(v.getContext(), imageTagModel.getImages(), imageTagModel.getPosition());
        }
    };

    /**
     * 话题点赞
     */
    private View.OnClickListener onPraiseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommunityDetailModel data = (CommunityDetailModel) v.getTag();
            handleSpotRequest(data.getData().getId(), data.getData().getIsSpot());
        }
    };

    /**
     * 话题用户列表
     */
    private CustomListener.ItemClickListener onUsersPreListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            int userId = (int) v.getTag();
            Intent intent = new Intent(getActivity(), UserCenterActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
    };

    /**
     * 评论用户头像
     */
    private CustomListener.ItemClickListener onCommentHeaderListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            int userId = (int) v.getTag();
            Intent intent = new Intent(getActivity(), UserCenterActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
    };

    /**
     * 评论用户昵称
     */
    private CustomListener.ItemClickListener onCommentNickListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            CommunityReplayModel.DataEntity.RowsEntity.ChildReplyEntity data = (CommunityReplayModel.DataEntity.RowsEntity.ChildReplyEntity) v.getTag();
            Intent intent = new Intent(getActivity(), UserCenterActivity.class);
            intent.putExtra("userId", data.getUserBasicInfo().getUserId());
            startActivity(intent);
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


    /**
     * 评论更多
     */
    CustomDialogBuilder builder;
    private CustomListener.ItemClickListener onMoreListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            final ArrayList<Integer> more = (ArrayList<Integer>) object;
            switch (more.get(1)) {
                case 1:// delete
                    builder = CustomDialogBuilder.getInstance(getActivity()).withTitle("操作")
                            .withMessage("您确认要删除您的回复吗？")
                            .withComfirmText("确认", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.dismiss();
                                    ((CommunityDetailActivity) getActivity()).handleDeleteReplayRequest(more.get(0));
                                }
                            })
                            .withCancelText("取消", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.dismiss();
                                }
                            });
                    builder.show();

                    break;
                case 0:// report
                    builder = CustomDialogBuilder.getInstance(getActivity()).withTitle("操作")
                            .withMessage("您确认要举报此回复吗？")
                            .withComfirmText("确认", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.dismiss();

                                    ((CommunityDetailActivity) getActivity()).handleReportReplayRequest(more.get(0));
                                }
                            })
                            .withCancelText("取消", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.dismiss();
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
            showShortToast("回复的回复~");
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
        super.onDestroy();
    }


    private void handleSpotRequest(int topicId, final int isSpot) {
        Log.e("SPOT", "spot = " + isSpot);
        HashMap<String, String> params = new HashMap<>();
        if (topicId == -1) return;
        params.put("id", topicId + "");
        switch (isSpot) {
            case 0:
                params.put("isSpot", 1 + "");
                break;
            case 1:
                params.put("isSpot", 0 + "");
                break;
        }
        NetRequest.postRequest(Constants.Community.TOPIC_SPOT, BaseApplication.token, params, SpotModel.class, new NetRequest.NetHandler(getActivity(), new NetRequest.NetResponseListener() {
            @Override
            public void onSuccessListener(Object object, String message) {
                showShortToast(message);
                SpotModel entity = (SpotModel) object;
                switch (isSpot) {
                    case 0:
                        resultDetail.getData().setIsSpot(1);
                        resultDetail.getData().setSpots(resultDetail.getData().getSpots() + 1);
                        //添加
                        if (resultDetail.getData().getSpotList().size() == 5) {
                            resultDetail.getData().getSpotList().remove(4);
                            resultDetail.getData().getSpotList().add(0, entity.getData());
                        } else {
                            resultDetail.getData().getSpotList().add(0, entity.getData());
                        }
                        break;
                    case 1:
                        resultDetail.getData().setIsSpot(0);
                        resultDetail.getData().setSpots(resultDetail.getData().getSpots() - 1);
                        // 删除
                        for (int i = 0; i < resultDetail.getData().getSpotList().size(); i++) {
                            if (resultDetail.getData().getSpotList().get(i).getId() == entity.getData().getId()) {
                                resultDetail.getData().getSpotList().remove(i);
                            }
                        }
                        break;
                }
                adapterFirst.notifyDataSetChanged();
            }

            @Override
            public void onErrorListener(String message) {
                showShortToast(message);
                switch (isSpot) {
                    case 0:
                        resultDetail.getData().setIsSpot(0);
                        break;
                    case 1:
                        resultDetail.getData().setIsSpot(1);
                        break;
                }
                adapterFirst.notifyDataSetChanged();
            }
        }));
    }


}
