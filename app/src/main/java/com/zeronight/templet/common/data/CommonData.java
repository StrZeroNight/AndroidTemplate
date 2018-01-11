package com.zeronight.templet.common.data;

import com.zeronight.templet.common.utils.AppSetting;
import com.zeronight.templet.common.utils.XStringUtils;

/**
 * Created by Administrator on 2018/1/3.
 */

public class CommonData {

    public static String token = "";
    public static String userLoginName = "";
    public static int userId = 0;
    public static String userPhone = "";
    public static String userInviteCode = "";

    public static String getToken(){
        if (XStringUtils.isEmpty (token)) {
            if (!XStringUtils.isEmpty(AppSetting.getString(CommonString.USER_TOKEN))) {
                token = AppSetting.getString(CommonString.USER_TOKEN);
            }
        }
        return token;
    }

    public static void clearToken(){
        token = "";
    }

    public static String getUserLoginName(){
        if (XStringUtils.isEmpty(userLoginName)) {
            if (!XStringUtils.isEmpty(AppSetting.getString(CommonString.USER_LOGIN_NAME))) {
                userLoginName = AppSetting.getString(CommonString.USER_LOGIN_NAME);
            }
        }
        return userLoginName;
    }

    public static void clearUserLoginName(){
        userLoginName = "";
    }


    public static String getUserInviteCode(){
        if (XStringUtils.isEmpty(userInviteCode)) {
            if (!XStringUtils.isEmpty(AppSetting.getString(CommonString.USER_INVITE))) {
                userInviteCode = AppSetting.getString(CommonString.USER_INVITE);
            }
        }
        return userInviteCode;
    }

    public static void clearUserInviteCode(){
        userInviteCode = "";
    }

    public static String getUserPhone(){
        if (XStringUtils.isEmpty(userPhone)) {
            if (!XStringUtils.isEmpty(AppSetting.getString(CommonString.USER_PHONE))) {
                userPhone = AppSetting.getString(CommonString.USER_PHONE);
            }
        }
        return userPhone;
//        return "18222233333";
    }

    public static void clearUserPhone(){
        userPhone = "";
    }

    public static String getUserId(){
        if (userId == 0) {
            if (AppSetting.getInt(CommonString.USER_ID) != 0 ) {
                userId = AppSetting.getInt(CommonString.USER_ID);
            }
        }
        return userId + "";
//        return "456";
    }

    public static void clearUserId(){
        userId = 0;
    }





}
