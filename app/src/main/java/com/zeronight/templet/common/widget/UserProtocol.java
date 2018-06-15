package com.zeronight.templet.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;

/**
 * Created by Administrator on 2018/3/27.
 */

public class UserProtocol extends RelativeLayout {

    private TextView tv_ok;
    private TextView tv_agrament;
    private boolean isAgree = false;

    public UserProtocol(Context context) {
        this(context, null);
    }

    public UserProtocol(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserProtocol(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.weight_userprotocol, this, true);

        tv_ok = (TextView) findViewById(R.id.tv_ok);
        tv_agrament = (TextView) findViewById(R.id.tv_agrament);
//        TypedArray attr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NorEditText, defStyleAttr, 0);

        tv_ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAgree) {
//                    tv_ok.setCompoundDrawablesWithIntrinsicBounds(R.drawable. , 0 ,0 ,0);
                }else{
//                    tv_ok.setCompoundDrawablesWithIntrinsicBounds(R.drawable. , 0 ,0 ,0);
                }
                isAgree = !isAgree;
                if (onProtocolClickListener != null) {
                    onProtocolClickListener.onOkClick();
                }
            }
        });

        tv_agrament.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onProtocolClickListener != null) {
                    onProtocolClickListener.onProtocolClick();
                }
            }
        });

    }

    private OnProtocolClickListener onProtocolClickListener;

    public interface OnProtocolClickListener {
        void onOkClick();

        void onProtocolClick();
    }

    public void setOnProtocolClickListener(OnProtocolClickListener onProtocolClickListener) {
        this.onProtocolClickListener = onProtocolClickListener;
    }

    public boolean getIsAgree(){
        return isAgree;
    }

}
