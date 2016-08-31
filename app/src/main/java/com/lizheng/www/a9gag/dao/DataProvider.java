package com.lizheng.www.a9gag.dao;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by 10648 on 2016/8/31 0031.
 */
public class DataProvider extends ContentProvider {

    public static final String AUTHORITY = "com.lizheng.www.a9gag"; //权限
    public static final String SCHEME = "content://";
    public static final String PATH_FEEDS = "/feeds";

    public static final Uri FEEDS_CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + PATH_FEEDS);

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
