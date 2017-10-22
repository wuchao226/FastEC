package com.wuchao.latte.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.wuchao.latte.R;
import com.wuchao.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author: wuchao
 * @date: 2017/10/22 22:45
 * @desciption: 作为整个页面唯一容器使用
 */

public abstract class ProxyActivity extends SupportActivity {

    //用来返回根delegate
    public abstract LatteDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        //第一次加载时
        if (savedInstanceState == null) {
            //加载根Fragment, 即Activity内的第一个Fragment
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //单activity架构，当这个activity退出时即整个应用也退出了
        System.gc();
        System.runFinalization();
    }
}
