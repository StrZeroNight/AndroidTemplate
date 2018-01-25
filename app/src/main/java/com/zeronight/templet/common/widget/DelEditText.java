package com.zeronight.templet.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;


/**
 * 带删除的文本编辑框
 * <p>
 * Created by Administrator on 2016/6/6.
 */
public class DelEditText extends RelativeLayout {

    private TextView tv_title;
    private EditText et_content;
    private ImageView iv_del;
    //
    private final static int STRING_TYPE_PASSWORD = 1;
    private final static int STRING_TYPE_STRING = 2;
    private final static int STRING_TYPE_PHONE = 3;

    public DelEditText(Context context) {
        this(context, null);
    }

    public DelEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DelEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.weight_deledittext, this, true);

        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_del = (ImageView) findViewById(R.id.iv_del);
        et_content = (EditText) findViewById(R.id.et_content);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DelEditText, defStyleAttr, 0);
        int indexCount = a.getIndexCount();

        for (int i = 0; i < indexCount; i++) {
            int index = a.getIndex(i);
            if (index == R.styleable.DelEditText_title) {
                String string = a.getString(index);
                if (!string.isEmpty()) {
                    tv_title.setText(string);
                }

            } else if (index == R.styleable.DelEditText_delimage) {
                int resourceId = a.getResourceId(index, 0);
                iv_del.setImageResource(resourceId);

            }else if (index == R.styleable.DelEditText_textType) {
                int stringType = a.getInt(index, 0);
                switch (stringType) {
                    case STRING_TYPE_PASSWORD:
                        et_content.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                    case STRING_TYPE_PHONE:
                        et_content.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                    case STRING_TYPE_STRING:
                        et_content.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                }
            }else if (index == R.styleable.DelEditText_titleTextColor) {

                float dimension = a.getDimension(index, 0.0f);
                tv_title.setTextSize(dimension);

            }else if (index == R.styleable.DelEditText_titleTextSize) {

                int color = a.getColor(index, 0);
                tv_title.setTextColor(color);

            }else if (index == R.styleable.DelEditText_hintTextColor) {

                int color = a.getColor(index, 0);
                et_content.setHintTextColor(color);

            }else if (index == R.styleable.DelEditText_hintTextSize) {

                float dimension = a.getDimension(index, 0.0f);
                et_content.setTextSize(dimension);

            }else if (index == R.styleable.DelEditText_leftPadding) {

            }else if (index == R.styleable.DelEditText_dhint){
                String hint = a.getString(index);
                et_content.setHint(hint);
            }else if (index == R.styleable.DelEditText_isShowTitle){
                boolean isShowTitle = a.getBoolean(index, true);
                if (isShowTitle) {
                    tv_title.setVisibility(VISIBLE);
                }else{
                    tv_title.setVisibility(GONE);
                }
            }

        }
        a.recycle();

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
                Log.i("zeroz", "beforeTextChanged: =============s" + s);
                if (!s.equals("")) {
                    iv_del.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void setTitle(String greenTitle) {
        tv_title.setText(greenTitle);
    }

    public void setDelImage(int resourceId) {
        iv_del.setImageResource(resourceId);
    }

    public String getContent(){
        if (et_content.getText().toString() == null) {
            return "";
        }
        return et_content.getText().toString();
    }

    public void setEdittext(String str){
        et_content.setText(str);
        et_content.setSelection(str.length());
    }

    public void moveCursorToEnd(String content){
        et_content.setSelection(content.length());
    }

}
