package com.zeronight.templet.module.bankcard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.data.EventBusBundle;
import com.zeronight.templet.common.dialog.BalanceDialog;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.ImageLoad;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.common.widget.ArrorText;
import com.zeronight.templet.common.widget.MoneyEditText;
import com.zeronight.templet.common.widget.SuperTextView;
import com.zeronight.templet.module.bankcard.list.BankCardListActivity;
import com.zeronight.templet.module.bankcard.list.BankCardListBean;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by Administrator on 2017/10/16.
 */
public class WithdrawalsActivity extends BaseActivity implements View.OnClickListener {

    private MoneyEditText met;
    private ImageView iv_bank_icon;
    private TextView tv_bank_name;
    private TextView tv_bank_num;
    private RelativeLayout rl_bank;
    private TextView tv_x2;
    private SuperTextView stv_confirm;
    private ArrorText at_add_bank;
    //
    private String balance = "";//获取余额以后初始化
    private BankCardListBean bank;
    public final static String NOTIFY_BALANCE = "NOTIFY_BALANCE";
    public final static String ITEM_WITHDRAWALS = "ITEM_WITHDRAWALS";
    public final static String ITEM_MONEY = "ITEM_MONEY";
    public final static String ITEM_C_IDS = "ITEM_C_IDS";
    public final static String ITEM_P_IDS = "ITEM_P_IDS";
    private String cids = "";
    private String pids = "";

    public static void start(Context context) {
        Intent it = new Intent(context, WithdrawalsActivity.class);
        context.startActivity(it);
    }

    private void initIntent() {
        Intent intent = getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawals);
        EventBus.getDefault().register(this);
        initView();
        getBalance(false);
    }


    private void initView() {
        met = findViewById(R.id.met);
        at_add_bank = (ArrorText) findViewById(R.id.at_add_bank);
        at_add_bank.setOnClickListener(this);
        iv_bank_icon = (ImageView) findViewById(R.id.iv_bank_icon);
        tv_bank_name = (TextView) findViewById(R.id.tv_bank_name);
        tv_bank_num = (TextView) findViewById(R.id.tv_bank_num);
        rl_bank = (RelativeLayout) findViewById(R.id.rl_bankcard);
        rl_bank.setOnClickListener(this);
        tv_x2 = (TextView) findViewById(R.id.tv_x2);
        stv_confirm = (SuperTextView) findViewById(R.id.stv_confirm);
        stv_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_bankcard:
                intentBankList();
                break;
            case R.id.stv_confirm:
                final String money = met.getMoney();
                if (XStringUtils.isEmpty(money) || !XStringUtils.isStringAreNum(money)) {
                    ToastUtils.showMessage("提现金额不能为0");
                    return;
                }
                if (Float.parseFloat(money) <= 0.0f) {
                    ToastUtils.showMessage("提现金额不能为0");
                    return;
                }
                if (bank != null) {
                    String tip = "是否向尾号<font color='#4bb4db'>" + XStringUtils.endFourNum(bank.getNumber()) + "</font>的银行卡提现<font color='#ff0000'>" + money + "</font>元";
                    new BalanceDialog(WithdrawalsActivity.this, Html.fromHtml(tip), new BalanceDialog.DialogButtonClick() {
                        @Override
                        public void onSure() {
                            if (XStringUtils.isEmpty(cids) && XStringUtils.isEmpty(pids)) {
                                withdrawals(bank.getId(), money);
                            }else{
                                itemWithdrawals(bank.getId(), money);
                            }
                        }
                    });
                } else {
                    ToastUtils.showMessage("请选择银行卡");
                }
                break;
            case R.id.at_add_bank:
                intentBankList();
                break;
        }
    }

    private void intentBankList() {
        if (bank != null) {
            BankCardListActivity.startActivityForResult(WithdrawalsActivity.this, bank.getId());
        } else {
            BankCardListActivity.startActivityForResult(WithdrawalsActivity.this, "0");
        }
    }

    private void withdrawals(String bank_id, final String money) {
//        bank_id	是	string	银行卡id
//        money	是	string	金额
        showprogressDialogCanNotCancel();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("bank_id", bank_id)
                .setParams("money", money)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess(String data) {
                dismissProgressDialog();
                ToastUtils.showMessage("提现成功，请等待审核");
                getBalance(true);
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

    private void itemWithdrawals(String bank_id, final String money) {
//        bank_id	是	string	银行卡id
//        money	是	string	金额
        showprogressDialogCanNotCancel();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("bank_id", bank_id)
                .setParams("money", money)
                .setParams("p_ids", pids)
                .setParams("c_ids", cids)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess(String data) {
                dismissProgressDialog();
                ToastUtils.showMessage("提现成功，请等待审核");
                getBalance(true);
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


    private void getBalance(final boolean isRefresh) {
        if (!isRefresh) {
            showprogressDialog();
        }
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess(String data) {
                dismissProgressDialog();
//                BalanceBean balanceBean = JSON.parseObject(data, BalanceBean.class);
//                balance = balanceBean.getBalance();
//                tv_balance.setText(balance + "元");
//                et_balance.setText(balance);
//                if (!isRefresh) {
//                    bank = balanceBean.getBank();
//                    if (bank == null) {
//                        at_add_bank.setVisibility(View.VISIBLE);
//                        rl_bank.setVisibility(View.GONE);
//                    } else {
//                        at_add_bank.setVisibility(View.GONE);
//                        rl_bank.setVisibility(View.VISIBLE);
//                        String open_bank = bank.getOpen_bank();
//                        String number = bank.getNumber();
//                        String image = bank.getImage();
//
//                        tv_bank_name.setText(open_bank);
//                        tv_bank_num.setText("尾号：" + XStringUtils.endFourNum(number));
//                        ImageUtils.loadCirclePic(WithdrawalsActivity.this, ConstantUrl.imageUrl + image, iv_bank_icon);
//
//                    }
//                }

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            //修正银行卡信息
            if (data.getParcelableExtra(BankCardListActivity.BANK_CARD_INFO) != null) {
                bank = data.getParcelableExtra(BankCardListActivity.BANK_CARD_INFO);
                String number = bank.getNumber();
                String open_bank = bank.getOpen_bank();
                String image = bank.getImage();

                tv_bank_name.setText(open_bank);
                tv_bank_num.setText("尾号：" + XStringUtils.endFourNum(number));
                ImageLoad.loadCircleImage(image, iv_bank_icon);
                at_add_bank.setVisibility(View.GONE);
                rl_bank.setVisibility(View.VISIBLE);
            } else {
                ToastUtils.showMessage("银行卡选择错误，请重新选择");
            }
        }
    }

    @Subscribe
    public void notifyBalance(EventBusBundle eventBusBundle) {
        if (eventBusBundle.getKey().equals(NOTIFY_BALANCE)) {
            getBalance(false);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void notifyMoney(final EventBusBundle eventBusBundle) {
        if (eventBusBundle.getKey().equals(ITEM_WITHDRAWALS)) {
            met.post(new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = eventBusBundle.getBundle();
                    String money = bundle.getString(ITEM_MONEY);
                    cids = bundle.getString(ITEM_C_IDS);
                    pids = bundle.getString(ITEM_P_IDS);
                    met.setMoney(money);
                }
            });
        }
    }


}
