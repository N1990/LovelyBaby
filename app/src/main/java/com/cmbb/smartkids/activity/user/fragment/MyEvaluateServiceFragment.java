package com.cmbb.smartkids.activity.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.serve.v2.ServerDetailActivityV2;
import com.cmbb.smartkids.activity.user.adapter.MyEvaluateServiceAdapter;
import com.cmbb.smartkids.activity.user.adapter.MyEvaluateUserAdapter;
import com.cmbb.smartkids.activity.user.model.EvaluateServiceModel;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.squareup.okhttp.Request;

/**
 * Created by javon on 16/3/21.
 */
public class MyEvaluateServiceFragment extends BaseFragment implements View.OnClickListener, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private SmartRecyclerView srv;
    private MyEvaluateServiceAdapter adapter;
    private int pager = 0;
    private int pagerSize = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerview_layout, container, false);
        srv = (SmartRecyclerView) root.findViewById(R.id.srv_self);
        srv.setLayoutManager(new LinearLayoutManager(getActivity()));
        srv.setRefreshListener(this);
        adapter = new MyEvaluateServiceAdapter(getActivity());
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        srv.setAdapterWithProgress(adapter);
        adapter.setOnServiceListener(new CustomListener.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Object object) {
                EvaluateServiceModel.DataEntity.RowsEntity data = (EvaluateServiceModel.DataEntity.RowsEntity) object;
                ServerDetailActivityV2.newIntent(getActivity(), data.getServiceBasicInfo().getId());
            }
        });
        onRefresh();
        return root;
    }


    @Override
    public void onRefresh() {
        pager = 0;
        EvaluateServiceModel.getEvaluateServiceRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<EvaluateServiceModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(EvaluateServiceModel response) {
                if (response != null) {
                    adapter.clear();
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }


    @Override
    public void onLoadMore() {
        pager++;
        EvaluateServiceModel.getEvaluateServiceRequest(pager, pagerSize, new OkHttpClientManager.ResultCallback<EvaluateServiceModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(EvaluateServiceModel response) {
                if (response != null) {
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }


}
