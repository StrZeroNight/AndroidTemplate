package com.zeronight.templet.module.main;


import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;

/**
 *
 * <p/>
 * Created by Administrator on 2016/10/20.
 */
public class FiveMainPresenter extends BasePresenter {

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FourFragment fourFragment;
    private FiveFragment fiveFragment;
    private BaseActivity rootActivity;

    public FiveMainPresenter(BaseActivity rootActivity) {
        this.rootActivity = rootActivity;
    }

    public void unRegister() {
        rootActivity = null;
    }

    /**
     * 初始化所有fragment
     */
    public void iniAllFragment() {
        if (firstFragment == null) {
            firstFragment = new FirstFragment();
            secondFragment = new SecondFragment();
            thirdFragment = new ThirdFragment();
            fourFragment = new FourFragment();
            fiveFragment = new FiveFragment();
        }
    }

    public void refreshFirstFragment(){
        if(firstFragment != null){
        }
    }

    public void refreshSecondFragment(){
        if(secondFragment != null){
        }
    }

    public void refreshThirdFragment(){
        if (thirdFragment != null) {
        }
    }

    public void refreshFourFragment(){
        if (fourFragment != null) {
        }
    }

    public void refreshFiveFragment(){
        if (fiveFragment != null) {
        }
    }

    /**
     * 显示第一个页面
     */
    public void iniFirstFragment() {
        if (!firstFragment.isAdded()) {
            rootActivity.getSupportFragmentManager().beginTransaction().add(R.id.fg_main, firstFragment).show(firstFragment).commit();
        } else {
            rootActivity.getSupportFragmentManager().beginTransaction().show(firstFragment).commitAllowingStateLoss();
        }
        rootActivity.getSupportFragmentManager().beginTransaction()
                .hide(secondFragment)
                .hide(fourFragment)
                .hide(thirdFragment)
                .hide(fiveFragment)
                .commitAllowingStateLoss();
    }


    /**
     * 显示第二个页面
     */
    public void iniSecondFragment() {

        if (!secondFragment.isAdded()) {
            rootActivity.getSupportFragmentManager().beginTransaction().add(R.id.fg_main, secondFragment).show(secondFragment).commit();
        } else {
            rootActivity.getSupportFragmentManager().beginTransaction().show(secondFragment).commit();
        }
        rootActivity.getSupportFragmentManager().beginTransaction()
                .hide(firstFragment)
                .hide(thirdFragment)
                .hide(fourFragment)
                .hide(fiveFragment)
                .commit();
    }

    /**
     * 显示第三个页面
     */
    public void iniThirdFragment() {

        if (!thirdFragment.isAdded()) {
            rootActivity.getSupportFragmentManager().beginTransaction().add(R.id.fg_main, thirdFragment).show(thirdFragment).commit();
        } else {
            rootActivity.getSupportFragmentManager().beginTransaction().show(thirdFragment).commit();
        }
        rootActivity.getSupportFragmentManager().beginTransaction()
                .hide(firstFragment)
                .hide(secondFragment)
                .hide(fourFragment)
                .hide(fiveFragment)
                .commit();
    }

    /**
     * 显示第四个页面
     */
    public void iniFourFragment() {
        if (!fourFragment.isAdded()) {
            rootActivity.getSupportFragmentManager().beginTransaction().add(R.id.fg_main, fourFragment).show(fourFragment).commit();
        } else {
            rootActivity.getSupportFragmentManager().beginTransaction().show(fourFragment).commit();
        }
        rootActivity.getSupportFragmentManager().beginTransaction()
                .hide(firstFragment)
                .hide(secondFragment)
                .hide(thirdFragment)
                .hide(fiveFragment)
                .commit();
    }

    /**
     * 显示第五个页面
     */
    public void iniFiveFragment() {
        if (!fiveFragment.isAdded()) {
            rootActivity.getSupportFragmentManager().beginTransaction().add(R.id.fg_main, fiveFragment).show(fiveFragment).commit();
        } else {
            rootActivity.getSupportFragmentManager().beginTransaction().show(fiveFragment).commit();
        }
        rootActivity.getSupportFragmentManager().beginTransaction()
                .hide(firstFragment)
                .hide(secondFragment)
                .hide(thirdFragment)
                .hide(fourFragment)
                .commit();
    }



}


