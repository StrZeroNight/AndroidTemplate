package com.zeronight.templet.common.retrofithttp;

import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.CommonString;
import com.zeronight.templet.common.utils.AppSetting;
import com.zeronight.templet.module.login.LoginActivity;

/**
 * Created by Administrator on 2018/2/28.
 */

public class CommenMethod {

    private BaseActivity baseActivity;

    public CommenMethod(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }


    public void isLogined(LoginClickListener loginClickListener) {
        if (AppSetting.getBoolean(CommonString.USER_IS_LOGIN)) {
            if (loginClickListener != null) {
                loginClickListener.logined();
            }
        } else {
            LoginActivity.start(baseActivity);
        }
    }

    public interface LoginClickListener {
        void logined();
    }


}
