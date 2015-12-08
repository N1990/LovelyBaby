package com.cmbb.smartkids.widget.popWindows;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.utils.TDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/6 15:06
 */
public class ReverPop extends PopupWindow {
    private Context context;
    private RecyclerView rv;
    private CustomListener.ItemClickListener onItemListener;
    private int id;
    SimpleAdapter adapter;

    public int getTag() {
        return id;
    }

    public void setTag(int id) {
        this.id = id;
    }

    public ReverPop(Context context, int[] resIds, String[] titles){
        this.context = context;
        initPopWindow(resIds, titles);
        addListener();
    }


    public CustomListener.ItemClickListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    /**
     * 初始化popWindow
     * */
    private void initPopWindow(int[] resIds, String[] titles) {
        View popView = LayoutInflater.from(context).inflate(R.layout.view_pop_recyclerview, null);
        setContentView(popView);
        setHeight(TDevice.dip2px(40, context));
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new ColorDrawable(0));
        //设置popwindow出现和消失动画
//        popupWindow.setAnimationStyle(R.style.PopMenuAnimation);
        rv = (RecyclerView) popView.findViewById(R.id.rv_pop_rever);
        rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < resIds.length; i ++){
            HashMap<String, Object> entry = new HashMap<>();
            entry.put("img", resIds[i]);
            entry.put("title", titles[i]);
            data.add(entry);
        }
        adapter = new SimpleAdapter(data);
        rv.setAdapter(adapter);
    }

    /**
     * 显示popWindow
     * */
    public void showPop(View parent, int x, int y) {
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        showAtLocation(parent, Gravity.NO_GRAVITY, location[0] - TDevice.dip2px(160, context) + x, location[1] + y);
        //设置popwindow显示位置
//        showAsDropDown(parent, x, y);
        //获取popwindow焦点
        setFocusable(true);
        //设置popwindow如果点击外面区域，便关闭。
        setOutsideTouchable(true);
        update();
    }




    private void addListener(){
        adapter.setOnItemListener(new CustomListener.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Object object) {
                if (onItemListener != null)
                    onItemListener.onItemClick(v, position, id);
            }
        });
    }





    class SimpleAdapter extends RecyclerView.Adapter<SimpleHolder>{

        private ArrayList<Map<String, Object>> data;

        private CustomListener.ItemClickListener onItemListener;


        public CustomListener.ItemClickListener getOnItemListener() {
            return onItemListener;
        }

        public void setOnItemListener(CustomListener.ItemClickListener onItemListener) {
            this.onItemListener = onItemListener;
        }

        public SimpleAdapter(ArrayList<Map<String, Object>> data){
            if(data != null){
                this.data = data;
            }else{
                this.data = new ArrayList<>();
            }
        }

        @Override
        public SimpleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pop_item, parent, false);
            return new SimpleHolder(root);
        }

        @Override
        public void onBindViewHolder(SimpleHolder holder, int position) {
            holder.setData(this, position, data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }



    class SimpleHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView iv;
        private TextView tv;
        private View root, vDivider;
        private int position;
        private SimpleAdapter adapter;

        public SimpleHolder(View itemView) {
            super(itemView);
            this.root = itemView;
            iv = (ImageView) itemView.findViewById(R.id.iv_popwindow_item);
            tv = (TextView) itemView.findViewById(R.id.tv_popwindow_item);
            vDivider = itemView.findViewById(R.id.v_popwindow_divider);
        }

        public void setData(SimpleAdapter adater, int position, Map<String, Object> data){
            this.adapter = adater;
            this.position = position;
            int resid = (int) data.get("img");
            iv.setBackgroundResource(resid);
            tv.setText((String) data.get("title"));
            if(position != data.size() - 1){
                vDivider.setVisibility(View.VISIBLE);
            }else{
                vDivider.setVisibility(View.GONE);
            }
            this.root.setOnClickListener(this);
            this.root.setTag(data);
        }


        @Override
        public void onClick(View v) {
            if(adapter.getOnItemListener() != null)
                adapter.getOnItemListener().onItemClick(v, position, v.getTag());
        }
    }

}
