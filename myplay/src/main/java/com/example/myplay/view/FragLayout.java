package com.example.myplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.myplay.R;
import com.example.myplay.utils.LogUtil;


/**
 * Created by Administrator on 2017/5/5.
 */

public class FragLayout extends FrameLayout {

    private View loading;//加载中的界面
    private View error;//加载失败的界面
    private View success;//加载成功的界面
    private OnReloadListener listener;

    public FragLayout(Context context) {
        this(context, null);
    }

    public FragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FragLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        //将2种状态的View对象添加到当前的帧布局中
        loading = View.inflate(getContext(), R.layout.page_loading, null);
        addView(loading);
        error = View.inflate(getContext(), R.layout.page_error, null);
        Button btn_reload = (Button) error.findViewById(R.id.btn_reload);
        btn_reload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                LogUtil.e(" btn_reload-onClick");
                listener.onReload();
            }
        });
        addView(error);
        hideAll();
    }

    /**
     * 设置展示数据的View
     *
     * @param view
     */
    public void setView(View view) {
        success = view;
        if (success != null) {
            success.setVisibility(View.INVISIBLE);
            addView(success);
        } else {
            throw new IllegalArgumentException("The successView can not be null!Please check it!");
        }
    }

    /**
     * 隐藏所有的View
     */
    private void hideAll() {
        loading.setVisibility(View.INVISIBLE);
        error.setVisibility(View.INVISIBLE);
        if (success != null) {
            success.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 显示loadingView
     */
    public void showLoading() {
        hideAll();
        loading.setVisibility(View.VISIBLE);
    }

    /**
     * 显示error界面
     */
    public void showError() {
        hideAll();
        error.setVisibility(View.VISIBLE);
    }

    /**
     * 显示success界面
     */
    public void showSuccess() {
        hideAll();
        if (success != null) {
            success.setVisibility(View.VISIBLE);
        }
    }

    public void setOnReloadListener(OnReloadListener reloadListener) {
        listener = reloadListener;
    }

    public interface OnReloadListener {
        void onReload();
    }
}
