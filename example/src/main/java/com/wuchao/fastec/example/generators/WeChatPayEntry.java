package com.wuchao.fastec.example.generators;

import com.wuchao.latte.annotations.PayEntryGenerator;
import com.wuchao.latte.wechat.templates.WXPayEntryTemplate;

/**
 * @author: wuchao
 * @date: 2017/12/27 23:05
 * @desciption:
 */
@PayEntryGenerator(packageName = "com.wuchao.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class)
public interface WeChatPayEntry {
}
