package com.zeronight.templet.module.mine;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseFragment;
import com.zeronight.templet.common.widget.ArrorText;
import com.zeronight.templet.common.widget.SuperTextView;
import com.zeronight.templet.module.bankcard.WithdrawalsActivity;
import com.zeronight.templet.module.order.OrderListActivity;
import com.zeronight.templet.module.order.OrderListOneActivity;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;


/**
 * Created by Administrator on 2017/10/9.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private ImageView iv_setting;
    private ImageView iv_info;
    private ImageView iv_header;
    private TextView tv_phone;
    private SuperTextView stv_level;
    private RelativeLayout rl_tip;
    private LinearLayout ll_reward;
    private LinearLayout ll_withdrawals;
    private ImageView iv_banner_1;
    private TextView tv_privilege_1;
    private TextView tv_privilege_2;
    private TextView tv_privilege_3;
    private TextView tv_privilege_4;
    private ImageView iv_banner_2;
    private TextView tv_order;
    private TextView tv_order_1;
    private TextView tv_order_2;
    private TextView tv_order_3;
    private TextView tv_order_4;
    private ArrorText at_userinfo;
    private ArrorText at_changephone;
    private ArrorText at_myorder;
    private ArrorText at_reward;
    private ArrorText at_share;
    private ArrorText at_privilege;
    private ArrorText at_tweetcenter;
    private ArrorText at_about;
    private ArrorText at_merchantentry;
    private ArrorText at_newuser;
    private ImageView iv_banner_3;
    private SuperTextView stv_confirm;
    private NestedScrollView nsv;
    private View view;
    private Badge badge1;
    private Badge badge2;
    private Badge badge3;
    private Badge badge4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_setting:
                break;
            case R.id.iv_info:

                break;
            case R.id.iv_banner_1:
                break;
            case R.id.tv_privilege_1:
                break;
            case R.id.tv_privilege_2:
                break;
            case R.id.tv_privilege_3:
                break;
            case R.id.tv_privilege_4:
                break;
            case R.id.iv_banner_2:
                break;
            case R.id.tv_order:
                OrderListActivity.start(getActivity());
                break;
            case R.id.tv_order_1:
                OrderListOneActivity.start(getActivity() , "待付款");
                break;
            case R.id.tv_order_2:
                OrderListOneActivity.start(getActivity() , "待发货");
                break;
            case R.id.tv_order_3:
                OrderListOneActivity.start(getActivity() , "待收货");
                break;
            case R.id.tv_order_4:
                OrderListOneActivity.start(getActivity() , "退款/售后");
                break;
            case R.id.at_userinfo:
//                UserActivity.start(getActivity());
                break;
            case R.id.at_changephone:
//                ChangePhoneActivity.start(getActivity());
                break;
            case R.id.at_myorder:
                OrderListActivity.start(getActivity());
                break;
            case R.id.at_reward:
                break;
            case R.id.at_share:
                break;
            case R.id.at_privilege:
                break;
            case R.id.at_tweetcenter:
                break;
            case R.id.at_about:
                break;
            case R.id.at_merchantentry:
//                BusinessActivity.start(getActivity());
                break;
            case R.id.at_newuser:
                break;
            case R.id.iv_banner_3:
                break;
            case R.id.stv_confirm:
                break;
            case R.id.ll_withdrawals:
                WithdrawalsActivity.start(getActivity());
                break;
            case R.id.rl_tip:
                break;
        }
    }

    private void initView(View view) {
        nsv = view.findViewById(R.id.nsv);
        iv_setting = (ImageView) view.findViewById(R.id.iv_setting);
        iv_setting.setOnClickListener(this);
        iv_info = (ImageView) view.findViewById(R.id.iv_info);
        iv_info.setOnClickListener(this);
        iv_header = (ImageView) view.findViewById(R.id.iv_header);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        stv_level = (SuperTextView) view.findViewById(R.id.stv_level);
        rl_tip = (RelativeLayout) view.findViewById(R.id.rl_tip);
        ObjectAnimator animator = ObjectAnimator.ofFloat(rl_tip, "rotation", 0f, 5f, 0f, -5f, 0f);
        animator.setDuration(500);
        animator.setStartDelay(2000);
        animator.start();

        ll_reward = (LinearLayout) view.findViewById(R.id.ll_reward);
        ll_withdrawals = (LinearLayout) view.findViewById(R.id.ll_withdrawals);
        iv_banner_1 = (ImageView) view.findViewById(R.id.iv_banner_1);
        iv_banner_1.setOnClickListener(this);
        tv_privilege_1 = (TextView) view.findViewById(R.id.tv_privilege_1);
        tv_privilege_1.setOnClickListener(this);
        tv_privilege_2 = (TextView) view.findViewById(R.id.tv_privilege_2);
        tv_privilege_2.setOnClickListener(this);
        tv_privilege_3 = (TextView) view.findViewById(R.id.tv_privilege_3);
        tv_privilege_3.setOnClickListener(this);
        tv_privilege_4 = (TextView) view.findViewById(R.id.tv_privilege_4);
        tv_privilege_4.setOnClickListener(this);
        iv_banner_2 = (ImageView) view.findViewById(R.id.iv_banner_2);
        iv_banner_2.setOnClickListener(this);
        tv_order = (TextView) view.findViewById(R.id.tv_order);
        tv_order.setOnClickListener(this);
        tv_order_1 = (TextView) view.findViewById(R.id.tv_order_1);
        tv_order_1.setOnClickListener(this);
        tv_order_2 = (TextView) view.findViewById(R.id.tv_order_2);
        tv_order_2.setOnClickListener(this);
        tv_order_3 = (TextView) view.findViewById(R.id.tv_order_3);
        tv_order_3.setOnClickListener(this);
        tv_order_4 = (TextView) view.findViewById(R.id.tv_order_4);
        tv_order_4.setOnClickListener(this);
        at_userinfo = (ArrorText) view.findViewById(R.id.at_userinfo);
        at_userinfo.setOnClickListener(this);
        at_changephone = (ArrorText) view.findViewById(R.id.at_changephone);
        at_changephone.setOnClickListener(this);
        at_myorder = (ArrorText) view.findViewById(R.id.at_myorder);
        at_myorder.setOnClickListener(this);
        at_reward = (ArrorText) view.findViewById(R.id.at_reward);
        at_reward.setOnClickListener(this);
        at_share = (ArrorText) view.findViewById(R.id.at_share);
        at_share.setOnClickListener(this);
        at_privilege = (ArrorText) view.findViewById(R.id.at_privilege);
        at_privilege.setOnClickListener(this);
        at_tweetcenter = (ArrorText) view.findViewById(R.id.at_tweetcenter);
        at_tweetcenter.setOnClickListener(this);
        at_about = (ArrorText) view.findViewById(R.id.at_about);
        at_about.setOnClickListener(this);
        at_merchantentry = (ArrorText) view.findViewById(R.id.at_merchantentry);
        at_newuser = (ArrorText) view.findViewById(R.id.at_newuser);
        at_newuser.setOnClickListener(this);
        iv_banner_3 = (ImageView) view.findViewById(R.id.iv_banner_3);
        iv_banner_3.setOnClickListener(this);
        stv_confirm = (SuperTextView) view.findViewById(R.id.stv_confirm);
        stv_confirm.setOnClickListener(this);
        at_merchantentry.setOnClickListener(this);
        ll_withdrawals.setOnClickListener(this);
        rl_tip.setOnClickListener(this);
        nsv = (NestedScrollView) view.findViewById(R.id.nsv);

        Badge badgeInfo = createBadge(iv_info);
        badgeInfo.setBadgeNumber(-1);
        badge1 = createBadge(tv_order_1);
        badge1.setBadgeNumber(2);
        badge2 = createBadge(tv_order_2);
        badge2.setBadgeNumber(99);
        badge3 = createBadge(tv_order_3);
        badge4 = createBadge(tv_order_4);
    }

    private Badge createBadge(TextView tv){
        Badge badge = new QBadgeView(getActivity())
                .bindTarget(tv)
                .setBadgeNumber(0)
                .setBadgeBackgroundColor(getResources().getColor(R.color.colorPrimary))
                .setGravityOffset(10 , 3 , true)
                .setShowShadow(true);
        return badge;
    }

    private Badge createBadge(ImageView iv){
        Badge badge = new QBadgeView(getActivity())
                .bindTarget(iv)
                .setBadgeNumber(0)
                .setBadgeBackgroundColor(getResources().getColor(R.color.colorPrimary))
                .setGravityOffset(10 , 7 , true)
                .setShowShadow(true);
        return badge;
    }

}
