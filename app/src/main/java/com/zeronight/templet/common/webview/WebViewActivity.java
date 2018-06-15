package com.zeronight.templet.common.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.orhanobut.logger.Logger;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.CommonData;
import com.zeronight.templet.common.data.EventBusBundle;
import com.zeronight.templet.common.utils.CommonUtils;
import com.zeronight.templet.common.widget.TitleBar;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * http://www.jianshu.com/p/3c94ae673e2a参考此网站
 * Created by Administrator on 2017/8/16.
 */

public class WebViewActivity extends BaseActivity {

    private final static String URL = "URL";
    public final static String NOTIFY_WEBVIEW = "NOTIFY_WEBVIEW";
    public final static String ANDROID = "1";
    public WebView webview;
    public TitleBar titlebar;
    public static ProgressBar progress;
    private WebSettings webSettings;
    private XWebviewClient xWebviewClient;
    private XWebviewChromeClient xWebviewChromeClient;

    public static void start(Context context, String url) {
        Intent it = new Intent(context, WebViewActivity.class);
        it.putExtra(URL, url);
        context.startActivity(it);
    }

    public void initIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra(URL) != null) {
            String url = intent.getStringExtra(URL);
            webview.clearCache(true);
            webview.addJavascriptInterface(new JavaInterface(WebViewActivity.this), "javaObject");
            Logger.i("url:" + url);
            webview.loadUrl(url);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        EventBus.getDefault().register(this);
        iniView();
        setWebView();
        initIntent();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void iniView() {
        titlebar = findViewById(R.id.titlebar);
        progress = findViewById(R.id.progress);
        webview = findViewById(R.id.webview);
        titlebar.setOnTitlebarClickListener(new TitleBar.TitlebarClick() {
            @Override
            public void onRightClick() {

            }

            @Override
            public void onBackClick() {
                back();
            }
        });
    }

    private void setWebView() {
        initClient();
        initChrome();
        webSettings = webview.getSettings();
        setWebviewJs();
        setWebviewZoom();
        setWebviewAccess();
        setWebviewDate();
        setWebviewCache();
        setWebviewSafe();
    }

    private void setWebviewSafe() {
        //关闭危险接口
        webview.removeJavascriptInterface("searchBoxJavaBridge_");
        webview.removeJavascriptInterface("accessibility");
        webview.removeJavascriptInterface("accessibilityTraversal");
        //关闭自动保存密码功能，放置明文存放数据
        webSettings.setSavePassword(false);
    }

    private void setWebviewCache() {
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        if (CommonUtils.isNetworkConnected()) {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//根据cache-control决定是否从网络上取数据。
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//没网，则从本地获取，即离线加载
        }
    }

    private void setWebviewAccess() {
        webSettings.setAllowFileAccess(false);
        webSettings.setAllowFileAccessFromFileURLs(false);
        webSettings.setAllowUniversalAccessFromFileURLs(false);
    }

    private void setWebviewDate() {
        webSettings.setDatabaseEnabled(true);//允许数据存储
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }

    private void setWebviewZoom() {
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
    }

    private void setWebviewJs() {
        webSettings.setJavaScriptEnabled(true);//支持js
        webSettings.setDomStorageEnabled(true);//允许js交互
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口，也就是支持js弹窗
    }

    private void initChrome() {
        xWebviewChromeClient = new XWebviewChromeClient(WebViewActivity.this);
        webview.setWebChromeClient(xWebviewChromeClient);
    }

    private void initClient() {
        xWebviewClient = new XWebviewClient(WebViewActivity.this);
        webview.setWebViewClient(xWebviewClient);
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (webview != null) {
            webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webview.clearHistory();
            ((ViewGroup) webview.getParent()).removeView(webview);
            webview.destroy();
            webview = null;
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webview.canGoBack()) {
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void back() {
        if (webview.canGoBack()) {
            WebBackForwardList webBackForwardList = webview.copyBackForwardList();
            for (int i = 0; i < webBackForwardList.getSize(); i++) {
                WebHistoryItem itemAtIndex = webBackForwardList.getItemAtIndex(i);
                Logger.i("webview: ============================ itemAtIndex ============================================");
                Logger.i("webview: ==========itemAtIndex Url:" + itemAtIndex.getUrl());
                Logger.i("webview: ===========itemAtIndex Title:" + itemAtIndex.getTitle());
            }
            if (webview.getUrl().contains("no_network")) {
                if (webview.copyBackForwardList().getSize() == 2) {
                    finish();
                } else {
                    webview.goBackOrForward(-2);
                }
            } else {
                webview.goBack();
            }

        } else {
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        webview.stopLoading();
    }


    @Subscribe
    public void getLeadUnit(EventBusBundle eventBusBundle) {
        if (eventBusBundle.getKey().equals(NOTIFY_WEBVIEW)) {
            webview.loadUrl("javascript:getuser('" + CommonData.getUserId() + "','" + CommonData.getToken() + "','" + ANDROID + "')");
            webview.reload();
        }
    }

    public void goBack() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (webview.canGoBack()) {
                    webview.goBack();
                } else {
                    finish();
                }
            }
        });
    }

    public void initUserInfo() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webview.loadUrl("javascript:getuser('" + CommonData.getUserId() + "','" + CommonData.getToken() + "','" + ANDROID + "')");
            }
        });
    }

    public void setTitle(String title){
        titlebar.setTitle(title);
    }

}
