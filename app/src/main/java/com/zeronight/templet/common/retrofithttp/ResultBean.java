package com.zeronight.templet.common.retrofithttp;

/**
 * Created by Administrator on 2017/8/21.
 */

public class ResultBean {

    /**
     * returnCode : 0
     * message : 验证码失效
     * resultData : null
     * time : 1503286683007
     */

    private int returnCode;
    private String message;
    private String resultData;
    private long time;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
