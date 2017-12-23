package com.wuchao.latte.ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author: wuchao
 * @date: 2017/12/20 21:18
 * @desciption:
 */

public class SectionBean extends SectionEntity<SectionContentItemEntity> {

    private boolean mIsMore = false;
    private int mId = -1;

    public SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public boolean isMore() {
        return mIsMore;
    }

    public void setIsMore(boolean more) {
        mIsMore = more;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
