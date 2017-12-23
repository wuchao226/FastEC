package com.wuchao.latte.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wuchao.latte.delegates.bottom.BottomItemDelegate;
import com.wuchao.latte.ec.R;

/**
 * @author: wuchao
 * @date: 2017/12/23 21:53
 * @desciption:
 */

public class ShopCartDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
