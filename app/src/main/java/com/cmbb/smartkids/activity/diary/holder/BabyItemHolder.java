package com.cmbb.smartkids.activity.diary.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.model.BabyListModel;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.utils.FrescoTool;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 14:25
 */
public class BabyItemHolder extends BaseViewHolder<BabyListModel.DataEntity.RowsEntity> {
    private final String TAG = BabyItemHolder.class.getSimpleName();

    private SimpleDraweeView sdvHeader;
    private TextView tvName, tvAge, tvRecord;

    public BabyItemHolder(ViewGroup parent) {
        super(parent, R.layout.list_mybaby_item);
        sdvHeader = $(R.id.sdv_baby_diary_item);
        tvName = $(R.id.tv_nickname_baby_diary_item);
        tvAge = $(R.id.tv_age_baby_diary_item);
        tvRecord = $(R.id.tv_record_baby_diary_item);
    }

    public void setData(BabyListModel.DataEntity.RowsEntity data) {
        if (data == null)
            return;
        FrescoTool.loadImage(sdvHeader, data.getBabyImg());
        tvName.setText(data.getBabyNike());
        if (Integer.parseInt(data.getBabySex()) == 1) {
            tvName.setCompoundDrawablesWithIntrinsicBounds(tvName.getContext().getResources().getDrawable(R.mipmap.btn_male_boy_bg), null, null, null);
        } else {
            tvName.setCompoundDrawablesWithIntrinsicBounds(tvName.getContext().getResources().getDrawable(R.mipmap.btn_male_girl_bg), null, null, null);
        }
        tvAge.setText(data.getAge());
        tvRecord.setText(data.getDiaryCount() > 999 ? "999+" : data.getDiaryCount() + "");
    }
}
