package com.zeronight.templet.common.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.zeronight.templet.common.application.BaseApplication;
import com.zeronight.templet.common.data.CommonUrl;


/**
 * Created by Administrator on 2016/10/29.
 */
public class ImageLoad {

    private final static String imageUrl = CommonUrl.IMAGE_URL;

    /**
     * glide加载图片
     */
    public static void loadImage(String imagePath, ImageView imageView) {
        if(!XStringUtils.isEmpty(imagePath)){
            if (imagePath.startsWith("http")) {
                Glide.with(BaseApplication.getInstance().getApplicationContext())
                        .load(imagePath)
                        .into(imageView);
            }else{
                Glide.with(BaseApplication.getInstance().getApplicationContext())
                        .load(imageUrl + handleImagePath(imagePath))
                        .into(imageView);
            }
        }
    }


    /**
     * gradle加载圆形图片
     */
    public static void loadCircleImage(String url, final ImageView iv_show) {
        //加载圆角图片
        if(!XStringUtils.isEmpty(url)){
            if (url.startsWith("http")) {
                if (BaseApplication.getInstance().getApplicationContext() != null) {
                    RequestOptions requestOptions = new RequestOptions()
                            .centerCrop()
                            .transforms(new CircleCrop());
                    //加载圆角图片
                    Glide.with(BaseApplication.getInstance().getApplicationContext())
                            .load(url)
                            .apply(requestOptions)
                            .into(iv_show);
                }
            }else{
                if (BaseApplication.getInstance().getApplicationContext() != null) {
                    RequestOptions requestOptions = new RequestOptions()
                            .centerCrop()
                            .transforms(new CircleCrop());
                    //加载圆角图片
                    Glide.with(BaseApplication.getInstance().getApplicationContext())
                            .load(imageUrl + handleImagePath(url))
                            .apply(requestOptions)
                            .into(iv_show);
                }
            }
        }
    }


    private static String handleImagePath(String imagePath){
        if (imagePath == null) {
            imagePath = "";
        }
        String imagePathF = "";
        if (!imagePath.startsWith("/")) {
            imagePathF = "/" + imagePath;
        }else{
            imagePathF = imagePath;
        }
        return imagePathF;
    }




}
