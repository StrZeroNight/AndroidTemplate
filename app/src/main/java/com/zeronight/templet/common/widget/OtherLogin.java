package com.zeronight.templet.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zeronight.templet.R;
import com.zeronight.templet.module.login.LoginActivity;

/**
 * Created by Administrator on 2018/3/27.
 */

public class OtherLogin extends RelativeLayout {

    private ImageView iv_qq;
    private ImageView iv_wx;
    private ImageView iv_wb;

    public OtherLogin(Context context) {
        this(context, null);
    }

    public OtherLogin(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OtherLogin(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.weight_otherlogin, this, true);

        iv_qq = (ImageView) findViewById(R.id.iv_qq);
        iv_wx = (ImageView) findViewById(R.id.iv_wx);
        iv_wb = (ImageView) findViewById(R.id.iv_wb);

        iv_qq.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onOtherLoginClickListener != null) {
                    onOtherLoginClickListener.onQQClick();
                }
            }
        });

        iv_wx.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onOtherLoginClickListener != null) {
                    onOtherLoginClickListener.onWxClick();
                }
            }
        });

        iv_wb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onOtherLoginClickListener != null) {
                    onOtherLoginClickListener.onWbClick();
                }
            }
        });

    }

    private OnOtherLoginClickListener onOtherLoginClickListener;

    public interface OnOtherLoginClickListener {
        void onQQClick();
        void onWxClick();
        void onWbClick();
    }

    public void setOnProtocolClickListener(OnOtherLoginClickListener onOtherLoginClickListener) {
        this.onOtherLoginClickListener = onOtherLoginClickListener;
    }


}
