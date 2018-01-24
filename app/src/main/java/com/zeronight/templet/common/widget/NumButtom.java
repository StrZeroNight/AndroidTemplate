package com.zeronight.templet.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;

/**
 * 左右加减的按钮
 * <p/>
 * Created by Administrator on 2016/6/12.
 */
public class NumButtom extends RelativeLayout implements View.OnClickListener {

    private int num;
    private TextView tv_num;
    private ImageView iv_reduce;
    private ImageView iv_add;
    private onNumChangeListener onNumChangeListener;

    public NumButtom(Context context) {
        this(context, null);
    }

    public NumButtom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumButtom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.weight_numbutton, this, true);
        //加号操作
        iv_add = (ImageView) findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);
        iv_add.setOnKeyListener(continuityAddKeyListener);//为imagebutton设置按键响应事件

        //减号操作
        iv_reduce = (ImageView) findViewById(R.id.iv_reduce);
        iv_reduce.setOnClickListener(this);
        iv_add.setOnKeyListener(continuityMinusKeyListener);//为imagebutton设置按键响应事件
        //获取数据
        tv_num = (TextView) findViewById(R.id.custom_numbutton_tv_num);
        num = Integer.parseInt(tv_num.getText().toString());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_add) {
            add();
            if (onNumChangeListener != null) {
                onNumChangeListener.add(tv_num.getText() + "");
            }

        } else if (id == R.id.iv_reduce) {
            minus();
            if (onNumChangeListener != null) {
                onNumChangeListener.mine(tv_num.getText() + "");
            }

        }
    }

    //加号的操作
    public void add() {
        if (num > -1) {
            num++;
            tv_num.setText(num + "");
        }
    }

    //减号操作
    public void minus() {
        if (num > 1) {
            num--;
            tv_num.setText(num + "");
        }
    }


    //按住+号后
    private OnKeyListener continuityAddKeyListener = new OnKeyListener() {
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                while (true) {
                    add();
                }
            }
            return false;
        }
    };

    //按住+号后
    private OnKeyListener continuityMinusKeyListener = new OnKeyListener() {
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    minus();
                    break;
            }
            return false;
        }
    };

    //获取数字
    public int getNum() {
        return Integer.parseInt(tv_num.getText().toString());
    }

    //填充文字
    public void setNum(String num) {
        this.num = Integer.parseInt(num);
        tv_num.setText(num);
    }

    //监听加号和减号
    public interface onNumChangeListener {
        void add(String num);

        void mine(String num);
    }

    //实现监听
    public void setNumChangeListener(onNumChangeListener numChangeListener) {
        this.onNumChangeListener = numChangeListener;
    }

}
