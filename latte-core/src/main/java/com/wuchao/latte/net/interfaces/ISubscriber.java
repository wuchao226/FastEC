package com.wuchao.latte.net.interfaces;

import io.reactivex.disposables.Disposable;

/**
 * @author: wuchao
 * @date: 2018/2/1 18:50
 * @desciption: 定义请求结果处理接口
 */

public interface ISubscriber {
    /**
     * doOnSubscribe 回调
     *
     * @param d
     */
    void doOnSubscribe(Disposable d);
}
