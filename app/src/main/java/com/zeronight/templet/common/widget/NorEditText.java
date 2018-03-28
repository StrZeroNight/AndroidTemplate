package com.zeronight.templet.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zeronight.templet.R;
import com.zeronight.templet.common.utils.XStringUtils;


/**
 * 带删除的文本编辑框
 * <p>
 * Created by Administrator on 2016/6/6.
 */
public class NorEditText extends RelativeLayout {

    private TextView tv_title;
    private EditText et_content;
    private ImageView iv_del;
    //
    private final static int STRING_TYPE_PASSWORD = 1;
    private final static int STRING_TYPE_PHONE = 2;

    public NorEditText(Context context) {
        this(context, null);
    }

    public NorEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NorEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.weight_noredittext, this, true);

        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_del = (ImageView) findViewById(R.id.iv_del);
        et_content = (EditText) findViewById(R.id.et_content);

        TypedArray attr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NorEditText, defStyleAttr, 0);

        String title = attr.getString(R.styleable.NorEditText_net_title);
        tv_title.setText(title);

        String hint = attr.getString(R.styleable.NorEditText_net_hint);
        et_content.setHint(hint);

        int textType = attr.getInt(R.styleable.NorEditText_net_texttype , 0);
        Logger.i("textType:" + textType);
        if (textType == STRING_TYPE_PASSWORD) {
            et_content.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        } else if (textType == STRING_TYPE_PHONE) {
            et_content.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        } else {
            et_content.setInputType(InputType.TYPE_CLASS_TEXT);
        }

        float textSize = attr.getDimension(R.styleable.NorEditText_net_title_size, 0);
        if (textSize != 0) {
            tv_title.setTextSize(textSize);
        }

        int titleColor = attr.getColor(R.styleable.NorEditText_net_title_color, 0);
        if (titleColor != 0) {
            tv_title.setTextColor(titleColor);
        }

        float hintSize = attr.getDimension(R.styleable.NorEditText_net_hint_size, 0);
        if (textSize != 0) {
            et_content.setTextSize(hintSize);
        }

        int hintColor = attr.getColor(R.styleable.NorEditText_net_hint_color, 0);
        if (hintColor != 0) {
            et_content.setTextColor(hintColor);
        }

        attr.recycle();

        iv_del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                et_content.setText("");
                iv_del.setVisibility(INVISIBLE);
            }
        });

        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!XStringUtils.isEmpty(s.toString())) {
                    iv_del.setVisibility(VISIBLE);
                }else{
                    iv_del.setVisibility(GONE);
                }
            }
        });

    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void setDelImage(int resourceId) {
        iv_del.setImageResource(resourceId);
    }

    public String getContent() {
        if (et_content.getText().toString() == null) {
            return "";
        }
        return et_content.getText().toString();
    }

    public void setEdittext(String str) {
        et_content.setText(str);
        et_content.setSelection(str.length());
    }

    public void moveCursorToEnd(String content) {
        et_content.setSelection(content.length());
    }

    public void setUnEdit() {
        et_content.setFocusable(false);
        iv_del.setVisibility(GONE);
    }

}
