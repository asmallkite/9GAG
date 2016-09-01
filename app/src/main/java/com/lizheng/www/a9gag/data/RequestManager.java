package com.lizheng.www.a9gag.data;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lizheng.www.a9gag.APP;

/**
 * Created by 10648 on 2016/9/1 0001.
 */
public class RequestManager {
    public static RequestQueue mRequestQueue = Volley.newRequestQueue(APP.getsContext());

    private RequestManager() {
        // no instances
    }

    public static void addRequest(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    public static void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
