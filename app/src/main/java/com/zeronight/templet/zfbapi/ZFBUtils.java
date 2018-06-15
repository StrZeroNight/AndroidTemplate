package com.zeronight.templet.zfbapi;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/31.
 */

public class ZFBUtils {

    private AppCompatActivity context;
    private String data;

    private static final int PAY_FLAG = 1;

    Runnable payRunnable = new Runnable() {
        @Override
        public void run() {

            PayTask payTask = new PayTask(context);

            Logger.i("支付宝信息：" + data);
            Map<String, String> pay = payTask.payV2(data , true);
            //
            Message msg = new Message();
            msg.what = PAY_FLAG;
            msg.obj = pay;
            mHandler.sendMessage(msg);

        }
    };


    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case PAY_FLAG:

                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    // 同步返回需要验证的信息    支付信息等
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        EventBus.getDefault().post(new EventBusBundle(PayActivity.NOTIFY_PAY_SUCCESS, ""));
                    } else {
//                        EventBus.getDefault().post(new EventBusBundle(PayActivity.NOTIFY_PAY_FAIL, ""));
                    }
                    break;

            }
        }
    };

    public ZFBUtils(AppCompatActivity context) {
        this.context = context;
    }

    public void startZfb(String data , String money){
        this.data = data;
        Thread paythread = new Thread(payRunnable);
        paythread.start();
    }


}
