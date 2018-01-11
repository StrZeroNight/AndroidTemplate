package com.zeronight.templet.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.zeronight.templet.common.application.BaseApplication;

/**
 * 全局工具类
 * @author Zyd
 */
public class ApplicationUtils {

	// 全局上下文环境获取
	public static Context getAppContext() {
		return BaseApplication.getInstance().getApplicationContext();
	}

	/**
	 * 获取本程序包信息
	 * @return
	 */
	public static PackageInfo getPackageInfo() {
		try {
			PackageInfo pinfo = getAppContext().getPackageManager()
					.getPackageInfo(getAppContext().getPackageName(), 0);
			return pinfo;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


}
