package com.zeronight.templet.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/14.
 */

public class TimeUtils {

    public final static String Y_M_D = "yyyy-MM-dd";
    public final static String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
    public final static String M_D_H_M = "MM-dd a HH:mm";
    public final static String H_M = "a HH:mm";
    public final static String M_D = "MM/dd";

    public static String converYMDHMSToHM(String times){
        try {
            SimpleDateFormat format = new SimpleDateFormat(Y_M_D_H_M_S);
            Date date = format.parse(times);
            long millinTime = date.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat(H_M);
            return sdf.format(millinTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String converYMDHMSToMD(String times){
        try {
            SimpleDateFormat format = new SimpleDateFormat(Y_M_D_H_M_S);
            Date date = format.parse(times);
            long millinTime = date.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat(M_D);
            return sdf.format(millinTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String converYMDHMSToMDHM(String times){
        try {
            SimpleDateFormat format = new SimpleDateFormat(Y_M_D_H_M_S);
            Date date = format.parse(times);
            long millinTime = date.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat(M_D_H_M);
            return sdf.format(millinTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String TimeToMillinYMD(String times) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(Y_M_D);
            Date date = format.parse(times);
            long millinTime = date.getTime();
            return millinTime + "";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public static long TimeToMillinYMDHMS(String times) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(Y_M_D_H_M_S);
            Date date = format.parse(times);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String convertTimeStampToTimeString(String timeFormat, long timeTamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        return sdf.format(timeTamp);
    }

}
