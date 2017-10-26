package com.wuchao.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.wuchao.latte.app.Latte;

/**
 * @author: wuchao
 * @date: 2017/10/26 23:26
 * @desciption:
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
