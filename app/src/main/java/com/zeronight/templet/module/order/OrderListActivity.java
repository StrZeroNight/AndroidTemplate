package com.zeronight.templet.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */

public class OrderListActivity extends BaseActivity {

    private TabLayout tablayout_orderx;
    private ViewPager vp_orderx;

    public static void start(Context context) {
        Intent it = new Intent(context, OrderListActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        initView();
    }

    private void initView() {
        vp_orderx = (ViewPager) findViewById(R.id.vp_orderx);
        List<Fragment> lists = new ArrayList<>();
        lists.add(new OrderAllFragment());
        lists.add(new OrderDFKFragment());
        lists.add(new OrderDFHFragment());
        lists.add(new OrderDSHFragment());
        lists.add(new OrderYWCFragment());
        vp_orderx.setAdapter(new OrderVpAdapter(getSupportFragmentManager() , lists));
        List<String> tabNames = initTabName();

        tablayout_orderx = (TabLayout) findViewById(R.id.tablayout_orderx);
        tablayout_orderx.setupWithViewPager(vp_orderx);
        tablayout_orderx.setTabMode(TabLayout.MODE_FIXED);
        tablayout_orderx.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                vp_orderx.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vp_orderx.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                tablayout_orderx.
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @NonNull
    private List<String> initTabName() {
        List<String> tabNames = new ArrayList<>();
        tabNames.add("全部");
        tabNames.add("待付款");
        tabNames.add("待发货");
        tabNames.add("待收货");
        tabNames.add("已完成");
        return tabNames;
    }


}
