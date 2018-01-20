package com.github.baoyan1998.zhihu.ui.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by lenovo on 2018/1/12.
 */

public class ZhihuMainAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public ZhihuMainAdapter(FragmentManager fm,List<Fragment> fragments)  {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
