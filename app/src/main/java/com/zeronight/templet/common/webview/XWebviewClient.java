package com.zeronight.templet.common.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.orhanobut.logger.Logger;
import com.zeronight.templet.common.data.CommonData;
import com.zeronight.templet.common.utils.CommonUtils;

/**
 * Created by Administrator on 2017/8/17.
 */

public class XWebviewClient extends WebViewClient {

    private Context mContext;
    public String wrongBeforePage = "";
    private boolean isRefresh = false;

    public XWebviewClient(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //不跳转浏览器
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);

        Logger.i("onReceivedError error:" + error.getDescription());//这里没有影响
        if (view.getUrl() != null ) {
            if (!view.getUrl().contains("no_network") && !view.getUrl().contains("kefu")) {
                //只有第一次的时候加载错误页面
                wrongBeforePage = view.getUrl();
                view.loadUrl("file:///android_asset/no_network.html");
            }
        }else{
            view.loadUrl("file:///android_asset/no_network.html");
        }

    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        WebViewActivity webViewActivity = (WebViewActivity) this.mContext;
        webViewActivity.setTitle(view.getTitle());


        if (!CommonUtils.isNetworkConnected()) {
            isRefresh = true;
        } else {
            if (isRefresh) {
                view.goBackOrForward(-1);
                isRefresh = false;
                webViewActivity.setTitle(view.getTitle());
            }
        }

        Logger.i("url: " + url + "\n"
                + "javascript:getuser('" + CommonData.getUserId() + "','" + CommonData.getToken() + "','" + WebViewActivity.ANDROID + "')");
        view.loadUrl("javascript:getuser('" + CommonData.getUserId() + "','" + CommonData.getToken() + "','" + WebViewActivity.ANDROID + "')");

    }


}
