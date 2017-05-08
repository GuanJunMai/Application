package com.example.myplay.utils;

import android.widget.Toast;
import com.example.myplay.global.GooglePlayApp;
/**
 * Created by dance on 2017/5/4.
 */

public class ToastUtil {
    private static Toast toast;
    /**
     * @param msg
     */
    public static void showToast(String msg){
        if(toast==null){
            toast = Toast.makeText(GooglePlayApp.context, msg, Toast.LENGTH_SHORT);
        }else {
            toast.setText(msg);
        }
        toast.show();
    }
}
