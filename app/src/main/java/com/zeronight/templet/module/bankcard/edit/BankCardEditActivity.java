package com.zeronight.templet.module.bankcard.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zeronight.templet.common.utils.ToastUtils;

/**
 * Created by Administrator on 2018/1/5.
 */

public class BankCardEditActivity extends BankCardAddActivity {

    private final static String ID = "ID";

    public static void start(Context context, String id) {
        Intent it = new Intent(context, BankCardEditActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
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
        initIntent();
        // TODO: 2018/1/5 get bankcard info
    }



}
