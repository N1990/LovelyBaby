package com.cmbb.smartkids.widget.popWindows;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.utils.TDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/6 15:06
 */
public class CommentPop extends PopupWindow {
    private Context context;
    private ListView lv;
    private CustomListener.ItemClickListener onItemListener;


    public CommentPop(Context context, int[] resIds, String[] titles){
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
        View popView = LayoutInflater.from(context).inflate(R.layout.view_comment_pop, null);
        setContentView(popView);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(TDevice.getScreenWidth(context) / 4);
        setBackgroundDrawable(new ColorDrawable(0));
        //设置popwindow出现和消失动画
//        setAnimationStyle(android.R.style.PopMenuAnimation);
        lv = (ListView) popView.findViewById(R.id.lv_custom_pop);
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < resIds.length; i ++){
            HashMap<String, Object> entry = new HashMap<>();
            entry.put("img", resIds[i]);
            entry.put("title", titles[i]);
            data.add(entry);
        }
        SimpleAdapter adapter = new SimpleAdapter(context, data,
                R.layout.view_popwindow_item, new String[] { "img", "title" },
                new int[] { R.id.iv_popwindow_item, R.id.tv_popwindow_item });
        lv.setAdapter(adapter);
    }

    /**
     * 显示popWindow
     * */
    public void showPop(View parent, int x, int y) {
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        //设置popwindow显示位置
        showAsDropDown(parent, location[1] + x, y);
        //获取popwindow焦点
        setFocusable(true);
        //设置popwindow如果点击外面区域，便关闭。
        setOutsideTouchable(true);
        update();
    }




    private void addListener(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(onItemListener != null)
                onItemListener.onItemClick(view, position, id);
            }
        });
    }





}
