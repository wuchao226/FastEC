package com.wuchao.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuchao.ec.R;
import com.wuchao.ec.R2;
import com.wuchao.latte.delegates.LatteDelegate;

import butterknife.BindView;

/**
 * @author: wuchao
 * @date: 2018/1/4 22:30
 * @desciption:
 */

public class SettingsDelegate extends LatteDelegate {

    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
