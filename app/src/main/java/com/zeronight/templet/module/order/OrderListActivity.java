package com.zeronight.templet.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.data.TestData;
import com.zeronight.templet.common.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */

public class OrderListActivity extends BaseActivity {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private XRecyclerView xrv;
    private TabLayout tablayout;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, OrderListActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, OrderListActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, OrderListActivity.class);
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.startActivityForResult(it, REQUEST_CODE);
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra(ID) != null) {
            String id = intent.getStringExtra(ID);
            ToastUtils.showMessage("获取id" + id);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        initView();
    }

    private void initView() {
        xrv = (XRecyclerView) findViewById(R.id.xrv);
        xrv.setLayoutManager(new LinearLayoutManager(this));
        OrderAdapter orderAdapter = new OrderAdapter(this, TestData.getLists());
        xrv.setAdapter(orderAdapter);
        orderAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                OrderDetialActivity.start(OrderListActivity.this);
            }
        });
        tablayout = (TabLayout) findViewById(R.id.tl);
        List<String> tabNames = new ArrayList<>();
        tabNames.add("全部");
        tabNames.add("待付款");
        tabNames.add("待发货");
        tabNames.add("待收货");
        tabNames.add("退款/售后");
        iniTablayout(tabNames);
    }

    public void iniTablayout(final List<String> tabNames) {
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            for (int i = 0; i < tabNames.size(); i++) {
                tablayout.addTab(tablayout.newTab().setText(tabNames.get(i).toString()));
            }
            //监听viewpager滑动
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int position = tab.getPosition();
//                    for (int i = 0; i < tabNames.size(); i++) {
//                        if (position == i) {
//                            page = 1;
//                            currentTab = position;
//    //                        tabId = tabNames.get(i).toString();
//                        }
//                    }
//                    getListInfo(xrecyclerview_basetablist, ll_root_wrongdata, ll_root_nodata);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });

        }


}
