package com.zeronight.templet.common.utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.data.EventBusBundle;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.widget.loadlayout.LoadingAndRetryManager;
import com.zeronight.templet.common.widget.loadlayout.OnLoadingAndRetryListener;
import com.zeronight.templet.module.cart.CartAdapter;
import com.zeronight.templet.module.cart.CartFragment;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

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
    private OnNullListener onNullListener; //监听code=0
    private OnReceiveHttpDataListener receiveHttpData;
    //
    boolean isCart = false;

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

    public ListManager isCart(boolean isCart){
        this.isCart = isCart;
        return this;
    }

    public List<T> getAllList(){
        return allList;
    }

    public BaseAdapter<T> getAdapter(){
        return mAdapter;
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
        getListFirst(xrv);
    }

    public void refresh(){
        getListRefresh(xrv);
    }

    Object paramsObject = null;
    public ListManager setParamsObject(Object paramsObject){
        this.paramsObject = paramsObject;
        return this;
    }

    public final static int LINEAR_LAYOUT = 1;
    public final static int GLID_LAYOUT_TWO = 2;
    RecyclerView.LayoutManager layoutManager;
    public ListManager setLayoutManagerType(int type){
        if (type == LINEAR_LAYOUT) {
            layoutManager = new LinearLayoutManager(context);
        }else if (type == GLID_LAYOUT_TWO){
            layoutManager = new GridLayoutManager(context , 2);
        }
        return this;
    }

    private void initClass() {
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClick(position);
                }
            }
        });
        mAdapter.setOnItemLongClickListener(new BaseAdapter.OnItemLongClickListener() {
            @Override
            public void longClick(int position) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemLongClick(position);
                }
            }
        });
        if (layoutManager != null) {
            xrv.setLayoutManager(layoutManager);
        }else{
            Logger.e("请使用setLayoutManagerType()方法填充LayoutManager");
        }
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
                getListRefresh(xrv);
            }

            @Override
            public void onLoadMore() {
                page++;
                getListLoad(xrv);
            }
        });
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(xrv, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                // TODO: 2018/3/21 根据不同的页面，显示不同的错误页面样式
                retryView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mLoadingAndRetryManager.showLoading();
                        page = 1;
                        getListFirst(xrv);
                    }
                });
            }

            @Override
            public void setEmptyEvent(View emptyView) {
                // TODO: 2018/3/21 根据不同的页面，显示不同的空页面样式
                if (isCart) {
                    
                }else{
                    
                }
            }
        });
    }

    private void getListRefresh(final XRecyclerView xrv) {
        xrv.refresh();
        getList(xrv);
    }

    private void getListFirst(final XRecyclerView xrv) {
        mLoadingAndRetryManager.showLoading();
        getList(xrv);
    }

    private void getListLoad(final XRecyclerView xrv) {
        getList(xrv);
    }

    private void getList(final XRecyclerView xrv) {
        XRetrofitUtils retrofitUtils = null;
        if (paramsObject != null) {
            retrofitUtils = new XRetrofitUtils.Builder()
                    .setUrl(url)
                    .setParams("page" , page + "")
                    .setObjectParams(paramsObject)
                    .build();
        }else{
            retrofitUtils = new XRetrofitUtils.Builder()
                    .setUrl(url)
                    .setParams("page" , page + "")
                    .build();
        }
        retrofitUtils.AsynPostByBean(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onServerError() {
                if (mLoadingAndRetryManager != null && xrv != null) {
                    xrv.setPullRefreshEnabled(true);
                    mLoadingAndRetryManager.showRetry();
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
                //根据不同的页面显示不同的内容
                if (isCart){
                    CartAdapter mAdapter = (CartAdapter) ListManager.this.mAdapter;
                    mAdapter.map.clear();
                    EventBus.getDefault().post(new EventBusBundle(CartFragment.CHOOSE_NOT_ALL , ""));
                }
                xrv.setPullRefreshEnabled(true);
                mLoadingAndRetryManager.showContent();
                xrv.refreshComplete();
                xrv.setVisibility(View.VISIBLE);
                //显示列表
                JSONArray jsonArray = null;
                //根据是否是纯list来决定如何处理参数
                if (receiveHttpData != null) {
                    jsonArray = receiveHttpData.ReceiveHttpData(data);
                } else {
                    jsonArray = JSON.parseArray(data);
                }
                List<T> list = (List<T>) jsonArray;
                if (page == 1) {
                    //判断如果第一页列表为空,则显示暂无数据
                    if (list.size() > 0) {
                        allList.clear();
                        allList.addAll(list);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        mLoadingAndRetryManager.showEmpty();
                        xrv.setVisibility(View.GONE);
                        xrv.refreshComplete();
                    }
                } else {
                    allList.addAll(list);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNoData() {
                if (onNullListener != null) {
                    onNullListener.OnNull();
                }
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
        void OnItemLongClick(int position);
    }

    public ListManager setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnNullListener{
        void OnNull();
    }

    public ListManager setOnNullListener(OnNullListener onNullListener){
        this.onNullListener = onNullListener;
        return this;
    }


    public interface OnReceiveHttpDataListener{
        JSONArray ReceiveHttpData(String data);
    }

    public ListManager setOnReceiveHttpData(OnReceiveHttpDataListener receiveHttpData){
        this.receiveHttpData = receiveHttpData;
        return this;
    }

    //处理非完全列表的data时使用
    public static JSONArray getJSONArrayFromList(List<?> list){
        JSONArray jsonArray = new JSONArray();
        if (list==null ||list.isEmpty()) {
            return jsonArray;//nerver return null
        }

        for (Object object : list) {
            jsonArray.add(object);
        }
        return jsonArray;
    }



}
