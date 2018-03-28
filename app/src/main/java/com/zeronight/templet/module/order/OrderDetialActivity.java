package com.zeronight.templet.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.utils.ToastUtils;

/**
 * Created by Administrator on 2018/2/7.
 */

public class OrderDetialActivity extends BaseActivity {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";

    public static void start(Context context, String id) {
        Intent it = new Intent(context, OrderDetialActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, OrderDetialActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, OrderDetialActivity.class);
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
        setContentView(R.layout.activity_orderdetail);
    }

}
