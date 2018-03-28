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
import android.widget.Switch;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.utils.XStringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 带删除的文本编辑框
 * <p>
 * Created by Administrator on 2016/6/6.
 */
public class AddressText extends RelativeLayout {

    private TextView tv_title;
    private RelativeLayout rl_edit;
    private EditText et_content;
    private ImageView iv_del;
    private TextView tv_city;
    private Switch switch_address;
    //
    private final static int STRING_TYPE_PASSWORD = 1;
    private final static int STRING_TYPE_PHONE = 2;

    public AddressText(Context context) {
        this(context, null);
    }

    public AddressText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddressText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.weight_addresstext, this, true);

        tv_title = (TextView) findViewById(R.id.tv_title);
        rl_edit = (RelativeLayout) findViewById(R.id.rl_edit);
        et_content = (EditText) findViewById(R.id.et_content);
        iv_del = (ImageView) findViewById(R.id.iv_del);
        tv_city = (TextView) findViewById(R.id.tv_city);
        switch_address = (Switch) findViewById(R.id.switch_address);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AddressText, defStyleAttr, 0);
        String title = a.getString(R.styleable.AddressText_at_title);
        String hint = a.getString(R.styleable.AddressText_at_hint);
        int textType = a.getInt(R.styleable.AddressText_at_textType, 0);
        int viewType = a.getInt(R.styleable.AddressText_at_viewType, 0);

        //编辑框类型
        switch (textType) {
            case STRING_TYPE_PASSWORD:
                et_content.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                break;
            case STRING_TYPE_PHONE:
                et_content.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                break;
        }

        //显示类型
        List<View> viewlist = new ArrayList<>();
        viewlist.add(rl_edit);
        viewlist.add(tv_city);
        viewlist.add(switch_address);
        showView(viewlist , viewType);

        //hint
        et_content.setHint(hint);
        tv_city.setText(hint);

        //title
        tv_title.setText(title);

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

    private void showView(List<View> viewlist , int witch) {
        for (int i = 0; i < viewlist.size(); i++) {
            if (i == witch) {
                viewlist.get(i).setVisibility(VISIBLE);
            }else{
                viewlist.get(i).setVisibility(GONE);
            }
        }
    }


    public String getEditText(){
        if (et_content.getText().toString() == null) {
            return "";
        }
        return et_content.getText().toString();
    }

    public String getTextView(){
        if (tv_city.getText().toString() == null) {
            return "";
        }
        return tv_city.getText().toString();
    }

    public void setTextView(String address){
        tv_city.setText(address);
    }

    public boolean getSwitchStatus(){
        return switch_address.isChecked();
    }


}
