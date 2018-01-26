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
public class SearchBar extends RelativeLayout {

    private TextView tv_title;
    private TextView tv_content;
    private ImageView iv_icon;
    private ImageView iv_arror;
    OnSearchBarClickListener onSearchBarClickListener;
    private EditText et_search;

    public SearchBar(Context context) {
        this(context , null);
    }

    public SearchBar(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public SearchBar(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.widget_searchbar, this, true);
        et_search = (EditText)findViewById(R.id.et_search);
        ImageView iv_left = (ImageView)findViewById(R.id.iv_left);
        ImageView iv_right = (ImageView)findViewById(R.id.iv_right);
        RelativeLayout rl_root = (RelativeLayout)findViewById(R.id.rl_root);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SearchBarMain, defStyleAttr, 0);
        int leftIcon = a.getResourceId(R.styleable.SearchBarMain_left_icon, 0);
        int rightIcon = a.getResourceId(R.styleable.SearchBarMain_right_icon, 0);
        int background = a.getResourceId(R.styleable.SearchBarMain_sbackground, 0);
        String hint = a.getString(R.styleable.SearchBarMain_shint);
        //左icon
        if (leftIcon == 0) {
            iv_left.setVisibility(INVISIBLE);
        }else{
            iv_left.setVisibility(VISIBLE);
            iv_left.setImageResource(leftIcon);
        }
        //右icon
        if (rightIcon == 0) {
            iv_right.setVisibility(INVISIBLE);
        }else{
            iv_right.setVisibility(VISIBLE);
            iv_right.setImageResource(rightIcon);
        }
        //searchbar背景
        rl_root.setBackgroundResource(background);
        //hint
        et_search.setHint(hint);
        //点击事件
        iv_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSearchBarClickListener != null) {
                    onSearchBarClickListener.OnLeftClick();
                }
            }
        });
        iv_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSearchBarClickListener != null) {
                    onSearchBarClickListener.OnRightClick();
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
                    if (onSearchBarClickListener != null) {
                        onSearchBarClickListener.OnSoftSearch();
                    }
                }
                return false;
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (onSearchBarClickListener != null) {
                    onSearchBarClickListener.OnFouceChange();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        a.recycle();

    }

    public interface OnSearchBarClickListener{
        void OnLeftClick();
        void OnRightClick();
        void OnSoftSearch();
        void OnFouceChange();
    }

    public void setOnSearchBarClickListener(OnSearchBarClickListener onSearchBarClickListener){
        this.onSearchBarClickListener = onSearchBarClickListener;
    }

    public String getSearchText(){
        return et_search.getText().toString();
    }

    public void setSearchText(String searchStr){
        et_search.setText(searchStr);
        et_search.setSelection(searchStr.length());
    }

}
