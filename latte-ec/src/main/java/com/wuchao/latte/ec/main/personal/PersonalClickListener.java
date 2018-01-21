package com.wuchao.latte.ec.main.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.wuchao.latte.delegates.LatteDelegate;
import com.wuchao.latte.ec.main.personal.list.ListBean;

/**
 * @author: wuchao
 * @date: 2018/1/7 22:21
 * @desciption:
 */

public class PersonalClickListener extends SimpleClickListener{

    private LatteDelegate mDelegate;

    public PersonalClickListener(LatteDelegate delegate) {
        mDelegate = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean= (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                mDelegate.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
                break;
            case 2:
                mDelegate.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
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
