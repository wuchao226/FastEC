package com.wuchao.fastec.example.event;

import com.blankj.utilcode.util.ToastUtils;
import com.wuchao.latte.delegates.web.event.Event;

/**
 * @author: wuchao
 * @date: 2018/1/18 16:48
 * @desciption:
 */

public class shareEvent extends Event {
    @Override
    public String execute(String params) {
        ToastUtils.showLong("分享");
        return null;
    }
}
