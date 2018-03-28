package com.zeronight.templet.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.utils.XStringUtils;


/**
 * 一般这样的自定义控件有很多种
 * 在代码里面登录注册的用的比较多，所以最基本的样式用登录注册的，其他的少量其他地方需要的就直接该参数处理
 * Created by Administrator on 2018/1/3.
 */
public class VerEditTextString extends RelativeLayout {

    private ImageView iv_icon;
    private EditText et_content;
    private TextView tv_getvercode;

    public VerEditTextString(Context context) {
        this(context, null);
    }

    public VerEditTextString(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("ResourceAsColor")
    public VerEditTextString(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.widget_ver_edittext, this, true);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        et_content = (EditText) findViewById(R.id.et_content);
        tv_getvercode = (TextView) findViewById(R.id.tv_getvercode);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LoginEditText, defStyleAttr, 0);
        String hint = a.getString(R.styleable.LoginEditText_let_hint);
        int hintColor = a.getResourceId(R.styleable.LoginEditText_let_hintcolor, defStyleAttr);
        int contentType = a.getInt(R.styleable.LoginEditText_let_contenttype, defStyleAttr);
        int icon = a.getResourceId(R.styleable.LoginEditText_let_icon, defStyleAttr);

        if (!XStringUtils.isEmpty(hint)) {
            et_content.setHint(hint);
        }
        if (hintColor != 0) { // TODO: 2018/1/3 no use
            et_content.setHintTextColor(hintColor);
        }
        if (icon != 0) {
            iv_icon.setImageResource(icon);
        }
        if (contentType == 1) {
            et_content.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        } else if (contentType == 2) {
            et_content.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        } else {
            et_content.setInputType(InputType.TYPE_CLASS_TEXT);
        }

        a.recycle();

    }

    public String getContent() {
        return et_content.getText().toString();
    }

    public void getVerCode(final String phone , final AppCompatActivity appCompatActivity) {
        tv_getvercode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



}
