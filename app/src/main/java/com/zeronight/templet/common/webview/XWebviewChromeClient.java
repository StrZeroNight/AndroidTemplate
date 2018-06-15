package com.zeronight.templet.common.webview;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.orhanobut.logger.Logger;
import com.zeronight.templet.common.data.CommonData;

/**
 * Created by Administrator on 2017/8/17.
 */

public class XWebviewChromeClient extends WebChromeClient {

    private WebViewActivity webViewActivity;

    public XWebviewChromeClient(WebViewActivity webViewActivity) {
        this.webViewActivity = webViewActivity;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        String titles = "";
        if (title != null) {
            titles = title;
        }
        Logger.i("title:" + title);
        webViewActivity.setTitle(titles);
        //为了保证web端能获取到用户信息 在三个地方放置传递用户id的数据
        view.loadUrl("javascript:getuser('" + CommonData.getUserId() + "','" + CommonData.getToken() + "','" + WebViewActivity.ANDROID + "')");

    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (newProgress >= 100) {
            webViewActivity.progress.setVisibility(View.GONE);
        }else{
            webViewActivity.progress.setVisibility(View.VISIBLE);
        }
        webViewActivity.progress.setProgress(newProgress);
    }


}
