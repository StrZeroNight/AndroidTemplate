package com.zeronight.templet.common.utils;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * sps存放和获取历史数据数据
 *
 * Created by Administrator on 2016/6/21.
 */
public class SearchHistoryUtils {

    public final static String SEARCH_HISTORY= "SEARCH_HISTORY";

    //把搜索历史记录放到sps中
    public static void storeHistory(String storeStr , String key){
        //不保存""
        if (!storeStr.isEmpty()) {
            String string = AppSetting.getString(key);
            if (!string.isEmpty()){
                List<String> strings = JSON.parseArray(string, String.class);
                //判断数据是否重复
                if (!strings.contains(storeStr)){
                    strings.add(storeStr);
                }
                String jsonString = JSON.toJSONString(strings);
                AppSetting.putString(key , jsonString);
            }else{
                List<String> strings = new ArrayList<>();
                strings.add(storeStr);
                String jsonString = JSON.toJSONString(strings);
                AppSetting.putString(key , jsonString);
            }
        }
    }

    //获取历史记录列表
    public static List<String> getHistory(String key ) {
        List<String> historyList = new ArrayList<>();
        String string = AppSetting.getString(key);
        if (!string.isEmpty()){
            if (JSON.parseArray(string, String.class) != null) {
                historyList = JSON.parseArray(string, String.class);
            }
        }
        return historyList;
    }

    //清除历史记录
    public static void clearHistory(String key){
        AppSetting.putString(key , "");
    }




}
