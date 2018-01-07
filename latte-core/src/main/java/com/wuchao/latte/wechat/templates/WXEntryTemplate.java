package com.wuchao.latte.wechat.templates;

import com.wuchao.latte.wechat.BaseWXEntryActivity;
import com.wuchao.latte.wechat.LatteWeChat;

/**
 * @author: wuchao
 * @date: 2017/12/27 23:03
 * @desciption:
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
