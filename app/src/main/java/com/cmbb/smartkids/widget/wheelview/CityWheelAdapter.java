package com.cmbb.smartkids.widget.wheelview;

import android.content.Context;

import com.cmbb.smartkids.model.AddressModel;

/**
 * 项目名称：JavonFZZ-master
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/20 15:17
 */
public class CityWheelAdapter implements WheelAdapter {
	
	private AddressModel[] cities;
	private Context context;
	
	public CityWheelAdapter(Context context, AddressModel[] array){
		this.context = context;
		this.cities = array;
	}

	@Override
	public int getItemsCount() {
		return cities == null ? 0 : cities.length;
	}

	@Override
	public String getItem(int index) {
		AddressModel addressModel=cities[index];
		return index <= cities.length - 1 ? addressModel.name : null;
	}

	@Override
	public int getMaximumLength() {
		return 7;
	}
	
	public void setCityList(AddressModel[] array){
		this.cities = array;
	}

	@Override
	public int getCurrentId(int index) {
		return 0;
	}
}
