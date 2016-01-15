package com.cmbb.smartkids.activity.community.holder;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.adapter.ReverCommentAdapter;
import com.cmbb.smartkids.activity.community.model.ReplayDetailListModel;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.date.JTimeTransform;
import com.cmbb.smartkids.utils.date.RecentDateFormat;


/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/6 10:11
 */
public class ReverHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ReverCommentAdapter adapter;
    private int position;
    private View root;

    private TextView tv;
    private ImageView iv;

    public ReverHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv_rever_comment_name_item);
        iv = (ImageView) itemView.findViewById(R.id.iv_rever_comment_delete_item);
    }

    public void setData(Object data, ReverCommentAdapter adapter, int position) {
        this.adapter = adapter;
        this.position = position;
        tv.setTag(((ReplayDetailListModel.DataEntity.RowsEntity) data).getImg());
        setReverContent(tv, (ReplayDetailListModel.DataEntity.RowsEntity) data);
        switch (((ReplayDetailListModel.DataEntity.RowsEntity) data).getUserBasicInfo().getIsLoginUser()) {
            case 1:
                iv.setVisibility(View.VISIBLE);
                iv.setTag(((ReplayDetailListModel.DataEntity.RowsEntity) data).getId());
                iv.setOnClickListener(this);
                break;
            case 0:
                iv.setVisibility(View.GONE);
                break;
        }

    }


    @Override
    public void onClick(View v) {
        if (adapter.getOnDeleteListener() != null)
            adapter.getOnDeleteListener().onItemClick(v, position, position);
    }


    private void setReverContent(TextView tv, ReplayDetailListModel.DataEntity.RowsEntity data) {
        String source1 = "";
        String name1 = data.getUserBasicInfo().getUserNike() + " : ";
        String title1 = data.getContents() + "  ";
        String time1 = new JTimeTransform(data.getCreateDate()).toString(new RecentDateFormat()) + "  ";
        source1 += name1 + title1 + time1;
        SpannableString ss1 = new SpannableString(source1);
        ss1.setSpan(new ForegroundColorSpan(Color.BLUE), 0, name1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss1.setSpan(new AbsoluteSizeSpan(12, true), name1.length() + title1.length(), name1.length() + title1.length() + time1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Drawable drawable1 = tv.getContext().getResources().getDrawable(R.mipmap.btn_community_preview_bg);
        drawable1.setBounds(0, 0, TDevice.dip2px(16, tv.getContext()), TDevice.dip2px(16, tv.getContext()));
        if (!TextUtils.isEmpty(data.getImg())) {
            ImageSpan is1 = new ImageSpan(drawable1);
            ss1.setSpan(is1, ss1.length() - 1, ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            MyClickableSpan clickSpan1 = new MyClickableSpan();
            ss1.setSpan(clickSpan1, ss1.length() - 1, ss1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(ss1);
    }

    class MyClickableSpan extends ClickableSpan {

        @Override
        public void onClick(View widget) {
            String tag = (String) widget.getTag();
            Log.e("ReverHolder", tag + "111");
            if (adapter.getOnReverPreListener() != null) {
                adapter.getOnReverPreListener().onItemClick(widget, position, widget.getTag());
            }
        }
    }

}
