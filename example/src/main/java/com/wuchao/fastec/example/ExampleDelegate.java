package com.wuchao.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wuchao.latte.delegates.LatteDelegate;

/**
 * @author: wuchao
 * @date: 2017/10/22 23:15
 * @desciption:
 */

public class ExampleDelegate extends LatteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
