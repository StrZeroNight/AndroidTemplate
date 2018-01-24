package com.zeronight.templet.module.goods;

/**
 * Created by Administrator on 2018/1/24.
 */

public class AttrsBean2 {

    String id;
    String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AttrsBean2(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public AttrsBean2() {
    }


    @Override
    public String toString() {
        return "AttrsBean2{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
