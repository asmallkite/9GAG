package com.lizheng.www.a9gag.util;

import android.os.AsyncTask;
import android.os.Build;

/**
 * Created by 10648 on 2016/9/1 0001.
 */
public class TaskUtils {
    public static <Params, Progress, Result> void executeAsyncTask(
            AsyncTask<Params, Progress, Result> task, Params... params) {
        if (Build.VERSION.SDK_INT >= 11) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        } else {
            task.execute(params);
        }
    }
}
