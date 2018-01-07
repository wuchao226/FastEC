package com.wuchao.latte.ec.pay;

/**
 * @author: wuchao
 * @date: 2017/12/25 22:29
 * @desciption:
 */

public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayingFail();

    void onPayingCancel();

    void onPayingConnectError();
}
