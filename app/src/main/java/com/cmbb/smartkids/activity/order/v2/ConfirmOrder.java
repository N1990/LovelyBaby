package com.cmbb.smartkids.activity.order.v2;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.model.SecurityCodeModel;
import com.cmbb.smartkids.activity.order.model.SubmitOrderModel;
import com.cmbb.smartkids.activity.serve.v2.PopuGridAdapter;
import com.cmbb.smartkids.activity.serve.v2.model.H5ServiceDetailModel;
import com.cmbb.smartkids.activity.serve.v2.model.ReserveModel;
import com.cmbb.smartkids.activity.user.DeliveryAddressListActivity;
import com.cmbb.smartkids.activity.user.model.DeliveryAddressListModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.db.DBContent;
import com.cmbb.smartkids.network.OkHttpClientManager;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.MyTimeCount;
import com.cmbb.smartkids.utils.Tools;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.okhttp.Request;
import com.umeng.socialize.utils.Log;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/2/25 下午4:34
 */
public class ConfirmOrder extends BaseActivity {
    private static final String TAG = ConfirmOrder.class.getSimpleName();
    private final int CHOOSE_ADDRESS_REQUEST = 10101;
    private final int MODIFY_PHONE_REQUEST = 10111;

    private RelativeLayout llHomeServiceItem;
    private SimpleDraweeView sdPic;
    private TextView tvTitle;
    private TextView tvAddress;
    private TextView tvPrice;
    private TextView tvCount;
    private TextView tvEdit;
    private RelativeLayout relativeLayout;
    private LinearLayout llContactPerson;
    private TextView tvNickTag;
    private TextView tvNick;
    private TextView tvOperation;
    private LinearLayout llContactPhone;
    private TextView tvPhoneTag;
    private TextView tvPhone;
    private TextView tvPhoneChange;
    private LinearLayout llContactAddress;
    private TextView tvAddressTag;
    private TextView tvAdd;
    private TextView tvAddressManager;
    private TextView tvTotalPrice;
    private TextView tvWholePrice;
    private TextView tvSubmit;
    private LinearLayout llBottom;
    private LinearLayout llContent;

    PopupWindow mEditPopupWindow;// 编辑的popup
    private RelativeLayout rlBg;
    private TextView tvType;
    private SmartRecyclerView editRecyclerView;
    private TextView tvEditTitle;
    private TextView tvEditPrice;
    private TextView tvEditJian;
    private TextView tvEditCount;
    private TextView tvEditAdd;
    PopuGridAdapter popuGridAdapter;
    H5ServiceDetailModel h5ServiceDetailModel;
    H5ServiceDetailModel.PriceListEntity priceListEntity;//选中的对象
    ReserveModel.DataEntity  reserveEntity;
    DeliveryAddressListModel.DataEntity.RowsEntity local;
    private String receiverName;
    private String receiverPhone;
    private String postCode;
    private String address;

    @Override
    protected void init(Bundle savedInstanceState) {
        priceListEntity = getIntent().getParcelableExtra("priceListEntity");
        h5ServiceDetailModel = getIntent().getParcelableExtra("DetailModel");
        reserveEntity = getIntent().getParcelableExtra("dataEntity");
        setTitle(getString(R.string.title_comfire_order));
        initView();
        initPopup(h5ServiceDetailModel.getPriceList());
        FrescoTool.loadImage(sdPic, reserveEntity.getServiceInfo().getServicesImg(), 1.67f);
        tvTitle.setText(reserveEntity.getServiceInfo().getTitle());
        tvAddress.setText(reserveEntity.getServiceInfo().getCityText());
        tvPrice.setText("RMB " + reserveEntity.getServicePrice());
        tvCount.setText("x" + reserveEntity.getBuyCount());
        tvTotalPrice.setText(reserveEntity.getPrice());
        tvNick.setText(reserveEntity.getReceiveName());
        String phone = reserveEntity.getReceivePhone();
        if (!TextUtils.isEmpty(phone))
            tvPhone.setText(phone);
        address = reserveEntity.getAddress();
        tvAdd.setText(address);
        receiverName = reserveEntity.getReceiveName();
        receiverPhone = reserveEntity.getReceivePhone();
        postCode = reserveEntity.getPostCode();

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_comfirm_order_layout;
    }

    private void initPopup(final List<H5ServiceDetailModel.PriceListEntity> data) {
        View view = LayoutInflater.from(this).inflate(R.layout.popup_order_edit, null);
        tvType = (TextView) view.findViewById(R.id.tv_type);
        editRecyclerView = (SmartRecyclerView) view.findViewById(R.id.edit_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        editRecyclerView.setLayoutManager(gridLayoutManager);
        popuGridAdapter = new PopuGridAdapter(this);
        popuGridAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                for (H5ServiceDetailModel.PriceListEntity entity : data) {
                    entity.setSelected(false);
                }
                priceListEntity = popuGridAdapter.getItem(position);
                priceListEntity.setSelected(true);
                // 设置界面
                tvEditTitle.setText(priceListEntity.getSpecification());
                tvEditCount.setText(priceListEntity.getCount() + "");
                tvEditPrice.setText("RMB " + priceListEntity.getPrice());
                tvWholePrice.setText("RMB " + priceListEntity.getAll());
                popuGridAdapter.notifyDataSetChanged();
            }
        });
        popuGridAdapter.clear();
        popuGridAdapter.addAll(data);
        editRecyclerView.setAdapter(popuGridAdapter);
        rlBg = (RelativeLayout) view.findViewById(R.id.rl_bg);
        rlBg.setOnClickListener(this);//取消popu
        tvEditTitle = (TextView) view.findViewById(R.id.tv_edit_title);
        tvEditPrice = (TextView) view.findViewById(R.id.tv_edit_price);
        tvEditJian = (TextView) view.findViewById(R.id.tv_edit_jian);
        tvEditCount = (TextView) view.findViewById(R.id.tv_edit_count);
        tvWholePrice = (TextView) view.findViewById(R.id.tv_edit_whole_price);
        view.findViewById(R.id.tv_edit_submit).setOnClickListener(this);
        tvEditJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 数量减小
                if (Integer.parseInt(tvEditCount.getText().toString()) != 0) {
                    tvEditCount.setText(Integer.parseInt(tvEditCount.getText().toString()) - 1 + "");
                    priceListEntity.setCount(Integer.parseInt(tvEditCount.getText().toString()));
                    if (!tvEditCount.getText().toString().equals("0")) {
                        tvWholePrice.setText("RMB " + Integer.parseInt(tvEditCount.getText().toString()) * priceListEntity.getPrice());
                    } else {
                        tvWholePrice.setText("RMB 0");
                    }
                }
            }
        });
        tvEditAdd = (TextView) view.findViewById(R.id.tv_edit_add);
        tvEditAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数量增加
                if (Integer.parseInt(tvEditCount.getText().toString()) != priceListEntity.getStock()) {
                    tvEditCount.setText(Integer.parseInt(tvEditCount.getText().toString()) + 1 + "");
                    priceListEntity.setCount(Integer.parseInt(tvEditCount.getText().toString()));
                    if (!tvEditCount.getText().toString().equals("0")) {
                        tvWholePrice.setText("RMB " + Integer.parseInt(tvEditCount.getText().toString()) * priceListEntity.getPrice());
                    } else {
                        tvWholePrice.setText("RMB 0");
                    }
                }
            }
        });
        mEditPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mEditPopupWindow.setOutsideTouchable(true);
        mEditPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mEditPopupWindow.setTouchable(true);
    }


    private void initView() {
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom);
        llContent = (LinearLayout) findViewById(R.id.ll_content);
        llHomeServiceItem = (RelativeLayout) findViewById(R.id.ll_home_service_item);
        sdPic = (SimpleDraweeView) findViewById(R.id.sd_pic);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvCount = (TextView) findViewById(R.id.tv_count);
        tvEdit = (TextView) findViewById(R.id.tv_edit);
        tvEdit.setOnClickListener(this);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        llContactPerson = (LinearLayout) findViewById(R.id.ll_contact_person);
        tvNickTag = (TextView) findViewById(R.id.tv_nick_tag);
        tvNick = (TextView) findViewById(R.id.tv_nick);
        tvOperation = (TextView) findViewById(R.id.tv_operation);
        llContactPhone = (LinearLayout) findViewById(R.id.ll_contact_phone);
        tvPhoneTag = (TextView) findViewById(R.id.tv_phone_tag);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvPhone.setEnabled(false);
        tvPhoneChange = (TextView) findViewById(R.id.tv_phone_change);
        tvPhoneChange.setOnClickListener(this);
        llContactAddress = (LinearLayout) findViewById(R.id.ll_contact_address);
        tvAddressTag = (TextView) findViewById(R.id.tv_address_tag);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        tvAdd.setTag(reserveEntity.getAddressId());
        tvAdd.setEnabled(false);
        tvAddressManager = (TextView) findViewById(R.id.tv_address_manager);
        tvAddressManager.setOnClickListener(this);
        tvTotalPrice = (TextView) findViewById(R.id.tv_whole_price);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edit:// 编辑
                int[] location = new int[2];
                llBottom.getLocationOnScreen(location);
                mEditPopupWindow.showAtLocation(llBottom, Gravity.NO_GRAVITY, location[0], location[1] - mEditPopupWindow.getHeight());
                break;
            case R.id.tv_edit_submit:
                tvCount.setText("x" +tvEditCount.getText().toString());
                tvTotalPrice.setText(tvWholePrice.getText().toString());
                break;
            case R.id.tv_phone_change:// 修改手机号码
                SavePhoneActivity.newInstance(ConfirmOrder.this, MODIFY_PHONE_REQUEST);
                break;
            case R.id.tv_address_manager:// 地址管理
                int deliveryAddressId = (int) tvAdd.getTag();
                DeliveryAddressListActivity.skipFromActivity(ConfirmOrder.this, deliveryAddressId, CHOOSE_ADDRESS_REQUEST);
                break;
            case R.id.tv_submit://确认
                String receiverContact = tvNick.getText().toString();
                String Phone = tvPhone.getText().toString();
                if(TextUtils.isEmpty(receiverContact)){
                    showShortToast("联系人名字不能为空");
                    return;
                }
                if(!Tools.isMobileNo(Phone)){
                    showShortToast("请正确填写你的手机号码");
                    return;
                }
                showWaitDialog();
                SubmitOrderModel.postSubmitOrderRequest(reserveEntity.getServiceId(), reserveEntity.getPriceId(), reserveEntity.getBuyCount(), Phone, receiverName, receiverPhone, postCode, address, new OkHttpClientManager.ResultCallback<SubmitOrderModel>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        hideWaitDialog();
                        showShortToast(e.toString());
                    }

                    @Override
                    public void onResponse(SubmitOrderModel response) {
                        hideWaitDialog();
                        if(response != null)
                            PayConfirm.newInstance(ConfirmOrder.this, response.getData());
                        showShortToast(response.getMsg());
                    }
                });
                break;
            case R.id.rl_bg:
                mEditPopupWindow.dismiss();
                break;
        }
    }








    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CHOOSE_ADDRESS_REQUEST && resultCode == RESULT_OK){
            local = data.getParcelableExtra("delivery_address");
            if(local != null) {
                receiverName = local.getReceiveName();
                receiverPhone = local.getReceivePhone();
                postCode = local.getPostCode();
                address = local.getProvinceText() + local.getCityText() + local.getDistrictText() + local.getAddress();
                tvAdd.setTag(local.getId());
                tvAdd.setText(local.getProvinceText() + local.getCityText() + local.getDistrictText() + local.getAddress());
            }
        }else if(requestCode == MODIFY_PHONE_REQUEST && resultCode == RESULT_OK){
            String phone = data.getStringExtra("phone");
            tvPhone.setText(phone+"");
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }















    /**
     * @param context
     * @param dataEntity      返回数据
     */
    public static void newIntent(Context context, H5ServiceDetailModel h5ServiceDetailModel, H5ServiceDetailModel.PriceListEntity priceListEntity, ReserveModel.DataEntity dataEntity) {
        Intent intent = new Intent(context, ConfirmOrder.class);
        intent.putExtra("DetailModel", h5ServiceDetailModel);
        intent.putExtra("priceListEntity", priceListEntity);
        intent.putExtra("dataEntity", dataEntity);
        context.startActivity(intent);
    }
}
