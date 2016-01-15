package com.cmbb.smartkids.widget.wheelview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.utils.TDevice;

public class LocationSelectorDialogBuilder extends CustomDialogBuilder implements
		CustomListener.DialogListener {

	private Context context;
	private RelativeLayout rlCustomLayout;
	private AreasWheel areasWheel;
	private OnSaveLocationLister saveLocationLister;
	private static LocationSelectorDialogBuilder instance;
	private static  int mOrientation=1;

	public interface OnSaveLocationLister {
		abstract void onSaveLocation(String local, String province, String city, String area, String provinceId, String cityId, String areaId);
	}

	public LocationSelectorDialogBuilder(Context context, int theme) {
		super(context, theme);
		this.context = context;
		initDialog();
	}

	public LocationSelectorDialogBuilder(Context context) {
		super(context);
		this.context = context;
		initDialog();
	}

	public static LocationSelectorDialogBuilder getInstance(Context context){

        int ort=context.getResources().getConfiguration().orientation;
        if (mOrientation!=ort){
            mOrientation=ort;
            instance=null;
        }

        if (instance == null||((Activity) context).isFinishing()) {
            synchronized (LocationSelectorDialogBuilder.class) {
                if (instance == null) {
                    instance = new LocationSelectorDialogBuilder(context,R.style.dialog_untran);
                }
            }
        }
        return instance;

    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	private void initDialog() {
		rlCustomLayout = (RelativeLayout) LayoutInflater.from(context).inflate(
				R.layout.dialog_location, null);
		areasWheel = (AreasWheel) rlCustomLayout
				.findViewById(R.id.aw_location_selector_wheel);
		setDialogProperties();
	}

	private void setDialogProperties() {
		setCanceledOnTouchOutside(false);
		int width = TDevice.getScreenWidth(context) * 5 / 6;
		this.setDialogWindows(width, LayoutParams.WRAP_CONTENT)
				.withTitle("选择地区")
				.withCancelText("取消", this)
				.withComfirmText("保存", this)
				.withMessageMiss(View.GONE)
				.withCustomContentView(rlCustomLayout, context);
	}

	public void setDialogDismiss(){
		instance = null;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_custom_dialog_cancel:
			dismiss();
			break;
		case R.id.tv_custom_dialog_comfirm:
			if (null != saveLocationLister) {
				saveLocationLister.onSaveLocation(areasWheel.getLocal(), areasWheel.getProvince(), areasWheel.getCity(), areasWheel.getArea(),areasWheel.getProvinceId(), areasWheel.getCityId(),areasWheel.getAreaId());
			}
			dismiss();
			break;
		}
	}
	/**
	 * 设置点击保存的监听
	 * @param saveLocationLister
	 */
	public void setOnSaveLocationLister(OnSaveLocationLister saveLocationLister) {
		this.saveLocationLister = saveLocationLister;
	}
	@Override
	public void dismiss() {
		super.dismiss();
		instance = null;
	}
}
