package com.wuchao.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wuchao.ec.R;
import com.wuchao.latte.delegates.LatteDelegate;

/**
 * @author: wuchao
 * @date: 2018/1/4 22:34
 * @desciption:
 */

public class NameDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
