package com.zeronight.templet.common.application;

import android.app.Application;
import android.util.Log;
import android.view.WindowManager;

import com.tencent.smtt.sdk.QbSdk;
import com.zeronight.templet.R;
import com.zeronight.templet.common.widget.loadlayout.LoadingAndRetryManager;

/**
 * 应用程序基类
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;
    private WindowManager.LayoutParams wmParams=new WindowManager.LayoutParams();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //友盟相关配置
//        UMShareAPI.get(this);
//        MobclickAgent.openActivityDurationTrack(false);
//        PlatformConfig.setWeixin("wxda2579931c8a7867", "1e1934ca76f24080bde99ac348170b75");
//        PlatformConfig.setQQZone("1106358674", "I8bk02efo2zSwqGU");
//        PlatformConfig.setSinaWeibo("3423106190", "2d55536c8800eb2e2230559971703505", "https://www.baidu.com/");

        LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.base_retry;
        LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.base_loading;
        LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.base_empty;

        //腾讯tbs webview浏览器配置
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }
            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);

        QbSdk.getTBSInstalling();
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public WindowManager.LayoutParams getMywmParams(){
        return wmParams;
    }

}
