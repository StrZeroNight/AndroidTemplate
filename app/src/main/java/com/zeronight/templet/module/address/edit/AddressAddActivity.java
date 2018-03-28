package com.zeronight.templet.module.address.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.data.EventBusBundle;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.CommonUtils;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.common.widget.AddressText;
import com.zeronight.templet.common.widget.SuperTextView;
import com.zeronight.templet.common.widget.TitleBar;
import com.zeronight.templet.module.address.chooser.AddressChooer;
import com.zeronight.templet.module.address.list.AddressDetialBean;
import com.zeronight.templet.module.address.list.AddressListActivity;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2018/1/3.
 */

public class AddressAddActivity extends BaseActivity implements View.OnClickListener {

    private final static String ID = "ID";
    protected TitleBar titlebar;
    private AddressText addt_name;
    private AddressText addt_phone;
    private AddressText addt_city;
    private AddressText addt_detial;
    private AddressText addt_default;
    private SuperTextView stv_add;

    public static void start(Context context) {
        Intent it = new Intent(context, AddressAddActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressadd);
        initView();
    }

    private void initView() {
        titlebar = (TitleBar) findViewById(R.id.titlebar);
        addt_name = (AddressText) findViewById(R.id.addt_name);
        addt_phone = (AddressText) findViewById(R.id.addt_phone);
        addt_city = (AddressText) findViewById(R.id.addt_city);
        addt_city.setOnClickListener(this);
        addt_detial = (AddressText) findViewById(R.id.addt_detial);
        addt_default = (AddressText) findViewById(R.id.addt_default);
        stv_add = (SuperTextView) findViewById(R.id.stv_add);
        stv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addt_city:
                CommonUtils.hideSoft(this , addt_city);
                AddressChooer addressChooer = new AddressChooer(this);
                addressChooer.showAddressChoose(new AddressChooer.OnAddressChoose() {
                    @Override
                    public void onAddressChoose(String address) {
                        addt_city.setTextView(address);
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
        String user = addt_name.getEditText();
        String phone = addt_phone.getEditText();
        String city = addt_city.getTextView();
        String address = addt_detial.getEditText();
        boolean switchStatus = addt_default.getSwitchStatus();

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
