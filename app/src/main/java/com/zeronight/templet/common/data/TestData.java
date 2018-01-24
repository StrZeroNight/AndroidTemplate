package com.zeronight.templet.common.data;

import com.zeronight.templet.module.goods.AttrsBean2;
import com.zeronight.templet.module.goods.AttrsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */

public class TestData {

    public static List<AttrsBean> getAttrs(){
        List<AttrsBean> list = new ArrayList<>();
        List<AttrsBean2> childlist = new ArrayList<>();
        childlist.add(new AttrsBean2("1" , "粉红色"));
        childlist.add(new AttrsBean2("2" , "红色"));
        childlist.add(new AttrsBean2("3" , "蓝色"));
        childlist.add(new AttrsBean2("4" , "粉红色"));
        childlist.add(new AttrsBean2("5" , "浅紫色"));
        childlist.add(new AttrsBean2("6" , "粉红色"));
        childlist.add(new AttrsBean2("7" , "红色"));
        childlist.add(new AttrsBean2("8" , "蓝色蓝色蓝色"));
        list.add(new AttrsBean("1" , "颜色1" , childlist));
        list.add(new AttrsBean("2" , "颜色2" , childlist));
        list.add(new AttrsBean("3" , "颜色3" , childlist));
        list.add(new AttrsBean("4" , "颜色4" , childlist));
        list.add(new AttrsBean("5" , "颜色5" , childlist));
        list.add(new AttrsBean("6" , "颜色6" , childlist));
        list.add(new AttrsBean("7" , "颜色7" , childlist));
        list.add(new AttrsBean("8" , "颜色8" , childlist));
        return list;
    }

}
