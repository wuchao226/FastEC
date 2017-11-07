package com.wuchao.latte.app;

import android.content.Context;

import java.util.HashMap;

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
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
