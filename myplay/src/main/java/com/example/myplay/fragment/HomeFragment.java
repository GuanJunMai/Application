package com.example.myplay.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.example.myplay.R;
import com.example.myplay.adapter.HomeHeaderAdapter;
import com.example.myplay.adapter.RecyclerAdapter;
import com.example.myplay.bean.AppInfo;
import com.example.myplay.bean.Home;
import com.example.myplay.http.Url;
import com.example.myplay.utils.GsonUtil;
import com.example.myplay.utils.LogUtil;
import com.lxj.xrefreshlayout.XRefreshLayout;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/5/5.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ArrayList<AppInfo> appInfos = new ArrayList<>();
    @BindView(R.id.xRefreshLayout)
    XRefreshLayout xRefreshLayout;
    private RecyclerAdapter recyclerAdapter;
    private HeaderAndFooterWrapper<AppInfo> headerAndFooterAdapter;
    private ViewPager viewPager;
    Boolean isRefresh = true;
    private String url;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //让ViewPager选择下一页
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            handler.sendEmptyMessageDelayed(0, 2500);
        }
    };

    @Override
    public View getSuView() {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        //设置线性列表
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAdapter = new RecyclerAdapter(getContext(), R.layout.adapter_recycler, appInfos);
//        recyclerView.setAdapter(recyclerAdapter);
        setHeaderAdapter();
        recyclerView.setAdapter(headerAndFooterAdapter);
        xRefreshLayout.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                loadData();
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                loadData();
            }
        });
        return view;
    }

    @Override
    public void loadData() {
        if (isRefresh) {
            url = Url.Home + 0;
        } else {
            url = Url.Home + appInfos.size();
        }
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        fragLayout.showError();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Home home = GsonUtil.parseJsonToBean(response, Home.class);
                        if (home != null) {
                            if (home.picture != null && home.picture.size() > 0) {
                                viewPager.setAdapter(new HomeHeaderAdapter(home.picture));
                                //每次加载先remove之前的消息
                                handler.removeMessages(0);
                                handler.sendEmptyMessageDelayed(0, 2500);
                            }
                            if (isRefresh) {
                                appInfos.clear();
                            }
                            appInfos.addAll(home.list);
                            headerAndFooterAdapter.notifyDataSetChanged();
                        }
                        fragLayout.showSuccess();
                        //完成刷新
                        xRefreshLayout.completeRefresh();
                    }
                });
    }

    /**
     * 设置头部ViewPager
     */
    private void setHeaderAdapter() {
        headerAndFooterAdapter = new HeaderAndFooterWrapper<>(recyclerAdapter);
        viewPager = (ViewPager) LayoutInflater.from(getContext()).inflate(R.layout.header_home, recyclerView, false);
        viewPager.setPageTransformer(true, new CubeOutTransformer());
        headerAndFooterAdapter.addHeaderView(viewPager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //避免内存泄露
        handler.removeMessages(0);
    }
}
