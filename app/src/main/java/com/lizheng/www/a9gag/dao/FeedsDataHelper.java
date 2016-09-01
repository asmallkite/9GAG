package com.lizheng.www.a9gag.dao;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.lizheng.www.a9gag.model.Category;
import com.lizheng.www.a9gag.model.Feed;
import com.lizheng.www.a9gag.util.Column;
import com.lizheng.www.a9gag.util.SQLiteTable;

/**
 * Created by 10648 on 2016/8/31 0031.
 */
public class FeedsDataHelper extends BaseDataHelper {

    private Category mCategory;

    public FeedsDataHelper(Context context, Category mCategory) {
        super(context);
        this.mCategory = mCategory;
    }

    @Override
    protected Uri getContentUri() {
        return DataProvider.FEEDS_CONTENT_URI;
    }

    public Feed query(long id) {
        Feed feed = null;
        Cursor cursor = query(null, FeedsDBInfo.CATEGORY + "=?" + " AND" +
        FeedsDBInfo.ID + "= ?", new String[] {
                String.valueOf(mCategory.ordinal()), String.valueOf(id)
        }, null);

        if (cursor.moveToFirst()) {
            feed = Feed.fromCursor(cursor);
        }
        cursor.close();
        return feed;
    }



    public static final class FeedsDBInfo implements BaseColumns {
        private FeedsDBInfo() {
        }

        public static final String TABLE_NAME = "feeds";

        public static final String ID = "id";

        public static final String CATEGORY = "category"; //在model中的Category

        public static final String JSON = "json";

        public static final SQLiteTable TABLE = new SQLiteTable(TABLE_NAME)
                .addColumn(ID, Column.DataType.INTEGER)
                .addColumn(CATEGORY, Column.DataType.INTEGER)
                .addColumn(JSON, Column.DataType.TEXT);
    }
}
