package com.cmbb.smartkids.widget.wheelview;

import android.content.Context;

import com.cmbb.smartkids.model.AddressModel;

/**
 * 项目名称：JavonFZZ-master
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/20 15:17
 */
public class ProvinceWheelAdapter implements WheelAdapter {
	
	private AddressModel[] provinces;
	
	public ProvinceWheelAdapter(Context context,AddressModel[] array){
		provinces = array;
	}

	@Override
	public int getItemsCount() {
		return provinces == null ? 0 : provinces.length;
	}

	@Override
	public String getItem(int index) {
		AddressModel addressModel = provinces[index];
		return index <= provinces.length - 1 ? addressModel.name : null;
	}

	@Override
	public int getMaximumLength() {
		return 7;
	}
	
	@Override
	public int getCurrentId(int index) {
		return 0;
	}

}
