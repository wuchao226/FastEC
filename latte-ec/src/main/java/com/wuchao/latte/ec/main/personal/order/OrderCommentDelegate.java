package com.wuchao.latte.ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.wuchao.ec.R;
import com.wuchao.ec.R2;
import com.wuchao.latte.delegates.LatteDelegate;
import com.wuchao.latte.ui.widget.AutoPhotoLayout;
import com.wuchao.latte.ui.widget.StarLayout;
import com.wuchao.latte.util.callback.CallbackManager;
import com.wuchao.latte.util.callback.CallbackType;
import com.wuchao.latte.util.callback.IGlobalCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: wuchao
 * @date: 2018/1/9 22:37
 * @desciption:
 */

public class OrderCommentDelegate extends LatteDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout = null;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout = null;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit() {
        Toast.makeText(getContext(), "评分： " + mStarLayout.getStarCount(), Toast.LENGTH_LONG).show();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
            @Override
            public void executeCallback(@NonNull Uri args) {
                mAutoPhotoLayout.onCropTarget(args);
            }
        });
    }
}
