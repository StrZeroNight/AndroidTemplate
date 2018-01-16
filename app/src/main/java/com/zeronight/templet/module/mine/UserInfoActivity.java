package com.zeronight.templet.module.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.widget.ArrorText;

/**
 * Created by Administrator on 2018/1/4.
 */

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private ImageView iv_head_image;
    private RelativeLayout rl_head_image;
    private ArrorText at_nike;
    private ArrorText at_gender;
    private ArrorText at_phone;
    private ArrorText at_age;
    private ArrorText at_area;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, UserInfoActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, UserInfoActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, UserInfoActivity.class);
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
        setContentView(R.layout.activity_userinfo);
        initView();
    }

    private void initView() {
//        iv_head_image = (ImageView) findViewById(R.id.iv_head_image);
//        rl_head_image = (RelativeLayout) findViewById(R.id.rl_head_image);
        rl_head_image.setOnClickListener(this);
        at_nike = (ArrorText) findViewById(R.id.at_nike);
        at_nike.setOnClickListener(this);
        at_gender = (ArrorText) findViewById(R.id.at_gender);
        at_gender.setOnClickListener(this);
        at_phone = (ArrorText) findViewById(R.id.at_phone);
        at_phone.setOnClickListener(this);
        at_age = (ArrorText) findViewById(R.id.at_age);
        at_age.setOnClickListener(this);
        at_area = (ArrorText) findViewById(R.id.at_area);
        at_area.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.rl_head_image:
//                break;
            case R.id.at_nike:
                break;
            case R.id.at_gender:
                break;
            case R.id.at_phone:
                break;
            case R.id.at_age:
                break;
            case R.id.at_area:
                break;
        }
    }

}
