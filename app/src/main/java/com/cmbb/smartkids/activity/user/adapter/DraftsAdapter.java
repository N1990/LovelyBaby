package com.cmbb.smartkids.activity.user.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.holder.DraftsHolder;
import com.cmbb.smartkids.adapter.RecyclerViewCursorAdapter;
import com.cmbb.smartkids.base.CustomListener;

/**
 * Created by javon on 16/1/11.
 */
public class DraftsAdapter extends RecyclerViewCursorAdapter<DraftsHolder> {
    private CustomListener.ItemClickListener onItemListener;
    private CustomListener.ItemClickListener onItemLongListener;

    public DraftsAdapter(Context context) {
        super(context);
        setupCursorAdapter(null, 0, R.layout.list_drafts_item, false);
    }


    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public CustomListener.ItemClickListener getOnItemLongListener() {
        return onItemLongListener;
    }

    public void setOnItemLongListener(CustomListener.ItemClickListener onItemLongListener) {
        this.onItemLongListener = onItemLongListener;
    }

    @Override
    public DraftsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_drafts_item, parent, false);
        return new DraftsHolder(mCursorAdapter.newView(mContext, mCursorAdapter.getCursor(), parent));
    }

    @Override
    public void onBindViewHolder(DraftsHolder holder, int position) {
        // Move cursor to this position
        mCursorAdapter.getCursor().moveToPosition(position);

        // Set the ViewHolder
        setViewHolder(holder);

        // Bind this view
        mCursorAdapter.bindView(null, mContext, mCursorAdapter.getCursor());
        holder.setData(this);
    }

}
