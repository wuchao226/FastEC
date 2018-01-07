package com.wuchao.latte.ec.main.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wuchao.latte.ui.recycler.DataConverter;
import com.wuchao.latte.ui.recycler.MultipleFields;
import com.wuchao.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author: wuchao
 * @date: 2018/1/2 22:46
 * @desciption:
 */

public class OrderListDataConverter extends DataConverter{

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final String time = data.getString("time");


            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.TITLE, title)
                    .setField(OrderItemFields.PRICE, price)
                    .setField(OrderItemFields.TIME,time)
                    .build();

            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
