package com.zeronight.templet.common.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.zeronight.templet.common.application.BaseApplication;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 通用工具类
 * <p>
 * Created by Administrator on 2016/8/13.
 */
public class CommonUtils {




    /**
     * 获取设备唯一编码
     */
    public static String getDeviceId() {
        TelephonyManager teleService =
                (TelephonyManager) BaseApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        return teleService.getDeviceId();
    }

    /**
     * 获取版本号
     */
    public static String getVersion(Context context) //获取版本号
    {
        try {
            PackageInfo pi = ApplicationUtils.getAppContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0.0";
        }
    }

    /**
     * 获取版本编号
     */
    public static int getVersionCode(Context context) //获取版本编号
    {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 判断网络连接
     */
    public static boolean isNetworkConnected() {

        if (BaseApplication.getInstance().getApplicationContext() != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) ApplicationUtils
                    .getAppContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 复制到粘贴板
     */
    public static void copy(Context context, String zcode) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(zcode);
        ToastUtils.showMessage("成功复制到粘贴板");
    }

    /**
     * 弹出软键盘
     */
    public static void showSoft(final AppCompatActivity context, final View view) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                view.requestFocus();
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        }, 300);
    }

    /**
     * 收起软键盘
     */
    public static void hideSoft(final AppCompatActivity context , final View view){
        if (view != null && context != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * px转dp
     */
    public static int px2dip ( Context context, float pxValue ) {
        final float scale = context. getResources ( ). getDisplayMetrics ( ). density ;
        return ( int ) (pxValue / scale + 0.5f ) ;
    }


    public static boolean isWeiboInstalled(@NonNull Context context) {
        PackageManager pm;
        if ((pm = context.getApplicationContext().getPackageManager()) == null) {
            return false;
        }
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo info : packages) {
            String name = info.packageName.toLowerCase(Locale.ENGLISH);
            if ("com.sina.weibo".equals(name)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isQQInstalled(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }


}
