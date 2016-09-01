package com.lizheng.www.a9gag.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 10648 on 2016/9/1 0001.
 */
public class DBHelper extends SQLiteOpenHelper {


    // 数据库名
    private static final String DB_NAME = "9gag.db";

    // 数据库版本
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        FeedsDataHelper.FeedsDBInfo.TABLE.create(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
