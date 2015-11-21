package com.cmbb.smartkids.activity.search;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.utils.FrescoTool;
import com.cmbb.smartkids.utils.TDevice;
import com.cmbb.smartkids.utils.log.Log;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 17:15
 */
public class SearchUserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final String TAG = SearchUserHolder.class.getSimpleName();

    private SimpleDraweeView sdvSearchUserHead;
    private TextView tvSearchUserNick;
    private RatingBar rbSearchUserPerssion;
    private TextView tvSearchUserTag;
    private TextView tvFans;

    private SearchUserAdapter adapter;
    private View root;
    private int position;

    public SearchUserHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        sdvSearchUserHead = (SimpleDraweeView) itemView.findViewById(R.id.sdv_search_user_head);
        tvSearchUserNick = (TextView) itemView.findViewById(R.id.tv_search_user_nick);
        rbSearchUserPerssion = (RatingBar) itemView.findViewById(R.id.rb_search_user_perssion);
        tvSearchUserTag = (TextView) itemView.findViewById(R.id.tv_search_user_tag);
        tvFans = (TextView) itemView.findViewById(R.id.tv_fans);
    }

    public void setData(SearchUserModel.DataEntity.RowsEntity data, SearchUserAdapter adapter, int position) {
        this.adapter = adapter;
        this.position = position;
        Log.e(TAG, "header : " + data.getUserSmallImg());
        FrescoTool.loadImage(sdvSearchUserHead, data.getUserSmallImg());
        tvSearchUserNick.setVisibility(View.VISIBLE);
        if(!TextUtils.isEmpty(data.getUserNike())){
            tvSearchUserNick.setText(data.getUserNike());
        }else{
            tvSearchUserNick.setVisibility(View.INVISIBLE);
        }
        rbSearchUserPerssion.setRating(data.getUserLevel());
        tvSearchUserTag.setText(data.getUserRole().get(0).getEredarName());
        tvFans.setText("Fans(" + data.getFans() + ")");
        this.root.setOnClickListener(this);
        this.root.setTag(data);
    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, v.getTag());
    }
}
