package com.zeronight.templet.module.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.CommonData;
import com.zeronight.templet.common.data.CommonString;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.data.CustomerBean;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.AppSetting;
import com.zeronight.templet.common.utils.CommonUtils;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.common.widget.LoginEditText;
import com.zeronight.templet.common.widget.SuperTextView;


/**
 * Created by Administrator on 2018/1/3.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";

    private ImageView iv_bg;
    private TextView tv_toforget;
    private TextView tv_toregister;
    private SuperTextView stv_login;
    private ImageView iv_qq;
    private ImageView iv_wx;
    private ImageView iv_wb;
    private LoginEditText let_phone;
    private LoginEditText let_password;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, LoginActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, LoginActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, LoginActivity.class);
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
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        iv_bg = (ImageView) findViewById(R.id.iv_bg);
        tv_toforget = (TextView) findViewById(R.id.tv_toforget);
        tv_toforget.setOnClickListener(this);
        tv_toregister = (TextView) findViewById(R.id.tv_toregister);
        tv_toregister.setOnClickListener(this);
        stv_login = (SuperTextView) findViewById(R.id.stv_register);
        stv_login.setOnClickListener(this);
        iv_qq = (ImageView) findViewById(R.id.iv_qq);
        iv_qq.setOnClickListener(this);
        iv_wx = (ImageView) findViewById(R.id.iv_wx);
        iv_wx.setOnClickListener(this);
        iv_wb = (ImageView) findViewById(R.id.iv_wb);
        iv_wb.setOnClickListener(this);
        let_phone = (LoginEditText) findViewById(R.id.let_phone);
        let_password = (LoginEditText) findViewById(R.id.let_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_toforget:
                ForgetPassActivity.start(this);
                break;
            case R.id.tv_toregister:
                RegisterActivity.start(this);
                break;
            case R.id.stv_register:
                checkInput();
                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_wx:
                break;
            case R.id.iv_wb:
                break;
        }
    }

    private void checkInput() {
        String password = let_password.getContent();
        String phone = let_phone.getContent();
        if (XStringUtils.isEmpty(phone)) {
            ToastUtils.showMessage(getString(R.string.phone_can_not_null));
            return;
        }
        if (!XStringUtils.checkPhoneNum(phone)) {
            ToastUtils.showMessage(getString(R.string.phone_can_not_normal));
            return;
        }
        if (XStringUtils.isEmpty(password)) {
            ToastUtils.showMessage(getResources().getString(R.string.password_can_not_null));
            return;
        }
        if (password.length() < 6) {
            ToastUtils.showMessage(getResources().getString(R.string.password_can_not_be_less_than_6_bits));
            return;
        }
        login(phone, password);
    }

    private void login(String phone, String password) {
        showprogressDialogCanNotCancel();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("phone", phone)
                .setParams("password", password)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess(String data) {
                dismissProgressDialog();
                handleLogin(data);
            }

            @Override
            public void onNoData() {
                dismissProgressDialog();
            }

            @Override
            public void onServerError() {
                dismissProgressDialog();
            }
        });
    }


    private void handleLogin(String data) {
        CommonUtils.hideSoft(LoginActivity.this, let_phone);
        CustomerBean customer = JSON.parseObject(data, CustomerBean.class);
        if (customer != null) {

            int customerId = customer.getCustomerId();
            String customerToken = customer.getCustomerToken();
            String phone = customer.getPhone();
//            MobclickAgent.onProfileSignIn(customerId + "");

            CommonData.clearUserPhone();
            CommonData.clearToken();
            CommonData.clearUserId();

            AppSetting.putBoolean(CommonString.USER_IS_LOGIN, true);
            AppSetting.putInt(CommonString.USER_ID, customerId);
            AppSetting.putString(CommonString.USER_TOKEN, customerToken);
            AppSetting.putString(CommonString.USER_PHONE, phone);

            finish();

        }
    }

}
