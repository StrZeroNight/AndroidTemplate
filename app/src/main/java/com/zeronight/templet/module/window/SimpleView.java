package com.zeronight.templet.module.window;

import android.content.Context;
import android.view.LayoutInflater;

import com.dalimao.library.DragView;
import com.zeronight.templet.R;

/**
 * Created by Administrator on 2018/1/18.
 */

public class SimpleView extends DragView {

    public SimpleView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_window, this);
    }

}