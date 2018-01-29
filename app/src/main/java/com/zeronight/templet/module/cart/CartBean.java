package com.zeronight.templet.module.cart;

/**
 * Created by Administrator on 2018/1/29.
 */

public class CartBean {

    String id;
    String price;
    String num;

    public CartBean(String id, String price, String num) {
        this.id = id;
        this.price = price;
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CartBean() {
    }

    @Override
    public String toString() {
        return "CartBean{" +
                "id='" + id + '\'' +
                ", price='" + price + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

}
