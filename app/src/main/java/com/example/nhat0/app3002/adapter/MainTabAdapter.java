package com.example.nhat0.app3002.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nhat0.app3002.entity.AppPreferences;
import com.example.nhat0.app3002.fragment.FilterFragment;
import com.example.nhat0.app3002.fragment.InformationListFragment;

/**
 * Created by nhat0 on 12/3/2016.
 */
public class MainTabAdapter extends FragmentPagerAdapter {
    private Context context;
    public MainTabAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InformationListFragment();
            case 1:
                return new FilterFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return AppPreferences.FRAGMENT_TITLE_LIST[position];
    }

    @Override
    public int getCount() {
        return AppPreferences.PAGE_COUNT;
    }
}
