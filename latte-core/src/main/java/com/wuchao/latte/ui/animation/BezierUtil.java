package com.wuchao.latte.ui.animation;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wuchao.latte.R;

/**
 * @author: wuchao
 * @date: 2018/1/20 20:29
 * @desciption:
 */

public class BezierUtil {

    static void startAnimationForJd(final View v, int fromXDelta,
                                    int fromYDelta, int fx, int fy, int mx, int my, int tx,
                                    int ty, final AnimationListener listener) {
        final AnimationSet set = new AnimationSet(false);
        final TranslateAnimation translateAnimation1 =
                new TranslateAnimation(fromXDelta, mx - fx, fromYDelta, my - fy);
        translateAnimation1.setInterpolator(new DecelerateInterpolator());
        translateAnimation1.setRepeatCount(0);
        translateAnimation1.setFillAfter(false);
        set.addAnimation(translateAnimation1);

        final TranslateAnimation translateAnimation2 =
                new TranslateAnimation(fromXDelta, tx - mx, fromYDelta, ty - my);
        translateAnimation2.setInterpolator(new AccelerateInterpolator());
        translateAnimation2.setRepeatCount(0);
        translateAnimation2.setFillAfter(false);
        set.addAnimation(translateAnimation2);
        set.setDuration(700);
        set.setFillAfter(false);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
                if (listener != null) {
                    listener.onAnimationEnd();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        v.startAnimation(set);
    }

    static ViewGroup createAnimLayout(Activity activity) {
        final ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        final LinearLayout animLayout = new LinearLayout(activity);
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE - 1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    static View addViewToAnimLayout(Context mContext, View view, int[] location, boolean wrap_content) {
        if (view == null) return null;
        int x = location[0];
        int y = location[1];
        final LinearLayout.LayoutParams params;
        if (wrap_content) {
            Drawable drawable = null;
            if (view instanceof ImageView) {
                drawable = ((ImageView) view).getDrawable();
            }
            if (drawable == null) {
                params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            } else {
                params = new LinearLayout.LayoutParams(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
        } else {
            final int wh = mContext.getResources().getDimensionPixelSize(R.dimen.db_goods_wh);
            params = new LinearLayout.LayoutParams(wh, wh);
        }
        params.leftMargin = x;
        params.topMargin = y;
        view.setLayoutParams(params);
        return view;
    }

    public interface AnimationListener {
        /**
         * 处理动画结束后的逻辑，不要涉及动画相关的View
         */
        void onAnimationEnd();
    }
}
