package com.zeronight.templet.module.payorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.widget.PayChoose;
import com.zeronight.templet.common.widget.SuperTextView;

/**
 * Created by Administrator on 2018/1/26.
 */

public class PayActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private TextView tv_pay;
    private PayChoose paychoose;
    private SuperTextView stv_confirm;
    //
    private int payMethod = 0;


    public static void start(Context context, String id) {
        Intent it = new Intent(context, PayActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, PayActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, PayActivity.class);
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
        setContentView(R.layout.activity_pay);
        initView();
    }

    private void initView() {
        stv_confirm = (SuperTextView) findViewById(R.id.stv_confirm);
        stv_confirm.setOnClickListener(this);
        tv_pay = (TextView) findViewById(R.id.tv_pay);
        paychoose = (PayChoose) findViewById(R.id.paychoose);
        paychoose.setPayChooseListener(new PayChoose.PayChooseListener() {
            @Override
            public void onWxChoose() {
                payMethod = 1;
            }

            @Override
            public void onZfbChoose() {

            }

            @Override
            public void onYeChoose() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stv_confirm:
                if (payMethod == 0) {
                    ToastUtils.showMessage("请选择支付方式");
                    return ;
                }

                break;
        }
    }

}
