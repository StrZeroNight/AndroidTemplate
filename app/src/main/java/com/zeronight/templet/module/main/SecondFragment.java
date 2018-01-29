package com.zeronight.templet.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseFragment;
import com.zeronight.templet.common.widget.TitleBar;

/**
 * 用来写购物车的页面
 * Created by Administrator on 2017/10/9.
 */

public class SecondFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private View fragment_second_view;
    private TitleBar titlebar;
    private XRecyclerView xrv;
    private TextView tv_all;
    private RelativeLayout rl_bottom;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        titlebar = (TitleBar) view.findViewById(R.id.titlebar);
        xrv = (XRecyclerView) view.findViewById(R.id.xrv);
        tv_all = (TextView) view.findViewById(R.id.tv_all);
        tv_all.setOnClickListener(this);
        rl_bottom = (RelativeLayout) view.findViewById(R.id.rl_bottom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:

                break;
        }
    }

}
