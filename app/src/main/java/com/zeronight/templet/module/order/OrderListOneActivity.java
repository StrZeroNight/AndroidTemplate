package com.zeronight.templet.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.data.TestData;
import com.zeronight.templet.common.widget.TitleBar;

/**
 * Created by Administrator on 2018/2/7.
 */

public class OrderListOneActivity extends BaseActivity {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String TITLE = "TITLE";
    private XRecyclerView xrv;
    private TitleBar titlebar;
    private String title;

    public static void start(Context context, String title) {
        Intent it = new Intent(context, OrderListOneActivity.class);
        it.putExtra(TITLE, title);
        context.startActivity(it);
    }

//    public static void start(Context context) {
//        Intent it = new Intent(context, OrderListOneActivity.class);
//        context.startActivity(it);
//    }

    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, OrderListOneActivity.class);
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.startActivityForResult(it, REQUEST_CODE);
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra(TITLE) != null) {
            title = intent.getStringExtra(OrderListOneActivity.TITLE);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlistone);
        initIntent();
        initView();
    }

    private void initView() {
        titlebar = (TitleBar) findViewById(R.id.titlebar);
        titlebar.setTitle(title);
        xrv = (XRecyclerView) findViewById(R.id.xrv);
        xrv.setLayoutManager(new LinearLayoutManager(this));
        OrderAdapter orderAdapter = new OrderAdapter(this, TestData.getLists());
        xrv.setAdapter(orderAdapter);
        orderAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                OrderDetialActivity.start(OrderListOneActivity.this);
            }
        });
    }

}
