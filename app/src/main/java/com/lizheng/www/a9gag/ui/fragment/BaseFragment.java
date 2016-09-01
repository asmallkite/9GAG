package com.lizheng.www.a9gag.ui.fragment;

import android.support.v4.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lizheng.www.a9gag.data.RequestManager;
import com.lizheng.www.a9gag.util.ToastUtils;

/**
 * Created by 10648 on 2016/9/1 0001.
 */
public abstract class BaseFragment extends Fragment{
    @Override
    public void onDestroy() {
        super.onDestroy();
        RequestManager.cancelAll(this);
    }
    protected void executeRequest(Request request) {
        RequestManager.addRequest(request, this);
    }

    protected Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.showLong(error.getMessage());
            }
        };
    }
}
