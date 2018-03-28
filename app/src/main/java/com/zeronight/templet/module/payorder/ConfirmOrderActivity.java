package com.zeronight.templet.module.payorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.EventBusBundle;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.widget.SuperTextView;
import com.zeronight.templet.common.widget.TitleBar;
import com.zeronight.templet.module.address.list.AddressDetialBean;
import com.zeronight.templet.module.address.list.AddressListActivity;
import com.zeronight.templet.module.address.list.AddressListAdapter;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by Administrator on 2018/1/24.
 */

public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private TitleBar titlebar;
    private TextView tv_address_lianxiren;
    private TextView tv_address_phone;
    private TextView tv_address_content;
    private RelativeLayout rl_address;
    private RelativeLayout rl_address_none;
    private SuperTextView stv_topay;
    private RelativeLayout rl_bottombar;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, ConfirmOrderActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, ConfirmOrderActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, ConfirmOrderActivity.class);
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
        setContentView(R.layout.activity_confirmorder);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        titlebar = (TitleBar) findViewById(R.id.titlebar);
        tv_address_lianxiren = (TextView) findViewById(R.id.tv_address_lianxiren);
        tv_address_phone = (TextView) findViewById(R.id.tv_address_phone);
        tv_address_content = (TextView) findViewById(R.id.tv_address_content);
        rl_address = (RelativeLayout) findViewById(R.id.rl_address);
        rl_address.setOnClickListener(this);
        rl_address_none = (RelativeLayout) findViewById(R.id.rl_address_none);
        rl_address_none.setOnClickListener(this);
        stv_topay = (SuperTextView) findViewById(R.id.stv_topay);
        stv_topay.setOnClickListener(this);
        rl_bottombar = (RelativeLayout) findViewById(R.id.rl_bottombar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_address:
                AddressListActivity.start(this, AddressListActivity.FROM_CART);
                break;
            case R.id.rl_address_none:
                AddressListActivity.start(this, AddressListActivity.FROM_CART);
                break;
            case R.id.stv_topay:
                PayActivity.start(this);
                break;
        }
    }

    @Subscribe
    public void getAddress(EventBusBundle eventBusBundle) {
        if (eventBusBundle.getKey().equals(AddressListAdapter.ADDRESS_INFO)) {
            Bundle bundle = eventBusBundle.getBundle();
            AddressDetialBean addressDetial = bundle.getParcelable(AddressListAdapter.ADDRESS_INFO);
            ToastUtils.showMessage(addressDetial.toString());
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


}
