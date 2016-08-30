package com.lizheng.www.a9gag.model;

/**
 * Created by 10648 on 2016/8/30 0030.
 * 三个大种类，在DrawerLayout里显示的
 */
public enum Category {
    hot("hot"), trending("trending"), fresh("fresh");

    private String mDisplayName;

    Category(String mDisplayName) {
        this.mDisplayName = mDisplayName;
    }

    public String getmDisplayName() {
        return mDisplayName;
    }
}
