package com.zeronight.templet.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.module.adapter.AdapterActivity;
import com.zeronight.templet.module.address.list.AddressListActivity;
import com.zeronight.templet.module.bankcard.WithdrawalsActivity;
import com.zeronight.templet.module.cart.CartActivity;
import com.zeronight.templet.module.classify.OneClassifyActivity;
import com.zeronight.templet.module.filereader.FileReadActivity;
import com.zeronight.templet.module.goods.GoodDetailActivity;
import com.zeronight.templet.module.login.LoginActivity;
import com.zeronight.templet.module.main.MainActivity;
import com.zeronight.templet.module.mine.UserInfoActivity;
import com.zeronight.templet.module.order.OrderListActivity;
import com.zeronight.templet.module.permission.PermissionActivity;
import com.zeronight.templet.module.richedit.RichEditActivity;
import com.zeronight.templet.module.search.SearchActvity;
import com.zeronight.templet.module.window.WindowActivity;

/**
 * Created by Administrator on 2018/1/3.
 */

public class TempletActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_main;
    private Button btn_login;
    private Button btn_address;
    private Button btn_userinfo;
    private Button btn_bankcard;
    private Button btn_permission;
    private Button btn_cart;
    private Button btn_fileread;
    private Button btn_window;
    private Button btn_goods;
    private Button btn_rich;
    private Button btn_search;
    private Button btn_adapter;
    private Button btn_classify;
    private Button btn_order;
    private Button btn_share;
    private Button btn_webview;

    public static void start(Context context) {
        Intent it = new Intent(context, TempletActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templet);
        initView();
    }

    private void initView() {
        btn_adapter = (Button) findViewById(R.id.btn_adapter);
        btn_adapter.setOnClickListener(this);
        btn_main = (Button) findViewById(R.id.btn_main);
        btn_main.setOnClickListener(this);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_address = (Button) findViewById(R.id.btn_address);
        btn_address.setOnClickListener(this);
        btn_userinfo = (Button) findViewById(R.id.btn_userinfo);
        btn_userinfo.setOnClickListener(this);
        btn_bankcard = (Button) findViewById(R.id.btn_bankcard);
        btn_bankcard.setOnClickListener(this);
        btn_permission = (Button) findViewById(R.id.btn_permission);
        btn_permission.setOnClickListener(this);
        btn_cart = (Button) findViewById(R.id.btn_cart);
        btn_cart.setOnClickListener(this);
        btn_fileread = (Button) findViewById(R.id.btn_fileread);
        btn_fileread.setOnClickListener(this);
        btn_window = (Button) findViewById(R.id.btn_window);
        btn_window.setOnClickListener(this);
        btn_goods = (Button) findViewById(R.id.btn_goods);
        btn_goods.setOnClickListener(this);
        btn_rich = (Button) findViewById(R.id.btn_rich);
        btn_rich.setOnClickListener(this);
        btn_classify = (Button) findViewById(R.id.btn_classify);
        btn_classify.setOnClickListener(this);
        btn_order = (Button) findViewById(R.id.btn_order);
        btn_order.setOnClickListener(this);
        btn_share = (Button) findViewById(R.id.btn_share);
        btn_share.setOnClickListener(this);
        btn_webview = (Button) findViewById(R.id.btn_webview);
        btn_webview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rich:
                RichEditActivity.start(this);
                break;
            case R.id.btn_goods:
                GoodDetailActivity.start(this);
                break;
            case R.id.btn_main:
                MainActivity.start(this);
                break;
            case R.id.btn_login:
                LoginActivity.start(this);
                break;
            case R.id.btn_address:
                AddressListActivity.start(this);
                break;
            case R.id.btn_userinfo:
                UserInfoActivity.start(this);
                break;
            case R.id.btn_bankcard:
                WithdrawalsActivity.start(this);
                break;
            case R.id.btn_permission:
                PermissionActivity.start(this);
                break;
            case R.id.btn_cart:
                CartActivity.start(this);
                break;
            case R.id.btn_fileread:
                FileReadActivity.start(this);
                break;
            case R.id.btn_window:
                WindowActivity.start(this);
                break;
            case R.id.btn_search:
                SearchActvity.start(this);
                break;
            case R.id.btn_adapter:
                AdapterActivity.start(this);
                break;
            case R.id.btn_classify:
                OneClassifyActivity.start(this);
                break;
            case R.id.btn_order:
                OrderListActivity.start(this);
                break;
            case R.id.btn_share:
                break;
            case R.id.btn_webview:
//                WebViewActivity.start(TempletActivity.this , );
                break;
        }
    }


}
