package com.zeronight.templet.module.bankcard.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.data.EventBusBundle;
import com.zeronight.templet.common.utils.ListManager;
import com.zeronight.templet.common.widget.ArrorText;
import com.zeronight.templet.module.address.list.AddressListActivity;
import com.zeronight.templet.module.bankcard.edit.BankCardAddActivity;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by Administrator on 2018/1/4.
 */

public class BankCardListActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private ArrorText at_addbank;
    private XRecyclerView xrv;
    ListManager<BankCardListBean> listManager = new ListManager<>(this);
    public static String bankCardId = "0";
    public final static String NOTFY_BANK_CARD = "NOTFY_BANK_CARD";
    public final static String BANK_CARD_ID = "BANK_CARD_ID";
    public final static String BANK_CARD_INFO = "BANK_CARD_INFO";
    public static boolean IS_CHOOSE = true;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, BankCardListActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, BankCardListActivity.class);
        context.startActivity(it);
        IS_CHOOSE = false;
    }

    public static void startActivityForResult(Context context , String bankCardId) {
        Intent it = new Intent(context, BankCardListActivity.class);
        it.putExtra(BANK_CARD_ID , bankCardId);
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.startActivityForResult(it, REQUEST_CODE);
        IS_CHOOSE = true;
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra(BANK_CARD_ID) != null) {
            bankCardId = intent.getStringExtra(BANK_CARD_ID);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankcard);
        EventBus.getDefault().register(this);
        initIntent();
        initView();
        initData();
    }

    private void initData() {
        listManager.getAllList().add(new BankCardListBean());
        listManager.getAllList().add(new BankCardListBean());
        listManager.getAllList().add(new BankCardListBean());
        listManager.setRecyclerView(xrv)
                .setAdapter(new BankCardAdapter(this, listManager.getAllList()))
                .setUrl(CommonUrl.login)
                .setOnItemClickListener(new ListManager.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        if (IS_CHOOSE) {
                            Intent mIntent = new Intent();
                            mIntent.putExtra(BANK_CARD_INFO, listManager.getAllList().get(position));
                            setResult(RESULT_CODE, mIntent);
                            finish();
                        }
                    }

                    @Override
                    public void OnItemLongClick(int position) {

                    }
                })
                .isLoadMore(true)
                .isPullRefresh(true)
                .run();
    }

    private void initView() {
        at_addbank = (ArrorText) findViewById(R.id.at_addbank);
        at_addbank.setOnClickListener(this);
        xrv = (XRecyclerView) findViewById(R.id.xrv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.at_addbank:
                BankCardAddActivity.start(this);
                break;
        }
    }

    @Subscribe
    public void refreshAddressList(EventBusBundle eventBusBundle) {
        if (eventBusBundle.getKey().equals(AddressListActivity.NOTIFY_ADDRESS)) {
            listManager.refresh();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
