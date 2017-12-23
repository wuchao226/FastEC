package com.wuchao.latte.delegates.web.event;

import com.wuchao.latte.util.log.LatteLogger;

/**
 * @author: wuchao
 * @date: 2017/11/29 22:44
 * @desciption:
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
