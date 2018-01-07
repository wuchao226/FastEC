package com.wuchao.latte.ui.camera;

import android.net.Uri;

import com.wuchao.latte.delegates.PermissionCheckerDelegate;
import com.wuchao.latte.util.file.FileUtil;

/**
 * @author: wuchao
 * @date: 2018/1/4 23:17
 * @desciption: 照相机调用类
 */

public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
