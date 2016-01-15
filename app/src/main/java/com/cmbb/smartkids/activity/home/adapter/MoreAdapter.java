package com.cmbb.smartkids.activity.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.CustomListener;

import java.util.ArrayList;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/1 19:41
 */
public class MoreAdapter extends RecyclerView.Adapter {
    private final int SPECIAL = 0;
    private final int NORMAL = 1;
    private ArrayList<String> data;
    private CustomListener.ItemClickListener onItemListener;

    public MoreAdapter() {
        data = new ArrayList<>();
        data.add("意见反馈");
        data.add("成长值说明");
        //data.add("等级说明");
        //data.add("积分说明");
        //data.add("金币说明");
//        data.add("使用说明");
        //data.add("达人申请说明");
        data.add("关于萌宝派");
        data.add("清除缓存");
    }

    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == data.size() - 1) {
            return SPECIAL;
        } else {
            return NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == SPECIAL) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_more_special, null);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_more_item, null);
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String title = data.get(position);
        ((ViewHolder) holder).setData(title, position, this);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl_more_item);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_more_title_item);
        }

        public void setData(final String title, final int position, final MoreAdapter adapter) {
            tvTitle.setText(title);
            rl.setTag(position);
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapter.getOnItemListener() != null)
                        adapter.getOnItemListener().onItemClick(v, position, title);
                }
            });
        }
    }

}
