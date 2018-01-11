package com.zeronight.templet.common.utils;

import android.widget.Toast;

import com.zeronight.templet.common.application.BaseApplication;


/**
 * Created by Administrator on 2016/7/25.
 */
public class ToastUtils {

    private static Toast toast;

    public static void showMessage(String message) {
        if (null == toast) {
            toast = Toast.makeText(BaseApplication.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        toast.cancel();
        toast = Toast.makeText(BaseApplication.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

}
