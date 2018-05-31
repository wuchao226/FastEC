package com.wuchao.latte.net.interceptors;

import com.wuchao.latte.util.log.LatteLogger;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author: wuchao
 * @date: 2018/1/31 18:22
 * @desciption: 拦截器工具类!
 */

public class InterceptorUtil {
    private static String TAG = "RetrofitLog";

    //日志拦截器
    public static HttpLoggingInterceptor LoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LatteLogger.i(TAG, message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
