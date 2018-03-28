package com.zeronight.templet.module.bankcard.edit;

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
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.common.widget.ArrorText;
import com.zeronight.templet.common.widget.NorEditText;
import com.zeronight.templet.common.widget.SuperTextView;
import com.zeronight.templet.module.bankcard.bank.BankActivity;
import com.zeronight.templet.module.bankcard.bank.BankBean;
import com.zeronight.templet.module.bankcard.list.BankCardListActivity;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2018/1/4.
 */

public class BankCardAddActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private NorEditText net_name;
    private NorEditText net_phone;

    private NorEditText net_num;

    private ArrorText at_choosebank;
    private SuperTextView stv_save;
    private BankBean bank;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, BankCardAddActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, BankCardAddActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, BankCardAddActivity.class);
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
        setContentView(R.layout.activity_bankcardadd);
        initView();
    }

    private void initView() {
        net_name =  findViewById(R.id.net_name);
        net_phone = findViewById(R.id.net_phone);
        net_num = findViewById(R.id.net_num);
        at_choosebank = (ArrorText) findViewById(R.id.at_choosebank);
        at_choosebank.setOnClickListener(this);
        stv_save = (SuperTextView) findViewById(R.id.stv_save);
        stv_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.at_choosebank:
                BankActivity.startActivityForResult(BankCardAddActivity.this);
                break;
            case R.id.stv_save:
                checkBankCardInfo();
                break;
        }
    }

    private void checkBankCardInfo() {

        BankCardUpdateBean bankCard = new BankCardUpdateBean();
        String user = net_name.getContent();
        String number = net_num.getContent();
        String phone = net_phone.getContent();
        String bank = at_choosebank.getContent();

        if (XStringUtils.isEmpty(user)) {
            ToastUtils.showMessage("持卡人姓名不能为空");
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
        if (XStringUtils.isEmpty(number)) {
            ToastUtils.showMessage("银行卡号不能为空");
            return;
        }
        if (bank.equals("请选择银行")) {
            ToastUtils.showMessage("请选择银行");
            return;
        }
        saveBankCard(bankCard);
    }

    protected void saveBankCard(BankCardUpdateBean bankCard){
        updateBankCard(bankCard);
    }

    private void updateBankCard(BankCardUpdateBean bankCard) {
        showprogressDialogCanNotCancel();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setObjectParams(bankCard)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess(String data) {
                dismissProgressDialog();
                notifyBankCardLsit();
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

    protected void notifyBankCardLsit(){
        EventBus.getDefault().post(new EventBusBundle(BankCardListActivity.NOTFY_BANK_CARD, ""));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            bank = data.getParcelableExtra(BankActivity.BANK_INFO);
            at_choosebank.setContent(bank.getTitle());
        }
    }

}
