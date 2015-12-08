package com.cmbb.smartkids.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.model.PhotoAdd;

import java.util.ArrayList;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：15/9/1 下午15:50
 */
public class PhotoAddAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private ArrayList<PhotoAdd> photoContent = new ArrayList<>();

    private CustomListener.ItemClickListener picDeleteListener, picPreListener;


    public ArrayList<PhotoAdd> getPhotoContent() {
        return photoContent;
    }

    public void setPhotoContent(ArrayList<PhotoAdd> photoContent) {
        this.photoContent = photoContent;
    }

    public void removeData(int position){
        if (photoContent != null)
            photoContent.remove(position);
        notifyItemRemoved(position);
    }


    public CustomListener.ItemClickListener getPicDeleteListener() {
        return picDeleteListener;
    }

    public void setOnPicDeleteListener(CustomListener.ItemClickListener picDeleteListener) {
        this.picDeleteListener = picDeleteListener;
    }

    public CustomListener.ItemClickListener getPicPreListener() {
        return picPreListener;
    }

    public void setOnPicPreListener(CustomListener.ItemClickListener picPreListener) {
        this.picPreListener = picPreListener;
    }

    public void setPhotoUrls(ArrayList<String> photoUrls) {
        if (null == photoUrls || photoUrls.size() == 0) {
            return;
        } else {
            photoContent.clear();
            for (int i = 0; i < photoUrls.size(); i++) {
                photoContent.add(new PhotoAdd(photoUrls.get(i), ""));
            }
        }
    }


    public PhotoAddAdapter(Context context, ArrayList<PhotoAdd> data) {
        mContext = context;
        this.photoContent = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_image_item, null);
        return new PopmanAddPicViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PopmanAddPicViewHolder) holder).setData(mContext, photoContent, position, this);
    }

    @Override
    public int getItemCount() {
        return photoContent.size();
    }
}
