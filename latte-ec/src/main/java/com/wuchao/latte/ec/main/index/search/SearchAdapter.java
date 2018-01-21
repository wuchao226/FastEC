package com.wuchao.latte.ec.main.index.search;

import android.support.v7.widget.AppCompatTextView;

import com.wuchao.ec.R;
import com.wuchao.latte.ui.recycler.MultipleFields;
import com.wuchao.latte.ui.recycler.MultipleItemEntity;
import com.wuchao.latte.ui.recycler.MultipleRecyclerAdapter;
import com.wuchao.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @author: wuchao
 * @date: 2018/1/16 22:25
 * @desciption:
 */

public class SearchAdapter extends MultipleRecyclerAdapter {

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        switch (item.getItemType()) {
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = item.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }
}
