package com.zeronight.templet.common.data;

import com.zeronight.templet.common.utils.AppSetting;

/**
 * Created by Administrator on 2018/1/3.
 */

public class CommonData {

    public static boolean getIsLogin() {
        return AppSetting.getBoolean(CommonString.USER_IS_LOGIN);
    }

    public static String getToken() {
        return AppSetting.getString(CommonString.USER_TOKEN);
    }

    public static String getUserLoginName() {
        return AppSetting.getString(CommonString.USER_LOGIN_NAME);
    }

    public static String getUserInviteCode() {
        return AppSetting.getString(CommonString.USER_INVITE);
    }


    public static String getUserPhone() {
        return AppSetting.getString(CommonString.USER_PHONE);
    }

    public static String getUserId() {
        return AppSetting.getString(CommonString.USER_ID);
    }

}
