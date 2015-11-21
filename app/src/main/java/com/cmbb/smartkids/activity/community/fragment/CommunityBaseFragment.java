package com.cmbb.smartkids.activity.community.fragment;

import com.cmbb.smartkids.activity.community.adapter.CommentAdapter;
import com.cmbb.smartkids.activity.community.adapter.CommunityDetailAdapter;
import com.cmbb.smartkids.activity.community.model.CommunityDetailModel;
import com.cmbb.smartkids.activity.community.model.CommunityReplayModel;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.NetRequest;

import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/13 下午5:34
 */
public class CommunityBaseFragment extends BaseFragment {


    public CommunityDetailModel resultDetail;
    public CommunityReplayModel resultReplay;
    public CommunityDetailAdapter adapterFirst;
    public CommentAdapter adapterNext;

    public void handleCollectionRequest(int topicId, final int isCollect, NetRequest.NetHandler netHandler) {
        HashMap<String, String> params = new HashMap<>();
        if (topicId == -1) return;
        params.put("id", topicId + "");
        switch (isCollect) {
            case 0:
                params.put("isCollect", 1 + "");
                break;
            case 1:
                params.put("isCollect", 0 + "");
                break;
        }
        NetRequest.postRequest(Constants.Community.TOPIC_COLLECT, BaseApplication.token, params, CommunityReplayModel.class, netHandler);
    }

    public void handleDeleteRequest(int topicId, NetRequest.NetHandler netHandler) {
        HashMap<String, String> params = new HashMap<>();
        if (topicId == -1) return;
        params.put("id", topicId + "");
        params.put("isDelete", 1 + "");
        NetRequest.postRequest(Constants.Community.TOPIC_DELETE, BaseApplication.token, params, CommunityReplayModel.class, netHandler);
    }

    public void handleReportRequest(int topicId, NetRequest.NetHandler netHandler) {
        HashMap<String, String> params = new HashMap<>();
        if (topicId == -1) return;
        params.put("id", topicId + "");
        params.put("isReport", 1 + "");
        NetRequest.postRequest(Constants.Community.TOPIC_REPORT, BaseApplication.token, params, CommunityReplayModel.class, netHandler);
    }

    public void updateCurrentFragment(CommunityReplayModel communityReplayModel) {
        if (adapterFirst != null) {
            adapterFirst.updateData(communityReplayModel);
        }
        if (adapterNext != null) {
            adapterNext.updateData(communityReplayModel);
        }
    }
}
