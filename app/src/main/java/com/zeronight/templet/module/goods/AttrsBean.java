package com.zeronight.templet.module.goods;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public class AttrsBean implements Comparable<AttrsBean>{

    String attrId1;
    String title;
    List<AttrsBean2> attrs;

    public String getAttrId1() {
        return attrId1;
    }

    public void setAttrId1(String attrId1) {
        this.attrId1 = attrId1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AttrsBean2> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttrsBean2> attrs) {
        this.attrs = attrs;
    }


    public AttrsBean() {
    }

    public AttrsBean(String attrId1, String title, List<AttrsBean2> attrs) {
        this.attrId1 = attrId1;
        this.title = title;
        this.attrs = attrs;
    }

    @Override
    public String toString() {
        return "AttrsBean{" +
                "attrId1='" + attrId1 + '\'' +
                ", title='" + title + '\'' +
                ", attrs=" + attrs +
                '}';
    }

    @Override
    public int compareTo(@NonNull AttrsBean attrsBean) {
        return this.attrId1.compareTo(attrsBean.attrId1);
    }
}
