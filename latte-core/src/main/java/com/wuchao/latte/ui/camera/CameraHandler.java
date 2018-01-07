package com.wuchao.latte.ui.camera;


import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.FileUtils;
import com.wuchao.latte.R;
import com.wuchao.latte.delegates.PermissionCheckerDelegate;
import com.wuchao.latte.util.file.FileUtil;

import java.io.File;

/**
 * @author: wuchao
 * @date: 2018/1/4 23:17
 * @desciption: 照片处理类
 */

public class CameraHandler implements View.OnClickListener {

    private final PermissionCheckerDelegate mDelegate;
    private final AlertDialog mDialog;

    public CameraHandler(PermissionCheckerDelegate delegate) {
        mDelegate = delegate;
        mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    final void beginCameraDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams param = window.getAttributes();
            param.width = WindowManager.LayoutParams.MATCH_PARENT;
            //让该window后所有的东西都成暗淡（dim）
            param.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            //dimAmount在0.0f和1.0f之间，0.0f完全不暗，即背景是可见的 ，1.0f时候，背景全部变黑暗。
            //如果要达到背景全部变暗的效果，需要设置
            //dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            // 否则，背景无效果。此方法可以用来设置浮动层
            param.dimAmount = 0.5f;
            window.setAttributes(param);

            window.findViewById(R.id.photodialog_btn_cancel).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_take).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_native).setOnClickListener(this);
        }
    }

    private String getPhotoName() {
        return FileUtil.getFileNameByTime("IMG", "jpg");
    }

    private void pickPhoto() {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        mDelegate.startActivityForResult(Intent.createChooser(intent, "选择获取图片的方式"), RequestCodes.PICK_PHOTO);
    }

    private void takePhoto() {
        final String currentPhotoName = getPhotoName();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final File tempFile = new File(FileUtil.CAMERA_PHOTO_DIR, currentPhotoName);
        //兼容7.0及以上写法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getPath());
            final Uri uri = mDelegate.getContext().getContentResolver()
                    .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            //需要将Uri路径转为实际路径
            final File realFile =
                    FileUtils.getFileByPath(FileUtil.getRealFilePath(mDelegate.getContext(), uri));
            final Uri realUri = Uri.fromFile(realFile);

            CameraImageBean.getInstance().setPath(realUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            final Uri fileUri = Uri.fromFile(tempFile);
            CameraImageBean.getInstance().setPath(fileUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        }
        mDelegate.startActivityForResult(intent, RequestCodes.TAKE_PHOTO);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.photodialog_btn_cancel) {
            mDialog.cancel();
        } else if (id == R.id.photodialog_btn_take) {
            takePhoto();
            mDialog.cancel();
        } else if (id == R.id.photodialog_btn_native) {
            pickPhoto();
            mDialog.cancel();
        }
    }

}
