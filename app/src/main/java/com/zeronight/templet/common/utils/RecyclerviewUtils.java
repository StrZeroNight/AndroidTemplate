package com.zeronight.templet.common.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2018/2/23.
 */

public class RecyclerviewUtils {

    public RecyclerviewUtils() {
    }

    public void setNestScrollRecyclerview(LinearLayoutManager linearLayoutManager , XRecyclerView xrv){
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        xrv.setLayoutManager(linearLayoutManager);
        xrv.setHasFixedSize(true);
        xrv.setNestedScrollingEnabled(false);
    }

    public void setNestScrollRecyclerview(LinearLayoutManager linearLayoutManager , RecyclerView rv){
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
    }

}
