package com.zeronight.templet.common.retrofithttp;

/**
 * Created by Administrator on 2017/8/21.
 */

public class ResultBean {

    /**
     * code : 0
     * msg : 验证码失效
     * data : null
     * time : 1503286683007
     */

    private int code;
    private String msg;
    private String data;
    private long time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
