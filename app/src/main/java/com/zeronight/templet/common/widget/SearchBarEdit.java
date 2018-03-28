package com.zeronight.templet.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * Created by Administrator on 2017/11/8.
 */

public class SearchBarEdit extends RelativeLayout {

    private EditText et_search;

    public SearchBarEdit(Context context) {
        this(context, null);
    }

    public SearchBarEdit(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchBarEdit(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.widget_searchbaredit, this, true);
        ImageView iv_left = (ImageView) findViewById(R.id.iv_left);
        ImageView iv_right = (ImageView) findViewById(R.id.iv_right);
        et_search = (EditText) findViewById(R.id.et_search);
        ImageView iv_background = (ImageView) findViewById(R.id.iv_background);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SearchBarEdit, defStyleAttr, 0);
        int leftIcon = a.getResourceId(R.styleable.SearchBarEdit_sbe_left_icon, 0);
        int rightIcon = a.getResourceId(R.styleable.SearchBarEdit_sbe_right_icon, 0);
        int background = a.getResourceId(R.styleable.SearchBarEdit_sbe_background, 0);
        String hint = a.getString(R.styleable.SearchBarEdit_sbe_hint);

        //hint
        et_search.setText(hint);

        //左icon
        if (leftIcon == 0) {
            iv_left.setVisibility(INVISIBLE);
        } else {
            iv_left.setVisibility(VISIBLE);
            iv_left.setImageResource(leftIcon);
        }

        //右icon
        if (rightIcon == 0) {
            iv_right.setVisibility(INVISIBLE);
        } else {
            iv_right.setVisibility(VISIBLE);
            iv_right.setImageResource(rightIcon);
        }

        //searchbar背景
        iv_background.setBackgroundResource(background);

        //点击事件
        iv_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onEditSearchClickListener != null) {
                    onEditSearchClickListener.OnLeftClick();
                }
            }
        });

        iv_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onEditSearchClickListener != null) {
                    onEditSearchClickListener.OnRightClick();
                }
            }
        });

        //点击软键盘搜索按钮
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_SEND) {
                    //隐藏键盘
                    ((InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(((AppCompatActivity)context).getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    if (onEditSearchClickListener != null) {
                        onEditSearchClickListener.OnSoftSearch();
                    }
                }
                return false;
            }
        });

        //监听焦点
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (onEditSearchClickListener != null) {
                    onEditSearchClickListener.OnFouceChange();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        a.recycle();

    }

    private OnEditSearchClickListener onEditSearchClickListener;
    public interface OnEditSearchClickListener {
        void OnFouceChange();
        void OnSoftSearch();
        void OnLeftClick();
        void OnRightClick();
    }

    public void setOnEditSearchClickListener(OnEditSearchClickListener onEditSearchClickListener) {
        this.onEditSearchClickListener = onEditSearchClickListener;
    }

    public String getSearchText(){
        return et_search.getText().toString();
    }

    public void setSearchText(String searchText){
        et_search.setText(searchText);
    }

}
