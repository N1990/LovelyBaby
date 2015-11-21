package com.cmbb.smartkids.widget.wheelview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.utils.TDevice;


public class DateTimeSelectorDialogBuilder extends CustomDialogBuilder implements
		View.OnClickListener, CustomListener.DialogListener {

	private final String TAG = DateTimeSelectorDialogBuilder.class.getSimpleName();

	private Context context;
	private RelativeLayout rlCustomLayout;
	private DateSelectorWheelView dateWheelView;
	private OnSaveListener saveListener;
	/**
	 * 默认方向标示
	 */
	private static int mOrientation = 1;
	private static DateTimeSelectorDialogBuilder instance;

	public interface OnSaveListener {
		abstract void onSaveSelectedDate(String selectedDate);
	}


	public DateTimeSelectorDialogBuilder(Context context, String currentTime) {
		super(context);
		this.context = context;
		initDialog(currentTime);
	}

	public DateTimeSelectorDialogBuilder(Context context, int theme, String currentTime) {
		super(context, theme);
		this.context = context;
		initDialog(currentTime);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public static DateTimeSelectorDialogBuilder getInstance(Context context, String currentTime) {

		int ort = context.getResources().getConfiguration().orientation;
		if (mOrientation != ort) {
			mOrientation = ort;
			instance = null;
		}

		if (instance == null || ((Activity) context).isFinishing()) {
			synchronized (DateTimeSelectorDialogBuilder.class) {
				if (instance == null) {
					instance = new DateTimeSelectorDialogBuilder(context,
							R.style.dialog_untran, currentTime);
				}
			}
		}
		return instance;

	}

	private void initDialog(String currentTime) {
		rlCustomLayout = (RelativeLayout) LayoutInflater.from(context).inflate(
				R.layout.dialog_date_time, null);
		dateWheelView = (DateSelectorWheelView) rlCustomLayout
				.findViewById(R.id.pdwv_date_time_selector_wheelView);
		if(!TextUtils.isEmpty(currentTime)){
			// 2014-11-01
			String year = currentTime.substring(0, 4);
			String month = currentTime.substring(5, 7);
			String day = currentTime.substring(8);
			dateWheelView.setCurrentYear(year);
			dateWheelView.setCurrentMonth(month);
			dateWheelView.setCurrentDay(day);
		}
		dateWheelView.setTitleClick(this);
		setDialogProperties();
	}

	private void setDialogProperties() {
		int width = TDevice.getScreenWidth(context) * 3 / 4;
		this.setDialogWindows(width, LayoutParams.WRAP_CONTENT)
				.withTitle("选择日期")
				.withCancelText("取消", this)
				.withComfirmText("确定", this)
				.withMessageMiss(View.GONE)
				.withCustomContentView(rlCustomLayout, context);

	}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.rl_date_time_title:
			if (dateWheelView.getDateSelectorVisibility() == View.VISIBLE) {
				dateWheelView.setDateSelectorVisiblility(View.GONE);
			} else {
				dateWheelView.setDateSelectorVisiblility(View.VISIBLE);
			}
			break;
		case R.id.tv_custom_dialog_cancel:
			dismiss();
			break;
		case R.id.tv_custom_dialog_comfirm:
			if (null != saveListener) {
				saveListener.onSaveSelectedDate(dateWheelView.getSelectedDate());
			}
			dismiss();
			break;
		}
	}

	/**
	 * 获取日期选择器
	 * 
	 * @return
	 */
	public DateSelectorWheelView getDateWheelView() {
		return dateWheelView;
	}

	/**
	 * 设置保存监听
	 * 
	 * @param saveListener
	 */
	public void setOnSaveListener(OnSaveListener saveListener) {
		this.saveListener = saveListener;
	}
	/**
	 * 最初显示时是否可以可见
	 * 
	 * @param visibility
	 */
	public void setWheelViewVisibility(int visibility) {
		dateWheelView.setDateSelectorVisiblility(visibility);
	}

	@Override
	public void dismiss() {
		super.dismiss();
		instance = null;
	}

}
