package com.cmbb.smartkids.photopicker.event;

import com.cmbb.smartkids.photopicker.entity.Photo;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/20 下午4:51
 */
public interface OnItemCheckListener {
    /**
     * @param position          所选图片的位置
     * @param path              所选图片
     * @param isCheck           当前状态
     * @param selectedItemCount 已选数量
     * @return enable check
     */
    boolean OnItemCheck(int position, Photo path, boolean isCheck, int selectedItemCount);
}
