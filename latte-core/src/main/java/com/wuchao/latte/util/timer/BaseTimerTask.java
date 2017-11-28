package com.wuchao.latte.util.timer;

import java.util.TimerTask;

/**
 * @author: wuchao
 * @date: 2017/11/14 22:29
 * @desciption:
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener timerListener) {
        mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
