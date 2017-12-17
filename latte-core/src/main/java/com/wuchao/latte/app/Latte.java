package com.wuchao.latte.app;

import android.content.Context;
import android.os.Handler;

/**
 * @author: wuchao
 * @date: 2017/10/16 22:36
 * @desciption:
 */

public final class Latte {

    /**
     * 返回配置
     *
     * @param context
     */
    public static Configurator init(Context context) {
        getConfigurator().getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfigurations(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfigurations(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler(){
        return getConfigurations(ConfigKeys.HANDLER);
    }
}
