package com.zeronight.templet.wxapi;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zeronight.templet.R;
import com.zeronight.templet.common.application.BaseApplication;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
        api = WXAPIFactory.createWXAPI(BaseApplication.getInstance().getApplicationContext() , WxUtils.WXAppId);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        Logger.d("==================== 微信支付请求 ====================" + "\n" +
                "onReq  openId = " + req.openId + "(openId)" + "\n" +
                "onReq  transaction = " + req.transaction);
    }

    @Override
    public void onResp(BaseResp resp) {
        Logger.d("==================== 微信支付结果 ====================" + "\n" +
                "onPayFinish  errCode = " + resp.errCode + "(errCode为0就是成功了)" + "\n" +
                "onPayFinish  errStr = " + resp.errStr + "\n" +
                "onPayFinish  openId = " + resp.openId);
        if (resp.errCode == 0) {
            //支付成功以后的处理
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    EventBus.getDefault().post(new EventBusBundle(PayActivity.NOTIFY_PAY_SUCCESS, ""));
                }
            }, 800);
            finish();
        } else {
            //支付失败以后的处理
//            EventBus.getDefault().post(new EventBusBundle(PayActivity.NOTIFY_PAY_FAIL, ""));
            finish();
        }
    }


}