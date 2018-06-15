package com.zeronight.templet.common.utils;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * Created by Administrator on 2018/6/8.
 */

public class MapUtils {

    AppCompatActivity activity;

    public MapUtils(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void mapIntent(String latitude , String longitude , String shopName){
        Logger.i("latitude:" + latitude + "  longitude:" + longitude);
        if (isInstallByread("com.baidu.BaiduMap") || isInstallByread("com.autonavi.minimap")) {
          Uri mUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + shopName);
          Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
          activity.startActivity(mIntent);
        }else{
            ToastUtils.showMessage("您没有安装百度或高德地图，请安装后重试");
        }
    }

    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

}
