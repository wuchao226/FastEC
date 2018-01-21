package com.wuchao.latte.ec.main.personal.settings;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.wuchao.latte.delegates.LatteDelegate;
import com.wuchao.latte.ec.main.personal.list.ListBean;

/**
 * @author: wuchao
 * @date: 2018/1/9 22:17
 * @desciption:
 */

public class SettingsClickListener extends SimpleClickListener {

    private final LatteDelegate mDelegate;

    public SettingsClickListener(LatteDelegate delegate) {
        mDelegate = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                //这是消息推送的开关
                break;
            case 2:
                mDelegate.getSupportDelegate().start(bean.getDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
