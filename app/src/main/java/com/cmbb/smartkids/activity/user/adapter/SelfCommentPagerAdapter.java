package com.cmbb.smartkids.activity.user.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/8 10:12
 */
public class SelfCommentPagerAdapter extends FragmentStatePagerAdapter{
    private ArrayList<Fragment> data;
    private ArrayList<String> titles;


    public SelfCommentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(ArrayList<Fragment> data, ArrayList<String> titles){
        if(data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        if(titles != null){
            this.titles = titles;
        }else{
            this.titles = new ArrayList<>();
        }
        if(data.size() != titles.size()){
            try {
                throw new Exception("fragment and title length must be equels");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
