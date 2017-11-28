package com.wuchao.latte.app;

import com.wuchao.latte.util.storage.LattePreference;

/**
 * @author: wuchao
 * @date: 2017/11/27 19:00
 * @desciption: 管理用户信息
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 保存用户状态
     *
     * @param state
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    public static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
