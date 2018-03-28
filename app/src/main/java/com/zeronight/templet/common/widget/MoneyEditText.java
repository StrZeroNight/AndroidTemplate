package com.zeronight.templet.common.widget;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.zeronight.templet.R;
import com.zeronight.templet.common.utils.XStringUtils;


/**
 * 带删除的文本编辑框
 * <p>
 * Created by Administrator on 2016/6/6.
 */
public class MoneyEditText extends RelativeLayout {

    private EditText et_content;

    public MoneyEditText(Context context) {
        this(context, null);
    }

    public MoneyEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoneyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.weight_moneyedittext, this, true);

        et_content = (EditText) findViewById(R.id.et_content);

        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String money = s.toString();
                if (money.startsWith("00")) {
                    et_content.setText("0");
                    et_content.setSelection(1);
                }
                if (XStringUtils.isEmpty(money)) {
                    return;
                }
            }
        });

        et_content.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(".") && dest.toString().length() == 0) {
                    return "0.";
                }
                if (dest.toString().contains(".")) {
                    int index = dest.toString().indexOf(".");
                    int mlength = dest.toString().substring(index).length();
                    if (mlength == 3) {
                        return ""; //return 返回的是当前的信息
                    }
                }
                return null;
            }
        }});

    }

    public String getMoney(){
        return et_content.getText().toString();
    }

    public void setMoney(String money){
        et_content.setText(money);
    }

}
