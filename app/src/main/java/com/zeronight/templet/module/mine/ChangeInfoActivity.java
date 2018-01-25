package com.zeronight.templet.module.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.widget.DelEditText;
import com.zeronight.templet.common.widget.TitleBar;

/**
 * 如果需要校验数据，可以继承该类
 * Created by Administrator on 2018/1/25.
 */
public class ChangeInfoActivity extends BaseActivity {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String INFO = "INFO";
    private final static String TITLE = "TITLE";
    private TitleBar titlebar;
    private DelEditText det_change;

    public static void start(Context context, String info , String title) {
        Intent it = new Intent(context, ChangeInfoActivity.class);
        it.putExtra(INFO, info);
        it.putExtra(TITLE, title);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, ChangeInfoActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, ChangeInfoActivity.class);
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.startActivityForResult(it, REQUEST_CODE);
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra(INFO) != null) {
            String info = intent.getStringExtra(INFO);
            String title = intent.getStringExtra(TITLE);
            titlebar.setTitle(title);
            det_change.setEdittext(info);
            det_change.moveCursorToEnd(info);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeinfo);
        initView();
        initIntent();
    }

    private void initView() {
        titlebar = (TitleBar) findViewById(R.id.titlebar);
        det_change = (DelEditText) findViewById(R.id.det_change);
    }


}
