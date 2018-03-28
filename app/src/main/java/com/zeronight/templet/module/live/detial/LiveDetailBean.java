package com.zeronight.templet.module.live.detial;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */

public class LiveDetailBean {

    /**
     * title : 产品介绍
     * host_name : 王老师
     * start_time : 2017-12-12 00:00:00
     * img : /public/images/2018-03-07/5a9f563732358.png
     * status : 3
     * live_url : rtmp://video-center.alivecdn.com/fanqie/151306631048171?vhost=zhibo.tjtomato.com
     * guess_you_say : ["你好","66666","23333","地域黑死全家","26键输入法都是垃圾，9键才是王道"]
     */

    private String title;
    private String host_name;
    private String start_time;
    private String img;
    private String status;
    private String live_url;
    private List<String> guess_you_say;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLive_url() {
        return live_url;
    }

    public void setLive_url(String live_url) {
        this.live_url = live_url;
    }

    public List<String> getGuess_you_say() {
        return guess_you_say;
    }

    public void setGuess_you_say(List<String> guess_you_say) {
        this.guess_you_say = guess_you_say;
    }

}
