package com.zeronight.templet.module.goods;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public class GoodAttrsBean {

    String title;
    List<String> attrs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<String> attrs) {
        this.attrs = attrs;
    }

    public GoodAttrsBean(String title, List<String> attrs) {
        this.title = title;
        this.attrs = attrs;
    }

    public GoodAttrsBean() {
    }

}
