package com.wuchao.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * @author: wuchao
 * @date: 2017/10/18 22:53
 * @desciption:
 */

public enum EcIcons implements Icon {
    icon_scan('\ue67c'),
    icon_ali_pay('\ue673');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
