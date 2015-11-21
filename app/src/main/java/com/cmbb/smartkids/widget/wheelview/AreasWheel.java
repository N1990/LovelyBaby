package com.cmbb.smartkids.widget.wheelview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;


import com.cmbb.smartkids.R;
import com.cmbb.smartkids.model.AddressModel;
import com.cmbb.smartkids.utils.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class AreasWheel extends LinearLayout {
	private WheelView wv_province;
	private WheelView wv_city;
	private WheelView wv_area;
	public int screenheight;
	private Context context;
	private OnWheelChangedListener provinceChangedListener;
	private OnWheelChangedListener cityChangedListener;
	private OnWheelChangedListener areaChangedListener;
	private AreaWheelAdapter areaWheelAdapter;
	private CityWheelAdapter cityWheelAdapter;
	private ProvinceWheelAdapter provinceWheelAdapter;
	private String mCurrentProviceId;
	private String mCurrentCityId;
	private String mCurrentAreaId;
	/**
	 * 所有省
	 */
	private AddressModel[] mProvinceDatas;

	/**
	 * key - 省 value - 市s
	 */
	private Map<String, AddressModel[]> mCitisDatasMap = new HashMap<String, AddressModel[]>();
	/**
	 * key - 市 values - 区s
	 */
	private Map<String, AddressModel[]> mAreaDatasMap = new HashMap<String, AddressModel[]>();
	

	public AreasWheel(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		initView();
	}

	public AreasWheel(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	public AreasWheel(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	private void initView() {
		initDatas();
		LayoutInflater.from(context).inflate(
				R.layout.layout_location, this, true);
		wv_province = (WheelView) findViewById(R.id.wv_province);
		wv_city = (WheelView) findViewById(R.id.wv_city);
		wv_area = (WheelView) findViewById(R.id.wv_arae);

		provinceWheelAdapter = new ProvinceWheelAdapter(context,mProvinceDatas);
		wv_province.setAdapter(provinceWheelAdapter);
		wv_province.setCyclic(false);
		wv_province.setVisibleItems(5);
		wv_province.setCurrentItem(0);

		AddressModel addressModel_city=mProvinceDatas[0];
		mCurrentProviceId = addressModel_city.id;
		cityWheelAdapter = new CityWheelAdapter(context,
				mCitisDatasMap.get(mCurrentProviceId));
		wv_city.setAdapter(cityWheelAdapter);
		wv_city.setCyclic(false);
		wv_city.setVisibleItems(5);


		if (mCitisDatasMap.get(mCurrentProviceId) == null){
			mCurrentCityId=null;
		}
		else {
			AddressModel addressModel_area=mCitisDatasMap.get(mCurrentProviceId)[0];
			mCurrentCityId=addressModel_area.id;
		}

		areaWheelAdapter = new AreaWheelAdapter(context,mAreaDatasMap.get(mCurrentCityId));
		wv_area.setAdapter(areaWheelAdapter);
		wv_area.setCyclic(false);
		wv_area.setVisibleItems(5);
		AddressModel addressModel=mAreaDatasMap.get(mCurrentCityId)[0];
		mCurrentAreaId=addressModel.id;


		provinceChangedListener = new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				AddressModel addressModel = mProvinceDatas[newValue];
				mCurrentProviceId = addressModel.id;
				cityWheelAdapter.setCityList(mCitisDatasMap.get(mCurrentProviceId));
				wv_city.setAdapter(cityWheelAdapter);
				wv_city.setCurrentItem(0);
				AddressModel[] array=mCitisDatasMap.get(mCurrentProviceId);
				AddressModel addressModel_city = array[0];
				mCurrentCityId=addressModel_city.id;
				areaWheelAdapter.setAreaList(mAreaDatasMap.get(mCurrentCityId));
				wv_area.setAdapter(areaWheelAdapter);
				wv_area.setCurrentItem(0);
				AddressModel addressModel_area=mAreaDatasMap.get(mCurrentCityId)[0];
				mCurrentAreaId=addressModel_area.id;




			}
		};
		wv_province.addChangingListener(provinceChangedListener);

		cityChangedListener = new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if (mCitisDatasMap.get(mCurrentProviceId) == null){
					mCurrentCityId=null;
				}
				else {
					AddressModel addressModel = mCitisDatasMap.get(mCurrentProviceId)[newValue];
					mCurrentCityId=addressModel.id;
				}
				areaWheelAdapter.setAreaList(mAreaDatasMap.get(mCurrentCityId));
				wv_area.setAdapter(areaWheelAdapter);
				wv_area.setCurrentItem(0);
				AddressModel addressModel_area=mAreaDatasMap.get(mCurrentCityId)[0];
				mCurrentAreaId=addressModel_area.id;
			}
		};
		wv_city.addChangingListener(cityChangedListener);

		areaChangedListener = new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if (mAreaDatasMap.get(mCurrentCityId) == null){
					mCurrentAreaId=null;
				}
				else {
					AddressModel addressModel = mAreaDatasMap.get(mCurrentCityId)[newValue];
					mCurrentAreaId=addressModel.id;
				}
			}
		};
		wv_area.addChangingListener(areaChangedListener);
	}

	private void initDatas() {
      String data= Tools.getJson(context, "address.json");
		try {
			JSONArray jsonArray=new JSONArray(data);
			mProvinceDatas = new AddressModel[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++)
			{

				JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
				String province = jsonP.getString("id");
				AddressModel addressModel = new AddressModel();
				addressModel.id=jsonP.getString("id");
				addressModel.code=jsonP.getString("code");
				addressModel.name=jsonP.getString("name");
				addressModel.pid=jsonP.getString("pid");
				addressModel.level=jsonP.getString("level");
				mProvinceDatas[i] = addressModel;

				JSONArray jsonCs = null;
				try
				{
					/**
					 * Throws JSONException if the mapping doesn't exist or is
					 * not a JSONArray.
					 */
					jsonCs = jsonP.getJSONArray("cities");
				} catch (Exception e1)
				{
					continue;
				}
				AddressModel[] mCitiesDatas = new AddressModel[jsonCs.length()];
				for (int j = 0; j < jsonCs.length(); j++)
				{
					JSONObject jsonCity = jsonCs.getJSONObject(j);
					String city_pid = jsonCity.getString("id");
					AddressModel addressModel_city = new AddressModel();
					addressModel_city.id=jsonCity.getString("id");
					addressModel_city.code=jsonCity.getString("code");
					addressModel_city.name=jsonCity.getString("name");
					addressModel_city.pid=jsonCity.getString("pid");
					addressModel_city.level=jsonCity.getString("level");
					mCitiesDatas[j] = addressModel_city;
					JSONArray jsonAreas = null;
					try
					{
						/**
						 * Throws JSONException if the mapping doesn't exist or
						 * is not a JSONArray.
						 */
						jsonAreas = jsonCity.getJSONArray("counties");
					} catch (Exception e)
					{
						continue;
					}

					AddressModel[] mAreasDatas = new AddressModel[jsonAreas.length()];// 当前市的所有区
					for (int k = 0; k < jsonAreas.length(); k++)
					{
						JSONObject jsonArea = jsonAreas.getJSONObject(k);
						AddressModel addressModel_area = new AddressModel();
						addressModel_area.id=jsonArea.getString("id");
						addressModel_area.code=jsonArea.getString("code");
						addressModel_area.name=jsonArea.getString("name");
						addressModel_area.pid=jsonArea.getString("pid");
						addressModel_area.level=jsonArea.getString("level");
						mAreasDatas[k] = addressModel_area;
					}
					mAreaDatasMap.put(city_pid, mAreasDatas);
				}

				mCitisDatasMap.put(province, mCitiesDatas);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取省市字符串
	 * 
	 * @return
	 */
	public String getLocal() {
		return wv_province.getCurrentItemValue() + " "
				+ wv_city.getCurrentItemValue()+" "+wv_area.getCurrentItemValue();
	}
	/**
	 * 获取省份的Id
	 * @return
	 */
	public String getProvinceId(){
		return mCurrentProviceId;
	}

	public String getProvince(){return wv_province.getCurrentItemValue(); }
	/**
	 * 获取城市的Id
	 * @return
	 */
	public String getCityId(){
		return mCurrentCityId;
	}

	public String getCity(){return wv_city.getCurrentItemValue(); }

	public String getAreaId(){
		return mCurrentAreaId;
	}

	public String getArea(){return wv_area.getCurrentItemValue(); }

}
