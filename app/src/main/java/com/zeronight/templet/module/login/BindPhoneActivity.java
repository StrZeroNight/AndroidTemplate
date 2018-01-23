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
 * Created by Administrator on 2018/1/19.
 */

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private LoginEditText let_phone;
    private ImageView iv_icon;
    private EditText et_content;
    private TextView tv_getvercode;
    private LoginEditText let_password;
    private LoginEditText let_npassword;
    private TextView tv_zhidian;
    private SuperTextView stv_bind;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, BindPhoneActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, BindPhoneActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, BindPhoneActivity.class);
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
        setContentView(R.layout.activity_bindactivity);
        initView();
    }

    private void initView() {
        let_phone = (LoginEditText) findViewById(R.id.let_phone);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        et_content = (EditText) findViewById(R.id.et_content);
        tv_getvercode = (TextView) findViewById(R.id.tv_getvercode);
        tv_getvercode.setOnClickListener(this);
        let_password = (LoginEditText) findViewById(R.id.let_password);
        let_npassword = (LoginEditText) findViewById(R.id.let_npassword);
        tv_zhidian = (TextView) findViewById(R.id.tv_zhidian);
        stv_bind = (SuperTextView) findViewById(R.id.stv_bind);
        stv_bind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_getvercode:
                String phone = let_phone.getContent();
                VerCodeUtils verCodeUtils = new VerCodeUtils(this);
                verCodeUtils.showImageCode(phone , tv_getvercode);
                break;
            case R.id.stv_bind:
                checkBindInfo();
                break;
        }
    }

    private void checkBindInfo() {

        String phone = let_phone.getContent();
        String vercode = et_content.getText().toString();
        String password = let_password.getContent();
        String password2 = let_npassword.getContent();

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
        bindPhone();
    }


    private void bindPhone() {
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
