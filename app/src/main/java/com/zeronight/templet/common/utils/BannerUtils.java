package com.zeronight.templet.common.utils;

import android.view.View;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by Administrator on 2018/4/12.
 */
public class BannerUtils {

    public BannerUtils() {
    }

    public void iniBanner(BGABanner banner, final List<String> banners) {
        if (banners != null && banners.size() > 0) {
            List<String> imageList = new ArrayList<>();
            List<String> stringList = new ArrayList<>();
            //拼接图片url
            for (int i = 0; i < banners.size(); i++) {
                imageList.add(banners.get(i).toString());
                stringList.add("");
            }
            //初始化数据
            banner.setOverScrollMode(View.OVER_SCROLL_NEVER);
            banner.setAdapter(new BGABanner.Adapter() {
                @Override
                public void fillBannerItem(final BGABanner banner, View view, Object model, final int position) {
                    final ImageView iv = (ImageView) view;
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    ImageLoad.loadImage((String)model , iv);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (onBannerClickListener != null) {
                                onBannerClickListener.OnBannerClicke(position);
                            }
                        }
                    });
                }
            });
            banner.setData(imageList, stringList);
        }else{
            Logger.e("暂无轮播图数据");
        }
    }

    private OnBannerClickListener onBannerClickListener;
        public interface OnBannerClickListener{
            void OnBannerClicke(int position);
        }

        public void setOnTabClickListener(OnBannerClickListener onBannerClickListener){
            this.onBannerClickListener = onBannerClickListener;
        }

}
