package com.zeronight.templet.module.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/5/14.
 */

public class OrderVpAdapter extends FragmentPagerAdapter {

    private List<Fragment> mlist;
    private String[] tableTitle = new String[] {"全部", "待付款", "待发货", "待收货", "已完成"};

    public OrderVpAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mlist = list;
    }

    @Override
    public Fragment getItem(int arg0) {
        return mlist.get(arg0);//显示第几个页面
    }

    @Override
    public int getCount() {
        return mlist.size();//有几个页面
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tableTitle[position];
    }


}
