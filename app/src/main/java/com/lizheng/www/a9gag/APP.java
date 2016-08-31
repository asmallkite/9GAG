package com.lizheng.www.a9gag;

import android.app.Application;
import android.content.Context;

/**
 * Created by 10648 on 2016/8/31 0031.
 * 设置全局变量
 * 后期加上ImageLoader
 */
public class APP extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getsContext() {
        return sContext;
    }
}
