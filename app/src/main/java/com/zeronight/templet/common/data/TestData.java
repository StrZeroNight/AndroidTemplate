package com.zeronight.templet.common.data;

import com.zeronight.templet.module.address.list.AddressDetialBean;
import com.zeronight.templet.module.cart.CartBean;
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

    public static List<String> getLists(){
        List<String> list = new ArrayList<>();
        list.add("测试说");
        list.add("测");
        list.add("测试说测试说");
        list.add("测试说测试说测试说测试说");
        list.add("测试说测试说");
        list.add("测试说");
        return list;
    }

    public static List<CartBean> getCartLists(){
        List<CartBean> list = new ArrayList<>();
        list.add(new CartBean("1" , "2.50" , "3"));
        list.add(new CartBean("2" , "12.34" , "100"));
        list.add(new CartBean("3" , "645.34" , "7"));
        list.add(new CartBean("4" , "5476" , "4"));
        list.add(new CartBean("5" , "208" , "9"));
        return list;
    }

    public static List<AddressDetialBean> getAddress(){
        List<AddressDetialBean> list = new ArrayList<>();
        list.add(new AddressDetialBean("1" , "小七" , "15222898621" , "北京市" , "长安区长安区长安区长安区" , 0));
        list.add(new AddressDetialBean("2" , "小八" , "15222898621" , "北京市" , "长安区" , 0));
        list.add(new AddressDetialBean("3" , "小九" , "15222898621" , "北京市" , "长安区" , 1));
        list.add(new AddressDetialBean("4" , "小十" , "15222898621" , "北京市" , "长安区长安区" , 0));
        list.add(new AddressDetialBean("5" , "小六" , "15222898621" , "北京市" , "长安区" , 0));
        return list;
    }

}
