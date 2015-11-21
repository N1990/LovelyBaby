package com.cmbb.smartkids.photopicker.event;

import android.view.View;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/20 下午4:51
 */
public interface OnPhotoClickListener {
    /**
     * @param v          被点击控件
     * @param position   点击位置
     * @param showCamera 是否现实camera按钮
     */
    void onClick(View v, int position, boolean showCamera);

}
