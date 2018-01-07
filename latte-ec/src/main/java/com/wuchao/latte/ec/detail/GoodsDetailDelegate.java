package com.wuchao.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wuchao.ec.R;
import com.wuchao.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author: wuchao
 * @date: 2017/12/17 16:34
 * @desciption:
 */

public class GoodsDetailDelegate extends LatteDelegate {

    public static GoodsDetailDelegate create() {
        GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
