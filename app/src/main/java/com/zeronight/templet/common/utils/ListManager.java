package com.zeronight.templet.common.utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.widget.loadlayout.LoadingAndRetryManager;
import com.zeronight.templet.common.widget.loadlayout.OnLoadingAndRetryListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 */

public class ListManager<T> {

    private AppCompatActivity context;
    private String url = "";
    private int page = 1;
    private List<T> allList = new ArrayList<>();
    private BaseAdapter<T> mAdapter;
    private LoadingAndRetryManager mLoadingAndRetryManager;
    private XRecyclerView xrv;
    private boolean isPullRefresh = true;
    private boolean isLoadMore = false;
    private OnItemClickListener onItemClickListener;

    public ListManager(AppCompatActivity context) {
        this.context = context;
    }

    public ListManager setRecyclerView(XRecyclerView xrv){
        this.xrv = xrv;
        return this;
    }

    public ListManager setAdapter(BaseAdapter mAdapter){
        this.mAdapter = mAdapter;
        return this;
    }

    public List<T> getAllList(){
        return allList;
    }

    public ListManager setUrl(String url){
        this.url = url;
        return this;
    }

    public ListManager isPullRefresh(boolean isPullRefresh){
        this.isPullRefresh = isPullRefresh;
        return this;
    }

    public ListManager isLoadMore(boolean isLoadMore){
        this.isLoadMore = isLoadMore;
        return this;
    }

    public void run(){
        initClass();
        getList(xrv);
    }

    public void refresh(){
        getList(xrv);
    }

    private void initClass() {
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                Logger.i("in position:" + position);
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClick(position);
                }
            }
        });
        xrv.setLayoutManager(new LinearLayoutManager(context));
        xrv.setAdapter(mAdapter);
        if (isPullRefresh) {
            xrv.setPullRefreshEnabled(true);
        }else{
            xrv.setPullRefreshEnabled(false);
        }
        if (isLoadMore) {
            xrv.setLoadingMoreEnabled(true);
        }else{
            xrv.setLoadingMoreEnabled(false);
        }
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrv.setPullRefreshEnabled(false);
                page = 1;
                getList(xrv);
            }

            @Override
            public void onLoadMore() {
                page++;
                getList(xrv);
            }
        });
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(xrv, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                LinearLayout ll_retry = (LinearLayout) retryView.findViewById(R.id.ll_retry);
                ll_retry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mLoadingAndRetryManager.showLoading();
                        page = 1;
                        getList(xrv);
                    }
                });
            }
        });
    }

    private void getList(final XRecyclerView xrv) {
        mLoadingAndRetryManager.showLoading();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(url)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {

            @Override
            public void onServerError() {
                if (mLoadingAndRetryManager != null && xrv != null) {
                    xrv.setPullRefreshEnabled(true);

                    mLoadingAndRetryManager.showContent();


                    xrv.refreshComplete();
//                    xrv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNetWorkError() {
                xrv.setPullRefreshEnabled(true);
                mLoadingAndRetryManager.showRetry();
                xrv.refreshComplete();
                xrv.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(String data) {
                xrv.setPullRefreshEnabled(true);
                mLoadingAndRetryManager.showContent();
                xrv.refreshComplete();
                xrv.setVisibility(View.VISIBLE);
                //显示列表
                JSONArray jsonArray = JSON.parseArray(data);
                List<T> list = (List<T>)jsonArray;
                if (page == 1) {
                    allList.clear();
                    allList.addAll(list);
                    mAdapter.notifyDataSetChanged();
                } else {
                    allList.addAll(list);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNoData() {
                xrv.setPullRefreshEnabled(true);
                if (page == 1) {
                    mLoadingAndRetryManager.showEmpty();
                    xrv.setVisibility(View.GONE);
                    xrv.refreshComplete();
                } else {
                    mLoadingAndRetryManager.showContent();
                    xrv.refreshComplete();
                }
            }
        });
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public ListManager setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
        return this;
    }

}