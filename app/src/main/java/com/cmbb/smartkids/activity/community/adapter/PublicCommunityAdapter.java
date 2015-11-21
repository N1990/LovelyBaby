package com.cmbb.smartkids.activity.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.community.holder.PublicItemHolder;
import com.cmbb.smartkids.activity.community.model.ImageModel;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.utils.log.Log;

import java.util.ArrayList;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/18 17:33
 */
public class PublicCommunityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = PublicCommunityAdapter.class.getSimpleName();

    private ArrayList<ImageModel> data;
    private final int FOOTER = 111;
    private final int NORMAL = 222;
    private int curPos = 0;

    private View.OnClickListener onFootListener;
    private CustomListener.ItemClickListener onItemListener;
    private CustomListener.ItemClickListener onItemDeleteListener;
    private CustomListener.ItemClickListener onItemZoomListener;


    public void setData(ArrayList<ImageModel> data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public ArrayList<ImageModel> getData() {
        return data;
    }

    public void addData(ArrayList<ImageModel> data) {
        if (data != null) {
            this.data.addAll(data);
        } else {
            this.data = new ArrayList<>();
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addData(ImageModel data) {
        if (data != null) {
            this.data.add(data);
        } else {
            this.data = new ArrayList<>();
            this.data.add(data);
        }
        notifyDataSetChanged();
    }

    public void removeData(int poisition) {
        if (data != null) {
            this.data.remove(poisition);
            notifyDataSetChanged();
        }
    }

    public void updateData(int position, ImageModel model) {
        this.data.set(position, model);
        notifyItemChanged(position);
    }

    public void setCurPos(int curPos) {
        this.curPos = curPos;
    }

    public int getCurPos() {
        return curPos;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.size() == 0 || position == data.size()) {
            return FOOTER;
        } else {
            return NORMAL;
        }
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder");
        if (viewType == FOOTER) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_public_communuty_add_item, parent, false);
            return new FootHolder(root);
        } else {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_public_communuty_item, parent, false);
            return new PublicItemHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e(TAG, "onCreateViewHolder : " + position);
        if (holder instanceof PublicItemHolder) {
            ((PublicItemHolder) holder).setData(this, position, data.get(position));
        } else if (holder instanceof FootHolder) {
            ((FootHolder) holder).setData(this);
            Log.e(TAG, data.size() + "");
            if (data.size() == 10) {
                ((FootHolder) holder).setViewDismiss();
            } else {
                ((FootHolder) holder).setViewVisiable();
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.data.size() + 1;
    }


    public View.OnClickListener getOnFootListener() {
        return onFootListener;
    }

    public void setOnFootListener(View.OnClickListener onFootListener) {
        this.onFootListener = onFootListener;
    }

    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public CustomListener.ItemClickListener getOnItemDeleteListener() {
        return onItemDeleteListener;
    }

    public void setOnItemDeleteListener(CustomListener.ItemClickListener onItemDeleteListener) {
        this.onItemDeleteListener = onItemDeleteListener;
    }

    public CustomListener.ItemClickListener getOnItemZoomListener() {
        return onItemZoomListener;
    }

    public void setOnItemZoomListener(CustomListener.ItemClickListener onItemZoomListener) {
        this.onItemZoomListener = onItemZoomListener;
    }

    class FootHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View root;
        private ImageView ivAdd;
        private PublicCommunityAdapter adapter;

        public FootHolder(View itemView) {
            super(itemView);
            this.root = itemView;
            ivAdd = (ImageView) itemView.findViewById(R.id.iv_public_community_add_item);
            ivAdd.setOnClickListener(this);
        }

        public void setData(PublicCommunityAdapter adapter) {
            this.adapter = adapter;
        }

        public void setViewDismiss() {
            this.root.setVisibility(View.GONE);
        }

        public void setViewVisiable() {
            this.root.setVisibility(View.VISIBLE);
        }


        @Override
        public void onClick(View v) {
            if (adapter.getOnFootListener() != null)
                adapter.getOnFootListener().onClick(v);
        }
    }


}
