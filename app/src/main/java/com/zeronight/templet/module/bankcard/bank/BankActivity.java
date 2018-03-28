package com.zeronight.templet.module.bankcard.bank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.utils.ListManager;


/**
 * Created by Administrator on 2017/10/18.
 */

public class BankActivity extends BaseActivity {

    public final static int REQUEST_CODE = 1005;
    private final static int RESULT_CODE = 1002;
    private XRecyclerView xrv;
    ListManager<BankBean> listManager = new ListManager<>(this);
    public final static String BANK_INFO = "BANK_INFO";

    public static void start(Context context) {
        Intent it = new Intent(context, BankActivity.class);
        context.startActivity(it);
    }

    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, BankActivity.class);
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.startActivityForResult(it, REQUEST_CODE);
    }


    private void initIntent() {
        Intent intent = getIntent();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        initView();
        initData();
    }

    private void initData() {
        listManager.getAllList().add(new BankBean());
        listManager.getAllList().add(new BankBean());
        listManager.getAllList().add(new BankBean());
        listManager.setRecyclerView(xrv)
                .setAdapter(new BankAdapter(this, listManager.getAllList()))
                .setUrl(CommonUrl.login)
                .setOnItemClickListener(new ListManager.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        Intent mIntent = new Intent();
                        mIntent.putExtra(BANK_INFO, listManager.getAllList().get(position));
                        setResult(RESULT_CODE , mIntent);
                        finish();
                    }

                    @Override
                    public void OnItemLongClick(int position) {

                    }
                })
                .isLoadMore(false)
                .isPullRefresh(false)
                .run();
    }


    private void initView() {
        xrv = (XRecyclerView) findViewById(R.id.xrv);
    }


}
