package com.zeronight.templet.module.address.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zeronight.templet.common.utils.ToastUtils;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AddressEditActivity extends AddressAddActivity {

    private final static String ID = "ID";

    public static void start(Context context, String id) {
        Intent it = new Intent(context, AddressEditActivity.class);
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
        // TODO: 2018/1/4 调用获取地址内容
        titlebar.setTitle("修改收货地址");
        ToastUtils.showMessage("edit");
        initIntent();
    }

//    @Override
//    protected void saveAddress(AddressDetialBean addressDetial) {
//        // TODO: 2018/1/4 调用保存地址
//        updateAddress(addressDetial);
//    }

//    private void updateAddress(AddressDetialBean addressDetial) {
//        showprogressDialogCanNotCancel();
//        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
//                .setUrl(CommonUrl.login)
//                .setObjectParams(addressDetial)
//                .build();
//        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
//            @Override
//            public void onNetWorkError() {
//                dismissProgressDialog();
//            }
//
//            @Override
//            public void onSuccess(String data) {
//                dismissProgressDialog();
//                notifyAddressLsit();
//                finish();
//            }
//
//            @Override
//            public void onNoData() {
//                dismissProgressDialog();
//            }
//
//            @Override
//            public void onServerError() {
//                dismissProgressDialog();
//            }
//        });
//    }

}
