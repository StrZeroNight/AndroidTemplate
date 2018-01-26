package com.zeronight.templet.module.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.data.TestData;
import com.zeronight.templet.common.utils.CommonUtils;
import com.zeronight.templet.common.utils.ListManager;
import com.zeronight.templet.common.utils.SearchHistoryUtils;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.widget.SearchBar;

import java.util.List;

import static com.zeronight.templet.common.utils.SearchHistoryUtils.SEARCH_HISTORY;

/**
 * Created by Administrator on 2018/1/19.
 */

public class SearchActvity extends BaseActivity {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private SearchBar searchbar;
    private RecyclerView rv_hot;
    private RecyclerView rv_history;
    private XRecyclerView xrv;
    private NestedScrollView nsc_history;
    private List<String> history;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, SearchActvity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, SearchActvity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, SearchActvity.class);
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.startActivityForResult(it, REQUEST_CODE);
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra(ID) != null) {
            String id = intent.getStringExtra(ID);
            ToastUtils.showMessage("获取id" + id);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initHistoryInfo();
        initView();
    }

    private void initView() {
        nsc_history = (NestedScrollView) findViewById(R.id.nsc_history);
        searchbar = (SearchBar) findViewById(R.id.searchbar);
        initSearchBar();
        xrv = (XRecyclerView) findViewById(R.id.xrv);
        initSearchList();
        rv_hot = (RecyclerView) findViewById(R.id.rv_hot);
        initHotRecycler();
        rv_history = (RecyclerView) findViewById(R.id.rv_history);
        initHistoryRecycler();
    }

    private void initSearchList() {
        ListManager<String> listManager = new ListManager<>(SearchActvity.this);
                listManager.setRecyclerView(xrv)
                        .setAdapter(new SearchAdapter(SearchActvity.this , listManager.getAllList()))
//                        .setUrl(ConstantUrl.getCodesMain)
                        .isLoadMore(true)
                        .isPullRefresh(true)
                        .run();
    }

    private void initHistoryInfo(){
        history = SearchHistoryUtils.getHistory(SEARCH_HISTORY);
        if (history != null && history.size() > 0) {
            history.add("删除历史记录");
        }
    }

    private void initSearchBar(){
        searchbar.setOnSearchBarClickListener(new SearchBar.OnSearchBarClickListener() {
            @Override
            public void OnLeftClick() {
                finish();
            }

            @Override
            public void OnRightClick() {
                CommonUtils.hideSoft(SearchActvity.this , searchbar);
                SearchHistoryUtils.storeHistory(searchbar.getSearchText(), SEARCH_HISTORY);
                search(searchbar.getSearchText());
            }

            @Override
            public void OnSoftSearch() {
                SearchHistoryUtils.storeHistory(searchbar.getSearchText(), SEARCH_HISTORY);
                search(searchbar.getSearchText());
            }

            @Override
            public void OnFouceChange() {
                if (nsc_history.getVisibility() != View.VISIBLE) {
                    xrv.setVisibility(View.GONE);
                    nsc_history.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initHotRecycler(){
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        rv_hot.setLayoutManager(layoutManager);
        HotAdapter hotAdapter = new HotAdapter(this, TestData.getLists());
        hotAdapter.setOnButtonClickListenr(new HotAdapter.OnButtonClickListenr() {
            @Override
            public void OnButtonClick(int position) {
                // TODO: 2018/1/26 修改来源
                search(TestData.getLists().get(position));
            }
        });
        rv_hot.setAdapter(hotAdapter);
    }

    private void initHistoryRecycler(){
        FlexboxLayoutManager layoutManager2 = new FlexboxLayoutManager(this);
        layoutManager2.setFlexDirection(FlexDirection.ROW);
        layoutManager2.setJustifyContent(JustifyContent.FLEX_START);
        layoutManager2.setFlexWrap(FlexWrap.WRAP);
        rv_history.setLayoutManager(layoutManager2);
        final SearchAdapter searchAdapter = new SearchAdapter(this, history);
        searchAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                if (position == history.size() - 1) {
                    SearchHistoryUtils.clearHistory(SEARCH_HISTORY);
                    history.clear();
                    searchAdapter.notifyDataSetChanged();
                }else{
                    CommonUtils.hideSoft(SearchActvity.this , searchbar);
                    search(history.get(position));
                }
            }
        });
        rv_history.setAdapter(searchAdapter);
    }

    private void search(String searchStr){
        searchbar.setSearchText(searchStr);
        xrv.setVisibility(View.VISIBLE);
        nsc_history.setVisibility(View.GONE);
    }

}
