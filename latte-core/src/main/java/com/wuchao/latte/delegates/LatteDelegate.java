package com.wuchao.latte.delegates;

/**
 * @author: wuchao
 * @date: 2017/10/22 23:01
 * @desciption: 正式使用的
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
