package com.example.myplay.fragment;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myplay.utils.LogUtil;

import static android.content.ContentValues.TAG;

/**
 * Created by dance on 2017/5/4.
 */

public class AppFragment extends BaseFragment {
    @Override
    public void loadData() {
//        String data = "sss";//从服务器得到的json数据
//        if (TextUtils.isEmpty(data)) {
//            fragLayout.showError();
//        } else {
//            fragLayout.showSuccess();
//        }
        fragLayout.showLoading();
    }

    @Override
    public View getSuView() {
        TextView textView = new TextView(getContext());
        textView.setPadding(20, 20, 20, 20);
        textView.setTextSize(20);
        textView.setText(this.getClass().getSimpleName());
        textView.setTextColor(Color.RED);
        return textView;
    }
}
