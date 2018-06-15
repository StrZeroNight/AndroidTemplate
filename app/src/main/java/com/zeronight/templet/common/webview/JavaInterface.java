package com.zeronight.templet.common.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.zeronight.templet.module.login.LoginActivity;

/**
 * 让前端调用的java方法 尽量不要调用复杂的方法 宁可自己写页面
 * Created by Administrator on 2017/8/18.
 */

public class JavaInterface extends Object {

    private Context mContext;

    public JavaInterface(Context context) {
        mContext = context;
    }

    @JavascriptInterface
    public void toLogin() {
        LoginActivity.start(mContext);
    }

    @JavascriptInterface
    public void appshare() {

    }

//    @JavascriptInterface
//    public void finish() {
//        ((WebViewActivity) mContext).runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                EventBus.getDefault().post(new EventBusBundle(OrderListActivity.NOTIFY_MAIN , ""));
//            }
//        });
//        ((WebViewActivity)mContext).finish();
//    }

}
