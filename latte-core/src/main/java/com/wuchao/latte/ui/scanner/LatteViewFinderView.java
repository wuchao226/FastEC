package com.wuchao.latte.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * @author: wuchao
 * @date: 2018/1/11 22:43
 * @desciption:
 */

public class LatteViewFinderView extends ViewFinderView {

    public LatteViewFinderView(Context context) {
        this(context, null);
    }

    public LatteViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mSquareViewFinder = true;
        mBorderPaint.setColor(Color.YELLOW);
    }
}
