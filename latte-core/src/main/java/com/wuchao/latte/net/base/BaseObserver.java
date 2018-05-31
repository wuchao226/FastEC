package com.wuchao.latte.net.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wuchao
 * @date: 2018/2/1 18:47
 * @desciption: 基类BaseObserver
 */

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
