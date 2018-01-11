package com.zeronight.templet.module.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.widget.BottomBarNormal;

public class MainActivity extends BaseActivity {

    private BottomBarNormal bottombar;
    private FiveMainPresenter fiveMainPresenter;
    private int currentBar = 0;

    public static void start(Context context) {
        Intent it = new Intent(context, MainActivity.class);
        context.startActivity(it);
    }

    private void initIntent() {
        Intent intent = getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPresenter();
    }

    private void initPresenter() {
        fiveMainPresenter.iniAllFragment();
        bottombar.setFirstButton(0);
        fiveMainPresenter.iniFirstFragment();
    }

    private void initView() {
        fiveMainPresenter = new FiveMainPresenter(MainActivity.this);
        bottombar = (BottomBarNormal) findViewById(R.id.bottombar);

        bottombar.setOnBottomButtonClickListener(new BottomBarNormal.OnBottomButtonClickListener() {
            @Override
            public void firstClick() {
                if (currentBar != 1) {
                    bottombar.showFirstButton();
                    fiveMainPresenter.iniFirstFragment();
                    fiveMainPresenter.refreshFirstFragment();
                    currentBar = 1;
                }
            }

            @Override
            public void secondClick() {
                if (currentBar != 2) {
                    bottombar.showSecondButton();
                    fiveMainPresenter.iniSecondFragment();
                    fiveMainPresenter.refreshSecondFragment();
                    currentBar = 2;
                }
            }

            @Override
            public void thirdClick() {
                if (currentBar != 3) {
                    bottombar.showThirdButton();
                    fiveMainPresenter.iniThirdFragment();
                    currentBar = 3;
                }
            }

            @Override
            public void fourClick() {
                if (currentBar != 4) {
                    bottombar.showFourButton();
                    fiveMainPresenter.iniFourFragment();
                    fiveMainPresenter.refreshFourFragment();
                    currentBar = 4;
                }
            }

            @Override
            public void fifthClick() {
                if (currentBar != 5) {
                    bottombar.showFiveButton();
                    fiveMainPresenter.iniFiveFragment();
                    fiveMainPresenter.refreshFiveFragment();
                    currentBar = 5;
                }
            }
        });

    }

}
