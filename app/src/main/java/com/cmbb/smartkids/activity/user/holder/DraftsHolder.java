package com.cmbb.smartkids.activity.user.holder;

import android.content.ContentUris;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.model.ImageModel;
import com.cmbb.smartkids.activity.user.adapter.DraftsAdapter;
import com.cmbb.smartkids.activity.user.model.DraftsModel;
import com.cmbb.smartkids.adapter.RecyclerViewCursorViewHolder;
import com.cmbb.smartkids.db.DBContent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by javon on 16/1/11.
 */
public class DraftsHolder extends RecyclerViewCursorViewHolder implements View.OnClickListener, View.OnLongClickListener{
    private DraftsAdapter adapter;
    private TextView tvTitle, tvTime;
    private int position;
    private DraftsModel draftsModel;

    public DraftsHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_drafts_title_item);
        tvTime = (TextView) itemView.findViewById(R.id.tv_drafts_time_item);
    }


    @Override
    public void bindCursor(Cursor cursor) {
        this.position = cursor.getPosition();
        draftsModel = new DraftsModel();
        draftsModel.setId(cursor.getInt(cursor.getColumnIndex("_id")));
        draftsModel.setTitle(cursor.getString(cursor.getColumnIndex(DBContent.DBTopic.TOPIC_TITLE)));
        draftsModel.setSort(cursor.getString(cursor.getColumnIndex(DBContent.DBTopic.TOPIC_SORT)));
        draftsModel.setSortValue(cursor.getString(cursor.getColumnIndex(DBContent.DBTopic.TOPIC_SORT_VALUE)));
        draftsModel.setUserId(cursor.getInt(cursor.getColumnIndex(DBContent.DBTopic.TOPIC_USER_ID)));
        draftsModel.setContent(cursor.getString(cursor.getColumnIndex(DBContent.DBTopic.TOPIC_CONTENT)));
        draftsModel.setTime(cursor.getString(cursor.getColumnIndex(DBContent.DBTopic.TOPIC_TIME)));

        String imagesStr = cursor.getString(cursor.getColumnIndex(DBContent.DBTopic.TOPIC_TELETEXTS));
        if(!TextUtils.isEmpty(imagesStr)){
            ArrayList<ImageModel> realImages = new ArrayList<>();
            Gson gson = new Gson();
            ArrayList<ImageModel> images = gson.fromJson(imagesStr, new TypeToken<ArrayList<ImageModel>>() {
            }.getType());
            for (ImageModel image : images){
                File file = new File(image.getImgUrl());
                if(file.exists()){
                    realImages.add(image);
                }
            }
            draftsModel.setList(realImages);
        }
        if(!TextUtils.isEmpty(draftsModel.getTitle())){
            tvTitle.setText(draftsModel.getTitle());
        }else if(!TextUtils.isEmpty(draftsModel.getContent())){
            tvTitle.setText(draftsModel.getContent());
        }else{
            tvTitle.setText("# 图片 #");
        }
        tvTime.setText(draftsModel.getTime());
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setData(DraftsAdapter adapter){
        this.adapter = adapter;


    }

    @Override
    public void onClick(View v) {
        if(adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, draftsModel);
    }

    @Override
    public boolean onLongClick(View v) {
        if(adapter.getOnItemLongListener() != null)
            adapter.getOnItemLongListener().onItemClick(v, position, draftsModel);
        return true;
    }
}
