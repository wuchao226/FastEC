package com.wuchao.fastec.example;

import com.wuchao.latte.activitys.ProxyActivity;
import com.wuchao.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
