package com.cmbb.smartkids.base;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/11 10:08
 */
public class BaseFragment extends Fragment implements View.OnClickListener {
    private final String TAG = BaseFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    protected void showToast(String tip) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showToast(tip);
        }
    }

    protected void showShortToast(String tip) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showShortToast(tip);
        }
    }

    protected void showWaitsDialog() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showWaitDialog();
        }
    }

    protected boolean isWaitShow() {
        if (getActivity() instanceof BaseActivity) {
            return ((BaseActivity) getActivity()).isWaitShow();
        } else {
            return false;
        }
    }

    protected void showWaitsDialog(String tip) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showWaitDialog(tip);
        }
    }

    protected void hideWaitDialog() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hideWaitDialog();
        }
    }

    protected void cancelRequest() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).cancelRequest();
        }
    }

    protected void showSnackBar(View root, String tip, String actionStr) {

    }

    @Override
    public void onClick(View v) {

    }
}
