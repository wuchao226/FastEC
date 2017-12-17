package com.wuchao.latte.delegates.bottom;

/**
 * @author: wuchao
 * @date: 2017/12/2 18:15
 * @desciption:
 */

public class BottomTabBean {

    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
