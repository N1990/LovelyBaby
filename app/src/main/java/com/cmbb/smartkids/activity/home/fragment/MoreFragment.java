package com.cmbb.smartkids.activity.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.adapter.MoreAdapter;
import com.cmbb.smartkids.activity.more.AboutActivity;
import com.cmbb.smartkids.activity.more.GrownValusActivity;
import com.cmbb.smartkids.activity.more.SuggestActivity;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.utils.log.Log;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 10:44
 */
public class MoreFragment extends BaseFragment implements CustomListener.ItemClickListener {
    private final String TAG = MoreFragment.class.getSimpleName();
    private RecyclerView rv;
    private MoreAdapter adapter;
    // toolbar

    private ActionBar actionbar;
    private TextView tvTitle, tvRight;
    private ImageView ivRight, ivLeft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_more, null);
        rv = (RecyclerView) v.findViewById(R.id.rv_home_more);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MoreAdapter();
        rv.setAdapter(adapter);
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initActionBar();
    }

    /**
     * 初始化actionbar
     */
    private void initActionBar() {
        try {
            Toolbar v = (Toolbar) getView().findViewById(R.id.tl_main_actionbar);
            tvTitle = (TextView) v.findViewById(R.id.tv_main_toolbar);
            tvTitle.setText("更多");
            ivLeft = (ImageView) v.findViewById(R.id.iv_main_toolbar_left);
            ivRight = (ImageView) v.findViewById(R.id.iv_main_toolbar_right);
            ivRight.setVisibility(View.GONE);
            ivRight.setImageResource(R.drawable.ic_action_email);
            tvRight = (TextView) v.findViewById(R.id.tv_main_toolbar_right);
            ((AppCompatActivity) getActivity()).setSupportActionBar(v);
            actionbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(false);
            actionbar.setDisplayShowTitleEnabled(false);
        } catch (Exception e) {
            Log.i(TAG, "actionbar is null");
        }
    }


    @Override
    public void onItemClick(View v, int position, Object object) {
        if (position == 0) {
            startActivity(new Intent(getActivity(), SuggestActivity.class));
        } else if (position == 1) {
            startActivity(new Intent(getActivity(), GrownValusActivity.class));
        } else if (position == 2) {
            startActivity(new Intent(getActivity(), AboutActivity.class));
        } else if (position == 3) {

        }
    }
}
