package com.wuchao.latte.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author: wuchao
 * @date: 2018/1/18 18:33
 * @desciption:
 */

public class CircleTextView extends AppCompatTextView {

    private Paint mPaint;
    //抗锯齿设置
    //给Canvas加上抗锯齿标志。
    //有些地方不能用paint的，就直接给canvas加抗锯齿，更方便。
    private PaintFlagsDrawFilter mFilter;

    public CircleTextView(Context context) {
        super(context, null);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        mPaint = new Paint();
        mFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public final void setCircleBackground(@ColorInt int color) {
        mPaint.setColor(color);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = getMeasuredWidth();
        final int height = getMaxHeight();
        final int max = Math.max(width, height);
        setMeasuredDimension(max, max);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setDrawFilter(mFilter);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2,
                Math.max(getWidth(), getHeight() / 2), mPaint);
        super.draw(canvas);
    }
}
