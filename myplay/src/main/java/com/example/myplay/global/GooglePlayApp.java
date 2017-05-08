package com.example.myplay.global;

import android.app.Application;
import android.content.Context;

/**
 * Created by dance on 2017/5/4.
 */

public class GooglePlayApp extends Application {
    public static Context context;

    /**
     * android程序的入口方法
     */
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
