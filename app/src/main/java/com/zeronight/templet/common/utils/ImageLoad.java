package com.zeronight.templet.common.utils;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.orhanobut.logger.Logger;
import com.zeronight.templet.common.application.BaseApplication;


/**
 * Created by Administrator on 2016/10/29.
 */
public class ImageLoad {

    private final static String imageUrl = "";

    /**
     * glide加载图片
     */
    public static void loadImage(String imagePath, ImageView imageView) {

        Logger.i("图片加载的内容：" + imageUrl + handleImagePath(imagePath));
        Glide.with(BaseApplication.getInstance().getApplicationContext())
                .load(imageUrl + handleImagePath(imagePath))
                .into(imageView);
    }

    /**
     * glide加载图片 传入默认图片
     */
    public static void loadImageWithErrorDrawable(String imagePath, ImageView imageView, int errorDrawable) {
        Glide.with(BaseApplication.getInstance().getApplicationContext())
                .load(imageUrl + handleImagePath(imagePath))
                .placeholder(errorDrawable)
                .into(imageView);
    }

    /**
     * gradle加载圆形图片
     */
    public static void loadCircleImage(String url, final ImageView iv_show) {
        //加载圆角图片
        if (BaseApplication.getInstance().getApplicationContext() != null) {
            Glide.with(BaseApplication.getInstance().getApplicationContext())
                    .load(imageUrl + handleImagePath(url))
                    .asBitmap()
                    .centerCrop()
                    .into(new BitmapImageViewTarget(iv_show) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(BaseApplication.getInstance().getApplicationContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    iv_show.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }

    /**
     * gradle加载圆形图片
     */
    public static void loadCircleImage(String url, final ImageView iv_show , int errorDrawable) {
        //加载圆角图片
        if (BaseApplication.getInstance().getApplicationContext() != null) {
            Glide.with(BaseApplication.getInstance().getApplicationContext())
                    .load(imageUrl + handleImagePath(url))
                    .asBitmap()
                    .centerCrop()
                    .error(errorDrawable)
                    .placeholder(errorDrawable)
                    .into(new BitmapImageViewTarget(iv_show) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(BaseApplication.getInstance().getApplicationContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    iv_show.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }

    /**
     * gradle加载圆角矩形
     */
    public static void loadRoundedRectangleIamge(String url, final ImageView iv_show , final int radius) {
        //加载圆角图片
        Glide.with(BaseApplication.getInstance().getApplicationContext())
                .load(url)
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(iv_show) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(BaseApplication.getInstance().getApplicationContext().getResources() , resource);
                roundedBitmapDrawable.setCornerRadius(radius);
                iv_show.setImageDrawable(roundedBitmapDrawable);
            }
        });
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
