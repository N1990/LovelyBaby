package com.cmbb.smartkids.base;

import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.javon.library.PullToRefreshBase;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/11 15:32
 */
public class CustomListener {
    public interface DialogListener {
        void onClick(View v);
    }

    public interface ItemClickListener {
        void onItemClick(View v, int position, Object object);
    }

    public interface NetWorkListener<T extends Parcelable> {

        /**
         * 网络请求成功
         *
         * @param object
         */
        void onSuccessListener(T object, String msg);


        /**
         * 网络请求成功，但是返回验证码错误
         *
         * @param message
         */
        void onErrorListener(String message);

    }

    /**
     *
     */
    public interface FragmentInterface{
        public void onDownPager(PullToRefreshBase<RecyclerView> refreshView);

        public void onUpPager(PullToRefreshBase<RecyclerView> refreshView);

        public void showKeyboard();
    }

}
