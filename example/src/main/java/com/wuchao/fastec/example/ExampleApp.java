package com.wuchao.fastec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wuchao.latte.app.Latte;
import com.wuchao.latte.ec.icon.FontEcModule;

/**
 * @author: wuchao
 * @date: 2017/10/16 22:26
 * @desciption:
 */

public class ExampleApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("")
                .configure();
    }
}
