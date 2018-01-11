package com.zeronight.templet.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.module.address.list.AddressListActivity;
import com.zeronight.templet.module.bankcard.WithdrawalsActivity;
import com.zeronight.templet.module.login.LoginActivity;
import com.zeronight.templet.module.main.MainActivity;
import com.zeronight.templet.module.mine.UserInfoActivity;
import com.zeronight.templet.module.permission.PermissionActivity;

/**
 * Created by Administrator on 2018/1/3.
 */

public class TempletActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private Button btn_main;
    private Button btn_login;
    private Button btn_address;
    private Button btn_userinfo;
    private Button btn_bankcard;
    private Button btn_permission;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, TempletActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, TempletActivity.class);
        context.startActivity(it);
    }

    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, TempletActivity.class);
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
        setContentView(R.layout.activity_templet);
        initView();

    }

    private void initView() {
        btn_main = (Button) findViewById(R.id.btn_main);
        btn_main.setOnClickListener(this);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
        }
    }

}