package com.wuchao.fastec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wuchao.latte.app.Latte;
import com.wuchao.latte.ec.database.DatabaseManager;
import com.wuchao.latte.ec.icon.FontEcModule;
import com.wuchao.latte.net.interceptors.DebugInterceptor;

/**
 * @author: wuchao
 * @date: 2017/10/16 22:26
 * @desciption:
 */

public class ExampleApp extends Application {

    //http://114.67.235.114/RestServer/api/user_profile.php
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://192.168.1.102/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
