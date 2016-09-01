package com.lizheng.www.a9gag.model;

import android.database.Cursor;

import com.google.gson.Gson;
import com.lizheng.www.a9gag.dao.FeedsDataHelper;

import java.util.HashMap;

/**
 * Created by 10648 on 2016/8/31 0031.
 * 根据api定义的Feed类
 */
public class Feed extends BaseModel {

    private static final HashMap<String, Feed> CACHE = new HashMap<>();

    public String id;
    public String caption;
    public String link;
    public Image images;
    public Vote votes;

    public class Image {
        public String small;
        public String normal;
        public String large;
    }

    private class Vote {
        public int count;
    }



    public static Feed fromCursor(Cursor cursor) {
        String id = cursor.getString(cursor.getColumnIndex(FeedsDataHelper.FeedsDBInfo.ID));
        Feed feed = getFromCache(id);
        if (feed == null) {
            feed = new Gson().fromJson(
                    cursor.getString(cursor.getColumnIndex(FeedsDataHelper.FeedsDBInfo.JSON)),
                    Feed.class);
            addToCache(feed);
        }
        return feed;
    }

    private static void addToCache(Feed feed) {
        CACHE.put(feed.id, feed);
    }

    private static Feed getFromCache(String id) {
        return CACHE.get(id);
    }

}
