package com.lizheng.www.a9gag.dao;

import android.content.Context;
import android.net.Uri;

import com.lizheng.www.a9gag.model.Category;

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
}
