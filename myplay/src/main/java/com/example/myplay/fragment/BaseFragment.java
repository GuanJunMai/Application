package com.example.myplay.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myplay.view.FragLayout;
import com.example.myplay.view.FragLayout.OnReloadListener;

/**
 * Created by Administrator on 2017/5/5.
 */

public abstract class BaseFragment extends Fragment implements OnReloadListener {
    FragLayout fragLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (fragLayout == null) {
            fragLayout = new FragLayout(getContext());
            fragLayout.setView(getSuView());
            fragLayout.setOnReloadListener(this);
            fragLayout.showLoading();
            loadData();
        }

        return fragLayout;
    }

    /**
     * 获取成功的View
     *
     * @return
     */
    public abstract View getSuView();

    /**
     * 加载数据的操作
     */
    public abstract void loadData();

    @Override
    public void onReload() {
        loadData();
    }
}
