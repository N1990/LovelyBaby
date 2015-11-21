package com.cmbb.smartkids.activity.user.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.MyServiceAdapter;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.CustomListener;
import com.javon.loadmorerecyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 10:22
 */
public class MyServiceFragment extends BaseFragment {
    private final String TAG = MyServiceFragment.class.getSimpleName();
    private LoadMoreRecyclerView lmrv;
    private MyServiceAdapter adapter;
    private int mCount = 1;  //模拟数据
    private boolean loadMore;//模拟数据
    private int flags = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerview_layout, null);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        addListener();
    }

    private void initView() {
        lmrv = (LoadMoreRecyclerView) getView().findViewById(R.id.lmrv_self);
        lmrv.setLinearLayout();
        adapter = new MyServiceAdapter();
        adapter.setData(new ArrayList<String>());// 模拟数据
        lmrv.setAdapter(adapter);
    }

    private void initData() {

    }

    private void addListener() {
        lmrv.setPullLoadMoreListener(lmrvListener);
        lmrv.setInitialize();
        adapter.setOnFooterTryAgain(this);
        adapter.setOnItemClick(itemClick);
    }

    private LoadMoreRecyclerView.PullLoadMoreListener lmrvListener = new LoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onInitialize() {
            mCount = 3;
            new DataAsyncTask().execute();


        }

        @Override
        public void onRefresh() {
            adapter.clearData();
            new DataAsyncTask().execute();
        }

        @Override
        public void onLoadMore() {

        }
    };

    private CustomListener.ItemClickListener itemClick = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            showShortToast("position:"+position);
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    //模拟数据=======================================================================================================================
    private ArrayList<String> setList() {
        ArrayList<String> dataList = new ArrayList<>();
        int start = 0;
        for (int i = start; i < mCount; i++) {
            dataList.add("测试数据" + i);
        }
        return dataList;

    }


    class DataAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (loadMore)
                adapter.setLoadMore();
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            flags++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (flags == 3) {
                return null;
//                return new ArrayList<>();
            } else {
                return setList();
            }
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            loadMore = false;
            /*if (mRecyclerViewAdapter == null) {
                mRecyclerViewAdapter = new RecyclerViewAdapter();
                mRecyclerViewAdapter.setData(strings);
                mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
            } else {
                mRecyclerViewAdapter.addData(strings, mPullLoadMoreRecyclerView);
            }*/
            adapter.addData(strings, lmrv);
            lmrv.setPullLoadMoreCompleted();
            Log.e(TAG, "i come here1001");
        }
    }
}
