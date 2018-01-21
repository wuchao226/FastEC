package com.wuchao.fastec.example;

import android.app.Application;
import android.support.annotation.NonNull;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wuchao.fastec.example.event.TestEvent;
import com.wuchao.fastec.example.event.shareEvent;
import com.wuchao.latte.app.Latte;
import com.wuchao.latte.ec.database.DatabaseManager;
import com.wuchao.latte.ec.icon.FontEcModule;
import com.wuchao.latte.net.rx.AddCookieInterceptor;
import com.wuchao.latte.util.callback.CallbackManager;
import com.wuchao.latte.util.callback.CallbackType;
import com.wuchao.latte.util.callback.IGlobalCallback;
import com.wuchao.latte.util.log.LatteLogger;

import cn.jpush.android.api.JPushInterface;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author: wuchao
 * @date: 2017/10/16 22:26
 * @desciption:
 */

public class ExampleApp extends Application {

    //http://114.67.235.114/RestServer/api/user_profile.php
    //192.168.1.102
    //192.168.1.128
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://192.168.1.102:8080/RestServer/api/")
                //.withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withInterceptor(mLoggingInterceptor)
                .withJavaScriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                .withWebEvent("share", new shareEvent())
                //添加Cookie拦截器
                .withWebHost("http://www.baidu.com/")
                .withInterceptor(new AddCookieInterceptor())
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@NonNull Object args) {
                        if(JPushInterface.isPushStopped(Latte.getApplicationContext())){
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@NonNull Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            JPushInterface.stopPush(Latte.getApplicationContext());
                        }
                    }
                });
    }

    HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            LatteLogger.i("RetrofitLog", message);
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY);


    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
