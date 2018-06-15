package com.zeronight.templet.module.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.StatusBarUtils;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.common.widget.LoginEditText;
import com.zeronight.templet.common.widget.SuperTextView;

/**
 * Created by Administrator on 2018/1/3.
 */

public class ForgetPassActivity extends BaseActivity implements View.OnClickListener {

    private SuperTextView stv_ok_register;
    private SuperTextView stv_back_register;
    private EditText et_content;

    public static void start(Context context) {
        Intent it = new Intent(context, ForgetPassActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.transparencyBar(ForgetPassActivity.this);
        setContentView(R.layout.activity_forgetpass);
        initView();
    }

    private void initView() {
        et_content = (EditText) findViewById(R.id.et_content);
        stv_back_register = (SuperTextView) findViewById(R.id.stv_back_register);
        stv_back_register.setOnClickListener(this);
        stv_ok_register = (SuperTextView) findViewById(R.id.stv_ok_register);
        stv_ok_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stv_next_register:
                checkRegisterInfo();
                break;
            case R.id.stv_back_register:
                finish();
                break;
        }
    }

    private void checkRegisterInfo() {

        String phone = letv_phone.getContent();
        String vercode = et_content.getText().toString();
        String password = let_password.getContent();
        String password2 = let_password2.getContent();

        if (XStringUtils.isEmpty(phone)) {
            ToastUtils.showMessage("手机号不能为空");
            return;
        }
        if (!XStringUtils.checkPhoneNum(phone)) {
            ToastUtils.showMessage("手机号不是标准手机号");
            return;
        }
        if (XStringUtils.isEmpty(vercode)) {
            ToastUtils.showMessage("短信验证码不能为空");
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
        if (XStringUtils.isEmpty(password2)) {
            ToastUtils.showMessage("确认密码不能为空");
            return;
        }
        if (password2.length() < 6 || password2.length() > 16) {
            ToastUtils.showMessage("确认密码不能小于6位或大于16位");
            return;
        }
        if (!password2.equals(password)) {
            ToastUtils.showMessage("密码不一致");
            return;
        }
        register(phone , vercode , password);
    }

    private void register(String phone , String code , String password) {
        showprogressDialogCanNotCancel();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.auth_resetpassword)
                .setParams("phone", phone)
                .setParams("code", code)
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
                ToastUtils.showMessage("找回成功");
                finish();
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


}
