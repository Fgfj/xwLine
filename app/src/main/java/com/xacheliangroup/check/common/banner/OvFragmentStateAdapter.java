package com.xacheliangroup.check.common.banner;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * author:yz
 * data: 2018/12/19,14:03
 */
public class OvFragmentStateAdapter extends FragmentStatePagerAdapter {
    //Fragments
    private List<Fragment> fragmentLists;

    //标题
    private String[] tabTitleArray;

    public OvFragmentStateAdapter(FragmentManager fm, List<Fragment> list, String[] tabTitleArray) {
        super(fm);
        this.fragmentLists=list;
        this.tabTitleArray=tabTitleArray;
    }

    public OvFragmentStateAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragmentLists=list;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentLists.get(position);
    }

    @Override
    public int getCount() {
        return fragmentLists.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(tabTitleArray!=null&&tabTitleArray.length!=0){
            return tabTitleArray[position%tabTitleArray.length];
        }else {
            return super.getPageTitle(position);
        }

    }
}
