package com.zeronight.templet.common.data;

/**
 * Created by Administrator on 2018/1/3.
 */

public class CommonUrl {

    public final static String BASE_URL = "http://jinchen.tjtomato.com/client/";
    public final static String IMAGE_URL = "http://tianhong.tjtomato.com";
    public final static String WEB_URL = "http://gas.tjtomato.com/webapp/";

    public static final String login = "http://h5.yichuxiansheng.com/";

    //web 千万不要用static
    public String account = WEB_URL + "account.html?token=" + CommonData.getToken();

}
