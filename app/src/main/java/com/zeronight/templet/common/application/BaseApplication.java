package com.zeronight.templet.common.application;

import android.app.Application;

import com.zeronight.templet.R;
import com.zeronight.templet.common.widget.loadlayout.LoadingAndRetryManager;


/**
 * 应用程序基类
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

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

    }

    public static BaseApplication getInstance() {
        return instance;
    }

}
