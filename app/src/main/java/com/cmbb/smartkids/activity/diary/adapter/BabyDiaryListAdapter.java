package com.cmbb.smartkids.activity.diary.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.diary.holder.BabyDiaryListHolder;
import com.cmbb.smartkids.activity.diary.model.BabyDiaryListModel;
import com.cmbb.smartkids.activity.diary.model.BabyListModel;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.Tools;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.javon.loadmorerecyclerview.BaseRecyclerAdapter;


/**
 * Created by javon on 15/12/18.
 */
public class BabyDiaryListAdapter extends BaseRecyclerAdapter {
    private final int HEADER = -1;
    private View.OnClickListener onPublishListener;
    private CustomListener.ItemClickListener onItemListener;
    private BabyListModel.DataEntity.RowsEntity babyInfo;
    private int tag; //记录点什么



    public void setHeaderData(BabyListModel.DataEntity.RowsEntity babyInfo, int tag){
        this.babyInfo = babyInfo;
        this.tag = tag;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (dataList != null) {
            if (position == dataList.size() + 1) {
                return FOOTER;
            } else if(position == 0){
                return HEADER;
            } else{
                return NORMAL;
            }
        } else{
            if(position == 0){
                return HEADER;
            }else{
                return FOOTER;
            }
        }
    }

    @Override
    protected RecyclerView.ViewHolder onCustomViewHolder(ViewGroup parent, int viewType) {
        if(viewType == HEADER){
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_baby_diary_header_item, parent, false);
            return new HeaderHolder(root);
        }else{
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_baby_diary_item, parent, false);
            return new BabyDiaryListHolder(root);
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position != dataList.size() + 1)
            onBindCustomViewHolder(holder, position);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  HeaderHolder){
            ((HeaderHolder) holder).setData(babyInfo, this, tag);
        }else if(holder instanceof BabyDiaryListHolder){
            ((BabyDiaryListHolder) holder).setData((BabyDiaryListModel.DataEntity.RowsEntity) dataList.get(position - 1), this, position);
        }
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 2;
        } else {
            return dataList.size() + 2;
        }
    }


    public View.OnClickListener getOnPublishListener() {
        return onPublishListener;
    }

    public void setOnPublishListener(View.OnClickListener onPublishListener) {
        this.onPublishListener = onPublishListener;
    }

    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    class HeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private SimpleDraweeView bg, ivHeader;
        private TextView tvBirth, tvPublish;
        private BabyDiaryListAdapter adapter;

        public HeaderHolder(View itemView) {
            super(itemView);
            bg = (SimpleDraweeView) itemView.findViewById(R.id.iv_baby_diary_bg_header_item);
            ivHeader = (SimpleDraweeView) itemView.findViewById(R.id.iv_baby_diary_header_item);
            tvBirth = (TextView) itemView.findViewById(R.id.tv_baby_diary_birthday_header_item);
            tvPublish = (TextView) itemView.findViewById(R.id.tv_baby_diary_publish_header_item);
        }

        public void setData(BabyListModel.DataEntity.RowsEntity babyInfo, BabyDiaryListAdapter adapter, int tag){
            this.adapter = adapter;
            Uri uri = Uri.parse("res://com.cmbb.smartkids/" + R.mipmap.baby_diary_bg);
            bg.setAspectRatio(1.33f);
            Log.e("Image", "Image = " + uri.toString());
            ImageRequest request = ImageRequestBuilder
                    .newBuilderWithSource(uri)
                    .setProgressiveRenderingEnabled(true)
                    .build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(bg.getController())
                    .build();
            bg.setController(controller);
//            FrescoTool.loadImage(bg, babyInfo.getBabyImg(), 1.33f);
            FrescoTool.loadImage(ivHeader, babyInfo.getBabyImg(), TDevice.dip2px(110, ivHeader.getContext()));
            try {
                String birthday = Tools.DataToString(babyInfo.getBabyBirthday(), "yy年MM月dd日");
                tvBirth.setText(birthday);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(tag == -1){
                tvPublish.setVisibility(View.VISIBLE);
                tvPublish.setOnClickListener(this);
            }else{
                tvPublish.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            if(adapter.getOnPublishListener() != null)
                adapter.getOnPublishListener().onClick(v);
        }
    }
}
