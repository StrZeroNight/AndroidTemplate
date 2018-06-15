package com.zeronight.templet.module.share;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;

/**
 * Created by Administrator on 2018/4/8.
 */

public class ShareActivity extends BaseActivity implements View.OnClickListener {

    private final static String ID = "ID";
    /**
     * 微信分享
     */
    private Button btn_wxshare;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, ShareActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, ShareActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        initView();
    }

    private void initView() {
        btn_wxshare = (Button) findViewById(R.id.btn_wxshare);
        btn_wxshare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_wxshare:

                break;
        }
    }

}
