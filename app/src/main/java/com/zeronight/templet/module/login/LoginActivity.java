package com.zeronight.templet.module.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
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

import java.util.Map;


/**
 * Created by Administrator on 2018/1/3.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_toforget;
    private TextView tv_toregister;
    private SuperTextView stv_login;
    private LoginEditText let_phone;
    private LoginEditText let_password;
    //
    private UMShareAPI mShareAPI;

    public static void start(Context context) {
        Intent it = new Intent(context, LoginActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUMShare();
        initView();
    }

    private void initUMShare() {
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        mShareAPI = UMShareAPI.get(this);
        mShareAPI.setShareConfig(config);
    }

    private void initView() {
        tv_toforget = (TextView) findViewById(R.id.tv_toforget);
        tv_toforget.setOnClickListener(this);
        tv_toregister = (TextView) findViewById(R.id.tv_toregister);
        tv_toregister.setOnClickListener(this);
        stv_login = (SuperTextView) findViewById(R.id.stv_login);
        stv_login.setOnClickListener(this);
        let_phone = (LoginEditText) findViewById(R.id.let_phone);
        let_password = (LoginEditText) findViewById(R.id.let_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_toforget:
                break;
            case R.id.tv_toregister:
                RegisterActivity.start(this);
                break;
            case R.id.stv_login:
                checkInput();
                break;
        }
    }

    private void checkInput() {
        String password = let_password.getContent();
        String phone = let_phone.getContent();
        if (XStringUtils.isEmpty(phone)) {
            ToastUtils.showMessage("手机号不能为空");
            return;
        }
        if (!XStringUtils.checkPhoneNum(phone)) {
            ToastUtils.showMessage("手机号不是标准手机号");
            return;
        }
        if (XStringUtils.isEmpty(password)) {
            ToastUtils.showMessage("密码不能为空");
            return;
        }
        if (password.length() < 6 || password.length() > 16) {
            ToastUtils.showMessage("密码不能小于6位或大于16位");
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

    private void wxLogin(){
        mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            ToastUtils.showMessage("授权调起");
            Logger.i("授权调起");
            showprogressDialog();
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            dismissProgressDialog();
            Logger.i("授权成功");
            ToastUtils.showMessage("授权成功");
            for (Map.Entry entry : data.entrySet()) {
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                Log.i("TAG", "onComplete: ===========================key：" + key + "---value：" + value);
                String openid = data.get("openid");
                wxLogin(openid);
            }
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Logger.i("失败：" + t.getMessage());
            ToastUtils.showMessage("授权失败");
            dismissProgressDialog();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Logger.i("取消");
            ToastUtils.showMessage("授权取消");
            dismissProgressDialog();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }

    private void wxLogin(String openid) {
        showprogressDialog();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("openid", openid)
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
//                BindActivity.start(LoginActivity.this , bindUpBean);
                finish();
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
            AppSetting.putBoolean(CommonString.USER_IS_LOGIN, true);
            AppSetting.putInt(CommonString.USER_ID, customerId);
            AppSetting.putString(CommonString.USER_TOKEN, customerToken);
            AppSetting.putString(CommonString.USER_PHONE, phone);
            finish();
        }
    }

}
