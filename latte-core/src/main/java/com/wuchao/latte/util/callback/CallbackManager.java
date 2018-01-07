package com.wuchao.latte.util.callback;

import java.util.WeakHashMap;

/**
 * @author: wuchao
 * @date: 2018/1/7 16:56
 * @desciption:
 */

public class CallbackManager {

    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    private static class Holder {
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    public CallbackManager addCallback(Object tag, IGlobalCallback callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag) {
        return CALLBACKS.get(tag);
    }
}
