package com.zeronight.templet.module.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private ImageView iv_bg;
    private SuperTextView stv_register;
    private LoginEditText let_phone;
    private LoginEditText let_password;
    private LoginEditText let_password2;
    private LoginEditText let_incvitecode;
    private TextView tv_zhidian;
    private TextView tv_tongyi;
    private TextView tv_xieyi;
    private ImageView iv_qq;
    private ImageView iv_wx;
    private ImageView iv_wb;
    private EditText et_content;
    private TextView tv_getvercode;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, RegisterActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, RegisterActivity.class);
        context.startActivity(it);
    }

    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, RegisterActivity.class);
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
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        tv_getvercode = (TextView) findViewById(R.id.tv_getvercode);
        tv_getvercode.setOnClickListener(this);
        et_content = (EditText) findViewById(R.id.et_content);
        iv_bg = (ImageView) findViewById(R.id.iv_bg);
        stv_register = (SuperTextView) findViewById(R.id.stv_register);
        stv_register.setOnClickListener(this);
        let_phone = (LoginEditText) findViewById(R.id.let_phone);
        let_password = (LoginEditText) findViewById(R.id.let_password);
        let_password2 = (LoginEditText) findViewById(R.id.let_npassword);
        let_incvitecode = (LoginEditText) findViewById(R.id.let_invitecode);
        tv_zhidian = (TextView) findViewById(R.id.tv_zhidian);
        tv_zhidian.setOnClickListener(this);
        tv_tongyi = (TextView) findViewById(R.id.tv_tongyi);
        tv_tongyi.setOnClickListener(this);
        tv_xieyi = (TextView) findViewById(R.id.tv_xieyi);
        tv_xieyi.setOnClickListener(this);
        iv_qq = (ImageView) findViewById(R.id.iv_qq);
        iv_qq.setOnClickListener(this);
        iv_wx = (ImageView) findViewById(R.id.iv_wx);
        iv_wx.setOnClickListener(this);
        iv_wb = (ImageView) findViewById(R.id.iv_wb);
        iv_wb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stv_register:
                checkRegisterInfo();
                break;
            case R.id.tv_zhidian:
                break;
            case R.id.tv_tongyi:
                break;
            case R.id.tv_xieyi:
                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_wx:
                break;
            case R.id.iv_wb:
                break;
            case R.id.tv_getvercode:
                String phone = let_phone.getContent();
                VerCodeUtils verCodeUtils = new VerCodeUtils(this);
                verCodeUtils.showImageCode(phone , tv_getvercode);
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
            ToastUtils.showMessage(getString(R.string.password_can_not_null));
            return;
        }
        if (password.length() < 6 || password.length() > 16) {
            ToastUtils.showMessage(getString(R.string.password_can_not_be_less_than_6_bits));
            return;
        }
        if (XStringUtils.isEmpty(password2)) {
            ToastUtils.showMessage(getString(R.string.password2_can_not_null));
            return;
        }
        if (password2.length() < 6 || password2.length() > 16) {
            ToastUtils.showMessage(getString(R.string.password2_can_not_be_less_than_6_bits));
            return;
        }
        if (!password2.equals(password)) {
            ToastUtils.showMessage(getString(R.string.password_not_equl));
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
