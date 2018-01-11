package com.zeronight.templet.module.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.VerCodeUtils;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.common.widget.LoginEditText;
import com.zeronight.templet.common.widget.SuperTextView;

/**
 * Created by Administrator on 2018/1/3.
 */

public class ForgetPassActivity extends BaseActivity implements View.OnClickListener {


    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private ImageView iv_move;
    private LoginEditText let_phone;
    private ImageView iv_icon;
    /**
     * 请输入验证码
     */
    private EditText et_content;
    /**
     * 获取验证码
     */
    private TextView tv_getvercode;
    private LoginEditText let_password;
    private LoginEditText let_npassword;
    private LoginEditText let_npassword2;
    private LinearLayout ll_edit;
    /**
     * 0000-0000
     */
    private TextView tv_zhidian;
    /**
     * 找回密码
     */
    private SuperTextView stv_register;
    private RelativeLayout ll_root;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, ForgetPassActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, ForgetPassActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, ForgetPassActivity.class);
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
        setContentView(R.layout.activity_forgetpass);
        initView();

    }

    private void initView() {
        iv_move = (ImageView) findViewById(R.id.iv_bg);
        let_phone = (LoginEditText) findViewById(R.id.let_phone);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        et_content = (EditText) findViewById(R.id.et_content);
        tv_getvercode = (TextView) findViewById(R.id.tv_getvercode);
        tv_getvercode.setOnClickListener(this);
        let_password = (LoginEditText) findViewById(R.id.let_password);
        let_npassword = (LoginEditText) findViewById(R.id.let_npassword);
        let_npassword2 = (LoginEditText) findViewById(R.id.let_npassword2);
        ll_edit = (LinearLayout) findViewById(R.id.ll_edit);
        tv_zhidian = (TextView) findViewById(R.id.tv_zhidian);
        tv_zhidian.setOnClickListener(this);
        stv_register = (SuperTextView) findViewById(R.id.stv_register);
        stv_register.setOnClickListener(this);
        ll_root = (RelativeLayout) findViewById(R.id.ll_root);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_getvercode:
                String phone = let_phone.getContent();
                VerCodeUtils verCodeUtils = new VerCodeUtils(this);
                verCodeUtils.showImageCode(phone , tv_getvercode);
                break;
            case R.id.tv_zhidian:
                break;
            case R.id.stv_register:
                checkForgetPasswordInfo();
                break;
        }
    }

    private void checkForgetPasswordInfo() {
        String phone = let_phone.getContent();
        String vercode = et_content.getText().toString();
        String password = let_password.getContent();
        String npassword = let_npassword.getContent();
        String npassword2 = let_npassword2.getContent();

        if (XStringUtils.isEmpty(phone)) {
            ToastUtils.showMessage(getString(R.string.phone_can_not_null));
            return;
        }
        if (!XStringUtils.checkPhoneNum(phone)) {
            ToastUtils.showMessage(getString(R.string.phone_can_not_normal));
            return;
        }
        if (XStringUtils.isEmpty(vercode)) {
            ToastUtils.showMessage(getString(R.string.vercode_can_not_normal));
            return;
        }
        if (XStringUtils.isEmpty(password)) {
            ToastUtils.showMessage("旧密码不能为空");
            return;
        }
        if (password.length() < 6 || password.length() > 16) {
            ToastUtils.showMessage("旧密码不能小于6位或大于16位");
            return;
        }
        if (XStringUtils.isEmpty(npassword)) {
            ToastUtils.showMessage("新密码不能为空");
            return;
        }
        if (npassword.length() < 6 || npassword.length() > 16) {
            ToastUtils.showMessage("新密码不能小于6位或大于16位");
            return;
        }
        if (XStringUtils.isEmpty(npassword2)) {
            ToastUtils.showMessage("确认新密码不能为空");
            return;
        }
        if (npassword2.length() < 6 || npassword2.length() > 16) {
            ToastUtils.showMessage("确认新密码不能小于6位或大于16位");
            return;
        }
        if (!npassword2.equals(npassword)) {
            ToastUtils.showMessage(getString(R.string.password_not_equl));
            return;
        }
        forgetPassword();
    }

    private void forgetPassword() {
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
                ToastUtils.showMessage("密码修改成功");
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
