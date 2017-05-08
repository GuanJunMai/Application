package com.example.myplay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.myplay.R;
import com.example.myplay.global.GooglePlayApp;

import java.util.ArrayList;

/**
 * Created by dance on 2017/5/4.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    //    String[] titles = {"efgerg"};
    String[] titles = GooglePlayApp.context.getResources().getStringArray(R.array.tab_names);

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * 添加Fragmnet
     */
    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
