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
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.common.widget.LoginEditText;
import com.zeronight.templet.common.widget.SuperTextView;

/**
 * Created by Administrator on 2018/1/3.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private SuperTextView stv_register;
    private LoginEditText let_phone;
    private LoginEditText let_password;
    private LoginEditText let_password2;
    private LoginEditText let_incvitecode;
    private EditText et_content;

    public static void start(Context context) {
        Intent it = new Intent(context, RegisterActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        et_content = (EditText) findViewById(R.id.et_content);
        stv_register = (SuperTextView) findViewById(R.id.stv_register);
        stv_register.setOnClickListener(this);
        let_phone = (LoginEditText) findViewById(R.id.let_phone);
        let_password = (LoginEditText) findViewById(R.id.let_password);
        let_password2 = (LoginEditText) findViewById(R.id.let_npassword);
        let_incvitecode = (LoginEditText) findViewById(R.id.let_invitecode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stv_register:
                checkRegisterInfo();
                break;
        }
    }

    private void checkRegisterInfo() {

        String phone = let_phone.getContent();
        String vercode = et_content.getText().toString();
        String password = let_password.getContent();
        String password2 = let_password2.getContent();
        String invitecode = let_incvitecode.getContent();

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
        register();
    }

    private void register() {
        showprogressDialogCanNotCancel();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
//                .setObjectParams("phone", phone)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess(String data) {
                dismissProgressDialog();
                ToastUtils.showMessage("注册成功");
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
