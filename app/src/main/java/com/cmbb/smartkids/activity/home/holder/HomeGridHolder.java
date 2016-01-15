package com.cmbb.smartkids.activity.home.holder;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.home.adapter.HomeFraAdapter;
import com.cmbb.smartkids.activity.home.model.RecommonedEredarModel;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 14:25
 */
public class HomeGridHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private LinearLayout llContent;
    private ArrayList<RecommonedEredarModel> data;
    private Context context;
    private HomeFraAdapter adapter;

    public HomeGridHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        llContent = (LinearLayout) itemView.findViewById(R.id.ll_home_fra_grid);
    }

    public void setData(ArrayList<RecommonedEredarModel> imgs, HomeFraAdapter adapter) {
        this.adapter = adapter;
        //adapter加载数据源改变，多次刷新
        if (llContent != null && llContent.getChildCount() != 0) {
            recycleImagePic(llContent);
            llContent.removeAllViews();
        }

        if (imgs != null) {
            this.data = imgs;
            int index = 0;
            for (RecommonedEredarModel pman : data) {
               /* LinearLayout.LayoutParams ps = new LinearLayout.LayoutParams(-2, -2);
                if (index != data.size() - 1) {
                    ps.rightMargin = TDevice.dip2px(10, context);
                    ps.leftMargin = TDevice.dip2px(10, context);
                    ps.bottomMargin = TDevice.dip2px(10, context);
                    ps.topMargin = TDevice.dip2px(10, context);
                }*/
                View child = LayoutInflater.from(context).inflate(R.layout.list_home_gird_item, null);
                TextView tv = (TextView) child.findViewById(R.id.tv_home_fra_grid_item);
                SimpleDraweeView iv = (SimpleDraweeView) child.findViewById(R.id.iv_home_fra_grid_item);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(TDevice.dip2px(48, context), TDevice.dip2px(48, context));
                iv.setLayoutParams(params);
                tv.setText(pman.getUserNike());
                //PicassoTools.loadCircleImage(context, iv, pman.getUserSmallImg(), true);
                FrescoTool.loadImage(iv, pman.getUserSmallImg());
                //child.setLayoutParams(ps);
                child.setTag(index);
                child.setOnClickListener(this);
                llContent.addView(child);
                index++;
            }
        }

    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        if (adapter.getOnGridItemClick() != null)
            adapter.getOnGridItemClick().onItemClick(v, index, data.get(index));
    }

    private void recycleImagePic(ViewGroup root) {
        int count = root.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = root.getChildAt(i);
            if (child instanceof ImageView)
                recycleBitmap((ImageView) child);
        }
    }

    private void recycleBitmap(ImageView view) {
        if (view == null)
            return;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) view.getDrawable();
        if (bitmapDrawable != null) {
            view.setImageBitmap(null);
            // 如果图片还未回收，先强制回收该图片
            if (bitmapDrawable.getBitmap() != null
                    && !bitmapDrawable.getBitmap().isRecycled()) {
                //                Log.i(TAG, "图片回收");
                bitmapDrawable.getBitmap().recycle();
            }
        }
    }
}
