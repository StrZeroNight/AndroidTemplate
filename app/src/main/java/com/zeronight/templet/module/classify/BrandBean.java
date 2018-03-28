package com.zeronight.templet.module.classify;

/**
 * Created by Administrator on 2018/2/23.
 */

public class BrandBean {

    private String brandId;
    private String brandString;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandString() {
        return brandString;
    }

    public void setBrandString(String brandString) {
        this.brandString = brandString;
    }

    public BrandBean(String brandId, String brandString) {
        this.brandId = brandId;
        this.brandString = brandString;
    }

}
