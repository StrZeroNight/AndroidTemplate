package com.zeronight.templet.module.main;


import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.module.cart.CartFragment;
import com.zeronight.templet.module.classify.OneClassifyFragment;
import com.zeronight.templet.module.mine.MineFragment;

/**
 *
 * <p/>
 * Created by Administrator on 2016/10/20.
 */
public class FiveMainPresenter extends BasePresenter {

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private OneClassifyFragment thirdFragment;
    private CartFragment cartFragment;
    private MineFragment fiveFragment;
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
            thirdFragment = new OneClassifyFragment();
            cartFragment = new CartFragment();
            fiveFragment = new MineFragment();
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
        if (cartFragment != null) {
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
                .hide(cartFragment)
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
                .hide(cartFragment)
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
                .hide(cartFragment)
                .hide(fiveFragment)
                .commit();
    }

    /**
     * 显示第四个页面
     */
    public void iniFourFragment() {
        if (!cartFragment.isAdded()) {
            rootActivity.getSupportFragmentManager().beginTransaction().add(R.id.fg_main, cartFragment).show(cartFragment).commit();
        } else {
            rootActivity.getSupportFragmentManager().beginTransaction().show(cartFragment).commit();
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
                .hide(cartFragment)
                .commit();
    }



}


