package com.zeronight.templet.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 公共方法区首次设置和每次的读取放在闪屏页的地方
 * 默认使用包名做为缓存文件名
 * 使用之前在minfest里面注册application name
 */

public class AppSetting {
	
	//获取项目包名  get package name
	private static final String PACKAGE_NAME = ApplicationUtils.getPackageInfo().packageName + ".config";
	
	private static final String PRE_NIGHT = "night";
	private static final String PRE_FIRST = "isFirst";
	private static final String PRE_SEND_INFO = "isSendInfo";
	private static final String PRE_SAVE_FLOW = "isSaveFlow";
	
	//获取以项目包名命名的sps文件 use packagename as sharepreference's file name
	public static SharedPreferences getSharedPreferences() {
		return ApplicationUtils.getAppContext().getSharedPreferences(
				"PACKAGE_NAME" , Context.MODE_PRIVATE);
	}

	public static String getString(String key) {
		return getSharedPreferences().getString(key, "");
	}

	public static Boolean getBoolean(String key) {
		return getSharedPreferences().getBoolean(key, false);
	}

	public static int getInt(String key) {
		return getSharedPreferences().getInt(key, 0);
	}

	public static void putInt(String key, int value) {
		getSharedPreferences().edit().putInt(key, value).commit();
	}

	public static long getLong(String key) {
		return getSharedPreferences().getLong(key, 0L);
	}

	public static void putLong(String key, long value) {
		getSharedPreferences().edit().putLong(key, value).commit();
	}

	public static void putString(String key, String value) {
		getSharedPreferences().edit().putString(key, value).commit();
	}

	public static void putBoolean(String key, Boolean value) {
		getSharedPreferences().edit().putBoolean(key, value).commit();
	}

	// 清除所有缓存数据
	public static void clear() {
		getSharedPreferences().edit().clear().commit();
	}

}
