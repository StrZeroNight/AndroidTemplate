package com.zeronight.templet.module.address.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.data.EventBusBundle;
import com.zeronight.templet.common.data.TestData;
import com.zeronight.templet.common.utils.ListManager;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.widget.SuperTextView;
import com.zeronight.templet.common.widget.TitleBar;
import com.zeronight.templet.module.address.edit.AddressAddActivity;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by Administrator on 2018/1/3.
 */
public class AddressListActivity extends BaseActivity implements View.OnClickListener {

    private final static String ID = "ID";
    private TitleBar titlebar;
    private XRecyclerView xrv;
    private SuperTextView stv_add;
    ListManager<AddressDetialBean> listManager = new ListManager<>(this);
    //
    public final static String ADDRESS_INTENT_TYPE = "ADDRESS_INTENT_TYPE";
    public final static String NOTIFY_ADDRESS = "NOTIFY_ADDRESS";
    public final static String CHECK_ADDRESS = "CHECK_ADDRESS";
    public final static int FROM_CART = 1;
    public final static int FROM_MINE = 2;
    //判断从哪个页面跳转来的参数
    //订单跳转来的列表内容可以点击
    //我的收货地址跳转来的 列表内容不能点击
    //1是从购物车跳入 2是从地址编辑跳入
    //intentType具体区别在adapter中
    private int intentType;

    public static void start(Context context) {
        Intent it = new Intent(context, AddressListActivity.class);
        context.startActivity(it);
    }

    public static void start(Context context, int intentType) {
        Intent it = new Intent(context, AddressListActivity.class);
        it.putExtra(ADDRESS_INTENT_TYPE , intentType);
        context.startActivity(it);
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra(ID) != null) {
            String id = intent.getStringExtra(ID);
            ToastUtils.showMessage("获取id" + id);
        }
        // 1是从购物车跳入 2是从地址编辑跳入
        if(intent.getIntExtra(ADDRESS_INTENT_TYPE , 0) != 0) {
            intentType = intent.getIntExtra(ADDRESS_INTENT_TYPE , 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresslist);
        EventBus.getDefault().register(this);
        initView();
        initIntent();
//        initData();
    }

    private void initData() {
        listManager.getAllList().add(new AddressDetialBean());
        listManager.getAllList().add(new AddressDetialBean());
        listManager.getAllList().add(new AddressDetialBean());
        listManager.setRecyclerView(xrv)
                .setAdapter(new AddressListAdapter(this, listManager.getAllList() , intentType))
                .setUrl(CommonUrl.login)
                .isLoadMore(false)
                .isPullRefresh(true)
                .run();
    }

    private void initView() {
        titlebar = (TitleBar) findViewById(R.id.titlebar);
        xrv = (XRecyclerView) findViewById(R.id.xrv_address);
        xrv.setLayoutManager(new LinearLayoutManager(this));
        xrv.setAdapter(new AddressListAdapter(this , TestData.getAddress() , intentType));
        stv_add = (SuperTextView) findViewById(R.id.stv_add);
        stv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stv_add:
                AddressAddActivity.start(this);
                break;
        }
    }


    @Subscribe
    public void refreshAddressList(EventBusBundle eventBusBundle) {
        if (eventBusBundle.getKey().equals(AddressListActivity.NOTIFY_ADDRESS)) {
            listManager.refresh();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
