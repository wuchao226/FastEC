package com.wuchao.latte.ui.camera;

import android.net.Uri;

/**
 * @author: wuchao
 * @date: 2018/1/4 23:18
 * @desciption: 存储一些中间值
 */

public class CameraImageBean {

    private Uri mPath = null;

    private static final class Holder {
        private static final CameraImageBean INSTANCE = new CameraImageBean();
    }

    public static CameraImageBean getInstance() {
        return Holder.INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri path) {
        mPath = path;
    }
}
