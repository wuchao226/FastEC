package com.wuchao.latte.ec.detail;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wuchao.ec.R;
import com.wuchao.latte.ui.recycler.ItemType;
import com.wuchao.latte.ui.recycler.MultipleFields;
import com.wuchao.latte.ui.recycler.MultipleItemEntity;
import com.wuchao.latte.ui.recycler.MultipleRecyclerAdapter;
import com.wuchao.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @author: wuchao
 * @date: 2018/1/20 17:30
 * @desciption:
 */

public class RecyclerImageAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .dontAnimate();

    protected RecyclerImageAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.SINGLE_BIG_IMAGE, R.layout.item_image);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        final int type = holder.getItemViewType();
        switch (type) {
            case ItemType.SINGLE_BIG_IMAGE:
                final AppCompatImageView imageView = holder.getView(R.id.image_rv_item);
                final String url = item.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(url)
                        .apply(OPTIONS)
                        .into(imageView);
                break;
            default:
                break;
        }
    }
}
