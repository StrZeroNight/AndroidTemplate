package com.zeronight.templet.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseFragment;

/**
 * Created by Administrator on 2017/10/9.
 */

public class SecondFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
    }

}
