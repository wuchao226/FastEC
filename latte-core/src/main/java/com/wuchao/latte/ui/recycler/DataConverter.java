package com.wuchao.latte.ui.recycler;

import java.util.ArrayList;

/**
 * @author: wuchao
 * @date: 2017/12/6 21:46
 * @desciption:
 */

public abstract class DataConverter {

    protected ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }

    public void clearData() {
        ENTITIES.clear();
    }

}
