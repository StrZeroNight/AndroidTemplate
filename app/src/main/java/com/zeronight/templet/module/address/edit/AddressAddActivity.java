package com.zeronight.templet.module.address.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.data.EventBusBundle;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.CommonUtils;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.common.widget.ArrorText;
import com.zeronight.templet.common.widget.DelEditText;
import com.zeronight.templet.common.widget.SuperTextView;
import com.zeronight.templet.common.widget.SwitchText;
import com.zeronight.templet.common.widget.TitleBar;
import com.zeronight.templet.module.address.chooser.AddressChooer;
import com.zeronight.templet.module.address.list.AddressListActivity;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2018/1/3.
 */

public class AddressAddActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private DelEditText det_user;
    private DelEditText det_phone;
    private ArrorText at_city;
    private DelEditText det_address;
    private SwitchText st_default;
    private SuperTextView stv_add;
    protected TitleBar titlebar;


    public static void start(Context context, String id) {
        Intent it = new Intent(context, AddressAddActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, AddressAddActivity.class);
        context.startActivity(it);
    }

    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, AddressAddActivity.class);
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
        setContentView(R.layout.activity_addressadd);
        initView();
    }

    private void initView() {
        titlebar = (TitleBar) findViewById(R.id.titlebar);
        det_user = (DelEditText) findViewById(R.id.det_user);
        det_phone = (DelEditText) findViewById(R.id.det_phone);
        at_city = (ArrorText) findViewById(R.id.at_city);
        at_city.setOnClickListener(this);
        det_address = (DelEditText) findViewById(R.id.det_address);
        st_default = (SwitchText) findViewById(R.id.st_default);
        stv_add = (SuperTextView) findViewById(R.id.stv_add);
        stv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.at_city:
                CommonUtils.hideSoft(this , at_city);
                AddressChooer addressChooer = new AddressChooer(this);
                addressChooer.showAddressChoose(new AddressChooer.OnAddressChoose() {
                    @Override
                    public void onAddressChoose(String address) {
                        at_city.setContent(address);
                    }
                });
                break;
            case R.id.stv_add:
                checkAddressInfo();
                break;
        }
    }

    private void checkAddressInfo() {
        AddressDetialBean addressDetial = new AddressDetialBean();
        String user = det_user.getContent();
        String phone = det_phone.getContent();
        String address = det_address.getContent();
        String city = at_city.getContent();
        boolean switchStatus = st_default.getSwitchStatus();

        if (XStringUtils.isEmpty(user)) {
            ToastUtils.showMessage("收货人不能为空");
            return;
        }
        if (XStringUtils.isEmpty(phone)) {
            ToastUtils.showMessage("手机号不能为空");
            return;
        }
        if (!XStringUtils.checkPhoneNum(phone)) {
            ToastUtils.showMessage("手机号不是标准手机号");
            return;
        }
        if (city.equals("请选择所在地区")) {
            ToastUtils.showMessage("请选择所在地区");
            return;
        }
        if (XStringUtils.isEmpty(address)) {
            ToastUtils.showMessage("详细地址不能为空");
            return;
        }

        addressDetial.setUser(user);
        addressDetial.setPhone(phone);
        addressDetial.setCity(city);
        addressDetial.setAddressDetial(address);
        addressDetial.setIsdefault(0);
        saveAddress(addressDetial);
    }

    protected void saveAddress(AddressDetialBean addressDetial){
        updateAddress(addressDetial);
    }

    private void updateAddress(AddressDetialBean addressDetial) {
        showprogressDialogCanNotCancel();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setObjectParams(addressDetial)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess(String data) {
                dismissProgressDialog();
                notifyAddressLsit();
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


    protected void notifyAddressLsit(){
        EventBus.getDefault().post(new EventBusBundle(AddressListActivity.NOTIFY_ADDRESS, ""));
    }

}
