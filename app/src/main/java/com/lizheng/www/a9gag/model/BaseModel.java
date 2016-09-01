package com.lizheng.www.a9gag.model;

import com.google.gson.Gson;

/**
 * Created by 10648 on 2016/8/31 0031.
 */
public abstract class BaseModel {
    public String toJson() {
        return new Gson().toJson(this);
    }
}
