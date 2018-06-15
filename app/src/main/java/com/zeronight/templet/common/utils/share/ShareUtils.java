package com.zeronight.templet.common.utils.share;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zeronight.templet.R;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.wxapi.WxUtils;

/**
 * Created by Administrator on 2018/3/12.
 */

public class ShareUtils {

    private static AppCompatActivity appCompatActivity;
    private static ShareUtils shareUtils = null;

    private ShareUtils(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public static ShareUtils getInstance(AppCompatActivity appCompatActivity) {
        if (shareUtils == null) {
            shareUtils = new ShareUtils(appCompatActivity);
        }
        return shareUtils;
    }

    public void showShareWindowx(View showParentView , final String shareUrl) {
        LayoutInflater layoutInflater = (LayoutInflater) appCompatActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popuView = layoutInflater.inflate(R.layout.popu_share, null);
        //设置弹出窗口参数
        final PopupWindow popupWindow = new PopupWindow(popuView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = appCompatActivity.getWindow().getAttributes();
        lp.alpha = 0.3f;

        ImageView iv_back_share = (ImageView) popuView.findViewById(R.id.iv_back_share);
        TextView tv_weixin_share = (TextView) popuView.findViewById(R.id.tv_weixin_share);
        TextView tv_friend_share = (TextView) popuView.findViewById(R.id.tv_friend_share);

        appCompatActivity.getWindow().setAttributes(lp);
        //消去遮罩
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = appCompatActivity.getWindow().getAttributes();
                lp.alpha = 1f;
                appCompatActivity.getWindow().setAttributes(lp);
                if (onShareClickListener != null ) {
                    onShareClickListener.cancel();
                }
            }
        });
        iv_back_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        tv_weixin_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WxUtils.getInstance().showShareCircleOfFrends(shareUrl, "耐人儿淘" , "新人注册送红包，一起购物一起嗨");
            }
        });
        tv_friend_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WxUtils.getInstance().showShareFrends(shareUrl, "耐人儿淘" , "新人注册送红包，一起购物一起嗨");
            }
        });
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        popupWindow.showAtLocation(showParentView, Gravity.BOTTOM, 0, 0);
    }

    // TODO: 2018/4/8 回调处理
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showMessage("分享成功");
            if (onShareClickListener != null ) {
                onShareClickListener.success();
            }
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.showMessage("分享失败");
            Logger.i("onError: 分享失败" + t.getCause());
            if (onShareClickListener != null ) {
                onShareClickListener.fail();
            }
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showMessage("取消分享");
            if (onShareClickListener != null ) {
                onShareClickListener.fail();
            }
        }
    };


    public void unRegister() {
        appCompatActivity = null;
    }

    private OnShareClickListener onShareClickListener;
    public interface OnShareClickListener{
        void cancel();
        void success();
        void fail();
    }

    public void setOnShareClickListener(OnShareClickListener onShareClickListener){
        this.onShareClickListener = onShareClickListener;
    }

}
