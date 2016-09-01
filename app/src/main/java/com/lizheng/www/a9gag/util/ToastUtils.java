package com.lizheng.www.a9gag.util;

import android.content.Context;
import android.widget.Toast;

import com.lizheng.www.a9gag.APP;

/**
 * Created by 10648 on 2016/9/1 0001.
 */
public class ToastUtils {
    private ToastUtils() {
    }

    private static void show(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    private static void show(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    public static void showShort(int resId) {
        Toast.makeText(APP.getsContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(String message) {
        Toast.makeText(APP.getsContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        Toast.makeText(APP.getsContext(), resId, Toast.LENGTH_LONG).show();
    }

    public static void showLong(String message) {
        Toast.makeText(APP.getsContext(), message, Toast.LENGTH_LONG).show();
    }
}