package com.wuchao.latte.app;

/**
 * @author: wuchao
 * @date: 2017/10/16 22:40
 * @desciption: 唯一的单例，只初始化一次，进行多线程操作时，通过枚举安全的惰性单例初始化，即线程安全的懒汉模式
 */

public enum ConfigKeys {
    API_HOST,               //配置网络请求域名
    APPLICATION_CONTEXT,    //全局上下文
    CONFIG_READY,           //控制我的初始化或配置是否完成
    ICON,       //存储自己初始化的项目
    INTERCEPTOR,
    JAVASCRIPT_INTERFACE,
    ACTIVITY,
    HANDLER,
}
