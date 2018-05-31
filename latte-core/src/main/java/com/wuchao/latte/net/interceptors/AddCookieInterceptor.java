package com.wuchao.latte.net.rx;

import com.wuchao.latte.util.log.LatteLogger;
import com.wuchao.latte.util.storage.LattePreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: wuchao
 * @date: 2017/12/23 21:05
 * @desciption:
 */

public final class AddCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        //通过just( )方式 直接触发onNext()，just中传递的参数将直接在Observer的onNext()方法中接收到
        Observable
                .just(LattePreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cookie) throws Exception {
                        LatteLogger.d("AddCookieInterceptor", "cookie---》" +cookie);
                        //给原生API请求附带上WebView拦截下来的Cookie
                        builder.addHeader("Cookie", cookie);
                    }
                });
        return chain.proceed(builder.build());
    }
}
