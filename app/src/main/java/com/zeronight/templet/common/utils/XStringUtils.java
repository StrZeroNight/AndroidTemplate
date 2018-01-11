package com.zeronight.templet.common.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文字工具类封装
 * Created by Zero on 2016/6/20.
 */
public class XStringUtils {

    private final static String PHONE_NOT_NORMAL = "手机号不是标准手机号";
    private final static String PHONE_CAN_NOT_BE_NULL = "手机号不能为空";

    /**
     * 是否包含特殊字符校验
     */
    public static boolean isStringIncludeSpecial(String str) {
        String regEx = "[0-9[a-zA-Z]] ";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String result = m.replaceAll("").trim();
        if (result.equals(str)) {
            return true;
        }
        return false;
    }

    //文字长度校验
    public static boolean checkStringLength(int minLength, int maxLength, String str) {
        if (str.length() > maxLength && str.length() < minLength) {
            return false;
        }
        return true;
    }

    /**
     * 手机号11位验证+正则表达式验证+空判断
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     * 总结起来就是第一位必定为1，第二位必定为13458，其他位置的可以为0-9
     */
    public static boolean checkPhoneNum(String phoneNum) {
        String telRegex = "[1][123456789]\\d{9}";
        if (!phoneNum.isEmpty()) {
            if (phoneNum.length() == 11) {//判断位数
                if (phoneNum.matches(telRegex)) {//判断正则表达式
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 判断填写的是不是数字
     */
    public static boolean isStringAreNum(String str) {
        Pattern pattern = Pattern.compile("[0-9.]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    /**
     * 增加灰色删除线
     */
    public static SpannableString addGrayDelete(String str , int start , int end) {
        SpannableString msp = new SpannableString(str);
        msp.setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return msp;
    }

    /**
     * 部分字体大小变化+颜色修改
     * size表示字体放大缩小的倍数 0.5f和2f
     */
    public static SpannableString changeTextSizeAndColor(String str, float size, int start, int end, int color) {
        SpannableString msp = new SpannableString(str);
        //设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍
        msp.setSpan(new RelativeSizeSpan(size), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (color != 0) {
            msp.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色
        }
        return msp;
    }

    public static SpannableString changeTextColor(String str, int start, int end, int color) {
        SpannableString msp = new SpannableString(str);
        //设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍
        if (color != 0) {
            msp.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色
        }
        return msp;
    }

    public static SpannableString changeTextSize(String str, float size, int start, int end) {
        SpannableString msp = new SpannableString(str);
        msp.setSpan(new RelativeSizeSpan(size), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return msp;
    }

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        if (str.isEmpty() || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static String switchListToArrayString(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                stringBuffer.append(list.get(i) + ",");
            } else {
                stringBuffer.append(list.get(i));
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    /**
     * 隐藏手机号中间四位
     */
    public static String hidePhoneMiddle(String phone){
        return phone.substring(0, 3) + "****" + phone.substring(7, 11);
    }

    public static SpannableString setStringClick(String content , int start , int end ,final StringClickListener stringClickListener){
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                widget.clearFocus();
                widget.clearAnimation();
                stringClickListener.onStringClick();
            }
        }, start , end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public interface StringClickListener{
        void onStringClick();
    }

    public static String handleEmpty(String str , String replace) {
        if (str == null) {
            return replace;
        }
        if (str.isEmpty() || str.trim().equals("")) {
            return replace;
        }
        return str;
    }

    public static void checkPhoneNumNullAndFormatReturn(String phoneNum){
        if (isEmpty(phoneNum)) {
            ToastUtils.showMessage(PHONE_NOT_NORMAL);
            return;
        }
        if (!checkPhoneNum(phoneNum)) {
            ToastUtils.showMessage(PHONE_CAN_NOT_BE_NULL);
            return;
        }
    }

    public static void isPhoneEmptyReturn(String str) {
        if (str == null || str.trim().equals("") || str.isEmpty()) {
            ToastUtils.showMessage(PHONE_CAN_NOT_BE_NULL);
            return ;
        }
    }

    public static String endFourNum(String str) {
        String end = "";
        if (!isEmpty(str) && str.length() > 4){
            end = str.substring(str.length() - 4 , str.length());
        }
        return end;
    }

}
