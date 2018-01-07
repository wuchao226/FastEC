package com.wuchao.latte.util.callback;

import android.support.annotation.NonNull;

/**
 * @author: wuchao
 * @date: 2018/1/7 16:57
 * @desciption:
 */

public interface IGlobalCallback<T> {

    void executeCallback(@NonNull T args);
}
