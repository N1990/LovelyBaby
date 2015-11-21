package com.cmbb.smartkids.widget.wheelview;

import android.content.Context;

import com.cmbb.smartkids.model.AddressModel;

/**
 * 项目名称：JavonFZZ-master
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/20 15:17
 */
public class AreaWheelAdapter implements WheelAdapter {
    private AddressModel[] areas;
    private Context context;

    public AreaWheelAdapter(Context context, AddressModel[] array){
        this.context = context;
        this.areas = array;
    }

    @Override
    public int getItemsCount() {
        return areas == null ? 0 : areas.length;
    }

    @Override
    public String getItem(int index) {
        AddressModel addressModel=areas[index];
        return index <= areas.length - 1 ? addressModel.name : null;
    }

    @Override
    public int getMaximumLength() {
        return 7;
    }

    public void setAreaList(AddressModel[] array){
        this.areas = array;;
    }

    @Override
    public int getCurrentId(int index) {
        return 0;
    }
}
