package com.zeronight.templet.common.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;


/**
 * 主页bottombar
 * 1.使用时直接修改对应的图标 修改主题颜色
 * 2.xml内容 不需要的bar注释掉 修改对应的图标
 * <p>
 * Created by Administrator on 2016/7/28.
 */
public class BottomBar extends RelativeLayout {

    private LinearLayout bottombar_ll_first;
    private ImageView bottombar_iv_first;
    private TextView bottombar_tv_first;
    private LinearLayout bottombar_ll_second;
    private ImageView bottombar_iv_second;
    private TextView bottombar_tv_second;
    private LinearLayout bottombar_ll_third;
    private ImageView bottombar_iv_third;
    private TextView bottombar_tv_third;
    private LinearLayout bottombar_ll_fourth;
    private ImageView bottombar_iv_fourth;
    private TextView bottombar_tv_fourth;
    private LinearLayout bottombar_ll_fifth;
    private ImageView bottombar_iv_fifth;
    private TextView bottombar_tv_fifth;
    private LinearLayout ll_bottom_icon;
    private ImageView iv_middle;
    private ImageView iv_translate;
    //
    public static final int ICON_FIRST = 0;
    public static final int ICON_SECOND = 1;
    public static final int ICON_THIRD = 2;
    public static final int ICON_FOUR = 3;
    public static final int ICON_FIVE = 4;

    //文字切换颜色
    private int colorDisplay = Color.parseColor("#4bb4db");
    private int color = Color.GRAY;

    OnBottomButtonClickListener onBottomButtonClickListener;

    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.weiget_bottombar, this, true);
        iniView();
        iniClick();

    }

    /**
     * 初始化所有控件
     */
    private void iniView() {

        bottombar_ll_first = (LinearLayout) findViewById(R.id.bottombar_ll_first);
        bottombar_iv_first = (ImageView) findViewById(R.id.bottombar_iv_first);
        bottombar_tv_first = (TextView) findViewById(R.id.bottombar_tv_first);
        bottombar_ll_second = (LinearLayout) findViewById(R.id.bottombar_ll_second);
        bottombar_iv_second = (ImageView) findViewById(R.id.bottombar_iv_second);
        bottombar_tv_second = (TextView) findViewById(R.id.bottombar_tv_second);
        bottombar_ll_third = (LinearLayout) findViewById(R.id.bottombar_ll_third);
        bottombar_iv_third = (ImageView) findViewById(R.id.bottombar_iv_third);
        bottombar_tv_third = (TextView) findViewById(R.id.bottombar_tv_third);
        bottombar_ll_fourth = (LinearLayout) findViewById(R.id.bottombar_ll_fourth);
        bottombar_iv_fourth = (ImageView) findViewById(R.id.bottombar_iv_fourth);
        bottombar_tv_fourth = (TextView) findViewById(R.id.bottombar_tv_fourth);
        bottombar_ll_fifth = (LinearLayout) findViewById(R.id.bottombar_ll_fifth);
        bottombar_iv_fifth = (ImageView) findViewById(R.id.bottombar_iv_fifth);
        bottombar_tv_fifth = (TextView) findViewById(R.id.bottombar_tv_fifth);
        ll_bottom_icon = (LinearLayout) findViewById(R.id.bottom_icon);
        iv_middle = (ImageView) findViewById(R.id.iv_middle);
        iv_translate = (ImageView) findViewById(R.id.iv_translate);

    }


    /**
     * 按钮的点击事件
     */
    private void iniClick() {


        bottombar_ll_first.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBottomButtonClickListener.firstClick();
            }
        });

        bottombar_ll_second.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBottomButtonClickListener.secondClick();
            }
        });

        bottombar_ll_third.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBottomButtonClickListener.thirdClick();
            }
        });

        bottombar_ll_fourth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBottomButtonClickListener.fourClick();
            }
        });

        bottombar_ll_fifth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBottomButtonClickListener.fifthClick();
            }
        });

        iv_middle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomBarIcon(ICON_THIRD);
                clickButton(iv_middle);
                onBottomButtonClickListener.thirdClick();
            }
        });

    }

    public void showFirstButton(){
        showBottomBarIcon(ICON_FIRST);
        clickButton(bottombar_ll_first);
    }

    public void showSecondButton(){
        showBottomBarIcon(ICON_SECOND);
        clickButton(bottombar_ll_second);
    }

    public void showThirdButton(){
        showBottomBarIcon(ICON_THIRD);
        clickButton(bottombar_ll_third);
    }

    public void showFourButton(){
        showBottomBarIcon(ICON_FOUR);
        clickButton(bottombar_ll_fourth);
    }

    public void showFiveButton(){
        showBottomBarIcon(ICON_FIVE);
        clickButton(bottombar_ll_fifth);
    }


    private void clickButton(View view){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(200);
        animSet.playTogether(anim1, anim2);
        animSet.start();
    }

    public void setFirstButton(int iniPage) {
        switch (iniPage) {
            case 0:
                showFirst();
                break;
            case 1:
                showSecond();
                break;
            case 2:
                showThird();
                break;
            case 3:
                showFourth();
                break;
            case 4:
                showFifth();
                break;
        }
    }

    public int getIconBottomHeight(){
        return iv_translate.getMeasuredHeight();
    }

    public void showBottomBarIcon(int icon){
        if (icon == ICON_FIRST) {
            showFirst();
        }else if (icon == ICON_SECOND) {
            showSecond();
        }else if (icon == ICON_THIRD) {
            showThird();
        }else if (icon == ICON_FOUR) {
            showFourth();
        }else if (icon == ICON_FIVE) {
            showFifth();
        }

    }

    private void showFirst() {
        setFirstColorSelected();
        setSecondColorNormal();
        setThirdColorNormal();
        setFourthColorNormal();
        setFifthColorNormal();
    }

    private void showSecond() {
        setFirstColorNormal();
        setSecondColorSelected();
        setThirdColorNormal();
        setFourthColorNormal();
        setFifthColorNormal();
    }

    private void showThird() {
        setFirstColorNormal();
        setSecondColorNormal();
        setThirdColorSelected();
        setFourthColorNormal();
        setFifthColorNormal();
    }

    private void showFourth() {
        setFirstColorNormal();
        setSecondColorNormal();
        setThirdColorNormal();
        setFourthColorSelected();
        setFifthColorNormal();
    }

    private void showFifth() {
        setFirstColorNormal();
        setSecondColorNormal();
        setThirdColorNormal();
        setFourthColorNormal();
        setFifthColorSelected();
    }

    private void setFirstColorSelected() {
//        bottombar_iv_first.setImageDrawable(getResources().getDrawable(R.drawable.));
        bottombar_tv_first.setTextColor(colorDisplay);
    }

    private void setFirstColorNormal() {
//        bottombar_iv_first.setImageDrawable(getResources().getDrawable(R.drawable.));
        bottombar_tv_first.setTextColor(color);
    }

    private void setSecondColorSelected() {
//        bottombar_iv_second.setImageDrawable(getResources().getDrawable(R.drawable.));
        bottombar_tv_second.setTextColor(colorDisplay);
    }

    private void setSecondColorNormal() {
//        bottombar_iv_second.setImageDrawable(getResources().getDrawable(R.drawable.));
        bottombar_tv_second.setTextColor(color);
    }

    private void setThirdColorSelected() {
//        bottombar_iv_third.setImageDrawable(getResources().getDrawable(R.drawable.));
        bottombar_tv_third.setTextColor(colorDisplay);
    }

    private void setThirdColorNormal() {
//        bottombar_iv_third.setImageDrawable(getResources().getDrawable(R.drawable.));
        bottombar_tv_third.setTextColor(color);
    }

    private void setFourthColorSelected() {
//        bottombar_iv_fourth.setImageDrawable(getResources().getDrawable(R.drawable.));
        bottombar_tv_fourth.setTextColor(colorDisplay);
    }

    private void setFourthColorNormal() {
//        bottombar_iv_fourth.setImageDrawable(getResources().getDrawable(R.drawable.));
        bottombar_tv_fourth.setTextColor(color);
    }

    private void setFifthColorSelected() {
//        bottombar_iv_fifth.setImageDrawable(getResources().getDrawable(R.drawable.));
        bottombar_tv_fifth.setTextColor(colorDisplay);
    }

    //mine白色
    private void setFifthColorNormal() {
//        bottombar_iv_fifth.setImageDrawable(getResources().getDrawable(R.drawable.));
        bottombar_tv_fifth.setTextColor(color);
    }

    /**
     * 按钮点击接口
     */
    public interface OnBottomButtonClickListener {
        void firstClick();
        void secondClick();
        void thirdClick();
        void fourClick();
        void fifthClick();
    }

    public void setOnBottomButtonClickListener(OnBottomButtonClickListener onBottomButtonClickListener) {
        this.onBottomButtonClickListener = onBottomButtonClickListener;
    }


}